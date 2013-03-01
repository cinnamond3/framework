package framework.ui.exceptions;

public class AuditloggingException extends RuntimeException{

    private static final long serialVersionUID = -4588382402171894572L;

    public AuditloggingException(String message, Throwable cause) {
        super(message, cause);
    }
}
