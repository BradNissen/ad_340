package com.example.helloworld;
/*
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

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;


import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText nameInput;
    private EditText userNameInput;
    private EditText emailInput;
    private EditText locationInput;
    private EditText jobInput;
    private EditText decsriptionInput;


    private String date;
    private int month_x, day_x, year_x;
    private Button errorButton;
    private TextView mDisplayDate;
    private boolean userClicked;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get user fields
        nameInput = findViewById(R.id.nameTextEdit);
        userNameInput = findViewById(R.id.UserNameTextEdit);
        emailInput = findViewById(R.id.EmailTextEdit);
        locationInput= findViewById(R.id.locationTextEdit);
        jobInput = findViewById(R.id.jobTextEdit);
        decsriptionInput = findViewById(R.id.descriptionTextEdit);

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
                         if ((nameInput != null) && (userNameInput != null) && (emailInput != null)) {
                             errorButton.setText(R.string.submit);
                             userClicked = true;
                         }

                }
                else {
                    mDisplayDate.setText(date +"\n");
                    errorButton.setText(R.string.limit_18);
                }
            }
        };
}


    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("You don't exist yet");
        }
        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
            age--;
        }
        return age;
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

        Calendar birthday = Calendar.getInstance();
        birthday.set(year_x, month_x, day_x);
        int age = getAge(birthday.getTime());
        String ageString = String.valueOf(age);





        //NAME
        if (isValid(nameInput)){
            intent.putExtra(Constants.KEY_NAME, nameInput.getText().toString());

        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.name_input_error)).append("\n");
        }


        //USERNAME
        if (isValid(userNameInput)) {
            intent.putExtra(Constants.KEY_USER_NAME, userNameInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.username_input_error)).append("\n");
        }


        //EMAIL
        if (checkEmail(emailInput.getText().toString())) {
            intent.putExtra(Constants.KEY_EMAIL, emailInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.email_input_error)).append("\n");
        }

        //BIRTHDAY
        if (is18(month_x, day_x, year_x) && userClicked ) {
            intent.putExtra(Constants.KEY_AGE, ageString);
            intent.putExtra(Constants.KEY_DOB, month_x + "/" + day_x + "/" + year_x);
        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.birthday_input_error)).append("\n");
        }



        //LOCATION
        if (isValid(locationInput)) {
            intent.putExtra(Constants.KEY_LOCATION, locationInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.location_input_error)).append("\n");
        }


        //JOB
        if (isValid(jobInput)) {
            intent.putExtra(Constants.KEY_JOB, jobInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.job_input_error)).append("\n");
        }


        //DESCRIPTION
        if (isValid(decsriptionInput)) {
            intent.putExtra(Constants.KEY_DESC, decsriptionInput.getText().toString());
        } else {
            numErrors += 1;
            errMsg.append(getString(R.string.description_input_error)).append("\n");
        }



        // if there are no errors to display, then begin the activity.
        if (numErrors == 0) {
            errorButton.setText(R.string.submit);
            startActivity(intent);

        } else {
            errorButton.setText(errMsg.toString());
        }

    }

    public boolean isValid(EditText input) {

        String name = input.getText().toString();
        int num = name.length();
        //obviously this isn't the best solution, if a user enters " " it accepts that as input.
        if (num >= 1){
            return true;
        }

        return false;
    }


    //Make this a method to perform and cler fields.
    @Override
    protected void onRestart() {
        super.onRestart();
        nameInput.setText("");
        userNameInput.setText("");
        emailInput.setText("");
        locationInput.setText("");
        jobInput.setText("");
        decsriptionInput.setText("");
        mDisplayDate.setText(R.string.openDialogText);
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

        if (savedInstanceState.containsKey(Constants.KEY_LOCATION)) {
            locationInput.setText((String) savedInstanceState.get(Constants.KEY_LOCATION));
        }

        if (savedInstanceState.containsKey(Constants.KEY_JOB)) {
            jobInput.setText((String) savedInstanceState.get(Constants.KEY_JOB));
        }

        if (savedInstanceState.containsKey(Constants.KEY_DESC)) {
            decsriptionInput.setText((String) savedInstanceState.get(Constants.KEY_DESC));
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
        outState.putString(Constants.KEY_LOCATION, locationInput.getText().toString());
        outState.putString(Constants.KEY_JOB, jobInput.getText().toString());
        outState.putString(Constants.KEY_DESC, decsriptionInput.getText().toString());
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
