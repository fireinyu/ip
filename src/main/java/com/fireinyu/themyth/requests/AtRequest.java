package com.fireinyu.themyth.requests;

import java.util.Set;

public class AtRequest extends Request{
    protected AtRequest(String[] args) {
        super(args, 2, Set.of());
    }
}
