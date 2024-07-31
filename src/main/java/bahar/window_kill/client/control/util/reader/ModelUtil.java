package bahar.window_kill.client.control.util.reader;

import java.util.Scanner;

public abstract class ModelUtil {
    public abstract Object read(Scanner sc);
    public abstract String write(Object object);
    protected String remainingString(Scanner sc) {
        StringBuilder remainingInput = new StringBuilder();
        while (sc.hasNextLine()) {
            remainingInput.append(sc.nextLine()).append("\n");
        }
        if (!remainingInput.isEmpty()) {
            remainingInput.deleteCharAt(remainingInput.length() - 1);
        }
        return remainingInput.toString();
    }
}
