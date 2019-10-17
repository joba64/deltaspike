package de.brainwork.deltaspike.audit;

import de.brainwork.deltaspike.audit.interceptor.AuditContext;
import de.brainwork.deltaspike.audit.interceptor.Context;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

@Named
public class AuditHandler {

    @Inject
    private Logger logger;

    @Inject
    private Context context;

    public void onAuditEvent(@Observes AuditEvent event) {

        var ctx = event.getInvocationContext();
        var auditContext = getAuditContext(ctx);

        if (!auditContext.message().isEmpty()) {
            context.setMessage(auditContext.message());
        }

        if (!context.getMessage().isEmpty()) {
            logger.info("im Interceptor als : " + context.getLoginName());
            System.out.println("im Interceptor als : " + context.getLoginName());
            System.out.println("    Message  : " + context.getMessage());
            System.out.println("    Method   : " + ctx.getMethod());

            var objects = Arrays.asList(ctx.getParameters());
            objects.stream()
                    .map(x -> "    Parameter: " + x.toString())
                    .forEach(System.out::println);

//            objects.forEach((x) -> System.out.println("    Parameter: " + x.toString()));
        }
    }

    private AuditContext getAuditContext(InvocationContext context) {
        var auditContext = context.getMethod().getAnnotation(AuditContext.class);
        if (auditContext != null) return auditContext;
        return context.getTarget().getClass().getAnnotation(AuditContext.class);
    }


}
