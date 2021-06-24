import java.text.ParseException;

//Class PrintCommand inherits from abstract class Command
class PrintCommand extends Command {
    private String entityType;
    private String queryType;
    private String queryValue;

    PrintCommand(String[] tokens) {
        super();        // Constructor in Command is called
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
    }

    /**
     * The function run is mentioned in abstract class Command, and it is rewritten here.
     * Objects in database can be printed only if the entityType matches.
     * If the entityType is neither CUSTOMER nor PLAN, this method throws a BadCommandException
     */
    @Override
    void run(Database database) {
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    /**
     * This method prints the total amount claimed if the queryType equals to "TOTAL_CLAIMED", 
     * or received based if the queryType equals to "TOTAL_RECEIVED"
c    */
    private void runPrintCustomer(Database database) {
        if (queryType.equals("TOTAL_CLAIMED")) {
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        } else if (queryType.equals("TOTAL_RECEIVED")) {
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                            " is " + database.totalReceivedAmountByCustomer(queryValue));
        }
    }

    /**
     * This method prints the number of customers under a certain plan if the queryType equals to "NUM_CUSTOMERS",
     * or the total amount payed if the queryType equals to "TOTAL_PAYED_TO_CUSTOMERS"
     */
    private void runPrintPlan(Database database) {
        if (queryType.equals("NUM_CUSTOMERS")) {
            System.out.println("Number of customers under " + database.getPlan(queryValue).getName()+
                    " is " + database.numberOfCustomerUnderPlan(queryValue));
        } else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")) {
            System.out.println("Total amount payed under " + database.getPlan(queryValue).getName() +
                    " is " + database.totalAmountPayedUnderPlan(queryValue));
        }
    }
}
