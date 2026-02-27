package pixel;

/**
 * Custom exception class for the Pixel application.
 * Used to handle application-specific errors, such as invalid commands,
 * incorrect formatting, or missing arguments.
 */
public class PixelException extends Exception {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Constructs a new PixelException with the specified error message.
     * The message is automatically wrapped in horizontal lines to maintain
     * consistent UI formatting when the error is printed to the console.
     *
     * @param message The detailed error message explaining the reason for the exception.
     */
    public PixelException(String message) {
        super(HORIZONTAL_LINE + System.lineSeparator() + message + System.lineSeparator() + HORIZONTAL_LINE);
    }
}
