package framework.core.service.exceptions;

import framework.core.constants.ServiceError;

public abstract class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 6245180922857816922L;
    private ServiceError error;
    
    public ServiceException(ServiceError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
    }
    
    public ServiceException(ServiceError error) {
        super(error.getMessage());
        this.error = error;
    }

    public Integer getCode() {
        return this.error.getCode();
    }

    public String getMessage() {
        return this.error.getMessage();
    }
}
