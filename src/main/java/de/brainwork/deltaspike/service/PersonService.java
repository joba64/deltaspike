package de.brainwork.deltaspike.service;


import de.brainwork.deltaspike.entity.User;
import de.brainwork.deltaspike.repository.PersonRepository;
import de.brainwork.deltaspike.repository.PersonRepository2;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Transactional
@ApplicationScoped
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    @Inject
    private PersonRepository2 personRepository2;

    public List<User> getUsers() {
        return personRepository.getUsers();
    }

    public User getUser() {
        return personRepository2.getUser();
    }

    public User findUserByNachname(String nachname) {
        return personRepository.findByNachname(nachname);
    }


}
