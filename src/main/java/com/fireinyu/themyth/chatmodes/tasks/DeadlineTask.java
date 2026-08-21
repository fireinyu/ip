package com.fireinyu.themyth.chatmodes.tasks;

import util.MythDateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class DeadlineTask extends Task {
    private final MythDateTime date;
    public DeadlineTask(String description, MythDateTime date) {
        super(String.format("%s (by: %s)",description, date), "D");
        this.date = date;
    }

    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription(),
                this.date.dump()
        );
    }

    public boolean isDueBy(MythDateTime dateTime) {
        return this.date.isBefore(dateTime);
    }
}
