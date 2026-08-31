package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.responses.Response;

/**
 * User request to mark a Task as completed.
 * @see com.fireinyu.themyth.tasks.Task
 * @see Response
 */
public class UnmarkRequest extends Request {
    protected UnmarkRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }
}
