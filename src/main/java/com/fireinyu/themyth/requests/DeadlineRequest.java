package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.responses.Response;

/**
 * User request to create a new DeadlineRequest due by a certain datetime.
 * @see com.fireinyu.themyth.tasks.DeadlineTask
 * @see Response
 */
public class DeadlineRequest extends Request {
    /**
     * @param posArgs Positional arguments.
     * @param kwargs Keyword arguments.
     */
    protected DeadlineRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of("by"));
    }
}
