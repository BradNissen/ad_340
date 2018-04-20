package com.example.helloworld;
/*
android built in regex for validation for emails.
have it display the age in a text view.

 */
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText nameInput;
    private EditText userNameInput;
    private EditText emailInput;
    private String date;
    private int month_x, day_x, year_x;
    private Button errorButton;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameInput = findViewById(R.id.nameTextEdit);
        userNameInput = findViewById(R.id.UserNameTextEdit);
        emailInput = findViewById(R.id.EmailTextEdit);
        mDisplayDate = findViewById(R.id.dateTextView);
        errorButton = findViewById(R.id.SubmitButton);


        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog (
                        MainActivity.this,
                        //android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                        android.R.style.Theme_DeviceDefault_Dialog_Alert,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                month_x = month;
                day_x = day;
                year_x = year;
                date = month + "/" + day + "/" + year;
                if (is18(month, day, year)){
                    mDisplayDate.setText(date +"\n");
                         if ((nameInput != null) && (userNameInput != null) && (emailInput != null))
                             errorButton.setText("Submit");
                }
                else {
                    mDisplayDate.setText(date +"\n");
                    errorButton.setText("You must be 18 yrs old.");
                }
            }
        };
}


    public boolean is18(int MM, int DD, int YYYY){
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        boolean age = false;

        if ((currentYear - YYYY) == 18) {
            if (currentMonth <= MM) {
                if (currentDay <= DD) {
                    age = true;
                }
            }
        }
        if ((currentYear - YYYY) > 18){
            age = true;
        }
        return age;
    }


    public void goToSecondActivity(View view) {

        StringBuilder errMsg = new StringBuilder();
        int numErrors = 0;
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);


        if (isValid(nameInput)){
            intent.putExtra(Constants.KEY_NAME, nameInput.getText().toString());

        } else {
            numErrors += 1;
            errMsg.append("name input error").append("\n");
        }


        if (isValid(userNameInput)) {
            intent.putExtra(Constants.KEY_USER_NAME, userNameInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append("username input error").append("\n");
        }


        if (isValid(emailInput)) {
            intent.putExtra(Constants.KEY_EMAIL, emailInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append("email input error").append("\n");
        }


        if (is18(month_x, day_x, year_x) && year_x>0) {
            intent.putExtra(Constants.KEY_DOB, month_x + "/" + day_x + "/" + year_x);
        } else {
            numErrors += 1;
            errMsg.append("birthday input error");
        }

        // if there are no errors to display, then begin the activity.
        if (numErrors == 0) {
            errorButton.setText("Submit");
            startActivity(intent);

        } else {
            errorButton.setText(errMsg.toString());
        }


    }

    public boolean isValid(EditText input) {

        String name = input.getText().toString();
        int num = name.length();
        //obviously this is the best solution, if a user enters " " it accepts that as input.
        if (num >= 1){
            return true;
        }

        return false;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "onRestoreInstanceState()");
        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            nameInput.setText((String)savedInstanceState.get(Constants.KEY_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_USER_NAME)) {
            userNameInput.setText((String) savedInstanceState.get(Constants.KEY_USER_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            emailInput.setText((String) savedInstanceState.get(Constants.KEY_EMAIL));
        }

        if (savedInstanceState.containsKey(Constants.KEY_DOB)) {
            mDisplayDate.setText((String) savedInstanceState.get(Constants.KEY_DOB));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");
        outState.putString(Constants.KEY_NAME, nameInput.getText().toString());
        outState.putString(Constants.KEY_USER_NAME, userNameInput.getText().toString());
        outState.putString(Constants.KEY_EMAIL, emailInput.getText().toString());
        outState.putString(Constants.KEY_DOB, mDisplayDate.getText().toString());
    }


    @Override
    protected void onResume() {

        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }
}
