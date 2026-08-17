import atomic.ChatMode;
import atomic.Request;
import atomic.Response;

public class EchoMode extends ChatMode {
    @Override
    protected Response respondToRemaining(Request request) {
        return new Response(request.getMessage());
    }
}

