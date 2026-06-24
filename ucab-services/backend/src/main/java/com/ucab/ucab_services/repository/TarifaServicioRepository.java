package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.TarifaServicio;
import com.ucab.ucab_services.entity.TarifaServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaServicioRepository extends JpaRepository<TarifaServicio, TarifaServicioId> {
}