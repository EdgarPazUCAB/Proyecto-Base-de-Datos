package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.BilleteraTai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilleteraTaiRepository extends JpaRepository<BilleteraTai, java.util.UUID> {
    BilleteraTai findByCedulaMiembro(String cedulaMiembro);
}