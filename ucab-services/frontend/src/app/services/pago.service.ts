import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PagoService {
  private apiUrl = 'http://localhost:8081/api/pagos';

  constructor(private http: HttpClient) {}

  procesarPagoMovil(datosPago: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/movil`, datosPago);
  }

  procesarPagoZelle(datosPago: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/zelle`, datosPago);
  }

  procesarPagoTai(datosPago: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/tai`, datosPago);
  }

  obtenerHistorialPagos(cedula: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/historial/${cedula}`);
  }
}