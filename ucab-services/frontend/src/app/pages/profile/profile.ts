import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router'; 
import { AuthService } from '../../services/auth.service';
import { MiembroService, MiembroDetalle } from '../../services/miembro.service';
import { BilleteraService, BilleteraResponse } from '../../services/billetera.service';
import { BeneficiarioService } from '../../services/beneficiario.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, RouterModule], 
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {
  miembro: MiembroDetalle | null = null;
  billetera: BilleteraResponse | null = null;
  
  // Arreglo y estado de carga para familiares
  beneficiarios: any[] = [];
  cargandoBeneficiarios = true;

  cargando = true;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private miembroService: MiembroService,
    private billeteraService: BilleteraService,
    private beneficiarioService: BeneficiarioService,
    private router: Router,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit(): void {
    const sesion = this.authService.obtenerUsuarioActual();

    if (!sesion?.cedulaMiembro) {
      this.router.navigate(['/login']);
      return;
    }

    // 1. Obtener Perfil del Miembro
    this.miembroService.buscarPorCedula(sesion.cedulaMiembro).subscribe({
      next: (datos) => {
        this.miembro = datos;
        this.cargando = false;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error('Error al cargar el perfil:', err);
        this.errorMessage = 'No se pudo cargar la información del perfil.';
        this.cargando = false;
        this.cdr.detectChanges(); 
      }
    });

    // 2. Obtener Billetera TAI
    this.billeteraService.obtenerBilletera(sesion.cedulaMiembro).subscribe({
      next: (datos) => {
        this.billetera = datos;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.warn('No se pudo cargar el saldo TAI:', err);
        this.cdr.detectChanges();
      }
    });

    // 3. Obtener Vínculos Familiares y filtrarlos
    this.beneficiarioService.obtenerBeneficiarios().subscribe({
      next: (todos) => {
        this.beneficiarios = todos.filter((b) => b.miembro?.cedulaMiembro === sesion.cedulaMiembro);
        this.cargandoBeneficiarios = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error al cargar familiares:', err);
        this.cargandoBeneficiarios = false;
        this.cdr.detectChanges();
      }
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