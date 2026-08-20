package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.chatmodes.tasks.DeadlineTask;
import com.fireinyu.themyth.chatmodes.tasks.EventTask;
import com.fireinyu.themyth.chatmodes.tasks.Task;
import com.fireinyu.themyth.chatmodes.tasks.TodoTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class TaskList extends CsvBackedList<Task>{
    public TaskList(Path file) throws IOException {
        super(file);
    }
    @Override
    public Task parse(List<String> item) {
        Task task = null;
        switch (item.get(0)) {
            case "T": {
                task = new TodoTask(item.get(2));
                break;
            }
            case "D": {
                task = new DeadlineTask(item.get(2), item.get(3));
                break;
            }
            case "E": {
                task = new EventTask(item.get(2), item.get(3), item.get(4));
                break;
            }
            default: {
                throw new RuntimeException();
            }
        }
        if (Boolean.parseBoolean(item.get(1))) {
            task.mark();
        } else {
            task.unmark();
        }
        return task;
    }
}
