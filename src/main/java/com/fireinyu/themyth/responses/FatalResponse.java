package com.fireinyu.themyth.responses;

import com.fireinyu.themyth.exceptions.FatalException;
import com.fireinyu.themyth.requests.Request;

public class FatalResponse extends Response{
    /**
     * Initialises a FatalResponse in response to a thrown FatalException
     * @param cause thrown FatalException
     * @see FatalException
     */
    public FatalResponse(FatalException cause) {
        super(cause.getMessage(), true);
    }
}
