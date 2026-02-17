package pixel;

public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs a new Event task with the specified description and duration.
     *
     * @param description The textual description of the event.
     * @param start       The start time or date of the event.
     * @param end         The end time or date of the event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     * Includes the task ID, the "[E]" type identifier, the status icon,
     * the description, and the start and end times.
     *
     * @return A formatted string (e.g., "2.[E][ ] team meeting (from: 2pm to: 4pm)").
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return this.getId() + ".[E]" + "[X] " + this.getDescription() + " (from: " + start + " to: " + end + ")";
        }
        return this.getId() + ".[E]" + "[ ] " + this.getDescription() + " (from: " + start + " to: " + end + ")";
    }
}
