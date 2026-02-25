package pixel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pixel {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String FILE_PATH = "./data.txt";
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The main entry point of the Pixel application.
     * <p>
     * Initializes the application by loading existing tasks from the storage file.
     * Runs the main command loop to process user input until the "bye" command is received.
     * Handles command delegation and exception management.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage(FILE_PATH);

        ui.printHello();

        storage.loadTasks(tasks);

        String line;
        line = in.nextLine();

        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    ui.listTasks(tasks);
                } else if (line.startsWith("mark")) {
                    markTask(line);
                } else if (line.startsWith("unmark")) {
                    unmarkTask(line);
                } else if (line.startsWith("todo")) {
                    addToDo(line);
                } else if (line.startsWith("deadline")) {
                    addDeadline(line);
                } else if (line.startsWith("event")) {
                    addEvent(line);
                } else if (line.startsWith("delete")) {
                    deleteTask(line);
                } else {
                    ui.printInvalidCommand();
                }
            } catch (PixelException e) {
                System.out.println(e.getMessage());
            }
            line = in.nextLine();
        }

        ui.printBye();
    }

    /**
     * Parses the command line input to create a new ToDo task.
     * Adds the task to the internal list and appends it to the storage file.
     *
     * @param line The full user input string containing the command and description.
     * @throws PixelException If the description part of the command is empty.
     */
    private static void addToDo(String line) throws PixelException {
        Parser parser = new Parser(line);
        String description = parser.parseToDo();

        ToDo newToDo = new ToDo(description, false);
        tasks.add(newToDo);

        try {
            Storage storage = new Storage(FILE_PATH);
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
     * Expects the format: "deadline [description] /by [date]".
     *
     * @param line The full user input string.
     * @throws PixelException If the command format is incorrect or arguments are missing.
     */
    private static void addDeadline(String line) throws PixelException {
        Parser parser = new Parser(line);
        String[] sections = parser.parseDeadline();

        String description = sections[0];
        String date = sections[1];

        Deadline newDeadline = new Deadline(description, false, date);
        tasks.add(newDeadline);

        try {
            Storage storage = new Storage(FILE_PATH);
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
     * Expects the format: "event [description] /from [start] /to [end]".
     *
     * @param line The full user input string.
     * @throws PixelException If the command format is incorrect, or if tags are missing.
     */
    private static void addEvent(String line) throws PixelException {
        Parser parser = new Parser(line);
        String[] sections = parser.parseEvent();

        String description = sections[0];
        String start = sections[1];
        String end = sections[2];

        Event newEvent = new Event(description, false, start, end);
        tasks.add(newEvent);

        try {
            Storage storage = new Storage(FILE_PATH);
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
    private static void markTask(String line) throws PixelException {
        Parser parser = new Parser(line);
        int id = parser.parseTaskId(tasks);

        tasks.get(id - 1).setDone(true);

        try {
            Storage storage = new Storage(FILE_PATH);
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
    private static void unmarkTask(String line) throws PixelException {
        Parser parser = new Parser(line);
        int id = parser.parseTaskId(tasks);

        tasks.get(id - 1).setDone(false);

        try {
            Storage storage = new Storage(FILE_PATH);
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
     * Deletes a task from the list and the storage file based on the provided index.
     *
     * @param line The full user input string (e.g., "delete 1").
     * @throws PixelException If the argument is missing or if the task number is invalid.
     */
    private static void deleteTask(String line) throws PixelException {
        Parser parser = new Parser(line);
        int id = parser.parseTaskId(tasks);

        Task deletedTask = tasks.get(id - 1);
        tasks.remove(id - 1);

        try {
            Storage storage = new Storage(FILE_PATH);
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
