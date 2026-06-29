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
  documentoIdentidad: string = '';
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

  guardarAcompanante(): void {
    if (!this.documentoIdentidad.trim() || !this.nombreAcompanante.trim()) {
      this.mensajeError = 'Por favor, complete todos los campos requeridos.';
      return;
    }

    if (!this.identificadorSolicitud) {
      this.mensajeError = 'Identificador de solicitud no encontrado. No se puede guardar.';
      return;
    }

    this.cargando = true;
    this.mensajeError = '';
    this.mensajeExito = '';

    const nuevoAcompanante: AcompananteTemporal = {
      documentoIdentidadAcom: this.documentoIdentidad,
      nombreAcompanante: this.nombreAcompanante,
      solicitudServicio: {
        identificador: this.identificadorSolicitud
      },
      estadoActivo: true
    };

    this.acompananteService.crearAcompanante(nuevoAcompanante).subscribe({
      next: (res) => {
        this.cargando = false;
        this.mensajeExito = 'Acompañante guardado exitosamente.';
        // Redirigimos al dashboard después de un momento
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 2000);
      },
      error: (err) => {
        this.cargando = false;
        console.error('Error al guardar el acompañante:', err);
        this.mensajeError = 'Ocurrió un error al intentar guardar el acompañante.';
      }
    });
  }

  omitir(): void {
    this.router.navigate(['/dashboard']);
  }
}
