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