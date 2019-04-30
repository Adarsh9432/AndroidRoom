package com.example.androidroom;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     * Creating reference variables for all the views
     */
    EditText email;
    EditText password;
    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIdAndListeners();
    }

    /*
     * Method for referencing all the declared views and setting listeners on login and signup button
     */

    private void setIdAndListeners() {

        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        email = findViewById(R.id.et_emailLogin);
        password = findViewById(R.id.et_passwordLogin);
    }

    @Override
    public void onClick(View v) {

        /*
         * Checking if signup button is clicked then firing an intent on Signup class
         */

        if (v.getId() == R.id.btn_signup) {
            Intent i = new Intent(this, Signup.class);
            startActivity(i);
            finish();
        }

        /*
         * Checking if login button is clicked then calling getDeatil method of DataContractor class to verify the login details
         * if login credentials are correct then firing an intent to another class for showing all user information to user
         * if login credentials are wrong then giving a toast for filling correct details
         */

        else if (v.getId() == R.id.btn_login) {
            Student st= Database.getInstance().daoClass().getData(email.getText().toString(), password.getText().toString());
            if (st!=null) {
                Intent i = new Intent(this, ShowUserDetails.class);
                i.putExtra("info", st);
                startActivity(i);
                finish();

            } else
                Toast.makeText(this, "Wrong user details or not having an account", Toast.LENGTH_SHORT).show();

        }


    }
}
