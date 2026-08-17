package requests;

public class Request {

    public static Request from (String message) {
        if (message.equals("bye")) {
            return new ExitRequest(message);
        } else {
            return new Request(message);
        }
    }
    private String message;

    protected Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}


