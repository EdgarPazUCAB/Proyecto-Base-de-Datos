import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Necesario para algunas directivas si las usas
import { AuthService } from '../services/auth.service'; // Ajusta la ruta a tu proyecto

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar implements OnInit {
  nombreUsuario: string = 'Usuario'; 

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    const usuario = this.authService.obtenerUsuarioActual();
    
    // Imprimirá: { token: "...", nombre: "Ana García", correo: "...", ... }
    console.log('Datos de Sesión mapeados:', usuario); 

    if (usuario && usuario.nombre) {
      // El nombre viene completo desde Spring Boot (ej. "Ana García").
      // .split(' ') lo separa en un arreglo ["Ana", "García"] y [0] toma el primer elemento.
      this.nombreUsuario = usuario.nombre.trim().split(' ')[0];
    }
  }
}