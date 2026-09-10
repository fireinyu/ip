package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;

public class QuizRequest extends Request{

    protected QuizRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs);
    }

    @Override
    public List<InputFieldParser<?>> getPosArgTypes() {
        return List.of(InputFieldParser.STRING);
    }

    @Override
    public Map<String, InputFieldParser<?>> getKwargTypes() {
        return Map.of();
    }

}
