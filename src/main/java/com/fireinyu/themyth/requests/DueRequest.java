package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User request to list all DeadlineTasks due by a given datetime
 * @see com.fireinyu.themyth.tasks.DeadlineTask
 * @see Response
 */
public class DueRequest extends Request{
    protected DueRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }
}
