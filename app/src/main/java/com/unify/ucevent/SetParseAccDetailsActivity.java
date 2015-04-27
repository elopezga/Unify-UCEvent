package com.unify.ucevent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by sandra on 4/26/15.
 */
public class SetParseAccDetailsActivity extends Activity {

    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordAgainView;
    private ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setaccdetails_activity);
    }

    public void updateAccDetails(View v){
        // Set up the signup form.
        usernameView = (EditText) findViewById(R.id.set_username);
        passwordView = (EditText) findViewById(R.id.set_password);
        passwordAgainView = (EditText) findViewById(R.id.set_passwordAgain);

        boolean updateUsername = false;
        boolean updatePassword = false;
        boolean validationError = false;
        StringBuilder validationErrorMessage =
        new StringBuilder(getResources().getString(R.string.error_intro));

        // Check Which fields to Update
        if (!isEmpty(usernameView)) {
            updateUsername = true;
        }

        if (!isEmpty(passwordView)) {
            if (!isMatching(passwordView, passwordAgainView)) {

                validationError = true;
                validationErrorMessage.append(getResources().getString(
                                R.string.error_mismatched_passwords));
                validationErrorMessage.append(getResources().getString(R.string.error_end));
            } else {
                updatePassword = true;
            }
        }

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(SetParseAccDetailsActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(SetParseAccDetailsActivity.this);
        dlg.setTitle("Please wait.");
        dlg.setMessage("Updating account details. Please wait.");
        dlg.show();

        // Set appropriate fields
        if( updateUsername )
        {
            currentUser.setUsername(usernameView.getText().toString());
        }
        if( updatePassword )
        {
             currentUser.setPassword(passwordView.getText().toString());
        }

        dlg.dismiss();
        Intent intent = new Intent(SetParseAccDetailsActivity.this, DispatchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }



}

