import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';

export interface BeneficiarioData {
  beneficiario_nombre: string;
  titular_nombre: string;
  rol_titular: string;
  fecha_nac: string;
  dias_para_18: number;
  edad: number;
}

@Component({
  selector: 'app-reporte-demografico',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './reporte-demografico.html',
  styleUrl: './reporte-demografico.css'
})
export class ReporteDemografico implements OnInit {
  rangoFecha: string = '2024';
  tipoPersonal: string = 'Todos';
  dependencia: string = 'Todas las Facultades';

  beneficiarios: BeneficiarioData[] = [];
  totalMenores: number = 0;
  proximos18: number = 0;

  // Chart data
  edad0_5_doc: number = 0;
  edad0_5_adm: number = 0;
  edad6_12_doc: number = 0;
  edad6_12_adm: number = 0;
  edad13_17_doc: number = 0;
  edad13_17_adm: number = 0;
  
  max_edad_count: number = 1;

  pctDocentes: number = 0;
  pctAdministrativo: number = 0;

  cargando: boolean = true;

  private readonly apiBase = 'http://localhost:8081/api/reportes/evolucion-demografica';

  constructor(
    private router: Router, 
    private authService: AuthService, 
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    const usuario = this.authService.obtenerUsuarioActual();
    if (!usuario) {
      this.router.navigate(['/login']);
      return;
    }
    
    const correo = usuario.correo ?? '';
    if (!correo.includes('@adm.')) {
      alert('Acceso denegado: Se requiere rol de PERSONAL_ADMINISTRATIVO.');
      this.router.navigate(['/dashboard']);
      return;
    }

    this.cargarDatos();
  }

  cargarDatos() {
    const usuario = this.authService.obtenerUsuarioActual();
    if (!usuario || !usuario.cedulaMiembro) return;

    this.cargando = true;
    this.cdr.detectChanges(); // Trigger loading state update

    const url = `${this.apiBase}/datos?cedula=${usuario.cedulaMiembro}&rangoFecha=${this.rangoFecha}&tipoPersonal=${this.tipoPersonal}&dependencia=${this.dependencia}`;

    this.http.get<BeneficiarioData[]>(url).subscribe({
      next: (data) => {
        this.procesarDatos(data);
        this.cargando = false;
        this.cdr.detectChanges(); // Update view with data
      },
      error: (err) => {
        console.error('Error cargando datos demograficos', err);
        this.cargando = false;
        this.cdr.detectChanges(); // Update view to hide loading
      }
    });
  }

  onFilterChange() {
    this.cargarDatos();
  }

  procesarDatos(data: BeneficiarioData[]) {
    if (!data || !Array.isArray(data)) {
      data = [];
    }

    let filtrados = data;
    if (this.tipoPersonal !== 'Todos') {
      filtrados = data.filter(d => d.rol_titular === this.tipoPersonal);
    }
    
    this.beneficiarios = filtrados;
    this.totalMenores = filtrados.length;
    this.proximos18 = filtrados.filter(d => d.dias_para_18 <= 180).length;

    this.edad0_5_doc = 0; this.edad0_5_adm = 0;
    this.edad6_12_doc = 0; this.edad6_12_adm = 0;
    this.edad13_17_doc = 0; this.edad13_17_adm = 0;
    
    let totalDoc = 0;
    let totalAdm = 0;

    filtrados.forEach(b => {
      const isDoc = b.rol_titular === 'Docente';
      if (isDoc) totalDoc++;
      else if (b.rol_titular === 'Administrativo') totalAdm++;

      if (b.edad <= 5) {
        if (isDoc) this.edad0_5_doc++; else this.edad0_5_adm++;
      } else if (b.edad <= 12) {
        if (isDoc) this.edad6_12_doc++; else this.edad6_12_adm++;
      } else {
        if (isDoc) this.edad13_17_doc++; else this.edad13_17_adm++;
      }
    });

    const max_0_5 = Math.max(this.edad0_5_doc, this.edad0_5_adm);
    const max_6_12 = Math.max(this.edad6_12_doc, this.edad6_12_adm);
    const max_13_17 = Math.max(this.edad13_17_doc, this.edad13_17_adm);
    this.max_edad_count = Math.max(1, max_0_5, max_6_12, max_13_17);

    const totalValid = totalDoc + totalAdm;
    this.pctDocentes = totalValid > 0 ? Math.round((totalDoc / totalValid) * 100) : 0;
    this.pctAdministrativo = totalValid > 0 ? Math.round((totalAdm / totalValid) * 100) : 0;
  }

  get h_0_5_doc() { return (this.edad0_5_doc / this.max_edad_count) * 100; }
  get h_0_5_adm() { return (this.edad0_5_adm / this.max_edad_count) * 100; }
  get h_6_12_doc() { return (this.edad6_12_doc / this.max_edad_count) * 100; }
  get h_6_12_adm() { return (this.edad6_12_adm / this.max_edad_count) * 100; }
  get h_13_17_doc() { return (this.edad13_17_doc / this.max_edad_count) * 100; }
  get h_13_17_adm() { return (this.edad13_17_adm / this.max_edad_count) * 100; }

  get pieChartGradient() {
    return `conic-gradient(var(--tw-colors-primary) 0% ${this.pctDocentes}%, var(--tw-colors-secondary) ${this.pctDocentes}% 100%)`;
  }

  descargarReporte(formato: string) {
    const usuario = this.authService.obtenerUsuarioActual();
    if (!usuario || !usuario.cedulaMiembro) return;
    
    const url = `${this.apiBase}/${formato}?cedula=${usuario.cedulaMiembro}&rangoFecha=${this.rangoFecha}&tipoPersonal=${this.tipoPersonal}&dependencia=${this.dependencia}`;
    window.open(url, '_blank');
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
