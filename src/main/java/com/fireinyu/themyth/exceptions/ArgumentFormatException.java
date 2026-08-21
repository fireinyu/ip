package com.fireinyu.themyth.exceptions;

import com.fireinyu.themyth.Defaults;

public class ArgumentFormatException extends TweakingException {
    public ArgumentFormatException(String type, String badArg, String format) {
        super(String.format("%s argument \"%s\" is ill-formatted, pls use %s format!", type, badArg, format));
    }
}