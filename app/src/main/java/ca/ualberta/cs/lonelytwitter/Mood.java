package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by mstrong on 9/16/15.
 */
public abstract class Mood {
    private Date date;
    private String MoodString;

    public Mood(Date date) {
        this.date = date;
    }

    public Mood(){
        this.date = new Date();
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public String getMoodString() {

        return MoodString;
    }

    public Date getDate() {

        return date;
    }

    public abstract String getMood();
}
