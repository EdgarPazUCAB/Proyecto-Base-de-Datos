package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiembroRepository extends JpaRepository<Miembro, String> {
    // Spring Data JPA deduce la consulta por el nombre del atributo exacto
    Optional<Miembro> findByCorreoInstitucional(String correoInstitucional);
}