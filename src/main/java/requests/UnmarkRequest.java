package requests;

import java.util.Set;

public class UnmarkRequest extends Request{
    protected UnmarkRequest(String[] args) {
        super(args, 2, Set.of());
    }
}
