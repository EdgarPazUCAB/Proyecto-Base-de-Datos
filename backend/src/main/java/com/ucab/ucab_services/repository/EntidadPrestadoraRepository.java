package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.EntidadPrestadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadPrestadoraRepository extends JpaRepository<EntidadPrestadora, Integer> {
}