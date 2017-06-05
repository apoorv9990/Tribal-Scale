package com.tribalscale;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.tribalscale.activities.MainActivity;
import com.tribalscale.network.CoreApi;
import com.tribalscale.network.responses.GetPersonsResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    private GetPersonsResponse response;

    public GetPersonsResponse loadJSONFromAsset() {
        String json = null;
        GetPersonsResponse response = null;
        try {

            InputStream is = mActivityRule.getActivity().getAssets().open("testData.txt");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

            Gson gson = new Gson();

            response = gson.fromJson(json, GetPersonsResponse.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return response;

    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Before
    public void setUp() {
        response = loadJSONFromAsset();

        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().showPersons(response.getResults());
            }
        });
    }

    @Test
    public void checkRecyclerItem() {
        onView(withRecyclerView(R.id.recycler_view_persons).atPosition(0))
                .check(matches(hasDescendant(withText(response.getResults().get(0).getName().getFullName()))));
    }

    @Test
    public void clickRecyclerItem() throws InterruptedException {
        onView(withId(R.id.recycler_view_persons))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.text_detail_name))
                .check(matches(withText(response.getResults().get(0).getName().getFullName())));
    }
}
