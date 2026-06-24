package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.Sede;
import com.ucab.ucab_services.repository.EdificacionRepository;
import com.ucab.ucab_services.repository.EspacioFisicoRepository;
import com.ucab.ucab_services.repository.SedeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Consulta de infraestructura física: Sede, Edificacion, EspacioFisico.
 *
 * SOLO LECTURA por decisión de negocio: estas entidades representan
 * estructuras físicas reales (sedes, edificios, espacios) que no se
 * crean, editan ni eliminan desde la aplicación web. Cambios aquí
 * solo ocurren por eventos físicos reales (construcción, demolición,
 * remodelación) y se gestionan directamente en la base de datos por
 * el equipo de infraestructura/DBA, no por usuarios del sistema.
 *
 * Por eso este servicio NO expone crear/editar/eliminar — únicamente
 * operaciones de consulta, usadas por ejemplo para mostrar opciones
 * de sede/edificio/espacio al reservar un servicio.
 */
@Service
public class InfraestructuraService {

    private final SedeRepository sedeRepository;
    private final EdificacionRepository edificacionRepository;
    private final EspacioFisicoRepository espacioFisicoRepository;

    public InfraestructuraService(SedeRepository sedeRepository,
                                   EdificacionRepository edificacionRepository,
                                   EspacioFisicoRepository espacioFisicoRepository) {
        this.sedeRepository = sedeRepository;
        this.edificacionRepository = edificacionRepository;
        this.espacioFisicoRepository = espacioFisicoRepository;
    }

    @Transactional(readOnly = true)
    public List<Sede> listarSedes() {
        return sedeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Edificacion> listarEdificacionesPorSede(String nombreSede) {
        return edificacionRepository.findBySedeNombre(nombreSede);
    }

    @Transactional(readOnly = true)
    public List<EspacioFisico> listarEspaciosPorEdificacion(String nombreEdif, String direccionInterna) {
        return espacioFisicoRepository.findByEdificacion(nombreEdif, direccionInterna);
    }

    @Transactional(readOnly = true)
    public List<EspacioFisico> listarEspaciosDisponiblesPorSede(String nombreSede) {
        return espacioFisicoRepository.findDisponiblesPorSede(nombreSede);
    }
}