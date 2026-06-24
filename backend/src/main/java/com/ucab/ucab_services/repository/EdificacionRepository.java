package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JpaRepository<Edificacion, EdificacionId>: el segundo parámetro es
 * el tipo de la PK compuesta (la clase @Embeddable), no String.
 */
@Repository
public interface EdificacionRepository extends JpaRepository<Edificacion, EdificacionId> {

    @Query("SELECT e FROM Edificacion e WHERE e.sede.nombreSede = :nombreSede")
    List<Edificacion> findBySedeNombre(@Param("nombreSede") String nombreSede);
}