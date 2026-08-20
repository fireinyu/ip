package com.fireinyu.themyth.requests;

import java.util.Set;

public class DeleteRequest extends Request{
    protected DeleteRequest(String[] args) {
        super(args, 2, Set.of());
    }
}
