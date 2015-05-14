package com.unify.ucevent;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.parse.*;

import java.util.ArrayList;
import java.util.List;

//added a comment


public class MainActivity extends ListActivity {

    /*
    public void onCreate() {
        // Enables Parse Local Datastore - Connects to UCEvent
        // Login: ssdai@ucsd.edu
        // PW: Cse110Unify

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn", "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF");
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        /* Test Parse SDK
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

    }*/

    private List<String> listvalues;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listvalues = new ArrayList<String>();
        listvalues.add("Android");
        listvalues.add("iOS");
        listvalues.add("Blackberry");
        listvalues.add("Windows Phone");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.event_list_row,
                R.id.listtext, listvalues);
        setListAdapter(myAdapter);
        
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("hi", "bar");
        testObject.saveInBackground();
        //onCreate(); // Call Parse test object
        */
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id){
        super.onListItemClick(list, view, position, id);

        //Do what you want
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void openEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void changeEventList(View view){
        // Change to event list xml/activity
    }

    public void changeMyEventList(View view) {
        // Change to my event list xml/activity
    }

    public void newEvent( View view ) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

}
