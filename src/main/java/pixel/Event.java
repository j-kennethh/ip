package pixel;

/**
 * Represents a task that occurs over a specific duration.
 * An Event task inherits from the base {@link Task} class and includes
 * defined start and end times or dates.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Constructs a new Event task with the specified description, status, and duration.
     *
     * @param description The textual description of the event.
     * @param isDone      The completion status of the event.
     * @param start       The start time or date of the event.
     * @param end         The end time or date of the event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     * Includes the "[E]" type identifier, the status icon,
     * the description, and the start and end times.
     *
     * @return A formatted string (e.g., "[E][ ] team meeting (from: 2pm to: 4pm)").
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[E][X] " + this.getDescription() + " (from: " + start + " to: " + end + ")";
        }
        return "[E][ ] " + this.getDescription() + " (from: " + start + " to: " + end + ")";
    }
}
