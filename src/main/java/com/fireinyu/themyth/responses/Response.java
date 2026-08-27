package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.FatalException;
import com.fireinyu.themyth.requests.Request;


/**
 * Response to a Request from the user or an event. Produced by the active ChatMode.<br><br>
 * Also includes instructions for whether to continue executing the app.
 * @see com.fireinyu.themyth.chatmodes.ChatMode
 * @see com.fireinyu.themyth.TheMyth
 * @see Response
 */
public class Response {
    private String body;
    private boolean exit;

    /**
     * Initialises a Response with a message body<br><br>
     * The app will continue to execute user cycle after the Response is executed
     * @param body message body
     * @see String
     */
    public Response(String body) {
        this(body, false);
    }
    /**
     * Initialises a Response with a message body<br><br>
     * Depending on <i>exit</i>, the app will either terminate with a CloseRequest or continue to execute user cycle after the Response is executed
     * @param body message body
     * @see String
     * @see com.fireinyu.themyth.requests.events.CloseRequest
     */
    public Response(String body, boolean exit) {
        this.body = body;
        this.exit = exit;
    }

    /**
     * Get the message body of this Response
     * @return message body of this Response
     * @see String
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Returns true if the active ChatMode should exit after handling this Response
     * @return true if the active ChatMode  should exit after handling this Response
     * @see com.fireinyu.themyth.chatmodes.ChatMode
     */
    public boolean doExit() {
        return exit;
    }
}
