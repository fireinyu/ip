package com.fireinyu.themyth.exceptions;

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
