package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.CategoriaFidelidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaFidelidadRepository extends JpaRepository<CategoriaFidelidad, String> {
}