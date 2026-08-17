import atomic.ChatMode;
import atomic.Request;
import atomic.Response;

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
        Request request = Request.from(message);
        Response response = chatMode.respondTo(request);
        return execute(response);
    }

    private void say(String something) {
        System.out.println(something);
        System.out.println(new StringBuilder().repeat('—', Defaults.LINEWIDTH).toString());
    }

    private boolean exit() {
        this.say("Bye. Hope to see you again soon!");
        return true;
    }

    private boolean execute(Response response) {
        this.say(response.getBody());
        return true;
    }
}

