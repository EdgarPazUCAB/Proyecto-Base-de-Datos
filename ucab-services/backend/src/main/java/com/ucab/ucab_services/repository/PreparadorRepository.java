package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Preparador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreparadorRepository extends JpaRepository<Preparador, String> {
}