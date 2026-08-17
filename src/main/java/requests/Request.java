package requests;

public class Request {

    public static Request from(String message) {
        String[] args = Request.parse(message);
        if (args[0].equals("bye")) {
            return new ExitRequest(args);
        } else if (args[0].equals("list")) {
            return new ListRequest(args);
        } else if (args[0].equals("mark")) {
            return new MarkRequest(args);
        } else if (args[0].equals("unmark")) {
            return new UnmarkRequest(args);
        } else {
            return new Request(args);
        }
    }

    private static String[] parse(String message) {
        return message.split("\s+");
    }

    private final String[] args;

    protected Request(String[] args) {
        this.args = args;
    }

    public String getArg(int at) {
        return this.args[at];
    }
}


