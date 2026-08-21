package com.fireinyu.themyth.exceptions;

public class FileAccessException extends TweakingException{
    public FileAccessException(String filename) {
        super(String.format("unable to access file \"%s\", defaulting to memory-only mode", filename));
    }
}
