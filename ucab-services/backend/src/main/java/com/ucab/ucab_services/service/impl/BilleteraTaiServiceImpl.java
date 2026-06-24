package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.BilleteraTai;
import com.ucab.ucab_services.repository.BilleteraTaiRepository;
import com.ucab.ucab_services.service.BilleteraTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BilleteraTaiServiceImpl implements BilleteraTaiService {

    @Autowired
    private BilleteraTaiRepository billeteraTaiRepository;

    @Override
    public BilleteraTai save(BilleteraTai billeteraTai) {
        return billeteraTaiRepository.save(billeteraTai);
    }

    @Override
    public BilleteraTai findById(UUID id) {
        return billeteraTaiRepository.findById(id).orElse(null);
    }

    @Override
    public BilleteraTai findByCedulaMiembro(String cedulaMiembro) {
        return billeteraTaiRepository.findByCedulaMiembro(cedulaMiembro);
    }

    @Override
    public java.util.List<BilleteraTai> findAll() {
        return billeteraTaiRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        billeteraTaiRepository.deleteById(id);
    }
}