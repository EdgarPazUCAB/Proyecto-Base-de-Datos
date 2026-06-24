package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.Externa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternaRepository extends JpaRepository<Externa, Integer> {
}