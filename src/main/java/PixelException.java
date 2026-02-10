public class PixelException extends RuntimeException {
    public static String horizontalLine = "____________________________________________________________";

    public PixelException(String message) {
        super(horizontalLine + System.lineSeparator() + message + System.lineSeparator() + horizontalLine);
    }
}
