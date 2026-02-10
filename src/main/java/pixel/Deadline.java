package pixel;

public class Deadline extends Task {
    private String date;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The textual description of the task.
     * @param date        The date or time by which the task must be completed.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task ID, the "[D]" type identifier, the status icon,
     * the description, and the due date.
     *
     * @return A formatted string (e.g., "1.[D][ ] submit report (by: Sunday)").
     */
    @Override
    public String list() {
        if (this.isDone()) {
            return this.getId() + ".[D]" + "[X] " + this.getDescription() + " (by: " + date + ")";
        }
        return this.getId() + ".[D]" + "[ ] " + this.getDescription() + " (by: " + date + ")";
    }
}
