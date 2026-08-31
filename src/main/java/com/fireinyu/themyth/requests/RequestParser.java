package com.fireinyu.themyth.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Parser which converts each raw line of user input to a Request<br><br>
* Different user inputs are parsed into different Request types based on the provided command<br>
* The command is the first word of the user input line.
*/
public class RequestParser {
    /**
     * Parse an input line into a Request
     * @param message input line
     * @return Request corresponding to input line
     * @see String
     * @see Request
     */
    public Request parse(String message) {
        String[] args = split(message.trim());
        List<String> posArgs = new ArrayList<>();
        Map<String, String> kwargs = new HashMap<>();
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
            case "find" -> new FindRequest(posArgs, kwargs);
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
