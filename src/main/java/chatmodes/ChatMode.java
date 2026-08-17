package chatmodes;

import exceptions.InvalidCommandException;
import exceptions.TweakingException;
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
        } else if (request instanceof TodoRequest) {
            return this.respondToTodo((TodoRequest) request);
        } else if (request instanceof DeadlineRequest) {
            return this.respondToDeadline((DeadlineRequest) request);
        } else if (request instanceof EventRequest) {
            return this.respondToEvent((EventRequest) request);

        } else {
            return this.respondToRemaining(request);
        }
    }
    protected Response respondToRemaining(Request request) {
        throw new InvalidCommandException(request);
    }
    protected Response respondToTodo(TodoRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToDeadline(DeadlineRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToEvent(EventRequest request) {
        return this.respondToRemaining(request);
    }
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
