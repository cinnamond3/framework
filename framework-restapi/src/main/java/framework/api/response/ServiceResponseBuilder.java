package framework.api.response;

import java.util.List;

import framework.core.enums.ApplicationStatus;

public class ServiceResponseBuilder<T> {

    private List<T> results;

    private Integer statusCode;
    private String statusMessage;

    ServiceResponseBuilder() {
        
    }
    
    public ServiceResponse<T> build() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatusCode(this.statusCode);
        responseHeader.setStatusMessage(this.statusMessage);
        return new ServiceResponse<T>(responseHeader, this.results);
    }

    /**
     * @param results
     *            the results to set
     */
    public ServiceResponseBuilder<T> results(List<T> results) {
        this.results = results;
        return this;
    }

    /**
     * @param results
     *            the results to set
     */
    public ServiceResponseBuilder<T> status(ApplicationStatus status) {
        this.statusMessage = status.getMessage();
        this.statusCode = status.getCode();
        return this;
    }

    /**
     * @param results
     *            the results to set
     */
    public ServiceResponseBuilder<T> statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * @param results
     *            the results to set
     */
    public ServiceResponseBuilder<T> statusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }
}
