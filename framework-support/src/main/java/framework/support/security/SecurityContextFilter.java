package framework.support.security;

import javax.inject.Inject;
import javax.inject.Named;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import framework.api.security.SecurityContext;
import framework.core.entity.Session;
import framework.core.entity.User;
import framework.core.service.SessionService;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    private SessionService sessionService;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        final String sessionId = request.getHeaderValue("session-id");
        User user = null;
        Session session = null;
        if ((sessionId != null) && (sessionId.length() > 0)) {
            session = this.sessionService.findById(sessionId);
            if (session != null) {
                user = session.getUser();
            }
        }
        request.setSecurityContext(new SecurityContext(user, session));
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        return response;
    }

    @Override
    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    @Override
    public ContainerResponseFilter getResponseFilter() {
        return this;
    }

}
