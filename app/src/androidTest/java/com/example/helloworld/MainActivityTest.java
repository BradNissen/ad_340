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

    String name = "brad";
    String age = "27";
    String username = "brad123";
    String email = "bradanissen@gmail.com";
    String city = "Seattle";
    String job = "Server";
    String desc = "Long Walks on the beach.";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validInputTest() {

        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
        Espresso.closeSoftKeyboard();
        setDate(R.id.dateTextView, 1990, 8, 13);
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(click());//

//        onView(withId(R.layout.tab1_fragment)).check(withId(R.id.nameDisplayTextView));
//        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
//        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(city)));
//        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
//        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));
//
//        // rotate the screen before
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        onView(withId(R.id.nameDisplayTextView)).check(matches(withText(name)));
//        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
//        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(city)));
//        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
//        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        onView(withId(R.id.nameDisplayTextView)).check(matches(withText("Brad")));
//        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
//        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(city)));
//        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
//        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }
//    @Test
//    public void rotatingSumbitwithValid() {
//        onView(withId(R.id.nameTextEdit)).perform(typeText("brad"));
//        onView(withId(R.id.UserNameTextEdit)).perform(typeText("brad123"));
//        onView(withId(R.id.EmailTextEdit)).perform(typeText("bradanissen@gmail.com"));
//        onView(withId(R.id.locationTextEdit)).perform(typeText("Seattle"));
//        onView(withId(R.id.jobTextEdit)).perform(typeText("Server"));
//        onView(withId(R.id.descriptionTextEdit)).perform(typeText("Long walks"));
//        Espresso.closeSoftKeyboard();
//        setDate(R.id.dateTextView, 1990, 8, 13);
//
//        // rotate the screen before
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.SubmitButton)).perform(ViewActions.scrollTo());
//        onView(withId(R.id.SubmitButton)).perform(click());
//    }
//
//    @Test
//    public void birthdayIsToday() {
//        onView(withId(R.id.nameTextEdit)).perform(typeText("brad"));
//        onView(withId(R.id.UserNameTextEdit)).perform(typeText("brad123"));
//        onView(withId(R.id.EmailTextEdit)).perform(typeText("bradanissen@gmail.com"));
//        onView(withId(R.id.locationTextEdit)).perform(typeText("Seattle"));
//        onView(withId(R.id.jobTextEdit)).perform(typeText("Server"));
//        onView(withId(R.id.descriptionTextEdit)).perform(typeText("Long walks"));
//        Espresso.closeSoftKeyboard();
//        setDate(R.id.dateTextView, 2000, 4, 26);
//
//        // rotate the screen before
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.SubmitButton)).perform(ViewActions.scrollTo());
//        onView(withId(R.id.SubmitButton)).perform(click());
//    }
//
//    @Test
//    public void inputSubmitError(){
//
//        onView(withId(R.id.descriptionTextEdit)).perform(typeText("Long walks"));
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.SubmitButton)).perform(click());
//
//    }
//
//    @Test
//    public void inputSubmitErrors(){
//        onView(withId(R.id.UserNameTextEdit)).perform(typeText("brad123"));
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.SubmitButton)).perform(click());
//
//    }
//
//
//    @Test
//    public void invalidBirthday() {
//        onView(withId(R.id.nameTextEdit)).perform(typeText("brad"));
//        onView(withId(R.id.UserNameTextEdit)).perform(typeText("brad123"));
//        onView(withId(R.id.EmailTextEdit)).perform(typeText("bradanissen@gmail.com"));
//        onView(withId(R.id.locationTextEdit)).perform(typeText("Seattle"));
//        onView(withId(R.id.jobTextEdit)).perform(typeText("Server"));
//        onView(withId(R.id.descriptionTextEdit)).perform(typeText("Long walks"));
//        Espresso.closeSoftKeyboard();
//        setDate(R.id.dateTextView, 2001, 8, 13);
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.SubmitButton)).perform(click());
//        onView(withId(R.id.SubmitButton)).check(matches(withText("birthday input error\n")));
//
//    }
//
//    @Test
//    public void flipOnSecondActivity() {
//
//    }
//
    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }
}