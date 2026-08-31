package com.fireinyu.themyth.requests.events;

import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.responses.Response;

/**
 * InterruptEvent posted immediately before the app terminates.<br><br>
 * @see Request
 * @see Response
 */
public class CloseRequest extends InterruptEvent {
    /**
     * Initialises a CloseRequest event triggered by app termination
     * @see Request
     */
    public CloseRequest() {
        super(new String[]{});
    }
}
