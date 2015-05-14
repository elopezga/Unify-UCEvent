package com.unify.ucevent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import com.unify.ucevent.R;


public class EditEventActivity extends ActionBarActivity {

    //Event event = Globals.event;
    Event editEvent = Globals.event;
    Event someEvent = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        /* Add event listener that changes event properties. And implement the
           db submit button
         */

        //editEvent.setTitle("Tea Party");
        //editEvent.setLocation("Botanic Garden");
        //editEvent.upload();
        //editEvent.saveInBackground();


        final TextView titleText = (TextView) findViewById(R.id.textView4);
        /*
        titleText.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // event.setTitle(titleText.getText().toString())
                editEvent.setTitle(v.getText().toString());
                return true;
            }
        });*/

        // Template for querying. Probably does not belong in Event class
        // Idea: Query Events that fall within certain created Time e.g. latest
        // so that you can retrieve many using query filter
        // Also, make events have a popularity field for the same reason; only
        // for internal use
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.getInBackground("jvcTxECzit", new GetCallback<Event>(){
            public void done(Event obj, ParseException e){
                // Note: Takes a while to retrieve data. This function will run when
                // it is done retrieving data
                Log.d("Query", obj.getString("Title")); // USE THIS not Event class methods

                //titleText.setText(obj.getString("Title"));
                someEvent.fillFromDB(obj);

                titleText.setText(someEvent.getTitle());


            }
        });
        //someEvent.setTitle("Fuck you asshole");
        //titleText.setText(someEvent.getTitle());
        //Log.d("Status", "DOne");
        //Log.d("Event", someEvent.getTitle() );

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
}
