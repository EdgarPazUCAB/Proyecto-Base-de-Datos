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

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllSolicitudesServicio() {
        List<SolicitudServicio> solicitudes = solicitudServicioService.findAll();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (SolicitudServicio sol : solicitudes) {
            Map<String, Object> map = new HashMap<>();
            map.put("identificador", sol.getIdentificador());
            map.put("estadoActual", sol.getEstadoActual());
            map.put("fechaCreacion", sol.getFechaCreacion());

            if (sol.getMiembro() != null) {
                Map<String, String> miembroMap = new HashMap<>();
                miembroMap.put("cedulaMiembro", sol.getMiembro().getCedulaMiembro());
                map.put("miembro", miembroMap);
            }

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

        String nuevoId = "SOL-" + System.currentTimeMillis();
        solicitudServicio.setIdentificador(nuevoId);

        if(solicitudServicio.getFechaCreacion() == null) {
            solicitudServicio.setFechaCreacion(LocalDateTime.now());
        }

        solicitudServicioService.save(solicitudServicio);

        try {
            jdbcTemplate.update("INSERT INTO Folio_Consumo (Identificador, Fecha_apertura, Estado_cierre) VALUES (?, CURRENT_DATE, 'Abierto')", nuevoId);

            jdbcTemplate.update("INSERT INTO Item_consumo (Identificador, Fecha_apertura, Concepto, Precio_unitario, Cantidad, Impuesto, Fecha_cargo) VALUES (?, CURRENT_DATE, ?, 50.00, 1, 8.00, CURRENT_DATE)",
                    nuevoId, "Consumo por servicio: " + solicitudServicio.getServicio().getCodigoServicio());

            System.out.println("Folio e Item de consumo creados para la nueva solicitud: " + nuevoId);
        } catch (Exception e) {
            System.err.println("Error creando Folio_Consumo o Item_consumo inicial: " + e.getMessage());
        }

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

    // --- NUEVO ENDPOINT PARA CANCELAR LA SOLICITUD ---
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, String>> cancelarSolicitud(@PathVariable String id) {
        SolicitudServicio solicitud = solicitudServicioService.findById(id);
        if (solicitud == null) {
            return ResponseEntity.notFound().build();
        }

        solicitud.setEstadoActual("Cancelada");
        solicitud.setFechaFin(java.time.LocalDate.now()); // Registramos el final explícitamente desde Java también
        solicitudServicioService.save(solicitud);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Solicitud cancelada con éxito");
        response.put("identificador", id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitudServicio(@PathVariable String id) {
        solicitudServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}