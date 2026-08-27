package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.FatalException;

public class ExitResponse extends Response {

    /**
     * Initialises an ExitResponse indicating an action that will terminate the app
     * @param body exit message
     * @see String
     */
    public ExitResponse(String body) {
        super(body, true);
    }
}
