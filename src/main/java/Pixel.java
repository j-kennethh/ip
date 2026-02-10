import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Pixel {
    public static String horizontalLine = "____________________________________________________________";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printHello();

        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks();
            } else if (line.startsWith("mark")) {
                markTask(line);
            } else if (line.startsWith("unmark")) {
                unmarkTask(line);
            } else if (line.startsWith("todo")) {
                try {
                    addToDo(line);
                } catch (PixelException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.startsWith("deadline")) {
                try {
                    addDeadline(line);
                } catch (PixelException e) {
                    System.out.println(e.getMessage());
                }
            } else if (line.startsWith("event")) {
                try {
                    addEvent(line);
                } catch (PixelException e) {
                    System.out.println(e.getMessage());
                }
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

    private static void addToDo(String line) throws PixelException  {
        String[] words = line.split(" ");
        if (words.length < 2) {
            throw new PixelException("Usage: todo [description]");
        }
        ToDo newToDo = new ToDo(line.substring(5));
        tasks.add(newToDo);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + newToDo.getDescription());
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void addDeadline(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 4 || !line.contains("/by")) {
            throw new PixelException("Usage: deadline [description] /by [date]");
        }
        String[] sections = line.substring(9).split(" /by ");
        Deadline newDeadline = new Deadline(sections[0], sections[1]);
        tasks.add(newDeadline);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + newDeadline.getDescription() + " (by: " + newDeadline.getDate() + ")");
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void addEvent(String line) throws PixelException {
        String[] words = line.split(" ");
        if (words.length < 6 || !line.contains("/from") || !line.contains("/to")) {
            throw new PixelException("Usage: event [description] /from [start] /to [end]");
        }
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        String description = line.substring(6, fromIndex);
        String start = line.substring(fromIndex + 6, toIndex - 1);
        String end = line.substring(toIndex + 4);
        Event newEvent = new Event(description, start, end);
        tasks.add(newEvent);
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + description + "(from: " + start + " to: " + end + ")");
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void listTasks() {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(task.list());
        }
        System.out.println(horizontalLine);
    }

    private static void markTask(String line) {
        int id = Integer.parseInt(line.substring(line.length() - 1));
        tasks.get(id - 1).setDone(true);
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks.get(id - 1).getDescription());
        System.out.println(horizontalLine);
    }

    private static void unmarkTask(String line) {
        int id = Integer.parseInt(line.substring(line.length() - 1));
        tasks.get(id - 1).setDone(false);
        System.out.println(horizontalLine);
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("[ ] " + tasks.get(id - 1).getDescription());
        System.out.println(horizontalLine);
    }
}
