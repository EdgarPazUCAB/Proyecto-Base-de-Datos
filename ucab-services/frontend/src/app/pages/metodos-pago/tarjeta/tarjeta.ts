import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { TasaCambioService } from '../../../services/tasa-cambio.service';
import { FolioConsumoService } from '../../../services/folio-consumo.service';

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

  public folioId: string = '';
  public tasaActual: number = 0;

  public subtotalUsd: number = 0;
  public ivaUsd: number = 0;

  public subtotalVes: number = 0;
  public ivaVes: number = 0;
  public tasaProcesamientoVes: number = 0;
  public totalPagarVes: number = 0;

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
          folioSeleccionado = listaFolios.find((f) => {
            if (!f) return false;
            const deuda =
              f.saldoRestante ??
              f.montoTotal ??
              f.totalAcumulado ??
              f.total_acumulado ??
              f.monto ??
              0;
            return deuda > 0;
          });
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

          const saldo =
            folioSeleccionado.saldoRestante ??
            folioSeleccionado.montoTotal ??
            folioSeleccionado.totalAcumulado ??
            folioSeleccionado.total_acumulado ??
            folioSeleccionado.monto ??
            0;

          if (saldo === 0) {
            this.subtotalUsd = 50.0;
            this.ivaUsd = 8.0;
          } else {
            this.subtotalUsd = saldo;
            this.ivaUsd = this.subtotalUsd * 0.16;
          }
        } else {
          this.asignarValoresPrueba('F-DESARROLLO', 50.0);
        }

        this.obtenerTasaYCalcularMontos();
      },
      error: (err) => {
        this.asignarValoresPrueba('F-FALLBACK-LOCAL', 50.0);
      },
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

        this.subtotalVes = this.subtotalUsd * this.tasaActual;
        this.ivaVes = this.ivaUsd * this.tasaActual;

        const subtotalMasIvaVes = this.subtotalVes + this.ivaVes;
        this.tasaProcesamientoVes = subtotalMasIvaVes * 0.03; // Tarjeta suele tener otra tasa, pongamos 3%

        this.totalPagarVes = subtotalMasIvaVes + this.tasaProcesamientoVes;

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

    const payload = {
      folioId: this.folioId,
      tipoRed: this.tipoRed,
      compania: this.compania,
      fechaVencimiento: fechaDate,
      numTarjeta: this.numTarjeta,
      totalPagado: this.totalPagarVes,
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
