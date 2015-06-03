package com.unify.ucevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.parse.ParseUser;


public class settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void logout (View view) {
        ParseUser.logOutInBackground();
        //finish(); used to finish activity so user can't press back button to go back
        while(ParseUser.getCurrentUser()!=null) {
            // Wait for logOutInBackground to finish
        }
        Intent intent = new Intent(this, SampleDispatchActivity.class); // Go to login page
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // Clears all previous activities
        startActivity(intent);
    }

    public void goToNewEvent(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }


}
