package com.fireinyu.themyth.exceptions;

public class CorruptedTaskFileException extends TweakingException{
    public CorruptedTaskFileException(String filename) {
        super(String.format("unable to access file \"%s\", defaulting to memory-only mode", filename));
    }
}
