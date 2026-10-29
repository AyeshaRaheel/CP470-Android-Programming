package com.example.androidassignments;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginFlowTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> rule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    private void login(String email, String password) {
        onView(withId(R.id.login_name)).perform(replaceText(email));
        onView(withId(R.id.password)).perform(replaceText(password));
        closeSoftKeyboard();
        onView(withId(R.id.login_button)).perform(click());
    }

    @Test
    public void invalidEmail_staysOnLoginScreen() {
        login("not-an-email", "secret123");
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        assertTrue(Intents.getIntents().isEmpty());
    }

    @Test
    public void emptyPassword_staysOnLoginScreen() {
        login("user@example.com", "");
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        assertTrue(Intents.getIntents().isEmpty());
    }

    @Test
    public void validLogin_launchesMainActivity() {
        login("user@example.com", "secret123");
        intended(hasComponent(MainActivity.class.getName()));
    }
}