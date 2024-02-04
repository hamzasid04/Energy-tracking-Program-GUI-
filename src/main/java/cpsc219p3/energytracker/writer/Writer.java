package cpsc219p3.energytracker.writer;

import cpsc219p3.energytracker.data.Data;
import cpsc219p3.energytracker.data.Person;
import cpsc219p3.energytracker.p2.Menu;
import cpsc219p3.energytracker.power.Power;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    /**
     * Writes the given data to a file named "Saver.txt".
     * The data includes personal information, power consumption details, boolean responses, and user-selected months.
     *
     * @param data The data object containing information to be written to the file.
     */

    /**
     * Writes the given data to a file named "Saver.txt".
     * The data includes personal information, power consumption details, boolean responses, and user-selected months.
     *
     * @param data The data object containing information to be written to the file.
     */
    public static void writeDataToFile(Data data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Saver.txt"))) {
            // Write Person and Report ID data
            for (Person person : data.getPersonAndIDList()) {
                writer.write(person.getName() + "\n");
                writer.write(person.getReportID() + "\n");
            }

            // Write Utility Power Consumption data
            for (Power powers : data.getallutilityPowerConsumptionList()) {
                writer.write(powers.getFridgePower() + "," +
                        powers.getMicrowavePower() + "," +
                        powers.getTVPower() + "," +
                        powers.getDishwasherPower() + "," +
                        powers.getLightPower() + "," +
                        powers.getWasherPower() + "," +
                        powers.getDryerPower());
                writer.newLine();
            }

            // Write Boolean Responses
            writer.write(data.hasAC() ? "true" : "false");
            writer.write(",");
            writer.write(data.hasHeater() ? "true" : "false");
            writer.write(",");
            writer.write(data.hasSolar() ? "true" : "false");
            writer.newLine();

            // Write User Selected Months
            for (String month : Data.getUserMonth()) {
                writer.write(month);
                writer.newLine();
            }

            System.out.println("Inputs have been saved into Saver.txt file within repository");
        } catch (IOException e) {
            System.err.println("Runtime Exception: ERROR WRITING TO 'Saver.txt': " + e.getMessage());
        }
    }
}
