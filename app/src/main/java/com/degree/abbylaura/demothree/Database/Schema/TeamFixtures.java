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
    public static final String KEY_HomeTeam = "HomeTeam";
    public static final String KEY_AwayTeam = "AwayTeam";


    private String fixtureID;
    private String fixtureDate;
    private String fixtureLocation;
    private String homeTeam;
    private String awayTeam;


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

    public void setHomeTeam(String homeTeamID) {
        this.homeTeam = homeTeamID;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setAwayTeam(String awayTeamID) {
        this.awayTeam = awayTeamID;
    }
    public String getAwayTeam() {
        return awayTeam;
    }
}
