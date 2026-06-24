package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.ItemConsumo;
import com.ucab.ucab_services.entity.ItemConsumoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemConsumoRepository extends JpaRepository<ItemConsumo, ItemConsumoId> {
}