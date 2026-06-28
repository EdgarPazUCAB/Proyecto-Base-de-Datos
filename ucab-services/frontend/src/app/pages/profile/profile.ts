import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MiembroService, MiembroDetalle } from '../../services/miembro.service';
import { BilleteraService, BilleteraResponse } from '../../services/billetera.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {
  miembro: MiembroDetalle | null = null;
  billetera: BilleteraResponse | null = null;
  cargando = true;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private miembroService: MiembroService,
    private billeteraService: BilleteraService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const sesion = this.authService.obtenerUsuarioActual();

    if (!sesion?.cedulaMiembro) {
      // No hay sesión válida: regresar al login en vez de mostrar un perfil vacío.
      this.router.navigate(['/login']);
      return;
    }

    this.miembroService.buscarPorCedula(sesion.cedulaMiembro).subscribe({
      next: (datos) => {
        this.miembro = datos;
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error al cargar el perfil:', err);
        this.errorMessage = 'No se pudo cargar la información del perfil.';
        this.cargando = false;
      }
    });

    // Saldo TAI: petición separada, no bloqueante. Si falla, el
    // perfil sigue mostrándose normal, solo sin esa tarjeta de saldo.
    this.billeteraService.obtenerBilletera(sesion.cedulaMiembro).subscribe({
      next: (datos) => this.billetera = datos,
      error: (err) => console.warn('No se pudo cargar el saldo TAI:', err)
    });
  }

  get esEstudiante(): boolean {
    return this.miembro?.rol === 'ESTUDIANTE';
  }

  get esDocente(): boolean {
    return this.miembro?.rol === 'DOCENTE';
  }

  get esPersonalAdministrativo(): boolean {
    return this.miembro?.rol === 'PERSONAL_ADMINISTRATIVO';
  }

  get esEgresado(): boolean {
    return this.miembro?.rol === 'EGRESADO';
  }

  // Docente y Personal Administrativo SÍ pueden tener beneficiarios
  // (vínculo familiar permanente) según la regla de negocio del
  // proyecto. Estudiante y Egresado NO.
  get puedeTenerBeneficiarios(): boolean {
    return this.esDocente || this.esPersonalAdministrativo;
  }

  get etiquetaRol(): string {
    switch (this.miembro?.rol) {
      case 'ESTUDIANTE': return 'Estudiante';
      case 'EGRESADO': return 'Egresado';
      case 'DOCENTE': return 'Docente';
      case 'PERSONAL_ADMINISTRATIVO': return 'Personal Administrativo';
      case 'ADMIN_SISTEMA': return 'Administrador';
      default: return 'Miembro';
    }
  }
}