package com.fireinyu.themyth.responses;


/**
 * Response to a Request to exit the app.<br><br>
 * Exits the app after execution.
 * @see com.fireinyu.themyth.chatmodes.ChatMode
 * @see com.fireinyu.themyth.TheMyth
 */
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
