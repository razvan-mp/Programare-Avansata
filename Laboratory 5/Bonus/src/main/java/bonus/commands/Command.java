package bonus.commands;

import bonus.exceptions.InvalidNameException;

public interface Command {
    static void execute() throws InvalidNameException {
        throw new InvalidNameException("Not a valid command!");
    }
}
