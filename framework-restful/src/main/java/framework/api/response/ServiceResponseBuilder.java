package framework.api.response;

import java.util.List;

public class ServiceResponseBuilder<T> {

    public static ServiceResponseBuilder getInstance() {
        return new ServiceResponseBuilder();
    }

    private List<T> results;
    private Integer statusCode;

    private String statusMessage;

    public ServiceResponse<T> build() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatusCode(this.statusCode);
        responseHeader.setStatusMessage(this.statusMessage);

        final ServiceResponse<T> serviceResponse = new ServiceResponse<T>();
        serviceResponse.setResults(this.results);
        serviceResponse.setResponseHeader(responseHeader);

        return serviceResponse;
    }

    public ServiceResponseBuilder<T> results(List<T> results) {
        this.results = results;
        return this;
    }

    public ServiceResponseBuilder<T> statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ServiceResponseBuilder<T> statusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }
}