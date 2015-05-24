package com.unify.ucevent;

import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by HDK on 5/17/15.
 */
public class CheckEventInputTest extends InstrumentationTestCase {

    CheckEventInput checkEventInput;

    @Before
    public void setup() {
        checkEventInput = new CheckEventInput(null, null, null, null, null,
                                              null); //Empty input
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
