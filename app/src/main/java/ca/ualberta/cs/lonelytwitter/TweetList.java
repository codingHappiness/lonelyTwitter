package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mstrong on 9/30/15.
 */
public class TweetList implements MyObservable {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();//model
    private ArrayList<MyObserver> myObservers = new ArrayList<MyObserver>();//model
    public void add(Tweet tweet){//controller
        if (this.tweets.contains(tweet)){
            throw new IllegalArgumentException();
        }
        else {
            this.tweets.add(tweet);
        }
        notifyObservers();
    }
    public void delete(Tweet tweet){//controller
        this.tweets.remove(tweet);
    }
    public boolean hasTweet(Tweet tweet){//
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

    public void addObserver(MyObserver o) {
        myObservers.add(o);
    }
    public void notifyObservers(){
        for (MyObserver observer : myObservers){
            observer.myNotify();
        }
    }
}
