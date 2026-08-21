package com.fireinyu.themyth.requests;

import com.fireinyu.themyth.exceptions.ArugmentMismatchException;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RequestTest {

    @Test
    public void constructor_wrongPosArgs() {
        try {
            Request req = new Request(
                    List.of("a"),
                    Map.of(),
                    5,
                    Set.of()
            );
        } catch (ArugmentMismatchException _) {
        } catch (Exception e) {
            fail();
        }
        try {
            Request req = new Request(
                    List.of("a", "b", "c"),
                    Map.of(),
                    2,
                    Set.of()
            );
        } catch (ArugmentMismatchException _) {
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void constructor_wrongKwArgs() {
        try {
            Request req = new Request(
                    List.of("a"),
                    Map.of(),
                    1,
                    Set.of("kw2")
            );
        } catch (ArugmentMismatchException _) {
        } catch (Exception e) {
            fail();
        }
        try {
            Request req = new Request(
                    List.of("a"),
                    Map.of("kw1", "val1"),
                    1,
                    Set.of("kw2")
            );
        } catch (ArugmentMismatchException _) {
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void constructor_correctArgs() {
        Request req = new Request(
                List.of("a", "b", "c"),
                Map.of("kw1", "val1", "kw2", "val2"),
                3,
                Set.of("kw1", "kw2")
        );
    }

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
