package com.example.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class Tabs_ActivityTest {

    String name = "Brad";
    String age = "27";
    String location = "Seattle, WA";
    String job = "Server";
    String desc = "I like long walks";


    //create rule for the second page :http://blog.xebia.com/android-intent-extras-espresso-rules/
    @Rule
    public ActivityTestRule<Tabs_Activity> activityTestRule = new ActivityTestRule<Tabs_Activity>(Tabs_Activity.class) {

        @Override
        protected Intent getActivityIntent() {

            Intent intent = new Intent();

            intent.putExtra(Constants.KEY_NAME, name);
            intent.putExtra(Constants.KEY_AGE, age);
            intent.putExtra(Constants.KEY_LOCATION, location);
            intent.putExtra(Constants.KEY_JOB, job);
            intent.putExtra(Constants.KEY_DESC, desc);

            return intent;
        }
    };

//    //PASSED
//    @Test
//    public void rotateScreenTest(){
//        //check the fields are filled.
//        onView(withId(R.id.nameDisplayTextView)).check(matches(withText(name)));
//        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
//        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(location)));
//        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
//        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));
//
//        //flip the screen
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//        //check the fields again
//        onView(withId(R.id.nameDisplayTextView)).check(matches(withText(name)));
//        onView(withId(R.id.ageDisplayTextView)).check(matches(withText(age)));
//        onView(withId(R.id.locationDisplayTextView)).check(matches(withText(location)));
//        onView(withId(R.id.occupationDisplayTextView)).check(matches(withText(job)));
//        onView(withId(R.id.descriptionDisplayTextView)).check(matches(withText(desc)));
//
//        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//    }
////

    @Test
    public void swipeTabsTest(){

        Matcher<View> matcher = allOf(withText("Profile"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(1000);


        Matcher<View> matcher1 = allOf(withText("Matches"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher1).perform(click());
        SystemClock.sleep(1000);

        Matcher<View> matcher2 = allOf(withText("Settings"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher2).perform(click());
        SystemClock.sleep(1000);

    }

}