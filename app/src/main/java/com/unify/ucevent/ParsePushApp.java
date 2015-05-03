package com.unify.ucevent;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.parse.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ParsePushApp extends Application {
    //Initializing Parse and stuff for our app

    String PARSE_APP_ID = "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn";
    String PARSE_CLIENT_KEY = "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Event.class);
        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
        ParseFacebookUtils.initialize(this);


        //ParseInstallation.getCurrentInstallation().saveInBackground();

        // Test Parse SDK

    }

}