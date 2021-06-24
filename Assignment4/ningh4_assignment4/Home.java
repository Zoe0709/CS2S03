import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

// Class Home inherits from class Insurable
class Home extends Insurable {
    private String postalCode;
    private Date buildDate;

    // State inputTag as HOME for blockType
    static final String inputTag = "HOME";

    // Method Home contains three elements super(tags), postalCode and buildDate
    Home(HashMap<String, Tag> tags) throws ParseException {
        super(tags);        // Calls constructor of Insurable for tags
        postalCode = tags.get("POSTAL_CODE").getValue();
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").getValue());
    }

    //Getter of postalCode
    public String getPostalCode() {
        return postalCode;
    }

    //Getter of buildDate
    public Date getBuildDate() {
        return buildDate;
    }

    // Getter of inputTag
    public static String getInputTag() {
        return inputTag;
    }
}
