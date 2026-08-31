package com.fireinyu.themyth.storage;

import java.util.List;

import com.fireinyu.themyth.exceptions.ArgumentFormatException;
import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;
import com.fireinyu.themyth.tasks.DeadlineTask;
import com.fireinyu.themyth.tasks.EventTask;
import com.fireinyu.themyth.tasks.Task;
import com.fireinyu.themyth.tasks.TodoTask;
import com.fireinyu.themyth.util.MythDateTime;

/**
 * List of Tasks that can be synced with a CSV file on disk.
 * @see Task
 * @see LinesDisk
 */
public class TaskList extends CsvBackedList<Task> {

    /**
     * Initialises a TaskList.<br><br>
     * It is initially not backed by any LinesDisk so it acts as an ArrayList of Tasks.<br>
     * Call open() to sync to a LinesDisk.
     * @see LinesDisk
     * @see Task
     */
    public TaskList() {
    }
    @Override
    protected Task parse(List<String> item) {
        try {
            Task task = null;
            switch (item.get(0)) {
                case "T": {
                    task = new TodoTask(item.get(2));
                    break;
                }
                case "D": {
                    task = new DeadlineTask(item.get(2), MythDateTime.parse(item.get(3)));
                    break;
                }
                case "E": {
                    task = new EventTask(item.get(2), MythDateTime.parse(item.get(3)), MythDateTime.parse(item.get(4)));
                    break;
                }
                default: {
                    throw new CorruptedTaskFileException(super.getPath().toString());
                }
            }
            if (Boolean.parseBoolean(item.get(1))) {
                task.mark();
            } else {
                task.unmark();
            }
            return task;
        } catch (IndexOutOfBoundsException | ArgumentFormatException e) {
            throw new CorruptedTaskFileException(super.getPath().toString());
        }
    }
}
