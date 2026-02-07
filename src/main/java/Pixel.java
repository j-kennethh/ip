import java.util.ArrayList;
import java.util.Scanner;

public class Pixel {
    public static String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);

        printHello();

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks(tasks);
            } else if (line.startsWith("mark")) {
                markTask(line, tasks);
            } else if (line.startsWith("unmark")) {
                unmarkTask(line, tasks);
            } else if (line.startsWith("todo")) {
                addToDo(line, tasks);
            }
            line = in.nextLine();
        }

        printBye();
    }

    public static void printHello() {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Pixel");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void printBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    private static void addToDo(String line, ArrayList<Task> tasks) {
        ToDo newToDo = new ToDo(line.substring(5));
        tasks.add(newToDo);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + newToDo.getDescription());
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            if (task.isDone()) {
                System.out.println(task.getId() + "." + task.type() + "[X] " + task.getDescription());
            } else {
                System.out.println(task.getId() + "." + task.type() + "[ ] " + task.getDescription());
            }
        }
        System.out.println(horizontalLine);
    }

    private static void markTask(String line, ArrayList<Task> tasks) {
        int id = Integer.parseInt(line.substring(line.length() - 1));
        tasks.get(id - 1).setDone(true);
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks.get(id - 1).getDescription());
        System.out.println(horizontalLine);
    }

    private static void unmarkTask(String line, ArrayList<Task> tasks) {
        int id = Integer.parseInt(line.substring(line.length() - 1));
        tasks.get(id - 1).setDone(false);
        System.out.println(horizontalLine);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("[ ] " + tasks.get(id - 1).getDescription());
        System.out.println(horizontalLine);
    }
}
