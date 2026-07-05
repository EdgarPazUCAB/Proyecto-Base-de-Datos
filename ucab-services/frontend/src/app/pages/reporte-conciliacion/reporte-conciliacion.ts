import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-reporte-conciliacion',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './reporte-conciliacion.html',
  styleUrl: './reporte-conciliacion.css',
})
export class ReporteConciliacion implements OnInit {
  cedula: string = '';
  fechaReporte: string = '';
  cargandoPdf: boolean = false;
  cargandoExcel: boolean = false;
  errorAcceso: boolean = false;
  errorMensaje: string = '';

  private readonly apiBase = 'http://localhost:8081/api/reportes/conciliacion-diaria';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    const usuario = this.authService.obtenerUsuarioActual();
    if (!usuario) {
      this.router.navigate(['/login']);
      return;
    }
    const correo = usuario.correo ?? '';
    if (!correo.includes('@adm.')) {
      this.errorAcceso = true;
      this.errorMensaje = 'Acceso restringido. Este reporte es exclusivo para Personal Administrativo.';
      return;
    }
    this.cedula = usuario.cedulaMiembro ?? '';
    // Fecha por defecto: hoy
    this.fechaReporte = new Date().toISOString().split('T')[0];
  }

  get fechaFormateada(): string {
    if (!this.fechaReporte) return '';
    const [y, m, d] = this.fechaReporte.split('-');
    const meses = ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
                   'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'];
    return `${parseInt(d)} de ${meses[parseInt(m) - 1]}, ${y}`;
  }

  descargarPdf(): void { this.descargar('pdf'); }
  descargarExcel(): void { this.descargar('excel'); }

  private descargar(formato: 'pdf' | 'excel'): void {
    if (formato === 'pdf') this.cargandoPdf = true;
    else this.cargandoExcel = true;
    this.errorMensaje = '';

    const params = new URLSearchParams({
      cedula: this.cedula,
      fechaReporte: this.fechaReporte || new Date().toISOString().split('T')[0],
    });

    fetch(`${this.apiBase}/${formato}?${params.toString()}`)
      .then(async (res) => {
        if (!res.ok) {
          const t = await res.text();
          throw new Error(t || `Error ${res.status}`);
        }
        return res.blob();
      })
      .then((blob) => {
        const ext = formato === 'pdf' ? 'pdf' : 'xlsx';
        const nombre = `conciliacion_diaria_${this.fechaReporte}.${ext}`;
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = nombre;
        a.click();
        URL.revokeObjectURL(url);
      })
      .catch((err) => {
        this.errorMensaje = err.message || 'Error al generar el reporte';
      })
      .finally(() => {
        this.cargandoPdf = false;
        this.cargandoExcel = false;
      });
  }
}
