package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisico, EspacioFisicoId> {
}