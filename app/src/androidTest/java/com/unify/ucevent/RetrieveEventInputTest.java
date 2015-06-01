package com.unify.ucevent;

import android.test.InstrumentationTestCase;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by HDK on 5/24/15.
 */
public class RetrieveEventInputTest extends InstrumentationTestCase{

    EditText title;
    EditText location;
    EditText description;
    EditText contact;
    DatePicker date;
    TimePicker start;
    TimePicker end;
    Spinner category;
    
    RetrieveEventInput retrieveEventInput;

    @Before
    public void setUp() {
        /*
        title = new EditText();
        location = new EditText();
        description = new EditText();
        contact = new EditText();
        date = new DatePicker();
        start = new TimePicker();
        end = new TimePicker();
        category = new Spinner();
        */
        retrieveEventInput = new RetrieveEventInput(title, description,
                            location, contact, date, start, end, category);
    }

    @Test
    public void testGetTitle() throws Exception {

        String title = retrieveEventInput.getTitle();
        Assert.assertEquals(null, title);
    }

    @Test
    public void testGetDescription() throws Exception {

        String description = retrieveEventInput.getDescription();
        Assert.assertEquals(null, description);
    }

    @Test
    public void testGetLocation() throws Exception {

        String location = retrieveEventInput.getLocation();
        Assert.assertEquals("", location);
    }

    @Test
    public void testGetContact() throws Exception {

        String contact = retrieveEventInput.getContact();
        Assert.assertEquals("", contact);
    }

    @Test
    public void testGetEventMonth() throws Exception {

        int month = retrieveEventInput.getEventMonth();
        Assert.assertEquals(0, month);
    }

    @Test
    public void testGetEventDay() throws Exception {

        int day = retrieveEventInput.getEventDay();
        Assert.assertEquals(0, day);
    }

    @Test
    public void testGetEventYear() throws Exception {

        int year = retrieveEventInput.getEventYear();
        Assert.assertEquals(0, year);
    }

    @Test
    public void testGetEventStartHour() throws Exception {

        int startHour = retrieveEventInput.getEventStartHour();
        Assert.assertEquals(0, startHour);
    }

    @Test
    public void testGetEventStartMinute() throws Exception {

        int startMinute = retrieveEventInput.getEventStartMinute();
        Assert.assertEquals(0, startMinute);
    }

    @Test
    public void testGetEventEndHour() throws Exception {

        int endHour = retrieveEventInput.getEventEndHour();
        Assert.assertEquals(0, endHour);
    }

    @Test
    public void testGetEventEndMinute() throws Exception {

        int endMinute = retrieveEventInput.getEventEndMinute();
        Assert.assertEquals(0, endMinute);
    }
}
