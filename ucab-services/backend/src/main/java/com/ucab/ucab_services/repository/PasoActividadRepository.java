package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PasoActividad;
import com.ucab.ucab_services.entity.PasoActividadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoActividadRepository extends JpaRepository<PasoActividad, PasoActividadId> {
}