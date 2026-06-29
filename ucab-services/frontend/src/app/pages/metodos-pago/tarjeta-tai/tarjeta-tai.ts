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

  public posTerminal: string = '';
  public reciboDigital: string = '';

  private apiUrl = 'http://localhost:8081/api/pagos/tai';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private tasaCambioService: TasaCambioService,
    private folioConsumoService: FolioConsumoService
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
      totalPagado: this.totalPagarVes
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
