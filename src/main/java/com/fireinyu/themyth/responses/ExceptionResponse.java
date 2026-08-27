package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.FatalException;
import com.fireinyu.themyth.exceptions.TweakingException;

public class ExceptionResponse extends Response{

    /**
     * Initialises an ExceptionResponse in response to a thrown TweakingException
     * @param cause thrown TweakingException
     * @see TweakingException
     */
    public ExceptionResponse(TweakingException cause) {
        super(cause.getMessage());
    }
}
