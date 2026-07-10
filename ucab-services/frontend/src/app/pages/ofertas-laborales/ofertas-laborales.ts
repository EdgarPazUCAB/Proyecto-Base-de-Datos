import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OfertaLaboralService } from '../../services/oferta-laboral.service';

@Component({
  selector: 'app-ofertas-laborales',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './ofertas-laborales.html', // NOTA: Asegúrate de que este nombre coincida con tu archivo HTML
  styleUrl: './ofertas-laborales.css',
})
export class OfertasLaborales implements OnInit {
  // Signals para el estado base
  public ofertas = signal<any[]>([]);
  public cargando = signal<boolean>(true);

  // Signals para la búsqueda y los filtros
  public terminoBusqueda = signal<string>('');
  public filtroEstatus = signal<string>('Todas'); // Puede ser 'Todas', 'Disponible' o 'Finalizada'

  // Computed Signal: Reacciona y se recalcula automáticamente si cambia la búsqueda, el filtro o las ofertas originales
  public ofertasFiltradas = computed(() => {
    const busqueda = this.terminoBusqueda().toLowerCase().trim();
    const estatus = this.filtroEstatus();

    return this.ofertas().filter(oferta => {
      // 1. Validar coincidencia de búsqueda (busca por cargo o por nombre de empresa)
      const cargo = oferta.cargoLaboral ? oferta.cargoLaboral.toLowerCase() : '';
      const empresa = oferta.empresa ? oferta.empresa.toLowerCase() : '';
      const coincideBusqueda = cargo.includes(busqueda) || empresa.includes(busqueda);

      // 2. Validar coincidencia del filtro de estatus
      const coincideEstatus = estatus === 'Todas' || oferta.estatusVacante === estatus;

      // Retorna la oferta solo si cumple ambas condiciones
      return coincideBusqueda && coincideEstatus;
    });
  });

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

  // Método para actualizar el término de búsqueda al escribir
  public onSearch(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.terminoBusqueda.set(input.value);
  }

  // Método para cambiar el filtro seleccionado al hacer clic en las pastillas ("chips")
  public setFiltro(estatus: string): void {
    this.filtroEstatus.set(estatus);
  }
}