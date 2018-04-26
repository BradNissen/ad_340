package com.example.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    private TextView nameDisplay;
    private TextView ageDisplay;
    private TextView locationDisplay;
    private TextView jobDisplay;
    private TextView descriptionDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        //ID's of the views about to be set within the table
        nameDisplay = findViewById(R.id.nameDisplayTextView);
        ageDisplay = findViewById(R.id.ageDisplayTextView);
        locationDisplay = findViewById(R.id.locationDisplayTextView);
        jobDisplay = findViewById(R.id.occupationDisplayTextView);
        descriptionDisplay = findViewById(R.id.descriptionDisplayTextView);


        //pull the info and make the intent
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Log.i(TAG, "intent");

        assert b != null;
        if (b.containsKey(Constants.KEY_NAME)){
            String name = b.getString(Constants.KEY_NAME);
            String lowerCaseName = name.toLowerCase();
            String properName = lowerCaseName.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
            nameDisplay.setText(properName);
        }
        if (b.containsKey(Constants.KEY_AGE)){
            String str = b.getString(Constants.KEY_AGE);
            ageDisplay.setText(str);
        }
        if (b.containsKey(Constants.KEY_LOCATION)){
            String str = b.getString(Constants.KEY_LOCATION);
            locationDisplay.setText(str);
        }
        if (b.containsKey(Constants.KEY_JOB)){
            String str = b.getString(Constants.KEY_JOB);
            jobDisplay.setText(str);
        }
        if (b.containsKey(Constants.KEY_DESC)){
            String str = b.getString(Constants.KEY_DESC);
            descriptionDisplay.setText(str);
        }
    }

    public void goToMainActivity(View view){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        //this.finish();
    }


//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        Log.i(TAG, "onRestoreInstanceState()");
//        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
//            nameDisplay.setText((String)savedInstanceState.get(Constants.KEY_NAME));
//        }
//        if (savedInstanceState.containsKey(Constants.KEY_AGE)) {
//            ageDisplay.setText((String) savedInstanceState.get(Constants.KEY_AGE));
//        }
//        if (savedInstanceState.containsKey(Constants.KEY_LOCATION)) {
//            locationDisplay.setText((String) savedInstanceState.get(Constants.KEY_LOCATION));
//        }
//        if (savedInstanceState.containsKey(Constants.KEY_JOB)) {
//            jobDisplay.setText((String) savedInstanceState.get(Constants.KEY_JOB));
//        }
//        if (savedInstanceState.containsKey(Constants.KEY_DESC)) {
//            descriptionDisplay.setText((String) savedInstanceState.get(Constants.KEY_DESC));
//        }
//    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");
        outState.putString(Constants.KEY_NAME, nameDisplay.getText().toString());
        outState.putString(Constants.KEY_AGE, ageDisplay.getText().toString());
        outState.putString(Constants.KEY_LOCATION, locationDisplay.getText().toString());
        outState.putString(Constants.KEY_JOB, jobDisplay.getText().toString());
        outState.putString(Constants.KEY_DESC, descriptionDisplay.getText().toString());
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
