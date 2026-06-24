package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.AuditoriaSesion;
import com.ucab.ucab_services.entity.AuditoriaSesionId;
import com.ucab.ucab_services.repository.AuditoriaSesionRepository;
import com.ucab.ucab_services.service.AuditoriaSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaSesionServiceImpl implements AuditoriaSesionService {

    @Autowired
    private AuditoriaSesionRepository auditoriaSesionRepository;

    @Override
    public List<AuditoriaSesion> findAll() {
        return auditoriaSesionRepository.findAll();
    }

    @Override
    public Optional<AuditoriaSesion> findById(AuditoriaSesionId id) {
        return auditoriaSesionRepository.findById(id);
    }

    @Override
    public AuditoriaSesion save(AuditoriaSesion auditoriaSesion) {
        return auditoriaSesionRepository.save(auditoriaSesion);
    }

    @Override
    public void deleteById(AuditoriaSesionId id) {
        auditoriaSesionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(AuditoriaSesionId id) {
        return auditoriaSesionRepository.existsById(id);
    }

    @Override
    public long count() {
        return auditoriaSesionRepository.count();
    }
}