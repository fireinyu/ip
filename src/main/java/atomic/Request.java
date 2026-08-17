package atomic;

public class Request {

    public static Request from (String message) {
        return new Request(message);
    }
    private String message;

    protected Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}


