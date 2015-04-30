package com.unify.ucevent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.parse.*;

//added a comment


public class MainActivity extends ActionBarActivity {

    public void onCreate() {
        // Enables Parse Local Datastore - Connects to UCEvent
        // Login: ssdai@ucsd.edu
        // PW: Cse110Unify

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "62Yg7BmL5VctbBBlYDiIutmcp3NwJSIXkzOIKMTn", "0uyGE5SGTg7szwgz9ZetTzBpD5wcR2pu6vKqgOSF");
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        /* Test Parse SDK
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseFacebookUtils.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
