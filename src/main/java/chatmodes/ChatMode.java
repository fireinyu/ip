package chatmodes;

import responses.Response;
import requests.ExitRequest;
import requests.ListRequest;
import requests.Request;
import responses.ExitResponse;

public abstract class ChatMode {
    public Response respondTo(Request request) {
        if (request instanceof ExitRequest) {
            return this.respondToExit((ExitRequest) request);
        } else if (request instanceof ListRequest) {
            return this.respondToList((ListRequest) request);
        } else {
            return this.respondToRemaining(request);
        }
    }
    protected abstract Response respondToRemaining(Request request);
    protected Response respondToList(ListRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToExit(ExitRequest request) {
        return new ExitResponse("Bye. Hope to see you again soon!");
    }
}
