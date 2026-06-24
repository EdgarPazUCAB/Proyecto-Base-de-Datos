package com.ucab.ucab_services.service.impl;

import com.ucab.ucab_services.entity.PersonalAdministrativo;
import com.ucab.ucab_services.repository.PersonalAdministrativoRepository;
import com.ucab.ucab_services.service.PersonalAdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAdministrativoServiceImpl implements PersonalAdministrativoService {

    @Autowired
    private PersonalAdministrativoRepository personalAdministrativoRepository;

    @Override
    public List<PersonalAdministrativo> findAll() {
        return personalAdministrativoRepository.findAll();
    }

    @Override
    public Optional<PersonalAdministrativo> findById(String id) {
        return personalAdministrativoRepository.findById(id);
    }

    @Override
    public PersonalAdministrativo save(PersonalAdministrativo personalAdministrativo) {
        return personalAdministrativoRepository.save(personalAdministrativo);
    }

    @Override
    public void deleteById(String id) {
        personalAdministrativoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return personalAdministrativoRepository.existsById(id);
}

    @Override
    public long count() {
        return personalAdministrativoRepository.count();
    }
}