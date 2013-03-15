package framework.core.service.exceptions;

import framework.core.constants.ServiceError;

public class AuthenticationException extends ServiceException {

    private static final long serialVersionUID = 7940333532126299159L;

    public AuthenticationException(ServiceError error) {
        super(error);
    }

}
