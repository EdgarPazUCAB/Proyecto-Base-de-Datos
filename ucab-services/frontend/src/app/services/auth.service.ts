import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

export interface LoginRequest {
  correo: string;
  clave: string;
}

export interface VerificarMfaRequest {
  cedula: string;
  codigo: string;
}

export interface LoginResponse {
  token?: string;
  roles?: string[];
  nombre?: string;
  correo?: string;
  cedulaMiembro?: string;
  requiereMfa?: boolean;
  mensaje?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/api/auth'; 

  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).pipe(
      tap({
        next: (response) => {
          console.log('AuthService -> Respuesta cruda del servidor:', response);
          if (!response.requiereMfa) {
            localStorage.setItem('user_session', JSON.stringify(response));
          }
        },
        error: (err) => console.error('AuthService -> Error de red en login:', err)
      })
    );
  }

  verificarMfa(request: VerificarMfaRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/verificar-mfa`, request).pipe(
      tap({
        next: (response) => {
          localStorage.setItem('user_session', JSON.stringify(response));
        },
        error: (err) => console.error('AuthService -> Error de red en MFA:', err)
      })
    );
  }

  obtenerUsuarioActual(): LoginResponse | null {
    const session = localStorage.getItem('user_session');
    return session ? JSON.parse(session) : null;
  }

  logout(): void {
    localStorage.removeItem('user_session');
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('user_session') !== null;
  }
}