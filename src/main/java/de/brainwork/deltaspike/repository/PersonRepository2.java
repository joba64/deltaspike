package de.brainwork.deltaspike.repository;

import de.brainwork.deltaspike.entity.User;
import org.apache.deltaspike.data.api.EntityGraphType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

@Named
public class PersonRepository2 {

    @Inject
    EntityManager em;


    public User getUser() {
        EntityGraph<?> graph = em.getEntityGraph(User.FETCHGROUP_DEFAULT);

        return em.createQuery("select u from User u", User.class)
                .setHint(EntityGraphType.FETCH.getHintName(), graph)
                .setMaxResults(1)
                .getSingleResult();
    }
}
