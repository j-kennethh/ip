package pixel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the data file and its parent directory exist.
     * If they do not exist, this method creates them.
     *
     * @throws IOException If the directory or file cannot be created.
     */
    public void checkFileExists() throws IOException {
        File f = new File(filePath);

        if (f.getParentFile() != null && !f.getParentFile().exists()) {
            if (!f.getParentFile().mkdirs()) {
                throw new IOException("Failed to create directory: " + f.getParentFile());
            }
        }

        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new IOException("Failed to create file: " + f.getAbsolutePath());
            }
        }
    }

    /**
     * Appends a formatted string representation of a task to the storage file.
     *
     * @param text The string to append to the file.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void appendToFile(String text) throws IOException {
        checkFileExists();
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

    /**
     * Updates the completion status (0 or 1) of a task in the storage file.
     * Reads the entire file, modifies the specific line, and rewrites the file.
     *
     * @param index  The 0-based index of the task to update.
     * @param isDone The new status of the task.
     * @throws IOException If an I/O error occurs during reading or writing.
     */
    public void updateFile(int index, boolean isDone) throws IOException {
        checkFileExists();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> fileContent = new ArrayList<>();

        while (s.hasNext()) {
            fileContent.add(s.nextLine());
        }
        s.close();

        if (0 <= index && index < fileContent.size()) {
            String line = fileContent.get(index);
            String[] words = line.split(" \\| ");
            if (isDone) {
                words[1] = "1";
            } else {
                words[1] = "0";
            }
            fileContent.set(index, String.join(" | ", words));
        }

        FileWriter fw = new FileWriter(filePath);
        for (String line : fileContent) {
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Removes a line corresponding to a deleted task from the storage file.
     * Reads the entire file, removes the specific line, and rewrites the file.
     *
     * @param index The 0-based index of the line to remove.
     * @throws IOException If an I/O error occurs during reading or writing.
     */
    public void deleteFromFile(int index) throws IOException {
        checkFileExists();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> fileContent = new ArrayList<>();

        while (s.hasNext()) {
            fileContent.add(s.nextLine());
        }
        s.close();

        if (0 <= index && index < fileContent.size()) {
            fileContent.remove(index);
        }

        FileWriter fw = new FileWriter(filePath);
        for (String line : fileContent) {
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads tasks from the storage file into the application's memory upon startup.
     * Parses the file content to recreate ToDo, Deadline, and Event objects.
     */
    public void loadTasks(ArrayList<Task> tasks) {
        File f = new File(filePath);

        if (!f.exists()) {
            return;
        }

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] words = line.split(" \\| ");

                if (line.startsWith("T")) {
                    ToDo newTodo = new ToDo(words[2], strToBool(words[1]));
                    tasks.add(newTodo);
                } else if (line.startsWith("D")) {
                    Deadline newDeadline = new Deadline(words[2], strToBool(words[1]), words[3]);
                    tasks.add(newDeadline);
                } else if (line.startsWith("E")) {
                    Event newEvent = new Event(words[2], strToBool(words[1]), words[3], words[4]);
                    tasks.add(newEvent);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Helper method to convert a string "1" or "0" from the file to a boolean.
     *
     * @param value The string value ("1" for true, "0" for false).
     * @return true if value is "1", false otherwise.
     */
    private boolean strToBool(String value) {
        return value.equals("1");
    }
}
