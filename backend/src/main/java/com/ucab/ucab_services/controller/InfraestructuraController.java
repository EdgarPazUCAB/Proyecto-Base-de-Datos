package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.Sede;
import com.ucab.ucab_services.service.InfraestructuraService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints de SOLO CONSULTA para infraestructura física.
 *
 * Sede, Edificacion y EspacioFisico no tienen endpoints de creación,
 * edición ni borrado: son estructuras físicas reales que solo cambian
 * por eventos físicos (construcción, demolición, remodelación), y se
 * gestionan directamente en la base de datos, no desde la aplicación
 * web. Estos endpoints existen para que otras pantallas (ej. reservar
 * un servicio en un espacio) puedan listar las opciones disponibles.
 */
@RestController
@RequestMapping("/api/infraestructura")
@CrossOrigin(origins = "http://localhost:4200")
public class InfraestructuraController {

    private final InfraestructuraService infraestructuraService;

    public InfraestructuraController(InfraestructuraService infraestructuraService) {
        this.infraestructuraService = infraestructuraService;
    }

    @GetMapping("/sedes")
    public List<Sede> listarSedes() {
        return infraestructuraService.listarSedes();
    }

    @GetMapping("/sedes/{nombreSede}/edificaciones")
    public List<Edificacion> listarEdificacionesPorSede(@PathVariable String nombreSede) {
        return infraestructuraService.listarEdificacionesPorSede(nombreSede);
    }

    @GetMapping("/edificaciones/{nombreEdif}/espacios")
    public List<EspacioFisico> listarEspaciosPorEdificacion(
            @PathVariable String nombreEdif,
            @RequestParam String direccionInterna) {
        return infraestructuraService.listarEspaciosPorEdificacion(nombreEdif, direccionInterna);
    }

    @GetMapping("/sedes/{nombreSede}/espacios-disponibles")
    public List<EspacioFisico> listarEspaciosDisponiblesPorSede(@PathVariable String nombreSede) {
        return infraestructuraService.listarEspaciosDisponiblesPorSede(nombreSede);
    }
}