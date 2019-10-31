package de.brainwork.deltaspike.repository;

import de.brainwork.deltaspike.audit.interceptor.AuditContext;
import de.brainwork.deltaspike.entity.User;
import org.apache.deltaspike.data.api.EntityGraph;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Repository
@AuditContext(message = "Intercept from PersonRepository")
public abstract class PersonRepository implements EntityRepository<User, String>, CriteriaSupport<User> {


    public abstract List<User> findByVorname(String vorname);

    public abstract User findByNachname(String nachname);

    @EntityGraph(paths = {"mitarbeiter"})
    @Query(named = User.QUERY_USER_FINDALL)
    public abstract List<User> getUsers();

}