package cpsc219p3.energytracker.data; /** *
 *
 * @author @hamza.siddiqui  Hamza Siddiqui, @marwah.ahmed@ucalgary.ca Marwah Ahmed
 * @UCID 30183881 , 30180880
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import cpsc219p3.energytracker.data.*;
import cpsc219p3.energytracker.cost.*;
import cpsc219p3.energytracker.power.*;

import cpsc219p3.energytracker.EnergyController.*;

/**
 * This class represents the data structure for storing information related to users' power consumption, costs, and other utilities.
 * It includes methods for storing and retrieving data about users, their appliances, and the corresponding costs.
 */
public class Data {
    public static final HashMap<Integer, Boolean> userAc= new HashMap<Integer, Boolean>();
    public static final HashMap<Integer, Boolean> userHeater= new HashMap<Integer, Boolean>();
    public static final ArrayList<Boolean> userAcResponse = new ArrayList<Boolean>();
    public static final ArrayList<Boolean> userHeaterResponse = new ArrayList<Boolean>();
    public static final ArrayList<String> monthList = new ArrayList<>();
    public static final ArrayList<Integer> reportIdList = new ArrayList<Integer>();
    public static final ArrayList<Double> acCostList = new ArrayList<Double>();
    public static final ArrayList<Double> heaterCostList = new ArrayList<Double>();
    public static final HashMap<Integer, String> lookUp = new HashMap<>();

    private final ArrayList<Person> personAndIDList;
    private static ArrayList<Power> allUtilityPowerConsumptionList;
    private static ArrayList<Cost> allUtilityCostsList;
    private final ArrayList<boolean[]> booleanResponses;
    private boolean hasAC;
    private boolean hasHeater;
    private boolean hasSolar;



    /**
     * Overrides the toString method to display information
     * @return string consisting of person info, utility info, ac, heater and solar info
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name and report ID : ").append(personAndIDList).append('\n');
        sb.append("Powers : ").append(allUtilityPowerConsumptionList).append('\n');

        sb.append("Selected Month: ").append(monthList).append('\n');
        // Correctly printing booleanResponses
        sb.append("booleanResponses=[");
        sb.append("AC: ").append(this.hasAC).append(", ");
        sb.append("Heater: ").append(this.hasHeater).append(", ");
        sb.append("Solar: ").append(this.hasSolar).append('\n');


        sb.append("]");

        sb.append('}');
        return sb.toString();
    }

    /**
     * Constructs a new cpsc219p3.energytracker.data.Data object with empty lists for storing information.
     */
    public Data() {
        this.personAndIDList = new ArrayList<Person>();
        this.allUtilityPowerConsumptionList = new ArrayList<Power>();
        this.booleanResponses = new ArrayList<>();
        this.allUtilityCostsList = new ArrayList<>();

    }

    /**
     * tores the person name and the reportID associated with it into reportIDIndex
     * @param reportID the integer report ID
     * @param name the string name of user
     */
    public void storePerson(int reportID, String name) {
        Person newPerson = new Person(name,reportID);
        //.add() adds the element into a set of collection (Hashset, ArrayList....) if not already present in the set from before.
        personAndIDList.add(newPerson);
        lookUp.put(reportID, name); // lookup puts in key for value inside hashmap
        reportIdList.add(reportID); //Puts all the report id's inside (loop through those and then use those integers as index to loop through all stored names

    }

    /**
     * Retrieves the list of people and their IDs.
     * @return A list of containing a cpsc219p3.energytracker.data.Person objects containing name and report ID information.
     */
    public ArrayList<Person> getPersonAndIDList(){
        //basically will return the list storing all the name and ID in there back.
        return personAndIDList;
    }

    /**
     * Checking if utility consumption is correctly stored (used only for DataTest)
     * @param allUtilitiesPower double array of all utilities power
     * @return double array consisting of all the stored utility power
     */

    public static double[] utilityConsumption(double[] allUtilitiesPower) {
        return allUtilitiesPower;
    }


    /**
     * Stores the monthly utility power consumption for different appliances.
     * @param fridgeKWh The power consumption of the fridge in kWh.
     * @param microwaveKWh The power consumption of the microwave in kWh.
     * @param tvKWh The power consumption of the TV in kWh.
     * @param dishwasherKWh The power consumption of the dishwasher in kWh.
     * @param lightKWh The power consumption of the lights in kWh.
     * @param washerKWh The power consumption of the washer in kWh.
     * @param dryerKWh The power consumption of the dryer in kWh.
     *
     */
    public void storeUtilityConsumptionPower(double fridgeKWh, double microwaveKWh, double tvKWh, double dishwasherKWh, double lightKWh, double washerKWh, double dryerKWh) {
        // stores the individual utilities power in KWh
        Power storeUtilitiesPowers = new Power(fridgeKWh,  microwaveKWh,  tvKWh,  dishwasherKWh,  lightKWh,  washerKWh,  dryerKWh);//Make new instance of Power

        allUtilityPowerConsumptionList.add(storeUtilitiesPowers);//Add power consumption to data list

    };

    /**
     * Stores utility consumption powers and converts it into cost in Cost class
     * @param fridgeKWh The power consumption of the fridge in kWh.
     * @param microwaveKWh The power consumption of the microwave in kWh.
     * @param tvKWh The power consumption of the TV in kWh.
     * @param dishwasherKWh The power consumption of the dishwasher in kWh.
     * @param lightKWh The power consumption of the lights in kWh.
     * @param washerKWh The power consumption of the washer in kWh.
     * @param dryerKWh The power consumption of the dryer in kWh.
     */

    public void storeAllutilityConsumptionCosts(double fridgeKWh, double microwaveKWh, double tvKWh, double dishwasherKWh, double lightKWh, double washerKWh, double dryerKWh) {
        // takes in the power results from the user and converts how much the power used would cost
        //acCost is calculated differently than all the other utilities here

        Cost storeUtilitiesCosts = new Cost(fridgeKWh,  microwaveKWh,  tvKWh,  dishwasherKWh,  lightKWh,  washerKWh,  dryerKWh);//Make new instance of Cost

        allUtilityCostsList.add(storeUtilitiesCosts);//Add cost into utility cost list in data

    }

    /**
     * Stores user month
     * @param month String month taken by user input
     */
    public static void storeUserMonth(String month) {
        monthList.add(month);//Add user month in list
    }

    /**
     * Getter for user month
     * @return The string user month
     */
    public static ArrayList<String> getUserMonth(){
        return monthList;
    }

    /**
     * Store user ac based on report ID and answer to (do you have an ac?)
     * @param reportID The integer report ID given to the user
     * @param hasAC The boolean true or false if the user has ac
     */
    public static void storeUserAC(int reportID, boolean hasAC ) {
        userAc.put(reportID, hasAC);//Put the information into hashmap
        userAcResponse.add(hasAC);//Add user ac response
    }

    /**
     * Getter for user ac
     * @return boolean true or false if the user has ac
     */
    public static ArrayList<Boolean> getUserAc(){
        return userAcResponse;
    }

    /**
     * Store User heater based on reportID and answer to (do you have an heater?)
     * @param reportId Integer reportID given to the user
     * @param hasHeater Boolean true or false if they have heater
     */
    public static void storeUserHeater(int reportId, boolean hasHeater){
        userHeater.put(reportId, hasHeater);//Put response into hashmap
        userHeaterResponse.add(hasHeater);//Add response into boolean array list
    }

    /**
     * Stores all the boolean responses (AC, Heater and Solar)
     * @param allResponsesGathered The boolean array consisting of all the boolean answers
     * @return boolean array of all the user energy usage answers
     */
    public static boolean[] storeAllBooleanResponseGathered(boolean[] allResponsesGathered){
        return allResponsesGathered;
    }

    /**
     * Store boolean responses (AC, Heater and Solar)
     * @param AC Does the user have an ac = boolean response
     * @param heater Does the user have heater = boolean response
     * @param solar Does the user have solar = boolean response
     */
    public void storeBooleanResponse(boolean AC, boolean heater, boolean solar) {
        // Store the boolean values in a way that they can be accessed when saving the file
        this.hasAC = AC;
        this.hasHeater = heater;
        this.hasSolar = solar;
    }

    /**
     * Retrieves the list of boolean responses gathered.
     * @return A list of boolean arrays containing responses.
     */
    public ArrayList<boolean[]> getBooleanResponses(){
        return booleanResponses;
    }

    /**
     * Stores Ac Cost
     * @param acCost double ac cost
     */
    public static void storeAcCost(double acCost){
        acCostList.add(acCost);
    }

    /**
     * Getter for ac cost
     * @return Double array list of all ac costs
     */
    public static ArrayList<Double> getAcCost(){
        return acCostList;
    }

    /**
     * Stores user heater cost
     * @param heaterCost The amount for using heater
     */
    public static void storeHeaterCost(double heaterCost) {
        heaterCostList.add(heaterCost);//Add user responses
    }

    /**
     * Getter for Heater Cost
     * @return Double array list of all the heat costs
     */
    public static ArrayList<Double> getHeaterCost(){
        return heaterCostList;
    }

    /**
     * Getter for all utility power consumption
     * @return Power Array list of all the instance of  Power
     */
    public ArrayList<Power> getallutilityPowerConsumptionList() {
        return allUtilityPowerConsumptionList;
    }

    /**
     * Getter for utility costs
     * @return Cost array list of all the instance of Cost
     */
    public ArrayList<Cost> getallutilityCostList() {
        return allUtilityCostsList;
    }

    /**
     * Does user have an Ac
     * @return The boolean response true or false
     */
    public boolean hasAC() {
        return hasAC;
    }

    /**
     * Does the user have a heater
     * @return The boolean response true or false
     */
    public boolean hasHeater() {
        return hasHeater;
    }

    /**
     * Does the user have solar
     * @return The boolean response true or false
     */
    public boolean hasSolar() {
        return hasSolar;
    }

}


