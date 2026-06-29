import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AcompananteTemporal {
  documentoIdentidadAcom: string;
  nombreAcompanante: string;
  solicitudServicio: {
    identificador: string;
  };
  estadoActivo?: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AcompananteTemporalService {
  private apiUrl = 'http://localhost:8081/api/acompanantes-temporales';

  constructor(private http: HttpClient) {}

  crearAcompanante(acompanante: AcompananteTemporal): Observable<AcompananteTemporal> {
    return this.http.post<AcompananteTemporal>(this.apiUrl, acompanante);
  }
}
