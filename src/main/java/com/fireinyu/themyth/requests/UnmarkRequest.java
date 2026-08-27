package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User request to mark a Task as completed.
 * @see com.fireinyu.themyth.tasks.Task
 * @see Response
 */
public class UnmarkRequest extends Request{
    protected UnmarkRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }
}
