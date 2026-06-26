import { Component, ChangeDetectorRef } from '@angular/core';
import { AuthService } from '../../services/auth.service'; 
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  credentials = { correo: '', clave: '' }; 
  mfaCode: string = '';
  cedulaUsuario: string = ''; 

  // Estados de la interfaz
  step: number = 1; 
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private authService: AuthService, 
    private router: Router,
    private cdr: ChangeDetectorRef // <-- Inyectamos el detector de cambios de Angular
  ) {}

  onLogin(): void {
    this.isLoading = true;
    this.errorMessage = '';
    console.log('1. Enviando credenciales...', this.credentials);

    this.authService.login(this.credentials).subscribe({
      // Usamos ': any' temporalmente para poder leer cualquier formato que mande el backend
      next: (response: any) => { 
        this.isLoading = false;
        console.log('2. Respuesta cruda del backend:', response);
        
        // Blindaje: Verificamos ambas formas en las que Spring Boot podría estar enviando el booleano
        const necesitaMfa = response.requiereMfa === true || response.isRequiereMfa === true;

        if (necesitaMfa) {
          console.log('3. Requiere MFA. Cambiando visualmente al Step 2...');
          this.cedulaUsuario = response.cedulaMiembro || ''; 
          this.step = 2; // Cambiamos el paso
          
          // FORZAMOS A ANGULAR A ACTUALIZAR EL HTML INMEDIATAMENTE
          this.cdr.detectChanges(); 
        } else {
          console.log('3. No requiere MFA. Redirigiendo al Dashboard...');
          this.router.navigate(['/dashboard']);
        }
      },
      error: (err) => {
        console.error('Error en Login:', err);
        this.isLoading = false;
        // Blindaje: Leemos tanto 'mensaje' como 'error' por si el backend lanza excepciones genéricas
        this.errorMessage = err.error?.mensaje || err.error?.error || 'Usuario o contraseña incorrectos';
        this.cdr.detectChanges(); // Forzamos actualización para mostrar el mensaje de error
      }
    });
  }

  onVerifyMfa(): void {
    if (!this.mfaCode || this.mfaCode.length !== 6) {
      this.errorMessage = 'El código debe tener exactamente 6 dígitos';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const payload = {
      cedula: this.cedulaUsuario,
      codigo: this.mfaCode
    };

    this.authService.verificarMfa(payload).subscribe({
      next: (response) => {
        this.isLoading = false;
        console.log('MFA verificado correctamente. Entrando al sistema...');
        this.router.navigate(['/dashboard']); 
      },
      error: (err) => {
        console.error('Error en verificación MFA:', err);
        this.isLoading = false;
        this.errorMessage = err.error?.mensaje || err.error?.error || 'Código de verificación inválido o expirado';
        this.cdr.detectChanges(); // Forzamos actualización para mostrar el mensaje de error
      }
    });
  }
}