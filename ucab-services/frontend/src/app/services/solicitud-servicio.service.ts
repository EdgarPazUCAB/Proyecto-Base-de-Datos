import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface NuevaSolicitud {
  estadoActual: string;
  fechaInicio: string;
  fechaFin?: string | null;
  // Le enviamos a Spring Boot los IDs de las entidades relacionadas
  miembro: { cedulaMiembro: string };
  servicio: { codigoServicio: string };
}

@Injectable({
  providedIn: 'root'
})
export class SolicitudServicioService {
  private apiUrl = 'http://localhost:8081/api/solicitudes-servicio';

  constructor(private http: HttpClient) {}

  crearSolicitud(solicitud: NuevaSolicitud): Observable<any> {
    return this.http.post<any>(this.apiUrl, solicitud);
  }

  obtenerSolicitudes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}