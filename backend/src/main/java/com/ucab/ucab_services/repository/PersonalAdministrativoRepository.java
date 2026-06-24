package com.ucab.ucab_services.repository;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalAdministrativoRepository extends JpaRepository<PersonalAdministrativo, String> {
}