package com.fireinyu.themyth.chatmodes.tasks;

import util.MythDateTime;

import java.util.List;

public class EventTask extends Task {
    private MythDateTime from;
    private MythDateTime to;
    public EventTask(String description, MythDateTime from, MythDateTime to) {
        this.from = from;
        this.to = to;
        super(String.format("%s (from: %s to: %s)",description, from, to), "E");
    }

    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription(),
                this.from.dump(),
                this.to.dump()
        );
    }

    public boolean contains(MythDateTime dateTime) {
        return dateTime.isBetween(this.from, this.to);
    }

}
