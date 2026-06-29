import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { BeneficiarioService } from '../../services/beneficiario.service';

@Component({
  selector: 'app-anadir-vinculo-familiar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './anadir-vinculo-familiar.html',
  styleUrl: './anadir-vinculo-familiar.css',
})
export class AnadirVinculoFamiliar {
  // Variables del formulario (Básicas)
  primerNombre: string = '';
  segundoNombre: string = '';
  primerApellido: string = '';
  segundoApellido: string = '';
  
  documentoIdentidad: string = '';
  fechaNacimiento: string = '';
  parentesco: string = '';

  // Variables Dinámicas (Cargo Menor < 18)
  esquemaVacunacion: string = '';
  centroEduInicial: string = '';

  // Variables Dinámicas (Cargo Mayor >= 18)
  constanciaEstUniversitarios: string = '';
  certificadoSolteria: string = '';

  // Estados
  cargando: boolean = false;
  mensajeExito: string = '';
  mensajeError: string = '';

  constructor(
    private authService: AuthService,
    private beneficiarioService: BeneficiarioService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  // MÉTODO MÁGICO: Calcula la edad en tiempo real al ingresar la fecha
  get esMayorDeEdad(): boolean | null {
    if (!this.fechaNacimiento) return null; // Si no hay fecha, no mostramos nada

    const hoy = new Date();
    const fechaNac = new Date(this.fechaNacimiento);
    
    let edad = hoy.getFullYear() - fechaNac.getFullYear();
    const mes = hoy.getMonth() - fechaNac.getMonth();
    
    // Si aún no ha pasado su mes de cumpleaños, restamos un año
    if (mes < 0 || (mes === 0 && hoy.getDate() < fechaNac.getDate())) {
      edad--;
    }
    
    return edad >= 18;
  }

  guardarVinculo(): void {
    const sesion = this.authService.obtenerUsuarioActual();
    if (!sesion || !sesion.cedulaMiembro) {
      this.mensajeError = 'Error: No se encontró la sesión activa.';
      return;
    }

    if (!this.primerNombre || !this.primerApellido || !this.documentoIdentidad || !this.fechaNacimiento || !this.parentesco) {
      this.mensajeError = 'Por favor complete todos los campos obligatorios (*).';
      window.scrollTo(0, 0);
      return;
    }

    this.cargando = true;
    this.mensajeError = '';

    const nombreCompleto = `${this.primerNombre} ${this.segundoNombre} ${this.primerApellido} ${this.segundoApellido}`.replace(/\s+/g, ' ').trim();

    // Armamos el JSON validando dinámicamente qué campos enviar
    const nuevoBeneficiario = {
      documentoIdentidad: this.documentoIdentidad,
      nombre: nombreCompleto,
      fechaNacimientoBeneficiario: this.fechaNacimiento,
      parentesco: this.parentesco,
      
      // Si es menor (false), mandamos sus datos. Si es mayor (true) mandamos null
      esquemaVacunacion: this.esMayorDeEdad === false ? this.esquemaVacunacion : null,
      centroEduInicial: this.esMayorDeEdad === false ? this.centroEduInicial : null,
      
      // Si es mayor (true), mandamos sus datos. Si es menor (false) mandamos null
      constanciaEstUniversitarios: this.esMayorDeEdad === true ? this.constanciaEstUniversitarios : null,
      certificadoSolteria: this.esMayorDeEdad === true ? this.certificadoSolteria : null,
      
      miembro: { cedulaMiembro: sesion.cedulaMiembro } 
    };

    this.beneficiarioService.crearBeneficiario(nuevoBeneficiario).subscribe({
      next: (res) => {
        this.cargando = false;
        this.mensajeExito = '¡Vínculo familiar registrado con éxito!';
        this.cdr.detectChanges();
        window.scrollTo(0, 0);
        setTimeout(() => this.router.navigate(['/profile']), 2500);
      },
      error: (err) => {
        console.error(err);
        this.cargando = false;
        this.mensajeError = 'Error al registrar. Verifique que la cédula no esté ya registrada.';
        this.cdr.detectChanges();
        window.scrollTo(0, 0);
      }
    });
  }
}