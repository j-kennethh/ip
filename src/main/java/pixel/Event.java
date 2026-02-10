package pixel;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
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

    @Override
    public String list() {
        if (this.isDone()) {
            return this.getId() + ".[E]" + "[X] " + this.getDescription() + "(from: " + start + " to: " + end + ")";
        }
        return this.getId() + ".[E]" + "[ ] " + this.getDescription() + "(from: " + start + " to: " + end + ")";
    }
}
