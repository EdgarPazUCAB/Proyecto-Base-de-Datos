import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { FolioConsumoService, Folio } from '../../services/folio-consumo.service';
import { PagoService } from '../../services/pago.service';
import { AuthService } from '../../services/auth.service';

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
  
  historialPagos: any[] = [];
  cargandoHistorial: boolean = true;

  constructor(
    private folioService: FolioConsumoService,
    private pagoService: PagoService,
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const sesion = this.authService.obtenerUsuarioActual();
    if (!sesion || !sesion.cedulaMiembro) {
      this.router.navigate(['/login']);
      return;
    }

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

    this.folioService.obtenerFolios().subscribe({
      next: (folios) => {
        const abierto = folios.find(f => f.estadoFolio?.toLowerCase() === 'abierto');
        
        if (abierto) {
          this.folioPendiente = abierto;
          
          // ¡MAGIA AQUÍ! Buscamos los cargos reales para que el cálculo sea idéntico
          this.folioService.obtenerCargosPorFolio(abierto.identificador).subscribe({
            next: (cargos) => {
              let subtotal = cargos.reduce((suma, cargo) => suma + (Number(cargo.monto) || 0), 0);
              
              // Regla de seguridad compartida
              if (subtotal === 0) subtotal = 25.00;
              
              this.totalAPagarUsd = subtotal * 1.16;
              this.cargando = false;
              this.cdr.detectChanges();
            },
            error: () => {
              this.totalAPagarUsd = 25.00 * 1.16; // Si falla, usamos el base
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

  continuarPago(): void {
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