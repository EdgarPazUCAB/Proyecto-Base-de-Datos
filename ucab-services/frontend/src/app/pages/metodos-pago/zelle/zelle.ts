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
  selector: 'app-zelle',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], 
  templateUrl: './zelle.html',
  styleUrls: ['./zelle.css']
})
export class Zelle implements OnInit {
  
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
  public totalPagarUsd: number = 0;
  
  // Conversiones e impuestos en VES (Bolívares) - Zelle se paga en dólares pero puede requerir cálculo para registro
  public subtotalVes: number = 0;
  public ivaVes: number = 0;
  public totalPagarVes: number = 0;

  // Modelos del formulario HTML
  public nombreTitular: string = '';
  public correoOrigen: string = '';
  public codigoConfirmacion: string = '';

  // Endpoint de la API de pagos
  private apiUrl = 'http://localhost:8081/api/pagos/zelle';

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

  cargarFolioDinamico(): void {
    this.cargandoTasa = true;
    const folioUrl = this.route.snapshot.queryParamMap.get('folio');

    this.folioConsumoService.obtenerFolios().subscribe({
      next: (res) => {
        console.log('--- DATOS RECIBIDOS DEL BACKEND ---', res);

        let listaFolios: any[] = [];
        if (Array.isArray(res)) {
          listaFolios = res;
        } else if (res && typeof res === 'object') {
          listaFolios = [res];
        }

        if (listaFolios.length === 0 || !listaFolios[0]) {
          console.warn('Respuesta vacía o inválida del backend. Activando datos de prueba.');
          this.asignarValoresPrueba('F-2026-DEFAULT', 50.00);
          return;
        }

        let folioSeleccionado = null;

        if (folioUrl) {
          folioSeleccionado = listaFolios.find(f => 
            f && (f.numeroControlFactura === folioUrl || 
                  f.numeroControl === folioUrl || 
                  f.identificador === folioUrl || 
                  String(f.id) === folioUrl)
          );
        }

        if (!folioSeleccionado) {
          folioSeleccionado = listaFolios.find(f => {
            if (!f) return false;
            const deuda = f.saldoRestante ?? f.montoTotal ?? f.totalAcumulado ?? f.total_acumulado ?? f.monto ?? 0;
            return deuda > 0;
          });
        }

        if (!folioSeleccionado) {
          folioSeleccionado = listaFolios[0];
        }

        if (folioSeleccionado) {
          this.folioId = folioSeleccionado.numeroControlFactura ?? 
                         folioSeleccionado.numeroControl ?? 
                         folioSeleccionado.identificador ?? 
                         (folioSeleccionado.id ? String(folioSeleccionado.id) : 'F-DESARROLLO');

          const saldo = folioSeleccionado.saldoRestante ?? 
                        folioSeleccionado.montoTotal ?? 
                        folioSeleccionado.totalAcumulado ?? 
                        folioSeleccionado.total_acumulado ?? 
                        folioSeleccionado.monto ?? 0;

          if (saldo === 0) {
            this.subtotalUsd = 50.00;
            this.ivaUsd = 8.00;
          } else {
            this.subtotalUsd = saldo;
            this.ivaUsd = this.subtotalUsd * 0.16; 
          }
        } else {
          this.asignarValoresPrueba('F-DESARROLLO', 50.00);
        }

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
          this.totalPagarUsd = this.subtotalUsd;
          this.totalPagarVes = this.subtotalVes;
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

  obtenerTasaYCalcularMontos(): void {
    this.tasaCambioService.obtenerTasaVES().subscribe({
      next: (tasaBCV) => {
        this.tasaActual = tasaBCV; 
        
        // Zelle payments are typically in USD, so we calculate total in USD
        this.totalPagarUsd = this.subtotalUsd + this.ivaUsd;
        
        // Conversión cambiaria de montos base para registro en BD local (ves)
        this.subtotalVes = this.subtotalUsd * this.tasaActual;
        this.ivaVes = this.ivaUsd * this.tasaActual;
        
        // Gran total neto a pagar en Bolívares (VES) - para el backend
        this.totalPagarVes = this.subtotalVes + this.ivaVes;
        
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

  registrarPago(): void {
    if (!this.nombreTitular || !this.correoOrigen || !this.codigoConfirmacion) {
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

    const payload = {
      folioId: this.folioId,
      nombreTitular: this.nombreTitular,
      correoOrigen: this.correoOrigen,
      codigoConfirmacion: this.codigoConfirmacion,
      // Se pasa el totalPagado en Bs porque el backend verifica deuda en Bs (o en la moneda configurada).
      totalPagado: this.totalPagarVes
    };

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http.post(this.apiUrl, payload, { headers, responseType: 'text' }).pipe(
      catchError((error: HttpErrorResponse) => {
        this.procesandoPago = false;
        this.cdr.detectChanges(); 
        console.error('Error capturado en registrarPago():', error);
        alert(error.error || 'Ocurrió un error al procesar el pago Zelle. Reintente.');
        return throwError(() => new Error('Error al enviar la solicitud de pago Zelle.'));
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
