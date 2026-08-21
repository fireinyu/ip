package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DueRequest extends Request{
    protected DueRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 2, Set.of());
    }
}
