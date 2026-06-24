package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, String> {
}