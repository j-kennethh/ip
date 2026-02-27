package pixel;

/**
 * Represents a task that needs to be completed by a specific date or time.
 * A Deadline task inherits from the base {@link Task} class and attaches
 * a due date to the task description.
 */
public class Deadline extends Task {
    private final String date;

    /**
     * Constructs a new Deadline task with the specified description, status, and due date.
     *
     * @param description The textual description of the task.
     * @param isDone      The completion status of the task.
     * @param date        The date or time by which the task must be completed.
     */
    public Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the "[D]" type identifier, the status icon,
     * the description, and the due date.
     *
     * @return A formatted string (e.g., "[D][ ] submit report (by: Sunday)").
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[D][X] " + this.getDescription() + " (by: " + date + ")";
        }
        return "[D][ ] " + this.getDescription() + " (by: " + date + ")";
    }
}
