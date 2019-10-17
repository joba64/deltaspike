package de.brainwork.deltaspike.audit.interceptor;

import de.brainwork.deltaspike.entity.User;
import org.apache.deltaspike.data.api.audit.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import java.io.Serializable;

@Singleton
public class Producer implements Serializable {

    @Produces
    @CurrentUser
    public User getCurrentUser() {
        User user = new User();
        user.setNachname("admin");
        user.setVorname("admin");
        user.setLoginname("DUMMY");
        return user;
    }

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}

