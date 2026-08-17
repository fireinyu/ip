package chatmodes;

import requests.*;
import responses.Response;
import responses.ExitResponse;

public abstract class ChatMode {
    public Response respondTo(Request request) {
        if (request instanceof ExitRequest) {
            return this.respondToExit((ExitRequest) request);
        } else if (request instanceof ListRequest) {
            return this.respondToList((ListRequest) request);
        } else if (request instanceof MarkRequest) {
            return this.respondToMark((MarkRequest) request);
        } else if (request instanceof UnmarkRequest) {
            return this.respondToUnmark((UnmarkRequest) request);
        } else {
            return this.respondToRemaining(request);
        }
    }
    protected abstract Response respondToRemaining(Request request);
    protected Response respondToUnmark(UnmarkRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToMark(MarkRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToList(ListRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToExit(ExitRequest request) {
        return new ExitResponse("Bye. Hope to see you again soon!");
    }
}
