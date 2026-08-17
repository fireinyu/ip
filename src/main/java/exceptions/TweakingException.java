package exceptions;

public abstract class TweakingException extends RuntimeException {
    public TweakingException(String message) {
        super("I'm tweaking because " + message);
    }
}
