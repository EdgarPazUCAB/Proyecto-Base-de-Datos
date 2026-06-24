package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PagoMovil;
import com.ucab.ucab_services.entity.PagoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoMovilRepository extends JpaRepository<PagoMovil, PagoId> {
}