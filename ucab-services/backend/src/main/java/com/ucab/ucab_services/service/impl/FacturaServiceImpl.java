package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.Factura;
import com.ucab.ucab_services.repository.FacturaRepository;
import com.ucab.ucab_services.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura findById(String id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public java.util.List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        facturaRepository.deleteById(id);
    }
}