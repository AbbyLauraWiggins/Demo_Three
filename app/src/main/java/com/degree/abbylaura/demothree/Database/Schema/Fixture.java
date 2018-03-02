package com.degree.abbylaura.demothree.Database.Schema;

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

    private String teamId;
    private String fixtureID;
    private String fixturePoints;

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

}
