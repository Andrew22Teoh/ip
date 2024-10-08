package rizzler;

import rizzler.command.Command;
import rizzler.parser.Parser;
import rizzler.task.TaskLog;

/**
 * Represents the entire chatbot.
 * Rizzler helps you to manage your tasks, and is definitely not interested in you.
 */
public class Rizzler {
    private boolean isUserDone = false;
    private final Parser parser;
    private final Storage storage;
    private final TaskLog taskLog;

    /**
     * Constructor for a Rizzler instance.
     */
    public Rizzler() {
        parser = new Parser();
        storage = new Storage();
        taskLog = storage.getTasks();
    }

    /**
     * Passes in an input to the Rizzler object, and returns its response.
     * Also updates <code>isUserDone</code> based on the output of the command.
     *
     * @param userInput Whatever the user has typed in as input.
     * @return Rizzler's output in response to user input.
     */
    public String getResponse(String userInput) {
        assert userInput != null : "userInput is null";

        StringBuilder response = new StringBuilder();
        Command userCommand = parser.parseInput(userInput);
        assert userCommand != null : "userCommand is null";

        String[] responseLines = userCommand.execute(storage, taskLog);
        isUserDone = userCommand.shouldEnd();

        for (String responseLine : responseLines) {
            assert responseLine != null : "responseLine is null";
            response.append(responseLine);
            response.append("\n");
        }
        return response.toString();
    }

    /**
     * Informs <code>MainWindow</code> whether the user is done with the Rizzler.
     * In the event that the user is done, the application may then be closed.
     *
     * @return Whether the user is done or not.
     */
    public boolean shouldEnd() {
        return isUserDone;
    }
}
