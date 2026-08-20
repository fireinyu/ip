package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.FatalException;

public class FatalResponse extends Response{
    public FatalResponse(FatalException cause) {
        super(cause.getMessage(), true);
    }
}
