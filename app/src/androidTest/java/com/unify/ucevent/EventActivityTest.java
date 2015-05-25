package com.unify.ucevent;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.view.View;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ashishpokharel on 5/25/15.
 */
public class EventActivityTest extends InstrumentationTestCase{

    EventActivity eventActivity;
    View view;

    Event event;

    String PARSE_APP_ID = "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn";
    String PARSE_CLIENT_KEY = "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF";

    @Before
    public void setup() {
        eventActivity = new EventActivity();
    }
    @Test
    public void testUploadData() throws Exception {
        eventActivity.uploadData(event);
        //assertEquals(, event);
    }

    @Test
    public void testOpenEditEvent() throws Exception {
        eventActivity.openEditEvent(view);
    }

    @Test
    public void testSubmit() throws Exception {
        eventActivity.submit(view);
    }
}