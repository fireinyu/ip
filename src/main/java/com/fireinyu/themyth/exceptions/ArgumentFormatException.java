package com.fireinyu.themyth.exceptions;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.tasks.Task;

/**
 * Thrown when the user provides badly-formatted input arguments.
 */
public class ArgumentFormatException extends TweakingException {

    /**
     * Initialises a ArgumentFormatException caused by a malformed argument to a Request
     * @param type name of type of argument that is malformed
     * @param badArg malformed argument
     * @param format expected argument format
     * @see String
     * @see com.fireinyu.themyth.requests.Request
     */
    public ArgumentFormatException(String type, String badArg, String format) {
        super(String.format("%s argument \"%s\" is ill-formatted, pls use %s format!", type, badArg, format));
    }
}