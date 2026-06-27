import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { RouterModule, Router } from '@angular/router'; // <-- Necesario para la navegación
import { AuthService } from '../services/auth.service'; 

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule], // <-- Agregamos RouterModule aquí
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  nombreUsuario: string = 'Usuario'; 
  isMenuOpen: boolean = false; // <-- Controla si el menú de tuerca está abierto

  // Inyectamos también el Router de Angular
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    const usuario = this.authService.obtenerUsuarioActual();
    
    console.log('Datos de Sesión mapeados:', usuario); 

    if (usuario && usuario.nombre) {
      this.nombreUsuario = usuario.nombre.trim().split(' ')[0];
    }
  }

  // Abre y cierra el menú de configuración
  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  // Borra la sesión y devuelve al usuario al Login
  cerrarSesion(): void {
    localStorage.removeItem('user_session'); // Borramos el caché
    this.isMenuOpen = false;
    this.router.navigate(['/login']); // Redirigimos
  }
}