package com.fireinyu.themyth.exceptions;

/** Thrown when an argument has an unexpected type. */
public class WrongTypeException extends TweakingException {
    /**
     * Initialises an exception for an unexpected argument type.
     *
     * @param type the expected argument type
     */
    public WrongTypeException(Class<?> type) {
        super(String.format("expected type: %s", type.toString()));
    }
}
