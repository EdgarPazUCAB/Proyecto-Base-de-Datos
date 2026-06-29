import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SolicitudServicioService } from '../../services/solicitud-servicio.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-gestionar-solicitudes',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './gestionar-solicitudes.html',
  styleUrl: './gestionar-solicitudes.css',
})
export class GestionarSolicitudes implements OnInit {
  misSolicitudes: any[] = [];
  cargando: boolean = true;
  nombreUsuario: string = '';

  constructor(
    private solicitudService: SolicitudServicioService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const usuario = this.authService.obtenerUsuarioActual();
    if (usuario && usuario.cedulaMiembro) {
      this.nombreUsuario = usuario.nombre || 'Miembro';
      this.cargarSolicitudes(usuario.cedulaMiembro);
    } else {
      this.cargando = false;
    }
  }

  cargarSolicitudes(cedula: string): void {
    this.solicitudService.obtenerSolicitudes().subscribe({
      next: (todas) => {
        this.misSolicitudes = todas
          .filter((sol) => sol.miembro?.cedulaMiembro === cedula)
          .sort((a, b) => new Date(b.fechaCreacion).getTime() - new Date(a.fechaCreacion).getTime());
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al cargar solicitudes:', err);
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  obtenerClaseEstado(estado: string): string {
    switch (estado) {
      case 'Pendiente': 
        return 'bg-surface-container-highest text-on-surface';
      case 'En_Proceso': 
        return 'bg-primary-container/20 text-primary font-bold'; 
      case 'Completada': 
        return 'bg-green-100 text-green-800 font-bold'; 
      case 'Cancelada': 
        return 'bg-red-100 text-red-800 font-bold'; 
      default: 
        return 'bg-surface-container-highest text-on-surface';
    }
  }
}
