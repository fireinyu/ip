package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User request to list all Tasks
 * @see com.fireinyu.themyth.tasks.Task
 * @see Response
 */
public class ListRequest extends Request{
    protected ListRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 1, Set.of());
    }
}
