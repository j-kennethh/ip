package pixel;

public class Task {
    private final int id;
    private final String description;
    private boolean isDone;
    private static int count = 0;

    public Task(String description) {
        count++;
        this.id = count;
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        if (this.isDone()) {
            return id + ".[T]" + "[X] " + description;
        }
        return id + ".[T]" + "[ ] " + description;
    }
}
