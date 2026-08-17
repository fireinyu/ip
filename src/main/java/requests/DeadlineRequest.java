package requests;

import java.util.Set;

public class DeadlineRequest extends Request{
    protected DeadlineRequest(String[] args) {
        super(args, 2, Set.of("by"));
    }
}
