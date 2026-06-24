package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AcompananteTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcompananteTemporalRepository extends JpaRepository<AcompananteTemporal, String> {
}