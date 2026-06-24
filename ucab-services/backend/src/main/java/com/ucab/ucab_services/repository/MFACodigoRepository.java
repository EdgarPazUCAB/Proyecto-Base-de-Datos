package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.MFACodigo;
import com.ucab.ucab_services.entity.MFACodigoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFACodigoRepository extends JpaRepository<MFACodigo, MFACodigoId> {
}