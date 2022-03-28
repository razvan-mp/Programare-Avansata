package homework.commands;

import homework.exceptions.InvalidNameException;

/**
 * interface for commands
 */
public interface Command {
    /**
     * @throws InvalidNameException in case a command isn't valid
     */
    static void execute() throws InvalidNameException {
        throw new InvalidNameException("Not a valid command!");
    }
}
