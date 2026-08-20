package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.TweakingException;

public class ExceptionResponse extends Response{
    public ExceptionResponse(TweakingException cause) {
        super(cause.getMessage());
    }
}
