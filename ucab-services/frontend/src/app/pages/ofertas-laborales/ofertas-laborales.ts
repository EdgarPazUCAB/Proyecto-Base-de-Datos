import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OfertaLaboralService } from '../../services/oferta-laboral.service';

@Component({
  selector: 'app-ofertas-laborales',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './ofertas-laborales.html',
  styleUrl: './ofertas-laborales.css',
}) // <-- Decorador cerrado correctamente
export class OfertasLaborales implements OnInit { // <-- Cambiado 'public' por 'export'
  // Signals para el estado reactivo
  public ofertas = signal<any[]>([]);
  public cargando = signal<boolean>(true);

  constructor(private ofertaService: OfertaLaboralService) {}

  public ngOnInit(): void {
    this.ofertaService.obtenerOfertas().subscribe({
      next: (data) => {
        console.log('Datos recibidos del backend en 8081:', data);
        this.ofertas.set(data);
        this.cargando.set(false);
      },
      error: (err) => {
        console.error('Error cargando ofertas laborales:', err);
        this.cargando.set(false);
      }
    });
  }
}