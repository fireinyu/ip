package com.fireinyu.themyth.exceptions;

import com.fireinyu.themyth.Defaults;

public class DateFormatException extends ArgumentFormatException{
    public DateFormatException(String badArg) {
        super("date", badArg, Defaults.DATE_INPUTFORMAT);
    }
}
