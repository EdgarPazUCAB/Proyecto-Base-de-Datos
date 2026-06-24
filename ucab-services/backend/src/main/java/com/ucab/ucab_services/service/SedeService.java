package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.Sede;
import java.util.List;

public interface SedeService {
    Sede save(Sede sede);
    Sede findById(String id);
    List<Sede> findAll();
    void deleteById(String id);
}