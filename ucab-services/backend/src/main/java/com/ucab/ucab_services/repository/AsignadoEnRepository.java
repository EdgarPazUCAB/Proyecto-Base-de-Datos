package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.AsignadoEn;
import com.ucab.ucab_services.entity.AsignadoEnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignadoEnRepository extends JpaRepository<AsignadoEn, AsignadoEnId> {
}