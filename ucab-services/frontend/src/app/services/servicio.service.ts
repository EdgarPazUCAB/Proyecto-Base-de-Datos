import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Mapeo exacto basado en las columnas de tu base de datos
export interface Servicio {
  codigoServicio: string;
  perfilSolicitante?: string;
  descripcionDetallada?: string; 
  estadoServicio?: string; 
  requisitos?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServicioService {
  private apiUrl = 'http://localhost:8081/api/servicios';

  constructor(private http: HttpClient) {}

  obtenerServicios(): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(this.apiUrl);
  }
}