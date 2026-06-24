package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, String> {

    List<Servicio> findByEstadoServicio(String estadoServicio);

    @Query("SELECT s FROM Servicio s WHERE s.entidadPrestadora.idEntidad = :idEntidad")
    List<Servicio> findByEntidadPrestadora(@Param("idEntidad") Integer idEntidad);
}