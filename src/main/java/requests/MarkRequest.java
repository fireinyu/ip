package requests;

import java.util.Set;

public class MarkRequest extends Request{
    protected MarkRequest(String[] args) {
        super(args, 2, Set.of());
    }
}
