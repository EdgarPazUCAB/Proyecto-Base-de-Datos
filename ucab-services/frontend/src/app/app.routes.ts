import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Profile } from './pages/profile/profile';
import { AgregarMiembro } from './pages/agregar-miembro/agregar-miembro';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'profile', component: Profile },
  { path: 'agregar-miembro', component: AgregarMiembro },
  { path: '', redirectTo: '/login', pathMatch: 'full' }

];

