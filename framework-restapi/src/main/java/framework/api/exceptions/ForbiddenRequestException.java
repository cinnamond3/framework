package framework.api.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import framework.api.response.ServiceResponse;
import framework.core.constants.ApplicationStatus;

public class ForbiddenRequestException extends WebApplicationException {

    private static final long serialVersionUID = 3603877448341210599L;

    public ForbiddenRequestException() {
        super(Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_JSON)
                .entity(ServiceResponse.results().status(ApplicationStatus.FORBIDDEN).build())
                .build());
    }

}
