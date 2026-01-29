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
                    System.out.println(tasks[i].getId() + ". [ ] " + tasks[i].getName());
                }
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
