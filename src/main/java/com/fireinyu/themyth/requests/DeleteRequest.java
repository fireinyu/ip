package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User request to delete an existing Task
 * @see com.fireinyu.themyth.tasks.Task
 * @see Response
 */
public class DeleteRequest extends Request{
    protected DeleteRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }

}
