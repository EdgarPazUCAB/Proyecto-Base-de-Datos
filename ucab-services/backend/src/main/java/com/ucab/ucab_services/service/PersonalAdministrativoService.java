package com.ucab.ucab_services.service;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import java.util.List;
import java.util.Optional;

public interface PersonalAdministrativoService {
    List<PersonalAdministrativo> findAll();
    Optional<PersonalAdministrativo> findById(String id);
    PersonalAdministrativo save(PersonalAdministrativo personalAdministrativo);
    void deleteById(String id);
    boolean existsById(String id);
    long count();
}