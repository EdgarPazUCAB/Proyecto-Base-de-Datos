# UCAB Services

Plataforma de gestión académica y administrativa para la UCAB (Universidad Católica Andrés Bello). 

Este proyecto se compone de un backend desarrollado en **Spring Boot (Java)** y un frontend desarrollado en **Angular**.

## Requisitos Previos

Asegúrate de tener instaladas las siguientes herramientas en tu sistema:

* **Java 21**: Para ejecutar el servidor backend.
* **Maven** (Opcional, el proyecto incluye `mvnw` que puede usarse en su lugar).
* **PostgreSQL**: Base de datos relacional requerida por el backend.
* **Node.js**: (Recomendado v18+ o v20+).
* **Angular CLI** v21+: Para ejecutar comandos de Angular (`npm install -g @angular/cli`).

---

## ⚙️ Configuración del Backend (Spring Boot)

El backend es una API REST construida con Spring Boot 4.1 y utiliza PostgreSQL como base de datos.

### 1. Configurar la Base de Datos
Debes asegurarte de tener un servidor de PostgreSQL en ejecución y crear una base de datos para el proyecto.
Las credenciales por defecto se configuran en el archivo `application.properties` o `application.yml` dentro de `backend/src/main/resources`.

### 2. Ejecutar el Servidor
Abre una terminal, navega a la carpeta del backend y usa el Maven Wrapper incluido para compilar y ejecutar el proyecto.

En **Windows**:
```bash
cd backend
mvnw.cmd spring-boot:run
```

En **Linux / macOS**:
```bash
cd backend
./mvnw spring-boot:run
```

El backend se iniciará y, por defecto, escuchará en el puerto `8080` (a menos que se especifique lo contrario en la configuración).

---

## 💻 Configuración del Frontend (Angular)

El frontend está construido con Angular 21 y Node.js.

### 1. Instalar Dependencias
Abre una terminal, dirígete a la carpeta `frontend` e instala todas las dependencias listadas en el `package.json`.

```bash
cd frontend
npm install
```

### 2. Ejecutar la Aplicación en Desarrollo
Para iniciar el servidor de desarrollo, ejecuta:

```bash
ng serve
```
También puedes usar `ng serve -o` para que se abra automáticamente en tu navegador por defecto.

La aplicación estará disponible en `http://localhost:4200/`. La página se recargará automáticamente cada vez que realices y guardes cambios en los archivos fuente.

---

## Dependencias Principales

**Frontend:**
* `@angular/core`, `@angular/common`, `@angular/router`, etc. (v21.2.0)
* `rxjs`
* TailwindCSS (integrado a través del entorno de compilación de Angular en muchos proyectos modernos)

**Backend:**
* `spring-boot-starter-webmvc`
* `spring-boot-starter-data-jpa`
* `postgresql` (Driver de conexión)
* `lombok` (Para reducir código repetitivo)
