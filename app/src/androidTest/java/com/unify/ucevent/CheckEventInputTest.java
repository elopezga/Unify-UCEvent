package com.unify.ucevent;

import android.content.Context;
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
    EditText email;
    DatePicker date;
    TimePicker start;
    TimePicker end;

    String test_title = "";
    String test_location = "";
    String test_contact = "";
    int year = 2011;
    int month = 11;
    int day = 10;
    int start_hr = 3;
    int start_min = 3;
    int end_hr = 2;
    int end_min = 2;

    CheckEventInput checkEventInput;

    @Before
    public void setUp() {
        Context context = this.getInstrumentation().getTargetContext().getApplicationContext();

        // title
        title = new EditText(context);
        title.setText(test_title);
        // location
        location = new EditText(context);
        title.setText(test_location);
        // contact
        email = new EditText(context);
        email.setText(test_contact);
        // date
        date = new DatePicker(context);
        date.updateDate(year, month, day);
        // start
        start = new TimePicker(context);
        start.setCurrentHour(start_hr);
        start.setCurrentMinute(start_min);
        //end
        end = new TimePicker(context);
        end.setCurrentHour(end_hr);
        end.setCurrentMinute(end_min);

        checkEventInput = new CheckEventInput(title, location, email, date, start, end);
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
/*
    @Test
    public void testConfirmContact() throws Exception {
        // Null test
        boolean isContactConfirmed = checkEventInput.confirmContact();
        assertFalse(isContactConfirmed);
    }
*/
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
