/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.photogalleryproject3;

import android.app.Activity;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

//make tests run in alphabetical order
//https://stackoverflow.com/questions/25308301/test-order-with-espresso
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UITest
{
    ///**
    // * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
    // * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
    //*/
    @Rule public ActivityScenarioRule<com.example.photogalleryproject3.MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(com.example.photogalleryproject3.MainActivity.class);

    @Test
    public void TestCaptionSearch()
    {
        //The test assumes that 2 pictures have been taken already!!
        //Note: the test starts on the RIGHTMOST (earliest) picture.

        try{Thread.sleep(1000);}catch(Exception e){}
        //Enter a caption "Caption1"
        onView(withId(R.id.editTextCaption)).perform(replaceText("Caption1"), closeSoftKeyboard());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Press Save
        onView(withId(R.id.buttonSaveCaption)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Press left
        onView(withId(R.id.buttonLeft)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Enter another caption "Caption2"
        onView(withId(R.id.editTextCaption)).perform(replaceText("Caption2"), closeSoftKeyboard());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Press Save
        onView(withId(R.id.buttonSaveCaption)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Press left (to show it does nothing)
        onView(withId(R.id.buttonLeft)).perform(click());
        //Press right three times (to show attempting to move too far to the right does nothing)
        onView(withId(R.id.buttonRight)).perform(click());
        onView(withId(R.id.buttonRight)).perform(click());
        onView(withId(R.id.buttonRight)).perform(click());
        //Press Search
        onView(withId(R.id.btnSnap2)).perform(click());
        //In the search window Enter a string "1"
        onView(withId(R.id.search_Captions)).perform(typeText("1"), closeSoftKeyboard());
        //In the search window Press Search
        onView(withId(R.id.button)).perform(click());
        //Press right (it should do nothing)
        onView(withId(R.id.buttonRight)).perform(click());
        //Press left twice (it should do nothing)
        onView(withId(R.id.buttonLeft)).perform(click());
        onView(withId(R.id.buttonLeft)).perform(click());
        //Verify that the result contains "1"
        onView(withId(R.id.editTextCaption)).check(matches(withText("Caption1")));
        try{Thread.sleep(2000);}catch(Exception e){}
        //Clear the search for the next test
        onView(withId(R.id.btnSnap2)).perform(click());
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void TestFolders()
    {
        //The test assumes that 2 pictures have been taken already!!
        //It also assumes the previous test has been run, so that the pictures are called Caption1 and Caption2.
        //It also assumes that it's on the main screen of the photo gallery app.

        try{Thread.sleep(1000);}catch(Exception e){}
        //add picture called Caption1 to Folder 1
        onView(withId(R.id.btnAddToFolder)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        onView(withId(R.id.textViewFolder1)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Now go to Folder 1
        onView(withId(R.id.btnFolderView)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        onView(withId(R.id.textViewFolder1)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Press left (it should do nothing)
        onView(withId(R.id.buttonLeft)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Press right twice (it should do nothing)
        onView(withId(R.id.buttonRight)).perform(click());
        onView(withId(R.id.buttonRight)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        //Verify that Caption1 is in Folder1
        onView(withId(R.id.editTextCaption)).check(matches(withText("Caption1")));
        try{Thread.sleep(1000);}catch(Exception e){}
    }

    @Test
    public void TestLocationSearch()
    {
        //The test assumes that at least one picture containing location information has been taken already!!
        //It also assumes the previous test has been run, so that the first picture to show up in the search will be Caption1.
        // UI test for sprint 2. IL

        //Press Search
        onView(withId(R.id.btnSnap2)).perform(click());
        //In the location search window Enter the desire search for upper bound latitude
        onView(withId(R.id.search_fromLatitude)).perform(typeText("49.300000"), closeSoftKeyboard());
        //In the location search window Enter the desire search for lower bound latitude
        onView(withId(R.id.search_toLatitude)).perform(typeText("49.100000"), closeSoftKeyboard());
        //In the location search window Enter the desire search for upper bound longitude
        onView(withId(R.id.search_fromLongitude)).perform(typeText("-122.000000"), closeSoftKeyboard());
        //In the location search window Enter the desire search for lower bound longitude
        onView(withId(R.id.search_toLongitude)).perform(typeText("-124.000000"), closeSoftKeyboard());
        //In the search window Press Search to go back to main activity
        onView(withId(R.id.button)).perform(click());
        //Verify that there is a result
        onView(withId(R.id.editTextCaption)).check(matches(withText("Caption1")));
        try{Thread.sleep(1000);}catch(Exception e){}
    }

    @Test
    public void zTestDelete() //tests run in alphabetical order, and this one must be last
    {
        //The test assumes that 2 pictures have been taken already!!
        //and that it is on the main screen of the photo gallery app.

        try{Thread.sleep(1000);}catch(Exception e){}
        onView(withId(R.id.btnDelete)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        onView(withId(R.id.btnDelete)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e){}
        onView(withId(R.id.editTextCaption)).check(matches(withText("No files found")));
        try{Thread.sleep(2000);}catch(Exception e){}
    }
}
