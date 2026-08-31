package com.fireinyu.themyth.requests.events;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.responses.Response;

/**
 * Request posted from the app instead of the user.<br><br>
 * Priority is taken to handle InterruptEvents before regular user Requests.
 * @see Request
 * @see Response
 */
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
