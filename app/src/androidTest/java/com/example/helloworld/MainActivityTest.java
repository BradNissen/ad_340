package com.example.helloworld;

import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;


import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //String name = "brad";
    String name = Constants.KEY_NAME; // the constant is set to "name" but I think its fine for a test
    String age = "27";
    String username = "brad123";
    String email = "bradanissen@gmail.com";
    String city = "Seattle";
    String job = "Server";
    String desc = "Long Walks on the beach.";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    //PASSED
    @Test
    public void validInputTest() {


        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
        Espresso.closeSoftKeyboard();
        setDate(R.id.dateTextView, 1990, 8, 13);
        Espresso.closeSoftKeyboard();
        //Espresso.closeSoftKeyboard();
        SystemClock.sleep(2000);
        onView(withId(R.id.SubmitButton)).perform(scrollTo());

        //error here the bday isn't kept
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(scrollTo());
        onView(withId(R.id.SubmitButton)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(1000);
        Espresso.pressBackUnconditionally();
        SystemClock.sleep(1000);

    }

    //PASSED
    @Test
    public void rotatingSumbitwithValid() {
        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
        Espresso.closeSoftKeyboard();
        setDate(R.id.dateTextView, 1990, 8, 13);

        // rotate the screen before
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(scrollTo());
        onView(withId(R.id.SubmitButton)).perform(click());

    }

    //FAILED
    @Test
    public void birthdayIsToday() {

        //get today's date, then subtract 18
        LocalDate localDate = LocalDate.now();
        String bday_year = DateTimeFormatter.ofPattern("yyy").format(localDate);
        String bday_month = DateTimeFormatter.ofPattern("MM").format(localDate);
        String bday_day = DateTimeFormatter.ofPattern("dd").format(localDate);


        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
        Espresso.closeSoftKeyboard();
        setDate(R.id.dateTextView, Integer.valueOf(bday_year) - 18, Integer.valueOf(bday_month), Integer.valueOf(bday_day));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(scrollTo());
        onView(withId(R.id.SubmitButton)).perform(click());
    }

    //PASSED
    @Test
    public void inputSubmitError1(){
        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(scrollTo());
        onView(withId(R.id.SubmitButton)).perform(click());

    }

    //PASSED
    @Test
    public void inputSubmitError2(){
        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(scrollTo());
        onView(withId(R.id.SubmitButton)).perform(click());
    }

    //PASSED
    @Test
    public void invalidBirthday() {
        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
        Espresso.closeSoftKeyboard();
        setDate(R.id.dateTextView, 2005, 1, 1);
        Espresso.closeSoftKeyboard();
        //onView(withId(R.id.dateTextView)).perform(swipeUp());
        onView(withId(R.id.SubmitButton)).check(matches(withText("You must be 18 yrs. old.")));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SubmitButton)).perform(scrollTo());
        
        onView(withId(R.id.SubmitButton)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.SubmitButton)).check(matches(withText("birthday input error\n")));

    }

    //PASSED
//    @Test
//    public void checkDataOnNextActivity() {
//
//        onView(withId(R.id.nameTextEdit)).perform(typeText(name));
//        onView(withId(R.id.UserNameTextEdit)).perform(typeText(username));
//        onView(withId(R.id.EmailTextEdit)).perform(typeText(email));
//        onView(withId(R.id.locationTextEdit)).perform(typeText(city));
//        onView(withId(R.id.jobTextEdit)).perform(typeText(job));
//        onView(withId(R.id.descriptionTextEdit)).perform(typeText(desc));
//        Espresso.closeSoftKeyboard();
//        setDate(R.id.dateTextView, 1990, 8, 13);
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.SubmitButton)).perform(click());
//
//        Espresso.onView(withId(R.id.nameDisplayTextView)).check(matches(isDisplayed()));
//        Espresso.onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
//        Espresso.onView(withId(R.id.locationDisplayTextView)).check(matches(withText(city)));
//        Espresso.onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
//        Espresso.onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));
//    }

    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());


    }
}