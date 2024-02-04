package cpsc219p3.energytracker;

import cpsc219p3.energytracker.cost.Cost;
import cpsc219p3.energytracker.data.Data;
import cpsc219p3.energytracker.data.Person;
import cpsc219p3.energytracker.p2.Menu;
import cpsc219p3.energytracker.power.Power;
import cpsc219p3.energytracker.util.Reader;
import cpsc219p3.energytracker.writer.Writer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import static cpsc219p3.energytracker.BooleanController.costBoolean;

/**
 * This class represents the controller for managing energy tracking in a household.
 * It handles user interactions for tracking power consumption and associated costs,
 * providing insights on energy efficiency, average consumption comparisons, and tips
 * for cost savings.
 *
 * @author Marwah Ahmed marwah.ahmed@ucalgary.ca, Hamza Siddiqui hamza.siddiqui@ucalgary.ca
 * @version 1.0
 *
 */

public class EnergyController {

    private Person person;//Declaration private instance variable 'person' of type Person

    public static Data data = new Data();//To store data
    private Window stage;//Used to open new stage for loading and saving to a file
    private static final DecimalFormat df = new DecimalFormat("0.00");


    @FXML
    private TextArea display_field;//Display field = central location where user will view all the contents in the GUI

    @FXML
    private TextField userMonth;

    @FXML
    private TextField userName;

    @FXML
    private Label status_display;//Status display = notification label in the bottom left corner

    /**
     * Getter method used to retrieve the stored data
     * @return The stored information
     */
    public static Data getData(){//Getter to retrieve data
        return data;
    }

    /**
     * Handles the action of generating ID, name, and month.
     * @param event the action event triggered by the user
     */
    @FXML
    void generate_ID_name_month(ActionEvent event) {
        try {
            // ArrayList of all months for validation
            ArrayList<String> validMonths = new ArrayList<>(Arrays.asList(
                    "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                    "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
            ));
            String name = userName.getText();
            String month = userMonth.getText().toUpperCase().trim();//Trim user month to upper case

            // Check if name is empty
            if (name == null || name.trim().isEmpty()) {
                status_display.setText("Error: Name cannot be empty.");
                status_display.setTextFill(Color.RED);
                return;
            }

            // Check if name contains numbers
            for (char c : name.toCharArray()) {
                if (Character.isDigit(c)) {
                    status_display.setText("Error: Name must not contain numbers.");
                    status_display.setTextFill(Color.RED);

                    return;
                }
            }

            // Check if month contains numbers
            for (char c : month.toCharArray()) {
                if (Character.isDigit(c)) {
                    status_display.setText("Error: Month must not contain numbers.");
                    status_display.setTextFill(Color.RED);

                    return;
                }
            }

            //Check to see if given month is a valid month
            if (!validMonths.contains(month)) {
                status_display.setText("Error: Please enter a valid month.");
                status_display.setTextFill(Color.RED);
                return;
            }

            //If month is empty
            if (month == null || month.trim().isEmpty()) {
                status_display.setText("Error: Month cannot be empty.");
                status_display.setTextFill(Color.RED);

                return;
            }

            //Generate random report ID
            Random random = new Random();
            int reportID = random.nextInt(1000);//Provide a number up to 1000

            // Create new Person object
            person = new Person(name, reportID);

            // Store month and person information
            Data.storeUserMonth(month);

            ArrayList<Person> personAndID = new ArrayList<>(data.getPersonAndIDList());//Make a copy of person and report ID list

            boolean samePersonChecker = false;//Set the same person checker to false

            for (Person personChecker : personAndID) {//For each person stored in the person list
                if (personChecker.equals(person)) {//If there exists a person already
                    samePersonChecker = true;//Will set to true and will break out the loop
                    status_display.setText("Account already exists, please enter another username");
                    status_display.setTextFill(Color.RED);
                    break;
                }
            }

            if (!samePersonChecker) {//Checks if the same person checker is still false (meaning it never go changed in the for loop)
                data.storePerson(reportID, name);//Store that person
            }

            // Update display
            display_field.setText("Name: " + person.getName() + '\n' +
                    "Report ID number: " + person.getReportID() + '\n' +
                    "Month: " + Data.getUserMonth()); // Assuming getUserMonth returns the latest stored month
            status_display.setText("Name, Report ID num and month successfully created");
            status_display.setTextFill(Color.DARKGREEN);

        } catch (Exception e) {
            // Handle any other exceptions here
            status_display.setText("Error: An unexpected error occurred. " + e.getMessage());
            status_display.setTextFill(Color.RED);

        }
    }

    /**
     * Opens a new window to input power details of individual utilities.
     *
     * @param event the action event triggered by the user
     */
    // Method to open a new window for power details input

    @FXML
    void power_details(ActionEvent event) {
        // Load the power details window and display it

        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("power.fxml"));
        Scene scene = null;
        try {
             scene = new Scene(fxmlLoader.load(), 600, 400);
        }catch (IOException e){
            status_display.setText("Error: An unexpected error occurred while opening power.fxml");
            status_display.setTextFill(Color.RED);
        }

        // Pass the reference of EnergyController
        PowerController cont  = fxmlLoader.getController();//retrieves the controller associated with the loaded power.fxml

        cont.setup(status_display,display_field);//cont.setEnergyController(this);
        Stage stage = new Stage();
        stage.setTitle("Enter Power of individual utilities");//Set title of window
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens a new window to input boolean details for power usage.
     * @param event the action event triggered by the user (the click of the 'AC, Heater and Solar Usage')
     */
    @FXML
    void boolean_details(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("booleanPower.fxml"));//Load booleanPower.fxml
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        }catch (IOException e){
            status_display.setText("Error: An unexpected error occurred while opening booleanPower.fxml");
            status_display.setTextFill(Color.RED);
        }
        // Pass the reference of EnergyController
        BooleanController cont  = fxmlLoader.getController();//retrieves the controller associated with the loaded power.fxml

        cont.setStatus_display(status_display);//cont.setEnergyController(this);
        Stage stage = new Stage();
        stage.setTitle("Enter yes/no questions");//Set title of window
        stage.setScene(scene);
        stage.show();


    }
    /**
     * Prints the details of electricity usage and cost.
     * @param event the action event triggered by the user (the click of the 'Print Electricity Summary')
     */
    @FXML
    void print_electricity_details(ActionEvent event) {
        //have to append cuz can't have 2 setText statement as the next one will override the prev. one

        ArrayList<Person> personAndIDList = data.getPersonAndIDList();//Make reference to ArrayList<Person> getPersonAndIDList()
        ArrayList<Power> allUtilityPowerConsumptionList = data.getallutilityPowerConsumptionList();//Make reference to ArrayList<Power> getallutilityPowerConsumptionList()
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();//Make reference to ArrayList<Cost> getallutilityCostList()

        StringBuilder displayText = new StringBuilder();//Make instance of StringBuilder

        displayText.append("Summary of your Electricity report: \n");
        displayText.append("----------------------------------------------------------\n");

        for (Person entry : personAndIDList) { //For each person in the stored list
            displayText.append("Report ID: ").append(entry.getReportID()).append('\n');//Append user report Id
            displayText.append("Name: ").append(entry.getName()).append("\n\n");//Append user name
        }

        displayText.append("--------------\n");

        for (Power powers : allUtilityPowerConsumptionList) {//For each power stored in the utility power consumption list
            displayText.append("Power of utilities in kWh:\n");
            displayText.append("Fridge: ").append(String.format("%.1f", powers.getFridgePower())).append(" kWh\n");//Append user fridge power
            displayText.append("Microwave: ").append(String.format("%.1f", powers.getMicrowavePower())).append(" kWh\n");//Append user microwave power
            displayText.append("TV: ").append(String.format("%.1f", powers.getTVPower())).append(" kWh\n");//Append user TV power
            displayText.append("Dishwasher: ").append(String.format("%.1f", powers.getDishwasherPower())).append(" kWh\n");//Append user microwave power
            displayText.append("Lights: ").append(String.format("%.1f", powers.getLightPower())).append(" kWh\n");//Append user light power
            displayText.append("Washer: ").append(String.format("%.1f", powers.getWasherPower())).append(" kWh\n");//Append user washer power
            displayText.append("Dryer: ").append(String.format("%.1f", powers.getDryerPower())).append(" kWh\n");//Append user dryer power
            displayText.append("Total Power: ").append(String.format("%.1f", powers.getTotalPower())).append(" kWh\n");//Append user total power
        }
        displayText.append("--------------\n");

        for (Cost costs : allUtilityCostList) {//For each cost in the utility cost list
            displayText.append("Cost of utilities in $:\n");
            displayText.append("Fridge: ").append(String.format("%.2f", costs.getFridgeCost())).append("$\n");//Append user fridge cost
            displayText.append("Microwave: ").append(String.format("%.2f", costs.getMicrowaveCost())).append("$\n");//Append user microwave cost
            displayText.append("TV: ").append(String.format("%.2f", costs.getTvCost())).append("$\n");//Append user TV cost
            displayText.append("Dishwasher: ").append(String.format("%.2f", costs.getDishwasherCost())).append("$\n");//Append dishwasher cost
            displayText.append("Lights: ").append(String.format("%.2f", costs.getLightsCost())).append("$\n");//Append user light cost
            displayText.append("Washer: ").append(String.format("%.2f", costs.getWasherCost())).append("$\n");//Append user washer cost
            displayText.append("Dryer: ").append(String.format("%.2f", costs.getDryerCost())).append("$\n");//Append user dryer cost

            if (cpsc219p3.energytracker.BooleanController.costBoolean!=null) {//If cost boolean is not null meaning something was stored (More information in BooleanController)
                displayText.append("AC: ").append(String.format("%.2f", costBoolean.getAcCost())).append("$\n");//Append user ac cost
                displayText.append("Heater: ").append(String.format("%.2f", costBoolean.getHeaterCost())).append("$\n");//Append user Heater cost
                displayText.append("Solar: ").append(String.format("%.2f", costBoolean.getSolarCost())).append("$\n");//Append user Solar cost
            }

            displayText.append("Total Cost: ").append(String.format("%.2f", costs.getTotalCost())).append("$\n");//Append user total cost
        }
        display_field.setText(displayText.toString());//Set the display field with the StringBuilder
        status_display.setText("Successfully able to display Summary");
        // Update the status display with success message

        status_display.setTextFill(Color.GREEN);
    }

    /**
     * Compares the user's total every cost with an average
     * @param event The click of the 'Average Consumption' button
     */
    @FXML
    void avg_consumption(ActionEvent event) {
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();//Make reference to ArrayList<Cost> getallutilityCostList()

        StringBuilder sb = new StringBuilder();//Make a new instance of StringBuilder

        for (Cost costs : allUtilityCostList) {//For each cost of each utility
            if (costs.getTotalCost() < 158.73) {//If total cost is less than the average consumption cost
                sb.append( "Total Energy Cost: " + df.format(costs.getTotalCost()) + "\n" +
                        "Wow, great job your total energy cost " + "\n" +
                        "is below the average energy cost");
            } else if (costs.getTotalCost() == 158.73) {//If total cost is equal to the average consumption cost
                sb.append( "Total Energy Cost: " + df.format(costs.getTotalCost()) + "\n" +
                        "Your total energy cost is the same as " + "\n" +
                        "the average energy cost");
            } else if (costs.getTotalCost() > 158.73) {//If total cost is greater than the average consumption cost
                sb.append( "Total Energy Cost: " + df.format(costs.getTotalCost()) + "\n" +
                        "Your total energy cost is now above the " + "\n" +
                        "average energy cost");
            }else{
                sb.append("There are currently no comparisons for your total energy cost");//If user never added in utility cost
            }
        }
        display_field.setText(String.valueOf(sb));//Set the display field with the StringBuilder
        status_display.setText("Displaying Average Energy Consumption");
        status_display.setTextFill(Color.BLACK);
    }

    /**
     * Outputs a list of energy efficiency tips based on user inputted power and totaled cost
     * @param event The click of the 'Energy Efficiency Tips' button
     */
    @FXML
    void energy_efficiency_tips(ActionEvent event) {
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();//Make reference to ArrayList<Cost> getallutilityCostList()
        ArrayList<Power> allUtilityPowerConsumptionList = data.getallutilityPowerConsumptionList();//Make reference to ArrayList<Cost> getallutilityPowerConsumptionList()

        StringBuilder sb = new StringBuilder();//Make a new instance of StringBuilder

        sb.append("Were here to help you save money! The following is a list of tips in budgeting" + "\n" );
        for (Power powers : allUtilityPowerConsumptionList) {//For each utility power
            if (powers.getWasherPower() >= 12.77) {//If washer power is greater than to the average power
                sb.append("- Try using cold water in your washing machines, this way you can save up 75-90% of the energy needed to heat the water" + "\n" );
            }
            if (powers.getDryerPower() >= 13) {//If dryer power is greater than equal to the average power
                sb.append("- To help reduce the amount of energy your dryer is taking make sure to clean your dryer vents every 12 months" + "\n" );
            }
            if (powers.getFridgePower() > 200) {//If fridge power is greater than equal to the average power
                sb.append("- Fridge coils and vents should be regularly cleaned otherwise it could slow down the operating system and exert more energy than needed" + "\n" );
            }
            if (powers.getTVPower() >= 4.55) {//If TV power is greater than equal to the average power
                sb.append("- Did you know by adjusting your TV's display settings you can reduce its energy consumption about 5-20%" + "\n" );
            }
            if (powers.getDishwasherPower() >= 20.98) {//If dishwasher power is greater than equal to the average power
                sb.append("- Only run full loads when using the dishwasher and be sure to not rinse them before" + "\n" );
            }
        }
        for (Cost costs : allUtilityCostList) {//For each utility cost
            if (costs.getAcCost() > 0) {//If user has ac
                sb.append("- Make sure to keep up with regular AC maintenance otherwise it can decrease its efficiency by 5-15%" + "\n");
            }
        }
        display_field.setText(String.valueOf(sb));//Set the display field with the StringBuilder
        status_display.setText("Displaying Energy Efficiency Tips");
        status_display.setTextFill(Color.BLACK);
    }

    /**
     * Outputs seasonal saving tips based on inputted month
     * @param event The click of the 'Seasonal Savings' button
     */
    @FXML
    void seasonalSavings(ActionEvent event) {
        StringBuilder sb = new StringBuilder();//Make a new instance of StringBuilder

        for (String month: Data.getUserMonth()) {//For each given month
            if (month.equals("MARCH") || month.equals("APRIL") || month.equals("MAY") || month.equals("JUNE") ||
                month.equals("JULY") || month.equals("AUGUST")) {
                //String season = Spring/Summer
                sb.append("During the Spring and Summer seasons it is suggested that you:" + "\n" +
                        "- Make sure your home is properly insulated and that there isn't any leaky doors and windows" + "\n" +
                        "- Keep your ceiling fans rotating counter-clockwise to keep cool air pushed down");
            }
            if (month.equals("SEPTEMBER") || month.equals("OCTOBER") || month.equals("NOVEMBER")) {
                //String season = "FALL"
                sb.append("During the Fall season it is suggested that you:" + "\n" +
                        "- Avoid blocking your vents" + "\n" +
                        "- Keep your ceiling fans rotating clockwise at low speed");
            }
            if (month.equals("DECEMBER") || month.equals("JANUARY") || month.equals("FEBRUARY")) {
                //String season = "WINTER"
                sb.append("During the Spring and Summer seasons it is suggested that you:" + "\n" +
                        "- Make sure your home is properly insulated and that there isn't any leaky doors and windows" + "\n" +
                        "- Insulate your to your hot water pipers" + "\n" +
                        "- If you have a fireplace be sure to close the flue damper so no cold air enters");
            }

        }
        display_field.setText(String.valueOf(sb));//Set the display field with the StringBuilder
        status_display.setText("Displaying Seasonal Savings");
        status_display.setTextFill(Color.BLACK);
    }
    /**
     * Loads the energy consumption data from a file.
     * @param event the action event triggered by the user
     */
    @FXML
    void loafFile(ActionEvent event) {
        FileChooser filechooser = new FileChooser();//Make a new instance of FileChooser

        File selectedFile = filechooser.showOpenDialog(stage);//Open dialog for user to select file

        if (selectedFile != null) {//If file is not empty
            try {
                Reader.loadFile(String.valueOf(selectedFile));
                data = Reader.loadFile(selectedFile.getAbsolutePath());//Using Reader method load user selected File

                display_field.setText(data.toString());//Set the text to display all the information in the file
                status_display.setText("Successfully loaded data");
                status_display.setTextFill(Color.GREEN);
            } catch (InputMismatchException | NumberFormatException e) {
                status_display.setText("Invalid file format: " + e.getMessage());
                status_display.setTextFill(Color.RED);
            } catch (Exception e) {
                status_display.setText("Error loading file: " + e.getMessage());
                status_display.setTextFill(Color.RED);
            }
        } else {
            status_display.setText("No File Selected");
            status_display.setTextFill(Color.RED);
        }
    }

    /**
     * Saves the current energy data to a file.
     * @param event the action event triggered by the user
     */
    @FXML
    void saveToFile(ActionEvent event) {
        Writer.writeDataToFile(data);//Using Writer method 'writeDataToFile'

    }
    /**
     * Saves the current energy data to a selected file.
     * @param event the action event triggered by the user
     */
    @FXML
    void saveAs(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        File selectedFile = filechooser.showSaveDialog(stage);//Show a new file save dialog

        try {
            // cpsc219p3.energytracker.data.Data will be saved in a file called Saver.txt within the repository
            //File will only be created once then is edited on top of it
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
            //it is good practice to write stuff to file as you go
            //saves username and reportID
            for (Person person : data.getPersonAndIDList()) {
                writer.write(person.getName() + "\n");
                writer.write(person.getReportID() + "\n");
            }

            for (Power powers : data.getallutilityPowerConsumptionList()) {
                writer.write(Double.toString(powers.getFridgePower()) + "," +
                        Double.toString(powers.getMicrowavePower()) + "," +
                        Double.toString(powers.getTVPower()) + "," +
                        Double.toString(powers.getDishwasherPower()) + "," +
                        Double.toString(powers.getLightPower()) + "," +
                        Double.toString(powers.getWasherPower()) + "," +
                        Double.toString(powers.getDryerPower()) + "," +
                        Double.toString(powers.getAcPower()));
                writer.newLine(); // Move to the next line after writing one record
            }

            // Write Boolean Responses
            writer.write(data.hasAC() ? "true" : "false");
            writer.write(",");
            writer.write(data.hasHeater() ? "true" : "false");
            writer.write(",");
            writer.write(data.hasSolar() ? "true" : "false");
            writer.newLine();


            ArrayList<String> userMonths = Data.getUserMonth();
            for (String month : userMonths) {
                writer.write(month); // write each month followed by a newline
                writer.newLine(); // Move to the next line after writing one record

            }
            status_display.setText("Saved to file");
            status_display.setTextFill(Color.BLACK);

            writer.close();
        } catch (IOException e) {
            status_display.setText("ERROR SAVING TO " + selectedFile);
            status_display.setTextFill(Color.RED);
            System.err.println("Runtime Exception: ERROR SAVING TO " + selectedFile);
        }
    }

    /**
     * Exits the application.
     * @param event the action event triggered by the user
     */
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();//Causes the termination

    }
    /**
     * Displays information about the program.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    void aboutProgram(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("Message");
        alert.setContentText(
                "Author: Marwah Ahmed, Hamza Siddiqui \nEmail: marwah.ahmed@ucalgary.ca, hamza.siddiqui@ucalgary.ca" +
                        " \nVersion: v1.0 \nEnergy tracking program involving household power consumption and associated costs. Features include, how to budget " +
                        "more effectively and average energy comparisons.");
        alert.showAndWait();//Will display the window until user presses 'ok' or x's out

    }

}
