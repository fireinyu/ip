package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.responses.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * User request to create a new TodoTask.
 * @see com.fireinyu.themyth.tasks.TodoTask
 * @see Response
 */
public class TodoRequest extends Request{
    protected TodoRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }}
