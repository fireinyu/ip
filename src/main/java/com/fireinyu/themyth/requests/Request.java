package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.exceptions.ArugmentMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Request {

    public static Request from(String message) {
        String[] args = Request.parse(message);
        if (args[0].equals("bye")) {
            return new ExitRequest(args);
        } else if (args[0].equals("list")) {
            return new ListRequest(args);
        } else if (args[0].equals("mark")) {
            return new MarkRequest(args);
        } else if (args[0].equals("unmark")) {
            return new UnmarkRequest(args);
        } else if (args[0].equals("todo")) {
            return new TodoRequest(args);
        } else if (args[0].equals("deadline")) {
            return new DeadlineRequest(args);
        } else if (args[0].equals("event")) {
            return new EventRequest(args);
        } else if (args[0].equals("delete")) {
            return new DeleteRequest(args);
        } else {
            return new Request(args);
        }
    }

    private static String[] parse(String message) {
        return message.split("\s+");
    }

    private final List<String> posargs;
    private final Map<String, String> kwargs;

    private Request(String[] args) {
        this.posargs = new ArrayList<>();
        this.kwargs = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (this.isKeyword(arg)) {
                i++;
                String val = args[i];
                this.kwargs.put(arg.substring(1), val);
            } else {
                posargs.add(arg);
            }
        }
    }
    protected Request(String[] args, int numPosArgs, Set<String> kwargs) {
        this(args);
        int numPosArgsGiven = this.posargs.size();
        if (numPosArgsGiven != numPosArgs) {
            throw new ArugmentMismatchException(numPosArgs, numPosArgsGiven);
        }
        if (!kwargs.equals(this.kwargs.keySet())) {
            throw new ArugmentMismatchException(kwargs, this.kwargs.keySet());
        }
    }

    public String getArg(int at) {
        return this.posargs.get(at);
    }

    public String getArg(String key) {
        return this.kwargs.get(key);
    }

    private boolean isKeyword(String arg) {
        return arg.startsWith("/");
    }
}