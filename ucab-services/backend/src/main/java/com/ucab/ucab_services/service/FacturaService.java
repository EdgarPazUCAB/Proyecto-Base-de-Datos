package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Factura;
import java.util.List;

public interface FacturaService {
    Factura save(Factura factura);
    Factura findById(String id);
    List<Factura> findAll();
    void deleteById(String id);
}