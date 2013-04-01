package framework.ui.controllers;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import framework.core.entity.Session;
import framework.core.service.UserService;
import framework.ui.request.LoginRequest;
import framework.ui.request.RequestHeader;
import framework.ui.response.SessionResponse;

@Named
@Path("/login")
public class LoginController extends AbstractController<LoginRequest, SessionResponse> {

    /**
     * 
     */
    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @Override
    public List<SessionResponse> processRequest(LoginRequest loginRequest) {
        final Session session = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        final SessionResponse sessionDTO = new SessionResponse();
        sessionDTO.setSessionId(String.valueOf(session.getId()));
        sessionDTO.setUserId(String.valueOf(session.getUser().getId()));
        sessionDTO.setUsergroupId(String.valueOf(session.getUser().getUsergroup().getId()));
        return Arrays.asList(sessionDTO);
    }

    @Override
    protected boolean isAccessible(RequestHeader requestHeader) {
        return true;
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}
