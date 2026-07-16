# UCAB Services - Guía de Instalación y Configuración

¡Bienvenido al repositorio del proyecto **UCAB Services**! 

Este documento es un tutorial paso a paso, detallado y claro, sobre cómo preparar tu entorno de desarrollo, instalar las dependencias necesarias y ejecutar tanto el **Backend** (Spring Boot) como el **Frontend** (Angular), además de configurar la **Base de Datos** (PostgreSQL).

---

## 📋 1. Prerrequisitos y Programas Necesarios

Antes de comenzar, asegúrate de descargar e instalar los siguientes programas en tu computadora:

1. **Java Development Kit (JDK) 21**
   - El backend está construido utilizando Java 21.
   - Puedes descargarlo desde [Oracle](https://www.oracle.com/java/technologies/downloads/#java21) o utilizar una distribución como [Eclipse Temurin (Adoptium)](https://adoptium.net/).
   - Asegúrate de configurar la variable de entorno `JAVA_HOME`.

2. **Node.js y npm**
   - El frontend requiere Node.js para ejecutar Angular. Se recomienda usar la versión LTS (v20 o superior).
   - Descárgalo desde la [página oficial de Node.js](https://nodejs.org/). `npm` (Node Package Manager) viene incluido por defecto.

3. **Angular CLI** (Opcional, pero recomendado)
   - Una vez instalado Node.js, abre una terminal y ejecuta el siguiente comando para instalar la herramienta de línea de comandos de Angular globalmente:
     ```bash
     npm install -g @angular/cli
     ```

4. **PostgreSQL**
   - El sistema de gestión de base de datos utilizado.
   - Descárgalo e instálalo desde su [página oficial](https://www.postgresql.org/download/).

5. **Herramienta de Gestión de Base de Datos** (Opcional)
   - Puedes usar **pgAdmin** (usualmente viene con PostgreSQL) o **DBeaver** para facilitar la importación y visualización del script de la base de datos.

6. **IDE (Entorno de Desarrollo Integrado)**
   - Se recomienda **Visual Studio Code** para el frontend (Angular) y **IntelliJ IDEA**, **Eclipse** o el mismo VS Code para el backend (Java Spring Boot).

---

## 🗄️ 2. Configuración de la Base de Datos

El proyecto ya incluye los scripts necesarios para levantar la estructura de la base de datos de manera rápida.

1. Abre tu gestor de base de datos (pgAdmin, DBeaver, o la consola psql).
2. Conéctate a tu servidor PostgreSQL local (por defecto se ejecuta en el puerto `5432`).
3. Crea una base de datos con el siguiente nombre exacto:
   ```sql
   CREATE DATABASE "Base_de_Datos";
   ```
4. **Credenciales por defecto**:
   El backend espera que PostgreSQL tenga las siguientes credenciales (puedes cambiar esto luego):
   - **Usuario:** `postgres`
   - **Contraseña:** `admin`
   *(Nota: Si usas otra contraseña, revisa la sección 3 para ver cómo configurarla localmente).*
5. Selecciona la base de datos recién creada (`Base_de_Datos`) y abre un script o consola de consultas (Query Tool).
6. Copia y ejecuta el contenido del archivo `Base de datos entera.sql` (ubicado en la raíz del proyecto). Esto creará todas las tablas, relaciones y estructuras necesarias.
7. *(Opcional)* Si deseas probar el sistema con datos de prueba, puedes ejecutar también el contenido del archivo `Comandos de consulta de inserts.sql`.

---

## ⚙️ 3. Instalación y Ejecución del Backend (Spring Boot)

El backend está ubicado en la carpeta `ucab-services/backend`. Se encarga de proveer la API REST, reportes en PDF/Excel (vía JasperReports) y la lógica de negocio.

### Pasos:

1. Abre una terminal y navega hasta el directorio del backend:
   ```bash
   cd ucab-services/backend
   ```
2. **Configuración Local (Opcional pero Recomendado)**:
   Si las credenciales de tu PostgreSQL (usuario o contraseña) no son `postgres` y `admin`, **no modifiques** el archivo `application.properties` principal para no afectar el control de versiones (Git).
   - Ve a la ruta: `src/main/resources/`
   - Crea un archivo llamado `application-local.properties`.
   - Agrega tus credenciales allí. Por ejemplo:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/Base_de_Datos
     spring.datasource.username=TU_USUARIO
     spring.datasource.password=TU_CONTRASEÑA
     ```
   El proyecto está configurado con el perfil `local` activo (`spring.profiles.active=local`), por lo que leerá tus credenciales personales automáticamente de este archivo.

3. **Descarga de dependencias y compilación**:
   El proyecto usa Maven Wrapper, lo que significa que **no necesitas instalar Maven manualmente**. Ejecuta en la terminal:
   - **En Windows:**
     ```cmd
     mvnw.cmd clean install -DskipTests
     ```
   - **En Mac/Linux:**
     ```bash
     ./mvnw clean install -DskipTests
     ```

4. **Ejecutar la aplicación**:
   - Para encender el servidor desde la consola:
     - **En Windows:** `mvnw.cmd spring-boot:run`
     - **En Mac/Linux:** `./mvnw spring-boot:run`
   - Si usas un IDE como IntelliJ IDEA, simplemente abre el proyecto, busca la clase principal `UcabServicesApplication.java` y haz clic en el botón de **Play / Run**.

El servidor del backend debería arrancar correctamente y estar escuchando en el puerto **8081**: `http://localhost:8081`.

---

## 💻 4. Instalación y Ejecución del Frontend (Angular)

El frontend está ubicado en la carpeta `ucab-services/frontend`. Se encarga de la interfaz gráfica y de interactuar con la API del backend.

### Pasos:

1. Abre una **nueva ventana de terminal** y navega hasta el directorio del frontend:
   ```bash
   cd ucab-services/frontend
   ```

2. **Instalar dependencias**:
   Ejecuta el siguiente comando para que Node.js descargue e instale todas las librerías de Angular necesarias especificadas en el archivo `package.json` (esto puede tardar unos minutos la primera vez):
   ```bash
   npm install
   ```

3. **Ejecutar el servidor de desarrollo**:
   Una vez terminada la instalación, inicia el servidor ejecutando:
   ```bash
   npm start
   ```
   *(Este comando es un alias de `ng serve`)*.

4. **Acceder a la aplicación**:
   La compilación tomará unos segundos. Una vez terminada, verás un mensaje verde en la consola indicando que fue exitosa.
   Abre tu navegador web favorito (Chrome, Firefox, Edge, Safari) e ingresa a:
   👉 **http://localhost:4200**

¡Listo! Ya deberías ver la aplicación web en funcionamiento, conectada al backend, el cual a su vez está conectado a tu base de datos local.
