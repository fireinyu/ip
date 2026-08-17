package chatmodes;

import responses.Response;
import requests.ListRequest;
import requests.Request;

import java.util.ArrayList;
import java.util.List;

public class TodoMode extends ChatMode {
    List<String> todoList = new ArrayList<>();

    @Override
    protected Response respondToRemaining(Request request) {
        todoList.add(request.getMessage());
        return new Response("added: " + request.getMessage());
    }

    @Override
    protected Response respondToList(ListRequest request) {
        StringBuilder body = new StringBuilder();
        int itemNumber = 1;
        for (String item : this.todoList) {
            body.append(itemNumber++);
            body.append(". ");
            body.append(item);
            body.append('\n');
        }
        return new Response(body.toString().strip());
    }
}
