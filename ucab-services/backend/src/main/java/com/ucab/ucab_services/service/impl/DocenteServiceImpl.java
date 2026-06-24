package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Docente;
import com.ucab.ucab_services.repository.DocenteRepository;
import com.ucab.ucab_services.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> findById(String id) {
        return docenteRepository.findById(id);
    }

    @Override
    public Docente save(Docente docente) {
        return docenteRepository.save(docente);
    }

    @Override
    public void deleteById(String id) {
        docenteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return docenteRepository.existsById(id);
    }

    @Override
    public long count() {
        return docenteRepository.count();
    }
}