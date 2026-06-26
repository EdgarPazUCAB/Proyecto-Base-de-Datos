# Inventario util del Frontend y Backend

Este documento resume los archivos mas utiles del proyecto y para que sirve cada uno.
Se excluyen carpetas generadas como `dist/`, `target/`, `node_modules/` y `.angular/`.

## Frontend

### Configuracion y arranque

| Archivo | Proposito |
| --- | --- |
| [ucab-services/frontend/angular.json](ucab-services/frontend/angular.json) | Configuracion principal de Angular, build, test y serve. |
| [ucab-services/frontend/package.json](ucab-services/frontend/package.json) | Dependencias y scripts del frontend. |
| [ucab-services/frontend/tsconfig.json](ucab-services/frontend/tsconfig.json) | Configuracion base de TypeScript. |
| [ucab-services/frontend/tsconfig.app.json](ucab-services/frontend/tsconfig.app.json) | Configuracion TypeScript para la app. |
| [ucab-services/frontend/tsconfig.spec.json](ucab-services/frontend/tsconfig.spec.json) | Configuracion TypeScript para pruebas. |
| [ucab-services/frontend/README.md](ucab-services/frontend/README.md) | Instrucciones propias del frontend. |
| [ucab-services/frontend/src/main.ts](ucab-services/frontend/src/main.ts) | Punto de entrada de Angular. |
| [ucab-services/frontend/src/index.html](ucab-services/frontend/src/index.html) | HTML base y definicion visual global con Tailwind por CDN y variables de tema. |
| [ucab-services/frontend/src/styles.css](ucab-services/frontend/src/styles.css) | Estilos globales. |

### Shell global de la app

| Archivo | Proposito |
| --- | --- |
| [ucab-services/frontend/src/app/app.ts](ucab-services/frontend/src/app/app.ts) | Componente raiz de la aplicacion. |
| [ucab-services/frontend/src/app/app.html](ucab-services/frontend/src/app/app.html) | Shell global; controla cuando mostrar el Navbar. |
| [ucab-services/frontend/src/app/app.css](ucab-services/frontend/src/app/app.css) | Estilos del shell raiz. |
| [ucab-services/frontend/src/app/app.config.ts](ucab-services/frontend/src/app/app.config.ts) | Providers globales, router y HttpClient. |
| [ucab-services/frontend/src/app/app.routes.ts](ucab-services/frontend/src/app/app.routes.ts) | Rutas de toda la aplicacion. |
| [ucab-services/frontend/src/app/app.spec.ts](ucab-services/frontend/src/app/app.spec.ts) | Pruebas del shell raiz. |

### Navbar compartido

| Archivo | Proposito |
| --- | --- |
| [ucab-services/frontend/src/app/navbar/navbar.ts](ucab-services/frontend/src/app/navbar/navbar.ts) | Componente Navbar reutilizable. |
| [ucab-services/frontend/src/app/navbar/navbar.html](ucab-services/frontend/src/app/navbar/navbar.html) | Estructura visual del Navbar. |
| [ucab-services/frontend/src/app/navbar/navbar.css](ucab-services/frontend/src/app/navbar/navbar.css) | Estilos del Navbar. |
| [ucab-services/frontend/src/app/navbar/navbar.spec.ts](ucab-services/frontend/src/app/navbar/navbar.spec.ts) | Pruebas del Navbar. |

### Login

| Archivo | Proposito |
| --- | --- |
| [ucab-services/frontend/src/app/pages/login/login.ts](ucab-services/frontend/src/app/pages/login/login.ts) | Formulario funcional de login con llamada a backend y soporte MFA. |
| [ucab-services/frontend/src/app/pages/login/login.html](ucab-services/frontend/src/app/pages/login/login.html) | Vista del login. |
| [ucab-services/frontend/src/app/pages/login/login.css](ucab-services/frontend/src/app/pages/login/login.css) | Estilos del login. |
| [ucab-services/frontend/src/app/pages/login/login.spec.ts](ucab-services/frontend/src/app/pages/login/login.spec.ts) | Pruebas del login. |

### Paginas funcionales

| Archivo | Proposito |
| --- | --- |
| [ucab-services/frontend/src/app/pages/dashboard/dashboard.ts](ucab-services/frontend/src/app/pages/dashboard/dashboard.ts) | Dashboard principal de la app. |
| [ucab-services/frontend/src/app/pages/dashboard/dashboard.html](ucab-services/frontend/src/app/pages/dashboard/dashboard.html) | Vista del dashboard. |
| [ucab-services/frontend/src/app/pages/dashboard/dashboard.css](ucab-services/frontend/src/app/pages/dashboard/dashboard.css) | Estilos del dashboard. |
| [ucab-services/frontend/src/app/pages/dashboard/dashboard.spec.ts](ucab-services/frontend/src/app/pages/dashboard/dashboard.spec.ts) | Pruebas del dashboard. |
| [ucab-services/frontend/src/app/pages/profile/profile.ts](ucab-services/frontend/src/app/pages/profile/profile.ts) | Perfil de usuario. |
| [ucab-services/frontend/src/app/pages/profile/profile.html](ucab-services/frontend/src/app/pages/profile/profile.html) | Vista del perfil. |
| [ucab-services/frontend/src/app/pages/profile/profile.css](ucab-services/frontend/src/app/pages/profile/profile.css) | Estilos del perfil. |
| [ucab-services/frontend/src/app/pages/profile/profile.spec.ts](ucab-services/frontend/src/app/pages/profile/profile.spec.ts) | Pruebas del perfil. |
| [ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.ts](ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.ts) | Consulta de miembros. |
| [ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.html](ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.html) | Vista de consulta de miembros. |
| [ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.css](ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.css) | Estilos de consulta de miembros. |
| [ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.spec.ts](ucab-services/frontend/src/app/pages/consultar-miembro/consultar-miembro.spec.ts) | Pruebas de consulta de miembros. |
| [ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.ts](ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.ts) | Alta de vinculo familiar. |
| [ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.html](ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.html) | Vista del formulario de vinculo familiar. |
| [ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.css](ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.css) | Estilos del formulario de vinculo familiar. |
| [ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.spec.ts](ucab-services/frontend/src/app/pages/anadir-vinculo-familiar/anadir-vinculo-familiar.spec.ts) | Pruebas del modulo de vinculo familiar. |
| [ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.ts](ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.ts) | Solicitud de servicios. |
| [ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.html](ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.html) | Vista de solicitud de servicios. |
| [ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.css](ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.css) | Estilos de solicitud de servicios. |
| [ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.spec.ts](ucab-services/frontend/src/app/pages/solicitar-servicio/solicitar-servicio.spec.ts) | Pruebas de solicitud de servicios. |
| [ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.ts](ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.ts) | Gestion de actividades de servicio. |
| [ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.html](ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.html) | Vista de actividades de servicio. |
| [ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.css](ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.css) | Estilos de actividades de servicio. |
| [ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.spec.ts](ucab-services/frontend/src/app/pages/servicio-actividad/servicio-actividad.spec.ts) | Pruebas de actividades de servicio. |
| [ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.ts](ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.ts) | Folio de consumo. |
| [ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.html](ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.html) | Vista del folio de consumo. |
| [ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.css](ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.css) | Estilos del folio de consumo. |
| [ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.spec.ts](ucab-services/frontend/src/app/pages/folio-consumo/folio-consumo.spec.ts) | Pruebas del folio de consumo. |
| [ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.ts](ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.ts) | Listado de ofertas laborales. |
| [ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.html](ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.html) | Vista de ofertas laborales. |
| [ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.css](ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.css) | Estilos de ofertas laborales. |
| [ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.spec.ts](ucab-services/frontend/src/app/pages/ofertas-laborales/ofertas-laborales.spec.ts) | Pruebas de ofertas laborales. |
| [ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.ts](ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.ts) | Detalle de una oferta laboral. |
| [ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.html](ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.html) | Vista del detalle de oferta laboral. |
| [ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.css](ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.css) | Estilos del detalle de oferta laboral. |
| [ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.spec.ts](ucab-services/frontend/src/app/pages/oferta-laboral-detalle/oferta-laboral-detalle.spec.ts) | Pruebas del detalle de oferta laboral. |
| [ucab-services/frontend/src/app/pages/pago/pago.ts](ucab-services/frontend/src/app/pages/pago/pago.ts) | Flujo de pagos. |
| [ucab-services/frontend/src/app/pages/pago/pago.html](ucab-services/frontend/src/app/pages/pago/pago.html) | Vista de pagos. |
| [ucab-services/frontend/src/app/pages/pago/pago.css](ucab-services/frontend/src/app/pages/pago/pago.css) | Estilos de pagos. |
| [ucab-services/frontend/src/app/pages/pago/pago.spec.ts](ucab-services/frontend/src/app/pages/pago/pago.spec.ts) | Pruebas de pagos. |
| [ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.ts](ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.ts) | Catalogo de servicios. |
| [ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.html](ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.html) | Vista del catalogo de servicios. |
| [ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.css](ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.css) | Estilos del catalogo de servicios. |
| [ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.spec.ts](ucab-services/frontend/src/app/pages/catalogo-servicios/catalogo-servicios.spec.ts) | Pruebas del catalogo de servicios. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.ts](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.ts) | Metodo de pago con tarjeta. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.html](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.html) | Vista de tarjeta. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.css](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.css) | Estilos de tarjeta. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.spec.ts](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta/tarjeta.spec.ts) | Pruebas de tarjeta. |
| [ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.ts](ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.ts) | Metodo de pago movil. |
| [ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.html](ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.html) | Vista de pago movil. |
| [ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.css](ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.css) | Estilos de pago movil. |
| [ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.spec.ts](ucab-services/frontend/src/app/pages/metodos-pago/pago-movil/pago-movil.spec.ts) | Pruebas de pago movil. |
| [ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.ts](ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.ts) | Metodo de pago por Zelle. |
| [ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.html](ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.html) | Vista de Zelle. |
| [ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.css](ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.css) | Estilos de Zelle. |
| [ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.spec.ts](ucab-services/frontend/src/app/pages/metodos-pago/zelle/zelle.spec.ts) | Pruebas de Zelle. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.ts](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.ts) | Metodo de pago con Tarjeta TAI. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.html](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.html) | Vista de Tarjeta TAI. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.css](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.css) | Estilos de Tarjeta TAI. |
| [ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.spec.ts](ucab-services/frontend/src/app/pages/metodos-pago/tarjeta-tai/tarjeta-tai.spec.ts) | Pruebas de Tarjeta TAI. |
| [ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.ts](ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.ts) | Metodo de pago con criptomonedas. |
| [ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.html](ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.html) | Vista de criptomonedas. |
| [ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.css](ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.css) | Estilos de criptomonedas. |
| [ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.spec.ts](ucab-services/frontend/src/app/pages/metodos-pago/criptomonedas/criptomonedas.spec.ts) | Pruebas de criptomonedas. |

## Backend

### Configuracion y arranque

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/pom.xml](ucab-services/backend/pom.xml) | Configuracion Maven, dependencias y plugins. |
| [ucab-services/backend/mvnw](ucab-services/backend/mvnw) | Maven Wrapper para Linux/macOS. |
| [ucab-services/backend/mvnw.cmd](ucab-services/backend/mvnw.cmd) | Maven Wrapper para Windows. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/UcabServicesApplication.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/UcabServicesApplication.java) | Clase principal de Spring Boot. |
| [ucab-services/backend/src/main/resources/application.properties](ucab-services/backend/src/main/resources/application.properties) | Configuracion de base de datos y servidor. |

### Controladores

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AuthController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AuthController.java) | Endpoint de login y MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/MFACodigoController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/MFACodigoController.java) | CRUD de codigos MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AuditoriaSesionController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AuditoriaSesionController.java) | CRUD de auditoria de sesiones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/GlobalExceptionHandler.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/GlobalExceptionHandler.java) | Manejo global de errores. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/MiembroController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/MiembroController.java) | Operaciones sobre miembros. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EstudianteController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EstudianteController.java) | Operaciones sobre estudiantes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/DocenteController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/DocenteController.java) | Operaciones sobre docentes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/PersonalAdministrativoController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/PersonalAdministrativoController.java) | Operaciones sobre personal administrativo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/PreparadorController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/PreparadorController.java) | Operaciones sobre preparadores. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/BecarioController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/BecarioController.java) | Operaciones sobre becarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/BeneficiarioController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/BeneficiarioController.java) | Operaciones sobre beneficiarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/BilleteraTaiController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/BilleteraTaiController.java) | Operaciones sobre billetera TAI. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AsignadoEnController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AsignadoEnController.java) | Operaciones sobre asignaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AplicaEnController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AplicaEnController.java) | Operaciones sobre postulaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/CategoriaFidelidadController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/CategoriaFidelidadController.java) | Operaciones sobre categorias de fidelidad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/ClasificadoEnController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/ClasificadoEnController.java) | Operaciones sobre clasificacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EntidadPrestadoraController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EntidadPrestadoraController.java) | Operaciones sobre entidades prestadoras. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EgresadoController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EgresadoController.java) | Operaciones sobre egresados. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EdificacionController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EdificacionController.java) | Operaciones sobre edificaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EspacioFisicoController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/EspacioFisicoController.java) | Operaciones sobre espacios fisicos. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/SedeController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/SedeController.java) | Operaciones sobre sedes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/ServicioController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/ServicioController.java) | Operaciones sobre servicios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/SolicitudServicioController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/SolicitudServicioController.java) | Operaciones sobre solicitudes de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/TarifaServicioController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/TarifaServicioController.java) | Operaciones sobre tarifas de servicio. |
| PagoMovilController | No existe como archivo separado en el repo actual. |

### Servicios

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AuthService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AuthService.java) | Logica completa de autenticacion y MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/MFACodigoService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/MFACodigoService.java) | Servicio de codigos MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AuditoriaSesionService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AuditoriaSesionService.java) | Servicio de auditoria de sesiones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/MiembroService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/MiembroService.java) | Servicio de miembros. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EstudianteService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EstudianteService.java) | Servicio de estudiantes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/DocenteService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/DocenteService.java) | Servicio de docentes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/PersonalAdministrativoService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/PersonalAdministrativoService.java) | Servicio de personal administrativo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/PreparadorService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/PreparadorService.java) | Servicio de preparadores. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/BecarioService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/BecarioService.java) | Servicio de becarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/BeneficiarioService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/BeneficiarioService.java) | Servicio de beneficiarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/BilleteraTaiService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/BilleteraTaiService.java) | Servicio de billetera TAI. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AsignadoEnService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AsignadoEnService.java) | Servicio de asignaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AplicaEnService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AplicaEnService.java) | Servicio de postulaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/CategoriaFidelidadService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/CategoriaFidelidadService.java) | Servicio de categorias de fidelidad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/ClasificadoEnService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/ClasificadoEnService.java) | Servicio de clasificacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EntidadPrestadoraService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EntidadPrestadoraService.java) | Servicio de entidades prestadoras. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EgresadoService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EgresadoService.java) | Servicio de egresados. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EdificacionService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EdificacionService.java) | Servicio de edificaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EspacioFisicoService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/EspacioFisicoService.java) | Servicio de espacios fisicos. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/FacturaService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/FacturaService.java) | Servicio de facturas. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/ServicioService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/ServicioService.java) | Servicio de servicios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/SedeService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/SedeService.java) | Servicio de sedes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/SolicitudServicioService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/SolicitudServicioService.java) | Servicio de solicitudes de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/TarifaServicioService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/TarifaServicioService.java) | Servicio de tarifas de servicio. |

### Implementaciones de servicios

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/AuditoriaSesionServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/AuditoriaSesionServiceImpl.java) | Implementacion de auditoria de sesiones. |
| AuthService impl | No existe como archivo separado en el repo actual. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/AsignadoEnServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/AsignadoEnServiceImpl.java) | Implementacion de asignaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/AplicaEnServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/AplicaEnServiceImpl.java) | Implementacion de postulaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/BecarioServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/BecarioServiceImpl.java) | Implementacion de becarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/BeneficiarioServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/BeneficiarioServiceImpl.java) | Implementacion de beneficiarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/BilleteraTaiServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/BilleteraTaiServiceImpl.java) | Implementacion de billetera TAI. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/CategoriaFidelidadServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/CategoriaFidelidadServiceImpl.java) | Implementacion de categorias de fidelidad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/ClasificadoEnServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/ClasificadoEnServiceImpl.java) | Implementacion de clasificacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/DocenteServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/DocenteServiceImpl.java) | Implementacion de docentes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EdificacionServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EdificacionServiceImpl.java) | Implementacion de edificaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EgresadoServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EgresadoServiceImpl.java) | Implementacion de egresados. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EspacioFisicoServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EspacioFisicoServiceImpl.java) | Implementacion de espacios fisicos. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EstudianteServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EstudianteServiceImpl.java) | Implementacion de estudiantes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EntidadPrestadoraServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/EntidadPrestadoraServiceImpl.java) | Implementacion de entidades prestadoras. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/FacturaServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/FacturaServiceImpl.java) | Implementacion de facturas. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/MFACodigoServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/MFACodigoServiceImpl.java) | Implementacion de codigos MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/MiembroServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/MiembroServiceImpl.java) | Implementacion de miembros. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/PersonalAdministrativoServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/PersonalAdministrativoServiceImpl.java) | Implementacion de personal administrativo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/PeriodoVinculacionServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/PeriodoVinculacionServiceImpl.java) | Implementacion de periodos de vinculacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/PreparadorServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/PreparadorServiceImpl.java) | Implementacion de preparadores. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/SedeServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/SedeServiceImpl.java) | Implementacion de sedes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/ServicioServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/ServicioServiceImpl.java) | Implementacion de servicios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/SolicitudServicioServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/SolicitudServicioServiceImpl.java) | Implementacion de solicitudes de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/TarifaServicioServiceImpl.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/impl/TarifaServicioServiceImpl.java) | Implementacion de tarifas de servicio. |

### DTOs

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginRequest.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginRequest.java) | Datos de entrada para login. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginResponse.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginResponse.java) | Respuesta del login. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java) | Datos para verificar MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/MiembroSesionDTO.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/MiembroSesionDTO.java) | Datos resumidos de sesion de miembro. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/MiembroDetalleDTO.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/MiembroDetalleDTO.java) | Detalle extendido de miembro. |

### Entidades

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Miembro.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Miembro.java) | Entidad base de miembros. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/RolMiembro.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/RolMiembro.java) | Roles de miembro. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AuditoriaSesion.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AuditoriaSesion.java) | Auditoria de sesiones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AuditoriaSesionId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AuditoriaSesionId.java) | ID compuesto de auditoria. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/MFACodigo.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/MFACodigo.java) | Entidad de codigos MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/MFACodigoId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/MFACodigoId.java) | ID compuesto de MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Estudiante.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Estudiante.java) | Entidad estudiante. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Docente.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Docente.java) | Entidad docente. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PersonalAdministrativo.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PersonalAdministrativo.java) | Entidad personal administrativo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Preparador.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Preparador.java) | Entidad preparador. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Becario.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Becario.java) | Entidad becario. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Beneficiario.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Beneficiario.java) | Entidad beneficiario. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/BilleteraTai.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/BilleteraTai.java) | Entidad billetera TAI. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AsignadoEn.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AsignadoEn.java) | Relacion asignado-en. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AsignadoEnId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AsignadoEnId.java) | ID compuesto de asignado-en. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AplicaEn.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AplicaEn.java) | Relacion aplica-en. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AplicaEnId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AplicaEnId.java) | ID compuesto de aplica-en. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/CategoriaFidelidad.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/CategoriaFidelidad.java) | Categoria de fidelidad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ClasificadoEn.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ClasificadoEn.java) | Relacion clasificado-en. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ClasificadoEnId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ClasificadoEnId.java) | ID compuesto de clasificado-en. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EntidadPrestadora.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EntidadPrestadora.java) | Entidad prestadora. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Egresado.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Egresado.java) | Entidad egresado. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Edificacion.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Edificacion.java) | Entidad edificacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EdificacionId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EdificacionId.java) | ID compuesto de edificacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EspacioFisico.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EspacioFisico.java) | Espacio fisico. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EspacioFisicoId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/EspacioFisicoId.java) | ID compuesto de espacio fisico. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Factura.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Factura.java) | Entidad factura. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Externa.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Externa.java) | Entidad externa. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Interna.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Interna.java) | Entidad interna. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/FolioConsumo.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/FolioConsumo.java) | Folio de consumo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/FolioConsumoId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/FolioConsumoId.java) | ID compuesto de folio de consumo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ItemConsumo.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ItemConsumo.java) | Item de consumo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ItemConsumoId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/ItemConsumoId.java) | ID compuesto de item de consumo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/OfertaLaboral.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/OfertaLaboral.java) | Oferta laboral. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/OfertaLaboralId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/OfertaLaboralId.java) | ID compuesto de oferta laboral. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Pago.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Pago.java) | Pago general. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PagoId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PagoId.java) | ID compuesto de pago. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PagoMovil.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PagoMovil.java) | Pago movil. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PagoTai.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PagoTai.java) | Pago TAI. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Tarjeta.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Tarjeta.java) | Pago con tarjeta. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Zelle.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Zelle.java) | Pago por Zelle. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacion.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacion.java) | Periodo de vinculacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacionId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacionId.java) | ID compuesto de periodo de vinculacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PasoActividad.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PasoActividad.java) | Paso de actividad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PasoActividadId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/PasoActividadId.java) | ID compuesto de paso de actividad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Servicio.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Servicio.java) | Servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Sede.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Sede.java) | Sede. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/SolicitudServicio.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/SolicitudServicio.java) | Solicitud de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/SugeridaA.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/SugeridaA.java) | Relacion sugerida-a. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/SugeridaAId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/SugeridaAId.java) | ID compuesto de sugerida-a. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/TarifaServicio.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/TarifaServicio.java) | Tarifa de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/TarifaServicioId.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/TarifaServicioId.java) | ID compuesto de tarifa de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Criptomonedas.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/Criptomonedas.java) | Pago con criptomonedas. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AcompananteTemporal.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/entity/AcompananteTemporal.java) | Entidad acompanante temporal. |

## Codigo fuente clave

### Frontend

#### [ucab-services/frontend/src/app/app.ts](ucab-services/frontend/src/app/app.ts)

```ts
import { Component, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Navbar } from './navbar/navbar';

@Component({
	selector: 'app-root',
	imports: [RouterOutlet, Navbar],
	templateUrl: './app.html',
	styleUrl: './app.css',
})
export class App {
	protected readonly router = inject(Router);
}
```

#### [ucab-services/frontend/src/app/app.html](ucab-services/frontend/src/app/app.html)

```html
@if (router.url !== '/' && !router.url.startsWith('/login')) {
	<app-navbar></app-navbar>
}

<router-outlet></router-outlet>
```

#### [ucab-services/frontend/src/app/app.config.ts](ucab-services/frontend/src/app/app.config.ts)

```ts
import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
	providers: [provideBrowserGlobalErrorListeners(), provideHttpClient(), provideRouter(routes)],
};
```

#### [ucab-services/frontend/src/app/app.routes.ts](ucab-services/frontend/src/app/app.routes.ts)

```ts
import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Profile } from './pages/profile/profile';
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

#### [ucab-services/frontend/src/app/navbar/navbar.ts](ucab-services/frontend/src/app/navbar/navbar.ts)

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

#### [ucab-services/frontend/src/app/navbar/navbar.html](ucab-services/frontend/src/app/navbar/navbar.html)

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

#### [ucab-services/frontend/src/app/pages/login/login.ts](ucab-services/frontend/src/app/pages/login/login.ts)

```ts
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

interface LoginResponse {
	token?: string;
	roles?: string[];
	nombre?: string;
	correo?: string;
	cedulaMiembro?: string;
	requiereMfa: boolean;
	mensaje?: string;
}

@Component({
	selector: 'app-login',
	imports: [CommonModule, ReactiveFormsModule],
	templateUrl: './login.html',
	styleUrl: './login.css',
})
export class Login {
	private readonly apiUrl = 'http://localhost:8080/api/auth';
	private readonly fb = inject(FormBuilder);
	private readonly http = inject(HttpClient);
	private readonly router = inject(Router);

	readonly loginForm = this.fb.nonNullable.group({
		correo: ['', [Validators.required, Validators.email]],
		clave: ['', [Validators.required, Validators.minLength(6)]],
	});

	readonly mfaForm = this.fb.nonNullable.group({
		codigo: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
	});

	isLoading = false;
	isVerifying = false;
	errorMessage = '';
	infoMessage = '';
	requiresMfa = false;
	cedulaMiembro = '';

	submitLogin(): void {
		if (this.loginForm.invalid) {
			this.loginForm.markAllAsTouched();
			return;
		}

		this.errorMessage = '';
		this.infoMessage = '';
		this.requiresMfa = false;
		this.isLoading = true;

		this.http.post<LoginResponse>(`${this.apiUrl}/login`, this.loginForm.getRawValue()).subscribe({
			next: (response) => {
				this.isLoading = false;

				if (response.requiereMfa) {
					this.requiresMfa = true;
					this.cedulaMiembro = response.cedulaMiembro ?? '';
					this.infoMessage = response.mensaje ?? 'Ingresa el código enviado a tu correo institucional.';
					this.mfaForm.reset();
					return;
				}

				this.storeSession(response);
				void this.router.navigate(['/dashboard']);
			},
			error: (errorResponse) => {
				this.isLoading = false;
				this.errorMessage = this.extractErrorMessage(errorResponse);
			},
		});
	}

	verifyMfa(): void {
		if (this.mfaForm.invalid || !this.cedulaMiembro) {
			this.mfaForm.markAllAsTouched();
			return;
		}

		this.errorMessage = '';
		this.infoMessage = '';
		this.isVerifying = true;

		this.http
			.post<LoginResponse>(`${this.apiUrl}/verificar-mfa`, {
				cedula: this.cedulaMiembro,
				codigo: this.mfaForm.getRawValue().codigo,
			})
			.subscribe({
				next: (response) => {
					this.isVerifying = false;
					this.storeSession(response);
					void this.router.navigate(['/dashboard']);
				},
				error: (errorResponse) => {
					this.isVerifying = false;
					this.errorMessage = this.extractErrorMessage(errorResponse);
				},
			});
	}

	private storeSession(response: LoginResponse): void {
		if (response.token) {
			localStorage.setItem('ucab_token', response.token);
		}

		if (response.nombre) {
			localStorage.setItem('ucab_nombre', response.nombre);
		}

		if (response.correo) {
			localStorage.setItem('ucab_correo', response.correo);
		}

		if (response.cedulaMiembro) {
			localStorage.setItem('ucab_cedula', response.cedulaMiembro);
		}

		if (response.roles?.length) {
			localStorage.setItem('ucab_roles', JSON.stringify(response.roles));
		}
	}

	private extractErrorMessage(errorResponse: unknown): string {
		const response = errorResponse as { error?: { error?: string; message?: string } };
		return response.error?.error ?? response.error?.message ?? 'No se pudo iniciar sesión. Intenta de nuevo.';
	}
}
```

#### [ucab-services/frontend/src/app/pages/login/login.html](ucab-services/frontend/src/app/pages/login/login.html)

```html
<div class="min-h-screen bg-background flex items-center justify-center px-4 py-10 text-on-surface font-body-md">
	<div class="absolute inset-0 pointer-events-none overflow-hidden">
		<div class="absolute -top-24 -left-24 h-72 w-72 rounded-full bg-primary/10 blur-3xl"></div>
		<div class="absolute bottom-0 right-0 h-96 w-96 rounded-full bg-surface-container-high/70 blur-3xl"></div>
	</div>

	<section class="relative z-10 w-full max-w-[520px] overflow-hidden rounded-2xl border border-outline-variant bg-surface-container-lowest shadow-[0_24px_60px_rgba(0,0,0,0.08)]">
		<div class="h-2 w-full bg-primary"></div>

		<div class="px-8 py-10 sm:px-10">
			<div class="mb-8 flex flex-col items-center text-center">
				<div class="mb-4 flex h-16 w-16 items-center justify-center rounded-full bg-primary-container text-primary shadow-sm">
					<span class="material-symbols-outlined text-[32px]" style="font-variation-settings: 'FILL' 1">school</span>
				</div>
				<h1 class="font-h1 text-h1 text-on-surface">Iniciar sesión</h1>
				<p class="mt-2 text-on-surface-variant">Accede a los servicios académicos y administrativos.</p>
			</div>

			<form class="space-y-6" [formGroup]="loginForm" (ngSubmit)="submitLogin()" novalidate>
				<div>
					<label class="mb-2 block text-sm font-semibold uppercase tracking-wide text-on-surface-variant" for="correo">Correo institucional</label>
					<div class="relative">
						<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline-variant">mail</span>
						<input
							class="w-full rounded-xl border border-outline-variant bg-surface-container-lowest py-3 pl-12 pr-4 text-on-surface outline-none transition focus:border-primary focus:ring-2 focus:ring-primary/20"
							id="correo"
							name="correo"
							type="email"
							placeholder="usuario@ucab.edu.ve"
							formControlName="correo"
							autocomplete="email"
						/>
					</div>
					@if (loginForm.controls.correo.touched && loginForm.controls.correo.invalid) {
						<p class="mt-2 text-sm text-error">Ingresa un correo válido.</p>
					}
				</div>

				<div>
					<label class="mb-2 block text-sm font-semibold uppercase tracking-wide text-on-surface-variant" for="clave">Contraseña</label>
					<div class="relative">
						<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline-variant">lock</span>
						<input
							class="w-full rounded-xl border border-outline-variant bg-surface-container-lowest py-3 pl-12 pr-4 text-on-surface outline-none transition focus:border-primary focus:ring-2 focus:ring-primary/20"
							id="clave"
							name="clave"
							type="password"
							placeholder="••••••••"
							formControlName="clave"
							autocomplete="current-password"
						/>
					</div>
					@if (loginForm.controls.clave.touched && loginForm.controls.clave.invalid) {
						<p class="mt-2 text-sm text-error">La contraseña debe tener al menos 6 caracteres.</p>
					}
				</div>

				<button
					class="flex h-12 w-full items-center justify-center gap-2 rounded-xl bg-primary px-4 font-semibold text-on-primary transition hover:bg-primary/90 disabled:cursor-not-allowed disabled:opacity-70"
					type="submit"
					[disabled]="isLoading"
				>
					@if (isLoading) {
						<span class="material-symbols-outlined animate-pulse text-[20px]">progress_activity</span>
						<span>Ingresando...</span>
					} @else {
						<span>Iniciar sesión</span>
						<span class="material-symbols-outlined text-[20px]">arrow_forward</span>
					}
				</button>
			</form>

			@if (requiresMfa) {
				<section class="mt-8 rounded-xl border border-outline-variant bg-surface-container-low p-5">
					<p class="font-semibold text-on-surface">Verificación adicional requerida</p>
					<p class="mt-1 text-sm text-on-surface-variant">{{ infoMessage }}</p>

					<form class="mt-4 space-y-4" [formGroup]="mfaForm" (ngSubmit)="verifyMfa()" novalidate>
						<div>
							<label class="mb-2 block text-sm font-semibold uppercase tracking-wide text-on-surface-variant" for="codigo">Código MFA</label>
							<input
								class="w-full rounded-xl border border-outline-variant bg-surface-container-lowest py-3 px-4 text-on-surface outline-none transition focus:border-primary focus:ring-2 focus:ring-primary/20"
								id="codigo"
								name="codigo"
								type="text"
								inputmode="numeric"
								maxlength="6"
								placeholder="123456"
								formControlName="codigo"
							/>
							@if (mfaForm.controls.codigo.touched && mfaForm.controls.codigo.invalid) {
								<p class="mt-2 text-sm text-error">El código debe tener 6 dígitos.</p>
							}
						</div>

						<button
							class="flex h-12 w-full items-center justify-center gap-2 rounded-xl border border-primary bg-primary text-on-primary transition hover:bg-primary/90 disabled:cursor-not-allowed disabled:opacity-70"
							type="submit"
							[disabled]="isVerifying"
						>
							@if (isVerifying) {
								<span class="material-symbols-outlined animate-pulse text-[20px]">progress_activity</span>
								<span>Verificando...</span>
							} @else {
								<span>Confirmar código</span>
								<span class="material-symbols-outlined text-[20px]">verified</span>
							}
						</button>
					</form>
				</section>
			}

			@if (errorMessage) {
				<div class="mt-6 rounded-xl border border-error/30 bg-error/10 px-4 py-3 text-sm text-error">
					{{ errorMessage }}
				</div>
			}

			@if (infoMessage && !requiresMfa) {
				<div class="mt-6 rounded-xl border border-primary/30 bg-primary/10 px-4 py-3 text-sm text-primary">
					{{ infoMessage }}
				</div>
			}
		</div>
	</section>
</div>
```

#### [ucab-services/frontend/src/app/pages/login/login.css](ucab-services/frontend/src/app/pages/login/login.css)

```css
.material-symbols-outlined {
	font-variation-settings:
		'FILL' 0,
		'wght' 400,
		'GRAD' 0,
		'opsz' 24;
}
```

#### [ucab-services/frontend/src/main.ts](ucab-services/frontend/src/main.ts)

```ts
import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app';

bootstrapApplication(App, appConfig).catch((err) => console.error(err));
```

### Backend

#### [ucab-services/backend/src/main/java/com/ucab/ucab_services/UcabServicesApplication.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/UcabServicesApplication.java)

```java
package com.ucab.ucab_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UcabServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcabServicesApplication.class, args);
	}

}
```

#### [ucab-services/backend/src/main/resources/application.properties](ucab-services/backend/src/main/resources/application.properties)

```properties
spring.application.name=ucab-services
# PostgreSQL DataSource Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/Base_de_Datos
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
# Hibernate DDL Auto (update for development)
spring.jpa.hibernate.ddl-auto=none
# Show SQL in console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

server.port=8081
```

#### [ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AuthController.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/controller/AuthController.java)

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * CORS restringido a localhost:4200 (Angular en desarrollo). El
 * comodin "*" se quito porque permitia que cualquier sitio web
 * llamara a este endpoint de autenticacion.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

		@Autowired
		private AuthService authService;

		@PostMapping("/login")
		public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
				try {
						LoginResponse response = authService.login(loginRequest);
						return ResponseEntity.ok(response);
				} catch (RuntimeException e) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error(e.getMessage()));
				}
		}

		@PostMapping("/verificar-mfa")
		public ResponseEntity<?> verificarMfa(@Valid @RequestBody VerificarMfaRequest request) {
				try {
						LoginResponse response = authService.verificarMfa(request);
						return ResponseEntity.ok(response);
				} catch (RuntimeException e) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error(e.getMessage()));
				}
		}

		private Map<String, String> error(String mensaje) {
				Map<String, String> errorDetails = new HashMap<>();
				errorDetails.put("error", mensaje);
				return errorDetails;
		}
}
```

#### [ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AuthService.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/service/AuthService.java)

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.repository.DocenteRepository;
import com.ucab.ucab_services.repository.PersonalAdministrativoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Orquesta el flujo completo de autenticacion.
 *
 * DECISION DE ARQUITECTURA: la verificacion de contrasena vive
 * UNICAMENTE en PostgreSQL, via fn_verificar_clave() (pgcrypto).
 * NO se usa PasswordEncoder/BCrypt de Java — eso crearia dos fuentes
 * de verdad para el mismo hash y arriesga incompatibilidad de
 * formato entre pgcrypto y spring-security-crypto, nunca probada.
 * Todo el hash/verificacion de claves pasa por las funciones SQL ya
 * construidas (fn_establecer_clave, fn_verificar_clave).
 *
 * IMPORTANTE: no hay endpoint de "registro". Los miembros ya existen
 * en la base de datos — este servicio solo autentica.
 */
@Service
public class AuthService {

		private static final Logger LOG = Logger.getLogger(AuthService.class.getName());

		@Autowired
		private MiembroRepository miembroRepository;

		@Autowired
		private EstudianteRepository estudianteRepository;

		@Autowired
		private DocenteRepository docenteRepository;

		@Autowired
		private PersonalAdministrativoRepository personalAdministrativoRepository;

		@Transactional
		public LoginResponse login(LoginRequest request) {
				Miembro miembro = miembroRepository.findByCorreoInstitucional(request.getCorreo())
								.orElseThrow(() -> new RuntimeException("Usuario o contrasena incorrectos."));

				boolean claveValida = miembroRepository.verificarClave(miembro.getCedulaMiembro(), request.getClave());

				if (!claveValida) {
						int intentosActuales = miembro.getIntentosFallidos() != null ? miembro.getIntentosFallidos() : 0;
						miembro.setIntentosFallidos(intentosActuales + 1);
						miembroRepository.save(miembro);
						// El trigger trg_bloquear_por_intentos_fallidos en PostgreSQL
						// bloquea la cuenta automaticamente al llegar a 5 intentos.
						throw new RuntimeException("Usuario o contrasena incorrectos.");
				}

				miembro.setIntentosFallidos(0);
				miembroRepository.save(miembro);

				validarEstadoCuenta(miembro);

				if (Boolean.TRUE.equals(miembro.getMfaHabilitado())) {
						String codigo = miembroRepository.generarCodigoMfa(miembro.getCedulaMiembro());
						// TODO: reemplazar por envio real de correo (JavaMailSender)
						// cuando se configuren credenciales SMTP.
						LOG.info("[MFA] Codigo para " + miembro.getCorreoInstitucional() + ": " + codigo);

						LoginResponse response = new LoginResponse();
						response.setRequiereMfa(true);
						response.setCedulaMiembro(miembro.getCedulaMiembro());
						response.setMensaje("Te enviamos un codigo de verificacion a tu correo institucional.");
						return response;
				}

				return construirRespuestaCompleta(miembro);
		}

		@Transactional
		public LoginResponse verificarMfa(VerificarMfaRequest request) {
				Miembro miembro = miembroRepository.findById(request.getCedula())
								.orElseThrow(() -> new RuntimeException("Miembro no encontrado."));

				boolean codigoValido = miembroRepository.verificarCodigoMfa(
								request.getCedula(), request.getCodigo());

				if (!codigoValido) {
						throw new RuntimeException("Codigo incorrecto o expirado.");
				}

				validarEstadoCuenta(miembro);

				return construirRespuestaCompleta(miembro);
		}

		private void validarEstadoCuenta(Miembro miembro) {
				switch (miembro.getEstadoCuenta()) {
						case "Suspendida" -> throw new RuntimeException(
										"Tu cuenta esta suspendida. Contacta a la administracion.");
						case "Bloqueada" -> throw new RuntimeException(
										"Tu cuenta esta bloqueada por intentos fallidos. Contacta a la administracion.");
						default -> { /* 'Activa': continua normalmente */ }
				}
		}

		private LoginResponse construirRespuestaCompleta(Miembro miembro) {
				List<String> roles = determinarRoles(miembro.getCedulaMiembro());

				LoginResponse response = new LoginResponse();
				response.setRequiereMfa(false);
				// Simulacion de token; sustituir por JWT real si se implementa.
				response.setToken("dummy-jwt-token-generado");
				response.setRoles(roles);
				response.setNombre(miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos());
				response.setCorreo(miembro.getCorreoInstitucional());
				response.setCedulaMiembro(miembro.getCedulaMiembro());
				response.setMensaje("Login exitoso.");
				return response;
		}

		/**
		 * Evalua la cedula de forma restrictiva.
		 * Solo permite los roles de Estudiante, Docente o Personal Administrativo.
		 */
		private List<String> determinarRoles(String cedula) {
				List<String> roles = new ArrayList<>();

				if (estudianteRepository.existsById(cedula)) {
						roles.add("ESTUDIANTE");
				} else if (docenteRepository.existsById(cedula)) {
						roles.add("DOCENTE");
				} else if (personalAdministrativoRepository.existsById(cedula)) {
						roles.add("PERSONAL_ADMINISTRATIVO");
				} else {
						throw new RuntimeException("Acceso denegado: su rol no tiene permisos para ingresar a la aplicacion Web.");
				}

				return roles;
		}
}
```

#### [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginRequest.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginRequest.java)

```java
package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
		@NotBlank(message = "Correo es requerido")
		@Size(max = 100, message = "Correo no puede exceder 100 caracteres")
		private String correo;

		@NotBlank(message = "Clave es requerida")
		@Size(min = 6, max = 100, message = "Clave debe tener entre 6 y 100 caracteres")
		private String clave;
}
```

#### [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginResponse.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/LoginResponse.java)

```java
package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
		private String token;
		private List<String> roles;
		private String nombre;
		private String correo;
		private String cedulaMiembro;
		private boolean requiereMfa;
		private String mensaje;
}
```

#### [ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java)

```java
package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VerificarMfaRequest {
		@NotBlank(message = "Cedula es requerida")
		@Size(min = 1, max = 20, message = "Cedula debe tener entre 1 y 20 caracteres")
		private String cedula;

		@NotBlank(message = "Codigo es requerido")
		@Size(min = 6, max = 6, message = "Codigo debe tener exactamente 6 caracteres")
		private String codigo;

		public String getCedula() {
				return cedula;
		}

		public void setCedula(String cedula) {
				this.cedula = cedula;
		}

		public String getCodigo() {
				return codigo;
		}

		public void setCodigo(String codigo) {
				this.codigo = codigo;
		}
}
```

#### [ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java)

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

		private MockMvc mockMvc;

		@Mock
		private AuthService authService;

		@InjectMocks
		private AuthController authController;

		private ObjectMapper objectMapper = new ObjectMapper();

		@BeforeEach
		void setUp() {
				MockitoAnnotations.openMocks(this);
				LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
				validator.afterPropertiesSet();
				mockMvc = MockMvcBuilders.standaloneSetup(authController)
								.setControllerAdvice(new GlobalExceptionHandler())
								.setValidator(validator)
								.build();
		}

		@Test
		void testLoginSuccess() throws Exception {
				LoginRequest loginRequest = new LoginRequest();
				loginRequest.setCorreo("juan.perez@ucab.edu.ve");
				loginRequest.setClave("password123");

				LoginResponse expectedResponse = new LoginResponse();
				expectedResponse.setToken("dummy-jwt-token");
				expectedResponse.setRoles(List.of("ESTUDIANTE"));
				expectedResponse.setNombre("Juan Perez");
				expectedResponse.setCorreo("juan.perez@ucab.edu.ve");
				expectedResponse.setCedulaMiembro("12345678");
				expectedResponse.setRequiereMfa(false);
				expectedResponse.setMensaje("Login successful");

				when(authService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

				mockMvc.perform(post("/api/auth/login")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(loginRequest)))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.token").value("dummy-jwt-token"))
								.andExpect(jsonPath("$.roles[0]").value("ESTUDIANTE"))
								.andExpect(jsonPath("$.nombre").value("Juan Perez"))
								.andExpect(jsonPath("$.correo").value("juan.perez@ucab.edu.ve"))
								.andExpect(jsonPath("$.cedulaMiembro").value("12345678"))
								.andExpect(jsonPath("$.requiereMfa").value(false))
								.andExpect(jsonPath("$.mensaje").value("Login successful"));
		}

		@Test
		void testLoginValidationFailure() throws Exception {
				LoginRequest loginRequest = new LoginRequest();

				mockMvc.perform(post("/api/auth/login")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(loginRequest)))
								.andExpect(status().isBadRequest())
								.andExpect(jsonPath("$.correo").exists())
								.andExpect(jsonPath("$.clave").exists());
		}
}
```

#### [ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java)

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerIntegrationTest {

		@Mock
		private AuthService authService;

		@InjectMocks
		private AuthController authController;

		private MockMvc mockMvc;
		private ObjectMapper objectMapper = new ObjectMapper();

		@BeforeEach
		void setUp() {
				LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
				validator.afterPropertiesSet();
				mockMvc = MockMvcBuilders.standaloneSetup(authController)
								.setControllerAdvice(new GlobalExceptionHandler())
								.setValidator(validator)
								.build();
		}

		@Test
		void testVerificarMfaSuccess() throws Exception {
				VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
				verificarMfaRequest.setCedula("12345678");
				verificarMfaRequest.setCodigo("123456");

				LoginResponse expectedResponse = new LoginResponse();
				expectedResponse.setToken("dummy-jwt-token");
				expectedResponse.setRoles(List.of("ESTUDIANTE"));
				expectedResponse.setNombre("Juan Perez");
				expectedResponse.setCorreo("juan.perez@ucab.edu.ve");
				expectedResponse.setCedulaMiembro("12345678");
				expectedResponse.setRequiereMfa(false);
				expectedResponse.setMensaje("MFA verified successfully");

				when(authService.verificarMfa(any(VerificarMfaRequest.class))).thenReturn(expectedResponse);

				mockMvc.perform(post("/api/auth/verificar-mfa")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(verificarMfaRequest)))
								.andExpect(status().isOk())
								.andExpect(jsonPath("$.token").value("dummy-jwt-token"))
								.andExpect(jsonPath("$.roles[0]").value("ESTUDIANTE"))
								.andExpect(jsonPath("$.nombre").value("Juan Perez"))
								.andExpect(jsonPath("$.correo").value("juan.perez@ucab.edu.ve"))
								.andExpect(jsonPath("$.cedulaMiembro").value("12345678"))
								.andExpect(jsonPath("$.requiereMfa").value(false))
								.andExpect(jsonPath("$.mensaje").value("MFA verified successfully"));
		}

		@Test
		void testVerificarMfaValidationFailure() throws Exception {
				VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
				verificarMfaRequest.setCedula("");
				verificarMfaRequest.setCodigo("123");

				mockMvc.perform(post("/api/auth/verificar-mfa")
												.contentType(MediaType.APPLICATION_JSON)
												.content(objectMapper.writeValueAsString(verificarMfaRequest)))
								.andExpect(status().isBadRequest())
								.andExpect(jsonPath("$.cedula").exists());
		}
}
```

## Notas rapidas

- El login del frontend ya apunta a `http://localhost:8080/api/auth`.
- El Navbar se muestra en todo el shell excepto en la ruta `/login`.
- El backend ya expone login y verificacion MFA, por eso el formulario del frontend puede trabajar sin crear endpoints nuevos.

### Repositorios

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/MiembroRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/MiembroRepository.java) | Acceso a datos de miembros y auth. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/MFACodigoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/MFACodigoRepository.java) | Acceso a codigos MFA. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/AuditoriaSesionRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/AuditoriaSesionRepository.java) | Acceso a auditoria de sesiones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EstudianteRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EstudianteRepository.java) | Acceso a estudiantes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/DocenteRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/DocenteRepository.java) | Acceso a docentes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PersonalAdministrativoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PersonalAdministrativoRepository.java) | Acceso a personal administrativo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/BecarioRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/BecarioRepository.java) | Acceso a becarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/BeneficiarioRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/BeneficiarioRepository.java) | Acceso a beneficiarios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/BilleteraTaiRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/BilleteraTaiRepository.java) | Acceso a billetera TAI. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/AsignadoEnRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/AsignadoEnRepository.java) | Acceso a asignaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/AplicaEnRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/AplicaEnRepository.java) | Acceso a postulaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/CategoriaFidelidadRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/CategoriaFidelidadRepository.java) | Acceso a categorias de fidelidad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/ClasificadoEnRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/ClasificadoEnRepository.java) | Acceso a clasificacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EntidadPrestadoraRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EntidadPrestadoraRepository.java) | Acceso a entidades prestadoras. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EgresadoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EgresadoRepository.java) | Acceso a egresados. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EdificacionRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EdificacionRepository.java) | Acceso a edificaciones. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EspacioFisicoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/EspacioFisicoRepository.java) | Acceso a espacios fisicos. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/FacturaRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/FacturaRepository.java) | Acceso a facturas. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/FolioConsumoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/FolioConsumoRepository.java) | Acceso a folios de consumo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/ItemConsumoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/ItemConsumoRepository.java) | Acceso a items de consumo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/OfertaLaboralRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/OfertaLaboralRepository.java) | Acceso a ofertas laborales. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PagoMovilRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PagoMovilRepository.java) | Acceso a pagos moviles. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PasoActividadRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PasoActividadRepository.java) | Acceso a pasos de actividad. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PeriodoVinculacionRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PeriodoVinculacionRepository.java) | Acceso a periodos de vinculacion. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PreparadorRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PreparadorRepository.java) | Acceso a preparadores. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PersonalAdministrativoRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/PersonalAdministrativoRepository.java) | Acceso a personal administrativo. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/SedeRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/SedeRepository.java) | Acceso a sedes. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/ServicioRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/ServicioRepository.java) | Acceso a servicios. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/SolicitudServicioRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/SolicitudServicioRepository.java) | Acceso a solicitudes de servicio. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/SugeridaARepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/SugeridaARepository.java) | Acceso a sugeridas-a. |
| [ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/TarifaServicioRepository.java](ucab-services/backend/src/main/java/com/ucab/ucab_services/repository/TarifaServicioRepository.java) | Acceso a tarifas de servicio. |

### Pruebas del backend

| Archivo | Proposito |
| --- | --- |
| [ucab-services/backend/src/test/java/com/ucab/ucab_services/UcabServicesApplicationTests.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/UcabServicesApplicationTests.java) | Smoke test de arranque. |
| [ucab-services/backend/src/test/java/com/ucab/ucab_services/service/MiembroServiceTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/service/MiembroServiceTest.java) | Pruebas de servicio de miembros. |
| [ucab-services/backend/src/test/java/com/ucab/ucab_services/service/EstudianteServiceTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/service/EstudianteServiceTest.java) | Pruebas de servicio de estudiantes. |
| [ucab-services/backend/src/test/java/com/ucab/ucab_services/service/BeneficiarioServiceTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/service/BeneficiarioServiceTest.java) | Pruebas de servicio de beneficiarios. |
| [ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java) | Pruebas del endpoint de login. |
| [ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java](ucab-services/backend/src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java) | Pruebas de integracion del login. |

## Notas rapidas

- El login del frontend ya apunta a `http://localhost:8080/api/auth`.
- El Navbar se muestra en todo el shell excepto en la ruta `/login`.
- El backend ya expone login y verificacion MFA, por eso el formulario del frontend puede trabajar sin crear endpoints nuevos.
