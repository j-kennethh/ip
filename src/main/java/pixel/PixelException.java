package pixel;

public class PixelException extends Exception {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public PixelException(String message) {
        super(HORIZONTAL_LINE + System.lineSeparator() + message + System.lineSeparator() + HORIZONTAL_LINE);
    }
}
