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

import java.util.Scanner;

public class TheMyth {
    public static void main(String[] args) {
/*        String banner = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
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

    public TheMyth() {
        this.say("""
                Hello! I'm The Myth.
                What can I do for you?
                """);
    }

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

