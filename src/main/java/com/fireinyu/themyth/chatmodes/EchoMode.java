package com.fireinyu.themyth.chatmodes;

import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.responses.Response;

/**
 * Request-Response model that echos the user input.
 * @see Request
 * @see Response
 */
public class EchoMode extends ChatMode {
    @Override
    protected Response respondToRemaining(Request request) {
        return new Response("Can you tell me more about " + request.getArg(0) + '?');
    }
}

