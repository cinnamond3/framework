package framework.ui.controllers;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import framework.core.constants.ServiceError;
import framework.core.service.SessionService;
import framework.core.service.exceptions.ServiceException;
import framework.ui.request.RequestHeader;
import framework.ui.request.ServiceRequest;
import framework.ui.response.ResponseHeader;
import framework.ui.response.ServiceResponse;

@Named
public abstract class BaseController<T extends Serializable, I extends Serializable> implements Serializable {

    private static final long serialVersionUID = -1508227485108273495L;
    private SessionService sessionService;

    @POST
    @Consumes(value={MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public final ServiceResponse<I> processRequest(ServiceRequest<T> serviceRequest) {
        final ServiceResponse<I> serviceResponse = new ServiceResponse<I>();
        final ResponseHeader header = new ResponseHeader();
        //if (this.isAccessible(serviceRequest.getRequestHeader())) {
            try {
                final List<I> t = this.processResult(serviceRequest.getRequest());
                header.setStatusCode(0);
                header.setStatusMessage("Successful.");
                serviceResponse.setResults(t);
            } catch (final ServiceException e) {
                header.setStatusCode(e.getCode());
                header.setStatusMessage(e.getMessage());
            }
            serviceResponse.setResponseHeader(header);
        //} else {
         //   header.setStatusCode(ServiceError.ACCESS_DENIED.getCode());
        //    header.setStatusMessage(ServiceError.ACCESS_DENIED.getMessage());
        //}
        return serviceResponse;
    }

    public abstract List<I> processResult(T t);

    protected boolean isAccessible(RequestHeader requestHeader) {
        if (requestHeader!=null) {
            return this.sessionService.isValidSession(requestHeader.getUserid(), requestHeader.getSessionid());
        }
        return false;
    }

    @Inject
    protected void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

}
