# Frontend consolidado

Este archivo agrupa el codigo fuente principal del frontend Angular de `ucab-services-ui` y su configuracion relevante. Se excluyeron artefactos generados como `dist/` y `node_modules/`.

## Archivos incluidos

- angular.json
- package.json
- README.md
- src/app/app.config.ts
- src/app/app.css
- src/app/app.html
- src/app/app.routes.ts
- src/app/app.spec.ts
- src/app/app.ts
- src/app/navbar/navbar.css
- src/app/navbar/navbar.html
- src/app/navbar/navbar.spec.ts
- src/app/navbar/navbar.ts
- src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.css
- src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.html
- src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.spec.ts
- src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.ts
- src/app/pages/catalogo-servicios/catalogo-servicios.css
- src/app/pages/catalogo-servicios/catalogo-servicios.html
- src/app/pages/catalogo-servicios/catalogo-servicios.spec.ts
- src/app/pages/catalogo-servicios/catalogo-servicios.ts
- src/app/pages/consultar-miembro/consultar-miembro.css
- src/app/pages/consultar-miembro/consultar-miembro.html
- src/app/pages/consultar-miembro/consultar-miembro.spec.ts
- src/app/pages/consultar-miembro/consultar-miembro.ts
- src/app/pages/dashboard/dashboard.css
- src/app/pages/dashboard/dashboard.html
- src/app/pages/dashboard/dashboard.spec.ts
- src/app/pages/dashboard/dashboard.ts
- src/app/pages/folio-consumo/folio-consumo.css
- src/app/pages/folio-consumo/folio-consumo.html
- src/app/pages/folio-consumo/folio-consumo.spec.ts
- src/app/pages/folio-consumo/folio-consumo.ts
- src/app/pages/login/login.css
- src/app/pages/login/login.html
- src/app/pages/login/login.spec.ts
- src/app/pages/login/login.ts
- src/app/pages/metodos-pago/criptomonedas/criptomonedas.css
- src/app/pages/metodos-pago/criptomonedas/criptomonedas.html
- src/app/pages/metodos-pago/criptomonedas/criptomonedas.spec.ts
- src/app/pages/metodos-pago/criptomonedas/criptomonedas.ts
- src/app/pages/metodos-pago/pago-movil/pago-movil.css
- src/app/pages/metodos-pago/pago-movil/pago-movil.html
- src/app/pages/metodos-pago/pago-movil/pago-movil.spec.ts
- src/app/pages/metodos-pago/pago-movil/pago-movil.ts
- src/app/pages/metodos-pago/tarjeta/tarjeta.css
- src/app/pages/metodos-pago/tarjeta/tarjeta.html
- src/app/pages/metodos-pago/tarjeta/tarjeta.spec.ts
- src/app/pages/metodos-pago/tarjeta/tarjeta.ts
- src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.css
- src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.html
- src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.spec.ts
- src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.ts
- src/app/pages/metodos-pago/zelle/zelle.css
- src/app/pages/metodos-pago/zelle/zelle.html
- src/app/pages/metodos-pago/zelle/zelle.spec.ts
- src/app/pages/metodos-pago/zelle/zelle.ts
- src/app/pages/modificar-miembro/modificar-miembro.css
- src/app/pages/modificar-miembro/modificar-miembro.html
- src/app/pages/modificar-miembro/modificar-miembro.spec.ts
- src/app/pages/modificar-miembro/modificar-miembro.ts
- src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.css
- src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.html
- src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.spec.ts
- src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.ts
- src/app/pages/ofertas-laborales/ofertas-laborales.css
- src/app/pages/ofertas-laborales/ofertas-laborales.html
- src/app/pages/ofertas-laborales/ofertas-laborales.spec.ts
- src/app/pages/ofertas-laborales/ofertas-laborales.ts
- src/app/pages/pago/pago.css
- src/app/pages/pago/pago.html
- src/app/pages/pago/pago.spec.ts
- src/app/pages/pago/pago.ts
- src/app/pages/profile/profile.css
- src/app/pages/profile/profile.html
- src/app/pages/profile/profile.spec.ts
- src/app/pages/profile/profile.ts
- src/app/pages/servicio-actividad/servicio-actividad.css
- src/app/pages/servicio-actividad/servicio-actividad.html
- src/app/pages/servicio-actividad/servicio-actividad.spec.ts
- src/app/pages/servicio-actividad/servicio-actividad.ts
- src/app/pages/solicitar-servicio/solicitar-servicio.css
- src/app/pages/solicitar-servicio/solicitar-servicio.html
- src/app/pages/solicitar-servicio/solicitar-servicio.spec.ts
- src/app/pages/solicitar-servicio/solicitar-servicio.ts
- src/index.html
- src/main.ts
- src/styles.css
- tsconfig.app.json
- tsconfig.json
- tsconfig.spec.json

## angular.json

```json
{
  "$schema": "./node_modules/@angular/cli/lib/config/schema.json",
  "version": 1,
  "cli": {
    "packageManager": "npm",
    "analytics": false
  },
  "newProjectRoot": "projects",
  "projects": {
    "ucab-services-ui": {
      "projectType": "application",
      "schematics": {},
      "root": "",
      "sourceRoot": "src",
      "prefix": "app",
      "architect": {
        "build": {
          "builder": "@angular/build:application",
          "options": {
            "browser": "src/main.ts",
            "tsConfig": "tsconfig.app.json",
            "assets": [
              {
                "glob": "**/*",
                "input": "public"
              }
            ],
            "styles": ["src/styles.css"]
          },
          "configurations": {
            "production": {
              "budgets": [
                {
                  "type": "initial",
                  "maximumWarning": "500kB",
                  "maximumError": "1MB"
                },
                {
                  "type": "anyComponentStyle",
                  "maximumWarning": "4kB",
                  "maximumError": "8kB"
                }
              ],
              "outputHashing": "all"
            },
            "development": {
              "optimization": false,
              "extractLicenses": false,
              "sourceMap": true
            }
          },
          "defaultConfiguration": "production"
        },
        "serve": {
          "builder": "@angular/build:dev-server",
          "configurations": {
            "production": {
              "buildTarget": "ucab-services-ui:build:production"
            },
            "development": {
              "buildTarget": "ucab-services-ui:build:development"
            }
          },
          "defaultConfiguration": "development"
        },
        "test": {
          "builder": "@angular/build:unit-test"
        }
      }
    }
  }
}

```

## package.json

```json
{
  "name": "ucab-services-ui",
  "version": "0.0.0",
  "scripts": {
    "ng": "ng",
    "start": "ng serve",
    "build": "ng build",
    "watch": "ng build --watch --configuration development",
    "test": "ng test"
  },
  "private": true,
  "packageManager": "npm@11.6.1",
  "dependencies": {
    "@angular/common": "^21.2.0",
    "@angular/compiler": "^21.2.0",
    "@angular/core": "^21.2.0",
    "@angular/forms": "^21.2.0",
    "@angular/platform-browser": "^21.2.0",
    "@angular/router": "^21.2.0",
    "rxjs": "~7.8.0",
    "tslib": "^2.3.0"
  },
  "devDependencies": {
    "@angular/build": "^21.2.15",
    "@angular/cli": "^21.2.15",
    "@angular/compiler-cli": "^15.0.4",
    "jsdom": "^28.0.0",
    "prettier": "^3.8.1",
    "typescript": "~5.9.2",
    "vitest": "^4.0.8"
  }
}

```

## README.md

```
# UcabServicesUi

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 21.2.15.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.

```

## src/app/app.config.ts

```ts
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [provideBrowserGlobalErrorListeners(), provideRouter(routes)],
};

```

## src/app/app.css

```css

```

## src/app/app.html

```html
<app-navbar></app-navbar>

<router-outlet></router-outlet>
```

## src/app/app.routes.ts

```ts
import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Profile } from './pages/profile/profile';
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
import { CatalogoServicios } from './pages/catalogo-servicios/catalogo-servicios';


export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'profile', component: Profile },
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
  { path: 'dashboard', component: Dashboard },
  { path: 'catalogo-servicios', component: CatalogoServicios }
];

```

## src/app/app.spec.ts

```ts
import { TestBed } from '@angular/core/testing';
import { App } from './app';

describe('App', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(App);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should render title', async () => {
    const fixture = TestBed.createComponent(App);
    await fixture.whenStable();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, ucab-services-ui');
  });
});

```

## src/app/app.ts

```ts
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from './navbar/navbar';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('ucab-services-ui');
}

```

## src/app/navbar/navbar.css

```css
.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/navbar/navbar.html

```html
<header class="bg-primary shadow-sm docked full-width top-0 z-50">
  <div class="flex justify-between items-center w-full px-lg py-sm max-w-container-max mx-auto">
    <div class="flex items-center gap-xl">
      <span class="font-h3 text-h3 font-bold text-on-primary">UCAB SERVICES</span>
      <nav class="hidden md:flex gap-lg">
        <a
          class="text-on-primary border-b-2 border-on-primary pb-1 font-bold font-body-md text-body-md hover:bg-primary-container/10 transition-all cursor-pointer active:opacity-80"
          href="#"
          >Dashboard</a
        >
        <a
          class="text-on-primary/80 hover:text-on-primary transition-colors font-body-md text-body-md hover:bg-primary-container/10 transition-all cursor-pointer active:opacity-80"
          href="#"
          >Services</a
        >
        <a
          class="text-on-primary/80 hover:text-on-primary transition-colors font-body-md text-body-md hover:bg-primary-container/10 transition-all cursor-pointer active:opacity-80"
          href="#"
          >Requests</a
        >
        <a
          class="text-on-primary/80 hover:text-on-primary transition-colors font-body-md text-body-md hover:bg-primary-container/10 transition-all cursor-pointer active:opacity-80"
          href="#"
          >Profile</a
        >
      </nav>
    </div>
    <div class="flex items-center gap-md text-on-primary">
      <button
        class="hover:bg-primary-container/10 p-sm rounded-full transition-all cursor-pointer active:opacity-80"
      >
        <span class="material-symbols-outlined">notifications</span>
      </button>
      <button
        class="hover:bg-primary-container/10 p-sm rounded-full transition-all cursor-pointer active:opacity-80"
      >
        <span class="material-symbols-outlined">settings</span>
      </button>
    </div>
  </div>
</header>

```

## src/app/navbar/navbar.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Navbar } from './navbar';

describe('Navbar', () => {
  let component: Navbar;
  let fixture: ComponentFixture<Navbar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Navbar],
    }).compileComponents();

    fixture = TestBed.createComponent(Navbar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/navbar/navbar.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {}

```

## src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.css

```css
:host {
  display: block;
}


```

## src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.html

```html
<div class="bg-background text-on-background font-body-md min-h-screen flex flex-col">
  <main class="flex-grow w-full max-w-[1280px] mx-auto px-6 py-8">
    <div class="mb-8">
      <div class="flex items-center text-secondary font-body-sm text-body-sm mb-2">
        <a class="hover:text-primary transition-colors" href="#">Profile</a>
        <span class="material-symbols-outlined mx-2 text-[16px]">chevron_right</span>
        <a class="hover:text-primary transition-colors" href="#">Family Members</a>
        <span class="material-symbols-outlined mx-2 text-[16px]">chevron_right</span>
        <span class="text-on-background font-medium">AÃ±adir VÃ­nculo Familiar</span>
      </div>
      <h1 class="font-h1 text-h1 text-on-background">AÃ±adir VÃ­nculo Familiar</h1>
      <p class="font-body-lg text-body-lg text-secondary mt-2">
        Registre los datos del nuevo miembro del nÃºcleo familiar.
      </p>
    </div>

    <div
      class="bg-surface-container-lowest border border-gray-200 rounded-xl shadow-[0_4px_6px_rgba(0,0,0,0.02)] overflow-hidden"
    >
      <form action="#" method="POST">
        <div class="p-8 border-b border-gray-100">
          <div class="flex items-center mb-6">
            <span
              class="material-symbols-outlined text-primary mr-3 text-[28px]"
              data-weight="fill"
              style="font-variation-settings: 'FILL' 1"
              >person</span
            >
            <h2 class="font-h3 text-h3 text-on-background">InformaciÃ³n Personal</h2>
          </div>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div class="flex flex-col">
              <label class="font-label-caps text-label-caps text-secondary mb-1" for="primer_nombre"
                >Primer Nombre *</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="primer_nombre"
                name="primer_nombre"
                required=""
                type="text"
              />
            </div>
            <div class="flex flex-col">
              <label
                class="font-label-caps text-label-caps text-secondary mb-1"
                for="segundo_nombre"
                >Segundo Nombre</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="segundo_nombre"
                name="segundo_nombre"
                type="text"
              />
            </div>
            <div class="flex flex-col">
              <label
                class="font-label-caps text-label-caps text-secondary mb-1"
                for="primer_apellido"
                >Primer Apellido *</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="primer_apellido"
                name="primer_apellido"
                required=""
                type="text"
              />
            </div>
            <div class="flex flex-col">
              <label
                class="font-label-caps text-label-caps text-secondary mb-1"
                for="segundo_apellido"
                >Segundo Apellido</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="segundo_apellido"
                name="segundo_apellido"
                type="text"
              />
            </div>
            <div class="flex flex-col">
              <label class="font-label-caps text-label-caps text-secondary mb-1" for="cedula"
                >NÃºmero de CÃ©dula *</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="cedula"
                name="cedula"
                required=""
                type="text"
              />
            </div>
            <div class="flex flex-col">
              <label
                class="font-label-caps text-label-caps text-secondary mb-1"
                for="fecha_nacimiento"
                >Fecha de Nacimiento *</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all text-secondary"
                id="fecha_nacimiento"
                name="fecha_nacimiento"
                required=""
                type="date"
              />
            </div>
            <div class="flex flex-col">
              <label class="font-label-caps text-label-caps text-secondary mb-1" for="sexo"
                >Sexo *</label
              >
              <div class="relative">
                <select
                  class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all w-full appearance-none"
                  id="sexo"
                  name="sexo"
                  required=""
                >
                  <option disabled="" selected="" value="">Seleccione</option>
                  <option value="M">Masculino</option>
                  <option value="F">Femenino</option>
                  <option value="O">Otro</option>
                </select>
                <span
                  class="material-symbols-outlined absolute right-3 top-1/2 -translate-y-1/2 text-secondary pointer-events-none"
                  >arrow_drop_down</span
                >
              </div>
            </div>
          </div>
        </div>

        <div class="p-8">
          <div class="flex items-center mb-6">
            <span
              class="material-symbols-outlined text-primary mr-3 text-[28px]"
              data-weight="fill"
              style="font-variation-settings: 'FILL' 1"
              >contact_mail</span
            >
            <h2 class="font-h3 text-h3 text-on-background">InformaciÃ³n de Contacto</h2>
          </div>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div class="flex flex-col md:col-span-2">
              <label class="font-label-caps text-label-caps text-secondary mb-1" for="direccion"
                >DirecciÃ³n de HabitaciÃ³n Detallada *</label
              >
              <textarea
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all resize-none"
                id="direccion"
                name="direccion"
                required=""
                rows="3"
              ></textarea>
            </div>
            <div class="flex flex-col">
              <label class="font-label-caps text-label-caps text-secondary mb-1" for="telefono"
                >NÃºmero de TelÃ©fono Personal *</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="telefono"
                name="telefono"
                required=""
                type="tel"
              />
            </div>
            <div class="flex flex-col">
              <label class="font-label-caps text-label-caps text-secondary mb-1" for="correo"
                >Cuenta de Correo ElectrÃ³nico *</label
              >
              <input
                class="font-body-md text-body-md border border-gray-200 rounded-DEFAULT px-4 py-3 bg-surface-container-lowest text-on-background focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                id="correo"
                name="correo"
                required=""
                type="email"
              />
            </div>
          </div>
        </div>

        <div
          class="p-6 bg-surface-bright border-t border-gray-200 flex justify-end items-center space-x-4"
        >
          <button
            class="px-6 py-2 bg-surface-container-lowest border border-gray-200 text-secondary font-body-md text-body-md rounded-DEFAULT hover:bg-gray-50 transition-colors"
            type="button"
          >
            Cancelar
          </button>
          <button
            class="px-6 py-2 bg-primary text-on-primary font-body-md text-body-md rounded-DEFAULT hover:bg-surface-tint transition-colors shadow-sm"
            type="submit"
          >
            Guardar VÃ­nculo
          </button>
        </div>
      </form>
    </div>
  </main>

  <footer
    class="bg-gray-50 dark:bg-gray-950 text-gray-600 dark:text-gray-400 text-sm font-inter w-full border-t border-gray-200 dark:border-gray-800 flat no-shadows mt-auto"
  >
    <div
      class="w-full py-8 px-6 flex flex-col md:flex-row justify-between items-center max-w-[1280px] mx-auto"
    >
      <div class="font-bold text-green-700 dark:text-green-500 mb-4 md:mb-0">
        Â© 2024 UCAB Services. Academic Excellence.
      </div>
      <div class="flex space-x-6">
        <a class="text-gray-500 hover:text-green-600 transition-colors duration-150" href="#"
          >Privacy Policy</a
        >
        <a class="text-gray-500 hover:text-green-600 transition-colors duration-150" href="#"
          >Terms of Service</a
        >
        <a class="text-gray-500 hover:text-green-600 transition-colors duration-150" href="#"
          >Help Desk</a
        >
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnadirVinculoFamiliar } from './anadir-vinculo-familiar';

describe('AnadirVinculoFamiliar', () => {
  let component: AnadirVinculoFamiliar;
  let fixture: ComponentFixture<AnadirVinculoFamiliar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnadirVinculoFamiliar],
    }).compileComponents();

    fixture = TestBed.createComponent(AnadirVinculoFamiliar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-anadir-vinculo-familiar',
  imports: [],
  templateUrl: './anadir-vinculo-familiar.html',
  styleUrl: './anadir-vinculo-familiar.css',
})
export class AnadirVinculoFamiliar {}

```

## src/app/pages/catalogo-servicios/catalogo-servicios.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-family: 'Material Symbols Outlined';
  font-weight: normal;
  font-style: normal;
  font-size: 24px;
  line-height: 1;
  letter-spacing: normal;
  text-transform: none;
  display: inline-block;
  white-space: nowrap;
  word-wrap: normal;
  direction: ltr;
  font-feature-settings: 'liga';
  -webkit-font-feature-settings: 'liga';
  -webkit-font-smoothing: antialiased;
}
```

## src/app/pages/catalogo-servicios/catalogo-servicios.html

```html
<div
  class="bg-background text-on-background flex flex-col min-h-screen font-body-md text-body-md antialiased"
>
  <main class="flex-grow w-full max-w-container-max mx-auto px-lg py-xl">
    <div class="mb-xl">
      <h1 class="font-h1 text-h1 text-primary mb-sm">CatÃ¡logo de Servicios</h1>
      <p class="font-body-lg text-body-lg text-on-surface-variant max-w-3xl">
        Explore nuestra oferta de eventos y servicios. Utilice los filtros para encontrar
        rÃ¡pidamente actividades acadÃ©micas, culturales o deportivas en nuestro campus.
      </p>
    </div>

    <div
      class="flex flex-col md:flex-row gap-gutter mb-xl items-center bg-surface-container-lowest p-md rounded-lg border border-outline-variant shadow-sm"
    >
      <div class="relative w-full md:w-1/3">
        <span
          class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant"
          >search</span
        >
        <input
          class="w-full pl-10 pr-4 py-2 border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary focus:outline-none transition-colors font-body-md text-body-md bg-surface-container-lowest text-on-surface"
          placeholder="Buscar eventos..."
          type="text"
        />
      </div>
      <div class="flex gap-sm w-full md:w-auto overflow-x-auto pb-2 md:pb-0">
        <button
          class="px-4 py-2 bg-primary-container text-on-primary-container rounded-full font-label-caps text-label-caps whitespace-nowrap hover:bg-primary hover:text-on-primary transition-colors border border-transparent"
        >
          Todos
        </button>
        <button
          class="px-4 py-2 bg-surface-container-lowest text-on-surface rounded-full font-label-caps text-label-caps whitespace-nowrap hover:bg-surface-container-low transition-colors border border-outline-variant"
        >
          AcadÃ©micos
        </button>
        <button
          class="px-4 py-2 bg-surface-container-lowest text-on-surface rounded-full font-label-caps text-label-caps whitespace-nowrap hover:bg-surface-container-low transition-colors border border-outline-variant"
        >
          Culturales
        </button>
        <button
          class="px-4 py-2 bg-surface-container-lowest text-on-surface rounded-full font-label-caps text-label-caps whitespace-nowrap hover:bg-surface-container-low transition-colors border border-outline-variant"
        >
          Deportivos
        </button>
      </div>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-gutter">
      <div
        class="bg-surface-container-lowest border border-outline-variant rounded-lg overflow-hidden group hover:shadow-[0_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300 flex flex-col"
      >
        <div class="h-48 w-full relative overflow-hidden bg-surface-container">
          <img
            class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
            data-alt="Auditorio UCAB"
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuDzA2XF7R0veQ5JBYBFGr2iYuhfbQB1rFj4YqGkIDp64Y9ckIqgehhUdrUTuSU8ihhZdkdoefUT47tl17VkUg4j7J9DXAGUbMpdXia8cE6ReUilDgK2Gof4lx3IWfIoi8tWWoUKDDrv0MIEb8xojpvX_ajczJl6vAuMHDWc3GuISaUwjeDm5190Z52-n4hA0jRhFR22BxDtqB0Hya-z9B4AacXNnBZfcT5Tgq_BhrQThY50wprjp6nv2k1nKR_KLSP0TcBar-fS0lQW"
          />
          <div
            class="absolute top-sm right-sm bg-primary-container text-on-primary-container font-label-caps text-label-caps px-2 py-1 rounded"
          >
            AcadÃ©mico
          </div>
        </div>
        <div class="p-md flex flex-col flex-grow">
          <h3 class="font-h3 text-h3 text-on-surface mb-xs">Seminario de InnovaciÃ³n TecnolÃ³gica</h3>
          <div
            class="flex items-center gap-2 mb-sm text-on-surface-variant font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[18px]">calendar_today</span>
            <span>15 de Noviembre, 2024</span>
          </div>
          <div
            class="flex items-center gap-2 mb-md text-on-surface-variant font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[18px]">location_on</span>
            <span>Auditorio Hermano Lanz</span>
          </div>
          <p class="font-body-sm text-body-sm text-on-surface-variant mb-md flex-grow">
            Ãšnase a nosotros para una discusiÃ³n en profundidad sobre las Ãºltimas tendencias en
            inteligencia artificial y su impacto en el sector de servicios corporativos.
          </p>
          <button
            class="w-full py-2 border border-outline-variant text-on-surface rounded font-body-md text-body-md hover:border-primary hover:text-primary transition-colors mt-auto"
          >
            Ver Detalles
          </button>
        </div>
      </div>

      <div
        class="bg-surface-container-lowest border border-outline-variant rounded-lg overflow-hidden group hover:shadow-[0_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300 flex flex-col"
      >
        <div class="h-48 w-full relative overflow-hidden bg-surface-container">
          <img
            class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
            data-alt="Orquesta SinfÃ³nica"
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuA-W56f2Ozh1RFsd_JcwI_gGUoROvJMKX6qTOGApqAyXDsxSnDkQVBQVXdUxMKabZJMiy7ZO0_wYwYjE6kE3Q2oU4GGU8kL5uHtrdZSd6pyuBpr1bEeblZb1rpRgCv0Uzf0jghz5Sqfc7VAJDtUVqNyF5tNWSkL1Yivblvs0sXR1z8_vtk-SPQZWDyQ_FI1iUP1LMvpvjnFHeGs-s4DhSQK6aOSrk12rMIn205ANZmSOwEokIdhzd0pQGX5dG9IO1j5AtHeVfLvLmU0"
          />
          <div
            class="absolute top-sm right-sm bg-tertiary-container text-on-tertiary-container font-label-caps text-label-caps px-2 py-1 rounded"
          >
            Cultural
          </div>
        </div>
        <div class="p-md flex flex-col flex-grow">
          <h3 class="font-h3 text-h3 text-on-surface mb-xs">Concierto de la Orquesta SinfÃ³nica</h3>
          <div
            class="flex items-center gap-2 mb-sm text-on-surface-variant font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[18px]">calendar_today</span>
            <span>22 de Noviembre, 2024</span>
          </div>
          <div
            class="flex items-center gap-2 mb-md text-on-surface-variant font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[18px]">location_on</span>
            <span>Plaza del Estudiante</span>
          </div>
          <p class="font-body-sm text-body-sm text-on-surface-variant mb-md flex-grow">
            Disfrute de una tarde musical con la Orquesta SinfÃ³nica de la universidad interpretando
            piezas clÃ¡sicas y contemporÃ¡neas en un entorno al aire libre.
          </p>
          <button
            class="w-full py-2 border border-outline-variant text-on-surface rounded font-body-md text-body-md hover:border-primary hover:text-primary transition-colors mt-auto"
          >
            Ver Detalles
          </button>
        </div>
      </div>

      <div
        class="bg-surface-container-lowest border border-outline-variant rounded-lg overflow-hidden group hover:shadow-[0_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300 flex flex-col"
      >
        <div class="h-48 w-full relative overflow-hidden bg-surface-container">
          <img
            class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
            data-alt="Complejo Deportivo"
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuCoPwuqCHvIr9nryRfIIJWAhrY_1luRKrC7D6YvSdzDhodq7fdyhzxrcbceESOzVEUY1uYsAXEgp3XKdCbLFv-vS7ZRPQruLemvp_aDu5pAkeVox_wVknV3iEJ2Sdpi4_nV4EFQXhXIL6pulbM6Q-k_FQTEocW11xS1X6U14FA9j18_5_WZu5CMv4UOxBV6ob62ikhYXERt8EoUa6EeSyPBBk5Zdr-GAL2iecwC5oMlbska4hftqrSLgplTApYM9LHsy2HIi8JzCfqo"
          />
          <div
            class="absolute top-sm right-sm bg-secondary-container text-on-secondary-container font-label-caps text-label-caps px-2 py-1 rounded"
          >
            Deportivo
          </div>
        </div>
        <div class="p-md flex flex-col flex-grow">
          <h3 class="font-h3 text-h3 text-on-surface mb-xs">
            Torneo Interuniversitario de Baloncesto
          </h3>
          <div
            class="flex items-center gap-2 mb-sm text-on-surface-variant font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[18px]">calendar_today</span>
            <span>28 de Noviembre, 2024</span>
          </div>
          <div
            class="flex items-center gap-2 mb-md text-on-surface-variant font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[18px]">location_on</span>
            <span>Complejo Deportivo Central</span>
          </div>
          <p class="font-body-sm text-body-sm text-on-surface-variant mb-md flex-grow">
            Apoye a nuestro equipo en las finales del torneo regional de baloncesto. Un evento lleno
            de energÃ­a, competencia sana y espÃ­ritu universitario.
          </p>
          <button
            class="w-full py-2 border border-outline-variant text-on-surface rounded font-body-md text-body-md hover:border-primary hover:text-primary transition-colors mt-auto"
          >
            Ver Detalles
          </button>
        </div>
      </div>
    </div>
  </main>

  <footer
    class="bg-surface-container-low dark:bg-inverse-surface border-t border-outline-variant mt-auto flat no-shadows w-full"
  >
    <div
      class="w-full py-lg px-lg flex flex-col md:flex-row justify-between items-center max-w-container-max mx-auto"
    >
      <div class="text-on-surface font-bold mb-sm md:mb-0">UCAB SERVICES</div>
      <p
        class="font-body-sm text-body-sm text-on-surface dark:text-inverse-on-surface text-center md:text-left mb-sm md:mb-0"
      >
        Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello. Todos los derechos reservados.
      </p>
      <nav class="flex gap-md font-body-sm text-body-sm">
        <a
          class="text-on-surface-variant dark:text-surface-variant hover:text-primary dark:hover:text-primary-fixed transition-colors"
          href="#"
          >Privacidad</a
        >
        <a
          class="text-on-surface-variant dark:text-surface-variant hover:text-primary dark:hover:text-primary-fixed transition-colors"
          href="#"
          >TÃ©rminos</a
        >
        <a
          class="text-on-surface-variant dark:text-surface-variant hover:text-primary dark:hover:text-primary-fixed transition-colors"
          href="#"
          >Soporte</a
        >
        <a
          class="text-on-surface-variant dark:text-surface-variant hover:text-primary dark:hover:text-primary-fixed transition-colors"
          href="#"
          >Contacto</a
        >
      </nav>
    </div>
  </footer>
</div>

```

## src/app/pages/catalogo-servicios/catalogo-servicios.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogoServicios } from './catalogo-servicios';

describe('CatalogoServicios', () => {
  let component: CatalogoServicios;
  let fixture: ComponentFixture<CatalogoServicios>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CatalogoServicios],
    }).compileComponents();

    fixture = TestBed.createComponent(CatalogoServicios);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/catalogo-servicios/catalogo-servicios.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-catalogo-servicios',
  imports: [],
  templateUrl: './catalogo-servicios.html',
  styleUrl: './catalogo-servicios.css',
})
export class CatalogoServicios {}

```

## src/app/pages/consultar-miembro/consultar-miembro.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}

.material-symbols-outlined.filled {
  font-variation-settings: 'FILL' 1;
}
```

## src/app/pages/consultar-miembro/consultar-miembro.html

```html
<div class="bg-background text-on-background font-body-md min-h-screen flex flex-col">
  <main class="flex-grow w-full max-w-[1280px] mx-auto px-6 py-8">
    <nav aria-label="Breadcrumb" class="flex text-body-sm text-secondary mb-6">
      <ol class="inline-flex items-center space-x-1 md:space-x-3">
        <li class="inline-flex items-center">
          <a class="inline-flex items-center hover:text-primary" href="#">
            <span class="material-symbols-outlined text-sm mr-1">dashboard</span>
            Dashboard
          </a>
        </li>
        <li>
          <div class="flex items-center">
            <span class="material-symbols-outlined text-sm mx-1">chevron_right</span>
            <a class="hover:text-primary ml-1 md:ml-2" href="#">Directorio</a>
          </div>
        </li>
        <li aria-current="page">
          <div class="flex items-center">
            <span class="material-symbols-outlined text-sm mx-1">chevron_right</span>
            <span class="text-on-surface ml-1 md:ml-2 font-medium">Consultar Miembro</span>
          </div>
        </li>
      </ol>
    </nav>

    <section
      class="bg-surface rounded-xl border border-outline-variant p-6 mb-8 flex flex-col md:flex-row items-center gap-6 shadow-sm"
    >
      <div
        class="w-32 h-32 rounded-full overflow-hidden border-4 border-surface-container-high shrink-0"
      >
        <img
          alt="Profile photo of AndrÃ©s Eduardo PÃ©rez GÃ³mez"
          class="w-full h-full object-cover"
          src="https://lh3.googleusercontent.com/aida-public/AB6AXuDZlm_iFO7lUuYFFMs40SL47vyWVXLjBF-0Gfgz_3TFlGf_kRMjhR9KvzHFOjPRVm8qURXhOEfB1NwEQ0vqdSwDN4GMMtKv-g3EKfKyhxmh1fJ7FuoQ1M7yGwL6TiU6DY4LnLvo_YbxYenVIGH366sMQ8MWg1ROldDf3SlCfPNxYZY2eRYPnG2QgjU9xf5YlwfmB1KHchOssijG5EA_cYz4dHBflBVYxkus21YqkDtY04XruUcZcxppIb5ObHsRV3igVtrpkbxs-tV8"
        />
      </div>
      <div class="flex-grow text-center md:text-left">
        <h1 class="font-h2 text-h2 text-on-surface mb-2">AndrÃ©s Eduardo PÃ©rez GÃ³mez</h1>
        <p class="font-body-lg text-body-lg text-secondary mb-3">V- 27.564.892</p>
        <div class="flex flex-wrap justify-center md:justify-start gap-2">
          <span
            class="bg-surface-container-high text-primary font-label-caps text-label-caps px-3 py-1 rounded-full border border-primary-container/30"
            >ESTUDIANTE</span
          >
          <span
            class="bg-primary-container/20 text-primary font-label-caps text-label-caps px-3 py-1 rounded-full border border-primary-container/30 flex items-center gap-1"
          >
            <span class="w-2 h-2 rounded-full bg-primary inline-block"></span>
            ACTIVO
          </span>
        </div>
      </div>
      <div class="shrink-0">
        <button
          class="bg-primary hover:bg-surface-tint text-on-primary font-body-md text-body-md px-6 py-2 rounded shadow-sm transition-colors flex items-center gap-2"
        >
          <span class="material-symbols-outlined">edit</span>
          Editar Miembro
        </button>
      </div>
    </section>

    <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
      <section class="bg-surface rounded-xl border border-outline-variant p-6 shadow-sm">
        <div class="flex items-center gap-2 mb-6 border-b border-outline-variant pb-3">
          <span class="material-symbols-outlined text-primary filled">person</span>
          <h2 class="font-h3 text-h3 text-on-surface">InformaciÃ³n Personal</h2>
        </div>
        <div class="space-y-4">
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label class="block font-label-caps text-label-caps text-secondary mb-1"
                >PRIMER NOMBRE</label
              >
              <div
                class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
              >
                AndrÃ©s
              </div>
            </div>
            <div>
              <label class="block font-label-caps text-label-caps text-secondary mb-1"
                >SEGUNDO NOMBRE</label
              >
              <div
                class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
              >
                Eduardo
              </div>
            </div>
          </div>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label class="block font-label-caps text-label-caps text-secondary mb-1"
                >PRIMER APELLIDO</label
              >
              <div
                class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
              >
                PÃ©rez
              </div>
            </div>
            <div>
              <label class="block font-label-caps text-label-caps text-secondary mb-1"
                >SEGUNDO APELLIDO</label
              >
              <div
                class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
              >
                GÃ³mez
              </div>
            </div>
          </div>
          <div>
            <label class="block font-label-caps text-label-caps text-secondary mb-1"
              >CÃ‰DULA DE IDENTIDAD</label
            >
            <div
              class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
            >
              27564892
            </div>
          </div>
          <div>
            <label class="block font-label-caps text-label-caps text-secondary mb-1"
              >CORREO INSTITUCIONAL</label
            >
            <div
              class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
            >
              aperez@est.ucab.edu.ve
            </div>
          </div>
        </div>
      </section>

      <section class="bg-surface rounded-xl border border-outline-variant p-6 shadow-sm">
        <div class="flex items-center gap-2 mb-6 border-b border-outline-variant pb-3">
          <span class="material-symbols-outlined text-primary filled">school</span>
          <h2 class="font-h3 text-h3 text-on-surface">Datos AcadÃ©micos</h2>
        </div>
        <div class="space-y-4">
          <div>
            <label class="block font-label-caps text-label-caps text-secondary mb-1"
              >ROL INSTITUCIONAL</label
            >
            <div
              class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
            >
              Estudiante de Pregrado
            </div>
          </div>
          <div>
            <label class="block font-label-caps text-label-caps text-secondary mb-1"
              >ESCUELA / FACULTAD</label
            >
            <div
              class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
            >
              IngenierÃ­a InformÃ¡tica
            </div>
          </div>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div>
              <label class="block font-label-caps text-label-caps text-secondary mb-1"
                >SEMESTRE ACTUAL</label
              >
              <div
                class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
              >
                8vo Semestre
              </div>
            </div>
            <div>
              <label class="block font-label-caps text-label-caps text-secondary mb-1"
                >NÃšMERO CARNET TAI</label
              >
              <div
                class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md"
              >
                TAI-984-221
              </div>
            </div>
          </div>
          <div>
            <label class="block font-label-caps text-label-caps text-secondary mb-1"
              >ESTADO DE INSCRIPCIÃ“N</label
            >
            <div
              class="bg-surface-container-lowest border border-outline-variant rounded p-3 text-on-surface font-body-md text-body-md flex items-center gap-2"
            >
              <span class="material-symbols-outlined text-primary-container text-sm"
                >check_circle</span
              >
              Inscrito - PerÃ­odo 2024-2025
            </div>
          </div>
        </div>
      </section>
    </div>

    <div class="mt-8 flex justify-end gap-4 border-t border-outline-variant pt-6">
      <button
        class="bg-surface-container-lowest hover:bg-surface-container-low border border-outline-variant text-secondary font-body-md text-body-md px-6 py-2 rounded shadow-sm transition-colors"
      >
        Volver
      </button>
    </div>
  </main>

  <nav
    class="fixed bottom-0 left-0 w-full z-50 flex justify-around items-center px-4 py-2 bg-white dark:bg-gray-900 border-t border-gray-200 dark:border-gray-800 shadow-[0_-4px_6px_-1px_rgba(0,0,0,0.05)] md:hidden active:scale-95 transition-transform"
  >
    <a
      class="flex flex-col items-center justify-center text-gray-500 dark:text-gray-400 text-[11px] font-medium font-inter hover:bg-gray-50 dark:hover:bg-gray-800 p-2 rounded"
      href="#"
    >
      <span class="material-symbols-outlined mb-1">dashboard</span>
      Home
    </a>
    <a
      class="flex flex-col items-center justify-center text-gray-500 dark:text-gray-400 text-[11px] font-medium font-inter hover:bg-gray-50 dark:hover:bg-gray-800 p-2 rounded"
      href="#"
    >
      <span class="material-symbols-outlined mb-1">inventory_2</span>
      Catalog
    </a>
    <a
      class="flex flex-col items-center justify-center text-green-600 dark:text-green-400 font-bold bg-green-50 dark:bg-green-900/20 rounded-lg px-3 py-1 text-[11px] font-inter"
      href="#"
    >
      <span class="material-symbols-outlined mb-1 filled">person</span>
      Account
    </a>
  </nav>

  <footer
    class="w-full border-t border-gray-200 dark:border-gray-800 bg-gray-50 dark:bg-gray-950 mt-auto hidden md:block"
  >
    <div
      class="w-full py-8 px-6 flex flex-col md:flex-row justify-between items-center max-w-[1280px] mx-auto"
    >
      <div class="text-sm font-inter text-gray-600 dark:text-gray-400 mb-4 md:mb-0">
        <span class="font-bold text-green-700 dark:text-green-500 mr-2">UCAB SERVICES</span>
        Â© 2024 UCAB Services. Academic Excellence.
      </div>
      <div class="flex gap-6 text-sm font-inter text-gray-500">
        <a class="hover:text-green-600 transition-colors" href="#">Privacy Policy</a>
        <a class="hover:text-green-600 transition-colors" href="#">Terms of Service</a>
        <a class="hover:text-green-600 transition-colors" href="#">Help Desk</a>
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/consultar-miembro/consultar-miembro.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarMiembro } from './consultar-miembro';

describe('ConsultarMiembro', () => {
  let component: ConsultarMiembro;
  let fixture: ComponentFixture<ConsultarMiembro>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultarMiembro],
    }).compileComponents();

    fixture = TestBed.createComponent(ConsultarMiembro);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/consultar-miembro/consultar-miembro.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-consultar-miembro',
  imports: [],
  templateUrl: './consultar-miembro.html',
  styleUrl: './consultar-miembro.css',
})
export class ConsultarMiembro {}

```

## src/app/pages/dashboard/dashboard.css

```css
:host {
  display: block;
  font-family: 'Inter', sans-serif;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/dashboard/dashboard.html

```html
<div class="bg-background min-h-screen flex flex-col">
  <main class="flex-grow w-full max-w-container-max mx-auto px-lg py-xl flex flex-col gap-xl">
    <section class="flex flex-col gap-sm">
      <h1 class="font-h1 text-h1 text-on-background">Bienvenido de nuevo, Carlos</h1>
      <p class="font-body-lg text-body-lg text-on-surface-variant">
        GestiÃ³n acadÃ©mica y administrativa UCAB
      </p>
    </section>

    <div class="grid grid-cols-1 md:grid-cols-12 gap-gutter">
      <div
        class="col-span-1 md:col-span-4 bg-surface-container-lowest border border-outline-variant rounded-xl p-lg flex flex-col justify-between shadow-[0px_4px_6px_rgba(0,0,0,0.05)] relative overflow-hidden"
      >
        <div
          class="absolute top-0 right-0 w-32 h-32 bg-primary-container opacity-10 rounded-bl-full pointer-events-none"
        ></div>
        <div class="flex items-center gap-sm mb-lg">
          <span class="material-symbols-outlined text-primary text-[32px]">credit_card</span>
          <h2 class="font-h3 text-h3 text-on-surface">Tarjeta TAI</h2>
        </div>
        <div class="flex flex-col gap-md">
          <div>
            <p class="font-label-caps text-label-caps text-on-surface-variant uppercase">
              Saldo Actual
            </p>
            <p class="font-h1 text-h1 text-primary font-bold">$25.50</p>
          </div>
          <div>
            <p class="font-label-caps text-label-caps text-on-surface-variant uppercase">UID</p>
            <p class="font-body-md text-body-md text-on-surface font-mono">88:77:66:55</p>
          </div>
        </div>
        <button
          class="mt-lg w-full bg-primary text-on-primary font-body-md text-body-md py-sm rounded-lg hover:bg-surface-tint transition-colors"
        >
          Recargar Saldo
        </button>
      </div>

      <div class="col-span-1 md:col-span-8 flex flex-col gap-md">
        <h3 class="font-h3 text-h3 text-on-background">Acciones RÃ¡pidas</h3>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-gutter h-full">
          <button
            class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg flex flex-col items-center justify-center gap-sm hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] hover:border-primary transition-all text-center group"
          >
            <div
              class="bg-surface-container-low p-md rounded-full group-hover:bg-primary-container/20 transition-colors"
            >
              <span class="material-symbols-outlined text-primary text-[32px]">event</span>
            </div>
            <span class="font-body-md text-body-md text-on-surface font-bold mt-sm"
              >Consultar Eventos</span
            >
            <span class="font-body-sm text-body-sm text-on-surface-variant"
              >Ver calendario acadÃ©mico y actividades</span
            >
          </button>

          <button
            class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg flex flex-col items-center justify-center gap-sm hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] hover:border-primary transition-all text-center group"
          >
            <div
              class="bg-surface-container-low p-md rounded-full group-hover:bg-primary-container/20 transition-colors"
            >
              <span class="material-symbols-outlined text-primary text-[32px]">meeting_room</span>
            </div>
            <span class="font-body-md text-body-md text-on-surface font-bold mt-sm"
              >Reservar Espacio</span
            >
            <span class="font-body-sm text-body-sm text-on-surface-variant"
              >Aulas, laboratorios y canchas</span
            >
          </button>

          <button
            class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg flex flex-col items-center justify-center gap-sm hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] hover:border-primary transition-all text-center group"
          >
            <div
              class="bg-surface-container-low p-md rounded-full group-hover:bg-primary-container/20 transition-colors"
            >
              <span class="material-symbols-outlined text-primary text-[32px]">payments</span>
            </div>
            <span class="font-body-md text-body-md text-on-surface font-bold mt-sm"
              >Pagar Servicios</span
            >
            <span class="font-body-sm text-body-sm text-on-surface-variant"
              >Aranceles, biblioteca y otros</span
            >
          </button>
        </div>
      </div>

      <div
        class="col-span-1 md:col-span-6 bg-surface-container-lowest border border-outline-variant rounded-xl p-lg flex flex-col gap-md"
      >
        <div class="flex justify-between items-center mb-sm">
          <h3 class="font-h3 text-h3 text-on-background">PrÃ³ximos Eventos</h3>
          <a class="font-body-sm text-body-sm text-primary hover:underline" href="#">Ver todos</a>
        </div>
        <div class="flex flex-col gap-sm">
          <div
            class="flex items-start gap-md p-md hover:bg-surface-container-low rounded-lg transition-colors border border-transparent hover:border-outline-variant cursor-pointer"
          >
            <div
              class="flex flex-col items-center justify-center bg-primary-container/10 text-primary rounded-lg p-sm min-w-[60px]"
            >
              <span class="font-label-caps text-label-caps uppercase">NOV</span>
              <span class="font-h3 text-h3 font-bold">15</span>
            </div>
            <div class="flex flex-col">
              <span class="font-body-md text-body-md font-bold text-on-surface"
                >Simposio de InnovaciÃ³n TecnolÃ³gica</span
              >
              <div class="flex items-center gap-xs text-on-surface-variant mt-xs">
                <span class="material-symbols-outlined text-[16px]">schedule</span>
                <span class="font-body-sm text-body-sm">09:00 AM - 12:00 PM</span>
              </div>
              <div class="flex items-center gap-xs text-on-surface-variant">
                <span class="material-symbols-outlined text-[16px]">location_on</span>
                <span class="font-body-sm text-body-sm">Aula Magna</span>
              </div>
            </div>
          </div>

          <div
            class="flex items-start gap-md p-md hover:bg-surface-container-low rounded-lg transition-colors border border-transparent hover:border-outline-variant cursor-pointer"
          >
            <div
              class="flex flex-col items-center justify-center bg-primary-container/10 text-primary rounded-lg p-sm min-w-[60px]"
            >
              <span class="font-label-caps text-label-caps uppercase">NOV</span>
              <span class="font-h3 text-h3 font-bold">18</span>
            </div>
            <div class="flex flex-col">
              <span class="font-body-md text-body-md font-bold text-on-surface"
                >Feria de Empleo UCAB</span
              >
              <div class="flex items-center gap-xs text-on-surface-variant mt-xs">
                <span class="material-symbols-outlined text-[16px]">schedule</span>
                <span class="font-body-sm text-body-sm">10:00 AM - 04:00 PM</span>
              </div>
              <div class="flex items-center gap-xs text-on-surface-variant">
                <span class="material-symbols-outlined text-[16px]">location_on</span>
                <span class="font-body-sm text-body-sm">Plaza del Estudiante</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="col-span-1 md:col-span-6 bg-surface-container-lowest border border-outline-variant rounded-xl p-lg flex flex-col gap-md"
      >
        <div class="flex justify-between items-center mb-sm">
          <h3 class="font-h3 text-h3 text-on-background">Mis Solicitudes</h3>
          <a class="font-body-sm text-body-sm text-primary hover:underline" href="#">Gestionar</a>
        </div>
        <div class="overflow-x-auto">
          <table class="w-full text-left border-collapse">
            <thead>
              <tr
                class="border-b border-outline-variant text-on-surface-variant font-label-caps text-label-caps uppercase"
              >
                <th class="py-sm font-semibold">Solicitud</th>
                <th class="py-sm font-semibold">Fecha</th>
                <th class="py-sm font-semibold text-right">Estado</th>
              </tr>
            </thead>
            <tbody class="font-body-sm text-body-sm text-on-surface">
              <tr
                class="border-b border-surface-variant hover:bg-surface-container-low transition-colors"
              >
                <td class="py-md pr-sm font-medium">Reserva de Aula - A23</td>
                <td class="py-md px-sm text-on-surface-variant">12 Nov 2024</td>
                <td class="py-md text-right">
                  <span
                    class="inline-block bg-primary-container/20 text-on-primary-container px-sm py-xs rounded-full font-label-caps text-label-caps"
                    >Aprobada</span
                  >
                </td>
              </tr>
              <tr
                class="border-b border-surface-variant hover:bg-surface-container-low transition-colors"
              >
                <td class="py-md pr-sm font-medium">Constancia de Estudios</td>
                <td class="py-md px-sm text-on-surface-variant">10 Nov 2024</td>
                <td class="py-md text-right">
                  <span
                    class="inline-block bg-surface-container-highest text-on-surface px-sm py-xs rounded-full font-label-caps text-label-caps"
                    >Procesando</span
                  >
                </td>
              </tr>
              <tr class="hover:bg-surface-container-low transition-colors">
                <td class="py-md pr-sm font-medium">PrÃ©stamo Libro Biblioteca</td>
                <td class="py-md px-sm text-on-surface-variant">08 Nov 2024</td>
                <td class="py-md text-right">
                  <span
                    class="inline-block bg-surface-container-highest text-on-surface px-sm py-xs rounded-full font-label-caps text-label-caps"
                    >Activo</span
                  >
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </main>

  <footer class="bg-surface border-t border-outline-variant full-width mt-auto">
    <div
      class="flex flex-col md:flex-row justify-between items-center w-full py-xl px-lg max-w-container-max mx-auto"
    >
      <span class="font-label-caps text-label-caps text-primary mb-md md:mb-0">UCAB SERVICES</span>
      <div class="flex gap-lg">
        <a
          class="font-body-sm text-body-sm text-on-surface-variant hover:text-primary transition-colors cursor-pointer"
          href="#"
          >Privacy Policy</a
        >
        <a
          class="font-body-sm text-body-sm text-on-surface-variant hover:text-primary transition-colors cursor-pointer"
          href="#"
          >Terms of Service</a
        >
        <a
          class="font-body-sm text-body-sm text-on-surface-variant hover:text-primary transition-colors cursor-pointer"
          href="#"
          >Academic Regulations</a
        >
        <a
          class="font-body-sm text-body-sm text-on-surface-variant hover:text-primary transition-colors cursor-pointer"
          href="#"
          >Support</a
        >
      </div>
      <span class="font-body-sm text-body-sm text-on-surface-variant mt-md md:mt-0"
        >Â© 2024 UCAB SERVICES. All rights reserved.</span
      >
    </div>
  </footer>
</div>

```

## src/app/pages/dashboard/dashboard.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Dashboard } from './dashboard';

describe('Dashboard', () => {
  let component: Dashboard;
  let fixture: ComponentFixture<Dashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Dashboard],
    }).compileComponents();

    fixture = TestBed.createComponent(Dashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/dashboard/dashboard.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {}

```

## src/app/pages/folio-consumo/folio-consumo.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/folio-consumo/folio-consumo.html

```html
<div class="bg-background text-on-background antialiased min-h-screen flex flex-col">
  <nav class="bg-primary dark:bg-primary border-b border-white/10 w-full fixed top-0 z-50">
    <div
      class="flex justify-between items-center w-full h-16 px-gutter max-w-container-max mx-auto"
    >
      <div class="font-h3 text-h3 font-bold text-on-primary dark:text-on-primary">
        UCAB SERVICES
      </div>
      <ul class="hidden md:flex space-x-8 items-center h-full">
        <li class="h-full flex items-center">
          <a
            class="font-body-md text-body-md text-on-primary/80 hover:text-on-primary transition-colors hover:bg-on-primary/10 transition-all px-4 py-2 rounded"
            href="#"
            >Dashboard</a
          >
        </li>
        <li class="h-full flex items-center">
          <a
            class="font-body-md text-body-md text-on-primary/80 hover:text-on-primary transition-colors hover:bg-on-primary/10 transition-all px-4 py-2 rounded"
            href="#"
            >Services</a
          >
        </li>
        <li class="h-full flex items-center">
          <a
            class="font-body-md text-body-md text-on-primary border-b-2 border-on-primary pb-1 font-semibold hover:bg-on-primary/10 transition-all px-4 pt-2"
            href="#"
            >Requests</a
          >
        </li>
        <li class="h-full flex items-center">
          <a
            class="font-body-md text-body-md text-on-primary/80 hover:text-on-primary transition-colors hover:bg-on-primary/10 transition-all px-4 py-2 rounded"
            href="#"
            >Profile</a
          >
        </li>
      </ul>
      <div class="flex items-center space-x-4">
        <button
          class="font-body-md text-body-md text-on-primary/80 hover:text-on-primary transition-colors hover:bg-on-primary/10 px-4 py-2 rounded"
        >
          Support
        </button>
      </div>
    </div>
  </nav>

  <main class="flex-grow pt-[88px] pb-xl px-gutter max-w-container-max mx-auto w-full">
    <div class="mb-sm flex items-center space-x-2 font-body-sm text-body-sm text-secondary">
      <a class="hover:text-primary transition-colors" href="#">Solicitudes</a>
      <span class="material-symbols-outlined text-[16px]">chevron_right</span>
      <span class="text-on-background font-medium">Folio de Consumo</span>
    </div>

    <h1 class="font-h1 text-h1 text-on-background mb-lg">
      Folio de Consumo - AsesorÃ­a de Proyecto
    </h1>

    <section
      class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg mb-lg hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow"
    >
      <div class="grid grid-cols-1 md:grid-cols-3 gap-md">
        <div>
          <p class="font-label-caps text-label-caps text-secondary mb-xs">NÃšMERO DE FOLIO</p>
          <p class="font-h3 text-h3 text-on-background">#F-2024-0892</p>
        </div>
        <div>
          <p class="font-label-caps text-label-caps text-secondary mb-xs">FECHA DE APERTURA</p>
          <p class="font-body-lg text-body-lg text-on-background flex items-center">
            <span class="material-symbols-outlined mr-sm text-secondary">calendar_today</span>
            15 Oct 2024
          </p>
        </div>
        <div>
          <p class="font-label-caps text-label-caps text-secondary mb-xs">ESTADO</p>
          <div
            class="inline-flex items-center px-3 py-1 rounded-full bg-primary-container/20 text-on-primary-container font-body-sm text-body-sm font-medium border border-primary-container/30"
          >
            <span class="material-symbols-outlined text-[16px] mr-1">lock_open</span>
            Abierto
          </div>
        </div>
      </div>
    </section>

    <section
      class="bg-surface-container-lowest border border-outline-variant rounded-xl overflow-hidden mb-lg hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow"
    >
      <div class="overflow-x-auto">
        <table class="w-full text-left border-collapse">
          <thead>
            <tr class="bg-surface-bright border-b border-outline-variant">
              <th class="py-md px-lg font-label-caps text-label-caps text-secondary">
                Fecha de Cargo
              </th>
              <th class="py-md px-lg font-label-caps text-label-caps text-secondary">Concepto</th>
              <th class="py-md px-lg font-label-caps text-label-caps text-secondary text-right">
                Cantidad
              </th>
              <th class="py-md px-lg font-label-caps text-label-caps text-secondary text-right">
                Precio Unitario
              </th>
              <th class="py-md px-lg font-label-caps text-label-caps text-secondary text-right">
                Impuesto (16%)
              </th>
              <th class="py-md px-lg font-label-caps text-label-caps text-secondary text-right">
                Subtotal
              </th>
            </tr>
          </thead>
          <tbody
            class="font-body-md text-body-md text-on-background divide-y divide-outline-variant/30"
          >
            <tr class="hover:bg-surface-bright transition-colors">
              <td class="py-md px-lg">15/10/2024</td>
              <td class="py-md px-lg font-medium">AsesorÃ­a Inicial (Hora)</td>
              <td class="py-md px-lg text-right">2.00</td>
              <td class="py-md px-lg text-right">$ 45.00</td>
              <td class="py-md px-lg text-right text-secondary">$ 14.40</td>
              <td class="py-md px-lg text-right font-medium">$ 104.40</td>
            </tr>
            <tr class="bg-[#F8FAFC] hover:bg-surface-bright transition-colors">
              <td class="py-md px-lg">16/10/2024</td>
              <td class="py-md px-lg font-medium">RevisiÃ³n de Documentos</td>
              <td class="py-md px-lg text-right">1.00</td>
              <td class="py-md px-lg text-right">$ 60.00</td>
              <td class="py-md px-lg text-right text-secondary">$ 9.60</td>
              <td class="py-md px-lg text-right font-medium">$ 69.60</td>
            </tr>
            <tr class="hover:bg-surface-bright transition-colors">
              <td class="py-md px-lg">18/10/2024</td>
              <td class="py-md px-lg font-medium">Uso de Laboratorio Especializado</td>
              <td class="py-md px-lg text-right">4.00</td>
              <td class="py-md px-lg text-right">$ 25.00</td>
              <td class="py-md px-lg text-right text-secondary">$ 16.00</td>
              <td class="py-md px-lg text-right font-medium">$ 116.00</td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>

    <section class="flex justify-end mb-xl">
      <div
        class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg w-full md:w-1/3 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow"
      >
        <div class="space-y-sm font-body-md text-body-md">
          <div class="flex justify-between text-secondary">
            <span>Subtotal Servicios</span>
            <span>$ 250.00</span>
          </div>
          <div class="flex justify-between text-secondary">
            <span>IVA (16%)</span>
            <span>$ 40.00</span>
          </div>
          <hr class="border-outline-variant my-sm" />
          <div class="flex justify-between font-h3 text-h3 text-on-background pt-sm">
            <span>Total Acumulado</span>
            <span>$ 290.00</span>
          </div>
        </div>
        <div class="mt-lg pt-lg border-t border-outline-variant flex gap-4">
          <button
            class="flex-1 bg-white border border-[#E2E8F0] text-secondary font-body-md text-body-md py-2 px-4 rounded-lg hover:bg-surface-bright transition-colors text-center"
          >
            Descargar PDF
          </button>
          <button
            class="flex-1 bg-primary text-on-primary font-body-md text-body-md font-medium py-2 px-4 rounded-lg hover:bg-primary/90 transition-colors text-center"
          >
            Pagar Folio
          </button>
        </div>
      </div>
    </section>
  </main>

  <footer
    class="bg-surface-container-lowest dark:bg-on-background border-t border-outline-variant mt-auto"
  >
    <div
      class="flex flex-col md:flex-row justify-between items-center w-full py-8 px-gutter max-w-container-max mx-auto space-y-4 md:space-y-0"
    >
      <div class="flex flex-col md:flex-row items-center md:items-start md:space-x-4">
        <span
          class="font-label-caps text-label-caps font-bold text-primary dark:text-primary-fixed mb-2 md:mb-0"
          >UCAB SERVICES</span
        >
        <span
          class="font-body-sm text-body-sm text-on-surface-variant dark:text-on-surface-variant text-center md:text-left"
        >
          Â© 2024 UCAB SERVICES. Academic Excellence &amp; Service.
        </span>
      </div>
      <ul class="flex flex-wrap justify-center gap-4 font-body-sm text-body-sm">
        <li>
          <a
            class="text-on-surface-variant hover:text-primary hover:underline transition-all focus:outline-none focus:text-primary"
            href="#"
            >Privacy Policy</a
          >
        </li>
        <li>
          <a
            class="text-on-surface-variant hover:text-primary hover:underline transition-all focus:outline-none focus:text-primary"
            href="#"
            >Terms of Service</a
          >
        </li>
        <li>
          <a
            class="text-on-surface-variant hover:text-primary hover:underline transition-all focus:outline-none focus:text-primary"
            href="#"
            >Legal Mentions</a
          >
        </li>
        <li>
          <a
            class="text-on-surface-variant hover:text-primary hover:underline transition-all focus:outline-none focus:text-primary"
            href="#"
            >Contact Support</a
          >
        </li>
      </ul>
    </div>
  </footer>
</div>

```

## src/app/pages/folio-consumo/folio-consumo.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FolioConsumo } from './folio-consumo';

describe('FolioConsumo', () => {
  let component: FolioConsumo;
  let fixture: ComponentFixture<FolioConsumo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FolioConsumo],
    }).compileComponents();

    fixture = TestBed.createComponent(FolioConsumo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/folio-consumo/folio-consumo.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-folio-consumo',
  imports: [],
  templateUrl: './folio-consumo.html',
  styleUrl: './folio-consumo.css',
})
export class FolioConsumo {}

```

## src/app/pages/login/login.css

```css
.material-symbols-outlined {
  font-variation-settings:
    'FILL' 0,
    'wght' 400,
    'GRAD' 0,
    'opsz' 24;
}
```

## src/app/pages/login/login.html

```html
<div class="bg-background min-h-screen flex flex-col font-body-md text-body-md text-on-surface">
  <!-- Header / Brand Anchor -->
  <header class="w-full bg-primary py-lg px-8 flex justify-center items-center shadow-sm z-10 relative border-b border-primary-container">
    <h1 class="font-h1 text-h1 text-on-primary tracking-wider uppercase font-black">
      UCAB SERVICES
    </h1>
  </header>
  <!-- Main Content Canvas -->
  <main class="flex-grow flex items-center justify-center p-8 relative overflow-hidden">
    <!-- Decorative Background Element (Subtle, Academic) -->
    <div class="absolute inset-0 z-0 opacity-30 pointer-events-none overflow-hidden flex items-center justify-center">
      <div class="w-[800px] h-[800px] bg-gradient-to-br from-surface-container-high to-background rounded-full blur-3xl opacity-50 transform -translate-x-1/2 -translate-y-1/4 absolute top-0 left-0"></div>
      <div class="w-[600px] h-[600px] bg-gradient-to-tl from-primary-fixed-dim/20 to-transparent rounded-full blur-3xl opacity-40 transform translate-x-1/4 translate-y-1/3 absolute bottom-0 right-0"></div>
    </div>
    <!-- Login Container -->
    <div class="w-full max-w-[480px] bg-surface-container-lowest rounded-xl border border-outline-variant shadow-sm z-10 overflow-hidden flex flex-col relative transition-all duration-300 hover:shadow-md">
      <!-- Top Color Bar -->
      <div class="h-2 w-full bg-primary"></div>
      <div class="p-xl flex flex-col items-center">
        <!-- Icon/Avatar Placeholder -->
        <div class="w-24 h-24 bg-surface-container-low rounded-full mb-8 flex items-center justify-center border-4 border-surface-container-lowest shadow-sm relative -mt-16">
          <span class="material-symbols-outlined text-[48px] text-primary" data-icon="school" style="font-variation-settings: 'FILL' 1">
            school
          </span>
        </div>
        <div class="text-center mb-10 w-full">
          <h2 class="font-h2 text-h2 text-on-surface mb-2">Iniciar SesiÃ³n</h2>
          <p class="font-body-md text-body-md text-on-surface-variant">
            Acceso a los servicios acadÃ©micos y administrativos
          </p>
        </div>
        <!-- Login Form -->
        <form action="#" class="w-full flex flex-col gap-6" method="POST">
          <!-- Email Field -->
          <div class="flex flex-col gap-xs relative">
            <label class="font-label-caps text-label-caps text-on-surface-variant uppercase ml-1" for="email">Correo electrÃ³nico</label>
            <div class="relative">
              <span class="material-symbols-outlined absolute left-4 top-1/2 transform -translate-y-1/2 text-on-surface-variant z-10 pointer-events-none" data-icon="mail">
                mail
              </span>
              <input class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-3 pl-12 pr-4 text-on-surface placeholder:text-outline focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md h-12" id="email" name="email" placeholder="usuario@ucab.edu.ve" required="" type="email" />
            </div>
          </div>
          <!-- Password Field -->
          <div class="flex flex-col gap-xs relative">
            <label class="font-label-caps text-label-caps text-on-surface-variant uppercase ml-1" for="password">ContraseÃ±a</label>
            <div class="relative">
              <span class="material-symbols-outlined absolute left-4 top-1/2 transform -translate-y-1/2 text-on-surface-variant z-10 pointer-events-none" data-icon="lock">
                lock
              </span>
              <input class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-3 pl-12 pr-4 text-on-surface placeholder:text-outline focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md h-12" id="password" name="password" placeholder="â€¢â€¢â€¢â€¢â€¢â€¢â€¢â€¢" required="" type="password" />
            </div>
          </div>
          <!-- Actions -->
          <div class="flex flex-col gap-4 mt-4 w-full">
            <button class="w-full bg-primary hover:bg-primary/90 text-on-primary font-h3 text-body-lg h-14 rounded-lg flex items-center justify-center gap-2 transition-all shadow-sm" type="submit">
              <span>Iniciar SesiÃ³n</span>
              <span class="material-symbols-outlined" data-icon="arrow_forward">
                arrow_forward
              </span>
            </button>
            <div class="text-center mt-2">
              <a class="font-body-sm text-body-sm text-primary hover:text-primary-container transition-colors hover:underline" href="#">
                Â¿OlvidÃ³ su contraseÃ±a?
              </a>
            </div>
          </div>
        </form>
      </div>
      <!-- Bottom Helper / Security Notice -->
      <div class="bg-surface-container-low p-4 text-center border-t border-outline-variant">
        <p class="font-body-sm text-body-sm text-on-surface-variant flex items-center justify-center gap-2">
          <span class="material-symbols-outlined text-[16px]" data-icon="verified_user">
            verified_user
          </span>
          ConexiÃ³n segura institucional
        </p>
      </div>
    </div>
  </main>
  <!-- Footer Component from JSON -->
  <footer class="w-full border-t border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 font-['Inter'] text-sm tracking-normal z-10 relative border-t border-slate-100 dark:border-slate-900 max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center text-slate-600 dark:text-slate-400">
    <div>
      <span class="text-lg font-bold text-green-600 dark:text-green-400">UCAB</span>
      <span class="ml-2">Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.</span>
    </div>
    <div class="flex gap-6">
      <a class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus: outline-none text-green-700" href="#">Aviso Legal</a>
      <a class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus: outline-none text-green-700" href="#">Privacidad</a>
      <a class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus: outline-none text-green-700" href="#">TÃ©rminos y Condiciones</a>
    </div>
  </footer>
</div>

```

## src/app/pages/login/login.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Login } from './login';

describe('Login', () => {
  let component: Login;
  let fixture: ComponentFixture<Login>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Login],
    }).compileComponents();

    fixture = TestBed.createComponent(Login);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/login/login.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {}

```

## src/app/pages/metodos-pago/criptomonedas/criptomonedas.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-family: 'Material Symbols Outlined';
  font-weight: normal;
  font-style: normal;
  font-size: 24px;
  line-height: 1;
  letter-spacing: normal;
  text-transform: none;
  display: inline-block;
  white-space: nowrap;
  word-wrap: normal;
  direction: ltr;
  -webkit-font-feature-settings: 'liga';
  font-feature-settings: 'liga';
  -webkit-font-smoothing: antialiased;
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/metodos-pago/criptomonedas/criptomonedas.html

```html
<div class="bg-background text-on-background font-body-md flex flex-col min-h-screen">
  <main
    class="flex-grow flex items-center justify-center py-xl px-4 md:px-lg w-full max-w-[1728px] mx-auto"
  >
    <div
      class="w-full max-w-container-max bg-surface-container-lowest rounded-xl border border-outline-variant shadow-[0_4px_6px_rgba(0,0,0,0.05)] overflow-hidden flex flex-col lg:flex-row"
    >
      <section
        class="w-full lg:w-5/12 bg-surface-container p-lg lg:p-xl border-b lg:border-b-0 lg:border-r border-outline-variant flex flex-col justify-between"
      >
        <div>
          <h2 class="font-h3 text-h3 text-on-surface mb-lg">Resumen del Servicio</h2>
          <div class="flex gap-md items-start mb-xl pb-md border-b border-outline-variant/50">
            <div
              class="w-24 h-24 rounded-lg overflow-hidden flex-shrink-0 bg-surface-variant border border-outline-variant"
            >
              <img
                alt="Proyecto de grado"
                class="w-full h-full object-cover object-center"
                src="https://lh3.googleusercontent.com/aida-public/AB6AXuBG9_cuOzQE-PkbDuttZTXoYfDsQnri4NdQHKJjNZe_M6CNoz1cNGlxNtsB8VY3VvNidFeS0iiytI6bMy9WpySwHUGOaMioXSUw3fucY7g12LBCCRauDBRefxk-b0H21XbNW4xkM5Bmxnmr6wmTgfotOnSz9xmRgT50idxQoJuz2HxTVdaP_CmWERUMOgNR-3Jm7YlzgxqT9Yy8RchWVBscLy5uLmwjFUgPFmrl-UPfxd4K5ff9PGKNBNuj6t9dgW5ukZTHVEeUeL4G"
              />
            </div>
            <div class="flex flex-col">
              <h3 class="font-body-lg text-body-lg text-on-surface font-semibold mb-xs">
                AsesorÃ­a de Proyecto de Grado
              </h3>
              <p class="font-body-sm text-body-sm text-secondary mb-xs">Facultad de IngenierÃ­a</p>
              <span
                class="inline-flex items-center gap-1 bg-surface-variant text-on-surface-variant font-label-caps text-label-caps px-2 py-1 rounded w-max mt-sm"
              >
                <span class="material-symbols-outlined text-[14px]">calendar_today</span>
                15 Oct, 2024
              </span>
            </div>
          </div>
        </div>
        <div class="flex flex-col gap-sm">
          <div class="flex justify-between items-center">
            <span class="font-body-md text-body-md text-secondary">Subtotal</span>
            <span class="font-body-md text-body-md text-on-surface">$150.00 USD</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="font-body-md text-body-md text-secondary">Impuestos (IVA 16%)</span>
            <span class="font-body-md text-body-md text-on-surface">$24.00 USD</span>
          </div>
          <div
            class="flex justify-between items-center pt-md mt-sm border-t border-outline-variant"
          >
            <span class="font-body-lg text-body-lg text-on-surface font-semibold"
              >Total a Pagar</span
            >
            <span class="font-h3 text-h3 text-primary font-bold">$174.00 USD</span>
          </div>
        </div>
      </section>

      <section class="w-full lg:w-7/12 bg-surface-container-lowest p-lg lg:p-xl flex flex-col">
        <h1 class="font-h2 text-h2 text-on-surface mb-md">MÃ©todo de Pago</h1>
        <p class="font-body-sm text-body-sm text-secondary mb-xl">
          Seleccione su mÃ©todo preferido para completar la transacciÃ³n de manera segura.
        </p>

        <div class="grid grid-cols-1 sm:grid-cols-3 gap-md mb-xl">
          <label class="cursor-pointer">
            <input class="peer sr-only" name="payment_method" type="radio" value="card" />
            <div
              class="h-full border border-outline-variant rounded-lg p-md flex flex-col items-center justify-center gap-sm text-secondary hover:bg-surface-container transition-colors peer-checked:border-primary peer-checked:bg-surface-container-low peer-checked:text-primary"
            >
              <span class="material-symbols-outlined text-[28px]">credit_card</span>
              <span class="font-label-caps text-label-caps text-center"
                >Tarjeta CrÃ©dito/DÃ©bito</span
              >
            </div>
          </label>
          <label class="cursor-pointer">
            <input
              checked=""
              class="peer sr-only"
              name="payment_method"
              type="radio"
              value="crypto"
            />
            <div
              class="h-full border-2 border-primary rounded-lg p-md flex flex-col items-center justify-center gap-sm bg-surface-container-low text-primary transition-colors"
            >
              <span
                class="material-symbols-outlined text-[28px]"
                style="font-variation-settings: 'FILL' 1"
                >currency_bitcoin</span
              >
              <span class="font-label-caps text-label-caps text-center">Criptomonedas</span>
            </div>
          </label>
          <label class="cursor-pointer">
            <input class="peer sr-only" name="payment_method" type="radio" value="bank" />
            <div
              class="h-full border border-outline-variant rounded-lg p-md flex flex-col items-center justify-center gap-sm text-secondary hover:bg-surface-container transition-colors peer-checked:border-primary peer-checked:bg-surface-container-low peer-checked:text-primary"
            >
              <span class="material-symbols-outlined text-[28px]">account_balance</span>
              <span class="font-label-caps text-label-caps text-center"
                >Transferencia Bancaria</span
              >
            </div>
          </label>
        </div>

        <div class="flex flex-col gap-lg flex-grow">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-md">
            <div class="flex flex-col gap-xs">
              <label class="font-label-caps text-label-caps text-on-surface-variant" for="network"
                >Red de Blockchain</label
              >
              <div class="relative">
                <select
                  class="w-full appearance-none bg-surface border border-outline text-on-surface font-body-md text-body-md rounded-lg px-md py-[10px] focus:outline-none focus:ring-2 focus:ring-primary focus:border-transparent transition-shadow"
                  id="network"
                  name="network"
                >
                  <option value="tron">TRON (TRC20) - USDT</option>
                  <option value="bsc">Binance Smart Chain (BEP20) - USDT</option>
                  <option value="ethereum">Ethereum (ERC20) - USDT</option>
                </select>
                <div
                  class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-md text-secondary"
                >
                  <span class="material-symbols-outlined">expand_more</span>
                </div>
              </div>
            </div>
            <div class="flex flex-col gap-xs">
              <label class="font-label-caps text-label-caps text-on-surface-variant"
                >Tasa de ConversiÃ³n</label
              >
              <div
                class="bg-surface-container px-md py-[10px] rounded-lg border border-outline-variant flex items-center justify-between"
              >
                <span class="font-body-md text-body-md text-on-surface">1.00 USDT</span>
                <span class="material-symbols-outlined text-secondary text-[16px]">sync_alt</span>
                <span class="font-body-md text-body-md text-on-surface">$1.00 USD</span>
              </div>
            </div>
          </div>

          <div class="flex flex-col gap-xs">
            <label class="font-label-caps text-label-caps text-on-surface-variant"
              >DirecciÃ³n de Billetera (Destino)</label
            >
            <div class="flex gap-md items-start">
              <div
                class="flex-grow flex bg-surface border border-outline rounded-lg overflow-hidden focus-within:ring-2 focus-within:ring-primary focus-within:border-transparent transition-shadow"
              >
                <input
                  class="w-full bg-transparent border-none text-on-surface font-body-md text-body-md px-md py-[10px] focus:ring-0 text-ellipsis"
                  readonly=""
                  type="text"
                  value="TJCnKyYw3k2v6J...mPv7bX9q1R"
                />
                <button
                  class="bg-surface-container hover:bg-surface-container-high text-primary px-md py-[10px] border-l border-outline transition-colors flex items-center gap-xs"
                  title="Copiar DirecciÃ³n"
                  type="button"
                >
                  <span class="material-symbols-outlined text-[18px]">content_copy</span>
                  <span class="font-label-caps text-label-caps hidden sm:inline">Copiar</span>
                </button>
              </div>
              <div
                class="w-12 h-12 flex-shrink-0 bg-surface-container border border-outline-variant rounded-lg flex items-center justify-center text-secondary cursor-pointer hover:bg-surface-variant transition-colors"
                title="Mostrar QR"
              >
                <span class="material-symbols-outlined">qr_code_2</span>
              </div>
            </div>
            <p class="font-body-sm text-body-sm text-secondary mt-1">
              EnvÃ­e exactamente 174.00 USDT a esta direcciÃ³n a travÃ©s de la red TRC20.
            </p>
          </div>

          <div class="flex flex-col gap-xs">
            <label class="font-label-caps text-label-caps text-on-surface-variant" for="dxid"
              >DXID / Hash de TransacciÃ³n <span class="text-error">*</span></label
            >
            <input
              class="w-full bg-surface border border-outline text-on-surface font-body-md text-body-md rounded-lg px-md py-[10px] focus:outline-none focus:ring-2 focus:ring-primary focus:border-transparent transition-shadow"
              id="dxid"
              name="dxid"
              placeholder="Pegue aquÃ­ el ID de su transacciÃ³n para verificar"
              type="text"
            />
          </div>
        </div>

        <div
          class="mt-xl pt-lg border-t border-outline-variant flex flex-col-reverse sm:flex-row justify-end items-center gap-md"
        >
          <button
            class="w-full sm:w-auto bg-surface-container-lowest border border-outline text-secondary hover:bg-surface-variant font-label-caps text-label-caps px-lg py-md rounded-lg transition-colors focus:outline-none focus:ring-2 focus:ring-secondary focus:ring-offset-2"
            type="button"
          >
            Cancelar
          </button>
          <button
            class="w-full sm:w-auto bg-primary text-on-primary hover:bg-on-primary-fixed-variant font-label-caps text-label-caps px-lg py-md rounded-lg transition-colors flex items-center justify-center gap-sm focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
            type="button"
          >
            Verificar Pago
            <span class="material-symbols-outlined text-[18px]">check_circle</span>
          </button>
        </div>
      </section>
    </div>
  </main>

  <footer
    class="w-full border-t border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 font-['Inter'] text-sm tracking-normal flat no-shadows"
  >
    <div class="max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center">
      <p class="text-slate-600 dark:text-slate-400">
        Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.
      </p>
      <div class="flex gap-6">
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >Aviso Legal</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >Privacidad</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >TÃ©rminos y Condiciones</a
        >
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/metodos-pago/criptomonedas/criptomonedas.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Criptomonedas } from './criptomonedas';

describe('Criptomonedas', () => {
  let component: Criptomonedas;
  let fixture: ComponentFixture<Criptomonedas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Criptomonedas],
    }).compileComponents();

    fixture = TestBed.createComponent(Criptomonedas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/metodos-pago/criptomonedas/criptomonedas.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-criptomonedas',
  imports: [],
  templateUrl: './criptomonedas.html',
  styleUrl: './criptomonedas.css',
})
export class Criptomonedas {}

```

## src/app/pages/metodos-pago/pago-movil/pago-movil.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/metodos-pago/pago-movil/pago-movil.html

```html
<div class="bg-background text-on-background font-body-md antialiased min-h-screen flex flex-col">
  <main
    class="flex-grow w-full max-w-container-max mx-auto px-lg py-xl flex flex-col md:flex-row gap-xl items-start"
  >
    <aside class="w-full md:w-5/12 flex flex-col gap-md">
      <div class="bg-surface border border-outline-variant rounded-xl p-lg shadow-sm">
        <h2 class="font-h3 text-h3 text-on-surface mb-md">Resumen de la solicitud</h2>
        <div class="flex items-start gap-md border-b border-outline-variant pb-md mb-md">
          <div
            class="w-12 h-12 rounded-lg bg-surface-container flex items-center justify-center text-primary shrink-0"
          >
            <span class="material-symbols-outlined">school</span>
          </div>
          <div>
            <h4 class="font-body-lg text-body-lg text-on-surface font-medium">
              EmisiÃ³n de Certificado de Notas
            </h4>
            <p class="font-body-sm text-body-sm text-secondary mt-xs">Solicitud #REQ-2024-8901</p>
          </div>
        </div>
        <div class="flex flex-col gap-sm border-b border-outline-variant pb-md mb-md">
          <div class="flex justify-between items-center">
            <span class="font-body-md text-body-md text-secondary">Subtotal</span>
            <span class="font-body-md text-body-md text-on-surface">Bs. 850,00</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="font-body-md text-body-md text-secondary">Impuestos (IVA 16%)</span>
            <span class="font-body-md text-body-md text-on-surface">Bs. 136,00</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="font-body-md text-body-md text-secondary">Tasa de procesamiento</span>
            <span class="font-body-md text-body-md text-on-surface">Bs. 14,00</span>
          </div>
        </div>
        <div class="flex justify-between items-center mt-sm">
          <span class="font-body-lg text-body-lg text-on-surface font-bold">Total a pagar</span>
          <span class="font-h2 text-h2 text-primary font-bold">Bs. 1.000,00</span>
        </div>
        <div
          class="mt-md bg-surface-container-low p-sm rounded border border-outline-variant flex items-center gap-sm"
        >
          <span class="material-symbols-outlined text-secondary text-[20px]">info</span>
          <p class="font-body-sm text-body-sm text-secondary">
            El pago debe realizarse por el monto exacto mostrado.
          </p>
        </div>
      </div>
      <div class="flex justify-center gap-lg opacity-60">
        <div class="flex items-center gap-xs font-label-caps text-label-caps text-secondary">
          <span class="material-symbols-outlined text-[16px]">lock</span> TransacciÃ³n Segura
        </div>
        <div class="flex items-center gap-xs font-label-caps text-label-caps text-secondary">
          <span class="material-symbols-outlined text-[16px]">verified</span> UCAB Verificado
        </div>
      </div>
    </aside>

    <section class="w-full md:w-7/12 flex flex-col gap-lg">
      <div>
        <h3 class="font-body-lg text-body-lg text-on-surface mb-sm font-medium">
          Selecciona tu mÃ©todo de pago
        </h3>
        <div class="grid grid-cols-3 gap-sm">
          <button
            class="bg-surface-container-low border-2 border-primary rounded-lg p-md flex flex-col items-center gap-xs relative overflow-hidden transition-all text-primary"
          >
            <span class="material-symbols-outlined text-[28px]">phone_iphone</span>
            <span class="font-label-caps text-label-caps">Pago MÃ³vil</span>
            <div class="absolute top-sm right-sm w-2 h-2 rounded-full bg-primary"></div>
          </button>
          <button
            class="bg-surface border border-outline-variant rounded-lg p-md flex flex-col items-center gap-xs text-secondary hover:border-outline transition-all"
          >
            <span class="material-symbols-outlined text-[28px]">credit_card</span>
            <span class="font-label-caps text-label-caps">Tarjeta</span>
          </button>
          <button
            class="bg-surface border border-outline-variant rounded-lg p-md flex flex-col items-center gap-xs text-secondary hover:border-outline transition-all"
          >
            <span class="material-symbols-outlined text-[28px]">account_balance</span>
            <span class="font-label-caps text-label-caps">Transferencia</span>
          </button>
        </div>
      </div>

      <div class="bg-surface border border-outline-variant rounded-xl p-lg shadow-sm">
        <div class="bg-surface-container-low border border-outline-variant rounded-lg p-md mb-lg">
          <h4 class="font-label-caps text-label-caps text-secondary uppercase tracking-wider mb-sm">
            Datos para realizar el pago mÃ³vil
          </h4>
          <div class="grid grid-cols-2 gap-sm">
            <div>
              <span class="font-body-sm text-body-sm text-secondary block">Banco Destino</span>
              <span class="font-body-md text-body-md text-on-surface font-medium"
                >Mercantil (0105)</span
              >
            </div>
            <div>
              <span class="font-body-sm text-body-sm text-secondary block">TelÃ©fono Destino</span>
              <span class="font-body-md text-body-md text-on-surface font-medium"
                >0414-1234567</span
              >
            </div>
            <div class="col-span-2">
              <span class="font-body-sm text-body-sm text-secondary block">RIF</span>
              <span class="font-body-md text-body-md text-on-surface font-medium"
                >J-00012255-5 (UCAB)</span
              >
            </div>
          </div>
        </div>

        <form class="flex flex-col gap-md">
          <div>
            <label class="font-label-caps text-label-caps text-secondary mb-xs block" for="banco"
              >Banco Emisor</label
            >
            <div class="relative">
              <select
                class="w-full bg-surface border border-outline-variant rounded-lg p-sm pl-md pr-xl font-body-md text-body-md text-on-surface appearance-none focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all"
                id="banco"
                name="banco"
              >
                <option disabled="" selected="" value="">Seleccione un banco...</option>
                <option value="0102">Banco de Venezuela (0102)</option>
                <option value="0104">Venezolano de CrÃ©dito (0104)</option>
                <option value="0105">Mercantil (0105)</option>
                <option value="0108">Provincial (0108)</option>
                <option value="0134">Banesco (0134)</option>
                <option value="0151">BFC (0151)</option>
                <option value="0156">100% Banco (0156)</option>
                <option value="0191">BNC (0191)</option>
              </select>
              <div
                class="absolute inset-y-0 right-0 flex items-center pr-sm pointer-events-none text-secondary"
              >
                <span class="material-symbols-outlined">expand_more</span>
              </div>
            </div>
          </div>

          <div>
            <label class="font-label-caps text-label-caps text-secondary mb-xs block" for="telefono"
              >TelÃ©fono Emisor</label
            >
            <div class="flex gap-sm">
              <select
                class="w-1/3 md:w-1/4 bg-surface border border-outline-variant rounded-lg p-sm font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all"
              >
                <option>0414</option>
                <option>0424</option>
                <option>0412</option>
                <option>0416</option>
                <option>0426</option>
              </select>
              <input
                class="w-2/3 md:w-3/4 bg-surface border border-outline-variant rounded-lg p-sm font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all placeholder:text-outline-variant"
                id="telefono"
                maxlength="7"
                name="telefono"
                pattern="[0-9]{7}"
                placeholder="Ej: 1234567"
                type="tel"
              />
            </div>
          </div>

          <div>
            <label
              class="font-label-caps text-label-caps text-secondary mb-xs block"
              for="referencia"
              >NÃºmero de Referencia</label
            >
            <input
              class="w-full bg-surface border border-outline-variant rounded-lg p-sm font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all placeholder:text-outline-variant"
              id="referencia"
              name="referencia"
              placeholder="Ãšltimos 4 a 6 dÃ­gitos"
              type="text"
            />
            <p class="font-body-sm text-body-sm text-secondary mt-xs">
              Ingrese el nÃºmero de comprobante arrojado por su banco al realizar la operaciÃ³n.
            </p>
          </div>

          <div class="mt-sm pt-md border-t border-outline-variant">
            <button
              class="w-full bg-primary text-on-primary font-label-caps text-label-caps uppercase tracking-wider rounded-lg py-md px-lg hover:bg-primary/90 hover:shadow-md transition-all flex justify-center items-center gap-sm"
              type="button"
            >
              Registrar Pago
              <span class="material-symbols-outlined text-[18px]">check_circle</span>
            </button>
          </div>
        </form>
      </div>
    </section>
  </main>

  <footer class="w-full border-t border-outline-variant bg-surface py-md mt-auto">
    <div
      class="max-w-container-max mx-auto px-lg flex flex-col md:flex-row justify-between items-center gap-sm"
    >
      <span class="font-body-sm text-body-sm text-secondary"
        >Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.</span
      >
      <div class="flex gap-md font-body-sm text-body-sm text-secondary">
        <a class="hover:text-primary transition-colors" href="#">Aviso Legal</a>
        <a class="hover:text-primary transition-colors" href="#">Privacidad</a>
        <a class="hover:text-primary transition-colors" href="#">TÃ©rminos y Condiciones</a>
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/metodos-pago/pago-movil/pago-movil.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoMovil } from './pago-movil';

describe('PagoMovil', () => {
  let component: PagoMovil;
  let fixture: ComponentFixture<PagoMovil>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PagoMovil],
    }).compileComponents();

    fixture = TestBed.createComponent(PagoMovil);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/metodos-pago/pago-movil/pago-movil.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-pago-movil',
  imports: [],
  templateUrl: './pago-movil.html',
  styleUrl: './pago-movil.css',
})
export class PagoMovil {}

```

## src/app/pages/metodos-pago/tarjeta/tarjeta.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}

/* Efecto Glassmorphism utilizado en tus tarjetas de diseÃ±o */
.glass-card {
  background: linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(255, 255, 255, 0.6) 100%);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.5);
}

/* Gradiente corporativo oscuro para elementos destacados */
.cc-gradient {
  background: linear-gradient(135deg, #0b1c30 0%, #213145 100%);
}
```

## src/app/pages/metodos-pago/tarjeta/tarjeta.html

```html
<div class="bg-surface min-h-screen flex flex-col text-on-surface antialiased">
  <main class="flex-grow w-full max-w-container-max mx-auto px-gutter py-xl">
    <div class="mb-xl text-center md:text-left">
      <h1 class="font-h1 text-h1 text-on-surface mb-xs">Pago Seguro</h1>
      <p
        class="font-body-lg text-body-lg text-on-surface-variant flex items-center justify-center md:justify-start gap-xs"
      >
        <span class="material-symbols-outlined text-[20px] text-primary">lock</span>
        Completa los detalles para procesar tu solicitud.
      </p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-xl">
      <div class="lg:col-span-8 flex flex-col gap-lg">
        <section
          class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg shadow-sm"
        >
          <h2 class="font-h3 text-h3 text-on-surface mb-md">MÃ©todo de Pago</h2>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-md">
            <div
              class="border-2 border-primary bg-surface-container-low rounded-lg p-md flex items-center justify-between cursor-pointer relative overflow-hidden"
            >
              <div
                class="absolute top-0 right-0 bg-primary text-on-primary text-[10px] font-bold px-2 py-1 rounded-bl-lg"
              >
                SELECCIONADO
              </div>
              <div class="flex items-center gap-md">
                <div
                  class="w-12 h-8 bg-surface-container-lowest rounded border border-outline-variant flex items-center justify-center text-primary"
                >
                  <span class="material-symbols-outlined">credit_card</span>
                </div>
                <span class="font-body-md text-body-md font-semibold text-on-surface">Tarjeta</span>
              </div>
              <span
                class="material-symbols-outlined text-primary"
                style="font-variation-settings: 'FILL' 1"
                >radio_button_checked</span
              >
            </div>

            <div
              class="border border-outline-variant hover:border-outline bg-surface-container-lowest rounded-lg p-md flex items-center justify-between cursor-pointer transition-colors opacity-60"
            >
              <div class="flex items-center gap-md">
                <div
                  class="w-12 h-8 bg-surface-container rounded border border-outline-variant flex items-center justify-center text-secondary"
                >
                  <span class="material-symbols-outlined">account_balance</span>
                </div>
                <span class="font-body-md text-body-md text-on-surface-variant">Transferencia</span>
              </div>
              <span class="material-symbols-outlined text-outline-variant"
                >radio_button_unchecked</span
              >
            </div>
          </div>
        </section>

        <section
          class="bg-surface-container-lowest border border-outline-variant rounded-xl p-lg shadow-sm relative overflow-hidden"
        >
          <div
            class="absolute -right-24 -top-24 w-64 h-64 bg-surface-container rounded-full blur-3xl opacity-50 pointer-events-none"
          ></div>
          <h2 class="font-h3 text-h3 text-on-surface mb-lg">Detalles de la Tarjeta</h2>
          <form class="grid grid-cols-1 md:grid-cols-2 gap-x-lg gap-y-md relative z-10">
            <div class="md:col-span-2">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant block mb-unit uppercase"
                for="cardNumber"
                >NÃºmero de la tarjeta</label
              >
              <div class="relative">
                <span
                  class="material-symbols-outlined absolute left-sm top-1/2 -translate-y-1/2 text-outline"
                  >credit_card</span
                >
                <input
                  class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-sm pl-xl pr-sm font-body-md text-body-md text-on-surface placeholder:text-outline-variant focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all shadow-sm"
                  id="cardNumber"
                  placeholder="0000 0000 0000 0000"
                  type="text"
                />
              </div>
            </div>

            <div class="md:col-span-1">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant block mb-unit uppercase"
                for="networkType"
                >Tipo de red</label
              >
              <div class="relative">
                <select
                  class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-sm px-md font-body-md text-body-md text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all shadow-sm appearance-none cursor-pointer"
                  id="networkType"
                >
                  <option disabled="" selected="" value="">Seleccione...</option>
                  <option value="visa">Visa</option>
                  <option value="mastercard">Mastercard</option>
                </select>
                <span
                  class="material-symbols-outlined absolute right-sm top-1/2 -translate-y-1/2 text-outline pointer-events-none"
                  >expand_more</span
                >
              </div>
            </div>

            <div class="md:col-span-1">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant block mb-unit uppercase"
                for="company"
                >CompaÃ±Ã­a</label
              >
              <input
                class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-sm px-md font-body-md text-body-md text-on-surface placeholder:text-outline-variant focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all shadow-sm"
                id="company"
                placeholder="Ej. Banco Mercantil"
                type="text"
              />
            </div>

            <div class="md:col-span-1">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant block mb-unit uppercase"
                for="expDate"
                >Fecha de Vencimiento</label
              >
              <input
                class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-sm px-md font-body-md text-body-md text-on-surface placeholder:text-outline-variant focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all shadow-sm"
                id="expDate"
                placeholder="MM/AA"
                type="text"
              />
            </div>

            <div class="md:col-span-1">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant block mb-unit uppercase"
                for="cvv"
                >CVV</label
              >
              <div class="relative">
                <input
                  class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-sm px-md font-body-md text-body-md text-on-surface placeholder:text-outline-variant focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all shadow-sm"
                  id="cvv"
                  maxlength="4"
                  placeholder="â€¢â€¢â€¢"
                  type="password"
                />
                <span
                  class="material-symbols-outlined absolute right-sm top-1/2 -translate-y-1/2 text-outline cursor-help"
                  title="CÃ³digo de seguridad en el reverso de la tarjeta"
                  >help</span
                >
              </div>
            </div>
          </form>
        </section>
      </div>

      <div class="lg:col-span-4">
        <div
          class="bg-surface-container-lowest border border-outline-variant rounded-xl shadow-sm sticky top-xl flex flex-col h-full max-h-[800px]"
        >
          <div class="p-lg border-b border-outline-variant bg-surface-container-low rounded-t-xl">
            <h2 class="font-h3 text-h3 text-on-surface flex items-center gap-sm">
              <span class="material-symbols-outlined text-primary">receipt_long</span>
              Resumen de la solicitud
            </h2>
          </div>
          <div class="p-lg flex-grow overflow-y-auto">
            <ul class="flex flex-col gap-md">
              <li class="flex justify-between items-start">
                <div>
                  <p class="font-body-md text-body-md font-medium text-on-surface">
                    AsesorÃ­a AcadÃ©mica
                  </p>
                  <p class="font-body-sm text-body-sm text-on-surface-variant">
                    Facultad de IngenierÃ­a
                  </p>
                </div>
                <span class="font-body-md text-body-md text-on-surface">$45.00</span>
              </li>
              <li class="flex justify-between items-start">
                <div>
                  <p class="font-body-md text-body-md font-medium text-on-surface">
                    EmisiÃ³n de Constancia
                  </p>
                  <p class="font-body-sm text-body-sm text-on-surface-variant">TrÃ¡mite express</p>
                </div>
                <span class="font-body-md text-body-md text-on-surface">$15.00</span>
              </li>
            </ul>
            <div class="mt-xl pt-md border-t border-dashed border-outline-variant">
              <div class="flex justify-between items-center mb-xs">
                <span class="font-body-md text-body-md text-on-surface-variant">Subtotal</span>
                <span class="font-body-md text-body-md text-on-surface">$60.00</span>
              </div>
              <div class="flex justify-between items-center mb-md">
                <span class="font-body-md text-body-md text-on-surface-variant"
                  >Impuestos (IVA 16%)</span
                >
                <span class="font-body-md text-body-md text-on-surface">$9.60</span>
              </div>
            </div>
          </div>
          <div class="p-lg bg-surface-container rounded-b-xl mt-auto">
            <div class="flex justify-between items-end mb-lg">
              <span class="font-h3 text-h3 text-on-surface">Total a Pagar</span>
              <span class="font-h2 text-h2 text-primary font-bold">$69.60</span>
            </div>
            <button
              class="w-full bg-primary text-on-primary font-body-lg text-body-lg rounded-lg py-md hover:bg-primary-fixed-variant transition-colors flex justify-center items-center gap-sm shadow-sm font-semibold mb-md"
              type="button"
            >
              <span class="material-symbols-outlined">lock</span>
              Procesar Pago Seguro
            </button>
            <p
              class="font-body-sm text-body-sm text-on-surface-variant text-center flex items-center justify-center gap-xs"
            >
              <span class="material-symbols-outlined text-[16px]">verified_user</span>
              Tus datos estÃ¡n encriptados y seguros
            </p>
          </div>
        </div>
      </div>
    </div>
  </main>
</div>

```

## src/app/pages/metodos-pago/tarjeta/tarjeta.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tarjeta } from './tarjeta';

describe('Tarjeta', () => {
  let component: Tarjeta;
  let fixture: ComponentFixture<Tarjeta>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tarjeta],
    }).compileComponents();

    fixture = TestBed.createComponent(Tarjeta);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/metodos-pago/tarjeta/tarjeta.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-tarjeta',
  imports: [],
  templateUrl: './tarjeta.html',
  styleUrl: './tarjeta.css',
})
export class Tarjeta {}

```

## src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.html

```html
<div class="bg-background text-on-background min-h-screen flex flex-col antialiased">
  <main class="flex-grow w-full max-w-container-max mx-auto px-gutter py-xl">
    <div class="mb-lg">
      <h1 class="font-h2 text-h2 text-on-surface">Procesar Pago</h1>
      <p class="font-body-md text-body-md text-on-surface-variant mt-sm">
        Complete los detalles de su mÃ©todo de pago para finalizar la solicitud.
      </p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-xl items-start">
      <div class="lg:col-span-8 flex flex-col gap-lg">
        <section>
          <h2 class="font-h3 text-h3 text-on-surface mb-md">MÃ©todo de Pago</h2>
          <div class="grid grid-cols-1 md:grid-cols-3 gap-md">
            <div
              class="border-2 border-primary bg-surface-container-low rounded-xl p-md flex flex-col items-start cursor-pointer relative shadow-[0_4px_6px_rgba(0,0,0,0.02)] transition-all"
            >
              <div class="absolute top-md right-md text-primary">
                <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1"
                  >check_circle</span
                >
              </div>
              <span class="material-symbols-outlined text-[32px] text-primary mb-sm"
                >credit_card</span
              >
              <span class="font-body-md text-body-md font-semibold text-on-surface"
                >Tarjeta TAI</span
              >
              <span class="font-body-sm text-body-sm text-on-surface-variant mt-xs"
                >Pago institucional</span
              >
            </div>

            <div
              class="border border-outline-variant bg-surface rounded-xl p-md flex flex-col items-start cursor-pointer hover:border-outline hover:bg-surface-container-lowest transition-all opacity-70"
            >
              <span class="material-symbols-outlined text-[32px] text-on-surface-variant mb-sm"
                >account_balance</span
              >
              <span class="font-body-md text-body-md font-medium text-on-surface"
                >Transferencia</span
              >
              <span class="font-body-sm text-body-sm text-on-surface-variant mt-xs"
                >Bancos Nacionales</span
              >
            </div>

            <div
              class="border border-outline-variant bg-surface rounded-xl p-md flex flex-col items-start cursor-pointer hover:border-outline hover:bg-surface-container-lowest transition-all opacity-70"
            >
              <span class="material-symbols-outlined text-[32px] text-on-surface-variant mb-sm"
                >payments</span
              >
              <span class="font-body-md text-body-md font-medium text-on-surface">Zelle</span>
              <span class="font-body-sm text-body-sm text-on-surface-variant mt-xs"
                >Moneda extranjera</span
              >
            </div>
          </div>
        </section>

        <section
          class="bg-surface border border-outline-variant rounded-xl p-lg shadow-[0_4px_6px_rgba(0,0,0,0.05)]"
        >
          <div class="flex items-center gap-sm mb-lg border-b border-outline-variant pb-md">
            <span class="material-symbols-outlined text-primary text-[28px]"
              >account_balance_wallet</span
            >
            <h3 class="font-h3 text-h3 text-on-surface">Detalles de Tarjeta TAI</h3>
          </div>

          <div class="grid grid-cols-1 gap-lg">
            <div class="flex flex-col gap-xs">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                for="pos-digital"
                >POS Digital</label
              >
              <div class="relative">
                <span
                  class="material-symbols-outlined absolute left-md top-1/2 -translate-y-1/2 text-on-surface-variant"
                  >point_of_sale</span
                >
                <input
                  class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg py-md pl-[48px] pr-md font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all"
                  id="pos-digital"
                  placeholder="Ingrese el cÃ³digo del POS Digital"
                  type="text"
                />
              </div>
              <span class="font-body-sm text-body-sm text-on-surface-variant"
                >El cÃ³digo de 6 a 8 dÃ­gitos generado por su terminal.</span
              >
            </div>

            <div class="flex flex-col gap-xs">
              <label
                class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                for="recibo-digital"
                >Recibo Digital</label
              >
              <div
                class="border-2 border-dashed border-outline-variant rounded-xl p-lg flex flex-col items-center justify-center bg-surface-container-lowest hover:bg-surface-container-low hover:border-primary transition-all cursor-pointer group"
              >
                <span
                  class="material-symbols-outlined text-[40px] text-on-surface-variant group-hover:text-primary mb-sm transition-colors"
                  >upload_file</span
                >
                <span class="font-body-md text-body-md font-medium text-on-surface"
                  >Haga clic para subir o arrastre el archivo</span
                >
                <span class="font-body-sm text-body-sm text-on-surface-variant mt-xs"
                  >Formatos soportados: PDF, JPG, PNG (Max. 5MB)</span
                >
              </div>
            </div>

            <div
              class="bg-secondary-container rounded-xl p-lg flex flex-col gap-md mt-sm border border-secondary-fixed-dim"
            >
              <div class="flex items-center gap-sm text-on-secondary-container">
                <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1"
                  >info</span
                >
                <span class="font-body-md text-body-md font-medium">ProyecciÃ³n de Saldo</span>
              </div>
              <div class="flex flex-col gap-sm">
                <div
                  class="flex justify-between items-center border-b border-on-secondary-container/20 pb-sm"
                >
                  <span class="font-body-md text-body-md text-on-secondary-container opacity-80"
                    >Saldo Actual en TAI</span
                  >
                  <span class="font-body-md text-body-md font-medium text-on-secondary-container"
                    >$1,250.00</span
                  >
                </div>
                <div
                  class="flex justify-between items-center border-b border-on-secondary-container/20 pb-sm"
                >
                  <span class="font-body-md text-body-md text-on-secondary-container opacity-80"
                    >Monto de la Solicitud</span
                  >
                  <span class="font-body-md text-body-md font-medium text-error">-$450.00</span>
                </div>
                <div class="flex justify-between items-center pt-xs">
                  <span class="font-h3 text-h3 text-on-secondary-container">Saldo Restante</span>
                  <span class="font-h3 text-h3 text-primary-fixed-variant font-bold">$800.00</span>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>

      <div class="lg:col-span-4">
        <aside
          class="bg-surface border border-outline-variant rounded-xl p-lg shadow-[0_4px_6px_rgba(0,0,0,0.05)] sticky top-xl"
        >
          <h2 class="font-h3 text-h3 text-on-surface border-b border-outline-variant pb-md mb-md">
            Resumen de la Solicitud
          </h2>
          <div class="flex flex-col gap-md mb-xl">
            <div class="flex justify-between items-start">
              <div>
                <span class="font-body-md text-body-md font-medium text-on-surface block"
                  >RenovaciÃ³n de Credenciales</span
                >
                <span class="font-body-sm text-body-sm text-on-surface-variant block mt-xs"
                  >Servicio Administrativo</span
                >
              </div>
              <span class="font-body-md text-body-md text-on-surface">$400.00</span>
            </div>
            <div class="flex justify-between items-start">
              <div>
                <span class="font-body-md text-body-md font-medium text-on-surface block"
                  >Gastos de Procesamiento TAI</span
                >
                <span class="font-body-sm text-body-sm text-on-surface-variant block mt-xs"
                  >Tarifa fija de plataforma</span
                >
              </div>
              <span class="font-body-md text-body-md text-on-surface">$50.00</span>
            </div>
          </div>

          <div class="border-t border-outline-variant pt-md mb-xl">
            <div class="flex justify-between items-end">
              <span class="font-body-lg text-body-lg font-medium text-on-surface"
                >Total a Pagar</span
              >
              <span class="font-h2 text-h2 text-primary font-bold tracking-tight">$450.00</span>
            </div>
          </div>

          <button
            class="w-full bg-primary hover:bg-primary/90 text-on-primary font-body-md text-body-md font-medium py-md rounded-lg flex items-center justify-center gap-sm transition-all focus:ring-4 focus:ring-primary/30 outline-none"
          >
            <span class="material-symbols-outlined">lock</span>
            Confirmar Pago
          </button>

          <p
            class="font-body-sm text-body-sm text-center text-on-surface-variant mt-md flex items-center justify-center gap-xs"
          >
            <span class="material-symbols-outlined text-[16px]">verified_user</span>
            TransacciÃ³n encriptada y segura.
          </p>
        </aside>
      </div>
    </div>
  </main>

  <footer
    class="w-full border-t border-slate-200 dark:border-slate-800 bg-white dark:bg-slate-950 mt-auto border-t border-slate-100 dark:border-slate-900 flat no-shadows"
  >
    <div class="max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center">
      <div class="flex flex-col gap-sm">
        <span class="text-lg font-bold text-green-600 dark:text-green-400 font-['Inter']"
          >UCAB SERVICES</span
        >
        <span class="text-slate-600 dark:text-slate-400 font-['Inter'] text-sm tracking-normal"
          >Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.</span
        >
      </div>
      <nav class="flex gap-lg">
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 font-['Inter'] text-sm tracking-normal hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >Aviso Legal</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 font-['Inter'] text-sm tracking-normal hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >Privacidad</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 font-['Inter'] text-sm tracking-normal hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >TÃ©rminos y Condiciones</a
        >
      </nav>
    </div>
  </footer>
</div>

```

## src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarjetaTai } from './tarjeta-tai';

describe('TarjetaTai', () => {
  let component: TarjetaTai;
  let fixture: ComponentFixture<TarjetaTai>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TarjetaTai],
    }).compileComponents();

    fixture = TestBed.createComponent(TarjetaTai);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-tarjeta-tai',
  imports: [],
  templateUrl: './tarjeta-tai.html',
  styleUrl: './tarjeta-tai.css',
})
export class TarjetaTai {}

```

## src/app/pages/metodos-pago/zelle/zelle.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-family: 'Material Symbols Outlined';
  font-weight: normal;
  font-style: normal;
  font-size: 24px;
  line-height: 1;
  letter-spacing: normal;
  text-transform: none;
  display: inline-block;
  white-space: nowrap;
  word-wrap: normal;
  direction: ltr;
  -webkit-font-feature-settings: 'liga';
  font-feature-settings: 'liga';
  -webkit-font-smoothing: antialiased;
}
```

## src/app/pages/metodos-pago/zelle/zelle.html

```html
<div class="bg-background text-on-background font-body-md min-h-screen flex flex-col antialiased">
  <main
    class="flex-1 w-full max-w-container-max mx-auto px-gutter py-xl grid grid-cols-1 lg:grid-cols-12 gap-xl"
  >
    <div class="col-span-1 lg:col-span-8 flex flex-col gap-lg">
      <div class="flex flex-col gap-xs">
        <h1 class="font-h1 text-h1 text-on-background">MÃ©todo de Pago</h1>
        <p class="font-body-lg text-body-lg text-on-surface-variant">
          Selecciona y completa los datos de tu mÃ©todo de pago preferido para procesar la solicitud.
        </p>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-3 gap-md">
        <div
          class="border-2 border-primary bg-surface-container-low rounded-xl p-md flex flex-col items-center justify-center gap-sm cursor-pointer relative overflow-hidden transition-all shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
        >
          <div class="absolute top-sm right-sm text-primary">
            <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1"
              >check_circle</span
            >
          </div>
          <div
            class="w-12 h-12 rounded-full bg-surface text-primary flex items-center justify-center mb-xs shadow-sm"
          >
            <span
              class="material-symbols-outlined text-[28px]"
              style="font-variation-settings: 'FILL' 1"
              >attach_money</span
            >
          </div>
          <span class="font-label-caps text-label-caps text-primary">Zelle</span>
        </div>

        <div
          class="border border-outline-variant bg-surface rounded-xl p-md flex flex-col items-center justify-center gap-sm cursor-pointer hover:border-outline hover:bg-surface-container transition-all"
        >
          <div
            class="w-12 h-12 rounded-full bg-surface-container text-on-surface-variant flex items-center justify-center mb-xs"
          >
            <span class="material-symbols-outlined text-[28px]">account_balance</span>
          </div>
          <span class="font-label-caps text-label-caps text-on-surface-variant">Transferencia</span>
        </div>

        <div
          class="border border-outline-variant bg-surface rounded-xl p-md flex flex-col items-center justify-center gap-sm cursor-pointer hover:border-outline hover:bg-surface-container transition-all"
        >
          <div
            class="w-12 h-12 rounded-full bg-surface-container text-on-surface-variant flex items-center justify-center mb-xs"
          >
            <span class="material-symbols-outlined text-[28px]">payments</span>
          </div>
          <span class="font-label-caps text-label-caps text-on-surface-variant"
            >Efectivo (Caja)</span
          >
        </div>
      </div>

      <div
        class="bg-surface rounded-xl border border-outline-variant p-lg flex flex-col gap-lg shadow-sm"
      >
        <div class="flex items-start gap-md pb-md border-b border-outline-variant">
          <div
            class="w-12 h-12 rounded-full bg-primary/10 text-primary flex items-center justify-center flex-shrink-0"
          >
            <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1"
              >info</span
            >
          </div>
          <div class="flex flex-col gap-xs">
            <h3 class="font-h3 text-h3 text-on-background">Datos para transferencia Zelle</h3>
            <p class="font-body-md text-body-md text-on-surface-variant">
              Realiza la transferencia desde tu aplicaciÃ³n bancaria a la siguiente direcciÃ³n de
              correo. AsegÃºrate de incluir el monto exacto mostrado en el resumen.
            </p>
            <div
              class="mt-sm bg-surface-container-low border border-outline-variant/50 p-sm rounded-lg flex items-center justify-between group cursor-pointer hover:bg-surface-container transition-colors"
            >
              <span class="font-body-lg text-body-lg text-on-surface font-medium select-all"
                >pagos.zelle&#64;ucab.edu.ve</span
              >
              <div class="flex items-center gap-xs text-primary">
                <span
                  class="font-label-caps text-label-caps opacity-0 group-hover:opacity-100 transition-opacity"
                  >COPIAR</span
                >
                <span class="material-symbols-outlined text-[20px]">content_copy</span>
              </div>
            </div>
          </div>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-md">
          <div class="flex flex-col gap-unit col-span-1 md:col-span-2">
            <label class="font-label-caps text-label-caps text-on-surface-variant"
              >Nombre Titular de la Cuenta</label
            >
            <input
              class="w-full bg-surface border border-outline-variant rounded-lg px-md py-sm font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all placeholder:text-secondary/50"
              placeholder="Ej. Juan PÃ©rez"
              type="text"
            />
          </div>

          <div class="flex flex-col gap-unit col-span-1">
            <label class="font-label-caps text-label-caps text-on-surface-variant"
              >Correo de Origen (Zelle)</label
            >
            <input
              class="w-full bg-surface border border-outline-variant rounded-lg px-md py-sm font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all placeholder:text-secondary/50"
              placeholder="ejemplo@correo.com"
              type="email"
            />
          </div>

          <div class="flex flex-col gap-unit col-span-1">
            <label class="font-label-caps text-label-caps text-on-surface-variant"
              >CÃ³digo de ConfirmaciÃ³n / Referencia</label
            >
            <input
              class="w-full bg-surface border border-outline-variant rounded-lg px-md py-sm font-body-md text-body-md text-on-surface focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all placeholder:text-secondary/50 uppercase"
              placeholder="Obligatorio"
              type="text"
            />
          </div>

          <div class="col-span-1 md:col-span-2 mt-xs flex items-center gap-sm text-secondary">
            <span class="material-symbols-outlined text-[16px]">help</span>
            <span class="font-body-sm text-body-sm"
              >El cÃ³digo de confirmaciÃ³n se encuentra en el recibo de tu transferencia
              bancaria.</span
            >
          </div>
        </div>
      </div>
    </div>

    <div class="col-span-1 lg:col-span-4 relative">
      <div
        class="bg-surface rounded-xl border border-outline-variant p-lg flex flex-col gap-lg sticky top-xl shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
      >
        <div class="flex items-center gap-sm border-b border-outline-variant pb-sm">
          <span class="material-symbols-outlined text-primary">receipt_long</span>
          <h2 class="font-h3 text-h3 text-on-background">Resumen de la solicitud</h2>
        </div>

        <div class="flex flex-col gap-md">
          <div class="flex items-start justify-between group">
            <div class="flex flex-col">
              <span class="font-body-md text-body-md text-on-background font-medium"
                >Constancia de Estudio Simple</span
              >
              <span class="font-body-sm text-body-sm text-on-surface-variant"
                >Sede MontalbÃ¡n â€¢ Pregrado</span
              >
            </div>
            <span class="font-body-md text-body-md text-on-background font-medium whitespace-nowrap"
              >$ 15.00</span
            >
          </div>
          <div class="flex items-start justify-between group">
            <div class="flex flex-col">
              <span class="font-body-md text-body-md text-on-background font-medium"
                >CertificaciÃ³n de Notas</span
              >
              <span class="font-body-sm text-body-sm text-on-surface-variant"
                >FÃ­sico â€¢ IngenierÃ­a</span
              >
            </div>
            <span class="font-body-md text-body-md text-on-background font-medium whitespace-nowrap"
              >$ 25.00</span
            >
          </div>
        </div>

        <div class="h-px w-full bg-outline-variant"></div>

        <div class="flex flex-col gap-sm">
          <div class="flex justify-between font-body-sm text-body-sm text-on-surface-variant">
            <span>Subtotal</span>
            <span>$ 40.00</span>
          </div>
          <div class="flex justify-between font-body-sm text-body-sm text-on-surface-variant">
            <span>Gastos administrativos (0%)</span>
            <span>$ 0.00</span>
          </div>
        </div>

        <div
          class="flex justify-between items-end bg-surface-container-low p-sm rounded-lg border border-outline-variant/50"
        >
          <span class="font-h3 text-h3 text-on-background">Total a pagar</span>
          <span class="font-h1 text-h1 text-primary tracking-tight">$ 40.00</span>
        </div>

        <div class="flex flex-col gap-sm mt-xs">
          <button
            class="w-full bg-primary text-on-primary font-label-caps text-label-caps py-md rounded-lg hover:bg-primary-fixed-variant transition-colors flex items-center justify-center gap-sm shadow-sm"
          >
            <span
              class="material-symbols-outlined text-[20px]"
              style="font-variation-settings: 'FILL' 1"
              >lock</span
            >
            PROCESAR PAGO SEGURO
          </button>
          <div
            class="flex items-center justify-center gap-xs text-secondary font-body-sm text-body-sm"
          >
            <span class="material-symbols-outlined text-[16px]">verified_user</span>
            <span>TransacciÃ³n encriptada e institucional</span>
          </div>
        </div>
      </div>
    </div>
  </main>
</div>

```

## src/app/pages/metodos-pago/zelle/zelle.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Zelle } from './zelle';

describe('Zelle', () => {
  let component: Zelle;
  let fixture: ComponentFixture<Zelle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Zelle],
    }).compileComponents();

    fixture = TestBed.createComponent(Zelle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/metodos-pago/zelle/zelle.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-zelle',
  imports: [],
  templateUrl: './zelle.html',
  styleUrl: './zelle.css',
})
export class Zelle {}

```

## src/app/pages/modificar-miembro/modificar-miembro.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/modificar-miembro/modificar-miembro.html

```html
<div
  class="bg-background text-on-background font-body-md text-body-md min-h-screen flex flex-col antialiased"
>
  <nav
    class="fixed bottom-0 left-0 w-full z-50 flex justify-around items-center px-4 py-2 bg-white dark:bg-gray-900 md:hidden border-t border-gray-200 dark:border-gray-800 shadow-[0_-4px_6px_-1px_rgba(0,0,0,0.05)] rounded-t-none"
  >
    <a
      class="flex flex-col items-center justify-center text-green-600 dark:text-green-400 font-bold bg-green-50 dark:bg-green-900/20 rounded-lg px-3 py-1 active:scale-95 transition-transform hover:bg-gray-50 dark:hover:bg-gray-800"
      href="#"
    >
      <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1"
        >dashboard</span
      >
      <span class="text-[11px] font-medium font-inter mt-1">Home</span>
    </a>
    <a
      class="flex flex-col items-center justify-center text-gray-500 dark:text-gray-400 active:scale-95 transition-transform hover:bg-gray-50 dark:hover:bg-gray-800 px-3 py-1 rounded-lg"
      href="#"
    >
      <span class="material-symbols-outlined">inventory_2</span>
      <span class="text-[11px] font-medium font-inter mt-1">Catalog</span>
    </a>
    <a
      class="flex flex-col items-center justify-center text-gray-500 dark:text-gray-400 active:scale-95 transition-transform hover:bg-gray-50 dark:hover:bg-gray-800 px-3 py-1 rounded-lg"
      href="#"
    >
      <span class="material-symbols-outlined">person</span>
      <span class="text-[11px] font-medium font-inter mt-1">Account</span>
    </a>
  </nav>

  <main class="flex-grow w-full max-w-[1280px] mx-auto px-6 py-10 md:py-16 pb-32 md:pb-16">
    <div class="mb-10">
      <div class="flex items-center gap-2 text-on-surface-variant mb-4">
        <a
          class="hover:text-primary transition-colors flex items-center gap-1 font-body-sm text-body-sm"
          href="#"
        >
          <span class="material-symbols-outlined text-[18px]">arrow_back</span>
          Volver a Dashboard
        </a>
      </div>
      <h1 class="font-h1 text-h1 text-on-surface">Modificar Miembro</h1>
      <p class="font-body-lg text-body-lg text-on-surface-variant mt-2 max-w-2xl">
        Revisa y actualiza la informaciÃ³n institucional del miembro seleccionado. Los cambios
        afectarÃ¡n su acceso a los servicios del campus.
      </p>
    </div>

    <form
      class="bg-surface-container-lowest border border-outline-variant rounded-xl shadow-[0_4px_6px_-1px_rgba(0,0,0,0.05)] overflow-hidden flex flex-col"
    >
      <div
        class="p-6 md:p-8 border-b border-outline-variant bg-surface-container-low flex flex-col md:flex-row items-center md:items-start gap-6"
      >
        <div
          class="w-24 h-24 rounded-full border-4 border-surface-container-lowest shadow-sm flex items-center justify-center overflow-hidden flex-shrink-0 bg-surface-variant"
          data-alt="A bright, professional studio portrait of a young male university student..."
          style="
            background-image: url('https://lh3.googleusercontent.com/aida-public/AB6AXuCzotmCjQ12GjM7SDRZ9RRI_HRLcetyZDdFC3tlQ3glMljFtFsVXkqg9uQEFXbIHwK3xcvnbFHxmEJiR4jQnghEGB0GidqKOv-Mv7ResVW-3snQi0XWr5mPQcmjgD9_TXkmedC0hfg2Lef1qKxfdrfloV9BWejvxdf_F98RtoPuyVUHsvoCg3zkxyYPemFkxsJstFUvJNCbJCzWhdF-3bh431r93c6lQndZvrZQeI5mxxsuieq52xDQbLKf2hMkbCL1A-y4vlFk_r2C');
            background-size: cover;
            background-position: center;
          "
        ></div>
        <div class="flex flex-col text-center md:text-left pt-2">
          <h2 class="font-h2 text-h2 text-on-surface">AndrÃ©s Eduardo PÃ©rez GÃ³mez</h2>
          <p
            class="font-body-md text-body-md text-on-surface-variant flex items-center justify-center md:justify-start gap-2 mt-1"
          >
            <span class="material-symbols-outlined text-[18px]">badge</span>
            V-28.123.456
          </p>
          <div class="mt-3 flex gap-2 justify-center md:justify-start">
            <span
              class="px-3 py-1 rounded-full bg-primary/10 text-primary font-label-caps text-label-caps uppercase tracking-wider"
              >Estudiante</span
            >
            <span
              class="px-3 py-1 rounded-full bg-surface-variant text-on-surface-variant font-label-caps text-label-caps uppercase tracking-wider"
              >Activo</span
            >
          </div>
        </div>
      </div>

      <div
        class="grid grid-cols-1 lg:grid-cols-12 gap-0 lg:divide-x divide-outline-variant flex-grow"
      >
        <div class="lg:col-span-7 p-6 md:p-8">
          <h3 class="font-h3 text-h3 text-on-surface mb-6 flex items-center gap-2">
            <span class="material-symbols-outlined text-primary">person</span>
            InformaciÃ³n Personal
          </h3>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-x-6 gap-y-6">
            <div class="flex flex-col gap-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >Primer Nombre</label
              >
              <input
                class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none"
                type="text"
                value="AndrÃ©s"
              />
            </div>
            <div class="flex flex-col gap-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >Segundo Nombre</label
              >
              <input
                class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none"
                type="text"
                value="Eduardo"
              />
            </div>
            <div class="flex flex-col gap-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >Primer Apellido</label
              >
              <input
                class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none"
                type="text"
                value="PÃ©rez"
              />
            </div>
            <div class="flex flex-col gap-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >Segundo Apellido</label
              >
              <input
                class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none"
                type="text"
                value="GÃ³mez"
              />
            </div>
            <div class="flex flex-col gap-2 md:col-span-2 mt-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >NÃºmero de CÃ©dula</label
              >
              <div class="relative">
                <span
                  class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-on-surface-variant"
                  >branding_watermark</span
                >
                <input
                  class="w-full pl-12 pr-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none"
                  type="text"
                  value="V-28.123.456"
                />
              </div>
            </div>
          </div>
        </div>

        <div class="lg:col-span-5 p-6 md:p-8 bg-surface-bright">
          <h3 class="font-h3 text-h3 text-on-surface mb-6 flex items-center gap-2">
            <span class="material-symbols-outlined text-primary">school</span>
            Datos AcadÃ©micos
          </h3>
          <div class="flex flex-col gap-6">
            <div class="flex flex-col gap-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >Rol Institucional</label
              >
              <div class="relative">
                <select
                  class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none appearance-none cursor-pointer"
                >
                  <option selected="" value="Estudiante">Estudiante</option>
                  <option value="Profesor">Profesor</option>
                  <option value="Administrativo">Administrativo</option>
                </select>
                <span
                  class="material-symbols-outlined absolute right-3 top-1/2 -translate-y-1/2 text-on-surface-variant pointer-events-none"
                  >expand_more</span
                >
              </div>
            </div>
            <div class="flex flex-col gap-2">
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >Escuela / Facultad</label
              >
              <div class="relative">
                <select
                  class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none appearance-none cursor-pointer"
                >
                  <option selected="" value="IngenierÃ­a InformÃ¡tica">IngenierÃ­a InformÃ¡tica</option>
                  <option value="IngenierÃ­a Civil">IngenierÃ­a Civil</option>
                  <option value="Derecho">Derecho</option>
                  <option value="AdministraciÃ³n de Empresas">AdministraciÃ³n de Empresas</option>
                </select>
                <span
                  class="material-symbols-outlined absolute right-3 top-1/2 -translate-y-1/2 text-on-surface-variant pointer-events-none"
                  >expand_more</span
                >
              </div>
            </div>
            <div class="grid grid-cols-2 gap-4">
              <div class="flex flex-col gap-2">
                <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                  >Semestre</label
                >
                <input
                  class="w-full px-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none"
                  max="10"
                  min="1"
                  type="number"
                  value="6"
                />
              </div>
              <div class="flex flex-col gap-2">
                <label
                  class="font-label-caps text-label-caps text-on-surface-variant uppercase text-transparent select-none"
                  >Status</label
                >
                <div
                  class="w-full px-4 py-3 bg-surface-container border border-outline-variant rounded-lg font-body-md text-body-md text-on-surface-variant flex items-center gap-2"
                >
                  <div class="w-2 h-2 rounded-full bg-primary"></div>
                  Regular
                </div>
              </div>
            </div>
            <div
              class="flex flex-col gap-2 mt-2 pt-6 border-t border-outline-variant border-dashed"
            >
              <label class="font-label-caps text-label-caps text-on-surface-variant uppercase"
                >NÃºmero de tarjeta TAI</label
              >
              <div class="relative">
                <span
                  class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-on-surface-variant"
                  >credit_card</span
                >
                <input
                  class="w-full pl-12 pr-4 py-3 bg-surface border border-outline-variant rounded-lg focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all font-body-md text-body-md text-on-surface outline-none tracking-widest font-mono"
                  type="text"
                  value="TAI-987654321"
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div
        class="p-6 md:p-8 bg-surface border-t border-outline-variant flex flex-col sm:flex-row justify-end gap-4 items-center"
      >
        <button
          class="w-full sm:w-auto px-6 py-3 bg-surface border border-outline text-on-surface hover:bg-surface-variant rounded-lg font-body-md text-body-md font-semibold transition-colors duration-200 text-center"
          type="button"
        >
          Cancelar
        </button>
        <button
          class="w-full sm:w-auto px-6 py-3 bg-primary text-on-primary hover:bg-primary/90 rounded-lg font-body-md text-body-md font-semibold shadow-sm transition-colors duration-200 flex items-center justify-center gap-2"
          type="submit"
        >
          <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1"
            >save</span
          >
          Guardar Cambios
        </button>
      </div>
    </form>
  </main>

  <footer
    class="bg-gray-50 dark:bg-gray-950 w-full border-t border-gray-200 dark:border-gray-800 flat no-shadows mt-auto"
  >
    <div
      class="w-full py-8 px-6 flex flex-col md:flex-row justify-between items-center max-w-[1280px] mx-auto text-sm font-inter text-gray-600 dark:text-gray-400"
    >
      <div class="flex items-center">
        <span class="font-bold text-green-700 dark:text-green-500">UCAB SERVICES</span>
        <span class="ml-2">Â© 2024 UCAB Services. Academic Excellence.</span>
      </div>
      <div class="flex gap-6 mt-4 md:mt-0">
        <a
          class="text-gray-500 hover:text-green-600 transition-colors duration-150 transition-opacity"
          href="#"
          >Privacy Policy</a
        >
        <a
          class="text-gray-500 hover:text-green-600 transition-colors duration-150 transition-opacity"
          href="#"
          >Terms of Service</a
        >
        <a
          class="text-gray-500 hover:text-green-600 transition-colors duration-150 transition-opacity"
          href="#"
          >Help Desk</a
        >
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/modificar-miembro/modificar-miembro.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarMiembro } from './modificar-miembro';

describe('ModificarMiembro', () => {
  let component: ModificarMiembro;
  let fixture: ComponentFixture<ModificarMiembro>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarMiembro],
    }).compileComponents();

    fixture = TestBed.createComponent(ModificarMiembro);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/modificar-miembro/modificar-miembro.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-modificar-miembro',
  imports: [],
  templateUrl: './modificar-miembro.html',
  styleUrl: './modificar-miembro.css',
})
export class ModificarMiembro {}

```

## src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.html

```html
<div class="bg-background text-on-background min-h-screen flex flex-col font-sans">
  <main class="flex-grow w-full px-gutter py-xl max-w-container-max mx-auto">
    <nav aria-label="Breadcrumb" class="mb-lg">
      <ol class="flex items-center space-x-sm font-body-sm text-body-sm text-secondary">
        <li>
          <a class="hover:text-primary transition-colors" href="#">Services</a>
        </li>
        <li>
          <span class="material-symbols-outlined text-[16px]">chevron_right</span>
        </li>
        <li>
          <a class="hover:text-primary transition-colors" href="#">Job Offers</a>
        </li>
        <li>
          <span class="material-symbols-outlined text-[16px]">chevron_right</span>
        </li>
        <li aria-current="page" class="text-on-background font-semibold">
          Desarrollador Full Stack Senior
        </li>
      </ol>
    </nav>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-gutter">
      <div class="lg:col-span-8 flex flex-col gap-lg">
        <div class="bg-surface rounded-xl border border-outline-variant p-xl shadow-sm">
          <div
            class="flex flex-col md:flex-row justify-between items-start md:items-center gap-md mb-md"
          >
            <div>
              <h1 class="font-h1 text-h1 text-on-background mb-xs">
                Desarrollador Full Stack Senior
              </h1>
              <p class="font-h3 text-h3 text-primary flex items-center gap-xs">
                <span class="material-symbols-outlined" data-weight="fill">business</span>
                TechSolutions C.A.
              </p>
            </div>
            <div class="flex flex-col items-end gap-sm">
              <span
                class="inline-flex items-center px-sm py-xs rounded-full bg-surface-container-highest text-primary font-label-caps text-label-caps border border-primary/20"
              >
                <span class="w-2 h-2 rounded-full bg-primary mr-xs"></span>
                Disponible
              </span>
              <span class="font-body-sm text-body-sm text-secondary flex items-center gap-xs">
                <span class="material-symbols-outlined text-[16px]">calendar_today</span>
                Publicado el 25 de Octubre 2024
              </span>
            </div>
          </div>
          <div class="flex flex-wrap gap-md mt-lg pt-lg border-t border-outline-variant">
            <div
              class="flex items-center gap-sm bg-surface-container-low px-md py-sm rounded-lg border border-outline-variant/50"
            >
              <span class="material-symbols-outlined text-secondary">school</span>
              <div class="flex flex-col">
                <span class="font-label-caps text-label-caps text-secondary"
                  >Requisito AcadÃ©mico</span
                >
                <span class="font-body-md text-body-md font-semibold text-on-background"
                  >Ãndice MÃ­n. 15</span
                >
              </div>
            </div>
            <div
              class="flex items-center gap-sm bg-surface-container-low px-md py-sm rounded-lg border border-outline-variant/50"
            >
              <span class="material-symbols-outlined text-secondary">work</span>
              <div class="flex flex-col">
                <span class="font-label-caps text-label-caps text-secondary">Modalidad</span>
                <span class="font-body-md text-body-md font-semibold text-on-background"
                  >HÃ­brido</span
                >
              </div>
            </div>
            <div
              class="flex items-center gap-sm bg-surface-container-low px-md py-sm rounded-lg border border-outline-variant/50"
            >
              <span class="material-symbols-outlined text-secondary">location_on</span>
              <div class="flex flex-col">
                <span class="font-label-caps text-label-caps text-secondary">UbicaciÃ³n</span>
                <span class="font-body-md text-body-md font-semibold text-on-background"
                  >Caracas, VE</span
                >
              </div>
            </div>
          </div>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-gutter">
          <div
            class="bg-surface rounded-xl border border-outline-variant p-lg shadow-sm md:col-span-2"
          >
            <h2
              class="font-h2 text-h2 text-on-background mb-md flex items-center gap-sm border-b border-outline-variant pb-sm"
            >
              <span class="material-symbols-outlined text-primary">assignment</span>
              Responsabilidades
            </h2>
            <ul
              class="space-y-sm font-body-lg text-body-lg text-on-surface-variant list-disc pl-lg"
            >
              <li>Desarrollo de nuevas funcionalidades robustas y escalables.</li>
              <li>Mantenimiento y optimizaciÃ³n de arquitectura de microservicios.</li>
              <li>Liderazgo tÃ©cnico y mentorÃ­a de equipos de desarrolladores junior.</li>
              <li>OptimizaciÃ³n avanzada de consultas a bases de datos relacionales y NoSQL.</li>
            </ul>
          </div>

          <div
            class="bg-surface rounded-xl border border-outline-variant p-lg shadow-sm hover:shadow-md transition-shadow"
          >
            <h3 class="font-h3 text-h3 text-on-background mb-md flex items-center gap-sm">
              <span class="material-symbols-outlined text-primary">person_search</span>
              Perfil Buscado
            </h3>
            <ul class="space-y-sm font-body-md text-body-md text-on-surface-variant">
              <li class="flex items-start gap-sm">
                <span class="material-symbols-outlined text-primary text-[20px] mt-1"
                  >check_circle</span
                >
                <span>Graduado en IngenierÃ­a InformÃ¡tica.</span>
              </li>
              <li class="flex items-start gap-sm">
                <span class="material-symbols-outlined text-primary text-[20px] mt-1"
                  >check_circle</span
                >
                <span>5+ aÃ±os de experiencia demostrable en Node.js y React.</span>
              </li>
              <li class="flex items-start gap-sm">
                <span class="material-symbols-outlined text-primary text-[20px] mt-1"
                  >check_circle</span
                >
                <span>Conocimientos avanzados en ecosistema AWS.</span>
              </li>
              <li class="flex items-start gap-sm">
                <span class="material-symbols-outlined text-primary text-[20px] mt-1"
                  >check_circle</span
                >
                <span>Dominio del idioma inglÃ©s (Fluido).</span>
              </li>
            </ul>
          </div>

          <div
            class="bg-surface-container-low rounded-xl border border-outline-variant p-lg shadow-sm hover:shadow-md transition-shadow"
          >
            <h3 class="font-h3 text-h3 text-on-background mb-md flex items-center gap-sm">
              <span class="material-symbols-outlined text-primary">redeem</span>
              Beneficios
            </h3>
            <div class="grid grid-cols-1 gap-sm">
              <div
                class="flex items-center gap-md bg-surface p-sm rounded-lg border border-outline-variant/30"
              >
                <span class="material-symbols-outlined text-primary">health_and_safety</span>
                <span class="font-body-md text-body-md text-on-surface-variant"
                  >Seguro mÃ©dico privado extendido</span
                >
              </div>
              <div
                class="flex items-center gap-md bg-surface p-sm rounded-lg border border-outline-variant/30"
              >
                <span class="material-symbols-outlined text-primary">trending_up</span>
                <span class="font-body-md text-body-md text-on-surface-variant"
                  >Bonos trimestrales por desempeÃ±o</span
                >
              </div>
              <div
                class="flex items-center gap-md bg-surface p-sm rounded-lg border border-outline-variant/30"
              >
                <span class="material-symbols-outlined text-primary">schedule</span>
                <span class="font-body-md text-body-md text-on-surface-variant"
                  >Flexibilidad de horario comprobada</span
                >
              </div>
              <div
                class="flex items-center gap-md bg-surface p-sm rounded-lg border border-outline-variant/30"
              >
                <span class="material-symbols-outlined text-primary">menu_book</span>
                <span class="font-body-md text-body-md text-on-surface-variant"
                  >Acceso a cursos especializados</span
                >
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="lg:col-span-4 flex flex-col gap-lg">
        <div
          class="bg-surface rounded-xl border border-outline-variant p-lg shadow-sm sticky top-[88px]"
        >
          <div class="text-center mb-md">
            <h3 class="font-h3 text-h3 text-on-background mb-xs">Â¿Cumples el perfil?</h3>
            <p class="font-body-sm text-body-sm text-secondary">
              AsegÃºrate de que tu perfil acadÃ©mico en UCAB Services estÃ© actualizado antes de
              postularte.
            </p>
          </div>
          <button
            class="w-full bg-primary-container text-on-primary-container font-h3 text-h3 py-md rounded-lg shadow-sm hover:shadow-md hover:bg-primary hover:text-on-primary transition-all duration-200 active:scale-95 flex items-center justify-center gap-sm"
          >
            <span class="material-symbols-outlined">send</span>
            Postularse
          </button>
          <div class="mt-md pt-md border-t border-outline-variant text-center">
            <p class="font-label-caps text-label-caps text-secondary mb-sm">
              ValidaciÃ³n AutomÃ¡tica
            </p>
            <div
              class="flex items-center justify-center gap-xs text-primary bg-surface-container-low py-xs px-sm rounded-md border border-outline-variant/30 inline-flex"
            >
              <span class="material-symbols-outlined text-[16px]">verified</span>
              <span class="font-body-sm text-body-sm">Tu Ã­ndice actual es: 16.5 (Apto)</span>
            </div>
          </div>
        </div>

        <div class="bg-surface rounded-xl border border-outline-variant p-lg shadow-sm">
          <h3
            class="font-h3 text-h3 text-on-background mb-md border-b border-outline-variant pb-sm"
          >
            Sobre la Empresa
          </h3>
          <div class="flex items-center gap-md mb-md">
            <div
              class="w-16 h-16 rounded-lg bg-surface-container-high border border-outline-variant flex items-center justify-center overflow-hidden"
            >
              <img
                alt="Company Logo"
                class="w-full h-full object-cover"
                src="https://lh3.googleusercontent.com/aida-public/AB6AXuCApDwd2C8WyBMtk5jngqQeqR-1aZQMt0BgbXWzUcT2yWYhMBW2f8vaoorPesl2yzqgK-njnfBHoTwCQ_xmE7dk2pRATn8uRx0Cy9gfLsHEYnq1NV8cAYkLFy9w1gwaZFtpbgiRF3MaSs7WSYO1nkhuR67e2pIxqfzYheJb9_ktk7lBFDrPHXcgKNfsj-2UZVkWHzAn5eWA83o8m0rYty5sjZ7ixwxguuE345fHbrMQm7GT88psKrssRZQm7l4hI5xuHuHZGa7BQzoU"
              />
            </div>
            <div>
              <span class="font-h3 text-h3 text-on-background block">TechSolutions C.A.</span>
              <span class="font-body-sm text-body-sm text-secondary"
                >TecnologÃ­a de la InformaciÃ³n</span
              >
            </div>
          </div>
          <p class="font-body-sm text-body-sm text-on-surface-variant mb-md">
            LÃ­deres en el desarrollo de soluciones cloud y software corporativo en la regiÃ³n,
            impulsando la transformaciÃ³n digital de grandes empresas.
          </p>
          <a
            class="font-body-sm text-body-sm text-primary font-semibold hover:underline flex items-center gap-xs"
            href="#"
          >
            Ver mÃ¡s ofertas de esta empresa
            <span class="material-symbols-outlined text-[16px]">arrow_forward</span>
          </a>
        </div>
      </div>
    </div>
  </main>

  <footer class="bg-surface-container-low py-12 border-t border-outline-variant mt-auto">
    <div
      class="flex flex-col md:flex-row justify-between items-center w-full px-lg max-w-container-max mx-auto gap-lg md:gap-0"
    >
      <div class="flex flex-col items-center md:items-start gap-sm">
        <span class="font-h3 text-h3 font-bold text-primary">UCAB SERVICES</span>
        <span class="font-body-sm text-body-sm text-on-surface-variant"
          >Â© 2024 UCAB SERVICES. Institutional Academic Platform.</span
        >
      </div>
      <nav class="flex flex-wrap justify-center gap-md font-body-sm text-body-sm">
        <a
          class="text-on-surface-variant hover:text-primary hover:underline transition-colors duration-200"
          href="#"
          >Privacy Policy</a
        >
        <a
          class="text-on-surface-variant hover:text-primary hover:underline transition-colors duration-200"
          href="#"
          >Terms of Service</a
        >
        <a
          class="text-on-surface-variant hover:text-primary hover:underline transition-colors duration-200"
          href="#"
          >Help Center</a
        >
        <a
          class="text-on-surface-variant hover:text-primary hover:underline transition-colors duration-200"
          href="#"
          >Compliance</a
        >
      </nav>
    </div>
  </footer>
</div>

```

## src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertaLaboralDetalle } from './oferta-laboral-detalle';

describe('OfertaLaboralDetalle', () => {
  let component: OfertaLaboralDetalle;
  let fixture: ComponentFixture<OfertaLaboralDetalle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertaLaboralDetalle],
    }).compileComponents();

    fixture = TestBed.createComponent(OfertaLaboralDetalle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-oferta-laboral-detalle',
  imports: [],
  templateUrl: './oferta-laboral-detalle.html',
  styleUrl: './oferta-laboral-detalle.css',
})
export class OfertaLaboralDetalle {}

```

## src/app/pages/ofertas-laborales/ofertas-laborales.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/ofertas-laborales/ofertas-laborales.html

```html
<div
  class="bg-background text-on-background min-h-screen flex flex-col font-body-md text-body-md antialiased"
>
  <main class="flex-grow w-full max-w-container-max mx-auto px-lg py-xl flex flex-col gap-xl">
    <section class="flex flex-col gap-sm border-b border-outline-variant pb-lg">
      <h1 class="font-h1 text-h1 text-primary">Ofertas Laborales y PasantÃ­as</h1>
      <p class="font-body-lg text-body-lg text-on-surface-variant max-w-3xl">
        Explora las oportunidades profesionales exclusivas para estudiantes. Conecta con empresas
        lÃ­deres, postula a pasantÃ­as y comienza a construir tu futuro laboral mientras completas tus
        estudios.
      </p>
    </section>

    <div
      class="flex flex-wrap items-center justify-between gap-md bg-surface p-md rounded-lg border border-outline-variant"
    >
      <div class="flex gap-sm">
        <span
          class="inline-flex items-center px-3 py-1.5 rounded-full bg-primary-container text-on-primary-container font-label-caps text-label-caps cursor-pointer"
        >
          Todas
        </span>
        <span
          class="inline-flex items-center px-3 py-1.5 rounded-full bg-surface-container-high text-on-surface hover:bg-surface-variant transition-colors font-label-caps text-label-caps cursor-pointer"
        >
          PasantÃ­as
        </span>
        <span
          class="inline-flex items-center px-3 py-1.5 rounded-full bg-surface-container-high text-on-surface hover:bg-surface-variant transition-colors font-label-caps text-label-caps cursor-pointer"
        >
          Tiempo Completo
        </span>
      </div>
      <div class="relative w-full md:w-64">
        <span
          class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline"
          >search</span
        >
        <input
          class="w-full pl-10 pr-4 py-2 bg-surface-container-lowest border border-outline-variant rounded-md font-body-sm text-body-sm focus:outline-none focus:border-primary focus:ring-1 focus:ring-primary transition-all"
          placeholder="Buscar cargos..."
          type="text"
        />
      </div>
    </div>

    <section class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-lg">
      <article
        class="bg-surface rounded-xl border border-outline-variant p-lg flex flex-col gap-md relative overflow-hidden group hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-all duration-300"
      >
        <div class="absolute left-0 top-0 bottom-0 w-1 bg-primary"></div>
        <div class="flex justify-between items-start">
          <h3 class="font-h3 text-h3 text-on-background group-hover:text-primary transition-colors">
            Pasante de Desarrollo Web
          </h3>
          <span class="material-symbols-outlined text-outline" data-icon="work">work</span>
        </div>
        <div class="flex flex-col gap-2">
          <div class="flex items-center gap-xs text-on-surface-variant font-body-sm text-body-sm">
            <span class="material-symbols-outlined text-[16px]" data-icon="business">business</span>
            TechSolutions C.A.
          </div>
          <div class="flex flex-wrap gap-sm mt-2">
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-secondary-container text-on-secondary-container font-label-caps text-label-caps"
            >
              <span class="material-symbols-outlined text-[14px]" data-icon="school">school</span>
              Ãndice Min: 14
            </span>
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-primary-container text-on-primary-container font-label-caps text-label-caps"
            >
              Disponible
            </span>
          </div>
        </div>
        <div class="mt-auto pt-md border-t border-outline-variant flex flex-col gap-sm">
          <p class="font-body-sm text-body-sm text-outline">Publicado el 20 de Octubre 2024</p>
          <button
            class="w-full py-2 bg-transparent border-2 border-primary rounded-lg text-primary font-label-caps text-label-caps hover:bg-primary hover:text-on-primary transition-colors focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
          >
            Ver Detalles
          </button>
        </div>
      </article>

      <article
        class="bg-surface rounded-xl border border-outline-variant p-lg flex flex-col gap-md relative overflow-hidden group hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-all duration-300"
      >
        <div class="absolute left-0 top-0 bottom-0 w-1 bg-primary"></div>
        <div class="flex justify-between items-start">
          <h3 class="font-h3 text-h3 text-on-background group-hover:text-primary transition-colors">
            Analista Contable Junior
          </h3>
          <span class="material-symbols-outlined text-outline" data-icon="calculate"
            >calculate</span
          >
        </div>
        <div class="flex flex-col gap-2">
          <div class="flex items-center gap-xs text-on-surface-variant font-body-sm text-body-sm">
            <span class="material-symbols-outlined text-[16px]" data-icon="business">business</span>
            Consultores Asociados
          </div>
          <div class="flex flex-wrap gap-sm mt-2">
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-secondary-container text-on-secondary-container font-label-caps text-label-caps"
            >
              <span class="material-symbols-outlined text-[14px]" data-icon="school">school</span>
              Ãndice Min: 16
            </span>
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-surface-variant text-on-surface-variant font-label-caps text-label-caps"
            >
              2 vacantes restantes
            </span>
          </div>
        </div>
        <div class="mt-auto pt-md border-t border-outline-variant flex flex-col gap-sm">
          <p class="font-body-sm text-body-sm text-outline">Publicado el 18 de Octubre 2024</p>
          <button
            class="w-full py-2 bg-transparent border-2 border-primary rounded-lg text-primary font-label-caps text-label-caps hover:bg-primary hover:text-on-primary transition-colors focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
          >
            Ver Detalles
          </button>
        </div>
      </article>

      <article
        class="bg-surface rounded-xl border border-outline-variant p-lg flex flex-col gap-md relative overflow-hidden opacity-75"
      >
        <div class="absolute left-0 top-0 bottom-0 w-1 bg-error"></div>
        <div class="flex justify-between items-start">
          <h3 class="font-h3 text-h3 text-on-background">Ingeniero de Datos Trainee</h3>
          <span class="material-symbols-outlined text-outline" data-icon="database">database</span>
        </div>
        <div class="flex flex-col gap-2">
          <div class="flex items-center gap-xs text-on-surface-variant font-body-sm text-body-sm">
            <span class="material-symbols-outlined text-[16px]" data-icon="business">business</span>
            DataCorp
          </div>
          <div class="flex flex-wrap gap-sm mt-2">
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-secondary-container text-on-secondary-container font-label-caps text-label-caps"
            >
              <span class="material-symbols-outlined text-[14px]" data-icon="school">school</span>
              Ãndice Min: 15
            </span>
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-error-container text-on-error-container font-label-caps text-label-caps"
            >
              Proceso Cerrado
            </span>
          </div>
        </div>
        <div class="mt-auto pt-md border-t border-outline-variant flex flex-col gap-sm">
          <p class="font-body-sm text-body-sm text-outline">Publicado el 05 de Octubre 2024</p>
          <button
            class="w-full py-2 bg-surface-container-high border border-outline-variant rounded-lg text-on-surface-variant font-label-caps text-label-caps cursor-not-allowed"
            disabled=""
          >
            No Disponible
          </button>
        </div>
      </article>

      <article
        class="bg-surface rounded-xl border border-outline-variant p-lg flex flex-col gap-md relative overflow-hidden group hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-all duration-300"
      >
        <div class="absolute left-0 top-0 bottom-0 w-1 bg-primary"></div>
        <div class="flex justify-between items-start">
          <h3 class="font-h3 text-h3 text-on-background group-hover:text-primary transition-colors">
            Asistente de Recursos Humanos
          </h3>
          <span class="material-symbols-outlined text-outline" data-icon="group">group</span>
        </div>
        <div class="flex flex-col gap-2">
          <div class="flex items-center gap-xs text-on-surface-variant font-body-sm text-body-sm">
            <span class="material-symbols-outlined text-[16px]" data-icon="business">business</span>
            Grupo Nacional
          </div>
          <div class="flex flex-wrap gap-sm mt-2">
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-secondary-container text-on-secondary-container font-label-caps text-label-caps"
            >
              <span class="material-symbols-outlined text-[14px]" data-icon="school">school</span>
              Ãndice Min: 13
            </span>
            <span
              class="inline-flex items-center gap-1 px-2 py-1 rounded bg-primary-container text-on-primary-container font-label-caps text-label-caps"
            >
              Disponible
            </span>
          </div>
        </div>
        <div class="mt-auto pt-md border-t border-outline-variant flex flex-col gap-sm">
          <p class="font-body-sm text-body-sm text-outline">Publicado el 22 de Octubre 2024</p>
          <button
            class="w-full py-2 bg-transparent border-2 border-primary rounded-lg text-primary font-label-caps text-label-caps hover:bg-primary hover:text-on-primary transition-colors focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2"
          >
            Postularse
          </button>
        </div>
      </article>
    </section>
  </main>

  <footer class="bg-white border-t border-slate-200 mt-auto">
    <div
      class="max-w-[1280px] mx-auto px-12 py-8 flex flex-col md:flex-row justify-between items-center gap-4"
    >
      <div class="font-['Inter'] text-sm tracking-normal text-slate-600">
        Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.
      </div>
      <div class="flex gap-6">
        <a
          class="font-['Inter'] text-sm tracking-normal text-slate-500 hover:text-green-600 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
        >
          Aviso Legal
        </a>
        <a
          class="font-['Inter'] text-sm tracking-normal text-slate-500 hover:text-green-600 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
        >
          Privacidad
        </a>
        <a
          class="font-['Inter'] text-sm tracking-normal text-slate-500 hover:text-green-600 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
        >
          TÃ©rminos y Condiciones
        </a>
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/ofertas-laborales/ofertas-laborales.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertasLaborales } from './ofertas-laborales';

describe('OfertasLaborales', () => {
  let component: OfertasLaborales;
  let fixture: ComponentFixture<OfertasLaborales>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertasLaborales],
    }).compileComponents();

    fixture = TestBed.createComponent(OfertasLaborales);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/ofertas-laborales/ofertas-laborales.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-ofertas-laborales',
  imports: [],
  templateUrl: './ofertas-laborales.html',
  styleUrl: './ofertas-laborales.css',
})
export class OfertasLaborales {}

```

## src/app/pages/pago/pago.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/pago/pago.html

```html
<div
  class="bg-background text-on-background min-h-screen flex flex-col font-body-md text-body-md antialiased"
>
  <main class="flex-grow w-full max-w-[1280px] mx-auto px-gutter py-xl">
    <div class="flex items-center gap-sm text-secondary font-body-sm text-body-sm mb-lg">
      <a class="hover:text-primary transition-colors" href="#">Solicitudes</a>
      <span class="material-symbols-outlined text-[16px]">chevron_right</span>
      <span class="text-on-background font-medium">Pago de Servicio</span>
    </div>

    <div class="mb-lg">
      <h1 class="font-h1 text-h1 text-on-background">Procesar Pago</h1>
      <p class="font-body-lg text-body-lg text-secondary mt-xs">
        Seleccione el mÃ©todo de pago para completar su solicitud de servicio.
      </p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-gutter">
      <div class="lg:col-span-4 flex flex-col gap-md">
        <div
          class="bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-lg sticky top-32 transition-shadow hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
        >
          <h2 class="font-h3 text-h3 text-on-surface mb-md pb-sm border-b border-[#E2E8F0]">
            Resumen de Solicitud
          </h2>
          <div class="flex flex-col gap-md">
            <div>
              <span class="font-label-caps text-label-caps text-secondary block mb-unit"
                >SERVICIO</span
              >
              <span class="font-body-md text-body-md text-on-surface font-medium"
                >AsesorÃ­a Legal Corporativa</span
              >
            </div>
            <div>
              <span class="font-label-caps text-label-caps text-secondary block mb-unit"
                >FECHA</span
              >
              <span class="font-body-md text-body-md text-on-surface">15 de Noviembre, 2024</span>
            </div>
            <div>
              <span class="font-label-caps text-label-caps text-secondary block mb-unit"
                >SOLICITANTE</span
              >
              <span class="font-body-md text-body-md text-on-surface">Empresa XYZ C.A.</span>
            </div>
            <div class="pt-sm mt-sm border-t border-[#E2E8F0]">
              <span class="font-label-caps text-label-caps text-secondary block mb-unit"
                >TOTAL A PAGAR</span
              >
              <span class="font-h2 text-h2 text-primary font-bold">$450.00</span>
            </div>
          </div>
        </div>
      </div>

      <div class="lg:col-span-8 flex flex-col gap-lg">
        <div class="bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-lg">
          <h3 class="font-h3 text-h3 text-on-surface mb-md">MÃ©todos de Pago</h3>
          <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-md">
            <label class="cursor-pointer relative group">
              <input class="peer sr-only" name="payment_method" type="radio" value="crypto" />
              <div
                class="h-full bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-md flex flex-col items-center justify-center gap-sm transition-all peer-checked:border-primary peer-checked:ring-2 peer-checked:ring-primary/20 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
              >
                <span
                  class="material-symbols-outlined text-[32px] text-secondary peer-checked:text-primary transition-colors"
                  data-icon="currency_bitcoin"
                  >currency_bitcoin</span
                >
                <span class="font-body-md text-body-md text-on-surface text-center font-medium"
                  >Criptomonedas</span
                >
              </div>
            </label>
            <label class="cursor-pointer relative group">
              <input class="peer sr-only" name="payment_method" type="radio" value="tai" />
              <div
                class="h-full bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-md flex flex-col items-center justify-center gap-sm transition-all peer-checked:border-primary peer-checked:ring-2 peer-checked:ring-primary/20 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
              >
                <span
                  class="material-symbols-outlined text-[32px] text-secondary peer-checked:text-primary transition-colors"
                  data-icon="account_balance"
                  >account_balance</span
                >
                <span class="font-body-md text-body-md text-on-surface text-center font-medium"
                  >Pago_TAI</span
                >
              </div>
            </label>
            <label class="cursor-pointer relative group">
              <input class="peer sr-only" name="payment_method" type="radio" value="pagomovil" />
              <div
                class="h-full bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-md flex flex-col items-center justify-center gap-sm transition-all peer-checked:border-primary peer-checked:ring-2 peer-checked:ring-primary/20 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
              >
                <span
                  class="material-symbols-outlined text-[32px] text-secondary peer-checked:text-primary transition-colors"
                  data-icon="phone_iphone"
                  >phone_iphone</span
                >
                <span class="font-body-md text-body-md text-on-surface text-center font-medium"
                  >Pago Movil</span
                >
              </div>
            </label>
            <label class="cursor-pointer relative group">
              <input class="peer sr-only" name="payment_method" type="radio" value="zelle" />
              <div
                class="h-full bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-md flex flex-col items-center justify-center gap-sm transition-all peer-checked:border-primary peer-checked:ring-2 peer-checked:ring-primary/20 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
              >
                <span
                  class="material-symbols-outlined text-[32px] text-secondary peer-checked:text-primary transition-colors"
                  data-icon="send_money"
                  >send_money</span
                >
                <span class="font-body-md text-body-md text-on-surface text-center font-medium"
                  >Zelle</span
                >
              </div>
            </label>
            <label class="cursor-pointer relative group">
              <input class="peer sr-only" name="payment_method" type="radio" value="efectivo" />
              <div
                class="h-full bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-md flex flex-col items-center justify-center gap-sm transition-all peer-checked:border-primary peer-checked:ring-2 peer-checked:ring-primary/20 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
              >
                <span
                  class="material-symbols-outlined text-[32px] text-secondary peer-checked:text-primary transition-colors"
                  data-icon="payments"
                  >payments</span
                >
                <span class="font-body-md text-body-md text-on-surface text-center font-medium"
                  >Efectivo</span
                >
              </div>
            </label>
            <label class="cursor-pointer relative group">
              <input
                checked=""
                class="peer sr-only"
                name="payment_method"
                type="radio"
                value="tarjeta"
              />
              <div
                class="h-full bg-surface-container-lowest border border-[#E2E8F0] rounded-DEFAULT p-md flex flex-col items-center justify-center gap-sm transition-all peer-checked:border-primary peer-checked:ring-2 peer-checked:ring-primary/20 hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
              >
                <span
                  class="material-symbols-outlined text-[32px] text-secondary peer-checked:text-primary transition-colors"
                  data-icon="credit_card"
                  >credit_card</span
                >
                <span class="font-body-md text-body-md text-on-surface text-center font-medium"
                  >Tarjeta</span
                >
              </div>
            </label>
          </div>
        </div>

        <div class="flex justify-end gap-md mt-sm">
          <button
            class="bg-surface-container-lowest text-secondary border border-[#E2E8F0] font-body-md text-body-md font-medium py-sm px-lg rounded-DEFAULT hover:bg-surface-container-low transition-colors"
          >
            Cancelar
          </button>
          <button
            class="bg-primary text-white font-body-md text-body-md font-medium py-sm px-lg rounded-DEFAULT hover:bg-primary-container hover:text-on-primary-container transition-colors shadow-[0px_4px_6px_rgba(0,0,0,0.05)]"
          >
            Continuar al Pago
          </button>
        </div>
      </div>
    </div>
  </main>

  <footer
    class="bg-white dark:bg-slate-950 font-['Inter'] text-sm tracking-normal w-full border-t border-slate-200 dark:border-slate-800 flat no-shadows mt-auto"
  >
    <div class="max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center">
      <div class="text-slate-600 dark:text-slate-400">
        Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.
      </div>
      <div class="flex items-center gap-6">
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
          >Aviso Legal</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
          >Privacidad</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
          >TÃ©rminos y Condiciones</a
        >
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/pago/pago.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Pago } from './pago';

describe('Pago', () => {
  let component: Pago;
  let fixture: ComponentFixture<Pago>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Pago],
    }).compileComponents();

    fixture = TestBed.createComponent(Pago);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/pago/pago.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-pago',
  imports: [],
  templateUrl: './pago.html',
  styleUrl: './pago.css',
})
export class Pago {}

```

## src/app/pages/profile/profile.css

```css
.material-symbols-outlined {
  font-variation-settings:
    'FILL' 0,
    'wght' 400,
    'GRAD' 0,
    'opsz' 24;
}
.icon-filled {
  font-variation-settings:
    'FILL' 1,
    'wght' 400,
    'GRAD' 0,
    'opsz' 24;
}
```

## src/app/pages/profile/profile.html

```html
<div class="bg-background text-on-background font-body-md min-h-screen flex flex-col">
  
  <nav class="bg-green-600 dark:bg-green-800 text-white font-['Inter'] antialiased tracking-tight sticky top-0 z-50 border-b border-green-700 dark:border-green-900 shadow-sm w-full">
    <div class="flex justify-between items-center w-full px-8 py-4 max-w-[1728px] mx-auto">
      <div class="text-xl font-black text-white uppercase tracking-wider">UCAB SERVICES</div>
      <div class="hidden md:flex items-center gap-8">
        <a class="text-green-100/80 hover:text-white transition-all duration-200" href="#">Dashboard</a>
        <a class="text-green-100/80 hover:text-white transition-all duration-200" href="#">Servicios</a>
        <a class="text-green-100/80 hover:text-white transition-all duration-200" href="#">Solicitudes</a>
        <a class="text-white border-b-2 border-white pb-1 font-bold" href="#">Perfil</a>
      </div>
      <div class="flex items-center gap-4">
        <button class="hover:bg-green-700/50 dark:hover:bg-green-700/30 rounded-sm p-2 transition-opacity opacity-90 hover:opacity-100">
          <span class="material-symbols-outlined">notifications</span>
        </button>
        <button class="hover:bg-green-700/50 dark:hover:bg-green-700/30 rounded-sm p-2 transition-opacity opacity-90 hover:opacity-100">
          <span class="material-symbols-outlined">settings</span>
        </button>
      </div>
    </div>
  </nav>

  <main class="flex-grow w-full max-w-container-max mx-auto px-gutter py-xl">
    <header class="mb-lg">
      <h1 class="font-h1 text-h1 text-on-background">Mi Perfil</h1>
      <p class="font-body-lg text-body-lg text-secondary mt-xs">Gestione su informaciÃ³n personal y acadÃ©mica.</p>
    </header>

    <div class="grid grid-cols-1 md:grid-cols-12 gap-lg">
      
      <div class="md:col-span-4 flex flex-col gap-lg">
        <div class="bg-surface-container-lowest border border-[#E2E8F0] rounded-xl p-lg hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300 flex flex-col items-center text-center">
          <div class="w-32 h-32 rounded-full overflow-hidden mb-md border-4 border-surface">
            <img alt="Profile Picture" class="w-full h-full object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBnHfTJ67UtAx42JwbTpryOgi7EPswaGOA7GP59ec5aJlA0vejh0uQWb1ZETQ0jBSE6oMvblcUxXB9_yqKxLoVhsdiUqPvIptSrmVuOUv4IvdJOTnfw6aQmagisJp3Gw5mbcBFT2RnvxCERE73Kn9rXzdhYQpdFKxHERm3jwkOY2gqoALXH79KhhtWZNHeMDULL7xYDgevGfoCYCpL5wxLwNWE8ePxQz9ANTDWgHWlyQBR4MxI9LQSCZptLBkST42lIrFNyWM4hZJHr" />
          </div>
          <h2 class="font-h3 text-h3 text-on-surface mb-xs">Ana MartÃ­nez Silva</h2>
          <span class="inline-block bg-primary-container text-on-primary-container font-label-caps text-label-caps px-sm py-1 rounded-full mb-md">Estudiante</span>
          
          <div class="w-full border-t border-[#E2E8F0] pt-md flex flex-col gap-sm text-left">
            <div class="flex items-center gap-sm text-secondary">
              <span class="material-symbols-outlined text-[20px]">badge</span>
              <span class="font-body-sm text-body-sm">ID: 202104589</span>
            </div>
            <div class="flex items-center gap-sm text-secondary">
              <span class="material-symbols-outlined text-[20px]">calendar_today</span>
              <span class="font-body-sm text-body-sm">Miembro desde: Sept 2021</span>
            </div>
          </div>
          
          <button class="mt-lg w-full bg-primary text-on-primary font-body-md text-body-md py-sm px-md rounded-DEFAULT hover:opacity-90 transition-opacity">
            Editar Perfil
          </button>
          
          <div class="w-full border-t border-[#E2E8F0] mt-lg pt-md flex flex-col gap-sm text-left">
            <div class="flex items-center gap-sm text-secondary">
              <span class="material-symbols-outlined text-[20px]">account_balance_wallet</span>
              <span class="font-body-sm text-body-sm">Saldo Tarjeta TAI: Bs. 1.250,00</span>
            </div>
            <div class="flex items-center gap-sm text-secondary">
              <span class="material-symbols-outlined text-[20px]">nfc</span>
              <span class="font-body-sm text-body-sm">UID Tarjeta TAI: 80:E1:92:BC</span>
            </div>
          </div>
        </div>
      </div>

      <div class="md:col-span-8 flex flex-col gap-lg">
        <section class="bg-surface-container-lowest border border-[#E2E8F0] rounded-xl p-lg hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300">
          <div class="flex items-center gap-sm mb-md border-b border-[#E2E8F0] pb-sm">
            <span class="material-symbols-outlined text-primary icon-filled">school</span>
            <h3 class="font-h3 text-h3 text-on-surface">InformaciÃ³n AcadÃ©mica</h3>
          </div>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-md">
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">ESCUELA</p>
              <p class="font-body-md text-body-md text-on-surface">IngenierÃ­a InformÃ¡tica</p>
            </div>
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">SEMESTRE ACTUAL</p>
              <p class="font-body-md text-body-md text-on-surface">6to Semestre</p>
            </div>
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">SEDE</p>
              <p class="font-body-md text-body-md text-on-surface">Caracas - MontalbÃ¡n</p>
            </div>
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">ESTADO ACADÃ‰MICO</p>
              <span class="inline-block bg-inverse-primary/20 text-primary font-label-caps text-label-caps px-sm py-1 rounded-full">Activo Regular</span>
            </div>
          </div>
        </section>

        <section class="bg-surface-container-lowest border border-[#E2E8F0] rounded-xl p-lg hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300">
          <div class="flex items-center gap-sm mb-md border-b border-[#E2E8F0] pb-sm">
            <span class="material-symbols-outlined text-primary icon-filled">contact_mail</span>
            <h3 class="font-h3 text-h3 text-on-surface">InformaciÃ³n de Contacto</h3>
          </div>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-md">
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">CORREO INSTITUCIONAL</p>
              <p class="font-body-md text-body-md text-on-surface">amartinez589@est.ucab.edu.ve</p>
            </div>
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">CORREO PERSONAL</p>
              <p class="font-body-md text-body-md text-on-surface">ana.martinez@email.com</p>
            </div>
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">TELÃ‰FONO MÃ“VIL</p>
              <p class="font-body-md text-body-md text-on-surface">+58 412 123 4567</p>
            </div>
            <div>
              <p class="font-label-caps text-label-caps text-secondary mb-xs">DIRECCIÃ“N PRINCIPAL</p>
              <p class="font-body-md text-body-md text-on-surface">Av. TeherÃ¡n, Residencia El Parque, Apto 4B, Caracas.</p>
            </div>
          </div>
        </section>

        <section class="bg-surface-container-lowest border border-[#E2E8F0] rounded-xl overflow-hidden hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300">
          <div class="p-lg border-b border-[#E2E8F0] flex justify-between items-center bg-surface-bright">
            <div class="flex items-center gap-sm">
              <span class="material-symbols-outlined text-primary icon-filled">family_restroom</span>
              <h3 class="font-h3 text-h3 text-on-surface">Mis VÃ­nculos Familiares</h3>
            </div>
            <button class="bg-primary text-on-primary font-body-sm text-body-sm py-xs px-sm md:py-sm md:px-md rounded-DEFAULT hover:opacity-90 transition-opacity">
              + AÃ±adir VÃ­nculo
            </button>
          </div>
          <div class="w-full overflow-x-auto">
            <table class="w-full text-left font-body-sm text-body-sm">
              <thead class="bg-[#F8FAFC] text-secondary font-label-caps text-label-caps border-b border-[#E2E8F0]">
                <tr>
                  <th class="py-md px-lg">NOMBRE</th>
                  <th class="py-md px-lg">PARENTESCO</th>
                  <th class="py-md px-lg">FECHA NACIMIENTO</th>
                  <th class="py-md px-lg">COBERTURA</th>
                </tr>
              </thead>
              <tbody>
                <tr class="border-b border-[#E2E8F0] hover:bg-surface-container-low transition-colors">
                  <td class="py-md px-lg text-on-surface font-medium">Juan PÃ©rez</td>
                  <td class="py-md px-lg text-on-surface">Hijo</td>
                  <td class="py-md px-lg text-secondary">15 May 2010</td>
                  <td class="py-md px-lg"><span class="inline-block bg-primary-container/30 text-primary-fixed-dim font-label-caps text-label-caps px-sm py-xs rounded-full">Activa</span></td>
                </tr>
                <tr class="hover:bg-surface-container-low transition-colors">
                  <td class="py-md px-lg text-on-surface font-medium">MarÃ­a GarcÃ­a</td>
                  <td class="py-md px-lg text-on-surface">CÃ³nyuge</td>
                  <td class="py-md px-lg text-secondary">22 Ago 1985</td>
                  <td class="py-md px-lg"><span class="inline-block bg-primary-container/30 text-primary-fixed-dim font-label-caps text-label-caps px-sm py-xs rounded-full">Activa</span></td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>

        <section class="bg-surface-container-lowest border border-[#E2E8F0] rounded-xl overflow-hidden hover:shadow-[0px_4px_6px_rgba(0,0,0,0.05)] transition-shadow duration-300">
          <div class="p-lg border-b border-[#E2E8F0] flex justify-between items-center bg-surface-bright">
            <div class="flex items-center gap-sm">
              <span class="material-symbols-outlined text-primary icon-filled">receipt_long</span>
              <h3 class="font-h3 text-h3 text-on-surface">Solicitudes Recientes</h3>
            </div>
            <a class="font-body-sm text-body-sm text-primary hover:underline" href="#">Ver todas</a>
          </div>
          <div class="w-full overflow-x-auto">
            <table class="w-full text-left font-body-sm text-body-sm">
              <thead class="bg-[#F8FAFC] text-secondary font-label-caps text-label-caps border-b border-[#E2E8F0]">
                <tr>
                  <th class="py-md px-lg">ID SOLICITUD</th>
                  <th class="py-md px-lg">TIPO</th>
                  <th class="py-md px-lg">FECHA</th>
                  <th class="py-md px-lg">ESTADO</th>
                </tr>
              </thead>
              <tbody>
                <tr class="border-b border-[#E2E8F0] hover:bg-surface-container-low transition-colors">
                  <td class="py-md px-lg text-on-surface">REQ-0042</td>
                  <td class="py-md px-lg text-on-surface">Constancia de Estudios</td>
                  <td class="py-md px-lg text-secondary">12 Oct 2024</td>
                  <td class="py-md px-lg"><span class="inline-block bg-primary-container/30 text-primary-fixed-dim font-label-caps text-label-caps px-sm py-xs rounded-full">Aprobado</span></td>
                </tr>
                <tr class="hover:bg-surface-container-low transition-colors">
                  <td class="py-md px-lg text-on-surface">REQ-0038</td>
                  <td class="py-md px-lg text-on-surface">RevisiÃ³n de Calificaciones</td>
                  <td class="py-md px-lg text-secondary">05 Sep 2024</td>
                  <td class="py-md px-lg"><span class="inline-block bg-surface-variant text-on-surface-variant font-label-caps text-label-caps px-sm py-xs rounded-full">Cerrado</span></td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  </main>

  <footer class="bg-white dark:bg-slate-950 text-slate-600 dark:text-slate-400 font-['Inter'] text-sm tracking-normal w-full border-t border-slate-200 dark:border-slate-800 mt-auto">
    <div class="max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center">
      <div class="flex items-center gap-md">
        <span class="text-lg font-bold text-green-600 dark:text-green-400">UCAB SERVICES</span>
        <span class="text-slate-500">Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.</span>
      </div>
      <div class="flex gap-lg">
        <a class="text-slate-500 hover:text-green-600 hover:underline transition-all" href="#">Aviso Legal</a>
        <a class="text-slate-500 hover:text-green-600 hover:underline transition-all" href="#">Privacidad</a>
        <a class="text-slate-500 hover:text-green-600 hover:underline transition-all" href="#">TÃ©rminos y Condiciones</a>
      </div>
    </div>
  </footer>
</div>
```

## src/app/pages/profile/profile.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Profile } from './profile';

describe('Profile', () => {
  let component: Profile;
  let fixture: ComponentFixture<Profile>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Profile],
    }).compileComponents();

    fixture = TestBed.createComponent(Profile);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/profile/profile.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile {}

```

## src/app/pages/servicio-actividad/servicio-actividad.css

```css
:host {
  display: block;
}

.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/servicio-actividad/servicio-actividad.html

```html
<div class="min-h-screen flex flex-col font-body-md text-body-md text-on-background">
  <main class="flex-grow w-full max-w-container-max mx-auto px-gutter py-xl flex flex-col gap-lg">
    <div class="flex justify-between items-center w-full max-w-[800px] mx-auto">
      <a
        class="inline-flex items-center gap-xs text-primary hover:text-surface-tint transition-colors font-body-sm text-body-sm group"
        href="#"
      >
        <span
          class="material-symbols-outlined text-[18px] group-hover:-translate-x-1 transition-transform"
          >arrow_back</span
        >
        <span>Volver al Dashboard</span>
      </a>
      <a
        class="inline-flex items-center gap-xs px-md py-sm bg-primary text-on-primary rounded-lg font-body-sm text-body-sm hover:bg-surface-tint transition-colors shadow-sm ml-auto"
        href="#"
      >
        <span class="material-symbols-outlined text-[18px]">receipt_long</span>
        <span>Ver Folio de Consumo</span>
      </a>
    </div>

    <div
      class="w-full max-w-[800px] mx-auto bg-surface-container-lowest border border-outline-variant rounded-xl shadow-sm overflow-hidden flex flex-col"
    >
      <div
        class="h-[200px] w-full bg-surface-variant relative overflow-hidden"
        data-alt="Auditorio UCAB"
        style="
          background-image: url('https://lh3.googleusercontent.com/aida-public/AB6AXuCZO3jrZhbgIerSpDgMCAuQ32UTSfGjo4ns6_KsRHXSOZmm0E2c5cIj_k4AwbLcblpg4b9cFiUg6KnsUChh9YXyWChbweyVcnJSJkJWFjQ9mfGPe1bcRxTYtStnzPBYP5IUftAN23X6K5-dZC1RNMCGCHhxc_6bPsLvNKfdy0LOqSw0YyL6r4l0DJOAM-0dIv1Vi_kXisEZ8Qr0EWxoW32BHgrlN75TfU0lTYp0VHe1FLiKVvtyRneMxSzh37LIOuSwM9gZnCAu6i1e');
          background-size: cover;
          background-position: center;
        "
      >
        <div
          class="absolute inset-0 bg-gradient-to-t from-on-secondary-fixed/40 to-transparent"
        ></div>
      </div>
      <div class="p-xl flex flex-col gap-lg">
        <div>
          <span
            class="inline-block px-2 py-1 bg-surface-container-highest text-on-surface-variant rounded font-label-caps text-label-caps mb-sm uppercase tracking-wider"
            >Detalles del Evento</span
          >
          <h1 class="font-h1 text-h1 text-on-background">
            Simposio de InnovaciÃ³n y TecnologÃ­a Educativa 2024
          </h1>
        </div>
        <hr class="border-outline-variant" />

        <div class="grid grid-cols-1 md:grid-cols-2 gap-lg">
          <div class="flex items-start gap-md">
            <div
              class="mt-1 w-10 h-10 rounded-full bg-surface-container flex items-center justify-center text-primary shrink-0"
            >
              <span class="material-symbols-outlined">calendar_today</span>
            </div>
            <div class="flex flex-col gap-xs">
              <h3 class="font-label-caps text-label-caps text-on-surface-variant uppercase">
                Fecha
              </h3>
              <p class="font-body-lg text-body-lg text-on-background">
                Viernes, 24 de Mayo de 2024
              </p>
              <p class="font-body-md text-body-md text-on-surface-variant">08:30 AM - 01:00 PM</p>
            </div>
          </div>
          <div class="flex items-start gap-md">
            <div
              class="mt-1 w-10 h-10 rounded-full bg-surface-container flex items-center justify-center text-primary shrink-0"
            >
              <span class="material-symbols-outlined">location_on</span>
            </div>
            <div class="flex flex-col gap-xs">
              <h3 class="font-label-caps text-label-caps text-on-surface-variant uppercase">
                EdificaciÃ³n
              </h3>
              <p class="font-body-lg text-body-lg text-on-background">Aula Magna</p>
              <p class="font-body-md text-body-md text-on-surface-variant">
                Edificio Cincuentenario, Planta Baja
              </p>
            </div>
          </div>
        </div>

        <div
          class="mt-md p-lg bg-surface-container-low border border-outline-variant rounded-lg flex flex-col gap-sm"
        >
          <div class="flex items-center gap-xs text-on-surface-variant">
            <span class="material-symbols-outlined text-[18px]">info</span>
            <h3 class="font-label-caps text-label-caps uppercase">Observaciones</h3>
          </div>
          <p class="font-body-md text-body-md text-on-background leading-relaxed">
            Se requiere presentar el carnet estudiantil vigente o la cÃ©dula de identidad en la
            puerta principal para el registro de asistencia. Se recomienda llegar con 15 minutos de
            anticipaciÃ³n. El cÃ³digo de vestimenta es casual de negocios. HabrÃ¡ un receso programado
            a las 10:30 AM con servicio de cafeterÃ­a disponible en los alrededores del auditorio.
          </p>
        </div>

        <div class="flex flex-col gap-md mt-md">
          <div class="flex items-center gap-xs text-on-surface-variant">
            <span class="material-symbols-outlined text-[18px]">group</span>
            <h3 class="font-label-caps text-label-caps uppercase">AcompaÃ±antes Temporales</h3>
          </div>
          <div
            class="overflow-hidden border border-outline-variant rounded-lg bg-surface-container-lowest"
          >
            <table class="w-full text-left border-collapse">
              <thead
                class="bg-surface-container-low text-on-surface-variant font-label-caps text-[11px] uppercase"
              >
                <tr>
                  <th class="px-lg py-sm border-b border-outline-variant">Nombre Completo</th>
                  <th class="px-lg py-sm border-b border-outline-variant">IdentificaciÃ³n</th>
                </tr>
              </thead>
              <tbody class="font-body-sm text-body-sm">
                <tr
                  class="hover:bg-surface-container-low/50 transition-colors border-b border-outline-variant last:border-0"
                >
                  <td class="px-lg py-md text-on-background">Ana Victoria MÃ©ndez</td>
                  <td class="px-lg py-md text-on-surface-variant">V-28.456.789</td>
                </tr>
                <tr
                  class="hover:bg-surface-container-low/50 transition-colors border-b border-outline-variant last:border-0"
                >
                  <td class="px-lg py-md text-on-background">Carlos Eduardo Rojas</td>
                  <td class="px-lg py-md text-on-surface-variant">V-25.123.456</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </main>

  <footer
    class="bg-white dark:bg-slate-950 font-['Inter'] text-sm tracking-normal w-full border-t border-slate-200 dark:border-slate-800 border-t border-slate-100 dark:border-slate-900 flat no-shadows mt-auto"
  >
    <div class="max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center">
      <div class="flex items-center gap-md">
        <span class="text-lg font-bold text-green-600 dark:text-green-400">UCAB SERVICES</span>
        <span class="text-slate-600 dark:text-slate-400"
          >Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.</span
        >
      </div>
      <div class="flex gap-lg">
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >Aviso Legal</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >Privacidad</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none text-green-700"
          href="#"
          >TÃ©rminos y Condiciones</a
        >
      </div>
    </div>
  </footer>
</div>

```

## src/app/pages/servicio-actividad/servicio-actividad.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicioActividad } from './servicio-actividad';

describe('ServicioActividad', () => {
  let component: ServicioActividad;
  let fixture: ComponentFixture<ServicioActividad>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicioActividad],
    }).compileComponents();

    fixture = TestBed.createComponent(ServicioActividad);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/servicio-actividad/servicio-actividad.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-servicio-actividad',
  imports: [],
  templateUrl: './servicio-actividad.html',
  styleUrl: './servicio-actividad.css',
})
export class ServicioActividad {}

```

## src/app/pages/solicitar-servicio/solicitar-servicio.css

```css
:host {
  display: block;
}

/* Configuraciones para Ã­conos u otras clases base si tuvieras personalizaciones */
.material-symbols-outlined {
  font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
}
```

## src/app/pages/solicitar-servicio/solicitar-servicio.html

```html
<div
  class="bg-background text-on-background min-h-screen flex flex-col font-body-md text-body-md antialiased"
>
  <main class="flex-grow w-full max-w-container-max mx-auto px-gutter py-12 flex flex-col gap-8">
    <div class="flex flex-col gap-xs">
      <nav
        aria-label="Breadcrumb"
        class="flex items-center text-secondary font-body-sm text-body-sm mb-xs"
      >
        <a class="hover:text-primary transition-colors" href="#">Servicios</a>
        <span class="material-symbols-outlined text-[16px] mx-xs">chevron_right</span>
        <span aria-current="page" class="text-on-surface">Solicitar un Servicio</span>
      </nav>
      <h1 class="font-h1 text-h1 text-on-surface">Solicitar un Servicio</h1>
      <p class="font-body-lg text-body-lg text-on-secondary-container max-w-2xl mt-sm">
        Seleccione el espacio, fecha y horario requerido para su actividad. Verifique la
        disponibilidad en tiempo real.
      </p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-gutter items-start">
      <div class="lg:col-span-8 flex flex-col gap-lg">
        <section
          class="bg-surface-container-lowest rounded-lg border border-outline-variant p-lg shadow-[0_4px_6px_rgba(0,0,0,0.05)] flex flex-col gap-md"
        >
          <header class="flex items-center gap-sm">
            <div
              class="w-8 h-8 rounded-full bg-primary-container text-on-primary-container flex items-center justify-center font-label-caps text-label-caps"
            >
              1
            </div>
            <h2 class="font-h3 text-h3 text-on-surface">SelecciÃ³n de Servicio</h2>
          </header>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-md">
            <div
              class="relative group border-2 border-primary rounded-lg overflow-hidden cursor-pointer bg-surface-container-low transition-colors"
            >
              <div
                class="absolute top-2 right-2 bg-primary text-on-primary rounded-full p-1 z-10 shadow-sm"
              >
                <span class="material-symbols-outlined text-[16px] block">check</span>
              </div>
              <div
                class="h-32 bg-surface-variant w-full"
                data-alt="Auditorio de la universidad"
                style="
                  background-image: url('https://lh3.googleusercontent.com/aida-public/AB6AXuBXlaM3NxuosN74-BskWl54LYvMhoFazWgo3xcrkiVFysyc3shXpbABzFpsRQWSdodZpNifluYWE_1__sHOdfravWQFRpIXXElf-aTF6M6Id_t5YuWTr2Ksf6S9Z0H6ei4T530JeZPFsoDcYVim1PltLyGPYqdRYuBO7SuYsnivy_XUd_vDBq98j-NirervXVC9ljjLvPoU1tk616hMHHM7RNsJ_krlxfeApuKbsIZ_z1GPbr2tCuMXe5p9Y1Bd18vqG6_oYnHoCsN-');
                  background-size: cover;
                  background-position: center;
                "
              ></div>
              <div class="p-md bg-surface-container-lowest">
                <h3 class="font-body-lg text-body-lg text-on-surface font-semibold mb-xs">
                  Uso de Auditorio
                </h3>
                <p class="font-body-sm text-body-sm text-secondary mb-sm">
                  Auditorio Hermano Lanz, Edificio Cincuentenario
                </p>
                <div class="flex gap-2">
                  <span
                    class="inline-flex items-center gap-1 px-2 py-1 rounded bg-secondary-container text-on-secondary-container font-label-caps text-label-caps"
                  >
                    <span class="material-symbols-outlined text-[14px]">groups</span> 250
                  </span>
                  <span
                    class="inline-flex items-center gap-1 px-2 py-1 rounded bg-secondary-container text-on-secondary-container font-label-caps text-label-caps"
                  >
                    <span class="material-symbols-outlined text-[14px]">wifi</span> SÃ­
                  </span>
                </div>
              </div>
            </div>

            <div
              class="relative group border border-outline-variant hover:border-outline hover:shadow-[0_4px_6px_rgba(0,0,0,0.05)] rounded-lg overflow-hidden cursor-pointer bg-surface-container-lowest transition-all"
            >
              <div
                class="h-32 bg-surface-variant w-full"
                data-alt="Laboratorio de computaciÃ³n"
                style="
                  background-image: url('https://lh3.googleusercontent.com/aida-public/AB6AXuCpp4fW-jxiq4GyQtWVxLoC8e-CPaEsDe_zHlWluwJLFvKJqNr_EmMWleaqHMzmWljYcEje1l5poJzF0-76Tw4t1pyqKDI8DLvsbCUUIpSF0EXiGzfgrAYqbfoq03BuOlkGn6RY--TvwheeO4M1uD5KTEGX25pJjgchhpaIy5HC26vwNB1WaMjVUzzD77jYZVDrTu1JCL7PTIazNpJotH6z45nV-VuPQN3wzcHJSLV0yo6Lknapc7ctiOQIXjmaclGYlc8D7QvcfZbc');
                  background-size: cover;
                  background-position: center;
                "
              ></div>
              <div class="p-md">
                <h3 class="font-body-lg text-body-lg text-on-surface font-semibold mb-xs">
                  Soporte TÃ©cnico en Laboratorio
                </h3>
                <p class="font-body-sm text-body-sm text-secondary mb-sm">
                  Laboratorio M-402, Edificio Aulas
                </p>
                <div class="flex gap-2">
                  <span
                    class="inline-flex items-center gap-1 px-2 py-1 rounded bg-surface-container text-secondary font-label-caps text-label-caps"
                  >
                    <span class="material-symbols-outlined text-[14px]">groups</span> 30
                  </span>
                  <span
                    class="inline-flex items-center gap-1 px-2 py-1 rounded bg-surface-container text-secondary font-label-caps text-label-caps"
                  >
                    <span class="material-symbols-outlined text-[14px]">computer</span> SÃ­
                  </span>
                </div>
              </div>
            </div>
          </div>
        </section>

        <section
          class="bg-surface-container-lowest rounded-lg border border-outline-variant p-lg shadow-[0_4px_6px_rgba(0,0,0,0.05)] flex flex-col gap-md"
        >
          <header class="flex items-center gap-sm">
            <div
              class="w-8 h-8 rounded-full bg-surface-variant text-on-surface flex items-center justify-center font-label-caps text-label-caps"
            >
              2
            </div>
            <h2 class="font-h3 text-h3 text-on-surface">Fecha y Horario</h2>
          </header>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-lg">
            <div class="border border-outline-variant rounded p-md bg-surface">
              <div class="flex justify-between items-center mb-md">
                <button class="text-secondary hover:text-primary">
                  <span class="material-symbols-outlined">chevron_left</span>
                </button>
                <span class="font-body-md text-body-md font-semibold text-on-surface"
                  >Octubre 2024</span
                >
                <button class="text-secondary hover:text-primary">
                  <span class="material-symbols-outlined">chevron_right</span>
                </button>
              </div>
              <div
                class="grid grid-cols-7 gap-1 text-center font-label-caps text-label-caps text-secondary mb-2"
              >
                <div>Lu</div>
                <div>Ma</div>
                <div>Mi</div>
                <div>Ju</div>
                <div>Vi</div>
                <div>Sa</div>
                <div>Do</div>
              </div>
              <div class="grid grid-cols-7 gap-1 text-center font-body-sm text-body-sm">
                <div class="p-1 text-outline-variant">30</div>
                <div class="p-1 text-outline-variant">1</div>
                <div class="p-1">2</div>
                <div class="p-1">3</div>
                <div class="p-1">4</div>
                <div class="p-1">5</div>
                <div class="p-1">6</div>
                <div class="p-1">7</div>
                <div class="p-1">8</div>
                <div class="p-1">9</div>
                <div class="p-1">10</div>
                <div class="p-1">11</div>
                <div class="p-1">12</div>
                <div class="p-1">13</div>
                <div class="p-1">14</div>
                <div
                  class="p-1 bg-primary text-on-primary rounded font-semibold shadow-sm cursor-pointer"
                >
                  15
                </div>
                <div class="p-1">16</div>
                <div class="p-1">17</div>
                <div class="p-1">18</div>
                <div class="p-1">19</div>
                <div class="p-1">20</div>
              </div>
            </div>

            <div class="flex flex-col gap-md">
              <div class="flex flex-col gap-xs">
                <label class="font-label-caps text-label-caps text-secondary" for="start-time"
                  >Hora de Inicio</label
                >
                <div class="relative">
                  <span
                    class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-secondary text-[20px]"
                    >schedule</span
                  >
                  <select
                    class="w-full pl-10 pr-4 py-2 bg-surface-container-lowest border border-outline-variant rounded font-body-md text-body-md text-on-surface focus:border-primary focus:ring-2 focus:ring-primary-container focus:outline-none transition-all appearance-none cursor-pointer"
                    id="start-time"
                  >
                    <option>08:00 AM</option>
                    <option selected="">09:00 AM</option>
                    <option>10:00 AM</option>
                  </select>
                  <span
                    class="material-symbols-outlined absolute right-3 top-1/2 -translate-y-1/2 text-secondary text-[20px] pointer-events-none"
                    >expand_more</span
                  >
                </div>
              </div>
              <div class="flex flex-col gap-xs">
                <label class="font-label-caps text-label-caps text-secondary" for="end-time"
                  >Hora de FinalizaciÃ³n</label
                >
                <div class="relative">
                  <span
                    class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-secondary text-[20px]"
                    >schedule</span
                  >
                  <select
                    class="w-full pl-10 pr-4 py-2 bg-surface-container-lowest border border-outline-variant rounded font-body-md text-body-md text-on-surface focus:border-primary focus:ring-2 focus:ring-primary-container focus:outline-none transition-all appearance-none cursor-pointer"
                    id="end-time"
                  >
                    <option>10:00 AM</option>
                    <option>11:00 AM</option>
                    <option selected="">12:00 PM</option>
                  </select>
                  <span
                    class="material-symbols-outlined absolute right-3 top-1/2 -translate-y-1/2 text-secondary text-[20px] pointer-events-none"
                    >expand_more</span
                  >
                </div>
              </div>
              <div
                class="mt-auto bg-surface-container-low p-sm rounded border border-outline-variant flex items-start gap-2"
              >
                <span class="material-symbols-outlined text-primary text-[20px] shrink-0 mt-0.5"
                  >info</span
                >
                <p class="font-body-sm text-body-sm text-on-surface-variant">
                  El espacio seleccionado permite reservas de hasta 4 horas continuas.
                </p>
              </div>
            </div>
          </div>
        </section>
      </div>

      <div class="lg:col-span-4 lg:sticky lg:top-24 flex flex-col gap-md">
        <div
          class="bg-surface-container-lowest rounded-lg border border-outline-variant p-lg shadow-[0_4px_6px_rgba(0,0,0,0.05)] flex flex-col gap-md"
        >
          <h2 class="font-h3 text-h3 text-on-surface border-b border-surface-variant pb-sm">
            Resumen de Solicitud
          </h2>
          <div class="flex items-center gap-sm">
            <div
              class="w-12 h-12 rounded bg-surface-variant flex items-center justify-center shrink-0"
            >
              <span class="material-symbols-outlined text-primary">domain</span>
            </div>
            <div>
              <h4 class="font-body-md text-body-md font-semibold text-on-surface">
                Auditorio Hermano Lanz
              </h4>
              <p class="font-body-sm text-body-sm text-secondary">Edificio Cincuentenario</p>
            </div>
          </div>
          <hr class="border-surface-variant" />
          <ul class="flex flex-col gap-sm">
            <li class="flex items-start gap-3">
              <span class="material-symbols-outlined text-secondary text-[20px] shrink-0"
                >event</span
              >
              <div>
                <span class="block font-label-caps text-label-caps text-secondary">FECHA</span>
                <span class="block font-body-sm text-body-sm text-on-surface"
                  >Martes, 15 de Octubre 2024</span
                >
              </div>
            </li>
            <li class="flex items-start gap-3">
              <span class="material-symbols-outlined text-secondary text-[20px] shrink-0"
                >schedule</span
              >
              <div>
                <span class="block font-label-caps text-label-caps text-secondary">HORARIO</span>
                <span class="block font-body-sm text-body-sm text-on-surface"
                  >09:00 AM - 12:00 PM (3 hrs)</span
                >
              </div>
            </li>
            <li class="flex items-start gap-3">
              <span class="material-symbols-outlined text-secondary text-[20px] shrink-0"
                >group</span
              >
              <div>
                <span class="block font-label-caps text-label-caps text-secondary">CAPACIDAD</span>
                <span class="block font-body-sm text-body-sm text-on-surface"
                  >MÃ¡x. 250 personas</span
                >
              </div>
            </li>
          </ul>
          <hr class="border-surface-variant" />
          <div>
            <span class="block font-label-caps text-label-caps text-secondary mb-2"
              >RECURSOS INCLUIDOS</span
            >
            <div class="flex flex-wrap gap-2">
              <span
                class="px-2 py-1 bg-surface border border-outline-variant rounded-full font-body-sm text-body-sm text-secondary flex items-center gap-1"
              >
                <span class="material-symbols-outlined text-[16px]">videocam</span> Proyector
              </span>
              <span
                class="px-2 py-1 bg-surface border border-outline-variant rounded-full font-body-sm text-body-sm text-secondary flex items-center gap-1"
              >
                <span class="material-symbols-outlined text-[16px]">mic</span> Audio
              </span>
              <span
                class="px-2 py-1 bg-surface border border-outline-variant rounded-full font-body-sm text-body-sm text-secondary flex items-center gap-1"
              >
                <span class="material-symbols-outlined text-[16px]">ac_unit</span> A/A
              </span>
            </div>
          </div>
          <button
            class="w-full mt-sm py-3 px-4 bg-primary text-on-primary font-body-md text-body-md font-semibold rounded hover:bg-surface-tint hover:shadow-[0_4px_6px_rgba(0,0,0,0.05)] transition-all flex items-center justify-center gap-2"
          >
            <span>Confirmar Solicitud</span>
            <span class="material-symbols-outlined text-[20px]">check_circle</span>
          </button>
          <p class="text-center font-label-caps text-label-caps text-secondary mt-1">
            Sujeto a aprobaciÃ³n administrativa
          </p>
        </div>
      </div>
    </div>
  </main>

  <footer
    class="bg-white dark:bg-slate-950 font-['Inter'] text-sm tracking-normal w-full border-t border-slate-200 dark:border-slate-800 flat no-shadows text-slate-600 dark:text-slate-400 mt-auto"
  >
    <div class="max-w-[1280px] mx-auto px-12 py-8 flex justify-between items-center">
      <span class="text-lg font-bold text-green-600 dark:text-green-400">UCAB SERVICES</span>
      <div class="flex gap-6">
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
          >Aviso Legal</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
          >Privacidad</a
        >
        <a
          class="text-slate-500 dark:text-slate-500 hover:text-green-600 dark:hover:text-green-400 hover:underline transition-all focus:outline-none focus:text-green-700"
          href="#"
          >TÃ©rminos y Condiciones</a
        >
      </div>
      <span>Â© 2024 UCAB SERVICES. Universidad CatÃ³lica AndrÃ©s Bello.</span>
    </div>
  </footer>
</div>

```

## src/app/pages/solicitar-servicio/solicitar-servicio.spec.ts

```ts
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitarServicio } from './solicitar-servicio';

describe('SolicitarServicio', () => {
  let component: SolicitarServicio;
  let fixture: ComponentFixture<SolicitarServicio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitarServicio],
    }).compileComponents();

    fixture = TestBed.createComponent(SolicitarServicio);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

```

## src/app/pages/solicitar-servicio/solicitar-servicio.ts

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-solicitar-servicio',
  imports: [],
  templateUrl: './solicitar-servicio.html',
  styleUrl: './solicitar-servicio.css',
})
export class SolicitarServicio {}

```

## src/index.html

```html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>UcabServicesUi</title>
    <base href="/" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="icon" type="image/x-icon" href="favicon.ico" />
    
    <!-- Fuentes y Material Symbols -->
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;900&display=swap" rel="stylesheet" />
    
    <!-- Tailwind CSS (CDN) -->
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
    <script>
      tailwind.config = {
        darkMode: 'class',
        theme: {
          extend: {
            colors: {
              'on-tertiary-fixed': '#410001',
              'tertiary-fixed': '#ffdad5',
              'on-primary': '#ffffff',
              'primary-fixed-dim': '#4ae176',
              'surface-container-high': '#dce9ff',
              tertiary: '#9e4036',
              'surface-dim': '#cbdbf5',
              'on-tertiary-fixed-variant': '#7f2a21',
              error: '#ba1a1a',
              secondary: '#565e74',
              'on-secondary-fixed': '#131b2e',
              'on-secondary-container': '#5c647a',
              background: '#f8f9ff',
              outline: '#6d7b6c',
              'on-primary-fixed-variant': '#005321',
              'surface-container': '#e5eeff',
              'error-container': '#ffdad6',
              'on-secondary-fixed-variant': '#3f465c',
              'on-primary-container': '#004b1e',
              primary: '#006e2f',
              'on-error': '#ffffff',
              'on-surface': '#0b1c30',
              'surface-variant': '#d3e4fe',
              'inverse-on-surface': '#eaf1ff',
              'inverse-surface': '#213145',
              'surface-container-lowest': '#ffffff',
              'on-surface-variant': '#3d4a3d',
              'on-secondary': '#ffffff',
              'on-tertiary': '#ffffff',
              'tertiary-container': '#ff8b7c',
              'secondary-container': '#dae2fd',
              'outline-variant': '#bccbb9',
              'surface-tint': '#006e2f',
              surface: '#f8f9ff',
              'surface-container-highest': '#d3e4fe',
              'surface-bright': '#f8f9ff',
              'secondary-fixed': '#dae2fd',
              'tertiary-fixed-dim': '#ffb4a9',
              'on-background': '#0b1c30',
              'on-primary-fixed': '#002109',
              'secondary-fixed-dim': '#bec6e0',
              'on-error-container': '#93000a',
              'surface-container-low': '#eff4ff',
              'on-tertiary-container': '#76231b',
              'primary-fixed': '#6bff8f',
              'primary-container': '#22c55e',
              'inverse-primary': '#4ae176',
            },
            borderRadius: {
              DEFAULT: '0.125rem',
              lg: '0.25rem',
              xl: '0.5rem',
              full: '0.75rem',
            },
            spacing: {
              xs: '4px',
              sm: '8px',
              xl: '48px',
              unit: '4px',
              gutter: '24px',
              'container-max': '1280px',
              md: '16px',
              lg: '24px',
            },
            fontFamily: {
              h2: ['Inter'],
              h3: ['Inter'],
              'body-sm': ['Inter'],
              'body-lg': ['Inter'],
              'label-caps': ['Inter'],
              h1: ['Inter'],
              'body-md': ['Inter'],
            },
            fontSize: {
              h2: ['30px', { lineHeight: '38px', letterSpacing: '-0.01em', fontWeight: '600' }],
              h3: ['24px', { lineHeight: '32px', letterSpacing: '0', fontWeight: '600' }],
              'body-sm': ['14px', { lineHeight: '20px', fontWeight: '400' }],
              'body-lg': ['18px', { lineHeight: '28px', fontWeight: '400' }],
              'label-caps': ['12px', { lineHeight: '16px', letterSpacing: '0.05em', fontWeight: '600' }],
              h1: ['40px', { lineHeight: '48px', letterSpacing: '-0.02em', fontWeight: '700' }],
              'body-md': ['16px', { lineHeight: '24px', fontWeight: '400' }],
            },
          },
        },
      };
    </script>
  </head>
  <body>
    <app-root></app-root>
  </body>
</html>

```

## src/main.ts

```ts
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app';

bootstrapApplication(App, appConfig).catch((err) => console.error(err));

```

## src/styles.css

```css
/* You can add global styles to this file, and also import other style files */

```

## tsconfig.app.json

```json
/* To learn more about Typescript configuration file: https://www.typescriptlang.org/docs/handbook/tsconfig-json.html. */
/* To learn more about Angular compiler options: https://angular.dev/reference/configs/angular-compiler-options. */
{
  "extends": "./tsconfig.json",
  "compilerOptions": {
    "outDir": "./out-tsc/app",
    "types": []
  },
  "include": ["src/**/*.ts"],
  "exclude": ["src/**/*.spec.ts"]
}

```

## tsconfig.json

```json
/* To learn more about Typescript configuration file: https://www.typescriptlang.org/docs/handbook/tsconfig-json.html. */
/* To learn more about Angular compiler options: https://angular.dev/reference/configs/angular-compiler-options. */
{
  "compileOnSave": false,
  "compilerOptions": {
    "strict": true,
    "noImplicitOverride": true,
    "noPropertyAccessFromIndexSignature": true,
    "noImplicitReturns": true,
    "noFallthroughCasesInSwitch": true,
    "skipLibCheck": true,
    "isolatedModules": true,
    "experimentalDecorators": true,
    "importHelpers": true,
    "target": "ES2022",
    "module": "preserve"
  },
  "angularCompilerOptions": {
    "enableI18nLegacyMessageIdFormat": false,
    "strictInjectionParameters": true,
    "strictInputAccessModifiers": true,
    "strictTemplates": true
  },
  "files": [],
  "references": [
    {
      "path": "./tsconfig.app.json"
    },
    {
      "path": "./tsconfig.spec.json"
    }
  ]
}

```

## tsconfig.spec.json

```json
/* To learn more about Typescript configuration file: https://www.typescriptlang.org/docs/handbook/tsconfig-json.html. */
/* To learn more about Angular compiler options: https://angular.dev/reference/configs/angular-compiler-options. */
{
  "extends": "./tsconfig.json",
  "compilerOptions": {
    "outDir": "./out-tsc/spec",
    "types": ["vitest/globals"]
  },
  "include": ["src/**/*.d.ts", "src/**/*.spec.ts"]
}

```


