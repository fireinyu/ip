package com.fireinyu.themyth.chatmodes;

import com.fireinyu.themyth.exceptions.InvalidCommandException;
import com.fireinyu.themyth.requests.*;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.responses.ExitResponse;

/**
 * Request-Response model of the app. Responds to different types of Requests by returning Responses.
 * @see Request
 * @see Response
 */
public abstract class ChatMode {

    /**
     * Provide a Response to a Request
     * @param request the Request to respond to
     * @return the Response
     * @see Request
     * @see Response
     */
    public Response respondTo(Request request) {
        if (request instanceof InitRequest) {
            return this.respondToInit((InitRequest) request);
        }else if (request instanceof CloseRequest) {
            return this.respondToClose((CloseRequest) request);
        }else if (request instanceof ExitRequest) {
            return this.respondToExit((ExitRequest) request);
        } else if (request instanceof ListRequest) {
            return this.respondToList((ListRequest) request);
        } else if (request instanceof AtRequest) {
            return this.respondToAt((AtRequest) request);
        } else if (request instanceof DueRequest) {
            return this.respondToDue((DueRequest) request);
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
        } else if (request instanceof DeleteRequest) {
            return this.respondToDelete((DeleteRequest) request);
        } else {
            return this.respondToRemaining(request);
        }
    }
    protected Response respondToRemaining(Request request) {
        throw new InvalidCommandException(request);
    }
    protected Response respondToInit(InitRequest request) {
        return new Response("no init handler");
    }
    protected Response respondToClose(CloseRequest request) {
        return new Response("no close handler");
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
    protected Response respondToAt(AtRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToDue(DueRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToDelete(DeleteRequest request) {
        return this.respondToRemaining(request);
    }
    protected Response respondToExit(ExitRequest request) {
        return new ExitResponse("Bye. Hope to see you again soon!");
    }
}
