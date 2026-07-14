import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface MiembroDetalle {
  cedulaMiembro: string;
  nombresCompletos: string;
  apellidosCompletos: string;
  sexo: string;
  fechaNacimiento: string;
  estadoCuenta: string;
  direccionHabitacion: string;
  correoInstitucional: string;
  telefonoPersonal: string;
  ultimaConexion: string | null;
  indiceRecurrencia: number | null;
  fechaApertura: string;
  tipoCategoria: string | null;
  rol: 'ESTUDIANTE' | 'EGRESADO' | 'DOCENTE' | 'PERSONAL_ADMINISTRATIVO' | 'ADMIN_SISTEMA' | 'DESCONOCIDO';
}

@Injectable({
  providedIn: 'root'
})
export class MiembroService {
  private apiUrl = 'http://localhost:8081/api/miembros';

  constructor(private http: HttpClient) {}

  buscarPorCedula(cedula: string): Observable<MiembroDetalle> {
    return this.http.get<MiembroDetalle>(`${this.apiUrl}/${cedula}`);
  }

  listarTodos(): Observable<MiembroDetalle[]> {
    return this.http.get<MiembroDetalle[]>(this.apiUrl);
  }
}