package com.fireinyu.themyth.tasks;

import java.util.List;

import com.fireinyu.themyth.util.MythDateTime;

/**
 * Task due by a certain datetime
 * @see MythDateTime
 */
public class DeadlineTask extends Task {
    private final MythDateTime date;

    /**
     * Initialises a DeadLineTask due by a certain datetime
     * @param description description of the task
     * @param date due date of the task
     * @see MythDateTime
     * @see String
     */
    public DeadlineTask(String description, MythDateTime date) {
        super(description, "D");
        this.date = date;
    }

    /**
     * Serializes this DeadlineTask into a List of String attributes<br><br>
     * @return List of String attributes representing the serialized DeadlineTask object
     * @see List
     * @see String
     */
    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription(),
                this.date.dump()
        );
    }

    /**
     * Returns whether this DeadlineTask is due by a given datetime<br><br>
     * @param dateTime the given datetime
     * @return whether this DeadlineTask is due by dateTime
     * @see MythDateTime
     */
    public boolean isDueBy(MythDateTime dateTime) {
        return this.date.isBefore(dateTime);
    }

    /**
     * Obtain a detailed String representation of this Task.<br><br>
     * Includes its type, whether it is completed, its description and due date
     * @return detailed String representation of this Task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), date);
    }
}
