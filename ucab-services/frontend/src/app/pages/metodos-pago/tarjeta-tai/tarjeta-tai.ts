import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router'; 
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { TasaCambioService } from '../../../services/tasa-cambio.service'; 
import { FolioConsumoService } from '../../../services/folio-consumo.service';
import { PagoService } from '../../../services/pago.service';

@Component({
  selector: 'app-tarjeta-tai',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], 
  templateUrl: './tarjeta-tai.html',
  styleUrls: ['./tarjeta-tai.css']
})
export class TarjetaTai implements OnInit {
  
  public cargandoTasa: boolean = true;
  public procesandoPago: boolean = false;
  public pagoExitoso: boolean = false;

  public folioId: string = ''; 
  public tasaActual: number = 0;
  
  public subtotalUsd: number = 0; 
  public ivaUsd: number = 0; 
  public totalPagarUsd: number = 0;
  
  public subtotalVes: number = 0;
  public ivaVes: number = 0;
  public totalPagarVes: number = 0;
  public montoAPagarVes: number = 0;

  public posTerminal: string = '';
  public reciboDigital: string = '';

  private apiUrl = 'http://localhost:8081/api/pagos/tai';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private tasaCambioService: TasaCambioService,
    private folioConsumoService: FolioConsumoService,
    private pagoService: PagoService
  ) {}

  ngOnInit(): void {
    this.cargarFolioDinamico();
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
          folioSeleccionado = listaFolios[0];
        }

        if (folioSeleccionado) {
          this.folioId = folioSeleccionado.numeroControlFactura ?? 
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
                    this.ivaUsd = 0;
                    this.totalPagarUsd = saldoUsdRestante;

                    this.subtotalVes = saldoInfo.saldoRestanteVes;
                    this.ivaVes = 0;
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
                    this.subtotalUsd = 50.00;
                    this.ivaUsd = this.subtotalUsd * 0.16;
                    this.obtenerTasaYCalcularMontos();
                  }
                });
              }
            },
            error: () => {
              this.subtotalUsd = 50.00;
              this.ivaUsd = this.subtotalUsd * 0.16;
              this.obtenerTasaYCalcularMontos();
            }
          });
        } else {
          this.asignarValoresPrueba('F-DESARROLLO', 50.00);
        }
      },
      error: (err) => {
        this.asignarValoresPrueba('F-FALLBACK-LOCAL', 50.00);
      }
    });
  }

  private asignarValoresPrueba(id: string, monto: number): void {
    this.folioId = id;
    this.subtotalUsd = monto;
    this.ivaUsd = this.subtotalUsd * 0.16;
    this.obtenerTasaYCalcularMontos();
  }

  obtenerTasaYCalcularMontos(): void {
    this.tasaCambioService.obtenerTasaVES().subscribe({
      next: (tasaBCV) => {
        this.tasaActual = tasaBCV; 
        
        this.totalPagarUsd = this.subtotalUsd + this.ivaUsd;
        this.subtotalVes = this.subtotalUsd * this.tasaActual;
        this.ivaVes = this.ivaUsd * this.tasaActual;
        
        // TAI might have processing fees or not, assume none for now.
        this.totalPagarVes = this.subtotalVes + this.ivaVes;
        this.montoAPagarVes = this.totalPagarVes;
        
        this.cargandoTasa = false;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        this.cargandoTasa = false;
        this.cdr.detectChanges();
      }
    });
  }

  registrarPago(): void {
    if (!this.posTerminal || !this.reciboDigital) {
      alert('Por favor, complete todos los campos requeridos.');
      return;
    }

    this.procesandoPago = true;

    const payload = {
      folioId: this.folioId,
      posTerminal: this.posTerminal,
      reciboDigital: this.reciboDigital,
      // Se pasa el totalPagado en Bs porque el backend verifica deuda en Bs.
      totalPagado: this.montoAPagarVes,
      montoTotalVes: this.totalPagarVes
    };

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http.post(this.apiUrl, payload, { headers, responseType: 'text' }).pipe(
      catchError((error: HttpErrorResponse) => {
        this.procesandoPago = false;
        this.cdr.detectChanges(); 
        alert(error.error || 'Ocurrió un error al procesar el pago TAI. Reintente.');
        return throwError(() => new Error('Error al enviar la solicitud de pago TAI.'));
      })
    ).subscribe({
      next: (response) => {
        this.procesandoPago = false;
        this.pagoExitoso = true; 
        this.cdr.detectChanges(); 
      },
      error: (err) => { }
    });
  }
}
