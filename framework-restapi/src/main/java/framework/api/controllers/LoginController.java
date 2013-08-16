package framework.api.controllers;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import framework.api.request.LoginRequest;
import framework.api.response.ServiceResponse;
import framework.api.response.SessionResponse;
import framework.core.constants.ApplicationStatus;
import framework.core.entity.Session;
import framework.core.service.UserService;

@Named
@Path("/login")
public class LoginController extends AbstractController<LoginRequest, SessionResponse> {

    /**
     * 
     */
    private static final long serialVersionUID = -6402313528023081815L;
    private UserService userService;

    @POST
    @Consumes(value = { MediaType.APPLICATION_JSON })
    public ServiceResponse<SessionResponse> processRequest(LoginRequest loginRequest) {
        final Session session = this.userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        final SessionResponse sessionDTO = new SessionResponse();
        sessionDTO.setSessionId(String.valueOf(session.getId()));
        sessionDTO.setUserId(String.valueOf(session.getUser().getId()));
        sessionDTO.setUsergroupId(String.valueOf(session.getUser().getUsergroup().getId()));
        return ServiceResponse.results(Arrays.asList(sessionDTO)).status(ApplicationStatus.SUCCESS).build();
    }

    @Inject
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

}