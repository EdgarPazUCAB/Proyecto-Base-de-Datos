import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { ServicioService, Servicio } from '../../services/servicio.service';

@Component({
  selector: 'app-servicio-actividad',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './servicio-actividad.html',
  styleUrl: './servicio-actividad.css',
})
export class ServicioActividad implements OnInit {
  servicio: Servicio | null = null;
  cargando: boolean = true;
  error: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private servicioService: ServicioService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    // 1. Leemos el "codigo" de la URL (ej: /servicio-actividad?codigo=SRV-001)
    this.route.queryParams.subscribe(params => {
      const codigo = params['codigo'];
      
      if (!codigo) {
        this.error = 'No se especificó ningún servicio válido.';
        this.cargando = false;
        return;
      }

      // 2. Buscamos los detalles en el backend
      this.cargarDetalleServicio(codigo);
    });
  }

  cargarDetalleServicio(codigo: string): void {
    this.servicioService.obtenerServicioPorId(codigo).subscribe({
      next: (datos) => {
        this.servicio = datos;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al cargar detalle del servicio:', err);
        this.error = 'Ocurrió un error al cargar los detalles del servicio.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  // Genera imágenes bonitas según el tipo de servicio
  getImagenFondo(codigo: string): string {
    const cod = (codigo || '').toLowerCase();
    if(cod.includes('aud')) return 'url("https://images.unsplash.com/photo-1540575467063-178a50c2df87?q=80&w=1200&auto=format&fit=crop")';
    if(cod.includes('lab') || cod.includes('tec')) return 'url("https://images.unsplash.com/photo-1629904853716-f0bc54eea481?q=80&w=1200&auto=format&fit=crop")';
    if(cod.includes('dep') || cod.includes('can')) return 'url("https://images.unsplash.com/photo-1526676037777-05a232554f77?q=80&w=1200&auto=format&fit=crop")';
    return 'url("https://images.unsplash.com/photo-1497366216548-37526070297c?q=80&w=1200&auto=format&fit=crop")';
  }
}