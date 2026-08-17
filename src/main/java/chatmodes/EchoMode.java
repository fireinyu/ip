package chatmodes;

import requests.Request;
import responses.Response;

public class EchoMode extends ChatMode {
    @Override
    protected Response respondToRemaining(Request request) {
        return new Response("Can you tell me more about " + request.getArg(1) + '?');
    }
}

