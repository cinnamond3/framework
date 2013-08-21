package framework.support.security;

import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import framework.api.providers.SecurityContext;
import framework.api.request.ServiceRequest;
import framework.core.entity.Session;
import framework.core.entity.User;
import framework.core.service.SessionService;

@Named
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    private SessionService sessionService;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        User user = null;
        Session session = null;
        InputStream inputStream = request.getEntityInputStream();
        ServiceRequest serviceRequest = request.getEntity(ServiceRequest.class);
        if (serviceRequest.getRequestHeader() != null) {
            session = this.sessionService.findSession(
                    serviceRequest.getRequestHeader().getUserid(),
                    serviceRequest.getRequestHeader().getSessionid());
            if (session != null) {
                user = session.getUser();
            }
        }
        request.setEntityInputStream(inputStream);
        request.setEntity(
                ServiceRequest.class, 
                null, 
                null, 
                MediaType.APPLICATION_JSON_TYPE, 
                null, 
                serviceRequest);
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
