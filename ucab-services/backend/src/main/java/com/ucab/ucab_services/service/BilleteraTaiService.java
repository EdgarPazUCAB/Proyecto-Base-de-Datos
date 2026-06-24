package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.BilleteraTai;

import java.util.List;
import java.util.UUID;

public interface BilleteraTaiService {
    
    BilleteraTai save(BilleteraTai billeteraTai);
    
    BilleteraTai findById(UUID id);
    
    // ✅ CAMBIO AQUÍ: Actualizado para coincidir con la implementación y el repositorio
    BilleteraTai findByMiembroCedulaMiembro(String cedulaMiembro);
    
    List<BilleteraTai> findAll();
    
    void deleteById(UUID id);
}