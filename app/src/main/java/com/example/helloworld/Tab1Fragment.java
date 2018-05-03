package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Tab1Fragment extends Fragment {

    private static final String TAG = "Tab1Fragment";

    private TextView nameFragDisplay;
    private TextView ageFragDisplay;
    private TextView locationFragDisplay;
    private TextView jobFragDisplay;
    private TextView descriptionFragDisplay;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container, false);


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

        //then fill the fields
        assert b != null;
        if (b.containsKey(Constants.KEY_NAME)){
            String name = b.getString(Constants.KEY_NAME);
            String lowerCaseName = name.toLowerCase();
            String properName = lowerCaseName.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
            nameFragDisplay.setText(properName);
        }
        if (b.containsKey(Constants.KEY_AGE)){
            String str = b.getString(Constants.KEY_AGE);
            ageFragDisplay.setText(str);
        }
        if (b.containsKey(Constants.KEY_LOCATION)){
            String str = b.getString(Constants.KEY_LOCATION);
            locationFragDisplay.setText(str);
        }
        if (b.containsKey(Constants.KEY_JOB)){
            String str = b.getString(Constants.KEY_JOB);
            jobFragDisplay.setText(str);
        }
        if (b.containsKey(Constants.KEY_DESC)){
            String str = b.getString(Constants.KEY_DESC);
            descriptionFragDisplay.setText(str);
        }

        return view;
    }

}
