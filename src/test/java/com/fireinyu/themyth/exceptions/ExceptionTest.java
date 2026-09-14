package com.fireinyu.themyth.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.requests.Request;

/**
 * Unit tests for custom exception classes in {@link com.fireinyu.themyth.exceptions}.
 */
public class ExceptionTest {

    private static class ConcreteTweakingException extends TweakingException {
        ConcreteTweakingException(String message) {
            super(message);
        }
    }

    /**
     * Tests {@link TweakingException} formatting.
     */
    @Test
    public void tweakingException_formatsMessage() {
        TweakingException ex = new ConcreteTweakingException("something broke");
        assertEquals("I'm tweaking because something broke", ex.getMessage());
    }

    /**
     * Tests {@link FatalException} formatting.
     */
    @Test
    public void fatalException_formatsMessage() {
        FatalException ex = new FatalException("out of memory");
        assertEquals("R.I.P. com.fireinyu.themyth.TheMyth, cause of death: out of memory", ex.getMessage());
    }

    /**
     * Tests {@link FileAccessException} formatting.
     */
    @Test
    public void fileAccessException_formatsMessage() {
        FileAccessException ex = new FileAccessException("data/test.csv");
        assertEquals("I'm tweaking because unable to access file \"data/test.csv\", defaulting to memory-only mode",
                ex.getMessage());
    }

    /**
     * Tests {@link CorruptedTaskFileException} formatting.
     */
    @Test
    public void corruptedTaskFileException_formatsMessage() {
        CorruptedTaskFileException ex = new CorruptedTaskFileException("tasks.csv");
        assertEquals("I'm tweaking because file \"tasks.csv\" contains corrupted data, defaulting to memory-only mode",
                ex.getMessage());
    }

    /**
     * Tests {@link ArgumentFormatException} formatting.
     */
    @Test
    public void argumentFormatException_formatsMessage() {
        ArgumentFormatException ex = new ArgumentFormatException("number", "abc", "integer");
        assertEquals("I'm tweaking because number argument \"abc\" is ill-formatted, pls use integer format!",
                ex.getMessage());
    }

    /**
     * Tests {@link DateFormatException} formatting.
     */
    @Test
    public void dateFormatException_formatsMessage() {
        DateFormatException ex = new DateFormatException("2025-99-99");
        assertEquals("I'm tweaking because date argument \"2025-99-99\" is ill-formatted, pls use "
                + Defaults.DATE_INPUTFORMAT + " format!", ex.getMessage());
    }

    /**
     * Tests {@link ArugmentMismatchException} constructor with positional argument counts.
     */
    @Test
    public void arugmentMismatchException_positionalCounts_formatsMessage() {
        ArugmentMismatchException ex = new ArugmentMismatchException(2, 1);
        assertEquals("I'm tweaking because 2 positional arguments expected but 1 given", ex.getMessage());
    }

    /**
     * Tests {@link ArugmentMismatchException} constructor with keyword argument sets.
     */
    @Test
    public void arugmentMismatchException_keywordCollections_formatsMessage() {
        ArugmentMismatchException ex = new ArugmentMismatchException(Set.of("by"), Set.of("from", "to"));
        assertTrue(ex.getMessage().contains("expected keyword arguments: {by}"));
        assertTrue(ex.getMessage().contains("given keyword arguments: {"));
    }

    /**
     * Tests {@link InvalidCommandException} formatting with a request.
     */
    @Test
    public void invalidCommandException_formatsMessage() {
        Request req = Request.of("dance");
        InvalidCommandException ex = new InvalidCommandException(req);
        assertEquals("I'm tweaking because I don't know how to \"dance\"", ex.getMessage());
    }

    /**
     * Tests {@link WrongTypeException} formatting.
     */
    @Test
    public void wrongTypeException_formatsMessage() {
        WrongTypeException ex = new WrongTypeException(List.class);
        assertEquals("I'm tweaking because expected type: " + List.class.toString(), ex.getMessage());
    }
}
