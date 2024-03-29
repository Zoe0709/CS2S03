import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();
    // New criterion for wealth of customers
    RangeCriterion customerWealthCriterion = new RangeCriterion();

    Plan(HashMap<String, Tag> tags) {
        name = tags.get("NAME").getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").getValue());

        // Add new criterion based on the new tags
        if (tags.get("CUSTOMER.AGE.MIN") != null) {
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGE.MIN"));
        }
        if (tags.get("CUSTOMER.AGE.MAX") != null) {
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGE.MAX"));
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            customerIncomeCriterion.addCriterion(tags.get("CUSTOMER.INCOME"));
        }
        if (tags.get("CUSTOMER.WEALTH") != null) {
            customerWealthCriterion.addCriterion(tags.get("CUSTOMER.WEALTH"));
        }
    }

    abstract boolean isEligible(Insurable insurable, Date date);

    abstract ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database);

    abstract Insurable getInsuredItem(String insurableID, Database database);

    boolean isEligible(Customer customer, Date currentDate) {
        // Extracting the age of the customer
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Calculate the age in years
        long age_in_Year = localCurrentDate.getYear() - localBirthDate.getYear();
        // Calculate the difference in months
        long age_in_Month = localCurrentDate.getMonthValue() - localBirthDate.getMonthValue();
        // Calculate the difference in days
        long age_in_Day = localCurrentDate.getDayOfMonth() - localBirthDate.getDayOfMonth();
        // Checking if the age is in the range.
        // If the difference of years is equal to the restriction of age of a contract, check month
        if (customerAgeCriterion.isOnEdge(age_in_Year)) {
            if (age_in_Month <= 0) {        // If the difference of months is zero of is negative, check day
                if (age_in_Day <= 0)
                    return false;
            }
        }
        if (!customerAgeCriterion.isInRange(age_in_Year))
            return false;
        // Checking if the income is in the range.
        if (!customerIncomeCriterion.isInRange(customer.getIncome())) {
            return false;
        }
        // Checking if the wealth is in the range.
        return customerWealthCriterion.isInRange(customer.getWealth());
    }

    String getName() {
        return name;
    }

    long getPremium() {
        return premium;
    }

    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    long getDeductible() {
        return deductible;
    }
}
