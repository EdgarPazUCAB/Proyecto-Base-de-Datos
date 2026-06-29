# Archivos del Backend

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
		<lombok.version>1.18.46</lombok.version>
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
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
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

## src/main/java/com/ucab/ucab_services/config/CorsConfig.java

```java
package com.ucab.ucab_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica a todos los endpoints que comiencen con /api/
                        .allowedOrigins("http://localhost:4200") // Permite explicitamente a tu Frontend en Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
```
## src/main/java/com/ucab/ucab_services/controller/BcvController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.service.BcvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasa-bcv")
@CrossOrigin(origins = "http://localhost:4200")
public class BcvController {

    @Autowired
    private BcvService bcvService;

    @GetMapping
    public Map<String, Double> getRate() {
        Map<String, Double> response = new HashMap<>();
        response.put("rate", bcvService.getBcvRate());
        return response;
    }
}


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
 * comodín "*" se quitó porque permitía que cualquier sitio web
 * llamara a este endpoint de autenticación.
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
@CrossOrigin(origins = "http://localhost:4200")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    // <-- CAMBIO APLICADO: Devolver un JSON limpio para evitar el "no session"
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllBeneficiarios() {
        List<Beneficiario> beneficiarios = beneficiarioService.findAll();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Beneficiario b : beneficiarios) {
            Map<String, Object> map = new HashMap<>();
            map.put("documentoIdentidad", b.getDocumentoIdentidad());
            map.put("nombre", b.getNombre());
            map.put("parentesco", b.getParentesco());
            map.put("estatusBeneficios", b.getEstatusBeneficios());

            // Extraemos solo la cédula del miembro principal para poder filtrar en Angular
            if (b.getMiembro() != null) {
                Map<String, String> miembroMap = new HashMap<>();
                miembroMap.put("cedulaMiembro", b.getMiembro().getCedulaMiembro());
                map.put("miembro", miembroMap);
            }

            responseList.add(map);
        }

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> getBeneficiarioById(@PathVariable String id) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        return optionalBeneficiario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createBeneficiario(@RequestBody Beneficiario beneficiario) {

        // Asignamos fecha de inicio y estatus por defecto
        if(beneficiario.getFechaInicio() == null) {
            beneficiario.setFechaInicio(new java.sql.Date(System.currentTimeMillis()));
        }
        if(beneficiario.getEstatusBeneficios() == null) {
            beneficiario.setEstatusBeneficios("Activo");
        }

        beneficiarioService.save(beneficiario);

        // EVITA EL ERROR DE "no session" AL RESPONDER JSON LIMPIO
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Vínculo familiar registrado con éxito");
        response.put("documento", beneficiario.getDocumentoIdentidad());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> updateBeneficiario(@PathVariable String id, @RequestBody Beneficiario beneficiarioDetails) {
        Optional<Beneficiario> optionalBeneficiario = beneficiarioService.findById(id);
        if (optionalBeneficiario.isPresent()) {
            Beneficiario beneficiario = optionalBeneficiario.get();
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
        // ✅ CAMBIO AQUÍ: Llamado al nuevo nombre del método
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

/**
 * SOLO CONSULTA: Categoria_Fidelidad (descuentos, prioridades de
 * reservación) es un dato institucional que la UCAB define
 * directamente en la base de datos — no se crea, edita ni elimina
 * desde la app web. La reclasificación automática de un miembro
 * dentro de una categoría existente ocurre vía trigger en PostgreSQL,
 * no por edición manual de las categorías mismas.
 */
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
            // Update fields via miembro composition
            if (docenteDetails.getMiembro() != null) {
                if (docente.getMiembro() == null) {
                    docente.setMiembro(docenteDetails.getMiembro());
                } else {
                    docente.getMiembro().setNombresCompletos(docenteDetails.getMiembro().getNombresCompletos());
                    docente.getMiembro().setApellidosCompletos(docenteDetails.getMiembro().getApellidosCompletos());
                    docente.getMiembro().setFechaNacimiento(docenteDetails.getMiembro().getFechaNacimiento());
                    docente.getMiembro().setSexo(docenteDetails.getMiembro().getSexo());
                    docente.getMiembro().setDireccionHabitacion(docenteDetails.getMiembro().getDireccionHabitacion());
                    docente.getMiembro().setTelefonoPersonal(docenteDetails.getMiembro().getTelefonoPersonal());
                    docente.getMiembro().setCorreoInstitucional(docenteDetails.getMiembro().getCorreoInstitucional());
                    docente.getMiembro().setFechaApertura(docenteDetails.getMiembro().getFechaApertura());
                }
            }
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

/**
 * SOLO CONSULTA: Edificacion representa infraestructura física real
 * que no se crea, edita ni elimina desde la aplicación web — solo
 * cambia por eventos físicos (construcción, demolición, remodelación)
 * gestionados directamente en la base de datos.
 */
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
            // Update fields via miembro composition
            if (egresadoDetails.getMiembro() != null) {
                if (egresado.getMiembro() == null) {
                    egresado.setMiembro(egresadoDetails.getMiembro());
                } else {
                    egresado.getMiembro().setNombresCompletos(egresadoDetails.getMiembro().getNombresCompletos());
                    egresado.getMiembro().setApellidosCompletos(egresadoDetails.getMiembro().getApellidosCompletos());
                    egresado.getMiembro().setFechaNacimiento(egresadoDetails.getMiembro().getFechaNacimiento());
                    egresado.getMiembro().setSexo(egresadoDetails.getMiembro().getSexo());
                    egresado.getMiembro().setDireccionHabitacion(egresadoDetails.getMiembro().getDireccionHabitacion());
                    egresado.getMiembro().setTelefonoPersonal(egresadoDetails.getMiembro().getTelefonoPersonal());
                    egresado.getMiembro().setCorreoInstitucional(egresadoDetails.getMiembro().getCorreoInstitucional());
                    egresado.getMiembro().setFechaApertura(egresadoDetails.getMiembro().getFechaApertura());
                }
            }
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

/**
 * SOLO CONSULTA: Entidad_Prestadora (Interna/Externa) es un dato
 * institucional gestionado directamente en la base de datos — no se
 * crea, edita ni elimina desde la app web.
 */
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

/**
 * SOLO CONSULTA: EspacioFisico representa infraestructura física real
 * que no se crea, edita ni elimina desde la aplicación web. El campo
 * "estatus" (Disponible/Ocupado/Mantenimiento) SÍ es dinámico, pero
 * cambia automáticamente vía los triggers de PostgreSQL al crear o
 * cerrar una Solicitud_Servicio — no a través de un endpoint manual
 * de edición aquí.
 */
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
            if (estudianteDetails.getMiembro() != null) {
                if (estudiante.getMiembro() == null) {
                    estudiante.setMiembro(estudianteDetails.getMiembro());
                } else {
                    estudiante.getMiembro().setNombresCompletos(estudianteDetails.getMiembro().getNombresCompletos());
                    estudiante.getMiembro().setApellidosCompletos(estudianteDetails.getMiembro().getApellidosCompletos());
                    estudiante.getMiembro().setFechaNacimiento(estudianteDetails.getMiembro().getFechaNacimiento());
                    estudiante.getMiembro().setSexo(estudianteDetails.getMiembro().getSexo());
                    estudiante.getMiembro().setDireccionHabitacion(estudianteDetails.getMiembro().getDireccionHabitacion());
                    estudiante.getMiembro().setTelefonoPersonal(estudianteDetails.getMiembro().getTelefonoPersonal());
                    estudiante.getMiembro().setCorreoInstitucional(estudianteDetails.getMiembro().getCorreoInstitucional());
                    estudiante.getMiembro().setFechaApertura(estudianteDetails.getMiembro().getFechaApertura());
                }
            }
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

import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Endpoints de gestión de Miembro.
 *
 * GET  /api/miembros/{cedula}        -> consultar por cédula
 * GET  /api/miembros/correo/{correo} -> consultar por correo
 * GET  /api/miembros/buscar?texto=.. -> consultar por nombre/apellido
 *
 * No hay PUT/DELETE genéricos de "actualizar cualquier campo": antes
 * permitían mandar un claveHash arbitrario desde el cliente porque
 * recibían la entidad Miembro completa. La edición de perfil del
 * usuario se debe manejar con endpoints específicos más adelante.
 */
@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    @Autowired
    private MiembroService miembroService;

    @GetMapping("/{cedula}")
    public ResponseEntity<?> buscarPorCedula(@PathVariable String cedula) {
        try {
            return ResponseEntity.ok(miembroService.buscarPorCedula(cedula));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {
        try {
            return ResponseEntity.ok(miembroService.buscarPorCorreo(correo));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MiembroDetalleDTO>> buscarPorNombreOApellido(@RequestParam String texto) {
        return ResponseEntity.ok(miembroService.buscarPorNombreOApellido(texto));
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
        // ✅ Se recibe Sede directo y se evalúa si es null
        Sede sede = sedeService.findById(id);
        return sede != null ? ResponseEntity.ok(sede) : ResponseEntity.notFound().build();
    }
}
```

## src/main/java/com/ucab/ucab_services/controller/ServicioController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Servicio;
import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.service.ServicioService;
import com.ucab.ucab_services.service.AsignadoEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // INYECTAMOS EL SERVICIO DE ASIGNACIONES PARA BUSCAR LOS CUPOS Y UBICACIÓN
    @Autowired
    private AsignadoEnService asignadoEnService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.findAll();
    }

    // <-- CAMBIO MÁGICO AQUÍ: Retornamos un Map con los espacios físicos anidados -->
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getServicioById(@PathVariable String id) {
        Servicio servicio = servicioService.findById(id);

        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("codigoServicio", servicio.getCodigoServicio());
        response.put("descripcionDetallada", servicio.getDescripcionDetallada());
        response.put("requisitos", servicio.getRequisitos());
        response.put("estadoServicio", servicio.getEstadoServicio());
        response.put("perfilSolicitante", servicio.getPerfilSolicitante());

        // Buscamos todos los espacios físicos asignados a este servicio
        List<AsignadoEn> asignaciones = asignadoEnService.findAll().stream()
                .filter(a -> a.getId().getCodigoServicio().equals(id))
                .collect(Collectors.toList());

        List<Map<String, Object>> espacios = new ArrayList<>();

        for (AsignadoEn asig : asignaciones) {
            if (asig.getEspacioFisico() != null) {
                Map<String, Object> esp = new HashMap<>();
                esp.put("nombreEdif", asig.getEspacioFisico().getId().getNombreEdif());
                esp.put("direccion", asig.getEspacioFisico().getId().getDireccionInterna());
                esp.put("capacidadAforo", asig.getEspacioFisico().getCapacidadAforo());
                esp.put("tipoInmobiliario", asig.getEspacioFisico().getTipoInmobiliario());
                esp.put("estatus", asig.getEspacioFisico().getEstatus());
                espacios.add(esp);
            }
        }

        // Empaquetamos los espacios en la respuesta
        response.put("espacios", espacios);

        return ResponseEntity.ok(response);
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
        servicio.setDescripcionDetallada(servicioDetails.getDescripcionDetallada());
        servicio.setRequisitos(servicioDetails.getRequisitos());
        servicio.setEstadoServicio(servicioDetails.getEstadoServicio());
        servicio.setPerfilSolicitante(servicioDetails.getPerfilSolicitante());

        return ResponseEntity.ok(servicioService.save(servicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable String id) {
        servicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```
##src/main/java/com/ucab_services/controller/PagoController.java

```java
package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.dto.PagoMovilRequest;
import com.ucab.ucab_services.entity.Pago;
import com.ucab.ucab_services.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/pago-movil")
    public ResponseEntity<Map<String, String>> registrarPagoMovil(
            @Valid @RequestBody PagoMovilRequest request) {

        try {
            // 1. Crear el objeto Pago con datos dummy
            Pago pago = new Pago();

            // 2. Lógica para el ID
            String nuevoId = "PGO-" + System.currentTimeMillis();
            pago.setIdentificador(nuevoId);

            // 3. Datos fijos hasta integrar confolio
            pago.setMonto(null); // Se actualizará al crear el FolioPago
            pago.setTipoMoneda("USD");
            pago.setMetodoPago("PAGO_MOVIL");
            pago.setEstatusPago("PENDIENTE");

            // 4. Insertar los datos del request
            pago.setBancoEmisor(request.getBancoEmisor());
            pago.setTelefonoEmisor(request.getTelefonoEmisor());
            pago.setReferencia(request.getReferencia());

            // 5. Crear el vínculo con el Folio (FK)
            pago.setFolio(request.getFolioId());

            // Guardar en BD
            pagoService.save(pago);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Pago registrado exitosamente.");
            response.put("identificador", nuevoId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al registrar el pago: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitudes-servicio")
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudServicioController {

    @Autowired
    private SolicitudServicioService solicitudServicioService;

    // --- CAMBIO APLICADO AQUÍ: Devolvemos un List<Map> en vez de entidades completas ---
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllSolicitudesServicio() {
        List<SolicitudServicio> solicitudes = solicitudServicioService.findAll();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (SolicitudServicio sol : solicitudes) {
            Map<String, Object> map = new HashMap<>();
            map.put("identificador", sol.getIdentificador());
            map.put("estadoActual", sol.getEstadoActual());
            map.put("fechaCreacion", sol.getFechaCreacion());

            // Extraemos solo la cédula para evitar el error de CategoriaFidelidad (LazyInitialization)
            if (sol.getMiembro() != null) {
                Map<String, String> miembroMap = new HashMap<>();
                miembroMap.put("cedulaMiembro", sol.getMiembro().getCedulaMiembro());
                map.put("miembro", miembroMap);
            }

            // Extraemos solo el código del servicio
            if (sol.getServicio() != null) {
                Map<String, String> servicioMap = new HashMap<>();
                servicioMap.put("codigoServicio", sol.getServicio().getCodigoServicio());
                map.put("servicio", servicioMap);
            }

            responseList.add(map);
        }

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudServicio> getSolicitudServicioById(@PathVariable String id) {
        SolicitudServicio solicitudServicio = solicitudServicioService.findById(id);
        return solicitudServicio != null ? ResponseEntity.ok(solicitudServicio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createSolicitudServicio(@RequestBody SolicitudServicio solicitudServicio) {

        // 1. Forzamos el ID corto
        String nuevoId = "SOL-" + System.currentTimeMillis();
        solicitudServicio.setIdentificador(nuevoId);

        // 2. Prevenimos el null de la fecha de creación
        if(solicitudServicio.getFechaCreacion() == null) {
            solicitudServicio.setFechaCreacion(LocalDateTime.now());
        }

        // 3. Guardamos la solicitud
        solicitudServicioService.save(solicitudServicio);

        // 4. Devolvemos un JSON simple en lugar del objeto complejo
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Solicitud creada con éxito");
        response.put("identificador", nuevoId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudServicio> updateSolicitudServicio(@PathVariable String id, @RequestBody SolicitudServicio solicitudServicioDetails) {
        SolicitudServicio solicitudServicio = solicitudServicioService.findById(id);
        if (solicitudServicio == null) {
            return ResponseEntity.notFound().build();
        }
        solicitudServicio.setEstadoActual(solicitudServicioDetails.getEstadoActual());
        solicitudServicio.setFechaInicio(solicitudServicioDetails.getFechaInicio());
        solicitudServicio.setFechaFin(solicitudServicioDetails.getFechaFin());

        return ResponseEntity.ok(solicitudServicioService.save(solicitudServicio));
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

/**
 * SOLO CONSULTA: Tarifa_Servicio es un dato institucional (precios,
 * límites de costo por perfil/sede) que la UCAB gestiona directamente
 * en la base de datos — no se crea, edita ni elimina desde la app web.
 */
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
}
```

## src/main/java/com/ucab/ucab_services/dto/LoginRequest.java

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

## src/main/java/com/ucab/ucab_services/dto/PagoMovilRequest.java

```java
package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagoMovilRequest {

    @NotNull(message = "El folioId es obligatorio.")
    private Integer folioId;

    @NotBlank(message = "El banco emisor es obligatorio.")
    @Size(max = 50, message = "El banco no puede exceder 50 caracteres.")
    private String bancoEmisor;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "El teléfono debe contener entre 10 y 11 dígitos.")
    private String telefonoEmisor;

    @NotBlank(message = "La referencia es obligatoria.")
    @Size(min = 4, max = 10, message = "La referencia debe tener entre 4 y 10 caracteres.")
    private String referencia;
}
```

## src/main/java/com/ucab/ucab_services/dto/LoginResponse.java

```java
package com.ucab.ucab_services.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private List<String> roles;
    private String nombre;
    private String correo;
    private String cedulaMiembro;

    @JsonProperty("requiereMfa") // Evita discrepancias con el generador de Lombok
    private boolean requiereMfa;

    private String mensaje;
}
```

## src/main/java/com/ucab/ucab_services/dto/MiembroDetalleDTO.java

```java
package com.ucab.ucab_services.dto;

import com.ucab.ucab_services.entity.RolMiembro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Vista detallada de un Miembro para pantallas de consulta
 * (ej. "consultar-miembro"). A diferencia de MiembroSesionDTO
 * (pensado para la sesión de login), este incluye más campos,
 * pero igualmente nunca expone claveHash.
 */
@Getter
@Setter
@AllArgsConstructor
public class MiembroDetalleDTO {
    private String cedulaMiembro;
    private String nombresCompletos;
    private String apellidosCompletos;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String estadoCuenta;
    private String direccionHabitacion;
    private String correoInstitucional;
    private String telefonoPersonal;
    private LocalDateTime ultimaConexion;
    private BigDecimal indiceRecurrencia;
    private LocalDate fechaApertura;
    private String tipoCategoria; // puede ser null
    private RolMiembro rol;
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
    private String rol; // <-- Aquí el Frontend en Angular sabrá si es Estudiante, Profesor, etc.
}
```

## src/main/java/com/ucab/ucab_services/dto/VerificarMfaRequest.java

```java
package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VerificarMfaRequest {
    @NotBlank(message = "Cédula es requerida")
    @Size(min = 1, max = 20, message = "Cédula debe tener entre 1 y 20 caracteres")
    private String cedula;

    @NotBlank(message = "Código es requerido")
    @Size(min = 6, max = 6, message = "Código debe tener exactamente 6 caracteres")
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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Becario. Especialización de Estudiante (no de
 * Miembro directamente) vía composición.
 */
@Entity
@Table(name = "becario")
@Getter @Setter @NoArgsConstructor
public class Becario {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Estudiante estudiante;

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

import com.fasterxml.jackson.annotation.JsonIgnore; // <-- IMPORTACIÓN AÑADIDA
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BilleteraTai {

    @Id
    @Column(name = "uid", nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uid;

    // <-- ANOTACIÓN AÑADIDA AQUÍ -->
    @JsonIgnore
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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Docente. Especialización de Miembro vía composición.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Docente {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

    @Column(name = "codigo_investigador", length = 50)
    private String codigoInvestigador;

    @Column(name = "escalafon_docente", length = 100)
    private String escalafonDocente;

    @Column(name = "carga_semanal", precision = 4, scale = 2)
    private BigDecimal cargaSemanal;

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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Egresado. Especialización de Miembro vía
 * composición. Datos permanentes que coexisten con cualquier nueva
 * vinculación que el miembro inicie en el futuro.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Egresado {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

    @Column(name = "titulo", length = 200)
    private String titulo;

    @Column(name = "ano_graduacion")
    private Integer anoGraduacion;

    @Column(name = "indice_academico", precision = 4, scale = 2)
    private BigDecimal indiceAcademico;

}

```

## src/main/java/com/ucab/ucab_services/entity/EntidadPrestadora.java

```java
package com.ucab.ucab_services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Entidad_Prestadora. Tabla padre de la especialización
 * Interna/Externa vía composición (@OneToOne + @MapsId en los
 * subtipos), NO herencia de Java — mismo estándar usado en Miembro,
 * para mantener consistencia en todo el proyecto.
 */
@Entity
@Table(name = "entidad_prestadora")
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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Estudiante. Especialización de Miembro vía
 * composición (@OneToOne + @MapsId), NO herencia de Java — mismo
 * estándar usado en EntidadPrestadora/Interna/Externa.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Estudiante {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Externa. Aliados comerciales o concesionarios
 * externos a la UCAB (RIF, razón social, contrato de concesión).
 */
@Entity
@Table(name = "externa")
@Getter @Setter @NoArgsConstructor
public class Externa {

    @Id
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_entidad")
    private EntidadPrestadora entidadPrestadora;

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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Mapea la tabla Interna. Entidades prestadoras propias de la UCAB
 * (identificadas por código presupuestario y director de oficina).
 */
@Entity
@Table(name = "interna")
@Getter @Setter @NoArgsConstructor
public class Interna {

    @Id
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_entidad")
    private EntidadPrestadora entidadPrestadora;

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

    // ¡AQUÍ SE RESUELVE EL PUNTO #1 DE LA REVISIÓN! El hash jamás viajará al Frontend
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

    // AÑADIDO: Ahora tipo_pago forma parte de la clave primaria compuesta
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
import jakarta.persistence.Entity; // ✅ IMPORTACIÓN AÑADIDA
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity // ✅ ANOTACIÓN FALTANTE AÑADIDA
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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Personal_Administrativo. Especialización de Miembro
 * vía composición.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class PersonalAdministrativo {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Miembro miembro;

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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Mapea la tabla Preparador. Especialización de Estudiante (no de
 * Miembro directamente) vía composición — su PK es FK hacia
 * Estudiante.cedula_miembro, requiere que esa fila ya exista.
 */
@Entity
@Table(name = "preparador")
@Getter @Setter @NoArgsConstructor
public class Preparador {

    @Id
    @Column(name = "cedula_miembro", length = 20)
    private String cedulaMiembro;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cedula_miembro")
    private Estudiante estudiante;

    @Column(name = "asignatura_asignada", length = 150)
    private String asignaturaAsignada;

    @Column(name = "horas_ayudantia", precision = 5, scale = 2)
    private BigDecimal horasAyudantia;

}

```

## src/main/java/com/ucab/ucab_services/entity/RolMiembro.java

```java
package com.ucab.ucab_services.entity;

/**
 * Roles detectables a partir del subdominio del correo institucional.
 *
 * CONVENCIÓN DE PROYECTO (decisión de diseño, no es una convención
 * oficial de la UCAB real — este es un proyecto académico):
 *
 *   est.    -> ESTUDIANTE              (ej: ana.lopez@est.ucab.edu.ve)
 *   egr.    -> EGRESADO                (ej: carlos.perez@egr.ucab.edu.ve)
 *   prof.   -> DOCENTE                 (ej: pedro.martinez@prof.ucab.edu.ve)
 *   adm.    -> PERSONAL_ADMINISTRATIVO (ej: maria.sanchez@adm.ucab.edu.ve)
 *   admin.  -> ADMIN_SISTEMA           (ej: usuario@admin.ucab.edu.ve)
 *
 * El correo es la fuente de verdad RÁPIDA para decidir qué interfaz
 * mostrar en el frontend (evita consultar las 6 tablas de subtipos en
 * cada login/consulta). No se valida contra las tablas de subtipos.
 */
public enum RolMiembro {
    ESTUDIANTE("est."),
    EGRESADO("egr."),
    DOCENTE("prof."),
    PERSONAL_ADMINISTRATIVO("adm."),
    ADMIN_SISTEMA("admin."),
    DESCONOCIDO(null);

    private final String prefijo;

    RolMiembro(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPrefijo() {
        return prefijo;
    }

    /**
     * Detecta el rol a partir del subdominio en el correo institucional.
     * Ej: detectarDesdeCorreo("maria.sanchez@adm.ucab.edu.ve") -> PERSONAL_ADMINISTRATIVO
     *
     * IMPORTANTE: se usa contains("@adm.") (subdominio), NO
     * startsWith("adm.") (prefijo) — la convención de correo cambió
     * de prefijo a subdominio en una iteración posterior del proyecto.
     */
    public static RolMiembro detectarDesdeCorreo(String correoInstitucional) {
        if (correoInstitucional == null) {
            return DESCONOCIDO;
        }

        String correoMinusculas = correoInstitucional.toLowerCase();

        if (correoMinusculas.contains("@admin.")) {
            return ADMIN_SISTEMA;
        }
        if (correoMinusculas.contains("@adm.")) {
            return PERSONAL_ADMINISTRATIVO;
        }
        if (correoMinusculas.contains("@est.")) {
            return ESTUDIANTE;
        }
        if (correoMinusculas.contains("@egr.")) {
            return EGRESADO;
        }
        if (correoMinusculas.contains("@prof.")) {
            return DOCENTE;
        }
        return DESCONOCIDO;
    }
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, String> {
    // Spring Data JPA deduce la consulta por el nombre del atributo exacto
    Optional<Miembro> findByCorreoInstitucional(String correoInstitucional);

    boolean existsByCorreoInstitucional(String correoInstitucional);

    @Query(value = """
            SELECT * FROM miembro
            WHERE nombres_completos ILIKE CONCAT('%', :texto, '%')
               OR apellidos_completos ILIKE CONCAT('%', :texto, '%')
            """, nativeQuery = true)
    List<Miembro> buscarPorNombreOApellido(@Param("texto") String texto);

    // ────────────────────────────────────────────────────────────
    // Llamadas a las funciones de seguridad de PostgreSQL.
    // NUNCA se compara/desencripta la contraseña en Java: siempre
    // se delega en la función de la base de datos, que usa pgcrypto.
    // Esta es la fuente de verdad ÚNICA para hash/verificación de
    // contraseñas en el proyecto (no se usa PasswordEncoder de Java).
    // ────────────────────────────────────────────────────────────

    @Query(value = "SELECT fn_verificar_clave(:cedula, :clavePlana)", nativeQuery = true)
    boolean verificarClave(@Param("cedula") String cedula, @Param("clavePlana") String clavePlana);

    @Query(value = "SELECT fn_generar_codigo_mfa(:cedula)", nativeQuery = true)
    String generarCodigoMfa(@Param("cedula") String cedula);

    @Query(value = "SELECT fn_verificar_codigo_mfa(:cedula, :codigo)", nativeQuery = true)
    boolean verificarCodigoMfa(@Param("cedula") String cedula, @Param("codigo") String codigo);

    /**
     * Llama a fn_establecer_clave(cedula, clave_plana) -> void.
     * NO se usa @Modifying aquí: la función se ejecuta como SELECT,
     * por lo que el driver JDBC siempre espera un result set;
     * @Modifying causa el error "Se retornó un resultado cuando no
     * se esperaba ninguno".
     */
    @Query(value = "SELECT fn_establecer_clave(:cedula, :clavePlana)", nativeQuery = true)
    void establecerClave(@Param("cedula") String cedula, @Param("clavePlana") String clavePlana);
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
    // Hereda automáticamente métodos como existsById(String id)
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

## src/main/java/com/ucab/ucab_services/service/BcvService.java

```java
package com.ucab.ucab_services.service;

public interface BcvService {
    double getBcvRate();
}

## src/main/java/com/ucab/ucab_services/service/PagoService.java

package com.ucab.ucab_services.service;

import com.ucab.ucab_services.dto.PagoMovilRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class PagoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String procesarPagoMovil(PagoMovilRequest request) {

        // 1. SOLUCIÓN AL ERROR: Calculamos el subtotal sumando los cargos del folio
        // Usamos COALESCE para que, si no hay cargos (o es null), devuelva 0
        Double subtotal = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(monto), 0) FROM Cargo WHERE identificador = ?",
                Double.class, request.getFolioId()
        );

        // Aplicamos la regla de seguridad: Si la BD da 0, forzamos $25.00
        if (subtotal == null || subtotal == 0) {
            subtotal = 25.00;
        }

        Double impuesto = subtotal * 0.16;
        Double totalNeto = subtotal + impuesto;

        // 2. Generamos código único de Factura
        String numFactura = "FCT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        jdbcTemplate.update(
                "INSERT INTO factura_procesada (numero_control_factura, identificador, fecha_emision, total_monto, monto_impuesto) VALUES (?, ?, CURRENT_DATE, ?, ?)",
                numFactura, request.getFolioId(), totalNeto, impuesto
        );

        // 3. Cerramos el Folio
        jdbcTemplate.update(
                "UPDATE folio_consumo SET estado_folio = 'Facturado' WHERE identificador = ?",
                request.getFolioId()
        );

        // 4. Registramos el Pago y su detalle en Pago_Movil con el MISMO TIMESTAMP
        Timestamp now = new Timestamp(System.currentTimeMillis());

        jdbcTemplate.update(
                "INSERT INTO pago (numero_control_factura, fecha_operacion, tipo_pago) VALUES (?, ?, 'Pago_Movil')",
                numFactura, now
        );

        jdbcTemplate.update(
                "INSERT INTO pago_movil (numero_control_factura, fecha_operacion, tipo_pago, numero_referencia, telefono_origen, banco_origen) VALUES (?, ?, 'Pago_Movil', ?, ?, ?)",
                numFactura, now, request.getReferencia(), request.getTelefonoEmisor(), request.getBancoEmisor()
        );

        // 5. ACTUALIZAMOS EL ESTADO DEL SERVICIO A "COMPLETADA"
        jdbcTemplate.update(
                "UPDATE solicitud_servicio SET estado_servicio = 'Completada' WHERE identificador = ?",
                request.getFolioId()
        );

        return numFactura;
    }
}

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
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos."));

        boolean claveValida = miembroRepository.verificarClave(miembro.getCedulaMiembro(), request.getClave());

        if (!claveValida) {
            int intentosActuales = miembro.getIntentosFallidos() != null ? miembro.getIntentosFallidos() : 0;
            miembro.setIntentosFallidos(intentosActuales + 1);
            miembroRepository.save(miembro);
            throw new RuntimeException("Usuario o contraseña incorrectos.");
        }

        miembro.setIntentosFallidos(0);
        miembroRepository.save(miembro);

        validarEstadoCuenta(miembro);

        if (Boolean.TRUE.equals(miembro.getMfaHabilitado())) {
            String codigo = miembroRepository.generarCodigoMfa(miembro.getCedulaMiembro());
            LOG.info("[MFA] Código para " + miembro.getCorreoInstitucional() + ": " + codigo);

            LoginResponse response = new LoginResponse();
            response.setRequiereMfa(true);
            response.setCedulaMiembro(miembro.getCedulaMiembro());
            response.setMensaje("Te enviamos un código de verificación a tu correo institucional.");
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
            throw new RuntimeException("Código incorrecto o expirado.");
        }

        validarEstadoCuenta(miembro);

        return construirRespuestaCompleta(miembro);
    }

    private void validarEstadoCuenta(Miembro miembro) {
        switch (miembro.getEstadoCuenta()) {
            case "Suspendida" -> throw new RuntimeException(
                    "Tu cuenta está suspendida. Contacta a la administración.");
            case "Bloqueada" -> throw new RuntimeException(
                    "Tu cuenta está bloqueada por intentos fallidos. Contacta a la administración.");
            default -> { /* 'Activa' */ }
        }
    }

    private LoginResponse construirRespuestaCompleta(Miembro miembro) {
        List<String> roles = determinarRoles(miembro.getCedulaMiembro());

        LoginResponse response = new LoginResponse();
        response.setRequiereMfa(false);
        response.setToken("dummy-jwt-token-generado");
        response.setRoles(roles);
        response.setNombre(miembro.getNombresCompletos() + " " + miembro.getApellidosCompletos());
        response.setCorreo(miembro.getCorreoInstitucional());
        response.setCedulaMiembro(miembro.getCedulaMiembro());
        response.setMensaje("Login exitoso.");
        return response;
    }

    private List<String> determinarRoles(String cedula) {
        List<String> roles = new ArrayList<>();

        if (estudianteRepository.existsById(cedula)) {
            roles.add("ESTUDIANTE");
        } else if (docenteRepository.existsById(cedula)) {
            roles.add("DOCENTE");
        } else if (personalAdministrativoRepository.existsById(cedula)) {
            roles.add("PERSONAL_ADMINISTRATIVO");
        } else {
            throw new RuntimeException("Acceso denegado: su rol no tiene permisos para ingresar a la aplicación Web.");
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
    
    // ✅ CAMBIO AQUÍ: Actualizado para coincidir con la implementación y el repositorio
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

import com.ucab.ucab_services.dto.MiembroDetalleDTO;

import java.util.List;

public interface MiembroService {
    MiembroDetalleDTO buscarPorCedula(String cedula);
    MiembroDetalleDTO buscarPorCorreo(String correo);
    List<MiembroDetalleDTO> buscarPorNombreOApellido(String texto);
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

import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.entity.RolMiembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Gestión de Miembro. No existe creación vía API pública.
 *
 * DECISIÓN DE ARQUITECTURA: el hash de contraseña se genera SIEMPRE
 * vía fn_establecer_clave() (PostgreSQL/pgcrypto) — NO se usa
 * BCryptPasswordEncoder de Java aquí. Esto evita tener dos sistemas
 * de hash compitiendo con formatos potencialmente incompatibles.
 */
@Service
public class MiembroServiceImpl implements MiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    @Transactional(readOnly = true)
    public MiembroDetalleDTO buscarPorCedula(String cedula) {
        Miembro miembro = miembroRepository.findById(cedula)
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encontró ningún miembro con la cédula " + cedula));
        return aDetalleDTO(miembro);
    }

    @Override
    @Transactional(readOnly = true)
    public MiembroDetalleDTO buscarPorCorreo(String correo) {
        Miembro miembro = miembroRepository.findByCorreoInstitucional(correo)
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encontró ningún miembro con el correo " + correo));
        return aDetalleDTO(miembro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MiembroDetalleDTO> buscarPorNombreOApellido(String texto) {
        return miembroRepository.buscarPorNombreOApellido(texto)
                .stream()
                .map(this::aDetalleDTO)
                .toList();
    }

    private MiembroDetalleDTO aDetalleDTO(Miembro miembro) {
        String tipoCategoria = miembro.getTipoCategoria() != null
                ? miembro.getTipoCategoria().getTipoCategoria()
                : null;

        // Antes esto se dejaba como null fijo, lo que rompía el frontend
        // (Profile no podía decidir qué sección mostrar). Se calcula el
        // rol real a partir del subdominio del correo institucional.
        RolMiembro rol = RolMiembro.detectarDesdeCorreo(miembro.getCorreoInstitucional());

        return new MiembroDetalleDTO(
                miembro.getCedulaMiembro(),
                miembro.getNombresCompletos(),
                miembro.getApellidosCompletos(),
                miembro.getSexo(),
                miembro.getFechaNacimiento() != null ? miembro.getFechaNacimiento().toLocalDate() : null,
                miembro.getEstadoCuenta(),
                miembro.getDireccionHabitacion(),
                miembro.getCorreoInstitucional(),
                miembro.getTelefonoPersonal(),
                miembro.getUltimaConexion() != null ? miembro.getUltimaConexion().toLocalDateTime() : null,
                miembro.getIndiceRecurrencia(),
                miembro.getFechaApertura() != null ? miembro.getFechaApertura().toLocalDate() : null,
                tipoCategoria,
                rol
        );
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

## src/main/resources/application.properties

```ini
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

## src/test/java/com/ucab/ucab_services/controller/AuthControllerIntegrationTest.java

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

## src/test/java/com/ucab/ucab_services/controller/AuthControllerTest.java

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
        com.ucab.ucab_services.entity.Miembro miembro1 = new com.ucab.ucab_services.entity.Miembro();
        miembro1.setCedulaMiembro("12345678");
        miembro1.setNombresCompletos("Juan");
        miembro1.setApellidosCompletos("Perez");
        estudiante1.setMiembro(miembro1);
        estudiante1.setPromedio(new BigDecimal("4.5")); // ✅ CORREGIDO A BIGDECIMAL

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setCedulaMiembro("87654321");
        com.ucab.ucab_services.entity.Miembro miembro2 = new com.ucab.ucab_services.entity.Miembro();
        miembro2.setCedulaMiembro("87654321");
        miembro2.setNombresCompletos("Maria");
        miembro2.setApellidosCompletos("Gonzalez");
        estudiante2.setMiembro(miembro2);
        estudiante2.setPromedio(new BigDecimal("3.8")); // ✅ CORREGIDO A BIGDECIMAL

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
        com.ucab.ucab_services.entity.Miembro miembro = new com.ucab.ucab_services.entity.Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");
        estudiante.setMiembro(miembro);
        estudiante.setPromedio(new BigDecimal("4.5")); // ✅ CORREGIDO A BIGDECIMAL

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
        com.ucab.ucab_services.entity.Miembro miembro3 = new com.ucab.ucab_services.entity.Miembro();
        miembro3.setCedulaMiembro("12345678");
        miembro3.setNombresCompletos("Juan");
        miembro3.setApellidosCompletos("Perez");
        estudiante.setMiembro(miembro3);
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

import com.ucab.ucab_services.dto.MiembroDetalleDTO;
import com.ucab.ucab_services.entity.Miembro;
import com.ucab.ucab_services.repository.MiembroRepository;
import com.ucab.ucab_services.service.impl.MiembroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void testBuscarPorCedula() {
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");
        miembro.setCorreoInstitucional("juan.perez@ucab.edu");

        when(miembroRepository.findById("12345678")).thenReturn(Optional.of(miembro));

        MiembroDetalleDTO result = miembroService.buscarPorCedula("12345678");

        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        assertEquals("juan.perez@ucab.edu", result.getCorreoInstitucional());
        verify(miembroRepository, times(1)).findById("12345678");
    }

    @Test
    void testBuscarPorCorreo() {
        Miembro miembro = new Miembro();
        miembro.setCedulaMiembro("12345678");
        miembro.setNombresCompletos("Juan");
        miembro.setApellidosCompletos("Perez");
        miembro.setCorreoInstitucional("juan.perez@ucab.edu");

        when(miembroRepository.findByCorreoInstitucional("juan.perez@ucab.edu")).thenReturn(Optional.of(miembro));

        MiembroDetalleDTO result = miembroService.buscarPorCorreo("juan.perez@ucab.edu");

        assertNotNull(result);
        assertEquals("12345678", result.getCedulaMiembro());
        assertEquals("Juan", result.getNombresCompletos());
        assertEquals("Perez", result.getApellidosCompletos());
        assertEquals("juan.perez@ucab.edu", result.getCorreoInstitucional());
        verify(miembroRepository, times(1)).findByCorreoInstitucional("juan.perez@ucab.edu");
    }

    @Test
    void testBuscarPorNombreOApellido() {
        Miembro miembro1 = new Miembro();
        miembro1.setCedulaMiembro("12345678");
        miembro1.setNombresCompletos("Juan");
        miembro1.setApellidosCompletos("Perez");

        Miembro miembro2 = new Miembro();
        miembro2.setCedulaMiembro("87654321");
        miembro2.setNombresCompletos("Maria");
        miembro2.setApellidosCompletos("Gonzalez");

        when(miembroRepository.buscarPorNombreOApellido("Juan")).thenReturn(List.of(miembro1, miembro2));

        List<MiembroDetalleDTO> result = miembroService.buscarPorNombreOApellido("Juan");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("12345678", result.get(0).getCedulaMiembro());
        assertEquals("87654321", result.get(1).getCedulaMiembro());
        verify(miembroRepository, times(1)).buscarPorNombreOApellido("Juan");
    }
}

```

