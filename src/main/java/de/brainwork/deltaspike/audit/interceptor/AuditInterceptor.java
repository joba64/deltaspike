package de.brainwork.deltaspike.audit.interceptor;


import de.brainwork.deltaspike.audit.AuditEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Interceptor
@AuditContextAnnotated
public class AuditInterceptor implements Serializable {

    private static final List<String> ignore = List.of(
            "Object.toString()",
            "Object.hashCode()");

    @Inject
    private Event<AuditEvent> eventService;

    Logger logger = Logger.getLogger(AuditInterceptor.class.getName());


    @AroundInvoke
    public Object interceptOrder(InvocationContext ctx) throws Exception {
        var skip = ignore.stream().anyMatch(x -> ctx.getMethod().toString().endsWith(x));

        if (!skip) {
            eventService.fire(new AuditEvent(ctx));
        }
        return ctx.proceed();
    }

}
