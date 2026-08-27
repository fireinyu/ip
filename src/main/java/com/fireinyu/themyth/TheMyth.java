package com.fireinyu.themyth;

import com.fireinyu.themyth.chatmodes.ChatMode;
import com.fireinyu.themyth.exceptions.FatalException;
import com.fireinyu.themyth.exceptions.TweakingException;
import com.fireinyu.themyth.requests.Request;
import com.fireinyu.themyth.requests.RequestParser;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InterruptEvent;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.responses.ExceptionResponse;
import com.fireinyu.themyth.responses.FatalResponse;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.util.MythDateTime;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * The main app object and entry point of The Myth chatbot.
 */
public class TheMyth {

    /**
     * Entry-point of app<br><br>
     * The app runs on a user-loop architecture with interrupt cycles for events
     */
    public static void main(String[] args) {
        String banner = """
                        ▄▄▄█████▓ ██░ ██ ▓█████     ███▄ ▄███▓▓██   ██▓▄▄▄█████▓ ██░ ██\s
                        ▓  ██▒ ▓▒▓██░ ██▒▓█   ▀    ▓██▒▀█▀ ██▒ ▒██  ██▒▓  ██▒ ▓▒▓██░ ██▒
                        ▒ ▓██░ ▒░▒██▀▀██░▒███      ▓██    ▓██░  ▒██ ██░▒ ▓██░ ▒░▒██▀▀██░
                        ░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ▒██    ▒██   ░ ▐██▓░░ ▓██▓ ░ ░▓█ ░██\s
                          ▒██▒ ░ ░▓█▒░██▓░▒████▒   ▒██▒   ░██▒  ░ ██▒▓░  ▒██▒ ░ ░▓█▒░██▓
                          ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░   ░ ▒░   ░  ░   ██▒▒▒   ▒ ░░    ▒ ░░▒░▒
                            ░     ▒ ░▒░ ░ ░ ░  ░   ░  ░      ░ ▓██ ░▒░     ░     ▒ ░▒░ ░
                          ░       ░  ░░ ░   ░      ░      ░    ▒ ▒ ░░    ░       ░  ░░ ░
                                  ░  ░  ░   ░  ░          ░    ░ ░               ░  ░  ░
                                                               ░ ░                     \s
                """;
        System.out.println(banner);
        TheMyth cs2103t = new TheMyth();
        if (cs2103t.interruptCycle(new InitRequest())) {
            while (cs2103t.userCycle());
        }
    }

    private final Scanner scanner = new Scanner(System.in);
    private final RequestParser parser = new RequestParser();
    private final ChatMode chatMode = Defaults.STARTMODE;

    /**
     * Initialises an instance of The Myth app<br><br>
     * The app will display a banner, then enter idle mode
     */
    public TheMyth() {
        this.say("""
                Hello! I'm The Myth.
                What can I do for you?
                """);
    }

    /**
     * Runs a single user cycle of The Myth<br><br>
     * Summary of actions: read Request -> ChatMode handles Request -> obtain Response -> execute Response
     * @return whether to continue running The Myth
     * @see Request
     * @see ChatMode
     * @see Response
     */
    public boolean userCycle() {
        String message = scanner.nextLine();
        Response response = null;
        try {
            Request request = parser.parse(message);
            response = chatMode.respondTo(request);
        } catch (TweakingException e) {
            response = new ExceptionResponse(e);
        } catch (FatalException e) {
            response = new FatalResponse(e);
        }
        return execute(response);
    }

    /**
     * Runs a single interrupt cycle of The Myth in response to an InterruptEvent <br><br>
     * Interrupt cycles originate from within the program. The cause and details of interrupt are encapsulated in the InterruptEvent<br>
     * Summary of actions: receive InterruptEvent -> ChatMode handles InterruptEvent -> obtain Response -> execute Response
     * @param event: the interrupt event that caused this interrupt cycle
     * @return whether to continue running The Myth
     * @see  InterruptEvent
     * @see  ChatMode
     * @see  Response
     */
    private boolean interruptCycle(InterruptEvent event) {
        Response response = null;
        try {
            response = chatMode.respondTo(event);
        } catch (TweakingException e) {
            response = new ExceptionResponse(e);
        } catch (FatalException e) {
            response = new FatalResponse(e);
        }
        return execute(response);
    }

    private void say(String something) {
        this.say(Defaults.BOTPROMPT, something);
    }

    private void say(String botPrompt, String something) {
        System.out.println(new StringBuilder().repeat('—', Defaults.LINEWIDTH).toString());
        System.out.println(botPrompt + '\n' + something.indent(4));
        System.out.println(new StringBuilder().repeat('—', Defaults.LINEWIDTH).toString());
        System.out.print(Defaults.USERPROMPT);
    }

    private boolean execute(Response response) {
        if (response instanceof ExceptionResponse) {
            this.say(Defaults.TWEAKPROMPT, response.getBody());
        } else if (response instanceof FatalResponse) {
            this.say(Defaults.DEATHPROMPT, response.getBody());
        } else {
            this.say(response.getBody());
        }
        if (response.doExit()) {
            this.interruptCycle(new CloseRequest());
        }
        return !(response.doExit());
    }
}

