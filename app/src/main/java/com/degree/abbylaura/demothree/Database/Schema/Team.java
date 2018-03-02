package com.degree.abbylaura.demothree.Database.Schema;

/**
 * Created by abbylaura on 01/03/2018.
 */

public class Team {

    public static final String TAG = Team.class.getSimpleName();
    public static final String TABLE = "Team";
    public static final String KEY_TeamId = "TeamId"; //PRIMARY KEY
    public static final String KEY_TeamName = "TeamName";
    public static final String KEY_TeamLocation = "TeamLocation";
    public static final String KEY_TeamCurPoints = "TeamCurPoints";

    private String teamId;
    private String teamName;
    private String teamLocation;
    private String teamCurPoints;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLocation() {
        return teamLocation;
    }

    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    public String getTeamCurPoints() {
        return teamCurPoints;
    }

    public void setTeamCurPoints(String teamCurPoints) {
        this.teamCurPoints = teamCurPoints;
    }
}
