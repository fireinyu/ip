package com.fireinyu.themyth.exceptions;

public class FatalException extends RuntimeException {
    public FatalException(String message) {
        super("R.I.P. com.fireinyu.themyth.TheMyth, cause of death: " + message);
    }
}
