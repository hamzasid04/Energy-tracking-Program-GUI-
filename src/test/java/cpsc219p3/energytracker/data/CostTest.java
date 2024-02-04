package cpsc219p3.energytracker.data;

import cpsc219p3.energytracker.cost.Cost;
import cpsc219p3.energytracker.data.Data;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CostTest {

    @Test
    public void constructor() {
        // Create a Cost object with some sample values to test the constructor
        Cost cost = new Cost(100, 50, 75, 80, 60, 90, 110, 120, 130, 140);

        // Calculate the expected costs for each appliance based on a predefined rate and assert the correctness
        double expectedTotalCost = 582.26 ;
        assertEquals(100*12.79/100,cost.getFridgeCost());
        assertEquals(50*12.79/100,cost.getMicrowaveCost());
        assertEquals(75*12.79/100,cost.getTvCost());
        assertEquals(80*12.79/100,cost.getDishwasherCost());
        assertEquals(60*12.79/100,cost.getLightsCost());
        assertEquals(90*12.79/100,cost.getWasherCost());
        assertEquals(110*12.79/100,cost.getDryerCost());
        //assertEquals(2,power.getAcPower());

        // Calculate expected total cost and assert it
        assertEquals(expectedTotalCost, cost.getTotalCost(), 0.01);
    }

    @Test
    void hashcode() {
        // Testing the hashCode method for consistency
        // Create two identical Cost objects
        Cost cost = new Cost(10,11,12,13,14,15,16);
        Cost cost1 = new Cost(10,11,12,13,14,15,16);
        int one = cost.hashCode();
        int two = cost1.hashCode();
        // Assert that identical objects have the same hashCode

        assertEquals(one,two);
    }

    // Individual tests for getting cost of each appliance
    // The pattern is similar for each appliance: create a Cost object, calculate the expected cost, and assert

    @Test
    void getFridgeCost() {

        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((1.0*12.79)/100);
        double actual = cost.getFridgeCost();
        assertEquals(expected, actual);

    }
    // Similar methods for getMicrowaveCost, getTvCost, getDishwasherCost, getLightsCost, getWasherCost, getDryerCost
    @Test
    void getMicrowaveCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((2.0*12.79)/100);
        double actual = cost.getMicrowaveCost();
        assertEquals(expected, actual);
    }

    @Test
    void getTvCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((3.0*12.79)/100);
        double actual = cost.getTvCost();
        assertEquals(expected, actual);
    }

    @Test
    void getDishwasherCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((4.0*12.79)/100);
        double actual = cost.getDishwasherCost();
        assertEquals(expected, actual);
    }

    @Test
    void getLightsCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((5.0*12.79)/100);
        double actual = cost.getLightsCost();
        assertEquals(expected, actual);
    }

    @Test
    void getWasherCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((6.0*12.79)/100);
        double actual = cost.getWasherCost();
        assertEquals(expected, actual);
    }

    @Test
    void getDryerCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((7.0*12.79)/100);
        double actual = cost.getDryerCost();
        assertEquals(expected, actual);
    }


    @Test
        // Test the total cost calculation by summing individual costs

    void getTotalCost() {
        Cost cost = new Cost(1.0,2.0,3.0,4.0,5.0,6.0,7.0);
        double expected = ((1.0*12.79)/100)+ ((2.0*12.79)/100)+((3.0*12.79)/100)+ ((4.0*12.79)/100)+((5.0*12.79)/100)+((6.0*12.79)/100)+((7.0*12.79)/100);
        double actual = cost.getTotalCost();
        assertEquals(expected, actual, 0.001); // Using a small delta for floating point comparison
    }


}