package com.fireinyu.themyth.tasks;

import com.fireinyu.themyth.storage.CsvSerializable;

/**
 * Represents a generic task with a description and a completion status.
 */
public abstract class Task implements CsvSerializable {
    private final String description;
    private boolean completed = false;
    private final String typeCode;

    /**
     * Constructs a Task.
     *
     * @param description The description of the task.
     * @param typeCode The code representing the task type (e.g., "T" for Todo).
     */
    public Task(String description, String typeCode) {
        this.description = description;
        this.typeCode = typeCode;
    }

    /**
     * Gets the description of the task.
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is completed.
     * @return true if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        completed = true;
    }

    /**
     * Marks the task as not completed.
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
     * Returns the string representation of the task, including its type,
     * completion status, and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", typeCode, completed ? "X" : " ", description);
    }
}
