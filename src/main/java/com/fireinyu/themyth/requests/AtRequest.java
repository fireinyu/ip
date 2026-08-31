package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.responses.Response;


/**
 * User request to list all EventTasks occurring at some given instance
 * @see com.fireinyu.themyth.tasks.EventTask
 * @see Response
 */
public class AtRequest extends Request {
    protected AtRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }
}
