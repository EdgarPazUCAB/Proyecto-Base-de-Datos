package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import java.util.List;
import java.util.Optional;

public interface MFACodigoService {
    List<MFACodigo> findAll();
    Optional<MFACodigo> findById(MFACodigoId id);
    MFACodigo save(MFACodigo mfaCodigo);
    void deleteById(MFACodigoId id);
    boolean existsById(MFACodigoId id);
    long count();
}