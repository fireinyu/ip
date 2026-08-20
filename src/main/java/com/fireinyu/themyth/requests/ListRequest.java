package com.fireinyu.themyth.requests;

import java.util.Set;

public class ListRequest extends Request{
    protected ListRequest(String[] args) {
        super(args, 1, Set.of());
    }
}
