package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.responses.Response;

/**
 * User request to exit the app.
 * @see com.fireinyu.themyth.tasks.EventTask
 * @see Response
 */
public class ExitRequest extends Request {
    protected ExitRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 1, Set.of());
    }
}
