import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router'; 
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

// IMPORTACIÓN DE SERVICIOS
import { TasaCambioService } from '../../../services/tasa-cambio.service'; 
import { FolioConsumoService } from '../../../services/folio-consumo.service'; 
import { BilleteraService } from '../../../services/billetera.service';
import { AuthService } from '../../../services/auth.service'; 

@Component({
  selector: 'app-pago-movil',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], 
  templateUrl: './pago-movil.html',
  styleUrls: ['./pago-movil.css']
})
export class PagoMovil implements OnInit {
  
  // Variables de control de estado
  public cargandoTasa: boolean = true;
  public procesandoPago: boolean = false;
  public pagoExitoso: boolean = false;

  public isRecargaTai: boolean = false;
  public montoRecargaVes: number = 0;

  // Datos dinámicos del Folio obtenidos del backend
  public folioId: string = ''; 
  public tasaActual: number = 0;
  
  // Montos base en USD
  public subtotalUsd: number = 0; 
  public ivaUsd: number = 0; 
  
  // Conversiones e impuestos en VES (Bolívares)
  public subtotalVes: number = 0;
  public ivaVes: number = 0;
  public tasaProcesamientoVes: number = 0;
  public totalPagarVes: number = 0;
  public montoAPagarVes: number = 0;

  // Modelos del formulario HTML
  public bancoEmisor: string = '';
  public telefonoPrefix: string = '';
  public telefonoNumero: string = '';
  public referencia: string = '';

  // Endpoint de la API de pagos
  private apiUrl = 'http://localhost:8081/api/pagos/movil';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private tasaCambioService: TasaCambioService,
    private folioConsumoService: FolioConsumoService,
    private billeteraService: BilleteraService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const tipo = this.route.snapshot.queryParamMap.get('tipo');
    if (tipo === 'recarga_tai') {
      this.isRecargaTai = true;
      this.montoRecargaVes = Number(this.route.snapshot.queryParamMap.get('monto') || 0);
      this.asignarValoresPrueba('RECARGA-TAI', this.montoRecargaVes);
    } else {
      this.cargarFolioDinamico();
    }
  }

  /**
   * Obtiene y normaliza los folios del backend. Soporta respuestas tipo Array u Objeto único,
   * y mapea los nombres de atributos reales de la base de datos de ucab-services.
   */
  cargarFolioDinamico(): void {
    this.cargandoTasa = true;
    const folioUrl = this.route.snapshot.queryParamMap.get('folio');

    this.folioConsumoService.obtenerFolios().subscribe({
      next: (res) => {
        console.log('--- DATOS RECIBIDOS DEL BACKEND ---', res);

        // 1. NORMALIZACIÓN: Forzar la respuesta a un arreglo para evitar fallos de lectura (.find)
        let listaFolios: any[] = [];
        if (Array.isArray(res)) {
          listaFolios = res;
        } else if (res && typeof res === 'object') {
          listaFolios = [res];
        }

        // Si la lista está vacía o el objeto es nulo, activar el resguardo de pruebas
        if (listaFolios.length === 0 || !listaFolios[0]) {
          console.warn('Respuesta vacía o inválida del backend. Activando datos de prueba.');
          this.asignarValoresPrueba('F-2026-DEFAULT', 50.00);
          return;
        }

        let folioSeleccionado = null;

        // 2. BÚSQUEDA POR URL: Si se especificó un folio en los parámetros de la ruta
        if (folioUrl) {
          folioSeleccionado = listaFolios.find(f => 
            f && (f.numeroControlFactura === folioUrl || 
                  f.numeroControl === folioUrl || 
                  f.identificador === folioUrl || 
                  String(f.id) === folioUrl)
          );
        }

        // 3. BÚSQUEDA POR SALDO ACTIVO: Si no hay URL, buscar el primero que tenga deuda pendiente
        if (!folioSeleccionado) {
          folioSeleccionado = listaFolios.find(f => {
            if (!f) return false;
            const deuda = f.saldoRestante ?? f.montoTotal ?? f.totalAcumulado ?? f.total_acumulado ?? f.monto ?? 0;
            return deuda > 0;
          });
        }

        // 4. FALLBACK DE LISTA: Si ninguno tiene deuda activa, tomar el primero disponible
        if (!folioSeleccionado) {
          folioSeleccionado = listaFolios[0];
        }

        // ===================================================================
        // MAPEO SEGURO COINCIDENTE CON EL BACKEND (Spring Boot / PostgreSQL)
        // ===================================================================
        if (folioSeleccionado) {
          // Extrae el identificador o número de control de factura de forma prioritaria
          this.folioId = folioSeleccionado.numeroControlFactura ?? 
                         folioSeleccionado.numeroControl ?? 
                         folioSeleccionado.identificador ?? 
                         (folioSeleccionado.id ? String(folioSeleccionado.id) : 'F-DESARROLLO');

          // Extrae el monto de la deuda usando el nombre real de tus columnas
          const saldo = folioSeleccionado.saldoRestante ?? 
                        folioSeleccionado.montoTotal ?? 
                        folioSeleccionado.totalAcumulado ?? 
                        folioSeleccionado.total_acumulado ?? 
                        folioSeleccionado.monto ?? 0;

          if (saldo === 0) {
            // Evita montos en cero durante pruebas locales si el registro de la BD está vacío
            this.subtotalUsd = 50.00;
            this.ivaUsd = 8.00;
          } else {
            this.subtotalUsd = saldo;
            this.ivaUsd = this.subtotalUsd * 0.16; // 16% de IVA estipulado
          }
        } else {
          this.asignarValoresPrueba('F-DESARROLLO', 50.00);
        }

        // LLAMADA CRÍTICA: Ejecuta el cálculo en bolívares con los montos asignados
        this.obtenerTasaYCalcularMontos();
      },
      error: (err) => {
        console.error('Error crítico al conectar con FolioConsumoService:', err);
        this.asignarValoresPrueba('F-FALLBACK-LOCAL', 50.00);
      }
    });
  }

  private asignarValoresPrueba(id: string, monto: number): void {
    this.folioId = id;
    if (this.isRecargaTai) {
      this.subtotalVes = monto;
      this.ivaVes = 0;
      this.tasaCambioService.obtenerTasaVES().subscribe({
        next: (tasaBCV) => {
          this.tasaActual = tasaBCV;
          this.subtotalUsd = monto / tasaBCV;
          this.ivaUsd = 0;
          const subtotalMasIvaVes = this.subtotalVes + this.ivaVes;
          this.tasaProcesamientoVes = subtotalMasIvaVes * 0.015; 
          this.totalPagarVes = subtotalMasIvaVes + this.tasaProcesamientoVes;
        this.montoAPagarVes = this.totalPagarVes;
          this.cargandoTasa = false;
          this.cdr.detectChanges();
        },
        error: () => {
          this.cargandoTasa = false;
          this.cdr.detectChanges();
        }
      });
    } else {
      this.subtotalUsd = monto;
      this.ivaUsd = this.subtotalUsd * 0.16;
      this.obtenerTasaYCalcularMontos();
    }
  }

  /**
   * Consume el servicio de divisas y realiza el desglose aritmético para la vista.
   */
  obtenerTasaYCalcularMontos(): void {
    this.tasaCambioService.obtenerTasaVES().subscribe({
      next: (tasaBCV) => {
        this.tasaActual = tasaBCV; 
        
        // Conversión cambiaria de montos base
        this.subtotalVes = this.subtotalUsd * this.tasaActual;
        this.ivaVes = this.ivaUsd * this.tasaActual;
        
        // Comisión bancaria estándar por procesamiento de Pago Móvil (1.5%)
        const subtotalMasIvaVes = this.subtotalVes + this.ivaVes;
        this.tasaProcesamientoVes = subtotalMasIvaVes * 0.015; 
        
        // Gran total neto a pagar en Bolívares (VES)
        this.totalPagarVes = subtotalMasIvaVes + this.tasaProcesamientoVes;
        this.montoAPagarVes = this.totalPagarVes;
        
        this.cargandoTasa = false;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error("Error obteniendo la tasa cambiaria base VES:", err);
        this.cargandoTasa = false;
        this.cdr.detectChanges();
      }
    });
  }

  /**
   * Transmite el payload estructurado con el pago móvil asentado hacia el backend.
   */
  registrarPago(): void {
    if (!this.bancoEmisor || !this.telefonoPrefix || !this.telefonoNumero || !this.referencia) {
      alert('Por favor, complete todos los campos del formulario.');
      return;
    }

    this.procesandoPago = true;
    
    if (this.isRecargaTai) {
      const usuario = this.authService.obtenerUsuarioActual();
      if (!usuario || !usuario.cedulaMiembro) {
        alert('Debe iniciar sesión para recargar');
        this.procesandoPago = false;
        return;
      }
      this.billeteraService.recargarSaldo(usuario.cedulaMiembro, this.subtotalVes).subscribe({
        next: () => {
          this.procesandoPago = false;
          this.pagoExitoso = true;
          this.cdr.detectChanges();
        },
        error: (err) => {
          this.procesandoPago = false;
          alert('Error al recargar saldo: ' + (err.error?.error || err.message || 'Error desconocido'));
          this.cdr.detectChanges();
        }
      });
      return;
    }

    const telefonoCompleto = `${this.telefonoPrefix}-${this.telefonoNumero}`;

    const payload = {
      folioId: this.folioId,
      bancoEmisor: this.bancoEmisor,
      telefonoEmisor: telefonoCompleto,
      referencia: this.referencia,
      totalPagado: this.montoAPagarVes,
      montoTotalVes: this.totalPagarVes
    };

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http.post(this.apiUrl, payload, { headers, responseType: 'text' }).pipe(
      catchError((error: HttpErrorResponse) => {
        this.procesandoPago = false;
        this.cdr.detectChanges(); 
        console.error('Error capturado en registrarPago():', error);
        alert(error.error || 'Ocurrió un error al procesar el pago móvil. Reintente.');
        return throwError(() => new Error('Error al enviar la solicitud de pago móvil.'));
      })
    ).subscribe({
      next: (response) => {
        this.procesandoPago = false;
        this.pagoExitoso = true; 
        this.cdr.detectChanges(); 
        console.log('Pago registrado de forma exitosa:', response);
      },
      error: (err) => { /* Capturado en pipe */ }
    });
  }
}