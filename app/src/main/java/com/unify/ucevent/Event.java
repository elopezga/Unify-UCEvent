package com.unify.ucevent;

import com.parse.*;


/**
 * Created by Edgar on 4/26/2015.
 * Author: Edgar Lopez
 * Created: 4/26/2015
 * Description: The Event class extend ParseObject so that it can leverage uploading and
 * downloading to and from the Parse Database. The Event class serves two purposes: 1) Handles
 * database transactions. 2) Makes event data available for UI purposes.
 */
@ParseClassName("Event")
public class Event extends ParseObject {
    private String title;
    private String location;
    private int dateMonth;
    private int dateDay;
    private int dateYear;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int numGoing;
    private String description;
    private String category;
    private ParseUser author;
    private String contact;
    //private ParseACL ACL;


    public Event(){
        super();
        title = "Blank";
        location = "No where";
        dateMonth = 0;
        dateDay = 0;
        dateYear = 0;
        startHour = 0;
        startMinute = 0;
        endHour = 0;
        endMinute = 0;
        numGoing = -1;
        description = "";
        category = "You";
        author = ParseUser.getCurrentUser();
        contact = "Test@email.com"; // Use bottom code for deployment!!!
        //contact = ParseUser.getCurrentUser().getEmail();
        //ACL = new ParseACL();
        //ACL.setWriteAccess(ParseUser.getCurrentUser(),true);
        //setACL(ACL);
        //saveInBackground();
    }

    public Event( String t, String l, String d, String c, String email) {
        super();
        title = t;
        location = l;
        dateMonth = 0;
        dateDay = 0;
        dateYear = 0;
        startHour = 0;
        startMinute = 0;
        endHour = 0;
        endMinute = 0;
        description = d;
        category = c;
        numGoing = 0;
        author = ParseUser.getCurrentUser();
        contact = email;
    }

    /****************Getter Methods******************/
    public String getTitle(){return title;}
    public String getLocation(){return location;}
    public int getDateMonth() {return dateMonth;}
    public int getDateDay(){return dateDay;}
    public int getDateYear(){return dateYear;}
    public int getStartHour(){return startHour;}
    public int getStartMinute(){return startMinute;}
    public int getEndHour(){return endHour;}
    public int getEndMinute() {return endMinute;}
    public int getNumGoing(){return numGoing;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}
    public ParseUser getAuthor(){ return author; }
    public String getContact(){ return contact; }

    /****************Setter Methods*****************/
    public void setTitle(String ttl){title=ttl;}

    public void setLocation(String loc){location=loc;}

    public void setDate(int month, int day, int year){
        dateMonth = month;
        dateDay = day;
        dateYear = year;
    }
    public void setDateMonth(int month){ dateMonth=month;}
    public void setDateDay(int day){ dateDay=day;}
    public void setDateYear(int year){ dateYear=year;}

    public void setTimes( int sHour, int sMin, int eHour, int eMin) {
        startHour = sHour;
        startMinute = sMin;
        endHour = eHour;
        endMinute = eMin;
    }
    public void setStartHour(int sHour){ startHour=sHour;}
    public void setStartMinute(int sMin){ startMinute=sMin;}
    public void setEndHour(int eHour){ endHour=eHour;}
    public void setEndMinute(int eMin){ endMinute=eMin;}

    public void setNumGoing(int num){numGoing=num;}
    public void setDescription(String desc){description=desc;}
    public void setCategory(String cat){category=cat;}

    // No setAuthor method because the author should not change

    public void setContact(String email){ contact = email; }


    /****************Database Methods****************/

    /* Upload the current state of the Event method to the database */
    public void upload(){
        put("Title",title);
        put("Location",location);
        put("DateMonth", dateMonth);
        put("DateDay", dateDay);
        put("DateYear",dateYear);
        put("StartHour",startHour);
        put("StartMinute", startMinute);
        put("EndHour", endHour);
        put("EndMinute", endMinute);
        put("NumGoing",numGoing);
        put("Description",description);
        put("Category",category);
        put("Author", author);
        put("Contact", contact);
        saveInBackground();
    }

    public void fillFromDB( Event retrieved ){
        title = retrieved.getString("Title");
        location = retrieved.getString("Location");
        //time = retrieved.getString("Time");
        numGoing = retrieved.getInt("NumGoing");
        description = retrieved.getString("Description");
        category = retrieved.getString("Category");
        author = retrieved.getParseUser("Author");
    }
}
