package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User request to create a new EventTask occurring between start and end times.
 * @see com.fireinyu.themyth.tasks.EventTask
 * @see Response
 */
public class EventRequest extends Request{
    protected EventRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of("from", "to"));
    }
}
