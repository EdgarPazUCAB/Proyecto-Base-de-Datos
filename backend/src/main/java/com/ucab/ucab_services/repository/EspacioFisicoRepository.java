package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository<EspacioFisico, EspacioFisicoId>: PK compuesta de
 * 3 columnas (ver EspacioFisicoId).
 */
@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisico, EspacioFisicoId> {

    /**
     * Espacios de un edificio específico (nombre + dirección, ya que
     * juntos identifican de forma única al edificio).
     */
    @Query("""
            SELECT ef FROM EspacioFisico ef
            WHERE ef.edificacion.id.nombreEdif = :nombreEdif
              AND ef.edificacion.id.direccionInterna = :direccionInterna
            """)
    List<EspacioFisico> findByEdificacion(
            @Param("nombreEdif") String nombreEdif,
            @Param("direccionInterna") String direccionInterna);

    /**
     * Espacios disponibles en toda una sede (útil para reservaciones).
     * Atraviesa EspacioFisico -> Edificacion -> Sede.
     */
    @Query("""
            SELECT ef FROM EspacioFisico ef
            WHERE ef.edificacion.sede.nombreSede = :nombreSede
              AND ef.estatus = 'Disponible'
            """)
    List<EspacioFisico> findDisponiblesPorSede(@Param("nombreSede") String nombreSede);
}