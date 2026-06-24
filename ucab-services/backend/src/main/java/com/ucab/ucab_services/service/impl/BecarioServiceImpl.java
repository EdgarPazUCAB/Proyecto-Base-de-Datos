package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Becario;
import com.ucab.ucab_services.repository.BecarioRepository;
import com.ucab.ucab_services.service.BecarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BecarioServiceImpl implements BecarioService {

    @Autowired
    private BecarioRepository becarioRepository;

    @Override
    public List<Becario> findAll() {
        return becarioRepository.findAll();
    }

    @Override
    public Optional<Becario> findById(String id) {
        return becarioRepository.findById(id);
    }

    @Override
    public Becario save(Becario becario) {
        return becarioRepository.save(becario);
    }

    @Override
    public void deleteById(String id) {
        becarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return becarioRepository.existsById(id);
    }

    @Override
    public long count() {
        return becarioRepository.count();
    }
}