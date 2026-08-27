package com.fireinyu.themyth.chatmodes;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.tasks.DeadlineTask;
import com.fireinyu.themyth.tasks.EventTask;
import com.fireinyu.themyth.tasks.Task;
import com.fireinyu.themyth.tasks.TodoTask;
import com.fireinyu.themyth.requests.*;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.storage.TaskList;
import com.fireinyu.themyth.util.MythDateTime;

import java.nio.file.Path;
import java.util.List;

/**
 * Request-Response model that acts a task list/ manager<br><br>
 * The user can create, view and delete different types of tasks.<br>
 * Tasks can be marked or unmarked as completed.<br>
 * Created are saved on disk and synced automatically.
 * @see Task
 * @see Request
 * @see Response
 */
public class TaskMode extends ChatMode {
    private final TaskList taskList = new TaskList();
    private final Path taskFile;

    /**
     * Initialises a TaskMode which syncs created Tasks with a given task file
     * @param taskFile Path to task file that syncs with this TaskMode
     * @see Path
     */
    public TaskMode(Path taskFile) {
        this.taskFile = taskFile;
    }

    /**
     * Initialises a TaskMode which syncs created Tasks with a task file at the default path
     * @see Defaults
     * @see Path
     */
    public TaskMode() {
        this(Path.of(Defaults.TASKFILE));
    }

    @Override
    protected Response respondToInit(InitRequest request) {
        this.taskList.open(this.taskFile);
        return new Response("task file loaded successfully");
    }

    @Override
    protected Response respondToClose(CloseRequest request) {
        this.taskList.close();
        return new Response("exited successfully");

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
        return this.listTasks("Here are the tasks in your list:", this.taskList);
    }

    @Override
    protected Response respondToAt(AtRequest request) {
        MythDateTime at = MythDateTime.parse(request.getArg(1));
        return this.listTasks(
                String.format("Here are the events happening on %s", at),
                this.taskList.stream()
                        .filter(task -> task instanceof EventTask)
                        .map(task -> (EventTask)task)
                        .filter(task -> task.contains(at))
                        .map(task -> (Task)task)
                        .toList()
        );
    }

    @Override
    protected Response respondToDue(DueRequest request) {
        MythDateTime due = MythDateTime.parse(request.getArg(1));
        return this.listTasks(
                String.format("Here are the deadlines due by %s", due),
                this.taskList.stream()
                        .filter(task -> task instanceof DeadlineTask)
                        .map(task -> (DeadlineTask)task)
                        .filter(task -> task.isDueBy(due))
                        .map(task -> (Task)task)
                        .toList()
        );
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

    private Response listTasks(String header, List<Task> tasks) {
        StringBuilder body = new StringBuilder(header);
        int itemNumber = 1;
        for (Task item : tasks) {
            body.append('\n');
            body.append(itemNumber++);
            body.append(". ");
            body.append(item.toString());
        }
        return new Response(body.toString());
    }
}
