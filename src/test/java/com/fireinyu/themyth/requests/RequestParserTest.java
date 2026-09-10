package com.fireinyu.themyth.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link RequestParser}.
 */
public class RequestParserTest {

    private final RequestParser parser = new RequestParser();

    /**
     * Tests parsing of exit command.
     */
    @Test
    public void parse_exitCommand_returnsExitRequest() {
        Request req = parser.parse("bye");
        assertInstanceOf(ExitRequest.class, req);
    }

    /**
     * Tests parsing of list command without and with keyword arguments.
     */
    @Test
    public void parse_listCommand_returnsListRequest() {
        Request req1 = parser.parse("list");
        assertInstanceOf(ListRequest.class, req1);

        Request req2 = parser.parse("list /sort created");
        assertInstanceOf(ListRequest.class, req2);
    }

    /**
     * Tests parsing of todo command.
     */
    @Test
    public void parse_todoCommand_returnsTodoRequest() {
        Request req = parser.parse("todo read");
        assertInstanceOf(TodoRequest.class, req);
        assertEquals("read", req.getArg(1, String.class));
    }

    /**
     * Tests parsing of deadline command with /by keyword.
     */
    @Test
    public void parse_deadlineCommand_returnsDeadlineRequest() {
        Request req = parser.parse("deadline homework /by 2025-10-15-18-00-00");
        assertInstanceOf(DeadlineRequest.class, req);
        assertEquals("homework", req.getArg(1, String.class));
    }

    /**
     * Tests parsing of event command with /from and /to keywords.
     */
    @Test
    public void parse_eventCommand_returnsEventRequest() {
        Request req = parser.parse("event camp /from 2025-06-01-09-00-00 /to 2025-06-01-17-00-00");
        assertInstanceOf(EventRequest.class, req);
        assertEquals("camp", req.getArg(1, String.class));
    }

    /**
     * Tests parsing of mark, unmark, and delete commands.
     */
    @Test
    public void parse_indexBasedCommands_returnsCorrectRequests() {
        Request markReq = parser.parse("mark 2");
        assertInstanceOf(MarkRequest.class, markReq);
        assertEquals(2, markReq.getArg(1, Integer.class));

        Request unmarkReq = parser.parse("unmark 3");
        assertInstanceOf(UnmarkRequest.class, unmarkReq);
        assertEquals(3, unmarkReq.getArg(1, Integer.class));

        Request deleteReq = parser.parse("delete 1");
        assertInstanceOf(DeleteRequest.class, deleteReq);
        assertEquals(1, deleteReq.getArg(1, Integer.class));
    }

    /**
     * Tests parsing of find command.
     */
    @Test
    public void parse_findCommand_returnsFindRequest() {
        Request req = parser.parse("find book");
        assertInstanceOf(FindRequest.class, req);
        assertEquals("book", req.getArg(1, String.class));
    }

    /**
     * Tests parsing of trivia quiz commands.
     */
    @Test
    public void parse_quizCommands_returnsQuizAndAnswerRequests() {
        Request quizReq = parser.parse("quiz");
        assertInstanceOf(QuizRequest.class, quizReq);

        Request answerReq = parser.parse("answer 1");
        assertInstanceOf(AnswerRequest.class, answerReq);
        assertEquals(1, answerReq.getArg(1, Integer.class));
    }

    /**
     * Tests parsing of unrecognized command.
     */
    @Test
    public void parse_unknownCommand_returnsGenericRequest() {
        Request req = parser.parse("unknown 123");
        assertEquals("unknown", req.getArg(0, String.class));
    }
}
