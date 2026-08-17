package atomic;

import requests.ExitRequest;
import requests.Request;
import responses.ExitResponse;

public abstract class ChatMode {
    public Response respondTo(Request request) {
        if (request instanceof ExitRequest) {
            return respondToExit((ExitRequest) request);
        }
        return respondToRemaining(request);
    }
    protected abstract Response respondToRemaining(Request request);
    protected Response respondToExit(ExitRequest request) {
        return new ExitResponse("Bye. Hope to see you again soon!");
    }
}
