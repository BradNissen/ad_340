package com.example.helloworld;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onCreate() {

        //check the View with the id=changeText and check to see if matches with the string "Hello, World!"
        onView(withId(R.id.changeText))
                .check(matches(withText(R.string.hello_world)));


        //check the View with the id=button, and perform a click
        onView(withId(R.id.button))
                .perform(click());


        //check the view with the id=changeText and check to see if matches the string "Goodbye, World!"
        onView(withId(R.id.changeText))
                .check(matches(withText(R.string.goodbye_world)));

    }

}