package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Edificacion;
import com.ucab.ucab_services.entity.EdificacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificacionRepository extends JpaRepository<Edificacion, EdificacionId> {
}