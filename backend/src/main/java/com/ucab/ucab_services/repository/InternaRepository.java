package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Interna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternaRepository extends JpaRepository<Interna, Integer> {
}