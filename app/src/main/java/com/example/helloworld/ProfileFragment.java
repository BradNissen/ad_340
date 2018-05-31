package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    private static final String TAG = "Tab1Fragment";

    private TextView nameFragDisplay;
    private TextView ageFragDisplay;
    private TextView locationFragDisplay;
    private TextView jobFragDisplay;
    private TextView descriptionFragDisplay;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container, false);


        // get the locations.
        nameFragDisplay = (TextView) view.findViewById(R.id.nameDisplayTextView);
        ageFragDisplay = (TextView) view.findViewById(R.id.ageDisplayTextView);
        locationFragDisplay = (TextView) view.findViewById(R.id.locationDisplayTextView);
        jobFragDisplay = (TextView) view.findViewById(R.id.occupationDisplayTextView);
        descriptionFragDisplay = (TextView) view.findViewById(R.id.descriptionDisplayTextView);


        //unbundle
        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        Log.i(TAG, "intent");


        String name = b.getString(Constants.KEY_NAME);
        String lowerCaseName = name.toLowerCase();
        String properName = lowerCaseName.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
        nameFragDisplay.setText(properName);

        String age = b.getString(Constants.KEY_AGE);
        ageFragDisplay.setText(age);

        String loc = b.getString(Constants.KEY_LOCATION);
        locationFragDisplay.setText(loc);

        String job = b.getString(Constants.KEY_JOB);
        jobFragDisplay.setText(job);

        String desc = b.getString(Constants.KEY_DESC);
        descriptionFragDisplay.setText(desc);

        return view;
    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        Log.i(TAG, "onRestoreInstanceState()");
//        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
//            nameFragDisplay.setText((String)savedInstanceState.get(Constants.KEY_NAME));
//        }
//
//        if (savedInstanceState.containsKey(Constants.KEY_AGE)) {
//            ageFragDisplay.setText((String) savedInstanceState.get(Constants.KEY_AGE));
//        }
//
//        if (savedInstanceState.containsKey(Constants.KEY_LOCATION)) {
//            locationFragDisplay.setText((String) savedInstanceState.get(Constants.KEY_LOCATION));
//        }
//
//        if (savedInstanceState.containsKey(Constants.KEY_JOB)) {
//            jobFragDisplay.setText((String) savedInstanceState.get(Constants.KEY_JOB));
//        }
//
//        if (savedInstanceState.containsKey(Constants.KEY_DESC)) {
//            descriptionFragDisplay.setText((String) savedInstanceState.get(Constants.KEY_DESC));
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");
        outState.putString(Constants.KEY_NAME, nameFragDisplay.getText().toString());
        outState.putString(Constants.KEY_AGE, ageFragDisplay.getText().toString());
        outState.putString(Constants.KEY_LOCATION, locationFragDisplay.getText().toString());
        outState.putString(Constants.KEY_JOB, jobFragDisplay.getText().toString());
        outState.putString(Constants.KEY_DESC, descriptionFragDisplay.getText().toString());
    }
}
