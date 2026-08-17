import chatmodes.ChatMode;
import exceptions.FatalException;
import exceptions.TweakingException;
import requests.Request;
import responses.ExceptionResponse;
import responses.FatalResponse;
import responses.Response;

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
        while (cs2103t.userCycle());

    }

    private final Scanner scanner = new Scanner(System.in);
    private ChatMode chatMode = Defaults.STARTMODE;

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
            Request request = Request.from(message);
            response = chatMode.respondTo(request);
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
        return !(response.doExit());
    }
}

