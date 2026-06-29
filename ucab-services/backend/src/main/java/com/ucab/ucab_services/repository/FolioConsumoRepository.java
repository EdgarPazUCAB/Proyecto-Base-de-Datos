package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.FolioConsumo;
import com.ucab.ucab_services.entity.FolioConsumoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FolioConsumoRepository extends JpaRepository<FolioConsumo, FolioConsumoId> {
    List<FolioConsumo> findByIdentificador(String identificador);
}