package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.SugeridaA;
import com.ucab.ucab_services.entity.SugeridaAId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugeridaARepository extends JpaRepository<SugeridaA, SugeridaAId> {
}