import { Routes } from '@angular/router';
import {Dashboard} from './components/dashboard/dashboard';
import {authGuard} from './guards/auth-guard';
import {Login} from './components/login/login';

export const routes: Routes = [
  //ruta para el login (sin proteccion)
  { path: 'login', component: Login },


  //ruta principal del dashborad (PROTEGIDA)
  { path: 'dashboard',
    component: Dashboard,
    canActivate: [authGuard]},

  //ruta por defecto
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },


];


