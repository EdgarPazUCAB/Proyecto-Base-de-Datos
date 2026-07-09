import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Cargo {
  fechaCargo: string;
  monto: number;
  concepto: string;
}

export interface Folio {
  identificador: string;
  fechaApertura: string;
  estadoFolio: string;
  totalAcumulado: number;
}

@Injectable({
  providedIn: 'root'
})
export class FolioConsumoService {
  private apiUrlFolios = 'http://localhost:8081/api/folios-consumo';
  private apiUrlCargos = 'http://localhost:8081/api/cargos';

  constructor(private http: HttpClient) {}

  obtenerFolios(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlFolios);
  }

  obtenerFoliosPorUsuario(cedula: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrlFolios}/usuario/${cedula}`);
  }

  obtenerCargosPorFolio(identificador: string): Observable<Cargo[]> {
    // Asumiendo que el backend tiene un endpoint para filtrar cargos por identificador de solicitud
    return this.http.get<Cargo[]>(`${this.apiUrlCargos}/buscar?identificador=${identificador}`);
  }
}