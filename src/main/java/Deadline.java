public class Deadline extends Task {
    private String date;

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

    @Override
    public String list() {
        if (this.isDone()) {
            return this.getId() + ".[D]" + "[X] " + this.getDescription() + " (by: " + this.getDate() + ")";
        }
        return this.getId() + ".[D]" + "[ ] " + this.getDescription() + " (by: " + this.getDate() + ")";
    }
}
