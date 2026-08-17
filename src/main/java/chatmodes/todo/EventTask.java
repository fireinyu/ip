package chatmodes.todo;

public class EventTask extends Task {
    public EventTask(String description, String from, String to) {
        super(String.format("%s (from: %s to: %s)",description, from, to), "E");
    }
}
