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
    public static final String KEY_TriesScored = "TriesScored";
    public static final String KEY_TriesSucceeded = "TriesSucceeded";
    public static final String KEY_Conversions = "Conversions";
    public static final String KEY_ConversionsSucceeded = "ConversionsSucceeded";
    public static final String KEY_ScrumsWon = "ScrumsWon";
    public static final String KEY_ScrumsLost = "ScrumsLost";
    public static final String KEY_MaulsWon = "MaulsWon";
    public static final String KEY_MaulsLost = "MaulsLost";
    public static final String KEY_LineOutsWon = "LineOutsWon";
    public static final String KEY_LineOutsLost = "LineOutsLost";
    public static final String KEY_DropGoals = "DropGoals";
    public static final String KEY_PenaltyKicks = "PenaltyKicks";

    private String teamId;
    private String fixtureID;
    private String fixturePoints;
    private String forward;
    private String back;
    private String player;
    private String triesScored;
    private String triesSucceeded;
    private String conversions;
    private String conversionsSucceeded;
    private String scrumsWon;
    private String scrumsLost;
    private String maulsWon;
    private String maulsLost;
    private String lineOutsWon;
    private String lineOutsLost;
    private String dropGoals;
    private String penaltyKicks;

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


    public String getTriesScored() {
        return triesScored;
    }

    public void setTriesScored(String tries) {
        this.triesScored = tries;
    }

    public String getTriesSucceeded() {
        return triesSucceeded;
    }

    public void setTriesSucceeded(String tries) {
        this.triesSucceeded = tries;
    }

    public String getConversions() {
        return conversions;
    }

    public void setConversions(String conversions) {
        this.conversions = conversions;
    }

    public String getConversionsSucceeded() {
        return conversionsSucceeded;
    }

    public void setConversionsSucceeded(String conversions) {
        this.conversionsSucceeded = conversions;
    }

    public String getScrumsWon() {
        return scrumsWon;
    }

    public void setScrumsWon(String scrumsWon) {
        this.scrumsWon = scrumsWon;
    }

    public String getScrumsLost() {
        return scrumsLost;
    }

    public void setScrumsLost(String scrumsLost) {
        this.scrumsLost = scrumsLost;
    }

    public String getMaulsWon() {
        return maulsWon;
    }

    public void setMaulsWon(String maulsWon) {
        this.maulsWon = maulsWon;
    }

    public String getMaulsLost() {
        return maulsLost;
    }

    public void setMaulsLost(String maulsLost) {
        this.maulsLost = maulsLost;
    }

    public String getLineOutsWon() {
        return lineOutsWon;
    }

    public void setLineOutsWon(String lineOutsWon) {
        this.lineOutsWon = lineOutsWon;
    }

    public String getLineOutsLost() {
        return lineOutsLost;
    }

    public void setLineOutsLost(String lineOutsLost) {
        this.lineOutsLost = lineOutsLost;
    }


    public String getDropGoals() {
        return dropGoals;
    }

    public void setDropGoals(String dropgoals) {
        this.dropGoals = dropgoals;
    }

    public String getPenaltyKicks() {
        return penaltyKicks;
    }

    public void setPenaltyKicks(String penaltyKicks) {
        this.penaltyKicks = penaltyKicks;
    }

}
