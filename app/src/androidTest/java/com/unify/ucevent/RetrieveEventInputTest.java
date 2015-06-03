package com.unify.ucevent;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.app.Activity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by HDK on 5/24/15.
 */
public class RetrieveEventInputTest extends InstrumentationTestCase{

    String title = "Helloo";
    String location = "pool";
    //String cate = "Party";
    String email = "poolparty@gmail.com";

    String description = "play beer pong";
    String contact = "9999999";
    int year = 2004;
    int month = 11; // 0-11
    int day = 31;
    int hourStart = 10;
    int hourEnd = 6;

    int minuteStart = 56;
    int minuteEnd = 30;
    String cate = "category1";

    //Event event = new Event(title, location, description, cate, email);

    RetrieveEventInput retrieveEventInput;

    @Before
    public void setUp() {

        Context context = this.getInstrumentation().getTargetContext().getApplicationContext();
        EditText eTitle = new EditText(context);
        eTitle.setText(title);

        EditText eLocation = new EditText(context);
        eLocation.setText(location);

        EditText eDescription = new EditText(context);
        eDescription.setText(description);

        EditText eContact = new EditText(context);
        eContact.setText(contact);

        DatePicker eDate = new DatePicker(context);
        eDate.updateDate(year, month, day);

        TimePicker e_start_time = new TimePicker(context);
        e_start_time.setCurrentHour(hourStart);
        e_start_time.setCurrentMinute(minuteStart);

        TimePicker e_end_time = new TimePicker(context);
        e_end_time.setCurrentHour(hourEnd);
        e_end_time.setCurrentMinute(minuteEnd);

        //start = new TimePicker();
        //end = new TimePicker();
        Spinner scategory = new Spinner(context);
        scategory.setPrompt(cate);

        System.err.println(eDate.getYear());

        retrieveEventInput = new RetrieveEventInput(eTitle, eDescription,
                eLocation, eContact, eDate, e_start_time, e_end_time,
                scategory);
    }

    @Test
    public void testGetTitle() throws Exception {

        String title = retrieveEventInput.getTitle();
        assertEquals("Helloo", title);

    }

    @Test
    public void testGetDescription() throws Exception {

        String description = retrieveEventInput.getDescription();

        assertEquals("play beer pong", description);

    }

    @Test
    public void testGetLocation() throws Exception {

        String location = retrieveEventInput.getLocation();
        assertEquals("pool", location);
    }

    @Test
    public void testGetContact() throws Exception {

        String contact = retrieveEventInput.getContact();
        assertEquals("poolparty@gmail.com", email);
    }

    @Test
    public void testGetEventMonth() throws Exception {

        int month = retrieveEventInput.getEventMonth();
        assertEquals(11, month);
    }

    @Test
    public void testGetEventDay() throws Exception {

        int day = retrieveEventInput.getEventDay();
        assertEquals(31, day);
    }

    @Test
    public void testGetEventYear() throws Exception {

        int year = retrieveEventInput.getEventYear();
        assertEquals(2004, year);
    }

    @Test
    public void testGetEventStartHour() throws Exception {

        int startHour = retrieveEventInput.getEventStartHour();
        assertEquals(10, startHour);
    }

    @Test
    public void testGetEventStartMinute() throws Exception {

        int startMinute = retrieveEventInput.getEventStartMinute();
        assertEquals(56, startMinute);
    }

    @Test
    public void testGetEventEndHour() throws Exception {

        int endHour = retrieveEventInput.getEventEndHour();
        assertEquals(6, endHour);
    }

    @Test
    public void testGetEventEndMinute() throws Exception {

        int endMinute = retrieveEventInput.getEventEndMinute();
        assertEquals(30, endMinute);
    }

    @Test
    public void testGetCategory() throws Exception {
        String testCategory = retrieveEventInput.getCategory();
        assertEquals("category1", cate);
    }
}