package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.SolicitudServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudServicioRepository extends JpaRepository<SolicitudServicio, String> {
}