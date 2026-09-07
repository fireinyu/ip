package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;

import com.fireinyu.themyth.responses.Response;

/**
 * User request to create a new TodoTask.
 * @see com.fireinyu.themyth.tasks.TodoTask
 * @see Response
 */
public class TodoRequest extends Request {
    /**
     * @param posArgs Positional arguments.
     * @param kwargs Keyword arguments.
     */
    protected TodoRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs);
    }
    /** {@inheritDoc} */
    @Override
    public List<InputFieldParser<?>> getPosArgTypes() {
        return List.of(InputFieldParser.STRING, InputFieldParser.STRING);
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, InputFieldParser<?>> getKwargTypes() {
        return Map.of();
    }

}
