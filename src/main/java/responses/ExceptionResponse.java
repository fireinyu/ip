package responses;

import exceptions.TweakingException;

public class ExceptionResponse extends Response{
    public ExceptionResponse(TweakingException cause) {
        super(cause.getMessage());
    }
}
