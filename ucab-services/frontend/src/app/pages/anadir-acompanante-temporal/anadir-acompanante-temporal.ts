import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { AcompananteTemporalService, AcompananteTemporal } from '../../services/acompanante-temporal.service';

@Component({
  selector: 'app-anadir-acompanante-temporal',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './anadir-acompanante-temporal.html'
})
export class AnadirAcompananteTemporal implements OnInit {
  identificadorSolicitud: string = '';
  
  tipoDocumento: string = 'V'; 
  numeroDocumento: string = '';
  nombreAcompanante: string = '';
  
  cargando: boolean = false;
  mensajeExito: string = '';
  mensajeError: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private acompananteService: AcompananteTemporalService,
    private cdr: ChangeDetectorRef // <-- Inyectamos ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.identificadorSolicitud = params['solicitud'] || '';
      if (!this.identificadorSolicitud) {
        this.mensajeError = 'No se proporcionó una solicitud válida.';
        this.cdr.detectChanges();
      }
    });
  }

  limpiarError(): void {
    if (this.mensajeError) {
      this.mensajeError = '';
      this.cdr.detectChanges(); // Refrescar vista al limpiar error
    }
  }

  guardarAcompanante(): void {
    if (!this.numeroDocumento.trim() || !this.nombreAcompanante.trim()) {
      this.mensajeError = 'Por favor, complete todos los campos requeridos.';
      this.cdr.detectChanges();
      window.scrollTo({ top: 0, behavior: 'smooth' });
      return;
    }

    const cedulaRegExp = /^[0-9]{6,9}$/;
    if (!cedulaRegExp.test(this.numeroDocumento.trim())) {
      this.mensajeError = 'El número de cédula debe contener solo números (entre 6 y 9 dígitos).';
      this.cdr.detectChanges();
      window.scrollTo({ top: 0, behavior: 'smooth' });
      return;
    }

    if (!this.identificadorSolicitud) {
      this.mensajeError = 'Identificador de solicitud no encontrado. No se puede guardar.';
      this.cdr.detectChanges();
      window.scrollTo({ top: 0, behavior: 'smooth' });
      return;
    }

    this.cargando = true;
    this.mensajeError = '';
    this.mensajeExito = '';
    this.cdr.detectChanges(); // Informar a la vista que empezó a cargar

    const documentoFormateado = `${this.tipoDocumento}-${this.numeroDocumento.trim()}`;

    const nuevoAcompanante: AcompananteTemporal = {
      documentoIdentidadAcom: documentoFormateado,
      nombreAcompanante: this.nombreAcompanante.trim(),
      solicitudServicio: {
        identificador: this.identificadorSolicitud
      },
      estadoActivo: true
    };

    this.acompananteService.crearAcompanante(nuevoAcompanante).subscribe({
      next: (res) => {
        this.cargando = false;
        this.mensajeExito = 'Acompañante guardado exitosamente.';
        
        // Forzar detección de cambios para que renderice el mensaje de éxito
        this.cdr.detectChanges(); 
        
        setTimeout(() => {
          window.scrollTo({ top: 0, behavior: 'smooth' });
        }, 100);
        
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 2000);
      },
      error: (err) => {
        this.cargando = false;
        console.error('Error al guardar el acompañante:', err);
        
        if (err.error && err.error.message) {
          this.mensajeError = err.error.message;
        } else if (err.error && typeof err.error === 'string') {
          this.mensajeError = err.error;
        } else {
          this.mensajeError = 'Ocurrió un error al intentar guardar el acompañante. Por favor, verifique los datos.';
        }
        
        // ¡Crucial! Forzamos a Angular a actualizar la pantalla y quitar el botón de carga
        this.cdr.detectChanges(); 
        
        // Esperamos 100ms para asegurar que el HTML del error ya existe antes de hacer scroll
        setTimeout(() => {
          window.scrollTo({ top: 0, behavior: 'smooth' });
        }, 100);
      }
    });
  }

  omitir(): void {
    this.router.navigate(['/dashboard']);
  }
}