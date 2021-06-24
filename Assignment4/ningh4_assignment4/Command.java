import java.text.ParseException;

/**
 * Abstract class Command requires a function called run.
 */
abstract class Command {
    abstract void run(Database database) throws ParseException;
}
