package cpsc219p3.energytracker.data;

import cpsc219p3.energytracker.data.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void constructor() {//Test Constructor
        Person person = new Person("Fatima", 123);
        // Asserting that the name and report ID are correctly set by the constructor
        assertEquals("Fatima",person.getName());
        assertEquals(123,person.getReportID());
    }
    @Test
    void getName() {
        // Testing the getName method of the Person class
        Person person = new Person("Fatima", 123);
        // Asserting that the getName method correctly returns the person's name
        String expected = "Fatima";
        String actual = person.getName();
;       assertEquals(expected,actual);
    }

    @Test
    void getReportID() {//Test reportID getter
        Person person = new Person("Fatima", 123);
        // Asserting that the getReportID method correctly returns the person's report ID
        int expected = 123;
        int actual = person.getReportID();
        assertEquals(expected,actual);
    }

    @Test
    void equals(){//Test override equals method
        Person person1 = new Person("Fatima", 123);
        Person person2 = new Person("Jacob", 901);
        assertFalse(person1.equals(person2));
    }

}