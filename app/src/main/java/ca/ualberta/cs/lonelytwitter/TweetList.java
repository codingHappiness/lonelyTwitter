package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mstrong on 9/30/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    public void add(Tweet tweet){
        if (this.tweets.contains(tweet)){
            throw new IllegalArgumentException();
        }
        else {
            this.tweets.add(tweet);
        }
    }
    public void delete(Tweet tweet){
        this.tweets.remove(tweet);
    }
    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }
    public Tweet getTweet(int i){
        return tweets.get(i);
    }
    public int tweetCount(){
        return this.tweets.size();
    }

    public ArrayList<Tweet> getTweets(){
        //return list of tweets
        ArrayList<Tweet> sortTweets = this.tweets;
        //http://stackoverflow.com/a/10471029
        Collections.sort(sortTweets, new Comparator<Tweet>() {
            public int compare(Tweet lhs, Tweet rhs) {
                return lhs.getDate().compareTo(rhs.getDate());
            }
        });
        return sortTweets;
    }
}
