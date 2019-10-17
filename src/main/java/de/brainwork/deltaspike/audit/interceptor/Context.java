package de.brainwork.deltaspike.audit.interceptor;


import de.brainwork.deltaspike.entity.User;
import org.apache.deltaspike.data.api.audit.CurrentUser;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Context implements Serializable {

    private String message = "";

    @Inject
    @CurrentUser
    private User currentUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message != null) this.message = message;
    }

    public String getLoginName() {
        return currentUser.getLoginname();
    }
}
