import {HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {inject} from '@angular/core';
import {Auth} from '../services/auth';

/**
* Interceptor para adjuntar el token JWT a las peticiones HTTP.
* Usa la sintaxis de función recomendada en Angular moderno (Standalone).
*/
export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  // 1. Obtiene una instancia del AuthService
  // Usamos 'inject' porque es una función, no una clase con constructor.
  const authService = inject(Auth);
  const token = authService.getToken();

  // 2. Verifica si existe el token
  if (token) {
    // 3. Clona la solicitud para añadir el encabezado 'Authorization'
    // La cabecera debe usar el esquema 'Bearer' (Bearer seguido de un espacio)
    // Esto es crucial para que Spring Security reconozca el token.
    req = req.clone({
      setHeaders: {
        Authorization: 'Bearer ${token}'
      }
    });
  }

  // 4. Continúa con la ejecución de la petición
  return next(req);
};
