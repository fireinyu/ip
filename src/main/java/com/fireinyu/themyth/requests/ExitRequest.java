package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExitRequest extends Request {
    protected ExitRequest(List<String> posArgs, Map<String, String> kwargs) {
        super(posArgs, kwargs, 1, Set.of());
    }
}
