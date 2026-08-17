package exceptions;

import requests.Request;

public class InvalidCommandException extends TweakingException {
    public InvalidCommandException(Request request) {
        super(String.format("I don't know how to \"%s\"", request.getArg(0)));
    }
}
