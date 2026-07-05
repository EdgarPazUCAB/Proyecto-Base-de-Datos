import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface BilleteraResponse {
  uid: string;
  saldoVirtual: number;
  saldoRestante: number;
}

@Injectable({
  providedIn: 'root'
})
export class BilleteraService {
  private apiUrl = 'http://localhost:8081/api/billeteras-tai';

  constructor(private http: HttpClient) {}

  obtenerBilletera(cedula: string): Observable<BilleteraResponse> {
    return this.http.get<BilleteraResponse>(`${this.apiUrl}/miembro/${cedula}`);
  }

  /**
   * Usa el endpoint dedicado de recarga que actualiza Saldo_Virtual
   * y Saldo_Restante con SQL directo, garantizando la escritura correcta en BD.
   */
  recargarSaldo(cedula: string, monto: number): Observable<BilleteraResponse> {
    return this.http.post<BilleteraResponse>(`${this.apiUrl}/recargar/${cedula}`, { monto });
  }

  // Mantenido por compatibilidad con otros endpoints que lo usen
  actualizarBilletera(id: string, data: Partial<BilleteraResponse>): Observable<BilleteraResponse> {
    return this.http.put<BilleteraResponse>(`${this.apiUrl}/${id}`, data);
  }
}