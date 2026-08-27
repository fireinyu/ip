package com.fireinyu.themyth.exceptions;

/**
 * Thrown when attempting to read a disk file with corrupted data.
 */
public class CorruptedTaskFileException extends TweakingException{

    /**
     * Initialises a CorruptedTaskFileException
     * @param filename name of file with corrupted data
     * @see String
     */
    public CorruptedTaskFileException(String filename) {
        super(String.format("file \"%s\" contains corrupted data, defaulting to memory-only mode", filename));
    }
}
