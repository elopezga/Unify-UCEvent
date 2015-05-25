package com.unify.ucevent;

import android.test.InstrumentationTestCase;

import com.parse.Parse;
import com.parse.ParseUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by HDK on 5/24/15.
 */
public class EventTest extends InstrumentationTestCase {

    public String title;
    public String location;
    public int dateMonth;
    public int dateDay;
    public int dateYear;
    public int startHour;
    public int startMinute;
    public int endHour;
    public int endMinute;
    public int numGoing;
    public String description;
    public String category;
    public ParseUser author;
    public String contact;

    String PARSE_APP_ID = "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn";
    String PARSE_CLIENT_KEY = "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF";

    Event event;

    @BeforeWor
    public void setUp() {
        title = "Customer meeting";
        location = "UCSD, Center 205";
        dateMonth = 5;
        dateDay = 25;
        dateYear = 2015;
        startHour = 7;
        startMinute = 30;
        endHour = 8;
        endMinute = 0;
        numGoing = 3;
        description = "Unify customer meeting";
        category = "Education";
        author = ParseUser.getCurrentUser();
        contact = "kimhandole@gmail.com";

        event = new Event(title, location, description, category, contact);
        Parse.initialize(null, PARSE_APP_ID, PARSE_CLIENT_KEY);
    }

    // Test getter methods of Event class
    @Test
    public void testGetTitle() {
        String eventTitle = event.getTitle();
        assertEquals(title, eventTitle);
    }

    @Test
    public void testGetLocation() {
        String eventLocation = event.getLocation();
        assertEquals(location, eventLocation);
    }

    @Test
    public void testGetDateMonth() {
        int eventDateMonth = event.getDateMonth();
        assertEquals(0, eventDateMonth);    // event.dateMonth initially 0
    }

    @Test
    public void testGetDateDay() {
        int eventDateDay = event.getDateDay();
        assertEquals(0, eventDateDay);      // event.dateDay initially 0
    }

    @Test
    public void testGetDateYear() {
        int eventDateYear = event.getDateYear();
        assertEquals(0, eventDateYear);     // event.dateYear initially 0
    }

    @Test
    public void testGetStartHour() {
        int eventStartHour = event.getStartHour();
        assertEquals(0, eventStartHour);    // event.startHour initially 0
    }

    @Test
    public void testGetStartMinute() {
        int eventStartMinute = event.getStartMinute();
        assertEquals(0, eventStartMinute);  // event.startMinute initially 0
    }

    @Test
    public void testGetEndHour() {
        int eventEndHour = event.getEndHour();
        assertEquals(0, eventEndHour);     // event.endHour initially 0
    }

    @Test
    public void testGetEndMinute() {
        int eventEndMinute = event.getEndMinute();
        assertEquals(0, eventEndMinute);   // event.endMinute initially 0
    }

    @Test
    public void testGetNumGoing() {
        int eventNumGoing = event.getNumGoing();
        assertEquals(0, eventNumGoing);   // event.numGoing initially -1
    }

    @Test
    public void testGetDescription() {
        String eventDescription = event.getDescription();
        assertEquals(description, eventDescription);
    }

    @Test
    public void testGetCategory() {
        String eventCategory = event.getCategory();
        assertEquals(category, eventCategory);
    }

    @Test
    public void testGetAuthor() {
        ParseUser eventAuthor = event.getAuthor();
        assertEquals(author, eventAuthor);
    }

    @Test
    public void testGetContact() {
        String eventContact = event.getContact();
        assertEquals(contact, eventContact);
    }

    // Test Setter Methods of Event class
    @Test
    public void testSetTitle() {

    }

    @Test
    public void testSetLocation() {

    }

    @Test
    public void testSetDate() {

    }

    @Test
    public void testSetDateMonth() {

    }

    @Test
    public void testSetDateDay() {

    }

    @Test
    public void testSetDateYear() {

    }

    @Test
    public void testSetTimes() {

    }

    @Test
    public void testSetStartHour() {

    }

    @Test
    public void testSetStartMinute() {

    }

    @Test
    public void testSetEndHour() {

    }

    @Test
    public void testSetEndMinute() {

    }

    @Test
    public void testSetNumGoing() {

    }

    @Test
    public void testSetDescription() {

    }

    @Test
    public void testSetCategory() {

    }

    @Test
    public void testSetContact() {

    }

}
