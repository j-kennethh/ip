package pixel;

/**
 * Represents a task without a specific deadline or duration.
 * A ToDo task inherits from the base {@link Task} class and represents a simple
 * action item to be completed.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the specified description and status.
     *
     * @param description The textual description of the ToDo task.
     * @param isDone      The completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
}
