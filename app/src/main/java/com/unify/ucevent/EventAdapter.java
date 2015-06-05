package com.unify.ucevent;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EventAdapter extends ArrayAdapter<Event> {
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

            final TextView name = (TextView) v.findViewById(R.id.name_of_event);
            TextView numAttend = (TextView) v.findViewById(R.id.attending_of_event);
            TextView date = (TextView) v.findViewById(R.id.date_of_event);
            TextView time = (TextView) v.findViewById(R.id.time_of_event);
            TextView location = (TextView) v.findViewById(R.id.location_of_event);
            TextView listpos = (TextView) v.findViewById(R.id.event_pos_in_list);
            final Button join = (Button) v.findViewById(R.id.join_or_going);
            //TextView description = (TextView) v.findViewById(R.id.description_of_event);


            // Assign text to each textview
            final String eventTitle = e.getString("Title"); // For pop-up
            String title = e.getString("Title");
            if( title.length()> 13 ) {
                title = title.substring(0,13);
                title = title+"...";
            }
            name.setText(title);
            numAttend.setText(Integer.toString(e.getInt("NumGoing")) + " attending");
            String eDate = e.getInt("DateMonth") + "/" + e.getInt("DateDay") + "/" + e.getInt("DateYear");
            date.setText(eDate);
            String eTime = timeWriter.writeTime(e);
            time.setText(eTime);
            location.setText(e.getString("Location"));
            listpos.setText(Integer.toString(position));

            // Attach listener to join button
            if( join != null ) {
                join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (join.getText().equals("Join")) {
                            //final boolean[] alreadyGoing = {false};
                            // Set to Going, increment attendence count
                            ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
                            query.whereEqualTo("Title", eventTitle);
                            query.findInBackground(new FindCallback<Event>() {
                                public void done(List<Event> event, ParseException e) {
                                    Globals.going = false;
                                    for (Event ev : event) {
                                        ArrayList<ParseUser> atten = (ArrayList<ParseUser>) ev.get("Attending");
                                        if (atten.contains(ParseUser.getCurrentUser())) {
                                            // Indicate user is already going
                                            Globals.going = true;
                                        } else {
                                            int going = (Integer) ev.get("NumGoing");
                                            ev.put("NumGoing", going + 1);
                                            List<ParseUser> add = new ArrayList<ParseUser>();
                                            add.add(ParseUser.getCurrentUser());
                                            ev.addAllUnique("Attending", add);
                                            ev.saveInBackground();
                                        }
                                    }
                                }
                            });
                            join.setText("Going!");
                            join.setBackgroundColor(Color.YELLOW);

                            // Display attending message
                            Context context = getContext();
                            CharSequence text = "";
                            int duration = Toast.LENGTH_SHORT;
                            if ( Globals.going/*alreadyGoing[0] == false*/ ) {
                                // Print user is already attending event
                                // Currently not working -- will fix in future iterations
                                text = "You are already attending " + eventTitle;
                            } else {
                                // Print user is now attending event
                                text = "You are now attending " + eventTitle;
                            }

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        } else {
                            // Set back to Join, decrement attendence count
                            ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
                            query.whereEqualTo("Title", eventTitle);
                            query.findInBackground(new FindCallback<Event>() {
                                public void done(List<Event> event, ParseException e) {
                                    for (Event ev : event) {
                                        int going = (Integer) ev.get("NumGoing");
                                        ev.put("NumGoing", going - 1);
                                        List<ParseUser> remove = new ArrayList<ParseUser>();
                                        remove.add(ParseUser.getCurrentUser());
                                        ev.removeAll("Attending", remove);
                                        ev.saveInBackground();
                                        // Print message user is no longer attending event

                                    }
                                }
                            });
                            join.setText("Join");
                            join.setBackgroundColor(Color.LTGRAY);

                            // Display no longer attending message
                            Context context = getContext();
                            CharSequence text = "You are no longer attending " + eventTitle;
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                });
            }
        }

        // the view must be returned to our activity
        return v;
    }

    /*
     * Returns the number of types of Views created by getView
     * If adapted always returns same type of View for all items, should return 1
     * Return objects.size() to have each view be considered different "type"
     * Fixes bug where clicking going button in one list view object changes button
     * in later list view objects
     * Return 1 for case when no objects to display (objects.size() == 0 )
     */
    @Override
    public int getViewTypeCount() {
        //Count=Size of ArrayList.
        if (objects.size() > 0) {
            return objects.size();
        }
        else {
            return 1;
        }
    }

    /*
     * Returns type of View that will be created by getView for specified item
     */
    @Override
    public int getItemViewType(int position) {

        return position;
    }
}