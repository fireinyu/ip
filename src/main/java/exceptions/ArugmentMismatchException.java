package exceptions;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class ArugmentMismatchException extends TweakingException {
    public ArugmentMismatchException(int expectedPosCount, int givenPosCount) {
        super(String.format("%d positional arguments expected but %d given", expectedPosCount, givenPosCount));
    }

    public ArugmentMismatchException(Collection<String> expectedKwargs, Collection<String> givenKwargs) {
        super(String.format("expected keyword arguments: {%s}, given keyword arguments: {%s}",
                String.join(", ", expectedKwargs),
                String.join(", ", givenKwargs)
        ));
    }
}
