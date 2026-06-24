package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Beneficiario;
import com.ucab.ucab_services.repository.BeneficiarioRepository;
import com.ucab.ucab_services.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Override
    public Beneficiario save(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    @Override
    public Optional<Beneficiario> findById(String id) {
        return beneficiarioRepository.findById(id);
    }

    @Override
    public java.util.List<Beneficiario> findAll() {
        return beneficiarioRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        beneficiarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return beneficiarioRepository.existsById(id);
    }

    @Override
    public long count() {
        return beneficiarioRepository.count();
    }
}