package cpsc219p3.energytracker.p2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import cpsc219p3.energytracker.data.*;
import cpsc219p3.energytracker.util.*;
import cpsc219p3.energytracker.power.*;
import cpsc219p3.energytracker.writer.*;
import cpsc219p3.energytracker.cost.*;


/** *
 *
 * @author @hamza.siddiqui  Hamza Siddiqui, @marwah.ahmed@ucalgary.ca Marwah Ahmed
 * @UCID 30183881 , 30180880
 */

/**
 * The cpsc219p3.energytracker.p2.Menu class provides an interactive command-line interface for users to input and manage energy consumption data.
 * It offers various options like entering personal details, utility usage, and saving or loading data from a file.
 */
public class Menu {
    // Data object to hold all energy tracking information

    private static Data data = new Data();

    // Scanner object to read input from the command line

    private static Scanner scanner = new Scanner(System.in);
    // Entry message to greet users when the program starts

    private static final String entryMessage = """
            Welcome to the energy tracking program!!
                        
            Home Page
                        
            Choose one of the following options:
            \tPress '1' to start energy tracking program
            \tPress '2' to exit the program
            """;
    // Menu options for the user to choose from

    private static final String menuOptions = """
            \tMenu Options
            \t1. Enter a new person
            \t2. Enter desired month
            \t3. Enter power consumption for individual utility items
            \t4. Enter AC, heater and Solar usage
            \t5. Print electricity summary
            \t6. Average Energy Comparison   
            \t7. Energy Efficiency Tips
            \t8. Load a file.
            \t9. Save to a file 
            \t10. To exit the program

            """;
    // Continuation prompt for the user to continue or exit

    private static final String continuation = """
            \tPress '1' to continue with energy tracking program
            \tPress '2' to go back to home page 
            """;

    //we add scanner.nextLine() because after using nextInt, it will read user's number but then will automatically go to an empty line (when u press enter) where ther will be no value.
    //this is the buffer line and when u add another nextInt(), it will read this empty line. So in order to not read this empty line, we do scanner.nextLine() to consume this empty/buffer line and store the value to the place where user will enter the number.
    // Main loop to display the menu and handle user input

    /**
     * Function loops the available menu options, allowing users to choose what option they like until they exit out the program
     */
    public static void menuLoop() {
        System.out.println(entryMessage);

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int optionSelected = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                // Exit loop if user selects to exit the program
                if (optionSelected == 2) {
                    break;
                }

                System.out.println(menuOptions);

                // Read user's choice for menu option
                System.out.print("Enter menu option: ");
                int inputOption = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (inputOption) {
                    case 1:
                        userName(); // Handle entering a new person into the data system
                        break;
                    case 2:
                        usersMonth(); // Option to enter desired month
                        break;
                    case 3:
                        // Call functions for power consumption and store the returned values
                        utilityItemsPowerConsumed(fridgePower(), microwavePower(), tvPower(), dishwasherPower(), lightsPower(), washerPower(), dryerPower());
                        break;
                    case 4:
                        // Collect boolean responses and record them
                        boolean ac = usesAC();
                        boolean heater = usesElectricHeater();
                        boolean solar = installSolar();
                        data.storeBooleanResponse(ac, heater, solar);
                        System.out.println("AC, Heater, and Solar Usage Responses Recorded.");
                        break;
                    case 5:
                        printElectricitySummary(); // Print a summary of electricity usage
                        break;
                    case 6:
                        householdComparison(); // Compare household energy consumption with an average
                        break;
                    case 7:
                        budgetingAdvice(); // Provide budgeting and energy-saving advice
                        break;
                    case 8:
                        loadAFile(); // Load data from a file
                        break;
                    case 9:
                        try {
                            Writer writer = new Writer(); // Create an instance of Writer
                            writer.writeDataToFile(data); // Call the method with the 'data' object
                            System.out.println("Data has been successfully saved to the file.");
                        } catch (RuntimeException e) {
                            System.out.println("An error occurred while writing data to the file: " + e.getMessage());
                        }
                        break;
                    case 10:
                        System.out.println("Exiting the program. Thank you for using the energy tracking program.");
                        return; // Exits the menuLoop method, effectively ending the program


                    default:
                        System.out.printf("Option is not recognized: %d\n", inputOption);
                }

                System.out.println(continuation);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("Thank you for using this program");
    }
    /**
     * Function asks for user's name, then calls menu function to store name along with declared report ID
     */
    private static void userName() {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();


        //Code for generating random numbers is from https://stackoverflow.com/questions/32534601/java-getting-a-random-number-from-100-to-999#:~:text=Random%20random%20%3D%20new%20Random()%3B%20int%20randomNumber%20%3D%20random.,randomNumber%20must%20be%20three%20digit.
        // initialize a Random object somewhere; you should only need one
        Random random = new Random();
        // generate a random integer from 0 to 999,
        int reportID = random.nextInt(1000);

        // Check if name is empty
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty.");

            return;
        }

        // Check if name contains numbers
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println("Error: Name must not contain numbers.");

                return;
            }
        }

        System.out.println("Your name is:\t" + name + "\n\nYour ReportID number is:\t" + reportID + "\n");

        data.storePerson(reportID, name);//Calls .storeperson() with parameters report id and name

    }


    /**
     * Gathers boolean responses from the user about their usage of AC, electric heater, and solar installation.
     * Stores these responses in an array and updates the data object.
     *
     * @return An array of boolean values representing the responses to AC, heater, and solar usage questions.
     */
    public static void allBooleanResponsesGathered() {
        // stores the values being returned by running the function into a variable

        boolean ac = usesAC();
        boolean heater = usesElectricHeater();
        boolean solar = installSolar();

        data.storeBooleanResponse(ac, heater, solar);
        //return allResponsesGathered;
    }

    /**
     * Asks the user if they have installed solar panels at their house and returns the response.
     *
     * @return A boolean value indicating whether the user has installed solar panels.
     */
    public static boolean installSolar() {
        boolean hasSolar = false; // Default to false
        boolean validResponse = false; // Flag to check for valid input

        // Loop until the user provides a valid response
        while (!validResponse) {
            try {
                System.out.println("Please answer the following question with either 'yes' or 'no'");
                System.out.println("Have you installed solar at your house?");
                String answer = scanner.nextLine().trim().toUpperCase();

                if (answer.equals("YES")) {
                    hasSolar = true;
                    validResponse = true; // Set flag to true as we have a valid response
                } else if (answer.equals("NO")) {
                    hasSolar = false;
                    validResponse = true; // Set flag to true as we have a valid response
                } else {
                    // If the response is not "YES" or "NO", prompt the user again
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            } catch (NullPointerException e) {
                System.err.println("Scanner is not initialized properly.");
                break; // Exit the loop and method as we cannot recover from this error here
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                break; // Exit the loop and method as we cannot recover from this error here
            }
        }

        return hasSolar;
    }

    /**
     * Asks the user if they use an electric heater and returns the response.
     *
     * @return A boolean value indicating whether the user uses an electric heater.
     */
    public static boolean usesElectricHeater() {
        boolean hasHeater = false; // Default to false
        boolean validResponse = false; // Flag to check for valid input

        // Loop until the user provides a valid response
        while (!validResponse) {
            try {
                System.out.println("Please answer the following question with either 'yes' or 'no'");
                System.out.println("Do you have an electric heater?");
                String answer = scanner.nextLine().trim().toUpperCase();

                if (answer.equals("YES")) {
                    hasHeater = true;
                    validResponse = true; // Set flag to true as we have a valid response
                } else if (answer.equals("NO")) {
                    hasHeater = false;
                    validResponse = true; // Set flag to true as we have a valid response
                } else {
                    // If the response is not "YES" or "NO", prompt the user again
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            } catch (NullPointerException e) {
                System.err.println("Scanner or data is not initialized properly.");
                break; // Exit the loop and method as we cannot recover from this error here
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                break; // Exit the loop and method as we cannot recover from this error here
            }
        }

        // If we have a valid response, store the heater usage information
        if (validResponse) {
            for (Person person : data.getPersonAndIDList()) {
                Data.storeUserHeater(person.getReportID(), hasHeater);
            }
        }

        return hasHeater;
    }


    /**
     * Asks the user if they have an AC and returns the response.
     *
     * @return A boolean value indicating whether the user has an AC.
     */
    public static boolean usesAC() {
        boolean hasAC = false; // Default to false
        boolean validResponse = false; // Flag to check for valid input

        // Loop until the user provides a valid response
        while (!validResponse) {
            try {
                System.out.println("Please answer the following question with either 'yes' or 'no'");
                System.out.println("Do you have an AC?");
                String answer = scanner.nextLine().trim().toUpperCase();

                if (answer.equals("YES")) {
                    hasAC = true;
                    validResponse = true; // Set flag to true as we have a valid response
                } else if (answer.equals("NO")) {
                    hasAC = false;
                    validResponse = true; // Set flag to true as we have a valid response
                } else {
                    // If the response is not "YES" or "NO", prompt the user again
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            } catch (NullPointerException e) {
                System.err.println("Scanner or data is not initialized properly.");
                break; // Exit the loop and method as we cannot recover from this error here
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                break; // Exit the loop and method as we cannot recover from this error here
            }
        }

        // If we have a valid response, store the AC usage information
        if (validResponse) {
            for (Person person : data.getPersonAndIDList()) {
                Data.storeUserAC(person.getReportID(), hasAC);
            }
        }

        return hasAC;
    }


    private static double fridgePower() {
        // KWh for fridge
        System.out.println("Enter the rated power input of your fridge in Watts:");
        int fridge = 0;
        while (true) {
            try {
                fridge = scanner.nextInt();
                if (fridge < 0) {
                    System.out.println("Invalid input. The power input cannot be negative. Please enter a positive number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        // formula to convert watts to KWh (unit used for calculating electricity prices on enmax)
        // fridge runs approx 8 hrs a day according to google thus 8 hrs a day * 30 days in a month = 240 hrs a month
        double fridgeKWh = (fridge * 240) / 1000.0;
        return fridgeKWh;
    }

    private static double microwavePower() {
        // KWh for microwave
        System.out.println("Enter the rated power input of your microwave in Watts:");
        int microwave = 0;
        while (true) {
            try {
                microwave = scanner.nextInt();
                if (microwave < 0) {
                    System.out.println("Invalid input. The power input cannot be negative. Please enter a positive number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        // Microwave runs 0.25 hrs per day * 30 = 7.5 hrs per month
        double microwaveKWh = (microwave * 7.5) / 1000.0;
        return microwaveKWh;
    }

    private static double tvPower() {
        // KWh for tv
        System.out.println("Enter the rated power input of your TV in Watts:");
        int tv = 0;
        while (true) {
            try {
                tv = scanner.nextInt();
                if (tv < 0) {
                    System.out.println("Invalid input. Please enter a positive number for the power input.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        // Tv runs tvHrs hrs per day * 30
        double tvKWh = (tv * (3 * 30)) / 1000; // Assuming 3 hours/day usage
        return tvKWh;
    }

    private static double dishwasherPower() {
        // KWh for dishwasher
        System.out.println("Enter the rated power input of your dishwasher in Watts:");
        int dishwasher = 0;
        while (true) {
            try {
                dishwasher = scanner.nextInt();
                if (dishwasher < 0) {
                    System.out.println("Invalid input. The power input cannot be negative. Please enter a positive number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        // avg num of cycles ran per week is 5 and avg num of hrs is 2
        int dishwasherCycleHrs = 2;
        int dishwasherRanPerWeek = 5;
        int dishwashermonthlyHrs = ((dishwasherCycleHrs * dishwasherRanPerWeek) * 4);
        // dishwasher runs dishwashermonthlyHrs per month
        double dishwasherKWh = (dishwasher * dishwashermonthlyHrs) / 1000.0;
        return dishwasherKWh;
    }

    private static double lightsPower() {
        // KWh for light
        System.out.println("Enter the rated power input of your light bulbs in Watts:");
        int lights = 0;
        while (true) {
            try {
                lights = scanner.nextInt();
                if (lights < 0) {
                    System.out.println("Invalid input. Please enter a positive number for the power input.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        // avg num of hrs light is run is a house is 12 hrs
        double lightKWh = (lights * (12 * 30)) / 1000; // Assuming 12 hours/day usage
        return lightKWh;
    }
    private static double washerPower() {
        // KWh for washer
        System.out.println("Enter the rated power input of your washer in Watts:");
        int washer = 0;
        while (true) {
            try {
                washer = scanner.nextInt();
                if (washer < 0) {
                    System.out.println("Invalid input. Please enter a positive number for the power input.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        // washer is run 4 times a month and for 1 hr on avg
        double washerKWh = (washer * 4) / 1000; // Assuming 1 hour/use, 4 uses/month
        return washerKWh;
    }

    private static double dryerPower() {
        // KWh for dryer
        System.out.println("Enter the rated power input of your dryer in Watts:");
        int dryer = 0;
        while (true) {
            try {
                dryer = scanner.nextInt();
                if (dryer < 0) {
                    System.out.println("Invalid input. Please enter a positive number for the power input.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        // dryer is run 4 times a month and for 1 hr on avg
        double dryerKWh = (dryer * 4) / 1000; // Assuming 1 hour/use, 4 uses/month
        return dryerKWh;
    }


    // calls the individual functions and also stores it into data class
    private static double[] utilityItemsPowerConsumed(double fridgeKWh, double microwaveKWh, double tvKWh, double dishwasherKWh, double lightKWh, double washerKWh, double dryerKWh) {

        //array filled with all the power consumption in KWh from the user
        double[] allUtiliesPower = new double[8];
        allUtiliesPower[0] = fridgeKWh;
        allUtiliesPower[1] = microwaveKWh;
        allUtiliesPower[2] = tvKWh;
        allUtiliesPower[3] = dishwasherKWh;
        allUtiliesPower[4] = lightKWh;
        allUtiliesPower[5] = washerKWh;
        allUtiliesPower[6] = dryerKWh;

        // store all the KWH values into Power and Cost class where they will do further calculations and arrangments
        data.storeUtilityConsumptionPower(fridgeKWh, microwaveKWh, tvKWh, dishwasherKWh, lightKWh, washerKWh, dryerKWh);
        data.storeAllutilityConsumptionCosts(fridgeKWh, microwaveKWh, tvKWh, dishwasherKWh, lightKWh, washerKWh, dryerKWh);
        return allUtiliesPower;
    }

    public static String month;

    // asks the user for the month and stores it in the data class
    private static void usersMonth() {
        // ArrayList of all months for validation
        ArrayList<String> validMonths = new ArrayList<>(Arrays.asList(
                "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
        ));
        System.out.println("Which month do you want the energy saving's report");

        while (true) {
            month = scanner.nextLine().toUpperCase();

            if (validMonths.contains(month)) {
                System.out.printf("The month of your energy saving's report is %s\n\n", month);
                Data.storeUserMonth(month);
                break;
            } else {
                System.out.println("Invalid month entered. Please enter a valid month name.");
            }
        }
    }


    /**
     * Prints a summary report of electricity consumption and costs to the console.
     * This report includes personal information of the users along with detailed
     * energy usage and costs for each utility. It presents the information in a
     * formatted manner that is easy to read, with each piece of information clearly
     * labeled and presented in a structured layout.
     */
    private static void printElectricitySummary() {
        ArrayList<Person> personAndIDList = data.getPersonAndIDList();
        ArrayList<Power> allUtilityPowerConsumptionList = data.getallutilityPowerConsumptionList();
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();


        System.out.printf("Summary of your Electricity report: \n");
        System.out.println("----------------------------------------------------------\n");
        for (Person entry : personAndIDList) {
            //the for each loop enters everything from personAndIDList into entry object array.
            System.out.printf("%-20s\t%-8s%n", "Report ID", entry.getReportID());
            System.out.printf("%-20s\t%-8s%n", "Name", entry.getName());
            System.out.println("");
        }
        System.out.println("--------------\n");
        for (Power powers : allUtilityPowerConsumptionList) {
            System.out.println("Power of utilities in kWh:\n");

            System.out.printf("Fridge: %.1f kWh, ", powers.getFridgePower());
            System.out.printf("Microwave: %.1f kWh, ", powers.getMicrowavePower());
            System.out.printf("TV: %.1f kWh, ", powers.getTVPower());
            System.out.printf("Dishwasher: %.1f kWh, ", powers.getDishwasherPower());
            System.out.printf("Lights: %.1f kWh, ", powers.getLightPower());
            System.out.printf("Washer: %.1f kWh, ", powers.getWasherPower());
            System.out.printf("Dryer: %.1f kWh%n", powers.getDryerPower());
            System.out.printf("AC: %.1f kWh%n", powers.getAcPower());

            //Displays totalPower
            System.out.printf("-> Total power: %.1f KWh %n%n", powers.getTotalPower());
        }
        System.out.println("--------------\n");
        for (Cost costs : allUtilityCostList) {
            System.out.println("Cost of utilities in $:\n");

            System.out.printf("Fridge: %.1f $, ", costs.getFridgeCost());
            System.out.printf("Microwave: %.1f $, ", costs.getMicrowaveCost());
            System.out.printf("TV: %.1f $, ", costs.getTvCost());
            System.out.printf("Dishwasher: %.1f $, ", costs.getDishwasherCost());
            System.out.printf("Lights: %.1f $, ", costs.getLightsCost());
            System.out.printf("Washer: %.1f $, ", costs.getWasherCost());
            System.out.printf("Dryer: %.1f $%n", costs.getDryerCost());
            System.out.printf("AC: %.1f $%n", costs.getAcCost());


            System.out.printf("-> Total cost: %.1f $ %n%n", costs.getTotalCost());
        }

    }

    /**
     * Compares the household's total energy cost with an average cost and provides a summary.
     * This method iterates over all utility costs, compares each with a predefined average,
     * and appends a corresponding message to a StringBuilder.
     *
     * @return A string summarizing the comparison of the household's energy costs with an average.
     */

    public static String householdComparison() {
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();
        StringBuilder sb = new StringBuilder();
        // Iterate through each cost item in the list

        for (Cost costs : allUtilityCostList) {
            // Compare total cost with the average and append appropriate message to StringBuilder

            if (costs.getTotalCost() < 158.73) {
                sb.append("Total Energy Cost: " + costs.getTotalCost() + "\n" +
                        "Wow, great job your total energy cost " + "\n" +
                        "is below the average energy cost");
            } else if (costs.getTotalCost() == 158.73) {
                sb.append("Total Energy Cost: " + costs.getTotalCost() + "\n" +
                        "Your total energy cost is the same as " + "\n" +
                        "the average energy cost");
            } else if (costs.getTotalCost() > 158.73) {
                sb.append("Total Energy Cost: " + costs.getTotalCost() + "\n" +
                        "Your total energy cost is now above the " + "\n" +
                        "average energy cost");
            } else {
                return "There are currently no comparisons for your total energy cost..";
            }
        }
        System.out.println(sb);// Print the summary
        return String.valueOf(sb);// Return the summary as a string
    }

    /**
     * Provides budgeting advice based on the power consumption of various utilities.
     * This method iterates over the list of power consumptions and provides specific
     * energy-saving tips based on certain threshold values.
     */
    public static void budgetingAdvice() {
        ArrayList<Cost> allUtilityCostList = data.getallutilityCostList();// Retrieve list of all utility costs
        ArrayList<Power> allUtilityPowerConsumptionList = data.getallutilityPowerConsumptionList();// Retrieve list of all utility power consumptions


        StringBuilder sb = new StringBuilder();
        System.out.print("Were here to help you save money! The following is a list of tips in budgeting" + "\n");
        // Iterate through each power item and provide tips based on power consumption

        for (Power powers : allUtilityPowerConsumptionList) {
            if (powers.getWasherPower() >= 12.77) {
                System.out.print("- Try using cold water in your washing machines, this way you can save up 75-90% of the energy needed to heat the water" + "\n");
            }
            if (powers.getDryerPower() >= 13) {
                System.out.print("- To help reduce the amount of energy your dryer is taking make sure to clean your dryer vents every 12 months" + "\n");
            }
            if (powers.getFridgePower() > 200) {
                System.out.print("- Fridge coils and vents should be regularly cleaned otherwise it could slow down the operating system and exert more energy than needed" + "\n");
            }
            if (powers.getTVPower() >= 4.55) {
                System.out.print("- Did you know by adjusting your TV's display settings you can reduce its energy consumption about 5-20%" + "\n");
            }
            if (powers.getDishwasherPower() >= 20.98) {
                System.out.print("- Only run full loads when using the dishwasher and be sure to not rinse them before" + "\n");
            }
        }
        for (Cost costs : allUtilityCostList) {
            if (costs.getAcCost() > 0) {
                System.out.print("- Make sure to keep up with regular AC maintenance otherwise it can decrease its efficiency by 5-15%" + "\n");
            }
        }
    }

    /**
     * Loads data from a specified file.
     * This method prompts the user to enter a filename, then uses a Reader object
     * to load data from the file into the 'data' object, and finally prints the loaded data.
     */
    public static void loadAFile() {
        System.out.println("Enter the filename you want the data to load from: ");// Prompt for filename
        try {
            String filename = scanner.nextLine(); // Read filename from user input
            Reader reader = new Reader(); // Create a Reader object
            data = reader.loadFile(filename); // Load data from the specified file
            System.out.println(data); // Print the loaded data
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}




