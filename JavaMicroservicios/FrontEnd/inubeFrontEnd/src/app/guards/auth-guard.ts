import {CanActivateFn, UrlTree} from '@angular/router';



// FIX: Eliminamos las dependencias de RxJS (Observable, of) para hacer la guardia puramente sincrona
// y evitar el error "reading 'map'" que ocurre en el entorno de compilación/SSR.
import { Auth } from '../services/auth';
import {Router} from 'express';
import {inject} from '@angular/core'; // Asegúrate de que la ruta del servicio es correcta
/**
 * Guardia de ruta funcional (CanActivateFn) para proteger rutas sensibles.
 * Utiliza el AuthService para verificar si el usuario está autenticado (si tiene un token).
 * @returns boolean o UrlTree (para redirección).
 */
export const authGuard: CanActivateFn = (route, state): boolean | UrlTree => {
  // 1. Inyectamos las dependencias necesarias
  const authService: Auth = inject(Auth);
  const router= inject(Router);

  // 2. Verificamos la sesión usando el método del servicio (sincrono)
  if (authService.isLoggedIn()) {
    // Si el usuario está loqueado, se devuelve true (acceso permitido).
    // Ya no necesitamos 'of(true)' ya que CanActivateFn acepta un 'boolean' sincrono.
    return true;
  }

  // 3. Si no hay token o la sesión no es válida:
  console.log('Acceso denegado: Redirigiendo a /login');
  // Se devuelve un UrlTree (redirección)
  return router.createUrlTree(['/login']);
};
