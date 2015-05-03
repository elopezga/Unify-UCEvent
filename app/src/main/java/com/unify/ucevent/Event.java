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
public class Event extends ParseObject{
    private String title;
    private String location;
    private String time;
    private int numGoing;
    private String description;
    private String category;


    public Event(){
        super();
        title = "Blank";
        location = "No where";
        time = "Whenever you feel like it";
        numGoing = -1;
        description = "Just do you, man";
        category = "You";
    }

    /****************Getter Methods******************/
    public String getTitle(){return title;}
    public String getLocation(){return location;}
    public String getTime(){return time;}
    public int getNumGoing(){return numGoing;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}

    /****************Setter Methods*****************/
    public void setTitle(String ttl){title=ttl;}
    public void setLocation(String loc){location=loc;}
    public void setTime(String t){time=t;}
    public void setNumGoing(int num){numGoing=num;}
    public void setDescription(String desc){description=desc;}
    public void setCategory(String cat){category=cat;}


    /****************Database Methods****************/

    /* Upload the current state of the Event method to the database */
    public void upload(){
        put("Title",title);
        put("Location",location);
        put("Time",time);
        put("NumGoing",numGoing);
        put("Description",description);
        put("Category",category);

    }
}
