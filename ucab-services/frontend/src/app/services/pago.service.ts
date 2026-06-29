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
}