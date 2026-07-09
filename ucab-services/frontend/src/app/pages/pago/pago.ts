import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FolioConsumoService, Folio } from '../../services/folio-consumo.service';
import { PagoService } from '../../services/pago.service';
import { AuthService } from '../../services/auth.service';
import { TasaCambioService } from '../../services/tasa-cambio.service';

@Component({
  selector: 'app-pago',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule], 
  templateUrl: './pago.html',
  styleUrl: './pago.css',
})
export class Pago implements OnInit {
  
  folioPendiente: Folio | null = null;
  cargando: boolean = true;
  error: string = '';

  metodoSeleccionado: string = 'pagomovil'; 
  totalAPagarUsd: number = 0; 
  
  isRecargaTai: boolean = false;
  montoRecarga: number = 100; // Monto por defecto

  historialPagos: any[] = [];
  cargandoHistorial: boolean = true;
  esAdmin: boolean = false;

  mostrandoModalFactura: boolean = false;
  cargandoFactura: boolean = false;
  detalleFactura: any = null;

  constructor(
    private folioService: FolioConsumoService,
    private pagoService: PagoService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef,
    private tasaCambioService: TasaCambioService
  ) {}

  ngOnInit(): void {
    const sesion = this.authService.obtenerUsuarioActual();
    if (!sesion || !sesion.cedulaMiembro) {
      this.router.navigate(['/login']);
      return;
    }

    this.esAdmin = sesion.correo?.includes('@adm.') ?? false;

    // Cargar historial de pagos siempre
    this.pagoService.obtenerHistorialPagos(sesion.cedulaMiembro).subscribe({
      next: (historial) => {
        this.historialPagos = historial;
        this.cargandoHistorial = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error cargando historial de pagos:', err);
        this.cargandoHistorial = false;
        this.cdr.detectChanges();
      }
    });

    this.route.queryParams.subscribe(params => {
      this.isRecargaTai = params['tipo'] === 'recarga_tai';
      
      if (this.isRecargaTai) {
        this.metodoSeleccionado = 'pagomovil'; // TAI not allowed
        this.cargando = false;
        this.cdr.detectChanges();
      } else {
        this.folioService.obtenerFoliosPorUsuario(sesion.cedulaMiembro!).subscribe({
          next: (folios) => {
            const abierto = folios.find(f => f.estadoFolio?.toLowerCase() === 'abierto');
            
            if (abierto) {
              this.folioPendiente = abierto;
              
              // Verificamos primero si hay un saldo restante en una factura (pagos parciales)
              this.pagoService.obtenerSaldoFacturaPorFolio(abierto.identificador).subscribe({
                next: (saldoInfo) => {
                  if (saldoInfo.tieneFactura && saldoInfo.saldoRestanteVes != null && saldoInfo.saldoRestanteVes > 0) {
                    this.tasaCambioService.obtenerTasaVES().subscribe({
                      next: (tasaBCV) => {
                        this.totalAPagarUsd = saldoInfo.saldoRestanteVes / tasaBCV;
                        this.cargando = false;
                        this.cdr.detectChanges();
                      },
                      error: () => {
                        this.totalAPagarUsd = saldoInfo.saldoRestanteVes / 36.5; // fallback
                        this.cargando = false;
                        this.cdr.detectChanges();
                      }
                    });
                  } else {
                    // Si no hay factura parcial, buscamos los cargos reales (primer pago)
                    this.folioService.obtenerCargosPorFolio(abierto.identificador).subscribe({
                      next: (cargos) => {
                        let subtotal = cargos.reduce((suma, cargo) => suma + (Number(cargo.monto) || 0), 0);
                        if (subtotal === 0) subtotal = 25.00;
                        
                        this.totalAPagarUsd = subtotal * 1.16;
                        this.cargando = false;
                        this.cdr.detectChanges();
                      },
                      error: () => {
                        this.totalAPagarUsd = 25.00 * 1.16;
                        this.cargando = false;
                        this.cdr.detectChanges();
                      }
                    });
                  }
                },
                error: () => {
                  this.cargando = false;
                  this.cdr.detectChanges();
                }
              });

            } else {
              this.error = 'No tienes ninguna solicitud de servicio pendiente por pagar.';
              this.cargando = false;
              this.cdr.detectChanges();
            }
          },
          error: (err) => {
            console.error('Error al cargar folios:', err);
            this.error = 'No pudimos verificar tus solicitudes por un error de conexión.';
            this.cargando = false;
            this.cdr.detectChanges();
          }
        });
      }
    });
  }

  continuarPago(): void {
    if (this.isRecargaTai) {
      if (!this.montoRecarga || this.montoRecarga <= 0) {
        alert('Por favor ingrese un monto válido mayor a 0');
        return;
      }
      
      const queryParams = { tipo: 'recarga_tai', monto: this.montoRecarga };
      switch (this.metodoSeleccionado) {
        case 'pagomovil': this.router.navigate(['/pago-movil'], { queryParams }); break;
        case 'tarjeta': this.router.navigate(['/tarjeta'], { queryParams }); break;
        case 'crypto': this.router.navigate(['/criptomonedas'], { queryParams }); break;
        case 'zelle': this.router.navigate(['/zelle'], { queryParams }); break;
        default: alert('Este método de pago no está disponible para recargas.'); break;
      }
    } else {
      if (!this.folioPendiente) return;

      switch (this.metodoSeleccionado) {
        case 'pagomovil':
          this.router.navigate(['/pago-movil'], { queryParams: { folio: this.folioPendiente.identificador } });
          break;
        case 'tarjeta':
          this.router.navigate(['/tarjeta'], { queryParams: { folio: this.folioPendiente.identificador } });
          break;
        case 'tai':
          this.router.navigate(['/tarjeta-tai'], { queryParams: { folio: this.folioPendiente.identificador } });
          break;
        case 'crypto':
          this.router.navigate(['/criptomonedas'], { queryParams: { folio: this.folioPendiente.identificador } });
          break;
        case 'zelle':
          this.router.navigate(['/zelle'], { queryParams: { folio: this.folioPendiente.identificador } });
          break;
        default:
          alert('Este método de pago estará disponible muy pronto.');
          break;
      }
    }
  }

  abrirModalFactura(numeroControl: string): void {
    if (!numeroControl) return;
    this.mostrandoModalFactura = true;
    this.cargandoFactura = true;
    this.detalleFactura = null;
    
    this.pagoService.obtenerDetalleFactura(numeroControl).subscribe({
      next: (detalle) => {
        this.detalleFactura = detalle;
        this.cargandoFactura = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error cargando detalle de factura:', err);
        alert('No se pudo cargar el detalle de la factura. Verifique su conexión.');
        this.cargandoFactura = false;
        this.mostrandoModalFactura = false;
        this.cdr.detectChanges();
      }
    });
  }

  cerrarModalFactura(): void {
    this.mostrandoModalFactura = false;
    this.detalleFactura = null;
  }
}