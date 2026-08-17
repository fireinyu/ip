import atomic.ChatMode;
import requests.ExitRequest;
import requests.Request;
import atomic.Response;
import responses.ExitResponse;

public class EchoMode extends ChatMode {
    @Override
    protected Response respondToRemaining(Request request) {
        return new Response("Can you tell me more about " + request.getMessage()+ '?');
    }
}

