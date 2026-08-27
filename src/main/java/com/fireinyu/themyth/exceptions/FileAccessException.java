package com.fireinyu.themyth.exceptions;

/**
 * Thrown when the app is unable to read or write to a disk file
 * @see TweakingException
 */
public class FileAccessException extends TweakingException{

    /**
     * Initialises a FileAccessException
     * @param filename name of file which cannot be accessed
     * @see String
     */
    public FileAccessException(String filename) {
        super(String.format("unable to access file \"%s\", defaulting to memory-only mode", filename));
    }
}
