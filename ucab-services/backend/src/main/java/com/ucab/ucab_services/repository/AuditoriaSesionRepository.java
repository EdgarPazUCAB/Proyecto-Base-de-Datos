package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaSesionRepository extends JpaRepository<AuditoriaSesion, AuditoriaSesionId> {
}