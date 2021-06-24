import java.text.ParseException;
import java.util.HashMap;

//Class BlockCommand inherits from abstract class Command
class BlockCommand extends Command {
    private String blockType;
    private HashMap<String, Tag> tags = new HashMap<>();        // Private variable HashMap stores item in <String, Tag > pairs

    BlockCommand(String blockType) {
        this.blockType = blockType;
    }

    void addTag(Tag tag) {
        tags.put(tag.getName(), tag);
    }

    String getBlockType() {
        return blockType;
    }


    /**
     * The function run is mentioned in abstract class Command, and it is rewritten here.
     * New objects can be added into database only if the blockType matches.
     * When insert claims, the function calls processClaim to check if claim can be successfully processed
     * If processClaim returns True, then claim is successful
     * If processClaim returns False, then claim is not successful
     */
    @Override
    void run(Database database) throws ParseException {
        if (blockType.equals(Customer.inputTag)) {      // Check Customer blockType
            database.insertCustomer(new Customer(tags));
        } if (blockType.equals(Home.inputTag)) {      // Check Home blockType
            database.insertHome(new Home(tags));
        } if (blockType.equals(Car.inputTag)) {      // Check Car blockType
            database.insertCar(new Car(tags));
        } if (blockType.equals(Claim.inputTag)) {      // Check Claim blockType
            Claim claim = new Claim(tags);      // Create new claim objects
            database.insertClaim(claim);
            if (processClaim(claim, database)) {
                claim.setSuccessful(true);
                // Print success message if true
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was successful.");
            } else {
                claim.setSuccessful(false);
                // Else print no success message
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was not successful.");
            }
        } if (blockType.equals(Contract.inputTag)) {      // Check Contract blockType
            database.insertContract(new Contract(tags));
        } if (blockType.equals(HomePlan.inputTag)) {      // Check HomePlan blockType
            database.insertPlan(new HomePlan(tags));
        } if (blockType.equals(CarPlan.inputTag)) {      // Check CarPlan blockType
            database.insertPlan(new CarPlan(tags));
        }
    }


    /**
     * processClaim function checks if a claim can be processed,
     * if yes, returns True; if no, returns False
     */
    private boolean processClaim(Claim claim, Database database) {
        Contract contract = database.getContract(claim.getContractName());
        Customer customer = database.getCustomer(contract.getCustomerName());
        Plan plan = database.getPlan(contract.getPlanName());
        Insurable insurable = plan.getInsuredItem(customer, database);

        // If the claimed amount is higher than covered by the plan.
        if (plan.getMaxCoveragePerClaim() < claim.getAmount())
            return false;

        // If the claim date is not in the contract period.
        if (claim.getDate().after(contract.getEndDate()) || claim.getDate().before(contract.getStartDate()))
            return false;

        // If the customer was not eligible.
        if (!plan.isEligible(customer, claim.getDate()))
            return false;

        return plan.isEligible(insurable, claim.getDate());
    }
}
