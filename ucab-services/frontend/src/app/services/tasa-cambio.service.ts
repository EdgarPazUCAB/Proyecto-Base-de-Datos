import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map, catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TasaCambioService {
  // API específica que consulta directamente la página oficial del BCV
  private apiUrl = 'https://ve.dolarapi.com/v1/dolares/oficial';

  constructor(private http: HttpClient) {}

  obtenerTasaVES(): Observable<number> {
    return this.http.get<any>(this.apiUrl).pipe(
      map(response => {
        // La API devuelve un objeto con el campo 'promedio' que contiene la tasa BCV del día
        return response.promedio; 
      }),
      catchError(error => {
        console.error('Error al obtener la tasa del BCV de la API, usando tasa de respaldo:', error);
        // Tasa de respaldo en caso de que falle el internet o la API
        return of(36.50); 
      })
    );
  }
}