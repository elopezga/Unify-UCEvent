package com.unify.ucevent;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ashishpokharel on 5/25/15.
 */
public class EventAdapterTest {
    public int position;
    public View convertView;
    public ViewGroup parent;
    EventAdapter eventAdapter;
    @Before
    public void setup() {
        position = 5;
        }


    @Test
    public void testGetView() throws Exception {
        assertNotNull(String.valueOf(eventAdapter.getView(position, convertView, parent)), true);
    }
}