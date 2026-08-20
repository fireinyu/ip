package com.fireinyu.themyth.responses;

public class Response {
    private String body;
    private boolean exit;

    public Response(String body) {
        this(body, false);
    }

    public Response(String body, boolean exit) {
        this.body = body;
        this.exit = exit;
    }

    public String getBody() {
        return this.body;
    }

    public boolean doExit() {
        return exit;
    }
}
