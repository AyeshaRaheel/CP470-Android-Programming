package com.example.androidassignments;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestToolbarTest {

    @Rule
    public ActivityScenarioRule<TestToolbar> rule =
            new ActivityScenarioRule<>(TestToolbar.class);

    @Test
    public void toolbar_showsThreeIcons() {
        onView(withId(R.id.action_one)).check(matches(isDisplayed()));
        onView(withId(R.id.action_two)).check(matches(isDisplayed()));
        onView(withId(R.id.action_three)).check(matches(isDisplayed()));
    }

    @Test
    public void itemOne_showsDefaultSnackbar() {
        onView(withId(R.id.action_one)).perform(click());
        onView(withText("You selected item 1")).check(matches(isDisplayed()));
    }

    @Test
    public void itemTwo_showsDialogAndNoKeepsActivity() {
        onView(withId(R.id.action_two)).perform(click());
        onView(withText("Do you want to go back?")).check(matches(isDisplayed()));
        onView(withText("No")).perform(click());
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        assertEquals(Lifecycle.State.RESUMED, rule.getScenario().getState());
    }

    @Test
    public void itemTwo_yesFinishesActivity() throws InterruptedException {
        onView(withId(R.id.action_two)).perform(click());
        onView(withText("Yes")).perform(click());

        Lifecycle.State state = rule.getScenario().getState();
        for (int i = 0; i < 20 && state != Lifecycle.State.DESTROYED; i++) {
            Thread.sleep(250);
            state = rule.getScenario().getState();
        }
        assertEquals(Lifecycle.State.DESTROYED, state);
    }

    @Test
    public void itemThree_setsNewSnackbarMessage() {
        onView(withId(R.id.action_three)).perform(click());
        onView(withId(R.id.new_message)).perform(typeText("Hello from me"));
        closeSoftKeyboard();
        onView(withText("Yes")).perform(click());

        onView(withId(R.id.action_one)).perform(click());
        onView(withText("Hello from me")).check(matches(isDisplayed()));
    }

    @Test
    public void fab_showsOwnMessage() {
        onView(withId(R.id.fab)).perform(click());
        onView(withText("This is my own message")).check(matches(isDisplayed()));
    }
}