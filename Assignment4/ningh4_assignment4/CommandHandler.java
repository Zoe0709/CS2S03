import java.text.ParseException;

/**
 * Command Handler deals with commands
 * When method run is executed, it will go to blockCommand or printCommand, and then execute the proper corresponding run function
 */

class CommandHandler {
    Database database;

    CommandHandler(Database database) {
        this.database = database;
    }

    void run(Command command) throws ParseException {
        command.run(database);
    }
}
