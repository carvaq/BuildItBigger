package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTaskTest extends ActivityTestRule<MainActivity> {

    public EndpointAsyncTaskTest() {
        super(MainActivity.class);
    }

    /*
    Reference:
    http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
    https://google.github.io/android-testing-support-library/docs/espresso/setup/
    */
    @Test
    public void testAsyncTaskReturnsNonEmptyValue() {
        final CountDownLatch signal = new CountDownLatch(1);
        new EndpointAsyncTask(getActivity()) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull("No joke available", result);
                signal.countDown();
            }
        }.execute();
        try {
            signal.await();// wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}