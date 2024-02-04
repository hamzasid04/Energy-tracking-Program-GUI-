package cpsc219p3.energytracker.data;

import java.util.Random;
import cpsc219p3.energytracker.data.*;
import cpsc219p3.energytracker.EnergyController.*;


public class Person {
    /**
     * The cpsc219p3.energytracker.data.Person class represents an individual with a unique name and report ID.
     */
    private String name;
    Random random = new Random();
    // already generates random number from the menu class
    // only the number that was generated is being stored here
    private final int reportID ;// random.nextInt(1000);

    /**
     * Constructs a new cpsc219p3.energytracker.data.Person object with a given name and report ID.
     *
     * @param name     The name of the person.
     * @param reportID The report ID associated with the person.
     */
    public Person(String name, int reportID ) {
        this.name = name;
        this.reportID = reportID;
    }
    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Retrieves the report ID of the person.
     *
     * @return The report ID of the person.
     */
    public int getReportID() {
        return this.reportID;
    }

    /**
     * Checks to see if two objects are the same
     * @param o Object your checking for
     * @return boolean: true they equal, false they do not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return reportID == person.reportID;//Checking to see if existing person reportID = make sure nobody gets the same report ID
    }

    /**
     * Calculates hashcode based on reportID for object
     * @return Integer report ID
     */
    @Override
    public int hashCode() {
        return reportID;
    }

    /**
     * To string to print out name and report ID
     * @return string consisting of person name and report ID
     */
    @Override
    public String toString() {
        return "" +
                "name='" + name + '\n' +
                "Report ID='" + reportID + '\n'
                ;
    }
}
