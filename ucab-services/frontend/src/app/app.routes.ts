import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Profile } from './pages/profile/profile';
import { AgregarMiembro } from './pages/agregar-miembro/agregar-miembro';
import { ModificarMiembro } from './pages/modificar-miembro/modificar-miembro';
import { ConsultarMiembro } from './pages/consultar-miembro/consultar-miembro';
import { AnadirVinculoFamiliar } from './pages/anadir-vinculo-familiar/anadir-vinculo-familiar';
import { SolicitarServicio } from './pages/solicitar-servicio/solicitar-servicio';
import { Pago } from './pages/pago/pago';
import { Tarjeta } from './pages/metodos-pago/tarjeta/tarjeta';
import { PagoMovil } from './pages/metodos-pago/pago-movil/pago-movil';
import { Zelle } from './pages/metodos-pago/zelle/zelle';
import { TarjetaTai } from './pages/metodos-pago/tarjeta-tai/tarjeta-tai';
import { Criptomonedas } from './pages/metodos-pago/criptomonedas/criptomonedas';
import { ServicioActividad } from './pages/servicio-actividad/servicio-actividad';
import { FolioConsumo } from './pages/folio-consumo/folio-consumo';
import { OfertasLaborales } from './pages/ofertas-laborales/ofertas-laborales';
import { OfertaLaboralDetalle } from './pages/oferta-laboral-detalle/oferta-laboral-detalle';
import { Dashboard } from './pages/dashboard/dashboard';


export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'profile', component: Profile },
  { path: 'agregar-miembro', component: AgregarMiembro },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'modificar-miembro', component: ModificarMiembro },
  { path: 'consultar-miembro', component: ConsultarMiembro },
  { path: 'anadir-vinculo-familiar', component: AnadirVinculoFamiliar },
  { path: 'solicitar-servicio', component: SolicitarServicio },
  { path: 'pago', component: Pago },
  { path: 'tarjeta', component: Tarjeta },
  { path: 'pago-movil', component: PagoMovil },
  { path: 'zelle', component: Zelle },
  { path: 'tarjeta-tai', component: TarjetaTai },
  { path: 'criptomonedas', component: Criptomonedas },
  { path: 'servicio-actividad', component: ServicioActividad },
  { path: 'folio-consumo', component: FolioConsumo },
  { path: 'ofertas-laborales', component: OfertasLaborales },
  { path: 'oferta-laboral-detalle', component: OfertaLaboralDetalle },
  { path: 'dashboard', component: Dashboard }
];
