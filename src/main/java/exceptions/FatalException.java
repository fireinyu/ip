package exceptions;

public class FatalException extends RuntimeException {
    public FatalException(String message) {
        super("R.I.P. TheMyth, cause of death: " + message);
    }
}
