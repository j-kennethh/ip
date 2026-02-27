package pixel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks and handles operations related to modifying the list.
 * This class is responsible for adding, deleting, finding, and updating the status of tasks,
 * as well as synchronizing these changes with the storage file.
 */
public class TaskList {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final String filePath;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList instance and initializes an empty list of tasks.
     *
     * @param filePath The file path used to initialize the Storage instance for saving changes.
     */
    public TaskList(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Parses the command line input to create a new ToDo task.
     * Adds the task to the internal list and appends it to the storage file.
     *
     * @param line The full user input string containing the command and description.
     * @throws PixelException If the description part of the command is empty.
     */
    public void addToDo(String line) throws PixelException {
        Parser parser = new Parser(line);
        String description = parser.parseToDo();

        ToDo newToDo = new ToDo(description, false);
        tasks.add(newToDo);

        try {
            Storage storage = new Storage(filePath);
            storage.appendToFile("T | 0 | " + description);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newToDo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Parses the command line input to create a new Deadline task.
     * Adds the task to the internal list and appends it to the storage file.
     * Expects the format: "deadline DESCRIPTION /by DATE".
     *
     * @param line The full user input string.
     * @throws PixelException If the command format is incorrect or arguments are missing.
     */
    public void addDeadline(String line) throws PixelException {
        Parser parser = new Parser(line);
        String[] sections = parser.parseDeadline();

        String description = sections[0];
        String date = sections[1];

        Deadline newDeadline = new Deadline(description, false, date);
        tasks.add(newDeadline);

        try {
            Storage storage = new Storage(filePath);
            storage.appendToFile("D | 0 | " + description + " | " + date);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Parses the command line input to create a new Event task.
     * Adds the task to the internal list and appends it to the storage file.
     * Expects the format: "event DESCRIPTION /from START /to END".
     *
     * @param line The full user input string.
     * @throws PixelException If the command format is incorrect, or if tags are missing.
     */
    public void addEvent(String line) throws PixelException {
        Parser parser = new Parser(line);
        String[] sections = parser.parseEvent();

        String description = sections[0];
        String start = sections[1];
        String end = sections[2];

        Event newEvent = new Event(description, false, start, end);
        tasks.add(newEvent);

        try {
            Storage storage = new Storage(filePath);
            storage.appendToFile("E | 0 | " + description + " | " + start + " | " + end);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a specific task as done in both the internal list and the storage file.
     *
     * @param line The full user input string (e.g., "mark 1").
     * @throws PixelException If the argument is missing or if the task number is invalid.
     */
    public void markTask(String line) throws PixelException {
        Parser parser = new Parser(line);
        int id = parser.parseTaskId(tasks);

        tasks.get(id - 1).setDone(true);

        try {
            Storage storage = new Storage(filePath);
            storage.updateFile(id - 1, true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(id - 1));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a specific task as not done in both the internal list and the storage file.
     *
     * @param line The full user input string (e.g., "unmark 1").
     * @throws PixelException If the argument is missing or if the task number is invalid.
     */
    public void unmarkTask(String line) throws PixelException {
        Parser parser = new Parser(line);
        int id = parser.parseTaskId(tasks);

        tasks.get(id - 1).setDone(false);

        try {
            Storage storage = new Storage(filePath);
            storage.updateFile(id - 1, false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(tasks.get(id - 1));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Searches for tasks that contain a specific keyword in their description and prints them.
     * The keyword is extracted from the user's input line using the {@link Parser}.
     * If any tasks match the keyword, they are displayed to the console as a
     * sequentially numbered list.
     *
     * @param line The full user input string containing the find command and keyword (e.g., "find book").
     * @throws PixelException If the command format is invalid or if the keyword is missing.
     */
    public void findTasks(String line) throws PixelException {
        Parser parser = new Parser(line);
        String keyword = parser.parseFind();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");

        int count = 1;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(count + "." + task);
                count++;
            }
        }

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Deletes a task from the list and the storage file based on the provided index.
     *
     * @param line The full user input string (e.g., "delete 1").
     * @throws PixelException If the argument is missing or if the task number is invalid.
     */
    public void deleteTask(String line) throws PixelException {
        Parser parser = new Parser(line);
        int id = parser.parseTaskId(tasks);

        Task deletedTask = tasks.get(id - 1);
        tasks.remove(id - 1);

        try {
            Storage storage = new Storage(filePath);
            storage.deleteFromFile(id - 1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
}
