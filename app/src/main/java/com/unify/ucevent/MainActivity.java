package com.unify.ucevent;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private List<String> listvalues = new ArrayList<String>();
    private List<String> myEventListValues = new ArrayList<String>();
    private ArrayList<Event> myEvents = new ArrayList<Event>();
    private ArrayList<Event> allEvents = new ArrayList<Event>();
    private MainActivity THIS = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //getEvents();


        /*final Button button = (Button) findViewById(R.id.new_event);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                updateListView(THIS);
            }
        });*/

        /*
        listvalues = new ArrayList<String>();
        listvalues.add("Android");
        listvalues.add("iOS");
        listvalues.add("Blackberry");
        listvalues.add("Windows Phone");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.event_list_row,
                R.id.listtext, listvalues);
        setListAdapter(myAdapter);*/
        
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

    public void getEvents(){

        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.whereGreaterThanOrEqualTo("NumGoing", 0);
        query.findInBackground(new FindCallback<Event>() {
            public void done(List<Event> events, ParseException e) {
                // Note: Takes a while to retrieve data. This function will run when
                // it is done retrieving data

                if (e != null) {
                    Log.d("Query Error", "Something went wrong with PARSE");
                }

                Globals.EventList.clear();
                listvalues.clear();

                for (Event ev : events) {
                    // See if this works; otherwise create new Event each time and call fillFromDB
                    // then add

                    Globals.EventList.add(ev);
                    allEvents.add(ev);
                    //Log.d("Object Found: ", ev.getString("Title"));
                }

                for (Event ev : Globals.EventList) {
                    listvalues.add(ev.getString("Title"));
                }


                /*
                Log.d("Query", obj.getString("Title")); // USE THIS not Event class methods

                //titleText.setText(obj.getString("Title"));
                someEvent.fillFromDB(obj);

                titleText.setText(someEvent.getTitle());*/


            }
        });

    }

    public void getMyEvents(){

        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        //query.whereGreaterThanOrEqualTo("NumGoing", 0);
        query.whereEqualTo("Author", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Event>() {
            public void done(List<Event> events, ParseException e) {
                // Note: Takes a while to retrieve data. This function will run when
                // it is done retrieving data

                if (e != null) {
                    Log.d("Query Error", "Something went wrong with PARSE");
                } else {

                    Globals.MyEventList.clear();
                    myEventListValues.clear();

                    for (Event ev : events) {
                        // See if this works; otherwise create new Event each time and call fillFromDB
                        // then add

                        Globals.MyEventList.add(ev);
                        myEvents.add(ev);
                        //Log.d("Object Found: ", ev.getString("Title"));
                    }

                    for (Event ev : Globals.MyEventList) {
                        myEventListValues.add(ev.getString("Title"));
                    }

                }


            }
        });

    }


    /*public void updateListView( MainActivity th ){
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(th, R.layout.event_list_row,
                R.id.name_of_event, listvalues);
        setListAdapter(myAdapter);
    }*/

    public void updateListView(View view){
        getEvents();
        /*ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.event_list_row,
                R.id.name_of_event, listvalues);*/
        ArrayAdapter myAdapter = new EventAdapter(this, R.layout.event_list_row, allEvents);
        setListAdapter(myAdapter);
    }

    public void updateMyListView(View view) {
        getMyEvents();
        /*ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.event_list_row,
                R.id.name_of_event, myEventListValues);*/
        ArrayAdapter myAdapter = new EventAdapter(this, R.layout.event_list_row, myEvents);
        setListAdapter(myAdapter);
    }

    public void goTosettings(View view){
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }

    public void goToNewEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void viewEvent(View view) {
        Intent intent = new Intent(this, Event_Detail.class);
        startActivity(intent);
    }
}
