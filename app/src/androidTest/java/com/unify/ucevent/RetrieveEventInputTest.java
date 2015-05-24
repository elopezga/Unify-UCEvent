package com.unify.ucevent;

import android.test.InstrumentationTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by HDK on 5/24/15.
 */
public class RetrieveEventInputTest extends InstrumentationTestCase{

    RetrieveEventInput retrieveEventInput;

    @Before
    public void setup() {
        retrieveEventInput = new RetrieveEventInput(null, null, null, null,
                                                    null, null, null, null);
    }

    @Test
    public void testGetTitle() throws Exception {

        String title = retrieveEventInput.getTitle();
        Assert.assertEquals("", title);
    }

    @Test
    public void testGetDescription() throws Exception {

        String description = retrieveEventInput.getDescription();
        Assert.assertEquals("", description);
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
