package chatmodes.todo;

public class TodoItem {
    private final String description;
    private boolean completed = false;

    public TodoItem(String description) {
        this.description = description;
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
        return String.format("[%s] %s", this.completed ? "X" : " ", this.description);
    }
}
