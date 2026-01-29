public class Task {
    private final int id;
    private final String name;
    private boolean isDone;
    private static int count = 0;

    public Task(String name) {
        count++;
        this.id = count;
        this.name = name;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
