package com.fireinyu.themyth.requests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fireinyu.themyth.exceptions.ArugmentMismatchException;
import com.fireinyu.themyth.responses.Response;

/**
 * User request which triggers a user cycle.<br><br>
 * Each Request is handled by the active ChatMode, which produces a Response.<br>
 * The app then handles the Response and then waits for the next Request.<br>
 * @see com.fireinyu.themyth.chatmodes.ChatMode
 * @see Response
 */
public class Request {


    private final List<String> posargs;
    private final Map<String, String> kwargs;

    protected Request(List<String> posArgs, Map<String, String> kwargs, int numPosArgs, Set<String> kwargNames) {
        if (posArgs.size() != numPosArgs) {
            throw new ArugmentMismatchException(numPosArgs, posArgs.size());
        }
        if (!kwargNames.equals(kwargs.keySet())) {
            throw new ArugmentMismatchException(kwargNames, kwargs.keySet());
        }
        this(posArgs, kwargs);
    }

    protected Request(List<String> posArgs, Map<String, String> kwargs) {
        this.posargs = posArgs;
        this.kwargs = kwargs;
    }

    private static String[] parse(String message) {
        return message.split("\s+");
    }

    /**
     * Get the value of a positional argument
     * @param at the argument's position (0 is the command)
     * @return the value of the positional argument
     * @see String
     */
    public String getArg(int at) {
        return this.posargs.get(at);
    }

    /**
     * Get the value of a keyword argument
     * @param key the argument's keyword
     * @return the value of the keyword argument
     * @see String
     */
    public String getArg(String key) {
        return this.kwargs.get(key);
    }

}
