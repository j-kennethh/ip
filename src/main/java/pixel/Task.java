package pixel;

/**
 * Represents a generic task in the Pixel application.
 * This class encapsulates the common properties of all tasks, such as a description
 * and a completion status. It serves as the base class for more specific task types
 * like ToDo, Deadline, and Event.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The textual description of the task.
     * @param isDone      The initial completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task to be displayed in the list.
     * The format includes the type identifier "[T]", the status icon
     * ("[X]" if done, "[ ]" if not done), and the task description.
     *
     * @return A formatted string representing the task details.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + description;
        }
        return "[T][ ] " + description;
    }
}
