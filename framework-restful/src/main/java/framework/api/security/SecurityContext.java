package framework.api.security;

import java.security.Principal;

import framework.api.controllers.ForbiddenRequestException;
import framework.core.entity.Role;
import framework.core.entity.Session;
import framework.core.entity.User;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {

    private final User user;
    private Session session;
 
    public SecurityContext(User user, Session session) {
        this.user = user;
        this.session = session;
    }
    
    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String name) {
        if (null == session) {
            throw new ForbiddenRequestException();
        }
        for (Role role : user.getUsergroup().getRoles()) {
            if (role.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return javax.ws.rs.core.SecurityContext.BASIC_AUTH;
    }

}
