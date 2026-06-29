import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfertaLaboralService {
  private apiUrl = 'http://localhost:8081/api/ofertas';

  constructor(private http: HttpClient) {}

  obtenerOfertas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  obtenerOfertaDetalle(idEntidad: number, cargo: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/detalle?entidad=${idEntidad}&cargo=${encodeURIComponent(cargo)}`);
  }
}
