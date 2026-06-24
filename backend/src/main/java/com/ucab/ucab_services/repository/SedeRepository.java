package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, String> {
}