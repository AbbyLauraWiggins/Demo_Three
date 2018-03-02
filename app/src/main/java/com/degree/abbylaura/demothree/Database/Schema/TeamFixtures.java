package com.degree.abbylaura.demothree.Database.Schema;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamFixtures {

    public static final String TAG = TeamFixtures.class.getSimpleName();
    public static final String TABLE = "TeamFixtures";
    public static final String KEY_FixtureId = "FixtureId"; //PRIMARY KEY
    public static final String KEY_TeamFixtureDate = "TeamFixtureDate";
    public static final String KEY_TeamFixtureLocation = "TeamFixtureLocation";


    private String fixtureID;
    private String fixtureDate;
    private String fixtureLocation;


    public String getFixtureId() {
        return fixtureID;
    }

    public void setFixtureId(String fixtureID) {
        this.fixtureID = fixtureID;
    }

    public String getFixtureDate() {
        return fixtureDate;
    }

    public void setFixtureDate(String fixtureDate) {
        this.fixtureDate = fixtureDate;
    }

    public String getFixtureLocation() {
        return fixtureLocation;
    }

    public void setFixtureLocation(String fixtureLocation) {
        this.fixtureLocation = fixtureLocation;
    }
}
