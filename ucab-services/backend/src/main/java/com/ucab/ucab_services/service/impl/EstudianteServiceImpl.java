package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Estudiante;
import com.ucab.ucab_services.repository.EstudianteRepository;
import com.ucab.ucab_services.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> findById(String id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteById(String id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return estudianteRepository.existsById(id);
    }

    @Override
    public long count() {
        return estudianteRepository.count();
    }
}