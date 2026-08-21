package com.fireinyu.themyth.requests;

import java.util.Set;

public class DueRequest extends Request{
    protected DueRequest(String[] args) {
        super(args, 2, Set.of());
    }
}
