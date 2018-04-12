package com.example.helloworld;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onCreate() {

        //onView check to see if the text is "Hello, world!"
        onView(withId(R.id.changeText))
                .check(matches(withText(R.string.hello_world))));

        //check the button click and see if the String is changed to "Goodbye, world!"
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());


        onView(withText(R.string.say_bye))
                .perform(click());

        onView(withId(R.id.hello_world_text))
                .check(matches(withText(R.string.say_bye)));


    }

}