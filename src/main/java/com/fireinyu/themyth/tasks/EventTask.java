package com.fireinyu.themyth.tasks;

import com.fireinyu.themyth.util.MythDateTime;

import java.util.List;

/**
 * Task occurring between start and end datetimes
 * @see MythDateTime
 */
public class EventTask extends Task {
    private MythDateTime from;
    private MythDateTime to;

    /**
     * Initialises an EventTask occurring between start and end datetimes
     * @param description description of the task
     * @param from start datetime of the task
     * @param to end datetime of the task
     * @see MythDateTime
     * @see String
     */
    public EventTask(String description, MythDateTime from, MythDateTime to) {
        super(String.format("%s (from: %s to: %s)",description, from, to), "E");
        this.from = from;
        this.to = to;
    }

    /**
     * Serializes this EventTask into a List of String attributes<br><br>
     * @return List of String attributes representing the serialized EventTask object
     * @see List
     * @see String
     */
    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                getDescription(),
                from.dump(),
                to.dump()
        );
    }

    /**
     * Checks if this event is ongoing at the given date/time.
     *
     * @param dateTime The date/time to check.
     * @return true if the event contains the given date/time, false otherwise.
     */
    public boolean contains(MythDateTime dateTime) {
        return dateTime.isBetween(from, to);
    }

}
