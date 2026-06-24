package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Egresado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresadoRepository extends JpaRepository<Egresado, String> {
}