package com.fireinyu.themyth.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses a raw string message into a specific {@link Request} object.
 */
public class RequestParser {
    /**
     * Parses a user's command string into a {@link Request} object.
     *
     * @param message The raw command string from the user.
     * @return A specific subclass of {@link Request} based on the command.
     */
    public Request parse(String message) {
        String[] args = split(message.trim());
        List<String> posArgs = new ArrayList<>();
        Map<String,String> kwargs = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (isKeyword(arg)) {
                i++;
                String val = args[i];
                kwargs.put(arg.substring(1), val);
            } else {
                posArgs.add(arg);
            }
        }
        String command = posArgs.isEmpty() ? "" : posArgs.get(0);
        return switch (command) {
            case "bye" -> new ExitRequest(posArgs, kwargs);
            case "list" -> new ListRequest(posArgs, kwargs);
            case "at" -> new AtRequest(posArgs, kwargs);
            case "due" -> new DueRequest(posArgs, kwargs);
            case "mark" -> new MarkRequest(posArgs, kwargs);
            case "unmark" -> new UnmarkRequest(posArgs, kwargs);
            case "todo" -> new TodoRequest(posArgs, kwargs);
            case "deadline" -> new DeadlineRequest(posArgs, kwargs);
            case "event" -> new EventRequest(posArgs, kwargs);
            case "delete" -> new DeleteRequest(posArgs, kwargs);
            default -> new Request(posArgs, kwargs);
        };
    }

    /**
     * Splits the message into parts based on whitespace.
     *
     * @param message The message to split.
     * @return An array of strings.
     */
    private String[] split(String message) {
        return message.split("\\s+");
    }

    /**
     * Checks if a string argument is a keyword argument (i.e., starts with '/').
     *
     * @param arg The argument string to check.
     * @return true if it is a keyword argument, false otherwise.
     */
    private boolean isKeyword(String arg) {
        return arg.startsWith("/");
    }

}
