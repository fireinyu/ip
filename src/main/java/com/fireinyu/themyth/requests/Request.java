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
        return switch (args[0]) {
            case "bye" -> new ExitRequest(args);
            case "list" -> new ListRequest(args);
            case "at" -> new AtRequest(args);
            case "due" -> new DueRequest(args);
            case "mark" -> new MarkRequest(args);
            case "unmark" -> new UnmarkRequest(args);
            case "todo" -> new TodoRequest(args);
            case "deadline" -> new DeadlineRequest(args);
            case "event" -> new EventRequest(args);
            case "delete" -> new DeleteRequest(args);
            default -> new Request(args);
        };
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