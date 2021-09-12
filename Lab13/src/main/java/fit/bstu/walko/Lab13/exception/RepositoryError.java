package fit.bstu.walko.Lab13.exception;

public class RepositoryError extends Exception{

    public RepositoryError(String message) {
        super(message);
    }

    public RepositoryError(Throwable throwable){
        super(throwable);
    }

    public  RepositoryError(Throwable throwable, String message){
        super(message,throwable);
    }

}
