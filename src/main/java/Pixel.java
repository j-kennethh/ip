import java.util.Scanner;

public class Pixel {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Pixel");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println(line);
            System.out.println(horizontalLine);
            line = in.nextLine();
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
