package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.TweakingException;

/**
 * Response to a Request from the user or an event, which resulted in an TweakingException.<br><br>
 * Exits the app after execution.
 * @see com.fireinyu.themyth.chatmodes.ChatMode
 * @see com.fireinyu.themyth.TheMyth
 * @see TweakingException
 */
public class ExceptionResponse extends Response {

    /**
     * Initialises an ExceptionResponse in response to a thrown TweakingException
     * @param cause thrown TweakingException
     * @see TweakingException
     */
    public ExceptionResponse(TweakingException cause) {
        super(cause.getMessage());
    }
}
