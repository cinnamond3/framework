package framework.core.constants;

public enum ServiceError {

    SYSTEM_EXCEPTION(1, "Unhandled exception."), 
    EXPIRED_CREDENTIALS(21, "Password has already expired."), 
    EXPIRED_PROFILE(22, "Profile has already expired."), 
    INVALID_USER(20, "Either the username of the password is invalid."), 
    ACCESS_DENIED(403, "Access Denied.");

    private Integer code;
    private String message;

    ServiceError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
