import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { TasaCambioService } from '../../../services/tasa-cambio.service';
import { FolioConsumoService } from '../../../services/folio-consumo.service';
import { BilleteraService } from '../../../services/billetera.service';
import { AuthService } from '../../../services/auth.service';
import { PagoService } from '../../../services/pago.service';

@Component({
  selector: 'app-tarjeta',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './tarjeta.html',
  styleUrls: ['./tarjeta.css'],
})
export class Tarjeta implements OnInit {
  public cargandoTasa: boolean = true;
  public procesandoPago: boolean = false;
  public pagoExitoso: boolean = false;

  public isRecargaTai: boolean = false;
  public montoRecargaVes: number = 0;

  public folioId: string = '';
  public tasaActual: number = 0;

  public subtotalUsd: number = 0;
  public ivaUsd: number = 0;

  public subtotalVes: number = 0;
  public ivaVes: number = 0;
  public tasaProcesamientoVes: number = 0;
  public totalPagarVes: number = 0;
  public montoAPagarVes: number = 0;

  // Modelos del formulario HTML
  public tipoRed: string = '';
  public compania: string = '';
  public mesVencimiento: string = '';
  public anioVencimiento: string = '';
  public numTarjeta: string = '';
  public cvv: string = ''; // No se guarda en DB por seguridad, pero se pide en UI

  private apiUrl = 'http://localhost:8081/api/pagos/tarjeta';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private tasaCambioService: TasaCambioService,
    private folioConsumoService: FolioConsumoService,
    private billeteraService: BilleteraService,
    private authService: AuthService,
    private pagoService: PagoService,
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
        let listaFolios: any[] = [];
        if (Array.isArray(res)) {
          listaFolios = res;
        } else if (res && typeof res === 'object') {
          listaFolios = [res];
        }

        if (listaFolios.length === 0 || !listaFolios[0]) {
          this.asignarValoresPrueba('F-2026-DEFAULT', 50.0);
          return;
        }

        let folioSeleccionado = null;

        if (folioUrl) {
          folioSeleccionado = listaFolios.find(
            (f) =>
              f &&
              (f.numeroControlFactura === folioUrl ||
                f.numeroControl === folioUrl ||
                f.identificador === folioUrl ||
                String(f.id) === folioUrl),
          );
        }

        if (!folioSeleccionado) {
          folioSeleccionado = listaFolios[0];
        }

        if (folioSeleccionado) {
          this.folioId =
            folioSeleccionado.numeroControlFactura ??
            folioSeleccionado.numeroControl ??
            folioSeleccionado.identificador ??
            (folioSeleccionado.id ? String(folioSeleccionado.id) : 'F-DESARROLLO');

          // ================================================================
          // CORRECCIÓN MULTI-PAGO:
          // Consultar el saldo restante real de la factura (en VES/Bs).
          // Si existe → saldo en Bs ÷ tasa BCV = USD restante por cobrar.
          // Si no existe (primer pago) → usar cargos originales en USD.
          // ================================================================
          this.pagoService.obtenerSaldoFacturaPorFolio(this.folioId).subscribe({
            next: (saldoInfo) => {
              if (saldoInfo.tieneFactura && saldoInfo.saldoRestanteVes != null && saldoInfo.saldoRestanteVes > 0) {
                this.tasaCambioService.obtenerTasaVES().subscribe({
                  next: (tasaBCV) => {
                    this.tasaActual = tasaBCV;
                    const saldoUsdRestante = saldoInfo.saldoRestanteVes / tasaBCV;
                    this.subtotalUsd = saldoUsdRestante;
                    this.ivaUsd = 0; // El saldo ya incluye IVA

                    this.subtotalVes = saldoInfo.saldoRestanteVes;
                    this.ivaVes = 0;
                    const tasaProcesamientoVes = this.subtotalVes * 0.03;
                    this.tasaProcesamientoVes = tasaProcesamientoVes;
                    this.totalPagarVes = this.subtotalVes + tasaProcesamientoVes;
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
                // Primer pago: leer montos originales en USD desde los cargos
                this.folioConsumoService.obtenerCargosPorFolio(this.folioId).subscribe({
                  next: (cargos) => {
                    let subtotal = cargos.reduce((suma, cargo) => suma + (Number(cargo.monto) || 0), 0);
                    if (subtotal === 0) subtotal = 25.00;
                    this.subtotalUsd = subtotal;
                    this.ivaUsd = this.subtotalUsd * 0.16;
                    this.obtenerTasaYCalcularMontos();
                  },
                  error: () => {
                    this.subtotalUsd = 50.0;
                    this.ivaUsd = this.subtotalUsd * 0.16;
                    this.obtenerTasaYCalcularMontos();
                  }
                });
              }
            },
            error: () => {
              this.subtotalUsd = 50.0;
              this.ivaUsd = this.subtotalUsd * 0.16;
              this.obtenerTasaYCalcularMontos();
            }
          });
        } else {
          this.asignarValoresPrueba('F-DESARROLLO', 50.0);
        }
      },
      error: (err) => {
        this.asignarValoresPrueba('F-FALLBACK-LOCAL', 50.0);
      },
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
          this.totalPagarVes = this.subtotalVes;
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

  obtenerTasaYCalcularMontos(): void {
    this.tasaCambioService.obtenerTasaVES().subscribe({
      next: (tasaBCV) => {
        this.tasaActual = tasaBCV;

        this.subtotalVes = this.subtotalUsd * this.tasaActual;
        this.ivaVes = this.ivaUsd * this.tasaActual;

        const subtotalMasIvaVes = this.subtotalVes + this.ivaVes;
        this.tasaProcesamientoVes = subtotalMasIvaVes * 0.03; // Tarjeta suele tener otra tasa, pongamos 3%

        this.totalPagarVes = subtotalMasIvaVes + this.tasaProcesamientoVes;
        this.montoAPagarVes = this.totalPagarVes;

        this.cargandoTasa = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.cargandoTasa = false;
        this.cdr.detectChanges();
      },
    });
  }

  registrarPago(): void {
    if (
      !this.tipoRed ||
      !this.compania ||
      !this.mesVencimiento ||
      !this.anioVencimiento ||
      !this.numTarjeta
    ) {
      alert('Por favor, complete todos los campos del formulario.');
      return;
    }

    // Convert MM and YY to YYYY-MM-DD
    const month = this.mesVencimiento.padStart(2, '0');
    let year = this.anioVencimiento;

    // Check for valid year input
    if (!/^\d{2}$/.test(year)) {
      alert('Por favor, ingrese un año válido de 2 dígitos (ej. 28).');
      return;
    }

    year = '20' + year;
    const fechaDate = `${year}-${month}-01`;

    // Validación de fecha de vencimiento
    const today = new Date();
    // Normalizamos el mes actual al primer día para comparar solo mes/año
    const currentMonth = new Date(today.getFullYear(), today.getMonth(), 1);

    // Parseamos la fechaDate creada antes para convertirla a objeto Date
    const partsFecha = fechaDate.split('-');
    const expirationDate = new Date(
      parseInt(partsFecha[0], 10),
      parseInt(partsFecha[1], 10) - 1,
      1,
    );

    if (expirationDate < currentMonth) {
      alert('La tarjeta ingresada está vencida. Por favor, intente con una tarjeta válida.');
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

    const totalPagadoUsd = this.subtotalUsd + this.ivaUsd;

    const payload = {
      folioId: this.folioId,
      tipoRed: this.tipoRed,
      compania: this.compania,
      fechaVencimiento: fechaDate,
      numTarjeta: this.numTarjeta,
      totalPagado: totalPagadoUsd,         // Monto en USD — se guarda como monto_liquidacion
      montoTotalVes: this.totalPagarVes,   // Total en VES con comisión — para la Factura
    };

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http
      .post(this.apiUrl, payload, { headers, responseType: 'text' })
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.procesandoPago = false;
          this.cdr.detectChanges();
          alert(error.error || 'Ocurrió un error al procesar el pago con tarjeta. Reintente.');
          return throwError(() => new Error('Error al enviar la solicitud de pago con tarjeta.'));
        }),
      )
      .subscribe({
        next: (response) => {
          this.procesandoPago = false;
          this.pagoExitoso = true;
          this.cdr.detectChanges();
        },
        error: (err) => {},
      });
  }
}
