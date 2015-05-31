package com.unify.ucevent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseUser;

/* ACTIVITY TO MAKE NEW EVENT */

public class EventActivity extends Activity /*ActionBarActivity*/ {

    //Event event = Globals.event;

    String PARSE_APP_ID = "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn";
    String PARSE_CLIENT_KEY = "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //ParseObject.registerSubclass(Event.class);
        //Parse.initialize(this,PARSE_APP_ID,PARSE_CLIENT_KEY);
        //event = new Event();
        //event = new Event();
        //event.put("Title","bullshit");
        /*uploadData(event);
        setContent(event);
        event.saveInBackground();


        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData(event);
            }
        }); */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

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


    /*
    public void setContent( Event event ){
        TextView titleText = (TextView) findViewById(R.id.event_title);
        titleText.setText(event.getTitle());
    }*/

    public void uploadData( Event event){
        event.upload();
    }

    public void openEditEvent(View view){
        Intent intent = new Intent(this, EditEventActivity.class);
        startActivity(intent);
    }

    /* Create new event object based on provided texts
     * Push Event to parse database
     * Send back to main page
     */
    public void submit( View view ) {

        EditText title = (EditText)findViewById(R.id.event_title);
        EditText description = (EditText)findViewById(R.id.event_description);
        EditText location = (EditText)findViewById(R.id.event_location);
        EditText contact = (EditText)findViewById(R.id.event_contact);
        DatePicker date = (DatePicker)findViewById(R.id.event_date);
        TimePicker start = (TimePicker)findViewById(R.id.event_start);
        TimePicker end = (TimePicker)findViewById(R.id.event_end);
        Spinner category = (Spinner)findViewById(R.id.event_category);

        RetrieveEventInput input = new RetrieveEventInput(title, description, location, contact, date, start, end, category);

        // Check valid fields
        CheckEventInput check = new CheckEventInput(title, location, contact, date, start, end);
        Context context = getApplicationContext();
        CharSequence text;
        boolean error = false;
        int duration = Toast.LENGTH_SHORT;

        if( !check.confirmTitle() ) {
            if( title.getText().toString().matches("") ) {
                text = "Please Specify Event Title";
            }
            else {
                text = "Event Title Already Exists - Please Rename";
            }
            error = true;
        }
        else if( !check.confirmDate() ) {
            text = "Invalid Date Specified";
            error = true;
        }
        else if( !check.confirmStartTime() ) {
            text = "Invalid Start Time Specified";
            error = true;
        }
        else if( !check.confirmEndTime() ) {
            text = "Event End Time cannot preceed Event Start Time";
            error = true;
        }
        else if( !check.confirmLocation() ) {
            text = "Please Specify Event Location";
            error = true;
        }
        else if( !check.confirmContact() ) {
            text = "Invalid Contact - If you are logged in with facebook, you MUST specify a contact email, else, invalid specified email";
            error = true;
        }
        else {
            text = "Success! Event Created";
        }

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        if( !error ) { // Only Create event if no invalid inputs
            Event newEvent = new Event(input.getTitle(), input.getLocation(), input.getDescription(),
                    input.getCategory(), input.getContact());
            newEvent.setDate(input.getEventMonth(), input.getEventDay(), input.getEventYear());
            newEvent.setTimes(input.getEventStartHour(), input.getEventStartMinute(),
                    input.getEventEndHour(), input.getEventEndMinute());
            newEvent.upload();


            // Make sure to always call saveInBackground after uploading event object!!
            newEvent.saveInBackground();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    /* For pop-up confirmation when exiting event page via back button */
    
    @Override
    public void onBackPressed() {
        EditText title = (EditText)findViewById(R.id.event_title);
        EditText description = (EditText)findViewById(R.id.event_description);
        EditText location = (EditText)findViewById(R.id.event_location);

        if( title.getText().toString().length()>0 || description.getText().toString().length()>0 ||
                location.getText().toString().length()>0 ) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Abandon New Event")
                    .setMessage("Are you sure you want to abandon your new event?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else {
            finish();
        }
    }

    public void changeSettings(View view) {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);

    }
}
