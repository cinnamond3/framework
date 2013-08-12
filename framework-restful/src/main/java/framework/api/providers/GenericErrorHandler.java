package framework.api.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import framework.api.response.ServiceResponseBuilder;
import framework.core.service.exceptions.ServiceException;

@Provider
public class GenericErrorHandler implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
        return Response
                .status(Status.BAD_REQUEST)
                .entity(ServiceResponseBuilder.getInstance().statusCode(exception.getCode()).statusMessage(exception.getMessage())
                        .build()).type(MediaType.APPLICATION_JSON).build();
    }

}
