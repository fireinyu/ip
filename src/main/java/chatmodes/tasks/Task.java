package chatmodes.tasks;

public abstract class Task {
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

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.typeCode, this.completed ? "X" : " ", this.description);
    }
}
