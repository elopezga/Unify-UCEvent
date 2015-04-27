package com.unify.ucevent;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ParsePushApp extends Application {
    //Initializing Parse and stuff for our app
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn", "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseFacebookUtils.initialize(getApplicationContext());
        FacebookSdk.sdkInitialize(getApplicationContext());
        //printHashKey();
    }



}