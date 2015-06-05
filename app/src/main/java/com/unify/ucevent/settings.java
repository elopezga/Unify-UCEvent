package com.unify.ucevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


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

    public void updateAccount(View view){
        EditText email = (EditText) findViewById(R.id.update_email);
        EditText username = (EditText) findViewById(R.id.update_username);
        EditText newPassword = (EditText) findViewById(R.id.update_password);
        EditText password = (EditText) findViewById(R.id.curr_password);

        // For pop-up message
        Context context = getApplicationContext();
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        boolean update_email = false;
        boolean update_username = false;
        boolean update_pass = false;
        //boolean error = false;

        if( email.getText().length() > 0 ) {
            // Do email checks
            boolean email_error = false;

            // Ensure valid email
            if( email.getText().length() < 3 || !email.getText().toString().contains("@") ) {
                email_error = true;
                text = "Invalid email";
            }
            else {
                // Check if email already taken
                ParseQuery<ParseUser> query = ParseQuery.getQuery("User");
                query.whereEqualTo("email", email.getText().toString());
                int count = 0;
                try {
                    count = query.count();
                } catch (ParseException e) {
                }
                if (count != 0) {
                    email_error = true;
                    text = "This email is already in use";
                }
            }

            // If success
            if (!email_error) {
                update_email = true;
            } else {
                // Print error message
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return; // Exit due to error
            }

        }

        if( username.getText().length() > 0 ) {
            // Do error checks
            boolean un_error = false;

            // Check if username already taken
            ParseQuery<ParseUser> query = ParseQuery.getQuery("User");
            query.whereEqualTo("username", email.getText().toString());
            int count = 0;
            try {
                count = query.count();
            } catch (ParseException e) {
            }
            if (count != 0) {
                un_error = true;
                text = "This username is already in use";
            }

            // If success
            if (!un_error) {
                update_username = true;
            } else {
                // Print error message
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return; // Exit due to error
            }
        }

        if( newPassword.getText().length() > 0 ) {
            // Do email checks
            boolean newpass_error = false;

            // Ensure valid email
            if( newPassword.getText().length() < 6 ) {
                newpass_error = true;
                text = "New password must be at least 6 characters long";
            }

            // If success
            if (!newpass_error) {
                update_pass = true;
            } else {
                // Print error message
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return; // Exit due to error
            }
        }

        if( update_email || update_username || update_pass ) {
            // Make sure correct password entered
            boolean passCheck;
            try{
                ParseUser.logIn( ParseUser.getCurrentUser().getUsername(),
                        password.getText().toString() );
                passCheck = true;
            } catch (ParseException e) {
                passCheck = false;
            }

            if( passCheck ) {
                text = "Success -- Updated ";
                int length = text.length();

                if( update_email ) { // Update email
                    ParseUser.getCurrentUser().setEmail( email.getText().toString() );
                    text = text+"email";
                }

                if( update_username ) { // Update username
                    ParseUser.getCurrentUser().setUsername( username.getText().toString() );
                    if( text.length() == length ) {
                        text = text+"username";
                    }
                    else {
                        text = text+", username";
                    }
                }

                if( update_pass ) { // Update password
                    ParseUser.getCurrentUser().setPassword( newPassword.getText().toString() );
                    if( text.length() == length ) {
                        text = text+"password";
                    }
                    else {
                        text = text+", password";
                    }
                }

                final boolean[] updateErr = {false};
                ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                    public void done(com.parse.ParseException e) {
                        // TODO Auto-generated method stub
                        if (e == null) { // Success
                        } else { // Error occurred
                            updateErr[0] = true;
                        }
                    }
                });

                if( updateErr[0] == true ) {
                    text = "Error in updating information";
                }
                // Display success message
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                if( updateErr[0] == false ) {
                    finish(); // Finish settings activity if success
                }

            }
            else {
                text = "Incorrect password entered";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return; // Exit due to error
            }
        }
        else {
            // No fields to update, display message
            text = "Nothing to update";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return; // Exit due to error
        }

        /*TODO USERNAME, NEW PASSWORD, PASSWORD CHECKS
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
    }

}
