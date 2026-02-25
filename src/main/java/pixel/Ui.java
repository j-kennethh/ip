package pixel;

import java.util.ArrayList;

public class Ui {
    private final String HORIZONTAL_LINE = "____________________________________________________________";

    public Ui() {
    }

    /**
     * Prints the standard welcome message and the initial prompt
     * to the console when the program runs.
     */
    public void printHello() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Pixel");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the standard farewell message to the console
     * when the program terminates.
     */
    public void printBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Iterates through the list of tasks and prints them to the console.
     * The list index is generated dynamically (1-based index).
     */
    public void listTasks(ArrayList<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
    }
}
