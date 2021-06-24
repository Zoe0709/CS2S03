import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

//Class car inherits from class Insurable
class Car extends Insurable {

    // Class car contains four private variable, make, model, purchaseDate and mileage
    private String make;
    private String model;
    private Date purchaseDate;
    private long mileage;

    // State inputTag as CAR to match the blockType whe adding new car objects
    static final String inputTag = "CAR";

    // Method Car contains five elements super(tags), make, model, purchaseDate, and mileage
    Car(HashMap<String, Tag> tags) throws ParseException {
        super(tags);        // Calls constructor of Insurable for tags
        make = tags.get("MAKE").getValue();         // Get the brand of cars
        model = tags.get("MODEL").getValue();       // Get the number of cars
        purchaseDate = Utils.convertDate(tags.get("PURCHASE_DATE").getValue());
        mileage = Long.parseLong(tags.get("MILEAGE").getValue());
    }

    // Several get functions that return the variable itself
    public String getOwnerName() {
        return ownerName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public long getMileage() {
        return mileage;
    }

    public static String getInputTag() {
        return inputTag;
    }
}

