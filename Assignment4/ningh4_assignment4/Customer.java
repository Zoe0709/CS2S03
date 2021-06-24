import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Customer {
    private String name;
    private Date dateOfBirth;
    private long income;

    // State inputTag as CUSTOMER for blockType
    static final String inputTag = "CUSTOMER";

    // Method Customer contains three elements name, dateOfBirth and income
    Customer(HashMap<String, Tag> tags) throws ParseException {
        name = tags.get("NAME").getValue();
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").getValue());
        income = Long.parseLong(tags.get("INCOME").getValue());
    }

    //Getter of name
    public String getName() {
        return name;
    }

    //Getter of dateOfBirth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    //Getter of income
    public long getIncome() {
        return income;
    }

    //Getter of inputTag
    public static String getInputTag() {
        return inputTag;
    }
}
