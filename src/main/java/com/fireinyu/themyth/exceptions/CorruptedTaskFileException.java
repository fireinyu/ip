package com.fireinyu.themyth.exceptions;

public class CorruptedTaskFileException extends TweakingException{
    public CorruptedTaskFileException(String filename) {
        super(String.format("file \"%s\" contains corrupted data, defaulting to memory-only mode", filename));
    }
}
