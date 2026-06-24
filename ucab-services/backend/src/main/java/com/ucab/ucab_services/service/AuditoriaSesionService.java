package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import java.util.List;
import java.util.Optional;

public interface AuditoriaSesionService {
    List<AuditoriaSesion> findAll();
    Optional<AuditoriaSesion> findById(AuditoriaSesionId id);
    AuditoriaSesion save(AuditoriaSesion auditoriaSesion);
    void deleteById(AuditoriaSesionId id);
    boolean existsById(AuditoriaSesionId id);
    long count();
}