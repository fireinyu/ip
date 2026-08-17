package chatmodes;

import chatmodes.todo.DeadlineTask;
import chatmodes.todo.EventTask;
import chatmodes.todo.Task;
import chatmodes.todo.TodoTask;
import requests.*;
import responses.Response;

import java.util.ArrayList;
import java.util.List;

public class TodoMode extends ChatMode {
    List<Task> taskList = new ArrayList<>();

    @Override
    protected Response respondToRemaining(Request request) {
        return new Response(String.format("Erm, what is \"%s\"?", request.getArg(0)));
    }

    @Override
    protected Response respondToTodo(TodoRequest request) {
        return this.addTask(new TodoTask(request.getArg(1)));
    }

    @Override
    protected Response respondToDeadline(DeadlineRequest request) {
        return this.addTask(new DeadlineTask(
                request.getArg(1),
                request.getArg("by")
        ));
    }

    @Override
    protected Response respondToEvent(EventRequest request) {
        return this.addTask(new EventTask(
                request.getArg(1),
                request.getArg("from"),
                request.getArg("to")
        ));
    }

    @Override
    protected Response respondToList(ListRequest request) {
        StringBuilder body = new StringBuilder("Here are the tasks in your list:");
        int itemNumber = 1;
        for (Task item : this.taskList) {
            body.append('\n');
            body.append(itemNumber++);
            body.append(". ");
            body.append(item.toString());
        }
        return new Response(body.toString());
    }

    @Override
    protected Response respondToMark(MarkRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1)) - 1;
        taskList.get(itemIndex).mark();
        return new Response("Nice! I've marked this task as done:\n\t" + taskList.get(itemIndex));
    }

    @Override
    protected Response respondToUnmark(UnmarkRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1)) - 1;
        taskList.get(itemIndex).unmark();
        return new Response("OK, I've marked this task as not done yet:\n\t" + taskList.get(itemIndex));
    }

    private Response addTask(Task task) {
        this.taskList.add(task);
        return new Response(
                "Got it. I've added this task:\n\t"
                + task.toString()
                + String.format("\nNow you have %d tasks in the list.", this.taskList.size())
        );
    }
}
