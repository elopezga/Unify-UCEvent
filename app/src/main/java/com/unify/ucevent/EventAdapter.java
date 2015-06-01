package com.unify.ucevent;

/**
 * Created by Sandra on 5/24/15.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> {
    private final static String AM = "AM";
    private final static String PM = "PM";

    // declaring our ArrayList of items
    private ArrayList<Event> objects;
    private int viewId;
    private final int EDIT_LAYOUT_ID = R.layout.my_event_list_row;
    private final int JOIN_LAYOUT_ID = R.layout.event_list_row;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public EventAdapter(Context context, int textViewResourceId, ArrayList<Event> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        this.viewId = textViewResourceId;
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(this.viewId, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Event e = objects.get(position);
        Button viewButton = (Button) v.findViewById(R.id.view_event_button);
        viewButton.setTag(position);

        // if currently using my event list
        if( viewId == EDIT_LAYOUT_ID ){
            Button editButton = (Button) v.findViewById(R.id.edit_event_button);
            editButton.setTag(position);
        } else {
            // using regular event list, so just set joinButton
            Button joinButton = (Button) v.findViewById(R.id.join_or_going);
            joinButton.setTag(position);
        }

        if (e != null) {



            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView name = (TextView) v.findViewById(R.id.name_of_event);
            TextView numAttend = (TextView) v.findViewById(R.id.attending_of_event);
            TextView date = (TextView) v.findViewById(R.id.date_of_event);
            TextView time = (TextView) v.findViewById(R.id.time_of_event);
            TextView location = (TextView) v.findViewById(R.id.location_of_event);
            TextView listpos = (TextView) v.findViewById(R.id.event_pos_in_list);
            //TextView description = (TextView) v.findViewById(R.id.description_of_event);


            // Assign text to each textview
            name.setText(e.getString("Title"));
            numAttend.setText(Integer.toString(e.getInt("NumGoing")) + " attending");
            String eDate = e.getInt("DateMonth") + "/" + e.getInt("DateDay") + "/" + e.getInt("DateYear");
            date.setText(eDate);
            String eTime = writeTime(e);
            time.setText(eTime);
            location.setText(e.getString("Location"));
            listpos.setText(Integer.toString(position));
            //description.setText(e.getString("Description"));
        }

        // the view must be returned to our activity
        return v;
    }

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
}