package com.unify.ucevent;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.parse.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    private List<String> listvalues = new ArrayList<String>();
    private List<String> myEventListValues = new ArrayList<String>();
    private ArrayList<Event> myEvents = new ArrayList<Event>();
    private ArrayList<Event> allEvents = new ArrayList<Event>();
    private MainActivity THIS = this;
    private ArrayAdapter myAdapter;
    public final int requestEdit = 1;
    public final int requestNew = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // updateMyListView(getCurrentFocus());
        updateListView(getCurrentFocus());
        Globals.CurrList = Globals.EventList;
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
    }

    public void openEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }*/


    // Get all events within the criteria
    public void getEvents(){

        /*
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
                allEvents.clear();
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
                titleText.setText(someEvent.getTitle());
            }
        });*/

        // Cleaner version of querying!
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.whereGreaterThanOrEqualTo("NumGoing", 0);
        query.orderByDescending("NumGoing");

        try {
            Globals.EventList = (ArrayList)query.find();
        }catch( ParseException e ){
            // Exception handle
        }

        /* testing purpose
        for( Event event : Globals.EventList){

        } */
    }

    // Get user's posted events
    public void getMyEvents(){

        // Cleaner version of querying!
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.whereGreaterThanOrEqualTo("NumGoing", 0);
        query.whereEqualTo("Author", ParseUser.getCurrentUser());

        try {
            Globals.MyEventList = (ArrayList)query.find();

        }catch( ParseException e ){
            // Exception handle
        }

        for( Event evnt : Globals.MyEventList){

        }

    }

    public void updateListView(View view){
        getEvents();
        myAdapter = new EventAdapter(this, R.layout.event_list_row,Globals.EventList);
        setListAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        Globals.CurrList = Globals.EventList;
    }

    public void updateMyListView(View view) {
        getMyEvents();
        /*ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.event_list_row,
                R.id.name_of_event, myEventListValues);*/
        myAdapter = new EventAdapter(this, R.layout.my_event_list_row, Globals.MyEventList);
        setListAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        Globals.CurrList = Globals.MyEventList;
    }

    public void goTosettings(View view){
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }

    public void goToNewEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivityForResult(intent, requestNew);
    }

    public void viewEvent(View view) {
        Intent intent = new Intent(this, Event_Detail.class);
        intent.putExtra("pos",(Integer) view.getTag());
        startActivity(intent);
    }

    public void joinButtonClick(View view) {

    }

    public void openEditEvent(View view) {
        Intent intent = new Intent(this, EditEventActivity.class);
        intent.putExtra("pos", (Integer) view.getTag());
        startActivityForResult(intent, requestEdit);
    }

    // update event list if new event is added or event is edited
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        switch(requestCode) {

            case requestEdit:
                if(resultCode == RESULT_OK) {
                    updateMyListView(getCurrentFocus());
                } else if(resultCode == -1) {// if deleted event
                    Toast.makeText(getApplicationContext(), "Event Deleted!", Toast.LENGTH_SHORT).show();
                    updateMyListView(getCurrentFocus());
                }
                break;
            case requestNew:
                if(resultCode == RESULT_OK) {
                    updateListView(getCurrentFocus());
                }
                break;
        }


    }
}