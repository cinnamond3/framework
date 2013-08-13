package framework.core.service.exceptions;

import framework.core.constants.ApplicationStatus;

public abstract class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 6245180922857816922L;
    private ApplicationStatus status;
    
    public ServiceException(ApplicationStatus error, Throwable cause) {
        super(error.getMessage(), cause);
        this.status = error;
    }
    
    public ServiceException(ApplicationStatus error) {
        super(error.getMessage());
        this.status = error;
    }

    public Integer getCode() {
        return this.status.getCode();
    }

    public String getMessage() {
        return this.status.getMessage();
    }
    
    public ApplicationStatus getStatus() {
        return this.status;
    }
}
