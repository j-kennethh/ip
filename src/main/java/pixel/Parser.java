package pixel;

import java.util.ArrayList;

/**
 * Parses user input to extract command arguments and validate input formats.
 * This class handles the extraction of task descriptions, dates, and IDs
 * from raw command line strings.
 */
public class Parser {
    private final String line;

    /**
     * Constructs a new Parser instance with the raw user input.
     *
     * @param line The full, raw string inputted by the user.
     */
    public Parser(String line) {
        this.line = line;
    }

    /**
     * Parses the raw input line to extract the description for a ToDo task.
     *
     * @return The description of the ToDo task.
     * @throws PixelException If the description argument is missing.
     */
    public String parseToDo() throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: todo [description]");
        }

        return line.substring(5);
    }

    /**
     * Parses the raw input line to extract the description and date for a Deadline task.
     * Expects the format: "deadline [description] /by [date]".
     *
     * @return A String array with the description at index 0 and the date at index 1.
     * @throws PixelException If the format is incorrect or arguments are missing.
     */
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

    /**
     * Parses the raw input line to extract the description, start time, and end time for an Event task.
     * Expects the format: "event [description] /from [start] /to [end]".
     *
     * @return A String array with the description at index 0, the start time at index 1, and the end time at index 2.
     * @throws PixelException If the format is incorrect or tags are missing.
     */
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

    /**
     * Parses the raw input line to extract and validate a task ID for commands
     * like mark, unmark, or delete.
     *
     * @param tasks The current list of tasks, used to validate if the ID is within bounds.
     * @return The 1-based integer task ID.
     * @throws PixelException If the ID is missing, not a number, or out of bounds.
     */
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

    /**
     * Parses the raw input line to extract the search keyword for the find command.
     *
     * @return The search keyword extracted from the input.
     * @throws PixelException If the keyword argument is missing.
     */
    public String parseFind() throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: find [keyword]");
        }

        return words[1];
    }
}
