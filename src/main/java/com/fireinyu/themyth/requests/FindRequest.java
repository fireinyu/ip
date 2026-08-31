package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.responses.Response;

/**
 * User request to list Tasks containing a keyword.
 * @see com.fireinyu.themyth.tasks.Task
 * @see Response
 */
public class FindRequest extends Request {
    protected FindRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }
}
