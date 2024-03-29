import java.util.ArrayList;

class Database {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    // Insert function for the new type of entity company to add new items
    void insertCompany(Company company){
        companies.add(company);
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

    // Calculate the wealth of a customer no matter if the insurable objects are owned by him or her directly
    // Calculate the wealth separately
    // First, calculate the total value of companies
    long companyWealthCalculate(Company company) {
        long wealth = company.getValue();
        for (Company otherCompany: companies) {
            if (otherCompany.getOwnerName().equals(company.getCompanyName())) {
                wealth += companyWealthCalculate(otherCompany);
            }
        }
        return wealth;
    }
    // Second, calculate the total value of all insurable objects
    // Including homes, car, and companies
    long totalWealthCalculate(String name) {
        long wealth = 0;
        for (Home home: homes) {
            if (home.getOwnerName().equals(name))
                wealth += home.getValue();
        }
        for (Car car: cars) {
            if (car.getOwnerName().equals(name))
                wealth += car.getValue();
        }
        for (Company company: companies) {
            if (company.getOwnerName().equals(name))
                wealth += companyWealthCalculate(company);
        }
        return wealth;
    }

    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    static Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /* This function has been updated to output a list
    of homes rather than a single home. In other words,
    an owner may own multiple homes.
     */
    ArrayList<Home> getHomesByOwnerName(String ownerName) {
        ArrayList<Home> result = new ArrayList<>();
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownerName))
                result.add(home);
        }
        return result;
    }


    /* This function has been updated to output a list
    of homes rather than a single home. In other words,
    an owner may own multiple homes.
     */
    ArrayList<Car> getCarsByOwnerName(String ownerName) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownerName))
                result.add(car);
        }
        return result;
    }

    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

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

    Insurable getCarByPlateNumber(String insurableID) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(insurableID))
                return car;
        }
        return null;
    }

    Insurable getHomeByPostalCode(String insurableID) {
        for (Home home : homes) {
            if (home.getPostalCode().equals(insurableID))
                return home;
        }
        return null;
    }
}
