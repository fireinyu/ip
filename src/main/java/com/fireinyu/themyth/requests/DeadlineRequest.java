package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User request to create a new DeadlineRequest due by a certain datetime.
 * @see com.fireinyu.themyth.tasks.DeadlineTask
 * @see Response
 */
public class DeadlineRequest extends Request{
    protected DeadlineRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of("by"));
    }
}
