package cpsc219p3.energytracker.util;

import cpsc219p3.energytracker.data.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * The cpsc219p3.energytracker.util.Reader class is responsible for reading and loading data from a specified file.
 * It processes various types of data including personal information, utility consumption, boolean responses, and user-selected months.
 */
public final class Reader {
    /**
     * Loads and parses data from a file into a cpsc219p3.energytracker.data.Data object.
     * The file is expected to contain personal information, utility consumption data, boolean responses, and a month.
     *
     * @param filename The name of the file to be read.
     * @return A cpsc219p3.energytracker.data.Data object containing all the parsed information from the file.
     */
    public static Data loadFile(String filename) {
        Data data = new Data();
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Reading and storing personal information
        // Assumes the first line of the file contains the name and the second line the report ID.
        String name = scanner.nextLine();
        int reportID = scanner.nextInt();
        scanner.nextLine();

        data.storePerson(reportID, name);

        // Reading and storing utility consumption data
        // Assumes utility data is in a comma-separated line with specific order: fridge, microwave, TV, dishwasher, lights, washer, dryer.
        String utilitiesLine = scanner.nextLine();
        String[] array = utilitiesLine.split(",");
        double fridge = Double.parseDouble(array[0]);
        double microwave = Double.parseDouble(array[1]);
        double TV = Double.parseDouble(array[2]);
        double dishwasher = Double.parseDouble(array[3]);
        double lights = Double.parseDouble(array[4]);
        double washer = Double.parseDouble(array[5]);
        double dryer = Double.parseDouble(array[6]);

        data.storeUtilityConsumptionPower(fridge,microwave,TV,dishwasher,lights,washer,dryer);
        data.storeAllutilityConsumptionCosts(fridge,microwave,TV,dishwasher,lights,washer,dryer);
        // Reading and storing boolean responses
        // Expects a line with boolean values separated by commas for AC, heater, and solar.
        String booleanLine = scanner.nextLine();
        String[] booleanArray = booleanLine.split(",");
        boolean AC = Boolean.parseBoolean(booleanArray[0]);
        boolean heater = Boolean.parseBoolean(booleanArray[1]);
        boolean solar = Boolean.parseBoolean(booleanArray[2]);


        data.storeBooleanResponse(AC, heater, solar);

        // Reading and storing user-selected month
        // Expects the month information as a single line string.
        String month = scanner.nextLine();
        Data.storeUserMonth(month);


        return data;
    }

}
