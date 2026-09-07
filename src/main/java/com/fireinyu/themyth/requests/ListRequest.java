package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.responses.Response;

/**
 * User request to list all Tasks
 * @see com.fireinyu.themyth.tasks.Task
 * @see Response
 */
public class ListRequest extends Request {
    /**
     * @param posArgs Positional arguments.
     * @param kwargs Keyword arguments.
     */
    protected ListRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, Map.of("sort", Defaults.TASK_ORDER));
    }
    /** {@inheritDoc} */
    @Override
    public List<InputFieldParser<?>> getPosArgTypes() {
        return List.of(InputFieldParser.STRING);
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, InputFieldParser<?>> getKwargTypes() {
        return Map.of("sort", InputFieldParser.ORDER);
    }
}
