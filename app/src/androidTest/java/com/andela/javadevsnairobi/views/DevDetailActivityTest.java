package com.andela.javadevsnairobi.views;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andela.javadevsnairobi.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class DevDetailActivityTest {

    @Rule
    public ActivityTestRule<DevDetailActivity> activityTestRule = new ActivityTestRule<>(DevDetailActivity.class, true, false);

    @Before
    public void customIntentToStartActivity() {
        Intent intent = new Intent();
        intent.putExtra("username", "k33ptoo");
        activityTestRule.launchActivity(intent);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void viewDisplaysShareButton() {
        onView(allOf(withId(R.id.dev_share_btn)))
                .check(matches(isDisplayed()));
    }

}
