# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 📂 Project Structure

The repository contains a full‑stack application:

- **Backend** (`ucab-services/backend`): Spring Boot 4.1 (Java 21) REST API with PostgreSQL.
- **Frontend** (`ucab-services/frontend`): Angular 21 standalone application.
- SQL scripts for the database schema and sample data are in the repository root:
  - `Base de datos entera.sql` – full schema and data.
  - `Comandos de consulta de inserts.sql` – additional insert statements.
  - `DROP TABLES.sql` – utility to drop all tables.

## ⚙️ Development Setup

### Prerequisites
- Java 21 (for backend)
- Maven (the project includes `mvnw`/`mvnw.cmd` wrapper)
- PostgreSQL server (create a database and update credentials in `application.properties`)
- Node.js (≥ 18 recommended) and Angular CLI (`npm install -g @angular/cli`)

### Backend
```bash
cd ucab-services/backend
# Using Maven Wrapper
./mvnw spring-boot:run   # Linux/macOS
mvnw.cmd spring-boot:run # Windows
```
The API runs on `http://localhost:8080` by default.

### Frontend
```bash
cd ucab-services/frontend
npm install
ng serve        # or ng serve -o to open browser
```
The app runs on `http://localhost:4200`.

### Testing
- **Backend unit tests**: `./mvnw test`
- **Specific backend test class**: `./mvnw test -Dtest=AuthServiceTest`
- **Frontend unit tests**: `ng test`
- **Frontend linting** (if configured): `ng lint`

### Database
The application expects a PostgreSQL database. The schema and initial data are in the SQL files at the repository root. After creating the database, run the scripts in this order:
1. `Base de datos entera.sql` (creates tables, functions, triggers, and inserts data)
2. Optionally, `Comandos de consulta de inserts.sql` for additional sample data.

The backend uses database functions for password verification and MFA handling (`fn_verificar_clave`, `fn_generar_codigo_mfa`, `fn_verificar_codigo_mfa`). Never handle plain‑text passwords in Java.

## 🏗️ Architecture Highlights

### Backend (Spring Boot)
- **Entry point**: `UcabServicesApplication.java` (standard `@SpringBootApplication`).
- **Authentication**:
  - Controller: `AuthController` exposes `/api/auth/login` and `/api/auth/verificar-mfa`.
  - Service: `AuthService` orchestrates login, MFA generation/verification, and account‑status checks.
  - Repository: `MiembroRepository` extends `JpaRepository` and calls native PostgreSQL functions for credential verification and MFA code handling.
  - Entity: `Miembro` maps to the `miembro` table; password hash (`clave_hash`) is marked `@JsonIgnore` and never exposed.
  - Security: Password verification delegated to PostgreSQL `fn_verificar_clave`; MFA code generation/verification via DB functions.
- **CORS**: `@CrossOrigin(origins = "http://localhost:4200")` on `AuthController` for development.
- **Lombok**: Getters, setters, and no‑args constructor generated via `@Getter @Setter @NoArgsConstructor`; annotation processing configured in `pom.xml`.

### Frontend (Angular)
- **Standalone components**: The app uses Angular standalone APIs (`app.config.ts`, `app.routes.ts`).
- **Routing**: Defined in `app.routes.ts`; lazy‑loaded page modules under `src/app/pages/` (e.g., `login`, `dashboard`, `agregar-miembro`).
- **Styling**: Tailwind CSS is integrated via the Angular build configuration (see `angular.json`).
- **State**: Simple services (not shown in the minimal scaffold) would be used for auth state; the login page calls the backend auth endpoints.
- **Configuration**: Environment files would normally live in `src/environments/` (not present in the minimal scaffold).

### Common Development Notes
- The backend uses `@Transactional` on service methods that call DB functions.
- Trigger‑based logic (e.g., blocking after failed attempts) resides in the database; the service only calls the relevant functions.
- No user‑registration endpoint exists; members are pre‑loaded into the database by administrators.
- The project includes a `.vscode` folder with recommended settings (format on save, etc.).
- A `.kiro` directory appears to be for project‑specific IDE configurations (likely from a UI tool).

## 📝 Useful Commands Summary

| Task | Command |
|------|---------|
| Start backend | `cd ucab-services/backend && ./mvnw spring-boot:run` |
| Start frontend | `cd ucab-services/frontend && npm install && ng serve` |
| Run backend tests | `cd ucab-services/backend && ./mvnw test` |
| Run a single backend test | `cd ucab-services/backend && ./mvnw test -Dtest=AuthServiceTest` |
| Run frontend unit tests | `cd ucab-services/frontend && ng test` |
| Lint frontend (if configured) | `cd ucab-services/frontend && ng lint` |
| Install frontend deps | `cd ucab-services/frontend && npm install` |
| Build backend jar | `cd ucab-services/backend && ./mvnw clean package` |
| Build frontend for prod | `cd ucab-services/frontend && ng build` |

When starting work, first ensure the database is running and initialized, then start the backend, and finally the frontend.

---
*This file is intended to be committed to the repository so that future Claude Code sessions have immediate context about how to build, test, and navigate the codebase.*