import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {
            // InputReader read inputs from keyboard
            InputReader inputReader = InputReader.getInstance();
            ArrayList<Command> commands = inputReader.getCommands();
            //Iterator allows us to traverse the collection, access the data element and remove the data elements of the collection.
            Iterator<Command> currentCommand = commands.iterator();

            CommandHandler commandHandler = new CommandHandler(new Database());

            /*
             * hasNext() returns true if Iterator has more element to iterate.
             * Object next() returns the next element in the collection. This method throws ‘NoSuchElementException’ if there is no next element.
             */

            while (currentCommand.hasNext()) {
                commandHandler.run(currentCommand.next());
            }
        //Errors from try part will catch if any
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (BadCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
