package com.unify.ucevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;


public class Event_Detail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // This currently pulls the title of the event but I don't know how to pull the id
        // Worst case, we can just requery the title, but that might end up with multiple events.
        int pos = Integer.parseInt(message); // Hold clicked event position in EventList
        Event clkdEvent = Globals.EventList.get(pos);

        //setContentView(R.layout.activity_event_detail);
        // blah
        final LayoutInflater factory = getLayoutInflater();
        //final View vw = factory.inflate(R.layout.activity_event_detail, null);
        /*LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.event_list_row, null);*/

        TextView textView = new TextView(this);
        TextView title = (TextView) findViewById(R.id.details_event_title);
        //TextView day = (TextView) findViewById(R.id.details_day);
        TextView month = (TextView) findViewById(R.id.details_month);
        TextView daynum = (TextView) findViewById(R.id.details_daynum);
        TextView time = (TextView) findViewById(R.id.details_time);
        TextView location = (TextView) findViewById(R.id.details_location);
        TextView description = (TextView) findViewById(R.id.details_description_text);
        Button attending = (Button) findViewById(R.id.details_attending);
        Button going = (Button) findViewById(R.id.details_going);
        Button editevent = (Button) findViewById(R.id.details_editevent);
        Button goback = (Button) findViewById(R.id.details_goback);


        //textView.setText( Globals.EventList.get(pos).getTitle());
        //textView.setText( clkdEvent.getString("Title"));
        //setContentView(textView);

        title.setText( clkdEvent.getString("Title"));
        month.setText( Integer.toString(clkdEvent.getInt("DateMonth")));
        location.setText( clkdEvent.getString("Location") );
        description.setText( clkdEvent.getString("Description") );
        attending.setText( Integer.toString(clkdEvent.getInt("NumGoing")) + " Attending!" );

        //setContentView(textView);
        //setContentView(R.layout.activity_event_detail);
        //setContentView(R.layout.activity_event_detail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_detail, menu);
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
