package de.brainwork.deltaspike.audit;

import javax.interceptor.InvocationContext;

public class AuditEvent {

    private InvocationContext invocationContext;

    public AuditEvent(InvocationContext ctx) {
        this.setInvocationContext(ctx);
    }

    public InvocationContext getInvocationContext() {
        return invocationContext;
    }

    public void setInvocationContext(InvocationContext invocationContext) {
        this.invocationContext = invocationContext;
    }
}
