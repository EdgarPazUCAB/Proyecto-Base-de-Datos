package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import com.ucab.ucab_services.repository.MFACodigoRepository;
import com.ucab.ucab_services.service.MFACodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MFACodigoServiceImpl implements MFACodigoService {

    @Autowired
    private MFACodigoRepository mfaCodigoRepository;

    @Override
    public List<MFACodigo> findAll() {
        return mfaCodigoRepository.findAll();
    }

    @Override
    public Optional<MFACodigo> findById(MFACodigoId id) {
        return mfaCodigoRepository.findById(id);
    }

    @Override
    public MFACodigo save(MFACodigo mfaCodigo) {
        return mfaCodigoRepository.save(mfaCodigo);
    }

    @Override
    public void deleteById(MFACodigoId id) {
        mfaCodigoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(MFACodigoId id) {
        return mfaCodigoRepository.existsById(id);
    }

    @Override
    public long count() {
        return mfaCodigoRepository.count();
    }
}