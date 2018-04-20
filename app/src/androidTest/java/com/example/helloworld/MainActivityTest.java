package com.example.helloworld;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validInputTest() {

        // Test the name field.
        onView(withId(R.id.nameTextEdit)).perform(typeText("Brad"));

        // Test the username field.
        onView(withId(R.id.UserNameTextEdit)).perform(typeText("brad123"));

        // Test the email field.
        onView(withId(R.id.EmailTextEdit)).perform(typeText("Bradanissen@gmail.com"));

        setDate(R.id.dateTextView, 1990, 8, 13);

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.SubmitButton)).perform(click());

        onView(withId(R.id.messageTextView)).check(matches(withText("Thanks for signing up, Brad!")));

    }
    @Test
    public void validInputTestWithRotateBeforeSubmitting() {

        // Test the name field.
        onView(withId(R.id.nameTextEdit)).perform(typeText("brad"));

        // Test the username field.
        onView(withId(R.id.UserNameTextEdit)).perform(typeText("brad123"));

        // Test the email field.
        onView(withId(R.id.EmailTextEdit)).perform(typeText("bradanissen@gmail.com"));

        setDate(R.id.dateTextView, 1990, 8, 13);

        Espresso.closeSoftKeyboard();

        // rotate the screen
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        onView(withId(R.id.SubmitButton)).perform(click());

        //onView(withId(R.id.messageTextView)).check(matches(withText("Thanks for signing up, Brad!")));

        // go back and check the test is gone.

//        onView(withId(R.id.button4)).perform(click());

        // Test the name field.
 //       onView(withId(R.id.nameTextEdit)).check(matches(withText("")));

        // Test the username field.
 //       onView(withId(R.id.UserNameTextEdit)).check(matches(withText("")));

        // Test the email field.
   //     onView(withId(R.id.EmailTextEdit)).check(matches(withText("")));
    }

    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }
}