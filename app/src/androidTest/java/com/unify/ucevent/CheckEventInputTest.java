package com.unify.ucevent;

import android.test.InstrumentationTestCase;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by HDK on 5/17/15.
 */
public class CheckEventInputTest extends InstrumentationTestCase {

    EditText title;
    EditText location;
    EditText contact;
    DatePicker date;
    TimePicker start;
    TimePicker end;

    CheckEventInput checkEventInput;

    @Before
    public void setUp() {
        /*
        title = new EditText();
        location = new EditText();
        contact = new EditText();
        date = new DatePicker();
        start = new TimePicker();
        end = new TimePicker();
        */
        checkEventInput = new CheckEventInput(title, location, contact, date, start, end);
    }

    @Test
    public void testConfirmTitle() throws Exception {
        // Null test
        boolean isTitleConfirmed = checkEventInput.confirmTitle();
        assertFalse(isTitleConfirmed);
    }

    @Test
    public void testConfirmLocation() throws Exception {
        // Null test
        boolean isLocationConfirmed = checkEventInput.confirmLocation();
        assertFalse(isLocationConfirmed);
    }

    @Test
    public void testConfirmContact() throws Exception {
        // Null test
        boolean isContactConfirmed = checkEventInput.confirmContact();
        assertFalse(isContactConfirmed);
    }

    @Test
    public void testConfirmDate() throws Exception {
        // Null test
        boolean isDateConfirmed = checkEventInput.confirmDate();
        assertFalse(isDateConfirmed);
    }

    @Test
    public void testConfirmStartTime() throws Exception {
        // Null test
        boolean isStartTimeConfirmed = checkEventInput.confirmStartTime();
        assertFalse(isStartTimeConfirmed);
    }

    @Test
    public void testConfirmEndTime() throws Exception {
        // Null test
        boolean isEndTimeConfirmed = checkEventInput.confirmEndTime();
        assertFalse(isEndTimeConfirmed);
    }
}
