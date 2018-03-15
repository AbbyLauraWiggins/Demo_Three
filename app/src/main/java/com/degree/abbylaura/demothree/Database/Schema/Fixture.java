package com.degree.abbylaura.demothree.Database.Schema;

import android.database.sqlite.SQLiteDatabase;

import java.io.FileNotFoundException;

/**
 * Created by abbylaura on 01/03/2018.
 */

public class Fixture {

    public static final String TAG = Fixture.class.getSimpleName();
    public static final String TABLE = "Fixture";
    public static final String KEY_FixturePrimary = "FixturePrimary";
    public static final String KEY_TeamId = "TeamId"; //FOREIGN KEY FROM TEAM
    public static final String KEY_FixtureId = "FixtureId"; //FOREIGN KEY FROM TEAMFIXTURE
    public static final String KEY_FixturePoints = "FixturePoints";
    public static final String KEY_Forward = "Forward"; //ID OF FORWARD OF THE MATCh
    public static final String KEY_Back = "Back"; //ID OF BACK OF THE MATCH
    public static final String KEY_Player = "Player"; //ID OF PLAYER OF THE MATCH


    private String teamId;
    private String fixtureID;
    private String fixturePoints;
    private String forward;
    private String back;
    private String player;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getFixtureId() {
        return fixtureID;
    }

    public void setFixtureId(String fixtureID) {
        this.fixtureID = fixtureID;
    }

    public String getFixturePoints() {
        return fixturePoints;
    }

    public void setFixturePoints(String fixturePoints) {
        this.fixturePoints = fixturePoints;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
