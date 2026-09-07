package com.fireinyu.themyth.tasks;

import java.util.List;

/**
 * Todo Task created by the user. Tasks can be marked or unmarked as completed.
 */
public class TodoTask extends Task {

    /**
     * Initialises a TodoTask
     * @param description description of the task
     * @see String
     */
    public TodoTask(String description) {
        super(description, "T");
    }

}
