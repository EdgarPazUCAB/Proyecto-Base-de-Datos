import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MiembroService, MiembroDetalle } from '../../services/miembro.service';

@Component({
  selector: 'app-lista-usuarios',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './lista-usuarios.html',
  styleUrl: './lista-usuarios.css',
})
export class ListaUsuarios implements OnInit {
  miembros: MiembroDetalle[] = [];
  cargando = true;
  errorMessage = '';

  busqueda = '';
  rolFiltro = '';

  readonly roles = [
    { valor: '', etiqueta: 'Todos los roles' },
    { valor: 'ESTUDIANTE', etiqueta: 'Estudiante' },
    { valor: 'EGRESADO', etiqueta: 'Egresado' },
    { valor: 'DOCENTE', etiqueta: 'Docente' },
    { valor: 'PERSONAL_ADMINISTRATIVO', etiqueta: 'Personal Administrativo' },
    { valor: 'ADMIN_SISTEMA', etiqueta: 'Administrador' },
  ];

  constructor(
    private miembroService: MiembroService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.miembroService.listarTodos().subscribe({
      next: (data) => {
        this.miembros = data;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al cargar los usuarios:', err);
        this.errorMessage = 'No se pudo cargar la lista de usuarios.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  get hayFiltrosActivos(): boolean {
    return this.busqueda.trim().length > 0 || this.rolFiltro.length > 0;
  }

  get miembrosFiltrados(): MiembroDetalle[] {
    const texto = this.busqueda.toLowerCase().trim();
    return this.miembros.filter((m) => {
      const coincideTexto =
        !texto ||
        m.nombresCompletos.toLowerCase().includes(texto) ||
        m.apellidosCompletos.toLowerCase().includes(texto) ||
        m.cedulaMiembro.toLowerCase().includes(texto) ||
        (m.correoInstitucional?.toLowerCase().includes(texto) ?? false);

      const coincideRol = !this.rolFiltro || m.rol === this.rolFiltro;

      return coincideTexto && coincideRol;
    });
  }

  etiquetaRol(rol: string): string {
    switch (rol) {
      case 'ESTUDIANTE': return 'Estudiante';
      case 'EGRESADO': return 'Egresado';
      case 'DOCENTE': return 'Docente';
      case 'PERSONAL_ADMINISTRATIVO': return 'Personal Adm.';
      case 'ADMIN_SISTEMA': return 'Administrador';
      default: return 'Miembro';
    }
  }

  colorRol(rol: string): string {
    switch (rol) {
      case 'ESTUDIANTE': return 'rol-estudiante';
      case 'EGRESADO': return 'rol-egresado';
      case 'DOCENTE': return 'rol-docente';
      case 'PERSONAL_ADMINISTRATIVO': return 'rol-admin';
      case 'ADMIN_SISTEMA': return 'rol-sistema';
      default: return 'rol-default';
    }
  }

  iniciales(nombres: string, apellidos: string): string {
    const n = nombres?.charAt(0) ?? '';
    const a = apellidos?.charAt(0) ?? '';
    return (n + a).toUpperCase();
  }

  limpiarFiltros(): void {
    this.busqueda = '';
    this.rolFiltro = '';
  }
}
