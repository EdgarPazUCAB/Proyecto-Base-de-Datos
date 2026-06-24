package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.OfertaLaboral;
import com.ucab.ucab_services.entity.OfertaLaboralId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaLaboralRepository extends JpaRepository<OfertaLaboral, OfertaLaboralId> {
}