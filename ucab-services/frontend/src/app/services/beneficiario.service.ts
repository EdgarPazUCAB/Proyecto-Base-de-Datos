import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BeneficiarioService {
  private apiUrl = 'http://localhost:8081/api/beneficiarios';

  constructor(private http: HttpClient) {}

  crearBeneficiario(datos: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, datos);
  }

  // Método GET para traer los familiares
  obtenerBeneficiarios(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}