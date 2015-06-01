package com.unify.ucevent;

import android.test.InstrumentationTestCase;

import com.parse.Parse;
import com.parse.ParseUser;
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
    //public ParseUser author;
    public String contact;

    String PARSE_APP_ID = "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn";
    String PARSE_CLIENT_KEY = "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF";

    Event event;

    @Before
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
        //author = ParseUser.getCurrentUser();
        contact = "kimhandole@gmail.com";

        event = new Event(title, location, description, category, contact);

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

    /*@Test
    public void testGetAuthor() {
        ParseUser eventAuthor = event.getAuthor();
        assertEquals(author, eventAuthor);
    }*/

    @Test
    public void testGetContact() {
        String eventContact = event.getContact();
        assertEquals(contact, eventContact);
    }

    // Test Setter Methods of Event class
    @Test
    public void testSetTitle() {
        String ttl = "title";
        event.setTitle(ttl);
        assertEquals(ttl, event.getTitle());
    }

    @Test
    public void testSetLocation() {
        String loc = "location";
        event.setLocation(loc);
        assertEquals(loc, event.getLocation());
    }

    @Test
    public void testSetDate() {
        int month = 1;
        int day = 1;
        int year = 1;
        event.setDate(month, day, year);
        assertEquals(month, event.getDateMonth());
        assertEquals(day, event.getDateDay());
        assertEquals(year, event.getDateYear());
    }

    @Test
    public void testSetDateMonth() {
        int month = 2;
        event.setDateMonth(month);
        assertEquals(month, event.getDateMonth());
    }

    @Test
    public void testSetDateDay() {
        int day = 2;
        event.setDateDay(day);
        assertEquals(day, event.getDateDay());
    }

    @Test
    public void testSetDateYear() {
        int year = 2;
        event.setDateYear(year);
        assertEquals(year, event.getDateYear());
    }

    @Test
    public void testSetTimes() {
        int startH = 3;
        int startM = 3;
        int endH = 4;
        int endM = 4;
        event.setTimes(startH, startM, endH, endM);
        assertEquals(startH, event.getStartHour());
        assertEquals(startM, event.getStartMinute());
        assertEquals(endH, event.getEndHour());
        assertEquals(endM, event.getEndMinute());
    }

    @Test
    public void testSetStartHour() {
        int startH = 5;
        event.setStartHour(startH);
        assertEquals(startH, event.getStartHour());
    }

    @Test
    public void testSetStartMinute() {
        int startM = 5;
        event.setStartMinute(startM);
        assertEquals(startM, event.getStartMinute());
    }

    @Test
    public void testSetEndHour() {
        int endH = 6;
        event.setEndHour(endH);
        assertEquals(endH, event.getEndHour());
    }

    @Test
    public void testSetEndMinute() {
        int endM = 6;
        event.setEndMinute(endM);
        assertEquals(endM, event.getEndMinute());
    }

    @Test
    public void testSetNumGoing() {
        int numG = 9;
        event.setNumGoing(numG);
        assertEquals(numG, event.getNumGoing());
    }

    @Test
    public void testSetDescription() {
        String desc = "setDescription";
        event.setDescription(desc);
        assertEquals(desc, event.getDescription());
    }

    @Test
    public void testSetCategory() {
        String cate = "setCategory";
        event.setCategory(cate);
        assertEquals(cate, event.getCategory());
    }

    @Test
    public void testSetContact() {
        String cont = "setContact";
        event.setContact(cont);
        assertEquals(cont, event.getContact());
    }

}
