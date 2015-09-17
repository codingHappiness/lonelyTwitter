package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by mstrong on 9/16/15.
 */
public class HappyMood extends Mood {
    public HappyMood() {
    }

    public HappyMood(Date date) {

        super(date);
    }

    public String getMood(){
        return ":)";
    }
}
