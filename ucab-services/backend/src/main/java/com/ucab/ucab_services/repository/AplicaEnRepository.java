package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AplicaEn;
import com.ucab.ucab_services.entity.AplicaEnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AplicaEnRepository extends JpaRepository<AplicaEn, AplicaEnId> {
}