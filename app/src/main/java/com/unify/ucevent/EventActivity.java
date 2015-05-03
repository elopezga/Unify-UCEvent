package com.unify.ucevent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.*;


public class EventActivity extends ActionBarActivity {

    Event event = Globals.event;

    String PARSE_APP_ID = "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn";
    String PARSE_CLIENT_KEY = "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //ParseObject.registerSubclass(Event.class);
        //Parse.initialize(this,PARSE_APP_ID,PARSE_CLIENT_KEY);
        //event = new Event();
        //event = new Event();
        //event.put("Title","bullshit");
        uploadData(event);
        setContent(event);
        event.saveInBackground();


        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData(event);
            }
        });

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


    public void setContent( Event event ){
        TextView titleText = (TextView) findViewById(R.id.event_title_content);
        titleText.setText(event.getTitle());
    }

    public void uploadData( Event event){
        event.upload();
    }

    public void openEditEvent(View view){
        Intent intent = new Intent(this, EditEventActivity.class);
        startActivity(intent);
    }

}
