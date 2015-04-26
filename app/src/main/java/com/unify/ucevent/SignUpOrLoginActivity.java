package com.unify.ucevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by HDK on 4/26/15.
 */
public class SignUpOrLoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_or_login);

        // Log in button click handler
        ((Button) findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent of the log in activity
                startActivity(new Intent(SignUpOrLoginActivity.this, LoginActivity.class));
            }
        });

        // Sign up button click handler
        ((Button) findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                startActivity(new Intent(SignUpOrLoginActivity.this, SignUpActivity.class));
            }
        });
    }
}
