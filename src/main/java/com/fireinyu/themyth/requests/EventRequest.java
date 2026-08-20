package com.fireinyu.themyth.requests;

import java.util.Set;

public class EventRequest extends Request{
    protected EventRequest(String[] args) {
        super(args, 2, Set.of("from", "to"));
    }
}
