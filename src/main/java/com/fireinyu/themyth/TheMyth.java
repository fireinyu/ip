package com.fireinyu.themyth;

import com.fireinyu.themyth.chatmodes.ChatMode;
import com.fireinyu.themyth.exceptions.FatalException;
import com.fireinyu.themyth.exceptions.TweakingException;
import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.requests.RequestParser;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.requests.events.InterruptEvent;
import com.fireinyu.themyth.responses.ExceptionResponse;
import com.fireinyu.themyth.responses.FatalResponse;
import com.fireinyu.themyth.responses.Response;

/**
 * The main app object and entry point of The Myth chatbot.
 */
public class TheMyth {

    private final RequestParser parser = new RequestParser();
    private final ChatMode chatMode = Defaults.STARTMODE;

    /**
     * Initialises an instance of The Myth app<br><br>
     * The app will display a banner, then enter idle mode
     */
    public TheMyth() {
    }

    /**
     * Start the app
     */
    public void start() {
        this.interruptCycle(new InitRequest());
    }

    /**
     * Stop the app
     */
    public void stop() {
        this.interruptCycle(new CloseRequest());
    }

    /**
     * Handles input
     * @param input input
     * @return response to input
     */
    public Response handleInput(String input) {
        Response response = null;
        try {
            Request request = parser.parse(input);
            response = chatMode.respondTo(request);
        } catch (TweakingException e) {
            response = new ExceptionResponse(e);
        } catch (FatalException e) {
            response = new FatalResponse(e);
        }
        if (response.doExit()) {
            this.stop();
        }
        return response;
    }

    /**
     * Runs a single interrupt cycle of The Myth in response to an InterruptEvent <br><br>
     * Interrupt cycles originate from within the program.
     * The cause and details of interrupt are encapsulated in the InterruptEvent<br>
     * Summary of actions: receive InterruptEvent -> ChatMode handles InterruptEvent
     * -> obtain Response -> execute Response
     * @param event: the interrupt event that caused this interrupt cycle
     * @see  InterruptEvent
     * @see  ChatMode
     * @see  Response
     */
    private void interruptCycle(InterruptEvent event) {
        Response response = null;
        try {
            response = chatMode.respondTo(event);
        } catch (TweakingException e) {
            response = new ExceptionResponse(e);
        } catch (FatalException e) {
            response = new FatalResponse(e);
            this.stop();
        }
        System.out.println(response.getBody());
    }
}

