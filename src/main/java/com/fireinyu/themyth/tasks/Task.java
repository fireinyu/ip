package com.fireinyu.themyth.tasks;

import com.fireinyu.themyth.storage.CsvSerializable;

/**
 * Task created by the user. Tasks can be marked or unmarked as completed.
 */
public abstract class Task implements CsvSerializable {
    private final String description;
    private boolean completed = false;
    private final String typeCode;

    /**
     * Initialises a Task
     * @param description description of the task
     * @param typeCode type of task
     * @see String
     */
    public Task(String description, String typeCode) {
        this.description = description;
        this.typeCode = typeCode;
    }

    /**
     * Get the description of this Task<br><br>
     * @return  the description of this Task
     * @see String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this Task is completed<br><br>
     * @return whether this Task is completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks this Task as completed
     */
    public void mark() {
        completed = true;
    }

    /**
     * Marks this Task as incomplete
     */
    public void unmark() {
        completed = false;
    }

    /**
     * Gets the type code of the task.
     *
     * @return The type code string.
     */
    protected String getTypeCode() {
        return typeCode;
    }

    /**
     * Obtain a detailed String representation of this Task.<br><br>
     * Includes its type, whether it is completed and its description
     * @return detailed String representation of this Task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", typeCode, completed ? "X" : " ", description);
    }
}
