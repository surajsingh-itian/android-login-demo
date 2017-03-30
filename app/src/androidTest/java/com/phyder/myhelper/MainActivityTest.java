package com.phyder.myhelper;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void emptyUsername() {
        onView(withId(R.id.txtUsername))
                .perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .perform(click());

        onView(withId(R.id.tilUsername))
                .check(matches(TextInputLayoutMatchers.withErrorText("Username is mandatory.")));
    }

    @Test
    public void emptyPassword() {
        onView(withId(R.id.txtUsername))
                .perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.txtPassword))
                .perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .perform(click());

        onView(withId(R.id.tilPassword))
                .check(matches(TextInputLayoutMatchers.withErrorText("Password is mandatory.")));
    }

    @Test
    public void invalidLogin() {
        onView(withId(R.id.txtUsername))
                .perform(typeText("admina"), closeSoftKeyboard());
        onView(withId(R.id.txtPassword))
                .perform(typeText("sbmp"), closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .perform(click());

//        onView(withText("Error while connecting to Server.")).inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
    }

    @Test
    public void validLogin() {
        onView(withId(R.id.txtUsername))
                .perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.txtPassword))
                .perform(typeText("password"), closeSoftKeyboard());

        onView(withId(R.id.btnLogin))
                .perform(click());
    }

}
