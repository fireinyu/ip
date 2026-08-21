package com.fireinyu.themyth.chatmodes;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.chatmodes.tasks.DeadlineTask;
import com.fireinyu.themyth.chatmodes.tasks.EventTask;
import com.fireinyu.themyth.chatmodes.tasks.Task;
import com.fireinyu.themyth.chatmodes.tasks.TodoTask;
import com.fireinyu.themyth.exceptions.TaskFileAccessException;
import com.fireinyu.themyth.requests.*;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.storage.CsvBackedList;
import com.fireinyu.themyth.storage.TaskList;
import util.MythDateTime;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskMode extends ChatMode {
    private List<Task> taskList;
    private final Path taskFile;

    public TaskMode(Path taskFile) {
        this.taskFile = taskFile;
    }

    public TaskMode() {
        this(Path.of(Defaults.TASKFILE));
    }

    @Override
    protected Response respondToInit(InitRequest request) {
        try {
            this.taskList = new TaskList(this.taskFile);
        } catch (IOException e) {
            e.printStackTrace();
            this.taskList = new ArrayList<>();
            throw new TaskFileAccessException(this.taskFile.toString());
        }
        return new Response("task file loaded successfully");
    }

    @Override
    protected Response respondToClose(CloseRequest request) {
        if (this.taskList instanceof CsvBackedList<Task>) {
            try {
                ((CsvBackedList<Task>) this.taskList).close();
            } catch (IOException e) {
                throw new TaskFileAccessException(this.taskFile.toString());
            }
        }
        return new Response("task file written successfully");

    }

    @Override
    protected Response respondToTodo(TodoRequest request) {
        return this.addTask(new TodoTask(request.getArg(1)));
    }

    @Override
    protected Response respondToDeadline(DeadlineRequest request) {
        return this.addTask(new DeadlineTask(
                request.getArg(1),
                MythDateTime.parse(request.getArg("by"))
        ));
    }

    @Override
    protected Response respondToEvent(EventRequest request) {
        return this.addTask(new EventTask(
                request.getArg(1),
                MythDateTime.parse(request.getArg("from")),
                MythDateTime.parse(request.getArg("to"))
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

    @Override
    protected Response respondToDelete(DeleteRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1)) - 1;
        Task task = this.taskList.remove(itemIndex);
        return new Response(
                "Noted. I've removed this task:\n\t"
                + task.toString()
                + String.format("\nNow you have %d tasks in the list.", this.taskList.size())
        );
    }
}
