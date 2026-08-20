package com.fireinyu.themyth.requests;

import java.util.Set;

public class ExitRequest extends Request {
    protected ExitRequest(String[] args) {
        super(args, 1, Set.of());
    }
}
