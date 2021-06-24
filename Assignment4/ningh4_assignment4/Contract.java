import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Contract {
    private String contractName;
    private String customerName;
    private String planName;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;

    // State inputTag as CONTRACT for blockType
    static final String inputTag = "CONTRACT";

    // Method Contract contains six elements contractName, customerName, planName, startDate, endDate, and discountPercentage
    Contract(HashMap<String, Tag> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").getValue();
        customerName = tags.get("CUSTOMER_NAME").getValue();
        planName = tags.get("PLAN_NAME").getValue();
        startDate = Utils.convertDate(tags.get("START_DATE").getValue());
        endDate = Utils.convertDate(tags.get("END_DATE").getValue());
        discountPercentage = Integer.parseInt(tags.get("DISCOUNT_PERCENTAGE").getValue());
    }

    //Get the customerName
    public String getCustomerName() {
        return customerName;
    }

    //Get the planName
    public String getPlanName() {
        return planName;
    }

    //Get the startDate
    public Date getStartDate() {
        return startDate;
    }

    //Get the endDate
    public Date getEndDate() {
        return endDate;
    }

    //Get the discountPercentage
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    //Get the inputTag
    public static String getInputTag() {
        return inputTag;
    }

    //Get the contractName
    public String getContractName() {
        return contractName;
    }
}
