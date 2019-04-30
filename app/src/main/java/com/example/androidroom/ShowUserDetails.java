package com.example.androidroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowUserDetails extends AppCompatActivity implements View.OnClickListener {

    /*
     * Creating reference variables for all the views
     */

    EditText name;
    EditText email;
    EditText password;
    Button edit;
    Button update;
    Student model;
    TextView logout;
    TextView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);
        setIdAndListeners();
        showDetails();
    }

    /*
     * Receiving an intent value and setting those values on corresponding edittexts
     */

    private void showDetails() {
        model = (Student) getIntent().getSerializableExtra("info");
        name.setText(model.getName());
        email.setText(model.getEmail());
        password.setText(model.getPassword());
    }

    /*
     * Referencing all fields and setting listeners on edit and update button and on logout and delete editext fields
     */

    private void setIdAndListeners() {
        name = findViewById(R.id.show_name);
        email = findViewById(R.id.show_email);
        password = findViewById(R.id.show_password);
        edit = findViewById(R.id.btn_edit);
        update = findViewById(R.id.btn_update);
        logout = findViewById(R.id.logout);
        delete = findViewById(R.id.delete_account);
        edit.setOnClickListener(this);
        update.setOnClickListener(this);
        logout.setOnClickListener(this);
        delete.setOnClickListener(this);
    }


    /*
     * A method to fire intent to main activity and finish current activity
     */


    private void moveToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onClick(View v) {

        /*
         * Checking if edit button is clicked then enabling all edittext fields as well as enabling the update button
         * Also disabling the edit button on click
         */

        if (v.getId() == R.id.btn_edit) {
            name.setEnabled(true);
            email.setEnabled(true);
            password.setEnabled(true);
            edit.setEnabled(false);
            update.setEnabled(true);
        }

        /*
         * if update button is clicked then updating the user information in database
         */

        else if (v.getId() == R.id.btn_update) {
            model.setName(name.getText().toString());
            model.setEmail(email.getText().toString());
            model.setPassword(password.getText().toString());
            if (Database.getInstance().daoClass().updateData(model)==1) {
                Toast.makeText(this, "Record modified successfully Please Login again with updated account", Toast.LENGTH_SHORT).show();
               moveToMain();

            }
        }

        /*
         * if logout textfield is clicked then closing this activity and firing an intent to main activity
         */

        else if (v.getId() == R.id.logout) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

        /*
         * if delete account textfield is clicked then deleting the particular record from the database
         * and closing this activity and firing intent to main activity
         */

        else if (v.getId() == R.id.delete_account) {
            int id = getIntent().getIntExtra("id", 0);
            if (Database.getInstance().daoClass().deleteData(model)==1) {
                Toast.makeText(this, "Account removed successfully", Toast.LENGTH_SHORT).show();
                moveToMain();
            }


        }


    }
}
