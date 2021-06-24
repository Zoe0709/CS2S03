import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

// Class HomePlan inherits from class Plan
class HomePlan extends Plan {
    // State inputTag as HOME_PLAN for blockType
    static final String inputTag = "HOME_PLAN";
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    // Method HomePlan contains three elements super(tags), home.value, and home.age
    HomePlan(HashMap<String, Tag> tags) {
        super(tags);        // Calls constructor of Insurable for tags

        if (tags.get("HOME.VALUE") != null) {
            homeValueCriterion.addCriterion(tags.get("HOME.VALUE"));
        }
        if (tags.get("HOME.AGE") != null) {
            homeAgeCriterion.addCriterion(tags.get("HOME.AGE"));
        }
    }

    /**
     * Function isEligible first check if the input insurable is an instance of home
     * Then convert the insurable to type home, and check if the value of this home is in range
     * if yes, then returns true to say this is an eligible home; and vice versa
     */
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Home))
            return false;
        Home home = (Home) insurable;
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the age of the home
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    /**
     * Overwrite the getInsuredItem method in Plan class
     */
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
