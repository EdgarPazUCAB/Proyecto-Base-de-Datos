import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Interfaz ajustada a tu BilleteraTai.java real
export interface BilleteraResponse {
  uid: string;
  saldoVirtual: number;
  saldoRestante: number;
}

@Injectable({
  providedIn: 'root'
})
export class BilleteraService {
  // RUTA CORREGIDA: Apuntando a tu BilleteraTaiController
  private apiUrl = 'http://localhost:8081/api/billeteras-tai';

  constructor(private http: HttpClient) {}

  obtenerBilletera(cedula: string): Observable<BilleteraResponse> {
    // ENDPOINT CORREGIDO: /miembro/{cedula}
    return this.http.get<BilleteraResponse>(`${this.apiUrl}/miembro/${cedula}`);
  }
}