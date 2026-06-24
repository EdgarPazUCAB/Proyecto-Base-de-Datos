package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.ClasificadoEn;
import com.ucab.ucab_services.entity.ClasificadoEnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificadoEnRepository extends JpaRepository<ClasificadoEn, ClasificadoEnId> {
}