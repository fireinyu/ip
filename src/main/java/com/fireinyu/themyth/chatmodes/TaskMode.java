package com.fireinyu.themyth.chatmodes;

import java.nio.file.Path;
import java.util.List;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.requests.AtRequest;
import com.fireinyu.themyth.requests.DeadlineRequest;
import com.fireinyu.themyth.requests.DeleteRequest;
import com.fireinyu.themyth.requests.DueRequest;
import com.fireinyu.themyth.requests.EventRequest;
import com.fireinyu.themyth.requests.FindRequest;
import com.fireinyu.themyth.requests.ListRequest;
import com.fireinyu.themyth.requests.MarkRequest;
import com.fireinyu.themyth.requests.TodoRequest;
import com.fireinyu.themyth.requests.UnmarkRequest;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.storage.TaskList;
import com.fireinyu.themyth.tasks.DeadlineTask;
import com.fireinyu.themyth.tasks.EventTask;
import com.fireinyu.themyth.tasks.Task;
import com.fireinyu.themyth.tasks.TodoTask;
import com.fireinyu.themyth.util.MythDateTime;

/**
 * Request-Response model that acts a task list/ manager<br><br>
 * The user can create, view and delete different types of tasks.<br>
 * Tasks can be marked or unmarked as completed.<br>
 * Created are saved on disk and synced automatically.
 * @see Task
 * @see com.fireinyu.themyth.requests.Request
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToInit(InitRequest request) {
        taskList.open(taskFile);
        return new Response("task file loaded successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToClose(CloseRequest request) {
        taskList.close();
        return new Response("exited successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToFind(FindRequest request) {
        return listTasks(
                "Here are the matching tasks in your list:",
                taskList.stream()
                        .filter(task -> task.toString().contains(request.getArg(1)))
                        .toList()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToTodo(TodoRequest request) {
        return addTask(new TodoTask(request.getArg(1)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToDeadline(DeadlineRequest request) {
        return addTask(new DeadlineTask(
                request.getArg(1),
                MythDateTime.parse(request.getArg("by"))
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToEvent(EventRequest request) {
        return addTask(new EventTask(
                request.getArg(1),
                MythDateTime.parse(request.getArg("from")),
                MythDateTime.parse(request.getArg("to"))
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToList(ListRequest request) {
        return listTasks("Here are the tasks in your list:", taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToAt(AtRequest request) {
        MythDateTime at = MythDateTime.parse(request.getArg(1));
        return listTasks(
                String.format("Here are the events happening on %s", at),
                taskList.stream().filter(task -> task instanceof EventTask eventTask && eventTask.contains(at))
                        .toList()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToDue(DueRequest request) {
        MythDateTime due = MythDateTime.parse(request.getArg(1));
        return listTasks(
                String.format("Here are the deadlines due by %s", due),
                taskList.stream().filter(task -> task instanceof DeadlineTask deadlineTask && deadlineTask.isDueBy(due))
                        .toList()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToMark(MarkRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1)) - 1;
        Task task = taskList.get(itemIndex);
        task.mark();
        String message = "Nice! I've marked this task as done:\n\t" + task;
        return new Response(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToUnmark(UnmarkRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1)) - 1;
        Task task = taskList.get(itemIndex);
        task.unmark();
        String message = "OK, I've marked this task as not done yet:\n\t" + task;
        return new Response(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToDelete(DeleteRequest request) {
        int itemIndex = Integer.parseInt(request.getArg(1)) - 1;
        Task task = taskList.remove(itemIndex);
        String message = String.format(
                "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                task,
                taskList.size()
        );
        return new Response(message);
    }

    /**
     * Adds a task to the task list and returns a response.
     *
     * @param task The task to add.
     * @return The response to the user.
     */
    private Response addTask(Task task) {
        taskList.add(task);
        String message = String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task,
                taskList.size()
        );
        return new Response(message);
    }

    /**
     * Formats a list of tasks into a response for the user.
     *
     * @param header The header message for the list.
     * @param tasks The list of tasks to format.
     * @return The response containing the formatted list.
     */
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
