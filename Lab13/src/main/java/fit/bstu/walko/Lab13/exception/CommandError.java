package fit.bstu.walko.Lab13.exception;

public class CommandError extends Exception{
    public CommandError(String message) {
        super(message);
    }

    public CommandError(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandError(Throwable cause) {
        super(cause);
    }
}
