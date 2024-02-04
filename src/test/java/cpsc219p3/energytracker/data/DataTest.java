package cpsc219p3.energytracker.data;

import cpsc219p3.energytracker.power.Power;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    private Data data;
    @Test
    void constructor() {
        Data data = new Data();
        // Test that the personAndIDList is initialized and empty
        assertNotNull(data.getPersonAndIDList(), "personAndIDList should be initialized");
        assertTrue(data.getPersonAndIDList().isEmpty(), "personAndIDList should be empty");

        // Test that allUtilityPowerConsumptionList is initialized and empty
        assertNotNull(data.getallutilityPowerConsumptionList(), "allUtilityPowerConsumptionList should be initialized");
        assertTrue(data.getallutilityPowerConsumptionList().isEmpty(), "allUtilityPowerConsumptionList should be empty");

        // Test that booleanResponses is initialized and empty
        assertNotNull(data.getBooleanResponses(), "booleanResponses should be initialized");
        assertTrue(data.getBooleanResponses().isEmpty(), "booleanResponses should be empty");

        // Test that allUtilityCostsList is initialized and empty
        assertNotNull(data.getallutilityCostList(), "allUtilityCostsList should be initialized");
        assertTrue(data.getallutilityCostList().isEmpty(), "allUtilityCostsList should be empty");

    }


    @BeforeEach
    void setUp() {
        // Setting up a new Data object and clearing static lists before each test
        data = new Data();
        // Clearing static lists to ensure test isolation
        Data.heaterCostList.clear();
        Data.monthList.clear();
        Data.userAcResponse.clear();
        Data.userAc.clear();
        Data.acCostList.clear(); // Clearing the AC cost list
        Data.lookUp.clear();
        // Clear other lists if necessary
    }

    @Test
    void storePerson() {//Test to check if properly stores person
        int reportID = 270;
        String name = "Bobby";
        data.storePerson(reportID, name);
        // Verifying if the person is stored correctly
        boolean found = false;
        for (Person person : data.getPersonAndIDList()) {
            if (person.getName().equals(name) && person.getReportID() == reportID) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Person not stored correctly");
        // Checking if the lookup map contains the correct mapping
        assertEquals(name, Data.lookUp.get(reportID), "Name does not exist in lookUp, not properly stored");
    }
    // Similar test methods for other functionalities like storeUserMonth, getUserMonth, testUtilityConsumption, etc.

    @Test
    void storeUserMonth() {//Test to check if properly stores user month
        String month = "JUNE";
        Data.storeUserMonth(month);

        assertEquals(1, Data.monthList.size(), "Size of month list is not 1");
        assertTrue(Data.monthList.contains(month), "Given month has not been properly stored in month list");
    }

    @Test
    void getUserMonth() {
        // Testing retrieval of stored user-selected month
        String month = "JUNE";
        Data.storeUserMonth(month);
        // Creating expected ArrayList containing the stored month for comparison
        ArrayList<String> expected = new ArrayList<>();
        expected.add(month);
        // Verifying if the retrieved month list matches the expected list
        ArrayList<String> actual = Data.getUserMonth();

        assertEquals(expected, actual, "Cannot retrieve month");
    }

    @Test
    void testUtilityConsumption() {//Check to see it properly stores utility power consumptions
        double[] expected = {20.9, 30.9, 40.8, 900.7, 87.8, 56.0, 37.0};
        double[] actual = Data.utilityConsumption(expected);
        // Verifying if the method returns the correct array of utility consumption values
        assertArrayEquals(expected, actual, "Utility consumption list returns incorrect list");
    }

    @Test
    void storeUserAC() {
        // Testing the storeUserAC method to ensure correct storage of AC usage data
        boolean hasAc = true;
        int reportId = 123;

        Data.storeUserAC(reportId, hasAc);
        // Checking if the AC data is correctly stored in the userAc map

        assertEquals(1, Data.userAc.size(), "Size of stored AC list is not 1");
        assertTrue(Data.userAc.containsKey(reportId), "Report ID does not exist, not properly stored");
        assertTrue(Data.userAc.get(reportId), "User's AC response does not exist, not properly stored");
    }

    @Test
    void getUserAc() {//Getter for user Ac
        boolean hasAc = true;
        int reportId = 123;

        Data.storeUserAC(reportId, hasAc);
        // Preparing the expected result list for comparison

        ArrayList<Boolean> expected = new ArrayList<>();
        expected.add(hasAc);//Add user ac answer
        ArrayList<Boolean> actual = Data.getUserAc();

        assertEquals(expected, actual, "Contents of AC response does not match expected");
    }

    @Test
    void storeAcCost() {
        // Testing the storeAcCost method to ensure it correctly stores AC cost data
        double cost = 278.02;

        Data.storeAcCost(cost);
        // Verifying if the AC cost is correctly stored in the acCostList
        assertEquals(1, Data.acCostList.size(), "AC cost list not size 1");
        assertTrue(Data.acCostList.contains(cost), "AC cost does not exist, not properly stored");
    }

    @Test
    void getAcCost() {//Getter ac cost
        double cost = 900.98;
        Data.storeAcCost(cost);
        // Preparing the expected list for comparison
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(cost);//Add ac cost

        ArrayList<Double> actual = Data.getAcCost();
        assertEquals(expected, actual, "Contents of AC cost do not match expected");
    }

    @Test
    void storeHeaterCost() {//Store heater cost
        double cost = 36.03;
        // Testing the storeHeaterCost method for correct storage of heater cost data
        Data.storeHeaterCost(cost);
        // Checking if the heater cost is correctly stored in the heaterCostList
        assertEquals(1, Data.heaterCostList.size(), "Heater cost list not size 1");
        assertTrue(Data.heaterCostList.contains(cost), "Heater cost does not exist, not properly stored");
    }

    @Test
    void getHeaterCost() {//Getter for heater cost
        double cost = 34.00;
        Data.storeHeaterCost(cost);
        // Preparing the expected list for comparison
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(cost);//Store heater cost

        ArrayList<Double> actual = Data.getHeaterCost();
        assertEquals(expected, actual, "Contents of heater cost do not match expected");
    }
}
