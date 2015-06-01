package com.unify.ucevent;

/*
 * This is a simple staticly-used class to format time output into 12-hour time.
 */
public class timeWriter {
    public static String writeTime(Event e){
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
        if(starthour == 0)
            starthour = 12;
        if(endhour == 0)
            endhour = 12;

        if(!startpm&&!endpm)
            eTime= starthour + startmin + "AM" + " to " + endhour + endmin + "AM";
        else if(!startpm&&endpm)
            eTime= starthour + startmin + "AM" + " to " + endhour + endmin + "PM";
        else if(startpm&&!endpm)
            eTime= starthour + startmin + "PM" + " to " + endhour + endmin + "AM";
        else
            eTime= starthour + startmin + "PM" + " to " + endhour + endmin + "PM";
        return eTime;
    }

}