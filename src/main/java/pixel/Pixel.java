package pixel;

import java.util.ArrayList;
import java.util.Scanner;

public class Pixel {
    public static final String FILE_PATH = "./data.txt";

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
        TaskList taskList = new TaskList(FILE_PATH);

        ArrayList<Task> tasks = taskList.getTasks();

        ui.printHello();
        storage.loadTasks(tasks);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    ui.listTasks(tasks);
                } else if (line.startsWith("todo")) {
                    taskList.addToDo(line);
                } else if (line.startsWith("deadline")) {
                    taskList.addDeadline(line);
                } else if (line.startsWith("event")) {
                    taskList.addEvent(line);
                } else if (line.startsWith("mark")) {
                    taskList.markTask(line);
                } else if (line.startsWith("unmark")) {
                    taskList.unmarkTask(line);
                } else if (line.startsWith("find")) {
                    taskList.findTasks(line);
                } else if (line.startsWith("delete")) {
                    taskList.deleteTask(line);
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
}
