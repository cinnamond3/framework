package framework.api.controllers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import framework.api.response.ServiceResponseBuilder;

public class ForbiddenRequestException extends WebApplicationException {

    private static final long serialVersionUID = 3603877448341210599L;

    public ForbiddenRequestException() {
        super(Response
                .status(Status.FORBIDDEN)
                .type(MediaType.APPLICATION_JSON)
                .entity(ServiceResponseBuilder.getInstance().statusCode(403)
                        .statusMessage("User is not allowed to access this functionality").build()).build());
    }

}
