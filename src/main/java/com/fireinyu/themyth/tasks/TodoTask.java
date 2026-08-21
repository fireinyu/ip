package com.fireinyu.themyth.tasks;

import java.util.List;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description, "T");
    }
    @Override
    public List<String> extract() {
        return List.of(
                super.getTypeCode(),
                String.valueOf(super.isCompleted()),
                super.getDescription()
        );
    }
}
