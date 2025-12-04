import {Inject, Injectable, Optional, PLATFORM_ID} from '@angular/core';
import {isPlatformBrowser} from '@angular/common';
import {HttpClient} from '@angular/common/http';
import {catchError, map, Observable, of, tap} from 'rxjs';
import {response} from 'express';



// Definición básica para el objeto de respuesta de tu backend

interface AuthResponse {
  success: boolean;
  mensaje: string;
  data?: { // Hacemos 'data' opcional
    token: string;
    uuidUsuario: string;
    firstname: string;
  };
}

@Injectable({
    providedIn: 'root',
  })
export class Auth {
  // IMPORTANTE! REEMPLAZA esta URL con el endpoint real de tu backend
  private apiUrl: string = 'http://localhost:8107/api/inube/auth/login';
  private readonly TOKEN_KEY = "jwt_token";
  private isBrowser: boolean; // Bandera para saber si estamos en el navegador

  // Hacemos que HttpClient sea opcional (@Optional()) para evitar que el DI
  // del servidor falle si la implementación Node.js de HttpClient no se inicializa correctamente.
  constructor(@Optional() private http: HttpClient | null, @Inject(PLATFORM_ID) platformId: Object) {
    this.isBrowser = isPlatformBrowser(platformId);
  }

  /**
   * Realiza la llamada de login y guarda el token JWT.
   * @param username El nombre de usuario.
   * @param password La contraseña.
   */
  login(username: string, password: string): Observable<boolean> {
    const credentials = {usuario: username, password: password};

    // VERIFICACIÓN CRUCIAL: Si HttpClient es nulo, significa que falló la inyección en Node.js (SSR).
    // Prevenimos el error "reading 'map' y devolvemos un fallo de login.
    if (!this.http) {
      // Esto solo debería ocurrir durante el SSR si hay un fallo de dependencia en Node.js.
      // El login real debe ocurrir en el navegador.
      console.warn('ADVERTENCIA SSR: El cliente HTTP no está disponible para esta petición. Falla el login en SSR.');
      return of(false);
    }

    // Si la inyección fue exitosa (o estamos en el navegador), continuamos con la petición.
    return this.http.post<AuthResponse>(this.apiUrl, credentials).pipe(
      tap(response => {
        if (response.success && response.data?.token) {
          this.setToken(response.data.token);

          // Guardamos el ID del usuario directamente (seguro por this.isBrowser)
          if (this.isBrowser && response.data.uuidUsuario) {
            localStorage.setItem('user_id', response.data.uuidUsuario);
          }
        }
      }),

      // Mapeamos la respuesta completa a un booleano de éxito.
      map(response => response.success),

      // Agregamos manejo de errores de conexión/servidor
      catchError(error => {
        console.error('Error durante la petición de login:', error);
        // Devolvemos un Observable que emite 'false' en caso de fallo de red/servidor
        return of(false);
      })
    );
  }
  /**
   * Guarda el token en localstorage, de forma segura para SSR.
   * @param token El JWT a guardar.
   */

  setToken(token: string): void {
    // Verificación antes de usar localStorage
    if (this.isBrowser) {
      localStorage.setItem(this.TOKEN_KEY, token);
    }
  }

/**
 * Obtiene el token desde localStorage, de forma segura para SSR.
 */

 getToken(): string | null {
    // Verificación antes de usar localStorage
    if (this.isBrowser) {
      return localStorage.getItem(this.TOKEN_KEY);
    }
    return null;
 }

  /**
   * Verifica si el usuario está loqueado (si el token existe).
   */
  isLoggedIn(): boolean{
    // Llama al metodo seguro getToken
    return !!this.getToken();
  }

  /**
   * Cierra la sesión y elimina el token, de forma segura para SSR.
   */

  logout(): void {
    // Verificación antes de usar localStorage
    if (this.isBrowser) {
      localStorage.removeItem(this. TOKEN_KEY);
      localStorage.removeItem('user_id');
    }
  }
}
