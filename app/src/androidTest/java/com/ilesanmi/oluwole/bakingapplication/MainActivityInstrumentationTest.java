package com.ilesanmi.oluwole.bakingapplication;

import com.ilesanmi.oluwole.bakingapplication.ui.main.MainActivity;


import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void isRecyclerViewItemTextOnePresent() {
        //        Espresso.onView(withId(R.id.recycler_view)).check(matches(isCompletelyDisplayed()))
        //               .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        String itemText = mActivityRule.getActivity().getResources().getString(
                R.string.main_activity_recycler_view_first_item);
        Espresso.onView(withText(itemText)).check(matches(isDisplayed()));

    }

    @Test
    public void isRecyclerViewItemTextTwoPresent() {

        String itemText = mActivityRule.getActivity().getResources().getString(
                R.string.main_activity_recycler_view_second_item);
        Espresso.onView(withText(itemText)).check(matches(isDisplayed()));

    }

    @Test
    public void isRecyclerViewItemTextThreePresent() {

        String itemText = mActivityRule.getActivity().getResources().getString(
                R.string.main_activity_recycler_view_third_item);
        Espresso.onView(withText(itemText)).check(matches(isDisplayed()));

    }

    @Test
    public void isRecyclerViewItemTextFourPresent() {

        String itemText = mActivityRule.getActivity().getResources().getString(
                R.string.main_activity_recycler_view_fourth_item);
        Espresso.onView(withText(itemText)).check(matches(isDisplayed()));

    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }


}
