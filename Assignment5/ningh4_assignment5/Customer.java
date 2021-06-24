import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Customer {
    private String name;
    private Date dateOfBirth;
    private long income;
    // Add a field called database
    private Database database;

    static final String inputTag = "CUSTOMER";

    Customer(HashMap<String, Tag> tags, Database db) throws ParseException {
        name = tags.get("NAME").getValue();
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").getValue());
        income = Long.parseLong(tags.get("INCOME").getValue());
        database = db;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public long getIncome() {
        return income;
    }

    // Getter of the wealth of a customer
    public long getWealth() {
        return database.totalWealthCalculate(name);
    }

    public static String getInputTag() {
        return inputTag;
    }
}
