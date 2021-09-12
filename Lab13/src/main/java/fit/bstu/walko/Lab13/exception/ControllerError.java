package fit.bstu.walko.Lab13.exception;

public class ControllerError extends Exception{
    public ControllerError(String message) {
        super(message);
    }

    public ControllerError(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerError(Throwable cause) {
        super(cause);
    }
}
