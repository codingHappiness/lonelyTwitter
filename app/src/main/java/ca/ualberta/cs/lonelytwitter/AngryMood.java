package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by mstrong on 9/16/15.
 */
public class AngryMood extends Mood {
    public AngryMood() {
    }

    public AngryMood(Date date) {

        super(date);
    }

    public String getMood(){
        return ">:(";
    }
}
