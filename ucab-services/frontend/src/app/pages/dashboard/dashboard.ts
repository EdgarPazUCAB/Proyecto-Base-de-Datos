import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  // Por defecto dejamos 'Usuario', pero se sobreescribirá si la sesión existe
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