package com.unify.ucevent;

/**
 * Created by sandra on 5/24/15.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> {

    // declaring our ArrayList of items
    private ArrayList<Event> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public EventAdapter(Context context, int textViewResourceId, ArrayList<Event> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
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
            v = inflater.inflate(R.layout.event_list_row, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Event e = objects.get(position);

        if (e != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView name = (TextView) v.findViewById(R.id.name_of_event);
            TextView numAttend = (TextView) v.findViewById(R.id.attending_of_event);
            TextView date = (TextView) v.findViewById(R.id.date_of_event);
            TextView time = (TextView) v.findViewById(R.id.time_of_event);
            TextView location = (TextView) v.findViewById(R.id.location_of_event);
            //TextView description = (TextView) v.findViewById(R.id.description_of_event);


            // Assign text to each textview
            name.setText(e.getString("Title"));
            numAttend.setText(Integer.toString(e.getInt("NumGoing")) );
            String eDate = e.getInt("DateMonth") + "/" + e.getInt("DateDay") + "/" + e.getInt("DateYear");
            date.setText(eDate);
            String eTime = e.getInt("StartHour") + ":" + e.getInt("StartMinute") + " to " + e.getInt("EndHour") + ":" + e.getInt("EndMinute");
            time.setText(eTime);
            location.setText(e.getString("Location"));
            //description.setText(e.getString("Description"));
        }

        // the view must be returned to our activity
        return v;
    }
}