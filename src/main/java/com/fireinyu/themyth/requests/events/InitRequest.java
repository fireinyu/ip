package com.fireinyu.themyth.requests.events;

import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.responses.Response;

/**
 * InterruptEvent posted immediately after the app is started<br><br>
 * @see Request
 * @see Response
 */
public class InitRequest extends InterruptEvent {
    /**
     * Initialises an InitRequest event triggered by app initialization
     * @see Request
     */
    public InitRequest() {
        super(new String[]{});
    }
}
