package com.fireinyu.themyth.requests.events;

import com.fireinyu.themyth.requests.Request;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class InterruptEvent extends Request {
    private static List<String> getArgs(String[] params) {
        String[] res = new String[params.length + 1];
        res[0] = "interrupt";
        System.arraycopy(params, 0, res, 1, params.length);
        return List.of(res);
    }
    protected InterruptEvent(String[] params) {
        super(InterruptEvent.getArgs(params), Map.of(), params.length + 1, Set.of());
    }

}
