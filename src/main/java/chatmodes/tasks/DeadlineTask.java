package chatmodes.tasks;

public class DeadlineTask extends Task {
    public DeadlineTask(String description, String date) {
        super(String.format("%s (by: %s)",description, date), "D");
    }
}
