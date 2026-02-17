package pixel;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pixel {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String FILE_PATH = "./data.txt";
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The main entry point of the Pixel application.
     * Initializes the scanner and runs the main command loop to process user input
     * until the "bye" command is received.
     * Handles various commands (todo, deadline, event, list, mark, unmark) and
     * manages exception handling for invalid inputs.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printHello();

        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        line = in.nextLine();
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    listTasks();
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
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Valid Commands: todo, deadline, event, list, mark, unmark, bye");
                    System.out.println(HORIZONTAL_LINE);
                }
            } catch (PixelException e) {
                System.out.println(e.getMessage());
            }
            line = in.nextLine();
        }

        printBye();
    }

    /**
     * Prints the standard welcome message and the initial prompt
     * to the console when the program runs.
     */
    private static void printHello() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Pixel");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the standard farewell message to the console
     * when the program terminates.
     */
    private static void printBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Parses the command line input to create and add a new ToDo task.
     *
     * @param line The full user input string containing the command and description.
     * @throws PixelException If the description part of the command is empty.
     */
    private static void addToDo(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: todo [description]");
        }

        String description = line.substring(5);

        ToDo newToDo = new ToDo(description, false);
        tasks.add(newToDo);

        try {
            appendToFile("T | 0 | " + description);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Parses the command line input to create and add a new Deadline task.
     * Expects the format: "deadline [description] /by [date]".
     *
     * @param line The full user input string.
     * @throws PixelException If the command format is incorrect or arguments are missing.
     */
    private static void addDeadline(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 4 || !line.contains("/by")) {
            throw new PixelException("Usage: deadline [description] /by [date]");
        }

        String[] sections = line.substring(9).split(" /by ");
        if (sections.length < 2) {
            throw new PixelException("Usage: deadline [description] /by [date]");
        }

        String description = sections[0];
        String date = sections[1];

        Deadline newDeadline = new Deadline(description, false, date);
        tasks.add(newDeadline);

        try {
            appendToFile("D | 0 | " + description + " | " + date);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + description + " (by: " + date + ")");
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Parses the command line input to create and add a new Event task.
     * Expects the format: "event [description] /from [start] /to [end]".
     *
     * @param line The full user input string.
     * @throws PixelException If the command format is incorrect, or if /from or /to tags are missing.
     */
    private static void addEvent(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 6 || !line.contains("/from") || !line.contains("/to")) {
            throw new PixelException("Usage: event [description] /from [start] /to [end]");
        }

        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        String description = line.substring(6, fromIndex - 1);
        String start = line.substring(fromIndex + 6, toIndex - 1);
        String end = line.substring(toIndex + 4);

        Event newEvent = new Event(description, false, start, end);
        tasks.add(newEvent);

        try {
            appendToFile("E | 0 | " + description + " | " + start + " | " + end);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + description + " (from: " + start + " to: " + end + ")");
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Iterates through the list of tasks and prints them to the console.
     */
    private static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");

        for (Task task : tasks) {
            System.out.println(task);
        }

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a specific task as done based on the task index provided in the command.
     *
     * @param line The full user input string (e.g., "mark 1").
     * @throws PixelException If the argument is missing or if the task number is invalid.
     */
    private static void markTask(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: mark [task number]");
        }

        int id = Integer.parseInt(words[1]);
        if (id < 1 || id > Task.getCount()) {
            throw new PixelException("Invalid task number");
        }

        tasks.get(id - 1).setDone(true);

        try {
            updateFile(id - 1, true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks.get(id - 1).getDescription());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a specific task as not done based on the task index provided in the command.
     *
     * @param line The full user input string (e.g., "unmark 1").
     * @throws PixelException If the argument is missing or if the task number is invalid.
     */
    private static void unmarkTask(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: unmark [task number]");
        }

        int id = Integer.parseInt(words[1]);
        if (id < 1 || id > Task.getCount()) {
            throw new PixelException("Invalid task number");
        }

        tasks.get(id - 1).setDone(false);

        try {
            updateFile(id - 1, false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("[ ] " + tasks.get(id - 1).getDescription());
        System.out.println(HORIZONTAL_LINE);
    }

    private static void deleteTask(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: delete [task number]");
        }
        int id = Integer.parseInt(words[1]);
        if (id < 1 || id > Task.getCount()) {
            throw new PixelException("Invalid task number");
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(id - 1).toString());
        tasks.remove(id - 1);
        Task.setCount(Task.getCount() - 1);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

    private static void loadTasks() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split(" \\| ");

            if (line.startsWith("T")) {
                ToDo newTodo = new ToDo(words[2], strToBool(words[1]));
                tasks.add(newTodo);
            } else if (line.startsWith("D")) {
                Deadline newDeadline = new Deadline(words[2], strToBool(words[1]), words[3]);
                tasks.add(newDeadline);
            } else if (line.startsWith("E")) {
                Event newEvent = new Event(words[2], strToBool(words[1]), words[3], words[4]);
                tasks.add(newEvent);
            }
        }
    }

    private static boolean strToBool(String value) {
        return value.equals("1");
    }

    private static void updateFile(int index, boolean isDone) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<String> fileContent = new ArrayList<>();

        while (s.hasNext()) {
            fileContent.add(s.nextLine());
        }

        if (0 <= index && index < fileContent.size()) {
            String line = fileContent.get(index);
            String[] words = line.split(" \\| ");
            if (isDone) {
                words[1] = "1";
            } else {
                words[1] = "0";
            }
            fileContent.set(index, String.join(" | ", words));
        }

        FileWriter fw = new FileWriter(FILE_PATH);
        for (String line : fileContent) {
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }
}
