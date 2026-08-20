package com.fireinyu.themyth.requests;

import java.util.Set;

public class TodoRequest extends Request{
    protected TodoRequest(String[] args) {
        super(args, 2, Set.of());
    }
}
