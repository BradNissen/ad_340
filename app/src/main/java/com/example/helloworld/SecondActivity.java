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
    private TextView occupationDisplay;
    private TextView descriptionDisplay;

    private TextView input;
    private TextView input2;
    private TextView input3;

    private final String NAME = "name";
    private final String AGE = "age";
    private final String LOCATION = "location";
    private final String OCCUPATION = "occupation";
    private final String DESCRIPTION = "description";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        nameDisplay = findViewById(R.id.nameDisplayTextView);
        ageDisplay = findViewById(R.id.ageDisplayTextView);
        locationDisplay = findViewById(R.id.locationDisplayTextView);
        occupationDisplay = findViewById(R.id.occupationDisplayTextView);
        descriptionDisplay = findViewById(R.id.descriptionDisplayTextView);

////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------LOCATION------
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Where are you from?");
        builder.setIcon(R.drawable.pic);
        builder.setMessage("Please fill this out completely");

        input = new EditText(this);
        builder.setView(input);

        //SET POSITIVE BUTTON
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = input.getText().toString();
                //Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
                locationDisplay.setText(txt);
            }
        });

        //NEGATIVE BUTTON
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        //create the dialog
        final AlertDialog ad = builder.create();

        //BUTTON
        locationDisplay = findViewById(R.id.locationDisplayTextView);
        locationDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ad.show();
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////
        //--------- OCCUPATION -------
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

        builder2.setTitle("Whats your occupation");
        builder2.setIcon(R.drawable.pic);
        builder2.setMessage("Please fill this out completely");

        input2 = new EditText(this);
        builder2.setView(input2);

        //SET POSITIVE BUTTON
        builder2.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = input2.getText().toString();
                //Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
                occupationDisplay.setText(txt);
            }
        });

        //NEGATIVE BUTTON
        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //create the dialog
        final AlertDialog ad2 = builder2.create();

        //BUTTON
        occupationDisplay = findViewById(R.id.occupationDisplayTextView);
        occupationDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ad2.show();
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////
        //--------- DESCRIPTION -------
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

        builder3.setTitle("Describe yourself");
        builder3.setIcon(R.drawable.pic);
        builder3.setMessage("Please fill this out completely");

        input3 = new EditText(this);
        builder3.setView(input3);

        //SET POSITIVE BUTTON
        builder3.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = input3.getText().toString();
                //Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
                descriptionDisplay.setText(txt);
            }
        });

        //NEGATIVE BUTTON
        builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //create the dialog
        final AlertDialog ad3 = builder3.create();

        //BUTTON
        descriptionDisplay = findViewById(R.id.descriptionDisplayTextView);
        descriptionDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ad3.show();
            }
        });


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
            String age = b.getString(Constants.KEY_AGE);
            ageDisplay.setText(age);
        }
    }

    public void goToMainActivity(View view){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        //this.finish();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "onRestoreInstanceState()");
        if (savedInstanceState.containsKey(NAME)) {
            nameDisplay.setText((String)savedInstanceState.get(NAME));
        }

        if (savedInstanceState.containsKey(AGE)) {
            ageDisplay.setText((String) savedInstanceState.get(AGE));
        }

        if (savedInstanceState.containsKey(LOCATION)) {
            locationDisplay.setText((String) savedInstanceState.get(LOCATION));
        }

        if (savedInstanceState.containsKey(OCCUPATION)) {
            occupationDisplay.setText((String) savedInstanceState.get(OCCUPATION));
        }

        if (savedInstanceState.containsKey(DESCRIPTION)) {
            descriptionDisplay.setText((String) savedInstanceState.get(DESCRIPTION));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");
        outState.putString(NAME, nameDisplay.getText().toString());
        outState.putString(AGE, ageDisplay.getText().toString());
        outState.putString(LOCATION, locationDisplay.getText().toString());
        outState.putString(OCCUPATION, occupationDisplay.getText().toString());
        outState.putString(DESCRIPTION, descriptionDisplay.getText().toString());
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
