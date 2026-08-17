package responses;

import exceptions.FatalException;

public class FatalResponse extends Response{
    public FatalResponse(FatalException cause) {
        super(cause.getMessage());
    }
}
