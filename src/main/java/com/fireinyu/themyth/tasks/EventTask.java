package com.fireinyu.themyth.tasks;

import com.fireinyu.themyth.util.MythDateTime;

import java.util.List;

/**
 * Represents a task that occurs over a period of time (an event).
 * It has a start time ('from') and an end time ('to').
 */
public class EventTask extends Task {
    private MythDateTime from;
    private MythDateTime to;

    /**
     * Constructs an EventTask.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public EventTask(String description, MythDateTime from, MythDateTime to) {
        super(String.format("%s (from: %s to: %s)",description, from, to), "E");
        this.from = from;
        this.to = to;
    }

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
