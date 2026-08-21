package com.fireinyu.themyth.tasks;

import com.fireinyu.themyth.storage.CsvSerializable;

public abstract class Task implements CsvSerializable {
    private final String description;
    private boolean completed = false;
    private String typeCode;

    public Task(String description, String typeCode) {
        this.description = description;
        this.typeCode = typeCode;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    protected String getTypeCode() {
        return typeCode;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.typeCode, this.completed ? "X" : " ", this.description);
    }
}
