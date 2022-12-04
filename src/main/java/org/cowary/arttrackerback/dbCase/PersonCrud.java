package org.cowary.arttrackerback.dbCase;

import org.cowary.arttrackerback.entity.Person;
import org.cowary.arttrackerback.integration.model.shiki.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.PersonRepo;

@Component
public class PersonCrud {

    @Autowired
    PersonRepo personRepo;

    public Person createOrGetByName(PersonModel personModel) {
        return personRepo.findByNameEn(personModel.getName()).orElseGet(() -> {
            Person person = new Person(personModel.getName(), personModel.getRussian(), personModel.getId());
            personRepo.save(person);
            return person;
        });
    }
}
