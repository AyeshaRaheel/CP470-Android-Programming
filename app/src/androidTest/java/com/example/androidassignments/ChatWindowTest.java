package com.example.androidassignments;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ChatWindowTest {

    @Rule
    public ActivityScenarioRule<ChatWindow> rule =
            new ActivityScenarioRule<>(ChatWindow.class);

    private void send(String text) {
        onView(withId(R.id.chatEditText)).perform(typeText(text));
        closeSoftKeyboard();
        onView(withId(R.id.sendButton)).perform(click());
    }

    @Test
    public void sendingMessage_clearsEditText() {
        send("Hello");
        onView(withId(R.id.chatEditText)).check(matches(withText("")));
    }

    @Test
    public void sendingMessage_showsItInTheList() {
        send("Hello");
        onData(anything())
                .inAdapterView(withId(R.id.chatListView))
                .atPosition(0)
                .onChildView(withId(R.id.message_text))
                .check(matches(withText("Hello")));
    }

    @Test
    public void sendingTwoMessages_bothAppearInOrder() {
        send("First");
        send("Second");
        onData(anything()).inAdapterView(withId(R.id.chatListView)).atPosition(0)
                .onChildView(withId(R.id.message_text)).check(matches(withText("First")));
        onData(anything()).inAdapterView(withId(R.id.chatListView)).atPosition(1)
                .onChildView(withId(R.id.message_text)).check(matches(withText("Second")));
    }

    @Test
    public void chatScreen_showsSendButtonAndInput() {
        onView(withId(R.id.sendButton)).check(matches(isDisplayed()));
        onView(withId(R.id.chatEditText)).check(matches(isDisplayed()));
    }
}