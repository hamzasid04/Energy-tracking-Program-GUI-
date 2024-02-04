package cpsc219p3.energytracker.data;

import cpsc219p3.energytracker.data.Data;
import cpsc219p3.energytracker.power.Power;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PowerTest {

    @Test
    void constructor() {
        // Testing the constructor of the Power class
        Power power = new Power(9,8,7,6,5,4,3);
        // Asserting that each appliance's power is correctly set by the constructor
        assertEquals(9,power.getFridgePower());
        assertEquals(8,power.getMicrowavePower());
        assertEquals(7,power.getTVPower());
        assertEquals(6,power.getDishwasherPower());
        assertEquals(5,power.getLightPower());
        assertEquals(4,power.getWasherPower());
        assertEquals(3,power.getDryerPower());



    }
    // Similar methods for getMicrowavePower, getTVPower, getDishwasherPower, getLightPower, getWasherPower, getDryerPower

    @Test
    void getFridgePower() {
        // Testing the getFridgePower method of the Power class

        Power power = new Power(13,14,15,16,17,18,19);
        // Asserting that the getFridgePower method returns the correct power value for the fridge
        double expected = 13;
        double actual = power.getFridgePower();
        assertEquals(expected, actual);

    }
    @Test
    void getMicrowavePower() {
        Power power = new Power(0, 14, 0, 0, 0, 0, 0);
        assertEquals(14, power.getMicrowavePower());
    }

    @Test
    void getTVPower() {
        Power power = new Power(0, 0, 15, 0, 0, 0, 0);
        assertEquals(15, power.getTVPower());
    }

    @Test
    void getDishwasherPower() {
        Power power = new Power(0, 0, 0, 16, 0, 0, 0);
        assertEquals(16, power.getDishwasherPower());
    }

    @Test
    void getLightPower() {
        Power power = new Power(0, 0, 0, 0, 17, 0, 0);
        assertEquals(17, power.getLightPower());
    }

    @Test
    void getWasherPower() {
        Power power = new Power(0, 0, 0, 0, 0, 18, 0);
        assertEquals(18, power.getWasherPower());
    }

    @Test
    void getDryerPower() {
        Power power = new Power(0, 0, 0, 0, 0, 0, 19);
        assertEquals(19, power.getDryerPower());
    }


    @Test
    void getTotalPower() {
        // Testing the getTotalPower method for correct total power calculation
        Power power = new Power(1, 2, 3, 4, 5, 6, 7);
        // Calculating the expected total power and asserting it matches the returned value
        double expectedTotal = 1 + 2 + 3 + 4 + 5 + 6 + 7;
        assertEquals(expectedTotal, power.getTotalPower());
    }


}