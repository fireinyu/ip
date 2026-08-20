package com.fireinyu.themyth.requests.events;

import com.fireinyu.themyth.requests.Request;

import java.util.Arrays;
import java.util.Set;

public abstract class InterruptEvent extends Request {
    private static String[] getArgs(String[] params) {
        String[] res = new String[params.length + 1];
        res[0] = "interrupt";
        System.arraycopy(params, 0, res, 1, params.length);
        return res;
    }
    protected InterruptEvent(String[] params) {
        super(InterruptEvent.getArgs(params), params.length + 1, Set.of());
    }

}
