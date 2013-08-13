package framework.core.service.exceptions;

import framework.core.constants.ApplicationStatus;

public class AuthenticationException extends ServiceException {

    private static final long serialVersionUID = 7940333532126299159L;

    public AuthenticationException(ApplicationStatus error) {
        super(error);
    }

}
