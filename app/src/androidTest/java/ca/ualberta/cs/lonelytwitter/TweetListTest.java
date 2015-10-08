package ca.ualberta.cs.lonelytwitter;


import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mstrong on 9/30/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver{

    private boolean wasNotified = false;

    public void myNotify()
    {
        wasNotified = true;
    }

    public TweetListTest() {
        super(LonelyTwitterActivity.class);

    }

    public void testDeleteTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihi");
        tweetList.add(tweet);
        tweetList.delete(tweet);
        assertFalse(tweetList.hasTweet(tweet)
);    }

    public void testHasTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihi");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }
    public void testAddTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihi");
        try {
            tweetList.add(tweet);
        }
        catch (IllegalArgumentException e){
            System.out.println("e");
            fail();
        }
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testTweetCount(){
        TweetList tweetList = new TweetList();
        assertTrue(tweetList.tweetCount()==0);
        Tweet tweet = new NormalTweet("hihihi");
        tweetList.add(tweet);
        assertTrue(tweetList.tweetCount() == 1);
    }

    public void testGetTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihi");
        tweetList.add(tweet);
        Tweet returned = tweetList.getTweet(0);
        assertTrue((tweet.equals(returned)));

    }

    public void testGetTweets(){
        TweetList tweetList = new TweetList();
        Tweet tweet2 = new NormalTweet("oldest", new Date(0));
        tweetList.add(tweet2);
        Tweet tweet = new NormalTweet("newest", new Date(10000));
        tweetList.add(tweet);
        tweet = new NormalTweet("middle", new Date(500));
        tweetList.add(tweet);
        assertTrue(tweetList.getTweet(2) == tweet);
        ArrayList<Tweet> testList = tweetList.getTweets();
        assertTrue(testList.get(1)==tweet);
        assertTrue(testList.get(0)==tweet2);
    }

    public void testGetTweetType(){

    }



    public void testTweetListChanged() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihi");
        tweetList.addObserver(this);
        wasNotified = false;
        assertFalse(wasNotified);
        tweetList.add(tweet);
        assertTrue(wasNotified);
    }
}