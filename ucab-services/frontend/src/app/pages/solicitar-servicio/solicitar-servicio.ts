import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { SolicitudServicioService } from '../../services/solicitud-servicio.service';
import { ServicioService, Servicio } from '../../services/servicio.service';

@Component({
  selector: 'app-solicitar-servicio',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './solicitar-servicio.html',
  styleUrl: './solicitar-servicio.css'
})
export class SolicitarServicio implements OnInit {
  cedulaUsuario: string = '';
  
  // Lista de servicios desde la BD
  serviciosBD: Servicio[] = [];
  cargandoServicios: boolean = true;

  // Variables conectadas a tu diseño UI
  servicioSeleccionado: string = ''; 
  nombreServicioUI: string = 'Seleccione un servicio';
  lugarServicioUI: string = 'Esperando selección...';

  // Estados de carga generales
  cargando: boolean = false;
  mensajeExito: string = '';
  mensajeError: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private solicitudService: SolicitudServicioService,
    private servicioService: ServicioService,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    // 1. Obtenemos la cédula del usuario en sesión
    const usuario = this.authService.obtenerUsuarioActual();
    if (usuario && usuario.cedulaMiembro) {
      this.cedulaUsuario = usuario.cedulaMiembro;
    } else {
      this.mensajeError = 'Error: No se encontró la sesión del usuario.';
    }

    // 2. Cargamos los servicios disponibles
    this.cargarServicios();
  }

  cargarServicios(): void {
    this.cargandoServicios = true;
    this.servicioService.obtenerServicios().subscribe({
      next: (data) => {
        // Guardamos los datos y filtramos solo los activos
        this.serviciosBD = data.filter(s => s.estadoServicio !== 'Inactivo');
        this.cargandoServicios = false;

        // Verificamos si viene un servicio preseleccionado desde la URL (del Catálogo)
        this.route.queryParams.subscribe(params => {
          if (params['servicio']) {
            const servEncontrado = this.serviciosBD.find(s => s.codigoServicio === params['servicio']);
            if (servEncontrado) {
              this.seleccionarServicioReal(servEncontrado);
            }
          }
        });

        // Obligamos a Angular a repintar el HTML
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error('Error al cargar servicios del backend:', err);
        this.mensajeError = 'Error al cargar los servicios de la base de datos.';
        this.cargandoServicios = false;
        this.cdr.detectChanges(); 
      }
    });
  }

  // Actualiza la parte derecha de la pantalla (Resumen) al hacer clic en una tarjeta
  seleccionarServicioReal(servicio: Servicio): void {
    this.servicioSeleccionado = servicio.codigoServicio;
    this.nombreServicioUI = servicio.codigoServicio; 
    this.lugarServicioUI = servicio.descripcionDetallada || 'Sin descripción detallada';
  }

  // Genera imágenes bonitas según el código del servicio
  getImagenServicio(codigo: string): string {
    const cod = (codigo || '').toLowerCase();
    if(cod.includes('aud')) return 'https://images.unsplash.com/photo-1540575467063-178a50c2df87?q=80&w=600&auto=format&fit=crop';
    if(cod.includes('lab') || cod.includes('tec')) return 'https://images.unsplash.com/photo-1629904853716-f0bc54eea481?q=80&w=600&auto=format&fit=crop';
    if(cod.includes('dep') || cod.includes('can')) return 'https://images.unsplash.com/photo-1526676037777-05a232554f77?q=80&w=600&auto=format&fit=crop';
    return 'https://images.unsplash.com/photo-1497366216548-37526070297c?q=80&w=600&auto=format&fit=crop';
  }

  enviarSolicitud(): void {
    // Validaciones de seguridad antes de enviar
    if (!this.cedulaUsuario || !this.servicioSeleccionado) {
       this.mensajeError = 'Asegúrese de seleccionar un servicio antes de confirmar.';
       window.scrollTo(0, 0);
       return;
    }

    this.cargando = true;
    this.mensajeError = '';
    
    // Extraemos estrictamente el formato YYYY-MM-DD para evitar el error de parsing
    const fechaFormateada = new Date().toISOString().split('T')[0];

    const nuevaSolicitud = {
      // Tiene que ser exactamente este String por la validación CHECK de tu base de datos
      estadoActual: 'En_Proceso', 
      fechaInicio: fechaFormateada,
      
      // Enviamos NULL para cumplir con el CHECK chk_fecha_fin_estado de la base de datos
      fechaFin: null, 
      
      miembro: { cedulaMiembro: this.cedulaUsuario },
      servicio: { codigoServicio: this.servicioSeleccionado }
    };

    // Enviamos al backend
    this.solicitudService.crearSolicitud(nuevaSolicitud).subscribe({
      next: (res) => {
        this.cargando = false;
        this.mensajeExito = '¡Solicitud enviada con éxito!';
        this.cdr.detectChanges();
        window.scrollTo(0, 0); 
        
        // Redirigimos al dashboard después de 2.5 segundos
        setTimeout(() => this.router.navigate(['/dashboard']), 2500);
      },
      error: (err) => {
        console.error('Error al enviar la solicitud:', err);
        this.cargando = false;
        this.mensajeError = 'Ocurrió un error al procesar tu solicitud.';
        this.cdr.detectChanges();
        window.scrollTo(0, 0);
      }
    });
  }
}