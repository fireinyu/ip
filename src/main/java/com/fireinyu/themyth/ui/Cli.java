package com.fireinyu.themyth.ui;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.TheMyth;
import com.fireinyu.themyth.responses.ExceptionResponse;
import com.fireinyu.themyth.responses.FatalResponse;
import com.fireinyu.themyth.responses.Response;

import java.util.Scanner;

public class Cli {

    private final TheMyth model;
    private final Scanner scanner = new Scanner(System.in);


    public Cli(TheMyth model) {
        this.model = model;
    }

    public void run() {
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
        this.model.start();
        this.say("""
                Hello! I'm The Myth.
                What can I do for you?
                """);
        while(true) {
            String input = this.scanner.nextLine();
            Response response = this.model.handleInput(input);
            if (response instanceof ExceptionResponse) {
                this.say(Defaults.TWEAKPROMPT, response.getBody());
            } else if (response instanceof FatalResponse) {
                this.say(Defaults.DEATHPROMPT, response.getBody());
            } else {
                this.say(response.getBody());
            }
            if (response.doExit()) {
                break;
            }
        }
    }

    private void say(String botPrompt, String something) {
        System.out.println(new StringBuilder().repeat('—', Defaults.LINEWIDTH).toString());
        System.out.println(botPrompt + '\n' + something.indent(4));
        System.out.println(new StringBuilder().repeat('—', Defaults.LINEWIDTH).toString());
        System.out.print(Defaults.USERPROMPT);
    }

    private void say(String something) {
        this.say(Defaults.BOTPROMPT, something);
    }


    public static void main(String... args) {
        new Cli(new TheMyth()).run();
    }
}
