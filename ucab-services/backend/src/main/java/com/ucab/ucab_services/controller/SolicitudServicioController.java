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

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

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

        // 3.5. Creamos automáticamente el Folio_Consumo asociado y un cargo base para que pueda ser pagado
        try {
            jdbcTemplate.update("INSERT INTO Folio_Consumo (Identificador, Fecha_apertura, Estado_cierre) VALUES (?, CURRENT_DATE, 'Abierto')", nuevoId);
            
            // Asignamos un precio base (ej: 50.00 + 8.00 IVA). 
            // Esto luego puede conectarse con la tabla Tarifa_Servicio según el perfil y servicio.
            jdbcTemplate.update("INSERT INTO Item_consumo (Identificador, Fecha_apertura, Concepto, Precio_unitario, Cantidad, Impuesto, Fecha_cargo) VALUES (?, CURRENT_DATE, ?, 50.00, 1, 8.00, CURRENT_DATE)", 
                nuevoId, "Consumo por servicio: " + solicitudServicio.getServicio().getCodigoServicio());
                
            System.out.println("Folio e Item de consumo creados para la nueva solicitud: " + nuevoId);
        } catch (Exception e) {
            System.err.println("Error creando Folio_Consumo o Item_consumo inicial: " + e.getMessage());
        }

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