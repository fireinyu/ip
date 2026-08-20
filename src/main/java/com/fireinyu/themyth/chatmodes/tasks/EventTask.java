package com.fireinyu.themyth.chatmodes.tasks;

import java.util.List;

public class EventTask extends Task {
    private String from;
    private String to;
    public EventTask(String description, String from, String to) {
        super(String.format("%s (from: %s to: %s)",description, from, to), "E");
    }

    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription(),
                this.from,
                this.to
        );
    }
}
