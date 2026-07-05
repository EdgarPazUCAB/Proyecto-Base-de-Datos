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

@Component({
  selector: 'app-criptomonedas',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './criptomonedas.html',
  styleUrls: ['./criptomonedas.css'],
})
export class Criptomonedas implements OnInit {
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
  public totalPagarVes: number = 0;

  public totalPagarUsdt: number = 0;

  // Modelos del formulario HTML
  public redBlockchain: string = '';
  public dxid: string = '';
  public billeteraDestino: string = 'TQn9Y2khEsLMG6jzWG3qXKQwxhfaJVqBdz'; // Fija para mostrar al usuario

  private apiUrl = 'http://localhost:8081/api/pagos/cripto';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private tasaCambioService: TasaCambioService,
    private folioConsumoService: FolioConsumoService,
    private billeteraService: BilleteraService,
    private authService: AuthService,
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
    if (this.isRecargaTai) {
      this.subtotalVes = monto;
      this.ivaVes = 0;
      this.tasaCambioService.obtenerTasaVES().subscribe({
        next: (tasaBCV) => {
          this.tasaActual = tasaBCV;
          this.subtotalUsd = monto / tasaBCV;
          this.ivaUsd = 0;
          this.totalPagarUsdt = this.subtotalUsd;
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

        this.subtotalVes = this.subtotalUsd * this.tasaActual;
        this.ivaVes = this.ivaUsd * this.tasaActual;

        // Crypto no tiene tasa de procesamiento adicional por defecto en este requerimiento,
        // pero se asume 1:1 con el monto USD + IVA para calcular el USDT
        const subtotalMasIvaUsd = this.subtotalUsd + this.ivaUsd;
        this.totalPagarUsdt = subtotalMasIvaUsd;

        // El total pagado que se asienta en base de datos será su equivalente en Bolívares
        this.totalPagarVes = this.totalPagarUsdt * this.tasaActual;

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
    if (!this.redBlockchain || !this.dxid) {
      alert('Por favor, complete todos los campos (Red Blockchain y DXID).');
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
      dxid: this.dxid,
      redBlockchain: this.redBlockchain,
      billetera: this.billeteraDestino,
      tasaConversion: this.tasaActual,
      totalPagado: this.totalPagarVes,
    };

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http
      .post(this.apiUrl, payload, { headers, responseType: 'text' })
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.procesandoPago = false;
          this.cdr.detectChanges();
          alert(
            error.error || 'Ocurrió un error al procesar el pago con criptomonedas. Reintente.',
          );
          return throwError(
            () => new Error('Error al enviar la solicitud de pago con criptomonedas.'),
          );
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

  copiarBilletera() {
    navigator.clipboard.writeText(this.billeteraDestino).then(() => {
      alert('Dirección de billetera copiada al portapapeles');
    });
  }
}
