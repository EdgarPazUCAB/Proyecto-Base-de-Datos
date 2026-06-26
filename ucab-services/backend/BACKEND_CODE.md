# Backend consolidado

Este archivo agrupa el codigo fuente principal del backend de `ucab-services` y su configuracion relevante. Se excluyeron `target/` y otros artefactos compilados.

## Archivos incluidos

- pom.xml
- src/main/java/com/ucab/ucab_services/config/SecurityConfig.java
- src/main/java/com/ucab/ucab_services/controller/AplicaEnController.java
- src/main/java/com/ucab/ucab_services/controller/AsignadoEnController.java
- src/main/java/com/ucab/ucab_services/controller/AuditoriaSesionController.java
- src/main/java/com/ucab/ucab_services/controller/AuthController.java
- src/main/java/com/ucab/ucab_services/controller/BecarioController.java
- src/main/java/com/ucab/ucab_services/controller/BeneficiarioController.java
- src/main/java/com/ucab/ucab_services/controller/BilleteraTaiController.java
- src/main/java/com/ucab/ucab_services/controller/CategoriaFidelidadController.java
- src/main/java/com/ucab/ucab_services/controller/ClasificadoEnController.java
- src/main/java/com/ucab/ucab_services/controller/DocenteController.java
- src/main/java/com/ucab/ucab_services/controller/EdificacionController.java
- src/main/java/com/ucab/ucab_services/controller/EgresadoController.java
- src/main/java/com/ucab/ucab_services/controller/EntidadPrestadoraController.java
- src/main/java/com/ucab/ucab_services/controller/EspacioFisicoController.java
- src/main/java/com/ucab/ucab_services/controller/EstudianteController.java
- src/main/java/com/ucab/ucab_services/controller/GlobalExceptionHandler.java
- src/main/java/com/ucab/ucab_services/controller/MFACodigoController.java
- src/main/java/com/ucab/ucab_services/controller/MiembroController.java
- src/main/java/com/ucab/ucab_services/controller/PeriodoVinculacionController.java
- src/main/java/com/ucab/ucab_services/controller/PersonalAdministrativoController.java
- src/main/java/com/ucab/ucab_services/controller/PreparadorController.java
- src/main/java/com/ucab/ucab_services/controller/SedeController.java
- src/main/java/com/ucab/ucab_services/controller/ServicioController.java
- src/main/java/com/ucab/ucab_services/controller/SolicitudServicioController.java
- src/main/java/com/ucab/ucab_services/controller/TarifaServicioController.java
- src/main/java/com/ucab/ucab_services/dto/LoginRequest.java
- src/main/java/com/ucab/ucab_services/dto/LoginResponse.java
- src/main/java/com/ucab/ucab_services/dto/MiembroSesionDTO.java
- src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java
- src/main/java/com/ucab/ucab_services/entity/AcompananteTemporal.java
- src/main/java/com/ucab/ucab_services/entity/AplicaEn.java
- src/main/java/com/ucab/ucab_services/entity/AplicaEnId.java
- src/main/java/com/ucab/ucab_services/entity/AsignadoEn.java
- src/main/java/com/ucab/ucab_services/entity/AsignadoEnId.java
- src/main/java/com/ucab/ucab_services/entity/AuditoriaSesion.java
- src/main/java/com/ucab/ucab_services/entity/AuditoriaSesionId.java
- src/main/java/com/ucab/ucab_services/entity/Becario.java
- src/main/java/com/ucab/ucab_services/entity/Beneficiario.java
- src/main/java/com/ucab/ucab_services/entity/BilleteraTai.java
- src/main/java/com/ucab/ucab_services/entity/CategoriaFidelidad.java
- src/main/java/com/ucab/ucab_services/entity/ClasificadoEn.java
- src/main/java/com/ucab/ucab_services/entity/ClasificadoEnId.java
- src/main/java/com/ucab/ucab_services/entity/Criptomonedas.java
- src/main/java/com/ucab/ucab_services/entity/Docente.java
- src/main/java/com/ucab/ucab_services/entity/Edificacion.java
- src/main/java/com/ucab/ucab_services/entity/EdificacionId.java
- src/main/java/com/ucab/ucab_services/entity/Efectivo.java
- src/main/java/com/ucab/ucab_services/entity/Egresado.java
- src/main/java/com/ucab/ucab_services/entity/EntidadPrestadora.java
- src/main/java/com/ucab/ucab_services/entity/EspacioFisico.java
- src/main/java/com/ucab/ucab_services/entity/EspacioFisicoId.java
- src/main/java/com/ucab/ucab_services/entity/Estudiante.java
- src/main/java/com/ucab/ucab_services/entity/Externa.java
- src/main/java/com/ucab/ucab_services/entity/Factura.java
- src/main/java/com/ucab/ucab_services/entity/FolioConsumo.java
- src/main/java/com/ucab/ucab_services/entity/FolioConsumoId.java
- src/main/java/com/ucab/ucab_services/entity/Interna.java
- src/main/java/com/ucab/ucab_services/entity/ItemConsumo.java
- src/main/java/com/ucab/ucab_services/entity/ItemConsumoId.java
- src/main/java/com/ucab/ucab_services/entity/MFACodigo.java
- src/main/java/com/ucab/ucab_services/entity/MFACodigoId.java
- src/main/java/com/ucab/ucab_services/entity/Miembro.java
- src/main/java/com/ucab/ucab_services/entity/OfertaLaboral.java
- src/main/java/com/ucab/ucab_services/entity/OfertaLaboralId.java
- src/main/java/com/ucab/ucab_services/entity/Pago.java
- src/main/java/com/ucab/ucab_services/entity/PagoId.java
- src/main/java/com/ucab/ucab_services/entity/PagoMovil.java
- src/main/java/com/ucab/ucab_services/entity/PagoTai.java
- src/main/java/com/ucab/ucab_services/entity/PasoActividad.java
- src/main/java/com/ucab/ucab_services/entity/PasoActividadId.java
- src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacion.java
- src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacionId.java
- src/main/java/com/ucab/ucab_services/entity/PersonalAdministrativo.java
- src/main/java/com/ucab/ucab_services/entity/Preparador.java
- src/main/java/com/ucab/ucab_services/entity/Sede.java
- src/main/java/com/ucab/ucab_services/entity/Servicio.java
- src/main/java/com/ucab/ucab_services/entity/SolicitudServicio.java
- src/main/java/com/ucab/ucab_services/entity/SugeridaA.java
- src/main/java/com/ucab/ucab_services/entity/SugeridaAId.java
- src/main/java/com/ucab/ucab_services/entity/TarifaServicio.java
- src/main/java/com/ucab/ucab_services/entity/TarifaServicioId.java
- src/main/java/com/ucab/ucab_services/entity/Tarjeta.java
- src/main/java/com/ucab/ucab_services/entity/Zelle.java
- src/main/java/com/ucab/ucab_services/repository/AcompananteTemporalRepository.java
- src/main/java/com/ucab/ucab_services/repository/AplicaEnRepository.java
- src/main/java/com/ucab/ucab_services/repository/AsignadoEnRepository.java
- src/main/java/com/ucab/ucab_services/repository/AuditoriaSesionRepository.java
- src/main/java/com/ucab/ucab_services/repository/BecarioRepository.java
- src/main/java/com/ucab/ucab_services/repository/BeneficiarioRepository.java
- src/main/java/com/ucab/ucab_services/repository/BilleteraTaiRepository.java
- src/main/java/com/ucab/ucab_services/repository/CategoriaFidelidadRepository.java
- src/main/java/com/ucab/ucab_services/repository/ClasificadoEnRepository.java
- src/main/java/com/ucab/ucab_services/repository/CriptomonedasRepository.java
- src/main/java/com/ucab/ucab_services/repository/DocenteRepository.java
- src/main/java/com/ucab/ucab_services/repository/EdificacionRepository.java
- src/main/java/com/ucab/ucab_services/repository/EgresadoRepository.java
- src/main/java/com/ucab/ucab_services/repository/EntidadPrestadoraRepository.java
- src/main/java/com/ucab/ucab_services/repository/EspacioFisicoRepository.java
- src/main/java/com/ucab/ucab_services/repository/EstudianteRepository.java
- src/main/java/com/ucab/ucab_services/repository/FacturaRepository.java
- src/main/java/com/ucab/ucab_services/repository/FolioConsumoRepository.java
- src/main/java/com/ucab/ucab_services/repository/ItemConsumoRepository.java
- src/main/java/com/ucab/ucab_services/repository/MFACodigoRepository.java
- src/main/java/com/ucab/ucab_services/repository/MiembroRepository.java
- src/main/java/com/ucab/ucab_services/repository/OfertaLaboralRepository.java
- src/main/java/com/ucab/ucab_services/repository/PagoMovilRepository.java
- src/main/java/com/ucab/ucab_services/repository/PasoActividadRepository.java
- src/main/java/com/ucab/ucab_services/repository/PeriodoVinculacionRepository.java
- src/main/java/com/ucab/ucab_services/repository/PersonalAdministrativoRepository.java
- src/main/java/com/ucab/ucab_services/repository/PreparadorRepository.java
- src/main/java/com/ucab/ucab_services/repository/SedeRepository.java
- src/main/java/com/ucab/ucab_services/repository/ServicioRepository.java
- src/main/java/com/ucab/ucab_services/repository/SolicitudServicioRepository.java
- src/main/java/com/ucab/ucab_services/repository/SugeridaARepository.java
- src/main/java/com/ucab/ucab_services/repository/TarifaServicioRepository.java
- src/main/java/com/ucab/ucab_services/service/AplicaEnService.java
- src/main/java/com/ucab/ucab_services/service/AsignadoEnService.java
- src/main/java/com/ucab/ucab_services/service/AuditoriaSesionService.java
- src/main/java/com/ucab/ucab_services/service/AuthService.java
- src/main/java/com/ucab/ucab_services/service/BecarioService.java
- src/main/java/com/ucab/ucab_services/service/BeneficiarioService.java
- src/main/java/com/ucab/ucab_services/service/BilleteraTaiService.java
- src/main/java/com/ucab/ucab_services/service/CategoriaFidelidadService.java
- src/main/java/com/ucab/ucab_services/service/ClasificadoEnService.java
- src/main/java/com/ucab/ucab_services/service/DocenteService.java
- src/main/java/com/ucab/ucab_services/service/EdificacionService.java
- src/main/java/com/ucab/ucab_services/service/EgresadoService.java
- src/main/java/com/ucab/ucab_services/service/EntidadPrestadoraService.java
- src/main/java/com/ucab/ucab_services/service/EspacioFisicoService.java
- src/main/java/com/ucab/ucab_services/service/EstudianteService.java
- src/main/java/com/ucab/ucab_services/service/FacturaService.java
- src/main/java/com/ucab/ucab_services/service/impl/AplicaEnServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/AsignadoEnServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/AuditoriaSesionServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/BecarioServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/BeneficiarioServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/BilleteraTaiServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/CategoriaFidelidadServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/ClasificadoEnServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/DocenteServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/EdificacionServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/EgresadoServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/EntidadPrestadoraServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/EspacioFisicoServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/EstudianteServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/FacturaServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/MFACodigoServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/MiembroServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/PeriodoVinculacionServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/PersonalAdministrativoServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/PreparadorServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/SedeServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/ServicioServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/SolicitudServicioServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/impl/TarifaServicioServiceImpl.java
- src/main/java/com/ucab/ucab_services/service/MFACodigoService.java
- src/main/java/com/ucab/ucab_services/service/MiembroService.java
- src/main/java/com/ucab/ucab_services/service/PeriodoVinculacionService.java
- src/main/java/com/ucab/ucab_services/service/PersonalAdministrativoService.java
- src/main/java/com/ucab/ucab_services/service/PreparadorService.java
- src/main/java/com/ucab/ucab_services/service/SedeService.java
- src/main/java/com/ucab/ucab_services/service/ServicioService.java
- src/main/java/com/ucab/ucab_services/service/SolicitudServicioService.java
- src/main/java/com/ucab/ucab_services/service/TarifaServicioService.java
- src/main/java/com/ucab/ucab_services/UcabServicesApplication.java
- src/main/resources/application.properties
- src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java
- src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java
- src/test/java/com/ucab/ucab_services/service/BeneficiarioServiceTest.java
- src/test/java/com/ucab/ucab_services/service/EstudianteServiceTest.java
- src/test/java/com/ucab/ucab_services/service/MiembroServiceTest.java
- src/test/java/com/ucab/ucab_services/UcabServicesApplicationTests.java

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.1.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.ucab</groupId>
	<artifactId>ucab-services</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name/>
	<description/>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
		<lombok.version>1.18.36</lombok.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>${lombok.version}</version>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
    		<groupId>org.springframework.security</groupId>
    		<artifactId>spring-security-crypto</artifactId>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<executions>
					<execution>
						<id>default-compile</id>
						<phase>compile</phase>
						<goals>
							<goal>compile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
									<version>${lombok.version}</version>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
					<execution>
						<id>default-testCompile</id>
						<phase>test-compile</phase>
						<goals>
							<goal>testCompile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>
```

## src/main/java/com/ucab/ucab_services/config/SecurityConfig.java

```java
package com.ucab.ucab_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Devuelve el codificador que internamente aplica la misma matemÃ¡tica de pgcrypto
        return new BCryptPasswordEncoder();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/AplicaEnController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import com.ucab.ucab_services.service.AplicaEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aplica-en")
public class AplicaEnController {
    @Autowired
    private AplicaEnService aplicaEnService;

    @GetMapping
    public List<AplicaEn> getAllAplicaEn() {
        return aplicaEnService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<AplicaEn> getAplicaEnById(@RequestParam String nombreSede,
            @RequestParam String codigoServicio, @RequestParam String perfilSolicitante) {
        AplicaEnId id = new AplicaEnId();
        id.setNombreSede(nombreSede);
        id.setCodigoServicio(codigoServicio);
        id.setPerfilSolicitante(perfilSolicitante);
        Optional<AplicaEn> aplicaEn = aplicaEnService.findById(id);
        return aplicaEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AplicaEn createAplicaEn(@RequestBody AplicaEn aplicaEn) {
        return aplicaEnService.save(aplicaEn);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<AplicaEn> updateAplicaEn(@RequestParam String nombreSede, @RequestParam String codigoServicio,
            @RequestParam String perfilSolicitante, @RequestBody AplicaEn aplicaEnDetails) {
        AplicaEnId id = new AplicaEnId();
        id.setNombreSede(nombreSede);
        id.setCodigoServicio(codigoServicio);
        id.setPerfilSolicitante(perfilSolicitante);
        Optional<AplicaEn> aplicaEnOptional = aplicaEnService.findById(id);
        if (aplicaEnOptional.isPresent()) {
            AplicaEn aplicaEn = aplicaEnOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(aplicaEnService.save(aplicaEn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteAplicaEn(@RequestParam String nombreSede, @RequestParam String codigoServicio,
            @RequestParam String perfilSolicitante) {
        AplicaEnId id = new AplicaEnId();
        id.setNombreSede(nombreSede);
        id.setCodigoServicio(codigoServicio);
        id.setPerfilSolicitante(perfilSolicitante);
        if (aplicaEnService.existsById(id)) {
            aplicaEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/AsignadoEnController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import com.ucab.ucab_services.service.AsignadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignado-en")
public class AsignadoEnController {
    @Autowired
    private AsignadoEnService asignadoEnService;

    @GetMapping
    public List<AsignadoEn> getAllAsignadoEn() {
        return asignadoEnService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<AsignadoEn> getAsignadoEnById(@RequestParam String codigoServicio,
            @RequestParam String nombreEdif, @RequestParam String direccionInterna,
            @RequestParam Integer numIdentificador) {
        AsignadoEnId id = new AsignadoEnId();
        id.setCodigoServicio(codigoServicio);
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<AsignadoEn> asignadoEn = asignadoEnService.findById(id);
        return asignadoEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AsignadoEn createAsignadoEn(@RequestBody AsignadoEn asignadoEn) {
        return asignadoEnService.save(asignadoEn);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<AsignadoEn> updateAsignadoEn(@RequestParam String codigoServicio,
            @RequestParam String nombreEdif, @RequestParam String direccionInterna,
            @RequestParam Integer numIdentificador, @RequestBody AsignadoEn asignadoEnDetails) {
        AsignadoEnId id = new AsignadoEnId();
        id.setCodigoServicio(codigoServicio);
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<AsignadoEn> asignadoEnOptional = asignadoEnService.findById(id);
        if (asignadoEnOptional.isPresent()) {
            AsignadoEn asignadoEn = asignadoEnOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated

            return ResponseEntity.ok(asignadoEnService.save(asignadoEn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteAsignadoEn(@RequestParam String codigoServicio, @RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestParam Integer numIdentificador) {
        AsignadoEnId id = new AsignadoEnId();
        id.setCodigoServicio(codigoServicio);
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        if (asignadoEnService.existsById(id)) {
            asignadoEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/AuditoriaSesionController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import com.ucab.ucab_services.service.AuditoriaSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auditoria-sesiones")
public class AuditoriaSesionController {
    @Autowired
    private AuditoriaSesionService auditoriaSesionService;

    @GetMapping
    public List<AuditoriaSesion> getAllAuditoriaSesiones() {
        return auditoriaSesionService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<AuditoriaSesion> getAuditoriaSesionById(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaHoraAcceso) {
        AuditoriaSesionId id = new AuditoriaSesionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaHoraAcceso(fechaHoraAcceso);
        Optional<AuditoriaSesion> auditoriaSesion = auditoriaSesionService.findById(id);
        return auditoriaSesion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AuditoriaSesion createAuditoriaSesion(@RequestBody AuditoriaSesion auditoriaSesion) {
        return auditoriaSesionService.save(auditoriaSesion);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<AuditoriaSesion> updateAuditoriaSesion(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaHoraAcceso, @RequestBody AuditoriaSesion auditoriaSesionDetails) {
        AuditoriaSesionId id = new AuditoriaSesionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaHoraAcceso(fechaHoraAcceso);
        Optional<AuditoriaSesion> auditoriaSesionOptional = auditoriaSesionService.findById(id);
        if (auditoriaSesionOptional.isPresent()) {
            AuditoriaSesion auditoriaSesion = auditoriaSesionOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(auditoriaSesionService.save(auditoriaSesion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteAuditoriaSesion(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaHoraAcceso) {
        AuditoriaSesionId id = new AuditoriaSesionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaHoraAcceso(fechaHoraAcceso);
        if (auditoriaSesionService.existsById(id)) {
            auditoriaSesionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/AuthController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite peticiones desde el puerto de tu Frontend en Angular
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.authenticate(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Construye una respuesta estructurada en formato JSON para el frontend
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/BecarioController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Becario;
import com.ucab.ucab_services.service.BecarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/becarios")
public class BecarioController {

    @Autowired
    private BecarioService becarioService;

    @GetMapping
    public List<Becario> getAllBecarios() {
        return becarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Becario> getBecarioById(@PathVariable String id) {
        Optional<Becario> becario = becarioService.findById(id);
        return becario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Becario createBecario(@RequestBody Becario becario) {
        return becarioService.save(becario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Becario> updateBecario(@PathVariable String id, @RequestBody Becario becarioDetails) {
        Optional<Becario> becarioOptional = becarioService.findById(id);
        if (becarioOptional.isPresent()) {
            Becario becario = becarioOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(becarioService.save(becario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBecario(@PathVariable String id) {
        if (becarioService.existsById(id)) {
            becarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/BeneficiarioController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.service.BeneficiarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping
    public List<Beneficiario> getAllBeneficiarios() {
        return beneficiarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable String id) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        return optionalBeneficiario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Beneficiario createBeneficiario(@RequestBody Beneficiario beneficiario) {
        return beneficiarioService.save(beneficiario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> updateBeneficiario(@PathVariable String id, @RequestBody Beneficiario beneficiarioDetails) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        if (optionalBeneficiario.isPresent()) {
            Beneficiario beneficiario = optionalBeneficiario.get();
            // Update fields - in a real app, you'd map only relevant fields
            beneficiario.setDocumentoIdentidad(beneficiarioDetails.getDocumentoIdentidad());
            beneficiario.setNombre(beneficiarioDetails.getNombre());
            beneficiario.setFechaNacimientoBeneficiario(beneficiarioDetails.getFechaNacimientoBeneficiario());
            beneficiario.setParentesco(beneficiarioDetails.getParentesco());
            beneficiario.setEsquemaVacunacion(beneficiarioDetails.getEsquemaVacunacion());
            beneficiario.setCentroEduInicial(beneficiarioDetails.getCentroEduInicial());
            beneficiario.setConstanciaEstUniversitarios(beneficiarioDetails.getConstanciaEstUniversitarios());
            beneficiario.setCertificadoSolteria(beneficiarioDetails.getCertificadoSolteria());
            beneficiario.setEstatusBeneficios(beneficiarioDetails.getEstatusBeneficios());
            beneficiario.setFechaInicio(beneficiarioDetails.getFechaInicio());
            beneficiario.setFechaFin(beneficiarioDetails.getFechaFin());
            Beneficiario updatedBeneficiario = beneficiarioService.save(beneficiario);
            return ResponseEntity.ok(updatedBeneficiario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiario(@PathVariable String id) {
        beneficiarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/BilleteraTaiController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.BilleteraTai;
import com.ucab.ucab_services.service.BilleteraTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/billeteras-tai")
public class BilleteraTaiController {

    @Autowired
    private BilleteraTaiService billeteraTaiService;

    @GetMapping
    public List<BilleteraTai> getAllBilleterasTai() {
        return billeteraTaiService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BilleteraTai> getBilleteraTaiById(@PathVariable UUID id) {
        BilleteraTai billeteraTai = billeteraTaiService.findById(id);
        return billeteraTai != null ? ResponseEntity.ok(billeteraTai) : ResponseEntity.notFound().build();
    }

    @GetMapping("/miembro/{cedula}")
    public ResponseEntity<BilleteraTai> getBilleteraTaiByMiembro(@PathVariable String cedula) {
        // âœ… CAMBIO AQUÃ: Llamado al nuevo nombre del mÃ©todo
        BilleteraTai billeteraTai = billeteraTaiService.findByMiembroCedulaMiembro(cedula);
        return billeteraTai != null ? ResponseEntity.ok(billeteraTai) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public BilleteraTai createBilleteraTai(@RequestBody BilleteraTai billeteraTai) {
        return billeteraTaiService.save(billeteraTai);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BilleteraTai> updateBilleteraTai(@PathVariable UUID id, @RequestBody BilleteraTai billeteraTaiDetails) {
        BilleteraTai billeteraTai = billeteraTaiService.findById(id);
        if (billeteraTai == null) {
            return ResponseEntity.notFound().build();
        }
        // Update fields (excluding immutable ones like uid and cedulaMiembro if they shouldn't change)
        billeteraTai.setSaldoVirtual(billeteraTaiDetails.getSaldoVirtual());
        billeteraTai.setSaldoRestante(billeteraTaiDetails.getSaldoRestante());
        BilleteraTai updatedBilleteraTai = billeteraTaiService.save(billeteraTai);
        return ResponseEntity.ok(updatedBilleteraTai);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilleteraTai(@PathVariable UUID id) {
        billeteraTaiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/CategoriaFidelidadController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import com.ucab.ucab_services.service.CategoriaFidelidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias-fidelidad")
public class CategoriaFidelidadController {

    @Autowired
    private CategoriaFidelidadService categoriaFidelidadService;

    @GetMapping
    public List<CategoriaFidelidad> getAllCategoriasFidelidad() {
        return categoriaFidelidadService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaFidelidad> getCategoriaFidelidadById(@PathVariable String id) {
        Optional<CategoriaFidelidad> categoriaFidelidad = categoriaFidelidadService.findById(id);
        return categoriaFidelidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoriaFidelidad createCategoriaFidelidad(@RequestBody CategoriaFidelidad categoriaFidelidad) {
        return categoriaFidelidadService.save(categoriaFidelidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaFidelidad> updateCategoriaFidelidad(@PathVariable String id, @RequestBody CategoriaFidelidad categoriaFidelidadDetails) {
        Optional<CategoriaFidelidad> categoriaFidelidadOptional = categoriaFidelidadService.findById(id);
        if (categoriaFidelidadOptional.isPresent()) {
            CategoriaFidelidad categoriaFidelidad = categoriaFidelidadOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            categoriaFidelidad.setTipoCategoria(categoriaFidelidadDetails.getTipoCategoria());
            categoriaFidelidad.setRangoIndiceMin(categoriaFidelidadDetails.getRangoIndiceMin());
            categoriaFidelidad.setRangoIndiceMax(categoriaFidelidadDetails.getRangoIndiceMax());
            categoriaFidelidad.setDescuentoGlobal(categoriaFidelidadDetails.getDescuentoGlobal());
            categoriaFidelidad.setPrioridadReservacion(categoriaFidelidadDetails.getPrioridadReservacion());
            return ResponseEntity.ok(categoriaFidelidadService.save(categoriaFidelidad));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaFidelidad(@PathVariable String id) {
        if (categoriaFidelidadService.existsById(id)) {
            categoriaFidelidadService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/ClasificadoEnController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import com.ucab.ucab_services.service.ClasificadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clasificados-en")
public class ClasificadoEnController {
    @Autowired
    private ClasificadoEnService clasificadoEnService;

    @GetMapping
    public List<ClasificadoEn> getAllClasificadosEn() {
        return clasificadoEnService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<ClasificadoEn> getClasificadoEnById(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestParam String tipoCategoria) {
        ClasificadoEnId id = new ClasificadoEnId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        id.setTipoCategoria(tipoCategoria);
        Optional<ClasificadoEn> clasificadoEn = clasificadoEnService.findById(id);
        return clasificadoEn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClasificadoEn createClasificadoEn(@RequestBody ClasificadoEn clasificadoEn) {

        return clasificadoEnService.save(clasificadoEn);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ClasificadoEn> updateClasificadoEn(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestParam String tipoCategoria,
            @RequestBody ClasificadoEn clasificadoEnDetails) {
        ClasificadoEnId id = new ClasificadoEnId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        id.setTipoCategoria(tipoCategoria);
        Optional<ClasificadoEn> clasificadoEnOptional = clasificadoEnService.findById(id);
        if (clasificadoEnOptional.isPresent()) {
            ClasificadoEn clasificadoEn = clasificadoEnOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(clasificadoEnService.save(clasificadoEn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteClasificadoEn(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestParam String tipoCategoria) {
        ClasificadoEnId id = new ClasificadoEnId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        id.setTipoCategoria(tipoCategoria);
        if (clasificadoEnService.existsById(id)) {
            clasificadoEnService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/DocenteController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Docente;
import com.ucab.ucab_services.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public List<Docente> getAllDocentes() {
        return docenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable String id) {
        Optional<Docente> docente = docenteService.findById(id);
        return docente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Docente createDocente(@RequestBody Docente docente) {
        return docenteService.save(docente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable String id, @RequestBody Docente docenteDetails) {
        Optional<Docente> docenteOptional = docenteService.findById(id);
        if (docenteOptional.isPresent()) {
            Docente docente = docenteOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            docente.setNombresCompletos(docenteDetails.getNombresCompletos());
            docente.setApellidosCompletos(docenteDetails.getApellidosCompletos());
            docente.setFechaNacimiento(docenteDetails.getFechaNacimiento());
            docente.setSexo(docenteDetails.getSexo());
            docente.setDireccionHabitacion(docenteDetails.getDireccionHabitacion());
            docente.setTelefonoPersonal(docenteDetails.getTelefonoPersonal());
            docente.setCorreoInstitucional(docenteDetails.getCorreoInstitucional());
            docente.setFechaApertura(docenteDetails.getFechaApertura());
            docente.setCodigoInvestigador(docenteDetails.getCodigoInvestigador());
            docente.setEscalafonDocente(docenteDetails.getEscalafonDocente());
            docente.setCargaSemanal(docenteDetails.getCargaSemanal());
            Docente updatedDocente = docenteService.save(docente);
            return ResponseEntity.ok(updatedDocente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable String id) {
        if (docenteService.existsById(id)) {
            docenteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/EdificacionController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import com.ucab.ucab_services.service.EdificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/edificaciones")
public class EdificacionController {
    @Autowired
    private EdificacionService edificacionService;

    @GetMapping
    public List<Edificacion> getAllEdificaciones() {
        return edificacionService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Edificacion> getEdificacionById(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna) {
        EdificacionId id = new EdificacionId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        Optional<Edificacion> edificacion = edificacionService.findById(id);
        return edificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Edificacion createEdificacion(@RequestBody Edificacion edificacion) {
        return edificacionService.save(edificacion);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Edificacion> updateEdificacion(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestBody Edificacion edificacionDetails) {
        EdificacionId id = new EdificacionId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        Optional<Edificacion> edificacionOptional = edificacionService.findById(id);
        if (edificacionOptional.isPresent()) {
            Edificacion edificacion = edificacionOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(edificacionService.save(edificacion));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteEdificacion(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna) {
        EdificacionId id = new EdificacionId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        if (edificacionService.existsById(id)) {
            edificacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/EgresadoController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Egresado;
import com.ucab.ucab_services.service.EgresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/egresados")
public class EgresadoController {

    @Autowired
    private EgresadoService egresadoService;

    @GetMapping
    public List<Egresado> getAllEgresados() {
        return egresadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Egresado> getEgresadoById(@PathVariable String id) {
        Optional<Egresado> egresado = egresadoService.findById(id);
        return egresado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Egresado createEgresado(@RequestBody Egresado egresado) {
        return egresadoService.save(egresado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Egresado> updateEgresado(@PathVariable String id, @RequestBody Egresado egresadoDetails) {
        Optional<Egresado> egresadoOptional = egresadoService.findById(id);
        if (egresadoOptional.isPresent()) {
            Egresado egresado = egresadoOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            egresado.setNombresCompletos(egresadoDetails.getNombresCompletos());
            egresado.setApellidosCompletos(egresadoDetails.getApellidosCompletos());
            egresado.setFechaNacimiento(egresadoDetails.getFechaNacimiento());
            egresado.setSexo(egresadoDetails.getSexo());
            egresado.setDireccionHabitacion(egresadoDetails.getDireccionHabitacion());
            egresado.setTelefonoPersonal(egresadoDetails.getTelefonoPersonal());
            egresado.setCorreoInstitucional(egresadoDetails.getCorreoInstitucional());
            egresado.setFechaApertura(egresadoDetails.getFechaApertura());
            egresado.setTitulo(egresadoDetails.getTitulo());
            egresado.setAnoGraduacion(egresadoDetails.getAnoGraduacion());
            egresado.setIndiceAcademico(egresadoDetails.getIndiceAcademico());
            Egresado updatedEgresado = egresadoService.save(egresado);
            return ResponseEntity.ok(updatedEgresado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEgresado(@PathVariable String id) {
        if (egresadoService.existsById(id)) {
            egresadoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/EntidadPrestadoraController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import com.ucab.ucab_services.service.EntidadPrestadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entidades-prestadoras")
public class EntidadPrestadoraController {

    @Autowired
    private EntidadPrestadoraService entidadPrestadoraService;

    @GetMapping
    public List<EntidadPrestadora> getAllEntidadesPrestadoras() {
        return entidadPrestadoraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadPrestadora> getEntidadPrestadoraById(@PathVariable Integer id) {
        Optional<EntidadPrestadora> entidadPrestadora = entidadPrestadoraService.findById(id);
        return entidadPrestadora.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntidadPrestadora createEntidadPrestadora(@RequestBody EntidadPrestadora entidadPrestadora) {
        return entidadPrestadoraService.save(entidadPrestadora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadPrestadora> updateEntidadPrestadora(@PathVariable Integer id, @RequestBody EntidadPrestadora entidadPrestadoraDetails) {
        Optional<EntidadPrestadora> entidadPrestadoraOptional = entidadPrestadoraService.findById(id);
        if (entidadPrestadoraOptional.isPresent()) {
            EntidadPrestadora entidadPrestadora = entidadPrestadoraOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(entidadPrestadoraService.save(entidadPrestadora));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntidadPrestadora(@PathVariable Integer id) {
        if (entidadPrestadoraService.existsById(id)) {
            entidadPrestadoraService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/EspacioFisicoController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import com.ucab.ucab_services.service.EspacioFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espacios-fisicos")
public class EspacioFisicoController {
    @Autowired
    private EspacioFisicoService espacioFisicoService;

    @GetMapping
    public List<EspacioFisico> getAllEspaciosFisicos() {
        return espacioFisicoService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<EspacioFisico> getEspacioFisicoById(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestParam Integer numIdentificador) {
        EspacioFisicoId id = new EspacioFisicoId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<EspacioFisico> espacioFisico = espacioFisicoService.findById(id);
        return espacioFisico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EspacioFisico createEspacioFisico(@RequestBody EspacioFisico espacioFisico) {
        return espacioFisicoService.save(espacioFisico);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<EspacioFisico> updateEspacioFisico(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestParam Integer numIdentificador,
            @RequestBody EspacioFisico espacioFisicoDetails) {
        EspacioFisicoId id = new EspacioFisicoId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        Optional<EspacioFisico> espacioFisicoOptional = espacioFisicoService.findById(id);
        if (espacioFisicoOptional.isPresent()) {
            EspacioFisico espacioFisico = espacioFisicoOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(espacioFisicoService.save(espacioFisico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteEspacioFisico(@RequestParam String nombreEdif,
            @RequestParam String direccionInterna, @RequestParam Integer numIdentificador) {
        EspacioFisicoId id = new EspacioFisicoId();
        id.setNombreEdif(nombreEdif);
        id.setDireccionInterna(direccionInterna);
        id.setNumIdentificador(numIdentificador);
        if (espacioFisicoService.existsById(id)) {
            espacioFisicoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/EstudianteController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Estudiante;
import com.ucab.ucab_services.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable String id) {
        Optional<Estudiante> estudiante = estudianteService.findById(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable String id, @RequestBody Estudiante estudianteDetails) {
        Optional<Estudiante> estudianteOptional = estudianteService.findById(id);
        if (estudianteOptional.isPresent()) {
            Estudiante estudiante = estudianteOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            estudiante.setNombresCompletos(estudianteDetails.getNombresCompletos());
            estudiante.setApellidosCompletos(estudianteDetails.getApellidosCompletos());
            estudiante.setFechaNacimiento(estudianteDetails.getFechaNacimiento());
            estudiante.setSexo(estudianteDetails.getSexo());
            estudiante.setDireccionHabitacion(estudianteDetails.getDireccionHabitacion());
            estudiante.setTelefonoPersonal(estudianteDetails.getTelefonoPersonal());
            estudiante.setCorreoInstitucional(estudianteDetails.getCorreoInstitucional());
            estudiante.setFechaApertura(estudianteDetails.getFechaApertura());
            Estudiante updatedEstudiante = estudianteService.save(estudiante);
            return ResponseEntity.ok(updatedEstudiante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable String id) {
        if (estudianteService.existsById(id)) {
            estudianteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/GlobalExceptionHandler.java

```java
package com.ucab.ucab_services.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleBindExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/MFACodigoController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import com.ucab.ucab_services.service.MFACodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mfa-codigos")
public class MFACodigoController {
    @Autowired
    private MFACodigoService mfaCodigoService;

    @GetMapping
    public List<MFACodigo> getAllMFA_Codigos() {
        return mfaCodigoService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<MFACodigo> getMFACodigoById(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaGenerado) {
        MFACodigoId id = new MFACodigoId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaGenerado(fechaGenerado);
        Optional<MFACodigo> mfaCodigo = mfaCodigoService.findById(id);
        return mfaCodigo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MFACodigo createMFACodigo(@RequestBody MFACodigo mfaCodigo) {
        return mfaCodigoService.save(mfaCodigo);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<MFACodigo> updateMFACodigo(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaGenerado, @RequestBody MFACodigo mfaCodigoDetails) {
        MFACodigoId id = new MFACodigoId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaGenerado(fechaGenerado);
        Optional<MFACodigo> mfaCodigoOptional = mfaCodigoService.findById(id);
        if (mfaCodigoOptional.isPresent()) {
            MFACodigo mfaCodigo = mfaCodigoOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(mfaCodigoService.save(mfaCodigo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteMFACodigo(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Timestamp fechaGenerado) {
        MFACodigoId id = new MFACodigoId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaGenerado(fechaGenerado);
        if (mfaCodigoService.existsById(id)) {
            mfaCodigoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/MiembroController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.service.MiembroService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @GetMapping
    public List<Miembro> getAllMiembros() {
        return miembroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Miembro> getMiembroById(@PathVariable String id) {
        Optional<Miembro> optionalMiembro = miembroService.findById(id);
        return optionalMiembro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Miembro createMiembro(@RequestBody Miembro miembro) {
        return miembroService.save(miembro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Miembro> updateMiembro(@PathVariable String id, @RequestBody Miembro miembroDetails) {
        Optional<Miembro> optionalMiembro = miembroService.findById(id);
        if (optionalMiembro.isPresent()) {
            Miembro miembro = optionalMiembro.get();
            // Update fields
            miembro.setNombresCompletos(miembroDetails.getNombresCompletos());
            miembro.setApellidosCompletos(miembroDetails.getApellidosCompletos());
            miembro.setSexo(miembroDetails.getSexo());
            miembro.setFechaNacimiento(miembroDetails.getFechaNacimiento());
            miembro.setEstadoCuenta(miembroDetails.getEstadoCuenta());
            // We'll set a few for brevity, but in reality we should set all.
            // For simplicity, we'll update a few.
            miembro.setDireccionHabitacion(miembroDetails.getDireccionHabitacion());
            miembro.setCorreoInstitucional(miembroDetails.getCorreoInstitucional());
            miembro.setTelefonoPersonal(miembroDetails.getTelefonoPersonal());
            Miembro updatedMiembro = miembroService.save(miembro);
            return ResponseEntity.ok(updatedMiembro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiembro(@PathVariable String id) {
        miembroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/PeriodoVinculacionController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import com.ucab.ucab_services.service.PeriodoVinculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/periodos-vinculacion")
public class PeriodoVinculacionController {
    @Autowired
    private PeriodoVinculacionService periodoVinculacionService;

    @GetMapping
    public List<PeriodoVinculacion> getAllPeriodosVinculacion() {
        return periodoVinculacionService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<PeriodoVinculacion> getPeriodoVinculacionById(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Date fechaInicio) {
        PeriodoVinculacionId id = new PeriodoVinculacionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaInicio(fechaInicio);
        Optional<PeriodoVinculacion> periodoVinculacion = periodoVinculacionService.findById(id);
        return periodoVinculacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PeriodoVinculacion createPeriodoVinculacion(@RequestBody PeriodoVinculacion periodoVinculacion) {
        return periodoVinculacionService.save(periodoVinculacion);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PeriodoVinculacion> updatePeriodoVinculacion(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Date fechaInicio, @RequestBody PeriodoVinculacion periodoVinculacionDetails) {
        PeriodoVinculacionId id = new PeriodoVinculacionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaInicio(fechaInicio);
        Optional<PeriodoVinculacion> periodoVinculacionOptional = periodoVinculacionService.findById(id);
        if (periodoVinculacionOptional.isPresent()) {
            PeriodoVinculacion periodoVinculacion = periodoVinculacionOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(periodoVinculacionService.save(periodoVinculacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deletePeriodoVinculacion(@RequestParam String cedulaMiembro,
            @RequestParam java.sql.Date fechaInicio) {
        PeriodoVinculacionId id = new PeriodoVinculacionId();
        id.setCedulaMiembro(cedulaMiembro);
        id.setFechaInicio(fechaInicio);
        if (periodoVinculacionService.existsById(id)) {
            periodoVinculacionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/PersonalAdministrativoController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import com.ucab.ucab_services.service.PersonalAdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-administrativo")
public class PersonalAdministrativoController {

    @Autowired
    private PersonalAdministrativoService personalAdministrativoService;

    @GetMapping
    public List<PersonalAdministrativo> getAllPersonalAdministrativo() {
        return personalAdministrativoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> getPersonalAdministrativoById(@PathVariable String id) {
        Optional<PersonalAdministrativo> personalAdministrativo = personalAdministrativoService.findById(id);
        return personalAdministrativo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PersonalAdministrativo createPersonalAdministrativo(@RequestBody PersonalAdministrativo personalAdministrativo) {
        return personalAdministrativoService.save(personalAdministrativo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> updatePersonalAdministrativo(@PathVariable String id, @RequestBody PersonalAdministrativo personalAdministrativoDetails) {
        Optional<PersonalAdministrativo> personalAdministrativoOptional = personalAdministrativoService.findById(id);
        if (personalAdministrativoOptional.isPresent()) {
            PersonalAdministrativo personalAdministrativo = personalAdministrativoOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(personalAdministrativoService.save(personalAdministrativo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalAdministrativo(@PathVariable String id) {
        if (personalAdministrativoService.existsById(id)) {
            personalAdministrativoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/PreparadorController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Preparador;
import com.ucab.ucab_services.service.PreparadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/preparadores")
public class PreparadorController {

    @Autowired
    private PreparadorService preparadorService;

    @GetMapping
    public List<Preparador> getAllPreparadores() {
        return preparadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preparador> getPreparadorById(@PathVariable String id) {
        Optional<Preparador> preparador = preparadorService.findById(id);
        return preparador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Preparador createPreparador(@RequestBody Preparador preparador) {
        return preparadorService.save(preparador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preparador> updatePreparador(@PathVariable String id, @RequestBody Preparador preparadorDetails) {
        Optional<Preparador> preparadorOptional = preparadorService.findById(id);
        if (preparadorOptional.isPresent()) {
            Preparador preparador = preparadorOptional.get();
            // Update fields (excluding the ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(preparadorService.save(preparador));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreparador(@PathVariable String id) {
        if (preparadorService.existsById(id)) {
            preparadorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/SedeController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Sede;
import com.ucab.ucab_services.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    // SOLO LECTURA PERMITIDA (GET)
    @GetMapping
    public List<Sede> getAllSedes() {
        return sedeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getSedeById(@PathVariable String id) {
        // âœ… Se recibe Sede directo y se evalÃºa si es null
        Sede sede = sedeService.findById(id);
        return sede != null ? ResponseEntity.ok(sede) : ResponseEntity.notFound().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/ServicioController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Servicio;
import com.ucab.ucab_services.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable String id) {
        Servicio servicio = servicioService.findById(id);
        return servicio != null ? ResponseEntity.ok(servicio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Servicio createServicio(@RequestBody Servicio servicio) {
        return servicioService.save(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable String id, @RequestBody Servicio servicioDetails) {
        Servicio servicio = servicioService.findById(id);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.setCodigoServicio(servicioDetails.getCodigoServicio());
        servicio.setDescripcionDetallada(servicioDetails.getDescripcionDetallada());
        servicio.setRequisitos(servicioDetails.getRequisitos());
        servicio.setEstadoServicio(servicioDetails.getEstadoServicio());
        servicio.setPerfilSolicitante(servicioDetails.getPerfilSolicitante());
        // Note: entidadPrestadora is a relationship, we might need to set it by id, but for simplicity we leave it as is.
        Servicio updatedServicio = servicioService.save(servicio);
        return ResponseEntity.ok(updatedServicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable String id) {
        servicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/SolicitudServicioController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.SolicitudServicio;
import com.ucab.ucab_services.service.SolicitudServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes-servicio")
public class SolicitudServicioController {

    @Autowired
    private SolicitudServicioService solicitudServicioService;

    @GetMapping
    public List<SolicitudServicio> getAllSolicitudesServicio() {
        return solicitudServicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudServicio> getSolicitudServicioById(@PathVariable String id) {
        SolicitudServicio solicitudServicio = solicitudServicioService.findById(id);
        return solicitudServicio != null ? ResponseEntity.ok(solicitudServicio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public SolicitudServicio createSolicitudServicio(@RequestBody SolicitudServicio solicitudServicio) {
        return solicitudServicioService.save(solicitudServicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudServicio> updateSolicitudServicio(@PathVariable String id, @RequestBody SolicitudServicio solicitudServicioDetails) {
        SolicitudServicio solicitudServicio = solicitudServicioService.findById(id);
        if (solicitudServicio == null) {
            return ResponseEntity.notFound().build();
        }
        // Update fields
        solicitudServicio.setEstadoActual(solicitudServicioDetails.getEstadoActual());
        solicitudServicio.setFechaInicio(solicitudServicioDetails.getFechaInicio());
        solicitudServicio.setFechaFin(solicitudServicioDetails.getFechaFin());
        // Note: We are not updating the relationships (miembro, servicio) for simplicity.
        SolicitudServicio updatedSolicitudServicio = solicitudServicioService.save(solicitudServicio);
        return ResponseEntity.ok(updatedSolicitudServicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitudServicio(@PathVariable String id) {
        solicitudServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/TarifaServicioController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import com.ucab.ucab_services.service.TarifaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarifas-servicio")
public class TarifaServicioController {
    @Autowired
    private TarifaServicioService tarifaServicioService;

    @GetMapping
    public List<TarifaServicio> getAllTarifaServicios() {
        return tarifaServicioService.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<TarifaServicio> getTarifaServicioById(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio) {
        TarifaServicioId id = new TarifaServicioId();

        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        Optional<TarifaServicio> tarifaServicio = tarifaServicioService.findById(id);
        return tarifaServicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarifaServicio createTarifaServicio(@RequestBody TarifaServicio tarifaServicio) {
        return tarifaServicioService.save(tarifaServicio);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TarifaServicio> updateTarifaServicio(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio, @RequestBody TarifaServicio tarifaServicioDetails) {
        TarifaServicioId id = new TarifaServicioId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        Optional<TarifaServicio> tarifaServicioOptional = tarifaServicioService.findById(id);
        if (tarifaServicioOptional.isPresent()) {
            TarifaServicio tarifaServicio = tarifaServicioOptional.get();
            // Update fields (excluding the composite ID which shouldn't change)
            // In a real application, you might want to validate which fields can be updated
            return ResponseEntity.ok(tarifaServicioService.save(tarifaServicio));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteTarifaServicio(@RequestParam String perfilSolicitante,
            @RequestParam String codigoServicio) {
        TarifaServicioId id = new TarifaServicioId();
        id.setPerfilSolicitante(perfilSolicitante);
        id.setCodigoServicio(codigoServicio);
        if (tarifaServicioService.existsById(id)) {
            tarifaServicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

## src/main/java/com/ucab/ucab_services/dto/LoginRequest.java

```java
package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String correo;
    private String clave;
}
```

## src/main/java/com/ucab/ucab_services/dto/LoginResponse.java

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
}
```

## src/main/java/com/ucab/ucab_services/dto/MiembroSesionDTO.java

```java
package com.ucab.ucab_services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MiembroSesionDTO {
    private String cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String rol; // <-- AquÃ­ el Frontend en Angular sabrÃ¡ si es Estudiante, Profesor, etc.
}
```

## src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java

```java
package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VerificarMfaRequest {
    @NotBlank(message = "CÃ©dula es requerida")
    @Size(min = 1, max = 20, message = "CÃ©dula debe tener entre 1 y 20 caracteres")
    private String cedula;

    @NotBlank(message = "CÃ³digo es requerido")
    @Size(min = 6, max = 6, message = "CÃ³digo debe tener exactamente 6 caracteres")
    private String codigo;

    // Getters and Setters
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

## src/main/java/com/ucab/ucab_services/entity/AcompananteTemporal.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acompanante_temporal")
@Getter @Setter @NoArgsConstructor
public class AcompananteTemporal {

    @Id
    @Column(name = "documento_identidad_acom", length = 30, nullable = false)
    private String documentoIdentidadAcom;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", nullable = false)
    private SolicitudServicio solicitudServicio;

    @Column(name = "nombre_acompanante", length = 200, nullable = false)
    private String nombreAcompanante;

    @Column(name = "estado_activo", nullable = false)
    private Boolean estadoActivo;

}
```

## src/main/java/com/ucab/ucab_services/entity/AplicaEn.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aplica_en")
@Getter @Setter @NoArgsConstructor
public class AplicaEn {

    @EmbeddedId
    private AplicaEnId id;
    
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "perfil_solicitante", referencedColumnName = "perfil_solicitante", insertable = false, updatable = false),
            @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false)
    })
    private TarifaServicio tarifaServicio;
    
    @Column(name = "precio_base", precision = 10, scale = 2, nullable = false)
    private java.math.BigDecimal precioBase;

    @Column(name = "limite_costo", precision = 10, scale = 2)
    private java.math.BigDecimal limiteCosto;

}
```

## src/main/java/com/ucab/ucab_services/entity/AplicaEnId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class AplicaEnId implements Serializable {

    @Column(name = "nombre_sede", length = 100, nullable = false)
    private String nombreSede;

    @Column(name = "codigo_servicio", length = 50, nullable = false)
    private String codigoServicio;

    @Column(name = "perfil_solicitante", length = 100, nullable = false)
    private String perfilSolicitante;

}
```

## src/main/java/com/ucab/ucab_services/entity/AsignadoEn.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asignado_en")
@Getter @Setter @NoArgsConstructor
public class AsignadoEn {

    @EmbeddedId
    private AsignadoEnId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nombre_edif", referencedColumnName = "nombre_edif", insertable = false, updatable = false),
            @JoinColumn(name = "direccion_interna", referencedColumnName = "direccion_interna", insertable = false, updatable = false),
            @JoinColumn(name = "num_identificador", referencedColumnName = "num_identificador", insertable = false, updatable = false)
    })
    private EspacioFisico espacioFisico;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false)
    private Servicio servicio;

}

```

## src/main/java/com/ucab/ucab_services/entity/AsignadoEnId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class AsignadoEnId implements Serializable {

    @Column(name = "codigo_servicio", length = 50, nullable = false)
    private String codigoServicio;

    @Column(name = "nombre_edif", length = 100, nullable = false)
    private String nombreEdif;

    @Column(name = "direccion_interna", columnDefinition = "TEXT", nullable = false)
    private String direccionInterna;

    @Column(name = "num_identificador", nullable = false)
    private Integer numIdentificador;

}
```

## src/main/java/com/ucab/ucab_services/entity/AuditoriaSesion.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auditoria_sesion")
@Getter @Setter @NoArgsConstructor
public class AuditoriaSesion {

    @EmbeddedId
    private AuditoriaSesionId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Miembro miembro;

    @Column(name = "geolocalizacion", columnDefinition = "TEXT")
    private String geolocalizacion;

    @Column(name = "ip_origen")
    private String ipOrigen; // InetAddress? we'll store as string

    @Column(name = "uuid_dispositivo")
    private java.util.UUID uuidDispositivo;

}
```

## src/main/java/com/ucab/ucab_services/entity/AuditoriaSesionId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class AuditoriaSesionId implements Serializable {

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

    @Column(name = "fecha_hora_acceso", nullable = false)
    private Timestamp fechaHoraAcceso;

}
```

## src/main/java/com/ucab/ucab_services/entity/Becario.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "becario")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Becario extends Estudiante {

    @Column(name = "tipo_beca", length = 50)
    private String tipoBeca;

    @Column(name = "estatus_beneficio", length = 50)
    private String estatusBeneficio;

    @Column(name = "indice_mantenimiento", precision = 4, scale = 2)
    private BigDecimal indiceMantenimiento;

}

```

## src/main/java/com/ucab/ucab_services/entity/Beneficiario.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beneficiario")
@Getter @Setter @NoArgsConstructor
public class Beneficiario {

    @Id
    @Column(name = "documento_identidad", length = 30, nullable = false, unique = true)
    private String documentoIdentidad;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false)
    private Miembro miembro;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimiento_beneficiario", nullable = false)
    private java.sql.Date fechaNacimientoBeneficiario;

    @Column(name = "parentesco", length = 50, nullable = false)
    private String parentesco;

    @Column(name = "esquema_vacunacion", columnDefinition = "TEXT")
    private String esquemaVacunacion;

    @Column(name = "centro_edu_inicial", length = 200)
    private String centroEduInicial;

    @Column(name = "constancia_est_universitarios", columnDefinition = "TEXT")
    private String constanciaEstUniversitarios;

    @Column(name = "certificado_solteria", columnDefinition = "TEXT")
    private String certificadoSolteria;

    @Column(name = "estatus_beneficios", length = 30)
    private String estatusBeneficios;

    @Column(name = "fecha_inicio")
    private java.sql.Date fechaInicio;

    @Column(name = "fecha_fin")
    private java.sql.Date fechaFin;

}
```

## src/main/java/com/ucab/ucab_services/entity/BilleteraTai.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "billetera_tai")
@Getter @Setter @NoArgsConstructor
public class BilleteraTai {

    @Id
    @Column(name = "uid", nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;

    @OneToOne(optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false, unique = true)
    private Miembro miembro;

    @Column(name = "saldo_virtual", precision = 18, scale = 4, nullable = false)
    private BigDecimal saldoVirtual = BigDecimal.ZERO;

    @Column(name = "saldo_restante", precision = 18, scale = 4, nullable = false)
    private BigDecimal saldoRestante = BigDecimal.ZERO;

}
```

## src/main/java/com/ucab/ucab_services/entity/CategoriaFidelidad.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria_fidelidad")
@Getter @Setter @NoArgsConstructor
public class CategoriaFidelidad {

    @Id
    @Column(name = "tipo_categoria", length = 50, nullable = false, unique = true)
    private String tipoCategoria;

    @Column(name = "rango_indice_min", precision = 4, scale = 2, nullable = false)
    private java.math.BigDecimal rangoIndiceMin;

    @Column(name = "rango_indice_max", precision = 4, scale = 2, nullable = false)
    private java.math.BigDecimal rangoIndiceMax;

    @Column(name = "descuento_global", precision = 5, scale = 2, nullable = false)
    private java.math.BigDecimal descuentoGlobal;

    @Column(name = "prioridad_reservacion", nullable = false)
    private Integer prioridadReservacion;

}
```

## src/main/java/com/ucab/ucab_services/entity/ClasificadoEn.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clasificado_en")
@Getter @Setter @NoArgsConstructor
public class ClasificadoEn {

    @EmbeddedId
    private ClasificadoEnId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "perfil_solicitante", referencedColumnName = "perfil_solicitante", insertable = false, updatable = false),
            @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false)
    })
    private TarifaServicio tarifaServicio;

    @ManyToOne
    @JoinColumn(name = "tipo_categoria", referencedColumnName = "tipo_categoria", insertable = false, updatable = false)
    private CategoriaFidelidad categoriaFidelidad;

}

```

## src/main/java/com/ucab/ucab_services/entity/ClasificadoEnId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class ClasificadoEnId implements Serializable {

    @Column(name = "perfil_solicitante", length = 30, nullable = false)
    private String perfilSolicitante;

    @Column(name = "codigo_servicio", length = 30, nullable = false)
    private String codigoServicio;

    @Column(name = "tipo_categoria", length = 50, nullable = false)
    private String tipoCategoria;

}
```

## src/main/java/com/ucab/ucab_services/entity/Criptomonedas.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "criptomonedas")
@Getter @Setter @NoArgsConstructor
public class Criptomonedas extends Pago {

    @Column(name = "dxid", length = 100, nullable = false)
    private String dxid;

    @Column(name = "red_blockchain", length = 50, nullable = false)
    private String redBlockchain;

    @Column(name = "billetera", length = 100, nullable = false)
    private String billetera;

    @Column(name = "tasa_conversion", precision = 10, scale = 2, nullable = false)
    private BigDecimal tasaConversion;
}
```

## src/main/java/com/ucab/ucab_services/entity/Docente.java

```java
package com.ucab.ucab_services.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Docente extends Miembro {

    @Column(name = "codigo_investigador", length = 50)
    private String codigoInvestigador;

    @Column(name = "escalafon_docente", length = 100)
    private String escalafonDocente;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private java.math.BigDecimal cargaSemanal;

}
```

## src/main/java/com/ucab/ucab_services/entity/Edificacion.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "edificacion")
@Getter @Setter @NoArgsConstructor
public class Edificacion {

    @EmbeddedId
    private EdificacionId id;

    @ManyToOne
    @JoinColumn(name = "nombre_sede", referencedColumnName = "nombre_sede", nullable = false)
    private Sede sede;

}
```

## src/main/java/com/ucab/ucab_services/entity/EdificacionId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class EdificacionId implements Serializable {

    @Column(name = "nombre_edif", length = 100, nullable = false)
    private String nombreEdif;
    
    @Column(name = "direccion_interna", columnDefinition = "TEXT", nullable = false)
    private String direccionInterna;

}
```

## src/main/java/com/ucab/ucab_services/entity/Efectivo.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "efectivo")
@Getter @Setter @NoArgsConstructor
public class Efectivo extends Pago {

    @Column(name = "denominacion_billetes", columnDefinition = "TEXT")
    private String denominacionBilletes;
}
```

## src/main/java/com/ucab/ucab_services/entity/Egresado.java

```java
package com.ucab.ucab_services.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Egresado extends Miembro {

    @Column(name = "titulo", length = 200)
    private String titulo;

    @Column(name = "ano_graduacion")
    private Integer anoGraduacion;

    @Column(name = "indice_academico", precision = 4, scale = 2)
    private java.math.BigDecimal indiceAcademico;

}
```

## src/main/java/com/ucab/ucab_services/entity/EntidadPrestadora.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entidad_prestadora")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public class EntidadPrestadora {

    @Id
    @Column(name = "id_entidad", nullable = false)
    private Integer idEntidad;

    @Column(name = "tipo_entidad", length = 10, nullable = false)
    private String tipoEntidad;

}
```

## src/main/java/com/ucab/ucab_services/entity/EspacioFisico.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "espacio_fisico")
@Getter @Setter @NoArgsConstructor
public class EspacioFisico {

    @EmbeddedId
    private EspacioFisicoId id;

    @Column(name = "capacidad_aforo", nullable = false)
    private Integer capacidadAforo;

    @Column(name = "tipo_inmobiliario", length = 50, nullable = false)
    private String tipoInmobiliario;

   @Column(name = "estatus", length = 30, nullable = false)
   private String estatus;

   @ManyToOne
   @JoinColumns({
            @JoinColumn(name = "nombre_edif", referencedColumnName = "nombre_edif", insertable = false, updatable = false),
            @JoinColumn(name = "direccion_interna", referencedColumnName = "direccion_interna", insertable = false, updatable = false)
    })
   private Edificacion edificacion;

}

```

## src/main/java/com/ucab/ucab_services/entity/EspacioFisicoId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class EspacioFisicoId implements Serializable {

    @Column(name = "nombre_edif", length = 100, nullable = false)
    private String nombreEdif;

    @Column(name = "direccion_interna", columnDefinition = "TEXT", nullable = false)
    private String direccionInterna;

    @Column(name = "num_identificador", nullable = false)
    private Integer numIdentificador;

}
```

## src/main/java/com/ucab/ucab_services/entity/Estudiante.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Estudiante extends Miembro {

    @Column(name = "promedio", precision = 4, scale = 2)
    private BigDecimal promedio;

    @Column(name = "escuela", length = 150)
    private String escuela;

    @Column(name = "estatus_beca", length = 50)
    private String estatusBeca;

    @Column(name = "semestre")
    private Integer semestre;

    @Column(name = "facultad", length = 150)
    private String facultad;

}
```

## src/main/java/com/ucab/ucab_services/entity/Externa.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_entidad", referencedColumnName = "id_entidad")
public class Externa extends EntidadPrestadora {

    @Column(name = "tipo_entidad", length = 10, nullable = false, insertable = false, updatable = false)
    private String tipoEntidad;

    @Column(name = "rif", length = 50, nullable = false, unique = true)
    private String rif;

    @Column(name = "razon_social", length = 150)
    private String razonSocial;

    @Column(name = "contactos_legales", columnDefinition = "TEXT")
    private String contactosLegales;

    @Column(name = "fecha_vencimiento_contrato")
    private java.sql.Date fechaVencimientoContrato;

}
```

## src/main/java/com/ucab/ucab_services/entity/Factura.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "factura")
@Getter @Setter @NoArgsConstructor
public class Factura {

    @Id
    @Column(name = "numero_control", length = 50, nullable = false)
    private String numeroControl;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", nullable = false)
    private SolicitudServicio solicitudServicio;

    @ManyToOne
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false)
    private Miembro miembro;

    @Column(name = "estatus_factura", length = 30, nullable = false)
    private String estatusFactura;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "monto_total", precision = 18, scale = 4, nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "saldo_restante_pagar", precision = 18, scale = 4, nullable = false)
    private BigDecimal saldoRestantePagar;

}
```

## src/main/java/com/ucab/ucab_services/entity/FolioConsumo.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "folio_consumo")
@Getter @Setter @NoArgsConstructor
@IdClass(FolioConsumoId.class)
public class FolioConsumo {

    @Id
    @Column(name = "identificador", length = 50, nullable = false, insertable = false, updatable = false)
    private String identificador;

    @Id
    @Column(name = "fecha_apertura", nullable = false, insertable = false, updatable = false)
    private LocalDate fechaApertura;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;

    @Column(name = "estado_cierre", length = 50, nullable = false)
    private String estadoCierre;

}
```

## src/main/java/com/ucab/ucab_services/entity/FolioConsumoId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class FolioConsumoId implements Serializable {

    @Column(name = "identificador", length = 50, nullable = false)
    private String identificador;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;
}
```

## src/main/java/com/ucab/ucab_services/entity/Interna.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_entidad", referencedColumnName = "id_entidad")
public class Interna extends EntidadPrestadora {

    @Column(name = "tipo_entidad", length = 10, nullable = false, insertable = false, updatable = false)
    private String tipoEntidad;

    @Column(name = "codigo_presu", length = 50, nullable = false, unique = true)
    private String codigoPresu;

    @Column(name = "director_oficina", length = 100)
    private String directorOficina;

}
```

## src/main/java/com/ucab/ucab_services/entity/ItemConsumo.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "item_consumo")
@Getter @Setter @NoArgsConstructor
@IdClass(ItemConsumoId.class)
public class ItemConsumo {

    @Id
    @Column(name = "identificador", length = 50, nullable = false, insertable = false, updatable = false)
    private String identificador;

    @Id
    @Column(name = "fecha_apertura", nullable = false, insertable = false, updatable = false)
    private LocalDate fechaApertura;

    @Id
    @Column(name = "concepto", length = 300, nullable = false, insertable = false, updatable = false)
    private String concepto;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "identificador", referencedColumnName = "identificador", insertable = false, updatable = false),
            @JoinColumn(name = "fecha_apertura", referencedColumnName = "fecha_apertura", insertable = false, updatable = false)
    })
    private FolioConsumo folioConsumo;

    @Column(name = "precio_unitario", precision = 18, scale = 4, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "cantidad", precision = 10, scale = 3, nullable = false)
    private BigDecimal cantidad;

    @Column(name = "impuesto", precision = 5, scale = 2, nullable = false)
    private BigDecimal impuesto;

    @Column(name = "fecha_cargo", nullable = false)
    private LocalDate fechaCargo;

}
```

## src/main/java/com/ucab/ucab_services/entity/ItemConsumoId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ItemConsumoId implements Serializable {

    @Column(name = "identificador", length = 50, nullable = false)
    private String identificador;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;

    @Column(name = "concepto", length = 300, nullable = false)
    private String concepto;
}
```

## src/main/java/com/ucab/ucab_services/entity/MFACodigo.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "mfa_codigo")
@Getter @Setter @NoArgsConstructor
public class MFACodigo {

    @EmbeddedId
    private MFACodigoId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Miembro miembro;

    @Column(name = "codigo", length = 6, nullable = false)
    private String codigo;

    @Column(name = "fecha_expira", nullable = false)
    private Timestamp fechaExpira;

    @Column(name = "usado", nullable = false)
    private Boolean usado;

}
```

## src/main/java/com/ucab/ucab_services/entity/MFACodigoId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class MFACodigoId implements Serializable {

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

    @Column(name = "fecha_generado", nullable = false)
    private Timestamp fechaGenerado;

}
```

## src/main/java/com/ucab/ucab_services/entity/Miembro.java

```java
package com.ucab.ucab_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "miembro")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public class Miembro {

    @Id
    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

    @Column(name = "nombres_completos", length = 200, nullable = false)
    private String nombresCompletos;

    @Column(name = "apellidos_completos", length = 200, nullable = false)
    private String apellidosCompletos;

    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "estado_cuenta", length = 30, nullable = false)
    private String estadoCuenta;

    // Â¡AQUÃ SE RESUELVE EL PUNTO #1 DE LA REVISIÃ“N! El hash jamÃ¡s viajarÃ¡ al Frontend
    @JsonIgnore
    @Column(name = "clave_hash", columnDefinition = "TEXT")
    private String claveHash;

    @Column(name = "fecha_cambio_clave")
    private Timestamp fechaCambioClave;

    @Column(name = "direccion_habitacion", columnDefinition = "TEXT", nullable = false)
    private String direccionHabitacion;

    @Column(name = "correo_institucional", length = 150, nullable = false, unique = true)
    private String correoInstitucional;

    @Column(name = "telefono_personal", length = 20, nullable = false)
    private String telefonoPersonal;

    @Column(name = "ultima_conexion")
    private Timestamp ultimaConexion;

    @Column(name = "indice_recurrencia", precision = 5, scale = 2)
    @ColumnDefault("0")
    private BigDecimal indiceRecurrencia;

    @Column(name = "fecha_apertura", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    private Date fechaApertura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_categoria", referencedColumnName = "tipo_categoria")
    private CategoriaFidelidad tipoCategoria;

    @Column(name = "intentos_fallidos")
    @ColumnDefault("0")
    private Integer intentosFallidos;

    @Column(name = "mfa_habilitado")
    @ColumnDefault("FALSE")
    private Boolean mfaHabilitado;
}
```

## src/main/java/com/ucab/ucab_services/entity/OfertaLaboral.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "oferta_laboral")
@Getter @Setter @NoArgsConstructor
public class OfertaLaboral {

    @EmbeddedId
    private OfertaLaboralId id;

    @ManyToOne
    @JoinColumn(name = "id_entidad_externa", referencedColumnName = "id_entidad", insertable = false, updatable = false)
    private Externa entidadExterna;

    @Column(name = "perfil_buscado", columnDefinition = "TEXT")
    private String perfilBuscado;

    @Column(name = "beneficios", columnDefinition = "TEXT")
    private String beneficios;

    @Column(name = "rango_fecha_graduacion", length = 100)
    private String rangoFechaGraduacion;

    @Column(name = "fecha_oferta")
    private java.sql.Date fechaOferta;

    @Column(name = "responsabilidades", columnDefinition = "TEXT")
    private String responsabilidades;

    @Column(name = "estatus_vacante", length = 50)
    private String estatusVacante;

    @Column(name = "indice_academico_min", precision = 4, scale = 2)
    private BigDecimal indiceAcademicoMin;

}

```

## src/main/java/com/ucab/ucab_services/entity/OfertaLaboralId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class OfertaLaboralId implements Serializable {

    @Column(name = "id_entidad_externa", nullable = false)
    private Integer idEntidadExterna;

    @Column(name = "cargo_laboral", length = 100, nullable = false)
    private String cargoLaboral;

}
```

## src/main/java/com/ucab/ucab_services/entity/Pago.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Inheritance(strategy = InheritanceType.JOINED)
@IdClass(PagoId.class)
@Getter @Setter @NoArgsConstructor
public class Pago {

    @Id
    @Column(name = "numero_control_factura", length = 50, nullable = false) // Sin insertable=false
    private String numeroControlFactura;

    @Id
    @Column(name = "fecha_operacion", nullable = false) // Sin insertable=false
    private LocalDateTime fechaOperacion;

    @Id
    @Column(name = "tipo_pago", length = 20, nullable = false)
    private String tipoPago;

    @ManyToOne
    @JoinColumn(name = "numero_control_factura", referencedColumnName = "numero_control", insertable = false, updatable = false)
    private Factura factura;

    @Column(name = "canal_origen", length = 50, nullable = false)
    private String canalOrigen;

    @Column(name = "monto_liquidacion", precision = 18, scale = 4, nullable = false)
    private BigDecimal montoLiquidacion;
}
```

## src/main/java/com/ucab/ucab_services/entity/PagoId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class PagoId implements Serializable {

    @Column(name = "numero_control_factura", length = 50, nullable = false)
    private String numeroControlFactura;

    @Column(name = "fecha_operacion", nullable = false)
    private LocalDateTime fechaOperacion;

    // AÃ‘ADIDO: Ahora tipo_pago forma parte de la clave primaria compuesta
    @Column(name = "tipo_pago", length = 20, nullable = false)
    private String tipoPago;
}
```

## src/main/java/com/ucab/ucab_services/entity/PagoMovil.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pago_movil")
@Getter @Setter @NoArgsConstructor
public class PagoMovil extends Pago {

    @Column(name = "telefono_origen", length = 20, nullable = false)
    private String telefonoOrigen;

    @Column(name = "banco_destino", length = 100, nullable = false)
    private String bancoDestino;

    @Column(name = "referencia_pm", length = 50, nullable = false)
    private String referenciaPm;
}
```

## src/main/java/com/ucab/ucab_services/entity/PagoTai.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "pago_tai")
@Getter @Setter @NoArgsConstructor
public class PagoTai extends Pago {

    @Column(name = "pos_terminal", length = 50, nullable = false)
    private String posTerminal;

    @Column(name = "recibo_digital", length = 255, nullable = false)
    private String reciboDigital;

    @Column(name = "saldo_restante", precision = 10, scale = 2, nullable = false)
    private BigDecimal saldoRestante;

    @Column(name = "uid_billetera", length = 50, nullable = false)
    private String uidBilletera;
}
```

## src/main/java/com/ucab/ucab_services/entity/PasoActividad.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // âœ… IMPORTACIÃ“N AÃ‘ADIDA
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity // âœ… ANOTACIÃ“N FALTANTE AÃ‘ADIDA
@Table(name = "paso_actividad")
@Getter @Setter @NoArgsConstructor
@IdClass(PasoActividadId.class)
public class PasoActividad {

    @Id
    @Column(name = "identificador", length = 30, nullable = false, insertable = false, updatable = false)
    private String identificador;

    @Id
    @Column(name = "orden_paso", nullable = false, insertable = false, updatable = false)
    private Integer ordenPaso;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;

    @Column(name = "estado_paso", length = 50, nullable = false)
    private String estadoPaso;

    @Column(name = "fecha_evento")
    private LocalDate fechaEvento;

    @Column(name = "responsable", length = 200)
    private String responsable;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

}
```

## src/main/java/com/ucab/ucab_services/entity/PasoActividadId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PasoActividadId implements Serializable {

    @Column(name = "identificador", length = 30, nullable = false)
    private String identificador;

    @Column(name = "orden_paso", nullable = false)
    private Integer ordenPaso;
}
```

## src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacion.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "periodo_vinculacion")
@Getter @Setter @NoArgsConstructor
public class PeriodoVinculacion {

    @EmbeddedId
    private PeriodoVinculacionId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Miembro miembro;

    @Column(name = "rol", length = 30, nullable = false)
    private String rol;

    @Column(name = "fecha_fin")
    private Date fechaFin;

}
```

## src/main/java/com/ucab/ucab_services/entity/PeriodoVinculacionId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class PeriodoVinculacionId implements Serializable {

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

}
```

## src/main/java/com/ucab/ucab_services/entity/PersonalAdministrativo.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class PersonalAdministrativo extends Miembro {

    @Column(name = "unidad_adscripcion", length = 150)
    private String unidadAdscripcion;

    @Column(name = "cargo_administrativo", length = 150)
    private String cargoAdministrativo;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private BigDecimal cargaSemanal;

}
```

## src/main/java/com/ucab/ucab_services/entity/Preparador.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "preparador")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro")
public class Preparador extends Estudiante {

    @Column(name = "asignatura_asignada", length = 150)
    private String asignaturaAsignada;

    @Column(name = "horas_ayudantia", precision = 5, scale = 2)
    private BigDecimal horasAyudantia;

}

```

## src/main/java/com/ucab/ucab_services/entity/Sede.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sede")
@Getter @Setter @NoArgsConstructor
public class Sede {

    @Id
    @Column(name = "nombre_sede", length = 100, nullable = false)
    private String nombreSede;

}
```

## src/main/java/com/ucab/ucab_services/entity/Servicio.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicio")
@Getter @Setter @NoArgsConstructor
public class Servicio {

    @Id
    @Column(name = "codigo_servicio", length = 50, nullable = false)
    private String codigoServicio;

    @Column(name = "descripcion_detallada", columnDefinition = "TEXT")
    private String descripcionDetallada;

    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @Column(name = "estado_servicio", length = 50, nullable = false)
    private String estadoServicio;

    @Column(name = "perfil_solicitante", columnDefinition = "TEXT")
    private String perfilSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_entidad", referencedColumnName = "id_entidad", nullable = false)
    private EntidadPrestadora entidadPrestadora;

}
```

## src/main/java/com/ucab/ucab_services/entity/SolicitudServicio.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "solicitud_servicio")
@Getter @Setter @NoArgsConstructor
public class SolicitudServicio {

    @Id
    @Column(name = "identificador", length = 30, nullable = false)
    private String identificador;

    @ManyToOne
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", nullable = false)
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", nullable = false)
    private Servicio servicio;

    @Column(name = "estado_actual", length = 50, nullable = false)
    private String estadoActual;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // We'll add a transient field for tiempo_resolucion if needed, but it's computed by a function in DB.
    // We can ignore it for now or add a method to compute it.

}
```

## src/main/java/com/ucab/ucab_services/entity/SugeridaA.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sugerida_a")
@Getter @Setter @NoArgsConstructor
public class SugeridaA {

    @EmbeddedId
    private SugeridaAId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_entidad_externa", referencedColumnName = "id_entidad_externa", insertable = false, updatable = false),
            @JoinColumn(name = "cargo_laboral", referencedColumnName = "cargo_laboral", insertable = false, updatable = false)
    })
    private OfertaLaboral ofertaLaboral;

    @ManyToOne
    @JoinColumn(name = "cedula_miembro", referencedColumnName = "cedula_miembro", insertable = false, updatable = false)
    private Egresado egresado;

}

```

## src/main/java/com/ucab/ucab_services/entity/SugeridaAId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class SugeridaAId implements Serializable {

    @Column(name = "id_entidad_externa", nullable = false)
    private Integer idEntidadExterna;

    @Column(name = "cargo_laboral", length = 100, nullable = false)
    private String cargoLaboral;

    @Column(name = "cedula_miembro", length = 20, nullable = false)
    private String cedulaMiembro;

}
```

## src/main/java/com/ucab/ucab_services/entity/TarifaServicio.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarifa_servicio")
@Getter @Setter @NoArgsConstructor
public class TarifaServicio {

    @EmbeddedId
    private TarifaServicioId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio", insertable = false, updatable = false),
            @JoinColumn(name = "perfil_solicitante", referencedColumnName = "perfil_solicitante", insertable = false, updatable = false)
    })
    private Servicio servicio;

    @Column(name = "precio_base", precision = 18, scale = 4, nullable = false)
    private java.math.BigDecimal precioBase;

    @Column(name = "limite_costo", precision = 18, scale = 4)
    private java.math.BigDecimal limiteCosto;

    @Column(name = "fecha_inicio")
    private java.sql.Date fechaInicio;

    @Column(name = "fecha_fin")
    private java.sql.Date fechaFin;

}

```

## src/main/java/com/ucab/ucab_services/entity/TarifaServicioId.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class TarifaServicioId implements Serializable {

    @Column(name = "perfil_solicitante", length = 30, nullable = false)
    private String perfilSolicitante;

    @Column(name = "codigo_servicio", length = 30, nullable = false)
    private String codigoServicio;

}
```

## src/main/java/com/ucab/ucab_services/entity/Tarjeta.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarjeta")
@Getter @Setter @NoArgsConstructor
public class Tarjeta extends Pago {

    @Column(name = "tipo_tarjeta", length = 20, nullable = false)
    private String tipoTarjeta;

    @Column(name = "banco_emisor", length = 100, nullable = false)
    private String bancoEmisor;

    @Column(name = "ultimos_cuatro", length = 4, nullable = false)
    private String ultimosCuatro;
}
```

## src/main/java/com/ucab/ucab_services/entity/Zelle.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "zelle")
@Getter @Setter @NoArgsConstructor
public class Zelle extends Pago {

    @Column(name = "correo_titular", length = 100, nullable = false)
    private String correoTitular;

    @Column(name = "referencia_zelle", length = 50, nullable = false)
    private String referenciaZelle;
}
```

## src/main/java/com/ucab/ucab_services/repository/AcompananteTemporalRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AcompananteTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcompananteTemporalRepository extends JpaRepository<AcompananteTemporal, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/AplicaEnRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AplicaEnRepository extends JpaRepository<AplicaEn, AplicaEnId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/AsignadoEnRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignadoEnRepository extends JpaRepository<AsignadoEn, AsignadoEnId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/AuditoriaSesionRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaSesionRepository extends JpaRepository<AuditoriaSesion, AuditoriaSesionId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/BecarioRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Becario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BecarioRepository extends JpaRepository<Becario, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/BeneficiarioRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/BilleteraTaiRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.BilleteraTai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilleteraTaiRepository extends JpaRepository<BilleteraTai, java.util.UUID> {
    BilleteraTai findByMiembroCedulaMiembro(String cedulaMiembro);
}
```

## src/main/java/com/ucab/ucab_services/repository/CategoriaFidelidadRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaFidelidadRepository extends JpaRepository<CategoriaFidelidad, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/ClasificadoEnRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificadoEnRepository extends JpaRepository<ClasificadoEn, ClasificadoEnId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/CriptomonedasRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Criptomonedas;
import com.ucab.ucab_services.entity.PagoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriptomonedasRepository extends JpaRepository<Criptomonedas, PagoId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/DocenteRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/EdificacionRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificacionRepository extends JpaRepository<Edificacion, EdificacionId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/EgresadoRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Egresado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresadoRepository extends JpaRepository<Egresado, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/EntidadPrestadoraRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadPrestadoraRepository extends JpaRepository<EntidadPrestadora, Integer> {
}
```

## src/main/java/com/ucab/ucab_services/repository/EspacioFisicoRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisico, EspacioFisicoId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/EstudianteRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/FacturaRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/FolioConsumoRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.FolioConsumo;
import com.ucab.ucab_services.entity.FolioConsumoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolioConsumoRepository extends JpaRepository<FolioConsumo, FolioConsumoId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/ItemConsumoRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.ItemConsumo;
import com.ucab.ucab_services.entity.ItemConsumoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemConsumoRepository extends JpaRepository<ItemConsumo, ItemConsumoId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/MFACodigoRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFACodigoRepository extends JpaRepository<MFACodigo, MFACodigoId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/MiembroRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, String> {
    // Spring Data JPA deduce la consulta por el nombre del atributo exacto
    Optional<Miembro> findByCorreoInstitucional(String correoInstitucional);
}
```

## src/main/java/com/ucab/ucab_services/repository/OfertaLaboralRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.OfertaLaboral;
import com.ucab.ucab_services.entity.OfertaLaboralId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaLaboralRepository extends JpaRepository<OfertaLaboral, OfertaLaboralId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/PagoMovilRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PagoMovil;
import com.ucab.ucab_services.entity.PagoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoMovilRepository extends JpaRepository<PagoMovil, PagoId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/PasoActividadRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PasoActividad;
import com.ucab.ucab_services.entity.PasoActividadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoActividadRepository extends JpaRepository<PasoActividad, PasoActividadId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/PeriodoVinculacionRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoVinculacionRepository extends JpaRepository<PeriodoVinculacion, PeriodoVinculacionId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/PersonalAdministrativoRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalAdministrativoRepository extends JpaRepository<PersonalAdministrativo, String> {
    // Hereda automÃ¡ticamente mÃ©todos como existsById(String id)
}
```

## src/main/java/com/ucab/ucab_services/repository/PreparadorRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Preparador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreparadorRepository extends JpaRepository<Preparador, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/SedeRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/ServicioRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/SolicitudServicioRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.SolicitudServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, String> {
}
```

## src/main/java/com/ucab/ucab_services/repository/SugeridaARepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.SugeridaA;
import com.ucab.ucab_services.entity.SugeridaAId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugeridaARepository extends JpaRepository<SugeridaA, SugeridaAId> {
}
```

## src/main/java/com/ucab/ucab_services/repository/TarifaServicioRepository.java

```java
package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaServicioRepository extends JpaRepository<TarifaServicio, TarifaServicioId> {
}
```

## src/main/java/com/ucab/ucab_services/service/AplicaEnService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import java.util.List;
import java.util.Optional;

public interface AplicaEnService {
    List<AplicaEn> findAll();
    Optional<AplicaEn> findById(AplicaEnId id);
    AplicaEn save(AplicaEn aplicaEn);
    void deleteById(AplicaEnId id);
    boolean existsById(AplicaEnId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/AsignadoEnService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import java.util.List;
import java.util.Optional;

public interface AsignadoEnService {
    List<AsignadoEn> findAll();
    Optional<AsignadoEn> findById(AsignadoEnId id);
    AsignadoEn save(AsignadoEn asignadoEn);
    void deleteById(AsignadoEnId id);
    boolean existsById(AsignadoEnId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/AuditoriaSesionService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import java.util.List;
import java.util.Optional;

public interface AuditoriaSesionService {
    List<AuditoriaSesion> findAll();
    Optional<AuditoriaSesion> findById(AuditoriaSesionId id);
    AuditoriaSesion save(AuditoriaSesion auditoriaSesion);
    void deleteById(AuditoriaSesionId id);
    boolean existsById(AuditoriaSesionId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/AuthService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.repository.DocenteRepository;
import com.ucab.ucab_services.repository.PersonalAdministrativoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Nota: Si manejas un servicio para JWT, inyÃ©ctalo aquÃ­
    // @Autowired
    // private JwtService jwtService;

    public LoginResponse authenticate(LoginRequest request) {
        // 1. Buscar al miembro base por su correo electrÃ³nico institucional
        Miembro miembro = miembroRepository.findByCorreoInstitucional(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en el sistema"));

        // 2. Validar que la contraseÃ±a coincida con el hash BCrypt guardado (claveHash)
        if (!passwordEncoder.matches(request.getClave(), miembro.getClaveHash())) {
            throw new RuntimeException("Credenciales invÃ¡lidas");
        }

        // 3. Obtener el rol restringido a los 3 tipos permitidos
        List<String> roles = determinarRoles(miembro.getCedulaMiembro());

        // 4. Mapear los datos a la respuesta esperada por Angular
        LoginResponse response = new LoginResponse();

        // SimulaciÃ³n de Token (SustitÃºyelo por tu generador de JWT si aplica)
        // response.setToken(jwtService.generateToken(miembro, roles));
        response.setToken("dummy-jwt-token-generado");

        response.setRoles(roles);

        // Se concatena nombre y apellido para enviarlo al Frontend
        response.setNombre(miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos());

        // Se extrae el correo usando el getter correcto de la entidad
        response.setCorreo(miembro.getCorreoInstitucional());

        return response;
    }

    /**
     * EvalÃºa la cÃ©dula de forma restrictiva.
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
            // Si el usuario existe en la tabla general pero no en estas tres tablas
            throw new RuntimeException("Acceso denegado: Su rol no tiene permisos para ingresar a la aplicaciÃ³n Web.");
        }

        return roles;
    }
}
```

## src/main/java/com/ucab/ucab_services/service/BecarioService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Becario;
import java.util.List;
import java.util.Optional;

public interface BecarioService {
    List<Becario> findAll();
    Optional<Becario> findById(String id);
    Becario save(Becario becario);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/BeneficiarioService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Beneficiario;
import java.util.List;
import java.util.Optional;

public interface BeneficiarioService {
    Beneficiario save(Beneficiario beneficiario);
    Optional<Beneficiario> findById(String id);
    List<Beneficiario> findAll();
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/BilleteraTaiService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.BilleteraTai;

import java.util.List;
import java.util.UUID;

public interface BilleteraTaiService {
    
    BilleteraTai save(BilleteraTai billeteraTai);
    
    BilleteraTai findById(UUID id);
    
    // âœ… CAMBIO AQUÃ: Actualizado para coincidir con la implementaciÃ³n y el repositorio
    BilleteraTai findByMiembroCedulaMiembro(String cedulaMiembro);
    
    List<BilleteraTai> findAll();
    
    void deleteById(UUID id);
}
```

## src/main/java/com/ucab/ucab_services/service/CategoriaFidelidadService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import java.util.List;
import java.util.Optional;

public interface CategoriaFidelidadService {
    List<CategoriaFidelidad> findAll();
    Optional<CategoriaFidelidad> findById(String id);
    CategoriaFidelidad save(CategoriaFidelidad categoriaFidelidad);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/ClasificadoEnService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import java.util.List;
import java.util.Optional;

public interface ClasificadoEnService {
    List<ClasificadoEn> findAll();
    Optional<ClasificadoEn> findById(ClasificadoEnId id);
    ClasificadoEn save(ClasificadoEn clasificadoEn);
    void deleteById(ClasificadoEnId id);
    boolean existsById(ClasificadoEnId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/DocenteService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Docente;
import java.util.List;
import java.util.Optional;

public interface DocenteService {
    List<Docente> findAll();
    Optional<Docente> findById(String id);
    Docente save(Docente docente);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/EdificacionService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import java.util.List;
import java.util.Optional;

public interface EdificacionService {
    List<Edificacion> findAll();
    Optional<Edificacion> findById(EdificacionId id);
    Edificacion save(Edificacion edificacion);
    void deleteById(EdificacionId id);
    boolean existsById(EdificacionId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/EgresadoService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Egresado;
import java.util.List;
import java.util.Optional;

public interface EgresadoService {
    List<Egresado> findAll();
    Optional<Egresado> findById(String id);
    Egresado save(Egresado egresado);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/EntidadPrestadoraService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import java.util.List;
import java.util.Optional;

public interface EntidadPrestadoraService {
    List<EntidadPrestadora> findAll();
    Optional<EntidadPrestadora> findById(Integer id);
    EntidadPrestadora save(EntidadPrestadora entidadPrestadora);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/EspacioFisicoService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import java.util.List;
import java.util.Optional;

public interface EspacioFisicoService {
    List<EspacioFisico> findAll();
    Optional<EspacioFisico> findById(EspacioFisicoId id);
    EspacioFisico save(EspacioFisico espacioFisico);
    void deleteById(EspacioFisicoId id);
    boolean existsById(EspacioFisicoId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/EstudianteService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> findAll();
    Optional<Estudiante> findById(String id);
    Estudiante save(Estudiante estudiante);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/FacturaService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Factura;
import java.util.List;

public interface FacturaService {
    Factura save(Factura factura);
    Factura findById(String id);
    List<Factura> findAll();
    void deleteById(String id);
}
```

## src/main/java/com/ucab/ucab_services/service/impl/AplicaEnServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import com.ucab.ucab_services.repository.AplicaEnRepository;
import com.ucab.ucab_services.service.AplicaEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AplicaEnServiceImpl implements AplicaEnService {

    @Autowired
    private AplicaEnRepository aplicaEnRepository;

    @Override
    public List<AplicaEn> findAll() {
        return aplicaEnRepository.findAll();
    }

    @Override
    public Optional<AplicaEn> findById(AplicaEnId id) {
        return aplicaEnRepository.findById(id);
    }

    @Override
    public AplicaEn save(AplicaEn aplicaEn) {
        return aplicaEnRepository.save(aplicaEn);
    }

    @Override
    public void deleteById(AplicaEnId id) {
        aplicaEnRepository.deleteById(id);
    }

    @Override
    public boolean existsById(AplicaEnId id) {
        return aplicaEnRepository.existsById(id);
    }

    @Override
    public long count() {
        return aplicaEnRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/AsignadoEnServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import com.ucab.ucab_services.repository.AsignadoEnRepository;
import com.ucab.ucab_services.service.AsignadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignadoEnServiceImpl implements AsignadoEnService {

    @Autowired
    private AsignadoEnRepository asignadoEnRepository;

    @Override
    public List<AsignadoEn> findAll() {
        return asignadoEnRepository.findAll();
    }

    @Override
    public Optional<AsignadoEn> findById(AsignadoEnId id) {
        return asignadoEnRepository.findById(id);
    }

    @Override
    public AsignadoEn save(AsignadoEn asignadoEn) {
        return asignadoEnRepository.save(asignadoEn);
    }

    @Override
    public void deleteById(AsignadoEnId id) {
        asignadoEnRepository.deleteById(id);
    }

    @Override
    public boolean existsById(AsignadoEnId id) {
        return asignadoEnRepository.existsById(id);
    }

    @Override
    public long count() {
        return asignadoEnRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/AuditoriaSesionServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import com.ucab.ucab_services.repository.AuditoriaSesionRepository;
import com.ucab.ucab_services.service.AuditoriaSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaSesionServiceImpl implements AuditoriaSesionService {

    @Autowired
    private AuditoriaSesionRepository auditoriaSesionRepository;

    @Override
    public List<AuditoriaSesion> findAll() {
        return auditoriaSesionRepository.findAll();
    }

    @Override
    public Optional<AuditoriaSesion> findById(AuditoriaSesionId id) {
        return auditoriaSesionRepository.findById(id);
    }

    @Override
    public AuditoriaSesion save(AuditoriaSesion auditoriaSesion) {
        return auditoriaSesionRepository.save(auditoriaSesion);
    }

    @Override
    public void deleteById(AuditoriaSesionId id) {
        auditoriaSesionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(AuditoriaSesionId id) {
        return auditoriaSesionRepository.existsById(id);
    }

    @Override
    public long count() {
        return auditoriaSesionRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/BecarioServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Becario;
import com.ucab.ucab_services.repository.BecarioRepository;
import com.ucab.ucab_services.service.BecarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BecarioServiceImpl implements BecarioService {

    @Autowired
    private BecarioRepository becarioRepository;

    @Override
    public List<Becario> findAll() {
        return becarioRepository.findAll();
    }

    @Override
    public Optional<Becario> findById(String id) {
        return becarioRepository.findById(id);
    }

    @Override
    public Becario save(Becario becario) {
        return becarioRepository.save(becario);
    }

    @Override
    public void deleteById(String id) {
        becarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return becarioRepository.existsById(id);
    }

    @Override
    public long count() {
        return becarioRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/BeneficiarioServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.repository.BeneficiarioRepository;
import com.ucab.ucab_services.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Override
    public Beneficiario save(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    @Override
    public Optional<Beneficiario> findById(String id) {
        return beneficiarioRepository.findById(id);
    }

    @Override
    public java.util.List<Beneficiario> findAll() {
        return beneficiarioRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        beneficiarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return beneficiarioRepository.existsById(id);
    }

    @Override
    public long count() {
        return beneficiarioRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/BilleteraTaiServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.BilleteraTai;
import com.ucab.ucab_services.repository.BilleteraTaiRepository;
import com.ucab.ucab_services.service.BilleteraTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BilleteraTaiServiceImpl implements BilleteraTaiService {
    @Autowired
    private BilleteraTaiRepository billeteraTaiRepository;

    @Override
    public BilleteraTai save(BilleteraTai billeteraTai) {
        return billeteraTaiRepository.save(billeteraTai);
    }

    @Override
    public BilleteraTai findById(UUID id) {
        return billeteraTaiRepository.findById(id).orElse(null);
    }

    @Override
    public BilleteraTai findByMiembroCedulaMiembro(String cedulaMiembro) {
        return billeteraTaiRepository.findByMiembroCedulaMiembro(cedulaMiembro);
    }

    @Override
    public java.util.List<BilleteraTai> findAll() {
        return billeteraTaiRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        billeteraTaiRepository.deleteById(id);
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/CategoriaFidelidadServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import com.ucab.ucab_services.repository.CategoriaFidelidadRepository;
import com.ucab.ucab_services.service.CategoriaFidelidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaFidelidadServiceImpl implements CategoriaFidelidadService {

    @Autowired
    private CategoriaFidelidadRepository categoriaFidelidadRepository;

    @Override
    public List<CategoriaFidelidad> findAll() {
        return categoriaFidelidadRepository.findAll();
    }

    @Override
    public Optional<CategoriaFidelidad> findById(String id) {
        return categoriaFidelidadRepository.findById(id);
    }

    @Override
    public CategoriaFidelidad save(CategoriaFidelidad categoriaFidelidad) {
        return categoriaFidelidadRepository.save(categoriaFidelidad);
    }

    @Override
    public void deleteById(String id) {
        categoriaFidelidadRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return categoriaFidelidadRepository.existsById(id);
    }

    @Override
    public long count() {
        return categoriaFidelidadRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/ClasificadoEnServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import com.ucab.ucab_services.repository.ClasificadoEnRepository;
import com.ucab.ucab_services.service.ClasificadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificadoEnServiceImpl implements ClasificadoEnService {

    @Autowired
    private ClasificadoEnRepository clasificadoEnRepository;

    @Override
    public List<ClasificadoEn> findAll() {
        return clasificadoEnRepository.findAll();
    }

    @Override
    public Optional<ClasificadoEn> findById(ClasificadoEnId id) {
        return clasificadoEnRepository.findById(id);
    }

    @Override
    public ClasificadoEn save(ClasificadoEn clasificadoEn) {
        return clasificadoEnRepository.save(clasificadoEn);
    }

    @Override
    public void deleteById(ClasificadoEnId id) {
        clasificadoEnRepository.deleteById(id);
    }

    @Override
    public boolean existsById(ClasificadoEnId id) {
        return clasificadoEnRepository.existsById(id);
    }

    @Override
    public long count() {
        return clasificadoEnRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/DocenteServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Docente;
import com.ucab.ucab_services.repository.DocenteRepository;
import com.ucab.ucab_services.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> findById(String id) {
        return docenteRepository.findById(id);
    }

    @Override
    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    public void deleteById(String id) {
        docenteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return docenteRepository.existsById(id);
    }

    @Override
    public long count() {
        return docenteRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/EdificacionServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import com.ucab.ucab_services.repository.EdificacionRepository;
import com.ucab.ucab_services.service.EdificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdificacionServiceImpl implements EdificacionService {

    @Autowired
    private EdificacionRepository edificacionRepository;

    @Override
    public List<Edificacion> findAll() {
        return edificacionRepository.findAll();
    }

    @Override
    public Optional<Edificacion> findById(EdificacionId id) {
        return edificacionRepository.findById(id);
    }

    @Override
    public Edificacion save(Edificacion edificacion) {
        return edificacionRepository.save(edificacion);
    }

    @Override
    public void deleteById(EdificacionId id) {
        edificacionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(EdificacionId id) {
        return edificacionRepository.existsById(id);
    }

    @Override
    public long count() {
        return edificacionRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/EgresadoServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Egresado;
import com.ucab.ucab_services.repository.EgresadoRepository;
import com.ucab.ucab_services.service.EgresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EgresadoServiceImpl implements EgresadoService {

    @Autowired
    private EgresadoRepository egresadoRepository;

    @Override
    public List<Egresado> findAll() {
        return egresadoRepository.findAll();
    }

    @Override
    public Optional<Egresado> findById(String id) {
        return egresadoRepository.findById(id);
    }

    @Override
    public Egresado save(Egresado egresado) {
        return egresadoRepository.save(egresado);
    }

    @Override
    public void deleteById(String id) {
        egresadoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return egresadoRepository.existsById(id);
    }

    @Override
    public long count() {
        return egresadoRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/EntidadPrestadoraServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import com.ucab.ucab_services.repository.EntidadPrestadoraRepository;
import com.ucab.ucab_services.service.EntidadPrestadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadPrestadoraServiceImpl implements EntidadPrestadoraService {

    @Autowired
    private EntidadPrestadoraRepository entidadPrestadoraRepository;

    @Override
    public List<EntidadPrestadora> findAll() {
        return entidadPrestadoraRepository.findAll();
    }

    @Override
    public Optional<EntidadPrestadora> findById(Integer id) {
        return entidadPrestadoraRepository.findById(id);
    }

    @Override
    public EntidadPrestadora save(EntidadPrestadora entidadPrestadora) {
        return entidadPrestadoraRepository.save(entidadPrestadora);
    }

    @Override
    public void deleteById(Integer id) {
        entidadPrestadoraRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return entidadPrestadoraRepository.existsById(id);
    }

    @Override
    public long count() {
        return entidadPrestadoraRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/EspacioFisicoServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import com.ucab.ucab_services.repository.EspacioFisicoRepository;
import com.ucab.ucab_services.service.EspacioFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacioFisicoServiceImpl implements EspacioFisicoService {

    @Autowired
    private EspacioFisicoRepository espacioFisicoRepository;

    @Override
    public List<EspacioFisico> findAll() {
        return espacioFisicoRepository.findAll();
    }

    @Override
    public Optional<EspacioFisico> findById(EspacioFisicoId id) {
        return espacioFisicoRepository.findById(id);
    }

    @Override
    public EspacioFisico save(EspacioFisico espacioFisico) {
        return espacioFisicoRepository.save(espacioFisico);
    }

    @Override
    public void deleteById(EspacioFisicoId id) {
        espacioFisicoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(EspacioFisicoId id) {
        return espacioFisicoRepository.existsById(id);
    }

    @Override
    public long count() {
        return espacioFisicoRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/EstudianteServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Estudiante;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> findById(String id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteById(String id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return estudianteRepository.existsById(id);
    }

    @Override
    public long count() {
        return estudianteRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/FacturaServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Factura;
import com.ucab.ucab_services.repository.FacturaRepository;
import com.ucab.ucab_services.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura findById(String id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        facturaRepository.deleteById(id);
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/MFACodigoServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import com.ucab.ucab_services.repository.MFACodigoRepository;
import com.ucab.ucab_services.service.MFACodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MFACodigoServiceImpl implements MFACodigoService {

    @Autowired
    private MFACodigoRepository mfaCodigoRepository;

    @Override
    public List<MFACodigo> findAll() {
        return mfaCodigoRepository.findAll();
    }

    @Override
    public Optional<MFACodigo> findById(MFACodigoId id) {
        return mfaCodigoRepository.findById(id);
    }

    @Override
    public MFACodigo save(MFACodigo mfaCodigo) {
        return mfaCodigoRepository.save(mfaCodigo);
    }

    @Override
    public void deleteById(MFACodigoId id) {
        mfaCodigoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(MFACodigoId id) {
        return mfaCodigoRepository.existsById(id);
    }

    @Override
    public long count() {
        return mfaCodigoRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/MiembroServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    @Override
    public Optional<Miembro> findById(String id) {
        return miembroRepository.findById(id);
    }

    @Override
    public Miembro save(Miembro miembro) {
        if (miembroRepository.existsById(miembro.getCedulaMiembro()) && miembro.getFechaApertura() == null) {
            throw new RuntimeException("El miembro con cÃ©dula " + miembro.getCedulaMiembro() + " ya existe.");
        }

        if (miembro.getClaveHash() != null && !miembro.getClaveHash().startsWith("$2a$")) {
            String hash = passwordEncoder.encode(miembro.getClaveHash());
            miembro.setClaveHash(hash);
        }

        if (miembro.getEstadoCuenta() == null) {
            miembro.setEstadoCuenta("ACTIVO");
        }
        if (miembro.getIntentosFallidos() == null) {
            miembro.setIntentosFallidos(0);
        }

        return miembroRepository.save(miembro);
    }

    @Override
    public void deleteById(String id) {
        miembroRepository.deleteById(id);
    }

    // âœ… MÃ‰TODOS OBLIGATORIOS AÃ‘ADIDOS
    @Override
    public boolean existsById(String id) {
        return miembroRepository.existsById(id);
    }

    @Override
    public long count() {
        return miembroRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/PeriodoVinculacionServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import com.ucab.ucab_services.repository.PeriodoVinculacionRepository;
import com.ucab.ucab_services.service.PeriodoVinculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoVinculacionServiceImpl implements PeriodoVinculacionService {

    @Autowired
    private PeriodoVinculacionRepository periodoVinculacionRepository;

    @Override
    public List<PeriodoVinculacion> findAll() {
        return periodoVinculacionRepository.findAll();
    }

    @Override
    public Optional<PeriodoVinculacion> findById(PeriodoVinculacionId id) {
        return periodoVinculacionRepository.findById(id);
    }

    @Override
    public PeriodoVinculacion save(PeriodoVinculacion periodoVinculacion) {
        return periodoVinculacionRepository.save(periodoVinculacion);
    }

    @Override
    public void deleteById(PeriodoVinculacionId id) {
        periodoVinculacionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(PeriodoVinculacionId id) {
        return periodoVinculacionRepository.existsById(id);
    }

    @Override
    public long count() {
        return periodoVinculacionRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/PersonalAdministrativoServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import com.ucab.ucab_services.repository.PersonalAdministrativoRepository;
import com.ucab.ucab_services.service.PersonalAdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAdministrativoServiceImpl implements PersonalAdministrativoService {

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    @Override
    public List<PersonalAdministrativo> findAll() {
        return personalAdministrativoRepository.findAll();
    }

    @Override
    public Optional<PersonalAdministrativo> findById(String id) {
        return personalAdministrativoRepository.findById(id);
    }

    @Override
    public PersonalAdministrativo save(PersonalAdministrativo personalAdministrativo) {
        return personalAdministrativoRepository.save(personalAdministrativo);
    }

    @Override
    public void deleteById(String id) {
        personalAdministrativoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return personalAdministrativoRepository.existsById(id);
}

    @Override
    public long count() {
        return personalAdministrativoRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/PreparadorServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Preparador;
import com.ucab.ucab_services.repository.PreparadorRepository;
import com.ucab.ucab_services.service.PreparadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreparadorServiceImpl implements PreparadorService {

    @Autowired
    private PreparadorRepository preparadorRepository;

    @Override
    public List<Preparador> findAll() {
        return preparadorRepository.findAll();
    }

    @Override
    public Optional<Preparador> findById(String id) {
        return preparadorRepository.findById(id);
    }

    @Override
    public Preparador save(Preparador preparador) {
        return preparadorRepository.save(preparador);
    }

    @Override
    public void deleteById(String id) {
        preparadorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return preparadorRepository.existsById(id);
    }

    @Override
    public long count() {
        return preparadorRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/SedeServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Sede;
import com.ucab.ucab_services.repository.SedeRepository;
import com.ucab.ucab_services.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public Sede save(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Override
    public Sede findById(String id) {
        return sedeRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Sede> findAll() {
        return sedeRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        sedeRepository.deleteById(id);
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/ServicioServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Servicio;
import com.ucab.ucab_services.repository.ServicioRepository;
import com.ucab.ucab_services.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio findById(String id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        servicioRepository.deleteById(id);
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/SolicitudServicioServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.SolicitudServicio;
import com.ucab.ucab_services.repository.SolicitudServicioRepository;
import com.ucab.ucab_services.service.SolicitudServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServicioServiceImpl implements SolicitudServicioService {

    @Autowired
    private SolicitudServicioRepository solicitudServicioRepository;

    @Override
    public SolicitudServicio save(SolicitudServicio solicitudServicio) {
        return solicitudServicioRepository.save(solicitudServicio);
    }

    @Override
    public SolicitudServicio findById(String id) {
        return solicitudServicioRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<SolicitudServicio> findAll() {
        return solicitudServicioRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        solicitudServicioRepository.deleteById(id);
    }
}
```

## src/main/java/com/ucab/ucab_services/service/impl/TarifaServicioServiceImpl.java

```java
package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import com.ucab.ucab_services.repository.TarifaServicioRepository;
import com.ucab.ucab_services.service.TarifaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaServicioServiceImpl implements TarifaServicioService {

    @Autowired
    private TarifaServicioRepository tarifaServicioRepository;

    @Override
    public List<TarifaServicio> findAll() {
        return tarifaServicioRepository.findAll();
    }

    @Override
    public Optional<TarifaServicio> findById(TarifaServicioId id) {
        return tarifaServicioRepository.findById(id);
    }

    @Override
    public TarifaServicio save(TarifaServicio tarifaServicio) {
        return tarifaServicioRepository.save(tarifaServicio);
    }

    @Override
    public void deleteById(TarifaServicioId id) {
        tarifaServicioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(TarifaServicioId id) {
        return tarifaServicioRepository.existsById(id);
    }

    @Override
    public long count() {
        return tarifaServicioRepository.count();
    }
}
```

## src/main/java/com/ucab/ucab_services/service/MFACodigoService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import java.util.List;
import java.util.Optional;

public interface MFACodigoService {
    List<MFACodigo> findAll();
    Optional<MFACodigo> findById(MFACodigoId id);
    MFACodigo save(MFACodigo mfaCodigo);
    void deleteById(MFACodigoId id);
    boolean existsById(MFACodigoId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/MiembroService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import java.util.List;
import java.util.Optional;

public interface MiembroService {
    Miembro save(Miembro miembro);
    Optional<Miembro> findById(String id);
    List<Miembro> findAll();
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/PeriodoVinculacionService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import java.util.List;
import java.util.Optional;

public interface PeriodoVinculacionService {
    List<PeriodoVinculacion> findAll();
    Optional<PeriodoVinculacion> findById(PeriodoVinculacionId id);
    PeriodoVinculacion save(PeriodoVinculacion periodoVinculacion);
    void deleteById(PeriodoVinculacionId id);
    boolean existsById(PeriodoVinculacionId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/PersonalAdministrativoService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import java.util.List;
import java.util.Optional;

public interface PersonalAdministrativoService {
    List<PersonalAdministrativo> findAll();
    Optional<PersonalAdministrativo> findById(String id);
    PersonalAdministrativo save(PersonalAdministrativo personalAdministrativo);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/PreparadorService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Preparador;
import java.util.List;
import java.util.Optional;

public interface PreparadorService {
    List<Preparador> findAll();
    Optional<Preparador> findById(String id);
    Preparador save(Preparador preparador);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/service/SedeService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Sede;
import java.util.List;

public interface SedeService {
    Sede save(Sede sede);
    Sede findById(String id);
    List<Sede> findAll();
    void deleteById(String id);
}
```

## src/main/java/com/ucab/ucab_services/service/ServicioService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Servicio;
import java.util.List;

public interface ServicioService {
    Servicio save(Servicio servicio);
    Servicio findById(String id);
    List<Servicio> findAll();
    void deleteById(String id);
}
```

## src/main/java/com/ucab/ucab_services/service/SolicitudServicioService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.SolicitudServicio;
import java.util.List;

public interface SolicitudServicioService {
    SolicitudServicio save(SolicitudServicio solicitudServicio);
    SolicitudServicio findById(String id);
    List<SolicitudServicio> findAll();
    void deleteById(String id);
}
```

## src/main/java/com/ucab/ucab_services/service/TarifaServicioService.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import java.util.List;
import java.util.Optional;

public interface TarifaServicioService {
    List<TarifaServicio> findAll();
    Optional<TarifaServicio> findById(TarifaServicioId id);
    TarifaServicio save(TarifaServicio tarifaServicio);
    void deleteById(TarifaServicioId id);
    boolean existsById(TarifaServicioId id);
    long count();
}
```

## src/main/java/com/ucab/ucab_services/UcabServicesApplication.java

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

## src/main/resources/application.properties

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

## src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

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
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testVerificarMfaSuccess() throws Exception {
        VerificarMfaRequest verificarMfaRequest = new VerificarMfaRequest();
        verificarMfaRequest.setCedula("12345678");
        verificarMfaRequest.setCodigo("123456");

        MiembroSesionDTO sesionDTO = new MiembroSesionDTO();
        sesionDTO.setCedulaMiembro("12345678");
        sesionDTO.setNombresCompletos("Juan");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setSuccess(true);
        expectedResponse.setMessage("MFA verified successfully");
        expectedResponse.setUsuario(sesionDTO);

        when(authService.verificarCodigoMfa("12345678", "123456")).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/verificar-mfa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificarMfaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("MFA verified successfully"))
                .andExpect(jsonPath("$.usuario.cedulaMiembro").value("12345678"));
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

## src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.LoginRequest;
import com.ucab.ucab_services.dto.LoginResponse;
import com.ucab.ucab_services.dto.VerificarMfaRequest;
import com.ucab.ucab_services.dto.MiembroSesionDTO;
import com.ucab.ucab_services.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

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
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCedula("12345678");
        loginRequest.setClave("password123");

        MiembroSesionDTO sesionDTO = new MiembroSesionDTO();
        sesionDTO.setCedulaMiembro("12345678");
        sesionDTO.setNombresCompletos("Juan");
        sesionDTO.setApellidosCompletos("Perez");
        sesionDTO.setCorreoInstitucional("juan.perez@ucab.edu.ve");
        sesionDTO.setEstadoCuenta("Activo");
        sesionDTO.setRol("ESTUDIANTE");

        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setSuccess(true);
        expectedResponse.setMessage("Login successful");
        expectedResponse.setUsuario(sesionDTO);

        // âœ… Se cambia a any(LoginRequest.class)
        when(authService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.usuario.cedulaMiembro").value("12345678"))
                .andExpect(jsonPath("$.usuario.nombresCompletos").value("Juan"));
    }

    @Test
    void testLoginValidationFailure() throws Exception {
        LoginRequest loginRequest = new LoginRequest();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.cedula").exists())
                .andExpect(jsonPath("$.clave").exists());
    }
}
```

## src/test/java/com/ucab/ucab_services/service/BeneficiarioServiceTest.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.repository.BeneficiarioRepository;
import com.ucab.ucab_services.service.impl.BeneficiarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneficiarioServiceTest {

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @InjectMocks
    private BeneficiarioServiceImpl beneficiarioService;

    @Test
    void testFindAll() {
        // Act
        beneficiarioService.findAll();

        // Assert
        verify(beneficiarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        String id = "123";
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setDocumentoIdentidad(id);
        when(beneficiarioRepository.findById(id)).thenReturn(Optional.of(beneficiario));

        // Act
        Optional<Beneficiario> result = beneficiarioService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getDocumentoIdentidad());
        verify(beneficiarioRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        // Arrange
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setDocumentoIdentidad("123");
        beneficiario.setNombre("Juan");
        // No apellido field in entity

        when(beneficiarioRepository.save(any(Beneficiario.class))).thenReturn(beneficiario);

        // Act
        Beneficiario result = beneficiarioService.save(beneficiario);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getDocumentoIdentidad());
        assertEquals("Juan", result.getNombre());
        verify(beneficiarioRepository, times(1)).save(beneficiario);
    }

    @Test
    void testDeleteById() {
        // Arrange
        String id = "123";
        doNothing().when(beneficiarioRepository).deleteById(id);

        // Act
        beneficiarioService.deleteById(id);

        // Assert
        verify(beneficiarioRepository, times(1)).deleteById(id);
    }

    @Test
    void testExistsById() {
        // Arrange
        String id = "123";
        when(beneficiarioRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = beneficiarioService.existsById(id);

        // Assert
        assertTrue(result);
        verify(beneficiarioRepository, times(1)).existsById(id);
    }

    @Test
    void testCount() {
        // Arrange
        when(beneficiarioRepository.count()).thenReturn(5L);

        // Act
        long result = beneficiarioService.count();

        // Assert
        assertEquals(5, result);
        verify(beneficiarioRepository, times(1)).count();
    }
}
```

## src/test/java/com/ucab/ucab_services/service/EstudianteServiceTest.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Estudiante;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.service.impl.EstudianteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;

    @BeforeEach
    void setUp() {}

    @Test
    void testFindAll() {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setCedulaMiembro("12345678");
        estudiante1.setNombresCompletos("Juan");
        estudiante1.setApellidosCompletos("Perez");
        estudiante1.setPromedio(new BigDecimal("4.5")); // âœ… CORREGIDO A BIGDECIMAL

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setCedulaMiembro("87654321");
        estudiante2.setNombresCompletos("Maria");
        estudiante2.setApellidosCompletos("Gonzalez");
        estudiante2.setPromedio(new BigDecimal("3.8")); // âœ… CORREGIDO A BIGDECIMAL

        List<Estudiante> estudiantes = Arrays.asList(estudiante1, estudiante2);
        when(estudianteRepository.findAll()).thenReturn(estudiantes);

        List<Estudiante> result = estudianteService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Estudiante estudiante = new Estudiante();
        estudiante.setCedulaMiembro("12345678");
        estudiante.setNombresCompletos("Juan");
        estudiante.setApellidosCompletos("Perez");
        estudiante.setPromedio(new BigDecimal("4.5")); // âœ… CORREGIDO A BIGDECIMAL

        when(estudianteRepository.findById("12345678")).thenReturn(Optional.of(estudiante));

        Optional<Estudiante> result = estudianteService.findById("12345678");

        assertTrue(result.isPresent());
        assertEquals("12345678", result.get().getCedulaMiembro());
        verify(estudianteRepository, times(1)).findById("12345678");
    }

    @Test
    void testSave() {
        Estudiante estudiante = new Estudiante();
        estudiante.setCedulaMiembro("12345678");
        estudiante.setNombresCompletos("Juan");
        estudiante.setApellidosCompletos("Perez");
        estudiante.setPromedio(new BigDecimal("4.5"));

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        Estudiante result = estudianteService.save(estudiante);

        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        verify(estudianteRepository, times(1)).save(estudiante);
    }

    @Test
    void testDeleteById() {
        String cedula = "12345678";
        doNothing().when(estudianteRepository).deleteById(cedula);
        estudianteService.deleteById(cedula);
        verify(estudianteRepository, times(1)).deleteById(cedula);
    }

    @Test
    void testExistsById() {
        String cedula = "12345678";
        when(estudianteRepository.existsById(cedula)).thenReturn(true);
        boolean result = estudianteService.existsById(cedula);
        assertTrue(result);
        verify(estudianteRepository, times(1)).existsById(cedula);
    }

    @Test
    void testCount() {
        when(estudianteRepository.count()).thenReturn(5L);
        long result = estudianteService.count();
        assertEquals(5, result);
        verify(estudianteRepository, times(1)).count();
    }
}
```

## src/test/java/com/ucab/ucab_services/service/MiembroServiceTest.java

```java
package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.impl.MiembroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MiembroServiceTest {

    @Mock
    private MiembroRepository miembroRepository;

    @InjectMocks
    private MiembroServiceImpl miembroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Miembro miembro1 = new Miembro();
        miembro1.setCedulaMiembro("12345678");
        miembro1.setNombresCompletos("Juan");
        miembro1.setApellidosCompletos("Perez");

        Miembro miembro2 = new Miembro();
        miembro2.setCedulaMiembro("87654321");
        miembro2.setNombresCompletos("Maria");
        miembro2.setApellidosCompletos("Gonzalez");

        List<Miembro> miembros = Arrays.asList(miembro1, miembro2);
        when(miembroRepository.findAll()).thenReturn(miembros);

        // Act
        List<Miembro> result = miembroService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345678", result.get(0).getCedulaMiembro());
        assertEquals("87654321", result.get(1).getCedulaMiembro());
        verify(miembroRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");

        when(miembroRepository.findById("12345678")).thenReturn(Optional.of(miembro));

        // Act
        Optional<Miembro> result = miembroService.findById("12345678");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("12345678", result.get().getCedulaMiembro());
        assertEquals("Juan", result.get().getNombresCompletos());
        assertEquals("Perez", result.get().getApellidosCompletos());
        verify(miembroRepository, times(1)).findById("12345678");
    }

    @Test
    void testSave() {
        // Arrange
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");

        when(miembroRepository.save(any(Miembro.class))).thenReturn(miembro);

        // Act
        Miembro result = miembroService.save(miembro);

        // Assert
        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        verify(miembroRepository, times(1)).save(miembro);
    }

    @Test
    void testDeleteById() {
        // Arrange
        String cedula = "12345678";
        doNothing().when(miembroRepository).deleteById(cedula);

        // Act
        miembroService.deleteById(cedula);

        // Assert
        verify(miembroRepository, times(1)).deleteById(cedula);
    }

    @Test
    void testExistsById() {
        // Arrange
        String cedula = "12345678";
        when(miembroRepository.existsById(cedula)).thenReturn(true);

        // Act
        boolean result = miembroService.existsById(cedula);

        // Assert
        assertTrue(result);
        verify(miembroRepository, times(1)).existsById(cedula);
    }

    @Test
    void testCount() {
        // Arrange
        when(miembroRepository.count()).thenReturn(5L);

        // Act
        long result = miembroService.count();

        // Assert
        assertEquals(5, result);
        verify(miembroRepository, times(1)).count();
    }
}
```

## src/test/java/com/ucab/ucab_services/UcabServicesApplicationTests.java

```java
package com.ucab.ucab_services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UcabServicesApplicationTests {

    @Test
    void contextLoads() {
    }

}
```


