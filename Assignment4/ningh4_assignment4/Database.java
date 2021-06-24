import java.util.ArrayList;

/**
 * Class Database can deal with arraylists that contain different types of variable like customers, homes, cars, plans,
 * contracts, and claims.
 */
class Database {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();


    /* Create insert functions for all types to add new items. */
    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    /* Create get functions to get element of different type. */

    // Get plan only if name matches
    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    // Get customer only if name matches
    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    // Get contract only if name matches
    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     * Get home only if ownerName matches
     */
    Home getHome(String ownerName) {
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     * Get car only if ownerName matches
     */
    Car getCar(String ownerName) {
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownerName))
                return car;
        }
        return null;
    }

    /**
     * Method totalClaimAmountByCustomer calculates the total amount that claimed by customers
     * It takes customer name as input, and if the customer name in contract matches the input,
     * returns the corresponding amount in claim
     */
    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

    /**
     * Method totalReceivedAmountByCustomer calculates the total amount that received from customers
     * It takes customer name as input, and if the customer name in contract matches the input, and the claim succeeded,
     * returns the corresponding amount in claim minus deductible or zero, depends on which one is bigger
     */
    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            if (contract.getCustomerName().equals(customerName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }

    /**
     * Method numberOfCustomerUnderPlan calculates the number of customers that are under a certain plan
     * It takes plan name as input, and if the plan name in contract matches the input, the number of customer plus one.
     * When all contracts are compared, returns the number
     */
    int numberOfCustomerUnderPlan(String planName) {
        int numOfCustomer = 0;
        for (Contract contract : contracts) {
            if (contract.getPlanName().equals(planName)) {
                numOfCustomer++;
            }
        }
        return numOfCustomer;
    }

    /**
     * Method totalAmountPayedUnderPlan calculates the total amount payed under a certain plan
     * It takes plan name as input, and if the plan name in contract matches the input,
     * the contract name in contract matches that in claim, and the claim succeeded,
     * returns the amount in claim minus deductible or zero, depends on which one is bigger
     */
    long totalAmountPayedUnderPlan(String planName) {
        long totalAmountPayed = 0;
        for (Contract contract : contracts) {
            for (Claim claim : claims) {
                if (contract.getPlanName().equals(planName)) {
                    if (contract.getContractName().equals(claim.getContractName()) && claim.wasSuccessful()) {
                        long deductible = getPlan(contract.getPlanName()).getDeductible();
                        totalAmountPayed += Math.max(0, claim.getAmount() - deductible);
                    }
                }
            }
        }
        return totalAmountPayed;
    }
}
