import { Component, OnInit } from '@angular/core';
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
  
  // Nuevas propiedades para manejar el flujo V-xxxxxxx
  tipoDocumento: string = 'V'; 
  numeroDocumento: string = '';
  
  nombreAcompanante: string = '';
  
  cargando: boolean = false;
  mensajeExito: string = '';
  mensajeError: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private acompananteService: AcompananteTemporalService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.identificadorSolicitud = params['solicitud'] || '';
      if (!this.identificadorSolicitud) {
        this.mensajeError = 'No se proporcionó una solicitud válida.';
      }
    });
  }

  limpiarError(): void {
    if (this.mensajeError) {
      this.mensajeError = '';
    }
  }

  guardarAcompanante(): void {
    if (!this.numeroDocumento.trim() || !this.nombreAcompanante.trim()) {
      this.mensajeError = 'Por favor, complete todos los campos requeridos.';
      return;
    }

    // Validación extra en TypeScript por seguridad
    const cedulaRegExp = /^[0-9]{6,9}$/;
    if (!cedulaRegExp.test(this.numeroDocumento.trim())) {
      this.mensajeError = 'El número de cédula debe contener solo números (entre 6 y 9 dígitos).';
      return;
    }

    if (!this.identificadorSolicitud) {
      this.mensajeError = 'Identificador de solicitud no encontrado. No se puede guardar.';
      return;
    }

    this.cargando = true;
    this.mensajeError = '';
    this.mensajeExito = '';

    // Formateamos el documento uniendo el tipo, el guion y el número
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
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 2000);
      },
      error: (err) => {
        this.cargando = false;
        console.error('Error al guardar el acompañante:', err);
        
        if (err.error && typeof err.error === 'string') {
          this.mensajeError = err.error;
        } else if (err.error && err.error.message) {
          this.mensajeError = err.error.message;
        } else {
          this.mensajeError = 'Ocurrió un error al intentar guardar el acompañante. Por favor, verifique los datos.';
        }
      }
    });
  }

  omitir(): void {
    this.router.navigate(['/dashboard']);
  }
}