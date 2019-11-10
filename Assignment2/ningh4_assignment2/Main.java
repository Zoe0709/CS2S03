import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<CarModel> carModelList = new ArrayList<CarModel>();
        ArrayList<Car> carList = new ArrayList<>();
        ArrayList<String> outputL = new ArrayList<>();
        Boolean checkNextLine = false;

        Scanner scanner = new Scanner(System.in);

        while (! checkNextLine) {
            String[] carStuff = scanner.nextLine().split(" ");
            String label = carStuff [0];

            switch (label) {
                case "MODEL":
                    CarModel newModel = createNewModel(carStuff);
                    carModelList.add(newModel);
                    break;

                case "CAR":
                    Car newCar = createCar(carStuff, carModelList);
                    carList.add(newCar);
                    break;

                case "TRIP":
                    outputL.add(solveTrip(carStuff, carList));
                    break;

                case "REFILL":
                    int plateN = Integer.parseInt(carStuff[1]);
                    Car theCar = findCar(plateN, carList);
                    theCar.refill();
                    break;

                case "FINISH":
                    for (String output : outputL) {
                        System.out.println(output);
                    }
                    checkNextLine = true;
                    break;

                case "LONGTRIPS":
                    solveLongTrip(carStuff, carList, outputL);
                    break;
            }

        }

    }

    private static CarModel createNewModel(String[] carStuff) {
        String name = carStuff[1];
        float fuelEconomy = Float.parseFloat(carStuff[2]);
        float gasTankSize = Float.parseFloat(carStuff[3]);
        return new CarModel(name, fuelEconomy, gasTankSize);

    }

    private static Car createCar(String[] carStuff, ArrayList<CarModel> carModelList) {
        String model = carStuff[1];
        CarModel carModel = findModel(model, carModelList);
        int plateNumber = Integer.parseInt(carStuff[2]);
        return new Car(carModel, plateNumber);

    }

    private static CarModel findModel(String modelName, ArrayList<CarModel> carModelList) {
        for (CarModel element : carModelList) {
            String nameRepresentation = element.getName();
            if (nameRepresentation.equals(modelName)) {
                return element;
            }
        }
        return null;
    }

    private static Car findCar(int plateNumber, ArrayList<Car> carList) {
        for (Car element : carList) {
            int plateNum = element.getPlateNumber();
            if (plateNum == (plateNumber)) {
                return element;
            }
        }
        return null;
    }

    private static String solveTrip(String[] carStuff,  ArrayList<Car> carList) {
        String result;
        int plateNum = Integer.parseInt(carStuff[1]);
        Car theCar = findCar(plateNum, carList);
        Double dis = Double.parseDouble(carStuff[2]);
        if (theCar.trip(dis)) {
            result = "Trip completed successfully for #" + Integer.toString(plateNum);
        } else {
            result = "Not enough fuel for #" + Integer.toString(plateNum);
        }
        return result;
    }

    private static void solveLongTrip(String[] carStuff, ArrayList<Car> carList, ArrayList<String> outputL) {
        int count = 0;
        int plateNum = Integer.parseInt(carStuff[1]);
        float distance = Float.parseFloat(carStuff[2]);
        Car car = findCar(plateNum, carList);
        assert car != null;
        ArrayList<Double> distanceList = car.getDistance();
        for (double dis : distanceList) {
            if (dis > distance ) {
                count++;
            }
        }
        String result = "#" + carStuff[1] + " made " + Integer.toString(count) + " trips longer than " + carStuff[2];
        outputL.add(result);
    }

}
