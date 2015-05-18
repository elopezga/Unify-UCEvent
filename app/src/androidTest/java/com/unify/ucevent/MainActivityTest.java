package com.unify.ucevent;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by HDK on 5/17/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
}
