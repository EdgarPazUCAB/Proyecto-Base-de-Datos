import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms'; // <-- 1. IMPORTAMOS FORMS MODULE PARA LOS FILTROS
import { ServicioService, Servicio } from '../../services/servicio.service';

@Component({
  selector: 'app-catalogo-servicios',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule], // <-- 2. LO AÑADIMOS AQUÍ
  templateUrl: './catalogo-servicios.html',
  styleUrl: './catalogo-servicios.css'
})
export class CatalogoServicios implements OnInit {
  servicios: Servicio[] = []; // Guarda TODOS los servicios de la BD (Intocable)
  serviciosFiltrados: Servicio[] = []; // Guarda solo los que coinciden con los filtros (Para mostrar)
  
  // Variables conectadas al HTML
  textoBusqueda: string = '';
  estadoFiltro: string = 'todos';
  
  cargando: boolean = true;
  error: string = '';

  constructor(
    private servicioService: ServicioService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarServicios();
  }

  cargarServicios(): void {
    this.cargando = true;
    this.servicioService.obtenerServicios().subscribe({
      next: (data) => {
        this.servicios = data; 
        this.serviciosFiltrados = data; // Al inicio, mostramos todos
        this.cargando = false;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        this.error = 'No se pudieron cargar los servicios por un error interno.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  // <-- 3. ESTA ES LA FUNCIÓN MÁGICA QUE FILTRA -->
  aplicarFiltros(): void {
    let resultado = this.servicios;

    // 1. Filtrar por texto (Código o Descripción)
    if (this.textoBusqueda.trim() !== '') {
      const busqueda = this.textoBusqueda.toLowerCase().trim();
      resultado = resultado.filter(s => 
        (s.codigoServicio && s.codigoServicio.toLowerCase().includes(busqueda)) ||
        (s.descripcionDetallada && s.descripcionDetallada.toLowerCase().includes(busqueda))
      );
    }

    // 2. Filtrar por Estado (Activo / Inactivo)
    if (this.estadoFiltro !== 'todos') {
      resultado = resultado.filter(s => 
        s.estadoServicio && s.estadoServicio.toLowerCase() === this.estadoFiltro.toLowerCase()
      );
    }

    this.serviciosFiltrados = resultado; // Actualizamos las tarjetas en pantalla
  }

  getIconoServicio(codigo: string): string {
    const cod = (codigo || '').toLowerCase();
    if (cod.includes('dep') || cod.includes('cancha')) return 'sports_tennis';
    if (cod.includes('salud') || cod.includes('med')) return 'health_and_safety';
    if (cod.includes('acad') || cod.includes('aula')) return 'school';
    if (cod.includes('biblio')) return 'menu_book';
    if (cod.includes('tec') || cod.includes('lab')) return 'computer';
    return 'design_services';
  }
}