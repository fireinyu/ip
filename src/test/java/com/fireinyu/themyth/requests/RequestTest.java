package com.fireinyu.themyth.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fireinyu.themyth.exceptions.ArugmentMismatchException;

/**
 * Unit tests for {@link Request} argument validation and retrieval mechanisms.
 */
public class RequestTest {

    private static class DummyRequest extends Request {
        DummyRequest(List<String> posArgs, Map<String, String> kwargs) {
            super(posArgs, kwargs);
        }

        @Override
        public List<InputFieldParser<?>> getPosArgTypes() {
            return List.of(InputFieldParser.STRING, InputFieldParser.STRING, InputFieldParser.STRING);
        }

        @Override
        public Map<String, InputFieldParser<?>> getKwargTypes() {
            return Map.of("kw1", InputFieldParser.STRING, "kw2", InputFieldParser.STRING);
        }
    }

    /**
     * Tests that supplying an invalid number of positional arguments throws an {@link ArugmentMismatchException}.
     */
    @Test
    public void constructor_wrongPosArgs() {
        assertThrows(ArugmentMismatchException.class, () -> new DummyRequest(
                List.of("a"),
                Map.of("kw1", "val1", "kw2", "val2")
        ));

        assertThrows(ArugmentMismatchException.class, () -> new DummyRequest(
                List.of("a", "b", "c", "d"),
                Map.of("kw1", "val1", "kw2", "val2")
        ));
    }

    /**
     * Tests that supplying invalid keyword arguments throws an {@link ArugmentMismatchException}.
     */
    @Test
    public void constructor_wrongKwArgs() {
        assertThrows(ArugmentMismatchException.class, () -> new DummyRequest(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1")
        ));

        assertThrows(ArugmentMismatchException.class, () -> new DummyRequest(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "wrongKw", "val2")
        ));
    }

    /**
     * Tests that constructing a Request with valid arguments completes without errors.
     */
    @Test
    public void constructor_correctArgs() {
        new DummyRequest(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2")
        );
    }

    /**
     * Tests that positional arguments can be retrieved correctly by index.
     */
    @Test
    public void getArg_posArg() {
        Request req = new DummyRequest(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2")
        );
        assertEquals("b", req.getArg(1, String.class));
        assertEquals("c", req.getArg(2, String.class));
    }

    /**
     * Tests that keyword arguments can be retrieved correctly by keyword name.
     */
    @Test
    public void getArg_kwArg() {
        Request req = new DummyRequest(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2")
        );
        assertEquals("val1", req.getArg("kw1", String.class));
    }
}
