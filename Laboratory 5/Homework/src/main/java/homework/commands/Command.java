package homework.commands;

import homework.exceptions.InvalidNameException;

public interface Command {
    static void execute() throws InvalidNameException {
        throw new InvalidNameException("Not a valid command!");
    }
}
