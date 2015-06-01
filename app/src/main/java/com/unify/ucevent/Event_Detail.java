package com.unify.ucevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Event_Detail extends Activity {

    private final static String AM = "AM";
    private final static String PM = "PM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Event e = Globals.CurrList.get(intent.getIntExtra("pos",0));
        setContentView(R.layout.activity_event_detail);
        // Log.d("detail", " " + e.getString("Title"));
        // String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // This currently pulls the title of the event but I don't know how to pull the id
        // Worst case, we can just requery the title, but that might end up with multiple events.
        // int pos = Integer.parseInt(message);
        // TextView textView = new TextView(this);
        //textView.setText( Globals.EventList.get(pos).getTitle());
        // textView.setText( Globals.EventList.get(pos).getString("Title"));
        // setContentView(textView);
        TextView name = (TextView) findViewById(R.id.name_of_event);
        TextView numAttend = (TextView) findViewById(R.id.attending_of_event);
        TextView date = (TextView) findViewById(R.id.date_of_event);
        TextView time = (TextView) findViewById(R.id.time_of_event);
        TextView location = (TextView) findViewById(R.id.location_of_event);
        TextView description = (TextView) findViewById(R.id.event_description);


        // Assign text to each textview
        name.setText(e.getString("Title"));
        numAttend.setText(Integer.toString(e.getInt("NumGoing")) + " Attending");
        String eDate = e.getInt("DateMonth") + "/" + e.getInt("DateDay") + "/" + e.getInt("DateYear");
        date.setText(eDate);
        String eTime = writeTime(e);
        time.setText(eTime);
        location.setText(e.getString("Location"));
        description.setText(e.getString("Description"));

    }

    // copied from eventadapter.java
    private String writeTime(Event e){
        String eTime;
        String startmin;
        String endmin;
        int starthour=0;
        int endhour=0;
        boolean startpm=false;
        boolean endpm=false;
        if(e.getInt("StartMinute")<10)
            startmin=":0"+e.getInt("StartMinute");
        else
            startmin=":"+e.getInt("StartMinute")+' ';
        if(e.getInt("EndMinute")<10)
            endmin=":0"+e.getInt("EndMinute");
        else
            endmin=":"+e.getInt("EndMinute")+' ';
        if(e.getInt("StartHour")>12){
            starthour=e.getInt("StartHour")-12;
            startpm=true;
        }
        if(e.getInt("EndHour")>12){
            endhour=e.getInt("EndHour")-12;
            endpm=true;
        }
        if(!startpm&&!endpm)
            eTime= starthour + startmin + AM + " to " + endhour + endmin + AM;
        else if(!startpm&&endpm)
            eTime= starthour + startmin + AM + " to " + endhour + endmin + PM;
        else if(startpm&&!endpm)
            eTime= starthour + startmin + PM + " to " + endhour + endmin + AM;
        else
            eTime= starthour + startmin + PM + " to " + endhour + endmin + PM;
        return eTime;
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

    public void Finish(View view) {
        finish();
    }
}
