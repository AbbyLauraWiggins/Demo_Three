package com.degree.abbylaura.demothree.Database.Schema;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class StrengthAndConditioning {

    public static final String TAG = StrengthAndConditioning.class.getSimpleName();
    public static final String TABLE = "StrengthAndConditioning";
    public static final String KEY_SessionId = "SessionId"; //PRIMARY KEY
    public static final String KEY_SessionDate = "SessionDate";
    public static final String KEY_SessionTime = "SessionTime";


    private String sessionID;
    private String sessionDate;
    private String sessionTime;


    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }
}
