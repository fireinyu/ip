package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.exceptions.ArugmentMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Request {

    private static String[] parse(String message) {
        return message.split("\s+");
    }

    private final List<String> posargs;
    private final Map<String, String> kwargs;

    protected Request(List<String> posArgs, Map<String, String> kwargs, int numPosArgs, Set<String> kwargNames) {
        if (posArgs.size() != numPosArgs) {
            throw new ArugmentMismatchException(numPosArgs, posArgs.size());
        }
        if (!kwargNames.equals(kwargs.keySet())) {
            throw new ArugmentMismatchException(kwargNames, kwargs.keySet());
        }
        this(posArgs, kwargs);
    }

    protected Request(List<String> posArgs, Map<String, String> kwargs) {
        this.posargs = posArgs;
        this.kwargs = kwargs;
    }

    public String getArg(int at) {
        return this.posargs.get(at);
    }

    public String getArg(String key) {
        return this.kwargs.get(key);
    }

}