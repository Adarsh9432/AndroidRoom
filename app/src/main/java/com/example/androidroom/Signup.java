package com.example.androidroom;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidroom.MainActivity;
import com.example.androidroom.R;

public class Signup extends AppCompatActivity implements View.OnClickListener, TextWatcher {


    /*
     * Creating reference variables for all the views
     */

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setIdAndListeners();

    }

    /*
     * A method to fire intent to main activity and finish current activity
     */

    private void moveToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /*
     * Method for referencing name email password and button fields
     * Also putting listeners on button, email and password fields
     */

    private void setIdAndListeners() {
        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        submit = findViewById(R.id.signup_submit);
        submit.setOnClickListener(this);
        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
    }


    @Override
    public void onClick(View v) {

        /*
         * Checking if signup button is clicked then checking if user already exists then no insertion of record else inserting the record
         */

        if (v.getId() == R.id.signup_submit) {

            Student student = new Student(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
            Student st=Database.getInstance().daoClass().getData(etEmail.getText().toString(), etPassword.getText().toString());
            if (st==null) {
                long insert=Database.getInstance().daoClass().insertData(student);
                if (insert>0 ){
                    Toast.makeText(this, "Details recorded successfully"+insert, Toast.LENGTH_SHORT).show();
                    moveToMain();
                } else
                    Toast.makeText(this, "Details not recorded. PLease fill again", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You are already registered. PLease Login", Toast.LENGTH_LONG).show();
                moveToMain();
            }


        }


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /*
     * Listener for both email and password fields
     * calling validation method to validate email and password fields
     * if validation method returns true then only login button is enabled
     */

    @Override
    public void afterTextChanged(Editable s) {
        if (validation()) {
            submit.setEnabled(true);
        } else
            submit.setEnabled(false);

    }



    /*
     *  A Method to validate the email and password.
     * Validating email against spaces and email proper format using email regex
     * Validating Password against spaces and password format using regex
     * Also if both fields are correctly filled then returning a true value
     */


    private boolean validation() {
        boolean val = true;

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.contains(" ")) {
            email = email.trim();
            etEmail.setText(email);
            etEmail.setError("Email should not have space");
            val = false;
        }

        if (password.contains(" ")) {
            password = password.trim();
            etPassword.setText(password);
            etPassword.setError("Password should not contain space");
            val = false;
        }

        if (password.length() > 0 && ((password.length() < 8 || (!password.matches(getString(R.string.password_regex)))))) {
            etPassword.setError("Password must have atleast 8 characters with atleast one letter and one digit");
            val = false;
        }

        if ((!email.matches(getString(R.string.email_regex))) && (email.length() > 0)) {
            etEmail.setError("Enter email in proper format");
            val = false;
        }

        return val;


    }


}
