package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.BilleteraTai;
import java.util.List;

public interface BilleteraTaiService {
    BilleteraTai save(BilleteraTai billeteraTai);
    BilleteraTai findById(java.util.UUID id);
    BilleteraTai findByCedulaMiembro(String cedulaMiembro);
    java.util.List<BilleteraTai> findAll();
    void deleteById(java.util.UUID id);
}