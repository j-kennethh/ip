import java.util.Scanner;

public class Pixel {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        int count = 0;
        String[] tasks = new String[100];

        String line;
        Scanner in = new Scanner(System.in);

        // Hello Message
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Pixel");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        // Main Loop
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ". [ ] " + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else {
                tasks[count] = line;
                count++;
                System.out.println(horizontalLine);
                System.out.println("added: " + line);
                System.out.println(horizontalLine);
            }
            line = in.nextLine();
        }

        // Bye Message
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
