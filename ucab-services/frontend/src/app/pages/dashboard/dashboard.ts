import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { BilleteraService } from '../../services/billetera.service'; 
// INYECTAMOS EL SERVICIO DE SOLICITUDES
import { SolicitudServicioService } from '../../services/solicitud-servicio.service'; 
import { ServicioService, Servicio } from '../../services/servicio.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule], 
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  nombreUsuario: string = 'Usuario'; 
  esAdmin: boolean = false;
  
  // Variables TAI
  saldoTai: number = 0;
  uidTai: string = '';
  cargandoBilletera: boolean = true; 

  // VARIABLES PARA MIS SOLICITUDES
  misSolicitudes: any[] = [];
  cargandoSolicitudes: boolean = true;

  // VARIABLES PARA SERVICIOS RECIENTES
  serviciosRecientes: Servicio[] = [];
  cargandoServicios: boolean = true;

  constructor(
    private authService: AuthService,
    private billeteraService: BilleteraService,
    private solicitudService: SolicitudServicioService, 
    private servicioService: ServicioService,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    const usuario = this.authService.obtenerUsuarioActual();
    
    if (usuario) {
      this.esAdmin = usuario.correo?.includes('@adm.') ?? false;
      if (usuario.nombre) {
        this.nombreUsuario = usuario.nombre.trim().split(' ')[0];
      }

      if (usuario.cedulaMiembro) {
        // --- 1. CARGAMOS LA BILLETERA ---
        this.billeteraService.obtenerBilletera(usuario.cedulaMiembro).subscribe({
          next: (res) => {
            this.saldoTai = res.saldoVirtual; 
            this.uidTai = res.uid;
            this.cargandoBilletera = false; 
            this.cdr.detectChanges(); 
          },
          error: (err) => {
            console.error('❌ Error al cargar la billetera TAI:', err);
            this.uidTai = 'NO REGISTRADA';
            this.cargandoBilletera = false;
            this.cdr.detectChanges();
          }
        });

        // --- 2. CARGAMOS LAS SOLICITUDES ---
        this.cargarMisSolicitudes(usuario.cedulaMiembro);

      } else {
        this.uidTai = 'CÉDULA FALTANTE';
        this.cargandoBilletera = false;
        this.cargandoSolicitudes = false;
        this.cdr.detectChanges();
      }
    }
    
    // --- 3. CARGAMOS LOS SERVICIOS RECIENTES ---
    this.cargarServiciosRecientes();
  }

  // MÉTODO PARA TRAER Y FILTRAR SOLICITUDES
  cargarMisSolicitudes(cedula: string): void {
    this.solicitudService.obtenerSolicitudes().subscribe({
      next: (todas) => {
        // Filtramos para que solo veas TUS solicitudes
        this.misSolicitudes = todas
          .filter((sol) => sol.miembro?.cedulaMiembro === cedula)
          // Ordenamos para que las más nuevas salgan primero
          .sort((a, b) => new Date(b.fechaCreacion).getTime() - new Date(a.fechaCreacion).getTime())
          // Tomamos solo las últimas 4 para no romper el diseño del dashboard
          .slice(0, 4); 

        this.cargandoSolicitudes = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('❌ Error al cargar solicitudes:', err);
        this.cargandoSolicitudes = false;
        this.cdr.detectChanges();
      }
    });
  }
  
  // MÉTODO PARA TRAER SERVICIOS RECIENTES
  cargarServiciosRecientes(): void {
    this.servicioService.obtenerServicios().subscribe({
      next: (todos) => {
        // Tomamos los últimos 2 para mostrarlos
        this.serviciosRecientes = todos.slice(-2).reverse();
        this.cargandoServicios = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('❌ Error al cargar servicios:', err);
        this.cargandoServicios = false;
        this.cdr.detectChanges();
      }
    });
  }

  // MÉTODO PARA COLORES DINÁMICOS SEGÚN EL ESTADO
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