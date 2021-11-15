package com.test.mvcnytimessample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.mvcnytimessample.activities.MainActivity;
import com.test.mvcnytimessample.adapter.MainRVAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class
            , true
            , true);

    
    // this test is to check main RV is not null
    @Test
    public void checkRVNotNull() {
        MainActivity activity = activityRule.getActivity();
        View viewByIdw = activity.findViewById(R.id.mainRV);
        assertThat(viewByIdw, notNullValue());
        assertThat(viewByIdw, instanceOf(RecyclerView.class));
        RecyclerView productRecyclerView = (RecyclerView) viewByIdw;
        RecyclerView.Adapter adapter = productRecyclerView.getAdapter();
        assertThat(adapter, instanceOf(MainRVAdapter.class));
    }
}
