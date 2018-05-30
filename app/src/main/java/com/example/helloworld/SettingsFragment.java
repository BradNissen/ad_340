package com.example.helloworld;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.helloworld.entity.User;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

public class SettingsFragment extends Fragment {


    TimePicker time_picker;
    TextView text_seekBar;
    TextView text_seekBar_age;
    SeekBar seek_bar;
    SeekBar seek_bar_age;
    RadioGroup rg;
    RadioButton rb;
    Switch switchButton;
    Button button_update;
    Spinner genders;
    String gendersResult;

    int progress_value;
    int progress_value_age;

    ArrayAdapter<CharSequence> genderAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_fragment, container, false);


        //rg = view.findViewById(R.id.r_group);
        genders = view.findViewById(R.id.genders);
        time_picker = view.findViewById(R.id.timePicker);
        button_update = view.findViewById(R.id.update);
        seek_bar = view.findViewById(R.id.distance);
        text_seekBar = view.findViewById(R.id.text_seekBar); // text will change with progress
        text_seekBar.setText("Distance : " + seek_bar.getProgress() + " / " + seek_bar.getMax());

        seek_bar_age = view.findViewById(R.id.age);
        text_seekBar_age = view.findViewById(R.id.text_age); // text will change with progress
        text_seekBar_age.setText("Age Range from 18 - " + (seek_bar_age.getProgress() + 18) + " years old");

        switchButton = view.findViewById(R.id.switchButton);

        progress_bar();
        progress_bar_age();


        genderAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),R.array.genders, android.R.layout.simple_spinner_dropdown_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genders.setAdapter(genderAdapter);


        new GetUserTask(getActivity(), this, "bradanissen@gmail.com").execute();




            button_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    User newUser = new User();

                    newUser.setEmail("bradanissen@gmail.com"); //    <-- I don't think I need the email.
                    newUser.setHour(getHourFromPicker());
                    newUser.setMinute(getMinuteFromPicker());
                    newUser.setAgeRange(ageMax());
                    //newUser.setGender(getGenderSetString());
                    gendersResult = genders.getSelectedItem().toString();
                    newUser.setGender(gendersResult);
                    newUser.setPublic(getSwitch());
                    newUser.setSearchDistance(getDistance());


                    new UpdateUserTask(getActivity(), newUser).execute();


                }
            });



        return view;
    }


    public int getHourFromPicker(){
        return time_picker.getHour();

    }
    public int getMinuteFromPicker(){
        return time_picker.getMinute();
    }



    /**
     * Will return the radio button which is selected.
     * @return String "Male", "Female", "Transgender"
     */
    public String getGenderSetString(){
        // get selected radio button from radioGroup
        int selectedId = rg.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        rb = getView().findViewById(selectedId);

        // return the string of the selected gender
        return rb.getText().toString();
    }



    /**
     * Shows the status of the progress bar and sets the global
     * variable progress_value so it can be called later.
     */
    public void progress_bar(){
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                text_seekBar.setText("Distance : " + progress + " / " + seek_bar.getMax());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text_seekBar.setText("Distance : " + progress_value + " / " + seek_bar.getMax());
            }
        });
    }

    /**
     * Returns the value of the progress/seek bar.
     * @return int of the distance in miles.
     */
    public int getDistance(){

        return progress_value;
    }

    /**
     * will check if its public or private account
     * @return
     */
    public boolean getSwitch() {
        boolean isPrivate;

        if (switchButton.isChecked())
            isPrivate = true;
        else {
            isPrivate = false;
        }

        return isPrivate;
    }



    public void progress_bar_age(){
        seek_bar_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value_age = progress;
                text_seekBar_age.setText("Age Range from 18 - " + (seek_bar_age.getProgress() + 18) + " years old");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text_seekBar_age.setText("Age Range from 18 - " + (seek_bar_age.getProgress() + 18) + " years old");
            }
        });
    }

    public int ageMax(){
        return progress_value_age + 18;
    }



    private static class UpdateUserTask extends AsyncTask<Void, Void, User> {

        private WeakReference<Activity> weakActivity;
        private User user;

        public UpdateUserTask(Activity activity, User user) {
            weakActivity = new WeakReference<>(activity);
            this.user = user;
        }

        @Override
        protected User doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if(activity == null) {
                return null;
            }
            AppDatabase db = AppDatabaseSingleton.getDatabase(activity.getApplicationContext());
            db.userDao().insertUsers(user);

            //Log.v("mylog", user.toString());
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            Activity activity = weakActivity.get();
            if(user == null || activity == null) {
                return;
            }

            Toast.makeText(activity.getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
        }
    }





    private static class GetUserTask extends AsyncTask<Void, Void, User> {

        private WeakReference<Activity> weakActivity;
        private WeakReference<SettingsFragment> weakFragment;
        private String userEmail;

        public GetUserTask(Activity activity, SettingsFragment fragment, String userEmail) {
            weakActivity = new WeakReference<>(activity);
            weakFragment = new WeakReference<>(fragment);
            this.userEmail = userEmail;
        }

        @Override
        protected User doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if(activity == null) {
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(activity.getBaseContext());

            String[] emails = { userEmail };

            //all the rows on that user.
            List<User> users = db.userDao().loadAllByIds(emails);

            if(users.size() <= 0 || users.get(0) == null) {
                    return null;
            }
            //Log.v("mycheck", users.toString());
            return users.get(0);
        }

        @Override
        protected void onPostExecute(User user) {
            SettingsFragment fragment = weakFragment.get();

            if(user == null || fragment == null) {
                Toast.makeText(fragment.getContext(), "null", Toast.LENGTH_LONG).show();
                return;
            }

            //set the time fields when open.
            fragment.time_picker.setHour(user.getHour());
            fragment.time_picker.setMinute(user.getMinute());
            fragment.seek_bar.setProgress(user.getSearchDistance());
            fragment.seek_bar_age.setProgress(user.getAgeRange() - 18);
            fragment.switchButton.setChecked(user.isPublic());
            fragment.genders.setSelection(fragment.genderAdapter.getPosition(user.getGender()));




            //Log.v("mylog", user.toString());
//            Toast.makeText(fragment.getContext(), "Email=" +user.getEmail() + "\n"+
//                    "Time="+user.getHour() + " : " + user.getMinute() +"\n"+
//                    "Dist="+user.getSearchDistance() +"\n"+
//                    "ageRange="+user.getAgeRange() +"\n"+
//                    "Gender="+user.getGender() +"\n"+
//                    "Public="+user.isPublic() , Toast.LENGTH_LONG).show();
//            Toast.makeText(fragment.getContext(), "Success", Toast.LENGTH_LONG).show();


        }
    }



}



