package de.brainwork.deltaspike.resource;


import de.brainwork.deltaspike.entity.User;
import de.brainwork.deltaspike.repository.PersonRepository;
import de.brainwork.deltaspike.service.PersonService;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.api.message.Message;
import org.apache.deltaspike.core.api.message.MessageContext;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.StringWriter;
import java.util.List;

/**
 * Beispiel um ConfigResolver zu testen...
 */
@Path("person")
public class PersonResource {

    // wird über ein Profile gesteuert
    @Inject
    ProjectStage projectStage;

    @Inject
    PersonRepository repository;

    @Inject
    EntityManager em;


    // steht in /resources/META-INF/apache-deltaspike.properties
    @Inject
    @ConfigProperty(name = "test")
    private String test;

    // steht NICHT in /resources/META-INF/apache-deltaspike.properties
    // automatische Typ-Umwandlung
    @Inject
    @ConfigProperty(name = "inttest", defaultValue = "42")
    private int inttest;


    @Inject
    private MessageContext messageContext;


    @Inject
    private PersonService personService;


    @GET
    public String getPerson() {
        StringWriter writer = new StringWriter();

        writer.append(getMessage().argument("ProjectStage").argument(projectStage.toString()).toString());
        writer.append(getMessage().argument("@Inject Projektabhängiges Property: test").argument(test).toString());
        writer.append(getMessage().argument("ConfigResolver.getPropertyValue(): test").argument(ConfigResolver.getPropertyValue("test")).toString());
        writer.append(getMessage().argument("ConfigResolver.getProjectStageAwarePropertyValue(): test").argument(ConfigResolver.getProjectStageAwarePropertyValue("test")).toString());


        User user = personService.findUserByNachname("Bauer");
//        System.out.println("<--------------- user: " + user);


        List<User> users = personService.getUsers();

//        System.out.println("<--------------- users: " + users);

//        users.get(0).setNachname("Maier");

        return writer.toString();
    }

    private Message getMessage() {
        return this.messageContext.messageSource("org.apache.deltaspike.message.Messages").message().template("{debug}");

    }
}