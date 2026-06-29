import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface EspacioFisico {
  nombreEdif: string;
  direccion: string;
  capacidadAforo: number;
  tipoInmobiliario: string;
  estatus: string;
}

export interface Servicio {
  codigoServicio: string;
  perfilSolicitante?: string;
  descripcionDetallada?: string; 
  estadoServicio?: string; 
  requisitos?: string;
  
  // Array que recibirá las ubicaciones con sus cupos (desde Asignado_En)
  espacios?: EspacioFisico[];
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

  obtenerServicioPorId(codigo: string): Observable<Servicio> {
    return this.http.get<Servicio>(`${this.apiUrl}/${codigo}`);
  }
}