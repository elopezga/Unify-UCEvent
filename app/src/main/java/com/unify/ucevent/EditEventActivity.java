package com.unify.ucevent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


public class EditEventActivity extends Activity {

    private EditText title;
    private EditText contact;
    private DatePicker date;
    private TimePicker start;
    private TimePicker end;
    private Spinner category;
    private EditText location;
    private EditText description;
    private Event e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        e = Globals.CurrList.get(intent.getIntExtra("pos",0));
        setContentView(R.layout.activity_edit_event);

        title = (EditText) findViewById(R.id.event_title);
        contact = (EditText) findViewById(R.id.event_contact);
        date = (DatePicker) findViewById(R.id.event_date);
        start = (TimePicker) findViewById(R.id.event_start);
        end = (TimePicker) findViewById(R.id.event_end);
        category = (Spinner) findViewById(R.id.event_category);
        location = (EditText) findViewById(R.id.event_location);
        description = (EditText) findViewById(R.id.event_description);

        title.setText(e.getString("Title"));
        contact.setText("");    // empty as designed in new event activity
        date.updateDate(e.getInt("DateYear"), e.getInt("DateMonth"), e.getInt("DateDay"));
        start.setCurrentHour(e.getInt("StartHour"));
        start.setCurrentMinute(e.getInt("StartMinute"));
        end.setCurrentHour(e.getInt("EndHour"));
        end.setCurrentMinute(e.getInt("EndMinute"));

        category.setSelection(getIndex(category, e.getString("Category")));

        location.setText(e.getString("Location"));
        description.setText(e.getString("Description"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_event, menu);
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

    //private method for getting preset position of category spinner
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    // when save button is clicked
    public void UpdateEvent(View view){
        RetrieveEventInput input = new RetrieveEventInput(title, description, location, contact, date, start, end, category);
        CheckEventInput check = new CheckEventInput(title, location, contact, date, start, end);
        Context context = getApplicationContext();
        CharSequence text = "";
        boolean error = false;
        int duration = Toast.LENGTH_SHORT;

        if( !check.confirmTitle() ) {
            if( title.getText().toString().matches("") ) {
                text = "Please Specify Event Title";
                error = true;
            } else if( (title.getText().toString().matches(e.getString("Title")) )) {
                error = false;
            } else {
                // error only if new event title found the same as other events' in the list
                text = "Event Title Already Exists - Please Rename";
                error = true;
            }
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
            text = "Success! Event Updated";
        }

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        if( !error ) { // Only update event if no invalid inputs

            // fill data from database to local event object
            e.fillFromDB(e);

            e.setTitle(input.getTitle());
            e.setLocation(input.getLocation());
            e.setDescription(input.getDescription());
            e.setCategory(input.getCategory());
            e.setContact(input.getContact());
            e.setDate(input.getEventMonth(), input.getEventDay(), input.getEventYear());
            e.setTimes(input.getEventStartHour(), input.getEventStartMinute(),
                    input.getEventEndHour(), input.getEventEndMinute());


            e.upload();
            // Make sure to always call saveInBackground after uploading event object!!
            e.saveInBackground();
            setResult(RESULT_OK);
            finish();

           /* Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); */
        }
    }

    // if delete button is clicked
    public void deleteEvent(View view) {

        // build alert dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // display warning msg
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        // ok button
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Globals.MyEventList.remove(e);
                Globals.EventList.remove(e);
                e.deleteInBackground();
                setResult(-1);
                finish();
            }
        });

        // cancel button
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.cancel();
            }
        });

        // create the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
