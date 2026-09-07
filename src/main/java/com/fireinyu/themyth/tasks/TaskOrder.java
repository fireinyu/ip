package com.fireinyu.themyth.tasks;

import java.util.Comparator;

public enum TaskOrder {
    CREATED(Comparator.comparing(Task::getCreated).reversed()),
    MODIFIED(Comparator.comparing(Task::getLastModified).reversed());

    private final Comparator<Task> comparator;

    TaskOrder(Comparator<Task> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Task> getComparator() {
        return comparator;
    }
}
