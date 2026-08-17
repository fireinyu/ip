package atomic;

public abstract class ChatMode {
    public Response respondTo(Request request) {
        return respondToRemaining(request);
    }
    protected abstract Response respondToRemaining(Request request);
}
