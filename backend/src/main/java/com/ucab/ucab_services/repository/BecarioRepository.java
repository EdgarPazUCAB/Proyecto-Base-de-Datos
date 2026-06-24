package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Becario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BecarioRepository extends JpaRepository<Becario, String> {
}