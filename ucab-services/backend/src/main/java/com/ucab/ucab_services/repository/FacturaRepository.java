package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {
}