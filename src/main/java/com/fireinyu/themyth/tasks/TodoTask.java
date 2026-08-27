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

    /**
     * Serializes this TodoTask into a List of String attributes<br><br>
     * @return List of String attributes representing the serialized TodoTask object
     * @see List
     * @see String
     */
    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription()
        );
    }
}
