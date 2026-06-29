import { Component, OnInit, signal } from '@angular/core'; // <-- Importamos 'signal'
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { OfertaLaboralService } from '../../services/oferta-laboral.service';

@Component({
  selector: 'app-oferta-laboral-detalle',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './oferta-laboral-detalle.html',
  styleUrl: './oferta-laboral-detalle.css',
})
export class OfertaLaboralDetalle implements OnInit { // <-- 'export class' impecable
  // Convertimos las propiedades en Signals reactivos
  public oferta = signal<any>(null);
  public cargando = signal<boolean>(true);
  public error = signal<string>('');

  constructor(
    private route: ActivatedRoute,
    private ofertaService: OfertaLaboralService
  ) {}

  public ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const idEntidad = params['entidad'];
      const cargo = params['cargo'];

      if (idEntidad && cargo) {
        this.ofertaService.obtenerOfertaDetalle(idEntidad, cargo).subscribe({
          next: (data) => {
            // Log estratégico para verificar en la consola (F12) que los datos lleguen completos
            console.log('Detalle de oferta recibido:', data);
            
            this.oferta.set(data);
            this.cargando.set(false);
          },
          error: (err) => {
            console.error('Error cargando detalles:', err);
            this.error.set('No se pudo cargar la oferta laboral. Verifica tu conexión o intenta más tarde.');
            this.cargando.set(false);
          }
        });
      } else {
        this.error.set('No se especificó la oferta que se desea visualizar.');
        this.cargando.set(false);
      }
    });
  }
}