/**
 * This InputReader class reads input from keyboard, and check the command type based on the first element of each line.
 * After checking type, corresponding code will be executed.
 */

import java.util.ArrayList;
import java.util.Scanner;

class InputReader {
    private Scanner keyboard;
    private static InputReader instance = null;
    private int lineNumber = 0;         // initialize private variable lineNumber

    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    // If there is nothing to begin with, call the constructor to initialize
    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();        // Create new command arraylist to store reformed input
        String line = "";
        lineNumber = 0;


    /*
     *  First execute try part. If next line of input exists, lineNumber plus one, read next line of input, and take different
     *  actions according to the first word.
     */

        try {
            while (keyboard.hasNext()) {
                lineNumber++;
                line = keyboard.nextLine();
                if (line.startsWith("PRINT ")) {        // If the first word of this line of input is PRINT
                    commands.add(makePrintCommand(line));       // Call makePrintCommand function
                } else if (line.startsWith("BEGIN_")) {         // If the first word of this line of input is BEGIN
                    commands.add(makeBlockCommand(line));       // Call makeBlockCommand function
                } else if (line.equals("FINISH")) {
                    break;
                } else if (!line.equals("")) {      // If the input line is not empty string
                    System.out.println(line);
                    throw new BadCommandException("Invalid command.");      // Raise error message "Invalid command"
                }
            }
        } catch (BadCommandException e) {       // If errors exist when executing try part, it goes to catch.
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        return commands;
    }

    /**
     * Function makeBlockCommand take one line of input that starts with BEGIN_
     * then, it goes to the next line and check if it starts with END_
     * if not, then split and change the input line into Tag, and add it to the command list
     */
    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));

        while (keyboard.hasNext()) {
            lineNumber ++;
            line = keyboard.nextLine();
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                String [] tokens = line.split(" ", 3);      // Split the input into three parts by space
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    /**
     * Function makePrintCommand takes a line of input, split, and check the length of split tokens
     * the function raises exception messages when the length is greater or smaller than 4
     */

    private Command makePrintCommand(String line) {
        String[] tokens = line.split(" ", 5);      // Split the input into five parts by space
        if (tokens.length > 4) {
            throw new BadCommandException("Invalid print command; too many tokens.");
        } else if (tokens.length < 4) {
            throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
    }
}