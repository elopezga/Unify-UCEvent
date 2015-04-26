package com.unify.ucevent;

import android.app.Application;
import com.parse.*;

public class ParsePushApp extends Application {
    //Initializing Parse and stuff for our app
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn", "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}