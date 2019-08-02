package com.snowplowanalytics.snowplowtrackerdemo.uitest;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.snowplowanalytics.snowplowtrackerdemo.Demo;
import com.snowplowanalytics.snowplowtrackerdemo.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.snowplowanalytics.snowplowtrackerdemo.BuildConfig.MICRO_ENDPOINT;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    private String uri;

    @Rule
    public ActivityTestRule<Demo> activityRule
            = new ActivityTestRule<>(Demo.class);

    @Before
    public void initValidString() {
        uri = MICRO_ENDPOINT;
    }

    @Test
    public void startDemoTest() throws InterruptedException {

        Espresso.closeSoftKeyboard();

        activityRule.getActivity().sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        // Type text and then press the button.
        onView( withId(R.id.emitter_uri_field) ).check(matches(withHint("Enter endpoint here…")));
        onView(withId(R.id.emitter_uri_field)).perform(replaceText(uri), closeSoftKeyboard());
        onView(withId(R.id.emitter_uri_field)).check(matches(withText(uri)));
        onView(withId(R.id.btn_lite_start)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_lite_start)).perform(click());

        Thread.sleep(20000);

    }
}
