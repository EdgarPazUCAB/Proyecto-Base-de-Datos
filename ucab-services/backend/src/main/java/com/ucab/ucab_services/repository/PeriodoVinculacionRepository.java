package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PeriodoVinculacion;
import com.ucab.ucab_services.entity.PeriodoVinculacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoVinculacionRepository extends JpaRepository<PeriodoVinculacion, PeriodoVinculacionId> {
}