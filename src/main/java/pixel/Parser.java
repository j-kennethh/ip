package pixel;

import java.util.ArrayList;

public class Parser {
    private final String line;

    public Parser(String line) {
        this.line = line;
    }

    public String parseToDo() throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: todo [description]");
        }

        return line.substring(5);
    }

    public String[] parseDeadline() throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 4 || !line.contains("/by")) {
            throw new PixelException("Usage: deadline [description] /by [date]");
        }

        String[] sections = line.substring(9).split(" /by ");
        if (sections.length < 2) {
            throw new PixelException("Usage: deadline [description] /by [date]");
        }

        return sections;
    }

    public String[] parseEvent() throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 6 || !line.contains("/from") || !line.contains("/to")) {
            throw new PixelException("Usage: event [description] /from [start] /to [end]");
        }

        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");

        String[] sections = new String[3];
        sections[0] = line.substring(6, fromIndex - 1);
        sections[1] = line.substring(fromIndex + 6, toIndex - 1);
        sections[2] = line.substring(toIndex + 4);

        return sections;
    }

    public int parseTaskId(ArrayList<Task> tasks) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Missing task number");
        }

        int id = Integer.parseInt(words[1]);

        if (id < 1 || id > tasks.size()) {
            throw new PixelException("Invalid task number");
        }

        return id;
    }
}
