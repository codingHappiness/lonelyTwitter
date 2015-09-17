package ca.ualberta.cs.lonelytwitter;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mstrong on 9/16/15.
 */
public abstract class Tweet extends Object {
    private String text;
    private Date date;
    private ArrayList<Mood> moodArray = new ArrayList<Mood>();

    public void addMood(Mood myMood){
        moodArray.add(myMood);
    }

    public Tweet(String tweet, Date date) {
        this.text = tweet;
        this.date = date;
    }

    public Tweet(String tweet) {
        this.text = tweet;
        this.date = new Date();
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(text.length() <= 140)
        {
            this.text = text;
        }
        else{
            throw new IllegalArgumentException("Tweet is too long!");
        }
    }

    public abstract Boolean isImportant();
}
