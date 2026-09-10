package com.fireinyu.themyth.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.fireinyu.themyth.exceptions.ArugmentMismatchException;


/**
 * Unit tests for {@link Request} argument validation and retrieval mechanisms.
 */
public class RequestTest {

    /**
     * Tests that supplying an invalid number of positional arguments throws an {@link ArugmentMismatchException}.
     */
    @Test
    public void constructor_wrongPosArgs() {
        assertThrows(ArugmentMismatchException.class, () -> new Request(
                List.of("a"),
                Map.of(),
                5,
                Set.of()
        ));

        assertThrows(ArugmentMismatchException.class, () -> new Request(
                    List.of("a", "b", "c"),
                    Map.of(),
                    2,
                    Set.of()
        ));
    }

    /**
     * Tests that supplying invalid keyword arguments throws an {@link ArugmentMismatchException}.
     */
    @Test
    public void constructor_wrongKwArgs() {
        assertThrows(ArugmentMismatchException.class, () -> new Request(
                    List.of("a"),
                    Map.of(),
                    1,
                    Set.of("kw2")
        ));

        assertThrows(ArugmentMismatchException.class, () -> new Request(
                    List.of("a"),
                    Map.of("kw1", "val1"),
                    1,
                    Set.of("kw2")
        ));
    }

    /**
     * Tests that constructing a Request with valid arguments completes without errors.
     */
    @Test
    public void constructor_correctArgs() {
        Request req = new Request(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2"),
                3,
                Set.of("kw1", "kw2")
        );
    }

    /**
     * Tests that positional arguments can be retrieved correctly by index.
     */
    @Test
    public void getArg_posArg() {
        Request req = new Request(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2"),
                3,
                Set.of("kw1", "kw2")
        );
        assertEquals("b", req.getArg(1));
        assertEquals("c", req.getArg(2));
    }

    /**
     * Tests that keyword arguments can be retrieved correctly by keyword name.
     */
    @Test
    public void getArg_kwArg() {
        Request req = new Request(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2"),
                3,
                Set.of("kw1", "kw2")
        );
        assertEquals("val1", req.getArg("kw1"));
    }
}
