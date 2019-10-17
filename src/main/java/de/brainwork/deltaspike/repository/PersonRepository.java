package de.brainwork.deltaspike.repository;

import de.brainwork.deltaspike.audit.interceptor.AuditContext;
import de.brainwork.deltaspike.entity.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Repository
@AuditContext(message = "Intercept from PersonRepository")
public abstract class PersonRepository implements EntityRepository<User, String> {
    public abstract List<User> findByVorname(String vorname);

    public abstract User findByNachname(String nachname);

}