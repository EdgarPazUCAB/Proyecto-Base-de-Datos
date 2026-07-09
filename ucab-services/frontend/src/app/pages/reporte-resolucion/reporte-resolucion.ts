import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-reporte-resolucion',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './reporte-resolucion.html',
  styleUrl: './reporte-resolucion.css',
})
export class ReporteResolucion implements OnInit {
  cedula: string = '';
  fechaInicio: string = '';
  fechaFin: string = '';
  cargando: boolean = false;
  errorAcceso: boolean = false;
  errorMensaje: string = '';

  private readonly apiBase = 'http://localhost:8081/api/reportes/resolucion-servicios';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const usuario = this.authService.obtenerUsuarioActual();
    if (!usuario) {
      this.router.navigate(['/login']);
      return;
    }
    // Verificar rol PERSONAL_ADMINISTRATIVO (correo contiene @adm.)
    const correo = usuario.correo ?? '';
    if (!correo.includes('@adm.')) {
      this.errorAcceso = true;
      this.errorMensaje = 'Acceso restringido. Este reporte es exclusivo para Personal Administrativo.';
      return;
    }
    this.cedula = usuario.cedulaMiembro ?? '';
    // Defaults: fecha actual
    const ahora = new Date();
    const anio = ahora.getFullYear();
    const mes = String(ahora.getMonth() + 1).padStart(2, '0');
    const dia = String(ahora.getDate()).padStart(2, '0');
    const hoyStr = `${anio}-${mes}-${dia}`;
    this.fechaInicio = hoyStr;
    this.fechaFin = hoyStr;
  }

  descargarPdf(): void {
    this.descargar('pdf');
  }

  descargarExcel(): void {
    this.descargar('excel');
  }

  private descargar(formato: 'pdf' | 'excel'): void {
    this.cargando = true;
    this.errorMensaje = '';

    const params = new URLSearchParams({
      cedula: this.cedula,
      fechaInicio: this.fechaInicio || '2000-01-01',
      fechaFin: this.fechaFin || '2099-12-31',
    });

    const url = `${this.apiBase}/${formato}?${params.toString()}`;

    fetch(url)
      .then(async (res) => {
        if (!res.ok) {
          const texto = await res.text();
          throw new Error(texto || `Error ${res.status}`);
        }
        return res.blob();
      })
      .then((blob) => {
        const extension = formato === 'pdf' ? 'pdf' : 'xlsx';
        const nombreArchivo = `resolucion_servicios_${this.fechaInicio}_${this.fechaFin}.${extension}`;
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = nombreArchivo;
        a.click();
        URL.revokeObjectURL(url);
      })
      .catch((err) => {
        this.errorMensaje = err.message || 'Error al generar el reporte';
      })
      .finally(() => {
        this.cargando = false;
      });
  }
}
