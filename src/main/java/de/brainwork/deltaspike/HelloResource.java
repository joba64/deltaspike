package de.brainwork.deltaspike;


import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.api.message.Message;
import org.apache.deltaspike.core.api.message.MessageContext;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.StringWriter;

/**
 * Beispiel um ConfigResolver zu testen...
 */
@Path("hello")
public class HelloResource {

    // wird über ein Profile gesteuert
    @Inject
    ProjectStage projectStage;

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


    @GET
    public String sayHello() {
        StringWriter writer = new StringWriter();

        writer.append(getMessage().argument("ProjectStage").argument(projectStage.toString()).toString());
        writer.append(getMessage().argument("@Inject Projektabhängiges Property: test").argument(test).toString());
        writer.append(getMessage().argument("ConfigResolver.getPropertyValue(): test").argument(ConfigResolver.getPropertyValue("test")).toString());
        writer.append(getMessage().argument("ConfigResolver.getProjectStageAwarePropertyValue(): test").argument(ConfigResolver.getProjectStageAwarePropertyValue("test")).toString());

        return writer.toString();
    }

    private Message getMessage() {
        return this.messageContext.messageSource("org.apache.deltaspike.message.Messages").message().template("{debug}");

    }
}