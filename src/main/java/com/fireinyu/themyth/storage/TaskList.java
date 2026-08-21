package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.chatmodes.tasks.DeadlineTask;
import com.fireinyu.themyth.chatmodes.tasks.EventTask;
import com.fireinyu.themyth.chatmodes.tasks.Task;
import com.fireinyu.themyth.chatmodes.tasks.TodoTask;
import com.fireinyu.themyth.exceptions.ArgumentFormatException;
import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;
import util.MythDateTime;

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
                    throw new CorruptedTaskFileException(super.file.toString());
                }
            }
            if (Boolean.parseBoolean(item.get(1))) {
                task.mark();
            } else {
                task.unmark();
            }
            return task;
        } catch (IndexOutOfBoundsException | ArgumentFormatException e) {
            throw new CorruptedTaskFileException(super.file.toString());
        }
    }
}
