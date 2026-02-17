package pixel;

public class Task {
    private final int id;
    private final String description;
    private boolean isDone;
    private static int count = 0;

    /**
     * Constructs a new Task with the specified description.
     * <p>
     * This constructor automatically increments the static task count,
     * assigns a unique ID to the new instance, and initializes the
     * task status as incomplete (not done).
     *
     * @param description The textual description of the task.
     */
    public Task(String description, boolean isDone) {
        count++;
        this.id = count;
        this.description = description;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Task.count = count;
    }

    /**
     * Returns a string representation of the task to be displayed in the list.
     * The format includes the task ID, the type identifier "[T]", the status icon
     * ("[X]" if done, "[ ]" if not done), and the task description.
     *
     * @return A formatted string representing the task details.
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return id + ".[T]" + "[X] " + description;
        }
        return id + ".[T]" + "[ ] " + description;
    }
}
