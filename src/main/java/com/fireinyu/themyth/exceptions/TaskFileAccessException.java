package com.fireinyu.themyth.exceptions;

public class TaskFileAccessException extends TweakingException{
    public TaskFileAccessException(String filename) {
        super(String.format("unable to access file \"%s\", defaulting to memory-only mode", filename));
    }
}
