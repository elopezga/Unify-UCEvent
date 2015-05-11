package com.unify.ucevent;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.parse.ParseUser;

/**
 * Created by sandra on 5/10/15.
 */
public class RetrieveEventInput {
    private EditText title;
    private EditText description;
    private EditText location;
    private EditText email;
    private DatePicker date;
    private TimePicker start;
    private TimePicker end;
    private Spinner category;

    public RetrieveEventInput(EditText eventTitle, EditText eventDesc, EditText eventLoc,
                         EditText contactEmail, DatePicker eventDate, TimePicker eventStart,
                         TimePicker eventEnd, Spinner eventCat) {
        title = eventTitle;
        description = eventDesc;
        location = eventLoc;
        email = contactEmail;
        date = eventDate;
        start = eventStart;
        end = eventEnd;
        category = eventCat;
    }

    public String getTitle() {
        return title.getText().toString();
    }
    public String getDescription() {
        return description.getText().toString();
    }
    public String getLocation() {
        return location.getText().toString();
    }
    public String getContact() {
        if( email.getText().toString().matches("") ) {
            return ParseUser.getCurrentUser().getEmail();
        }
        else {
            return email.getText().toString();
        }

    }
    public int getEventMonth() {
        return date.getMonth();
    }
    public int getEventDay() {
        return date.getDayOfMonth();
    }
    public int getEventYear() {
        return date.getYear();
    }
    public int getEventStartHour(){
        return start.getCurrentHour();
    }
    public int getEventStartMinute(){
        return start.getCurrentMinute();
    }
    public int getEventEndHour(){
        return end.getCurrentHour();
    }
    public int getEventEndMinute(){
        return end.getCurrentMinute();
    }

    public String getCategory(){
        return (String)category.getSelectedItem();
    }






}
