import java.util.Scanner;

public class Pixel {
    public static String horizontalLine = "____________________________________________________________";

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

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);

        printHello();

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.getCount(); i++) {
                    if (tasks[i].isDone()) {
                        System.out.println(tasks[i].getId() + ". [X] " + tasks[i].getName());
                    } else {
                        System.out.println(tasks[i].getId() + ". [ ] " + tasks[i].getName());
                    }
                }
                System.out.println(horizontalLine);
            } else if (line.startsWith("mark")) {
                int id = Integer.parseInt(line.substring(line.length() - 1));
                tasks[id - 1].setDone(true);
                System.out.println(horizontalLine);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[id - 1].getName());
                System.out.println(horizontalLine);
            } else if (line.startsWith("unmark")) {
                int id = Integer.parseInt(line.substring(line.length() - 1));
                tasks[id - 1].setDone(false);
                System.out.println(horizontalLine);
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[id - 1].getName());
                System.out.println(horizontalLine);
            } else {
                Task newTask = new Task(line);
                tasks[Task.getCount() - 1] = newTask;
                System.out.println(horizontalLine);
                System.out.println("added: " + newTask.getName());
                System.out.println(horizontalLine);
            }
            line = in.nextLine();
        }

        printBye();
    }
}
