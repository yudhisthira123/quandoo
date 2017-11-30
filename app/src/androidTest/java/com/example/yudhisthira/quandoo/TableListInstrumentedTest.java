package com.example.yudhisthira.quandoo;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.yudhisthira.quandoo.utils.TestUtils;
import com.orhanobut.hawk.Hawk;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by yudhisthira
 */

public class TableListInstrumentedTest {
    @Rule
    public ActivityTestRule<TableActivity> activityTestRule = new ActivityTestRule<TableActivity>(TableActivity.class, false, true);

    private MockWebServer mockWebServer = new MockWebServer();

    @Before
    public void setup() {
        try{
            Hawk.deleteAll();
            mockWebServer.start(555);
        }catch (Exception e) {

        }
    }

    @Test
    public void showErrorOnFailure() {
        mockWebServer.enqueue(TestUtils.getMockGenericError());

        activityTestRule.launchActivity(new Intent());
        TestUtils.waitUntill(2000);

        Espresso.onView(ViewMatchers.withId(R.id.errorLyt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testRetryWithSuccessAfterError() {
        mockWebServer.enqueue(TestUtils.getMockGenericError());
        mockWebServer.enqueue(TestUtils.geMockGenericCustomerSuccess(activityTestRule.getActivity()));

        activityTestRule.launchActivity(new Intent());

        TestUtils.waitUntill(2000);
        Espresso.onView(ViewMatchers.withId(R.id.errorLyt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        TestUtils.waitUntill(1000);
        Espresso.onView(ViewMatchers.withId(R.id.button_refresh)).perform(ViewActions.click());

        TestUtils.waitUntill(3000);
        Espresso.onView(ViewMatchers.withId(R.id.table_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void showTableListOnSuccess() {
        mockWebServer.enqueue(TestUtils.geMockGenericCustomerSuccess(activityTestRule.getActivity()));

        activityTestRule.launchActivity(new Intent());

        TestUtils.waitUntill(2000);
        Espresso.onView(ViewMatchers.withId(R.id.table_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    @After
    public void tearDown() {
        try{
            mockWebServer.shutdown();
        }catch (Exception e) {

        }
    }
}
