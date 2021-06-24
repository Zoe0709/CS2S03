import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Claim {
    private String contractName;
    private long amount;
    private Date date;
    private boolean successful;

    // State inputTag as CLAIM for blockType
    static final String inputTag = "CLAIM";

    // Method Claim contains three elements contractName, date and amount
    Claim(HashMap<String, Tag> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").getValue();
        date = Utils.convertDate(tags.get("DATE").getValue());
        amount = Long.parseLong(tags.get("AMOUNT").getValue());
    }

    // Get the contractName
    public String getContractName() {
        return contractName;
    }

    // Get the amount
    public long getAmount() {
        return amount;
    }

    // Get the date
    public Date getDate() {
        return date;
    }

    public boolean wasSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
