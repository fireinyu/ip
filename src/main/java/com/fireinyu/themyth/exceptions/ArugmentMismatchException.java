package com.fireinyu.themyth.exceptions;

import java.util.Collection;

/**
 * Thrown when the user provides the wrong number or names of input arguments
 */
public class ArugmentMismatchException extends TweakingException {

    /**
     * Initialises an ArugmentMismatchException caused by wrong number of positional arguments supplied in a Request
     * @param expectedPosCount expected number of positional arguments
     * @param givenPosCount number of positional arguments supplied
     * @see com.fireinyu.themyth.requests.Request
     */
    public ArugmentMismatchException(int expectedPosCount, int givenPosCount) {
        super(String.format("%d positional arguments expected but %d given", expectedPosCount, givenPosCount));
    }

    /**
     * Initialises an ArugmentMismatchException caused by wrong set of keyword arguments supplied in a Request
     * @param expectedKwargs expected keyword arguments
     * @param givenKwargs keyword arguments supplied
     * @see com.fireinyu.themyth.requests.Request
     */
    public ArugmentMismatchException(Collection<String> expectedKwargs, Collection<String> givenKwargs) {
        super(String.format("expected keyword arguments: {%s}, given keyword arguments: {%s}",
                String.join(", ", expectedKwargs),
                String.join(", ", givenKwargs)
        ));
    }
}
