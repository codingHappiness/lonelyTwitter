package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditTweet() {
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        //reset app to known state
        activity.getTweets().clear();

        // add a tweet using the UI
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("test tweet");
            }
        });
        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //make sure the tweet was actually added
        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("test tweet", tweet.getText());

        Instrumentation.ActivityMonitor receiverActivityMonitor = getInstrumentation().addMonitor(EditTweetActivity.class.getName(), null, false);
        //click n the tweet to edit
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        final EditTweetActivity receiverActivity = (EditTweetActivity) receiverActivityMonitor.waitForActivityWithTimeout(1000);
        //ensure the tweet editor starts up
        assertNotNull("Receiver activity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called", 1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of the wrong type", EditTweetActivity.class, receiverActivity.getClass());

      //  getInstrumentation().waitForIdleSync();



        //test that the editor starts up with the right tweet to edit
        Tweet tweet1 = new NormalTweet("yo");

        //These tests didn't get done.
        receiverActivity.addTweet(tweet1);
        Tweet tweet2 = receiverActivity.getTweet();
        assertEquals(tweet2, tweet1);


        //test that we can edit that tweet

        //test that we can push some kind of save button for that tweet

        //the new modified tweet text was actually saved

        //the new modified tweet text is displayed on the other activity

        //clean up our activities at the end of the test
        receiverActivity.finish();
    }
}