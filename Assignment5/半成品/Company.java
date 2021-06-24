import java.text.ParseException;
import java.util.HashMap;

// Class Company inherits from class Insurable
// Company contains three elements owner name, company name, and value
class Company extends Insurable {
    private String ownerName;
    private String companyName;
    private long value;

    // States inputTag as COMPANY to match the blockType whe adding new company objects
    static final String inputTag = "COMPANY";

    Company(HashMap<String, Tag> tags) throws ParseException {
        super(tags);
        ownerName = tags.get("OWNER_NAME").getValue();
        companyName = tags.get("COMPANY_NAME").getValue();
        value = Long.parseLong(tags.get("VALUE").getValue());
    }

    // Getter of ownerName
    public String getOwnerName() { return ownerName; }

    // Getter of companyName
    public String getCompanyName() { return companyName; }

    // Getter of value
    public long getValue() { return value; }

    // Getter of inputTag
    public static String getInputTag() { return inputTag; }
}
