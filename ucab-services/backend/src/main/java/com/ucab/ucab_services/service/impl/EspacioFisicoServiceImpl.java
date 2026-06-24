package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.EspacioFisico;
import com.ucab.ucab_services.entity.EspacioFisicoId;
import com.ucab.ucab_services.repository.EspacioFisicoRepository;
import com.ucab.ucab_services.service.EspacioFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacioFisicoServiceImpl implements EspacioFisicoService {

    @Autowired
    private EspacioFisicoRepository espacioFisicoRepository;

    @Override
    public List<EspacioFisico> findAll() {
        return espacioFisicoRepository.findAll();
    }

    @Override
    public Optional<EspacioFisico> findById(EspacioFisicoId id) {
        return espacioFisicoRepository.findById(id);
    }

    @Override
    public EspacioFisico save(EspacioFisico espacioFisico) {
        return espacioFisicoRepository.save(espacioFisico);
    }

    @Override
    public void deleteById(EspacioFisicoId id) {
        espacioFisicoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(EspacioFisicoId id) {
        return espacioFisicoRepository.existsById(id);
    }

    @Override
    public long count() {
        return espacioFisicoRepository.count();
    }
}