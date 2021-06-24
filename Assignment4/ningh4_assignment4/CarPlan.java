import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

// Class CarPlan inherits from abstract class Plan
class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";      // Set the inputTag for blockType
    RangeCriterion mileageCriterion = new RangeCriterion();

    CarPlan(HashMap<String, Tag> tags) {
        super(tags);        // Constructor in Plan is called for tags

        // If there is a CAR.MILEAGE, call addCriterion to find the minValue or maxValue
        if (tags.get("CAR.MILEAGE") != null) {
            mileageCriterion.addCriterion(tags.get("CAR.MILEAGE"));
        }
    }


    /**
     * Function isEligible first check if the input insurable is an instance of car
     * Then convert the insurable to type Car, and check if the mileage of this car is in range
     * if yes, then returns true to say this is an eligible car; and vice versa
     */
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Car))
            return false;
        Car car = (Car) insurable;
        return mileageCriterion.isInRange(car.getMileage());
    }

    /**
     * Overwrite the getInsuredItem method in Plan class
     */
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
