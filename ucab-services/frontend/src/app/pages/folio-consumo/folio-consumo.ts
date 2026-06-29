import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FolioConsumoService, Folio, Cargo } from '../../services/folio-consumo.service';
import { AuthService } from '../../services/auth.service';
import { SolicitudServicioService } from '../../services/solicitud-servicio.service';

@Component({
  selector: 'app-folio-consumo',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './folio-consumo.html',
  styleUrl: './folio-consumo.css',
})
export class FolioConsumo implements OnInit {
  codigoServicio: string = '';
  folio: Folio | null = null;
  cargos: Cargo[] = [];
  
  cargando: boolean = true;
  error: string = '';

  // Variables de facturación
  subtotal: number = 0;
  iva: number = 0;
  total: number = 0;

  constructor(
    private route: ActivatedRoute,
    private folioService: FolioConsumoService,
    private authService: AuthService,
    private solicitudService: SolicitudServicioService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.codigoServicio = params['servicio'] || 'General';
      this.cargarFolioReal();
    });
  }

  cargarFolioReal(): void {
    this.cargando = true;
    const usuario = this.authService.obtenerUsuarioActual();
    if (!usuario || !usuario.cedulaMiembro) {
      this.error = 'Debe iniciar sesión para ver sus folios.';
      this.cargando = false;
      return;
    }

    this.solicitudService.obtenerSolicitudes().subscribe({
      next: (solicitudes: any[]) => {
        // Buscar la última solicitud del usuario para este servicio
        const misSolicitudes = solicitudes
          .filter((s: any) => s.miembro?.cedulaMiembro === usuario.cedulaMiembro && s.servicio?.codigoServicio === this.codigoServicio)
          .sort((a: any, b: any) => new Date(b.fechaCreacion).getTime() - new Date(a.fechaCreacion).getTime());

        if (misSolicitudes.length === 0) {
          this.error = 'No tienes ninguna solicitud registrada para este servicio.';
          this.cargando = false;
          this.cdr.detectChanges();
          return;
        }

        const ultimaSolicitudId = misSolicitudes[0].identificador;

        // Ahora buscamos el folio correspondiente a esta solicitud
        this.folioService.obtenerFolios().subscribe({
          next: (folios) => {
            const folioAsociado = folios.find(f => f.identificador === ultimaSolicitudId);
            
            if (folioAsociado) {
              this.folio = folioAsociado;
              this.cargarCargosDelFolio(folioAsociado.identificador);
            } else {
              this.error = 'No se encontró un folio de consumo generado para esta solicitud.';
              this.cargando = false;
              this.cdr.detectChanges();
            }
          },
          error: () => {
            this.error = 'Error al conectar con el servidor para buscar folios.';
            this.cargando = false;
            this.cdr.detectChanges();
          }
        });
      },
      error: () => {
        this.error = 'Error al cargar tus solicitudes.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  cargarCargosDelFolio(identificador: string): void {
    this.folioService.obtenerCargosPorFolio(identificador).subscribe({
      next: (cargos) => {
        this.cargos = cargos;
        this.calcularTotales();
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.warn('Error al cargar cargos', err);
        this.cargos = [];
        this.calcularTotales(); // Forzamos el cálculo aunque falle
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  calcularTotales(): void {
    // 1. Sumamos los montos reales de la base de datos
    let sumaCargos = this.cargos.reduce((suma, cargo) => suma + (Number(cargo.monto) || 0), 0);
    
    // 2. Aplicamos la regla de seguridad: Si la BD está vacía, usamos 25.00
    if (sumaCargos === 0) {
      this.subtotal = 25.00;
    } else {
      this.subtotal = sumaCargos;
    }

    this.iva = this.subtotal * 0.16; // 16% de IVA
    this.total = this.subtotal + this.iva;
  }
}