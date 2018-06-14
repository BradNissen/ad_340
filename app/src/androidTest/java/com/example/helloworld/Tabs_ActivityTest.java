package com.example.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TimePicker;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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

    @Test
    public void adjustSetting(){

        Matcher<View> matcher2 = allOf(withText("Settings"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher2).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.timePicker)).perform(setTime(2,30));
        SystemClock.sleep(1000);

        onView(withId(R.id.distance)).perform(setProgress(5));

        SystemClock.sleep(1000);
        onView(withId(R.id.age)).perform(scrollTo());
        onView(withId(R.id.age)).perform(setProgress(5));

        onView(withId(R.id.genders)).perform(scrollTo());
        onView(withId(R.id.genders)).perform(click());


        onData(anything()).atPosition(1).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.switchButton)).perform(scrollTo());
        onView(withId(R.id.switchButton)).perform(click());

        onView(withId(R.id.update)).perform(scrollTo());
        onView(withId(R.id.update)).perform(click());
        SystemClock.sleep(1000);

        onView(withText("Saved!")).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(1000);

        Matcher<View> matcher3 = allOf(withText("Matches"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher3).perform(click());
        SystemClock.sleep(3000);

        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SystemClock.sleep(2000);
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SystemClock.sleep(2000);



    }


    public static ViewAction setTime(final int hour, final int minute) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                TimePicker tp = (TimePicker) view;
                tp.setCurrentHour(hour);
                tp.setCurrentMinute(minute);
            }
            @Override
            public String getDescription() {
                return "Set the passed time into the TimePicker";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(TimePicker.class);
            }
        };
    }



    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
            @Override
            public String getDescription() {
                return "Set a progress on a SeekBar";
            }
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }
}