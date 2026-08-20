package com.fireinyu.themyth.chatmodes.tasks;

import java.util.List;

public class DeadlineTask extends Task {
    private final String date;
    public DeadlineTask(String description, String date) {
        super(String.format("%s (by: %s)",description, date), "D");
        this.date = date;
    }

    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription(),
                this.date
        );
    }
}
