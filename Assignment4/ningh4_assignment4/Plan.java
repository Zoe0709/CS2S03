import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Abstract class Plan is a restricted class that cannot be used to create objects.
 * To access it, it must be inherited from another class.
 */
abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();

    Plan(HashMap<String, Tag> tags) {
        name = tags.get("NAME").getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").getValue());

        if (tags.get("CUSTOMER.AGE") != null) {
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGE"));
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            customerIncomeCriterion.addCriterion(tags.get("CUSTOMER.INCOME"));
        }

    }

    /*
     * Abstract method can only be used in this abstract class Plan, and it does not have a body.
     * The body is provided by the subclass.
     */
    abstract boolean isEligible(Insurable insurable, Date date);

    abstract Insurable getInsuredItem(Customer customer, Database database);

    boolean isEligible(Customer customer, Date currentDate) {
        // Extracting the age of the customer
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        // Checking if the age is in the range.
        if (!customerAgeCriterion.isInRange(age))
            return false;
        // Checking if the income is in the range.
        return customerIncomeCriterion.isInRange(customer.getIncome());
    }

    //Getter of name
    String getName() {
        return name;
    }

    //Getter of premium
    long getPremium() {
        return premium;
    }

    //Getter of maxCoveragePerClaim
    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    //Getter of deductible
    long getDeductible() {
        return deductible;
    }
}
