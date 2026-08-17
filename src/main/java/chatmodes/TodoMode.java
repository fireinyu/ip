package chatmodes;

import chatmodes.todo.TodoItem;
import requests.MarkRequest;
import requests.UnmarkRequest;
import responses.Response;
import requests.ListRequest;
import requests.Request;

import java.util.ArrayList;
import java.util.List;

public class TodoMode extends ChatMode {
    List<TodoItem> todoList = new ArrayList<>();

    @Override
    protected Response respondToRemaining(Request request) {
        todoList.add(new TodoItem(request.getArg(0)));
        return new Response("added: " + request.getArg(0));
    }

    @Override
    protected Response respondToList(ListRequest request) {
        StringBuilder body = new StringBuilder("Here are the tasks in your list:");
        int itemNumber = 1;
        for (TodoItem item : this.todoList) {
            body.append('\n');
            body.append(itemNumber++);
            body.append(". ");
            body.append(item.toString());
        }
        return new Response(body.toString());
    }

    @Override
    protected Response respondToMark(MarkRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1));
        todoList.get(itemIndex).mark();
        return new Response("Nice! I've marked this task as done:\n\t" + todoList.get(itemIndex));
    }

    @Override
    protected Response respondToUnmark(UnmarkRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1))-1;
        todoList.get(itemIndex).unmark();
        return new Response("OK, I've marked this task as not done yet:\n\t" + todoList.get(itemIndex));
    }
}
