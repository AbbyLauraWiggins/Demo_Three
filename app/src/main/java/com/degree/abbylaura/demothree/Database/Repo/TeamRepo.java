package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.App.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Team;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamRepo {
    private Team team;

    public TeamRepo(){

        team = new Team();

    }


    public static String createTable(){
        return "CREATE TABLE " + Team.TABLE  + "("
                + Team.KEY_TeamId  + "   PRIMARY KEY,"
                + Team.KEY_TeamName + " TEXT,"
                + Team.KEY_TeamLocation + " TEXT,"
                + Team.KEY_TeamCurPoints + " TEXT)";
    }


    public int insert(Team team) {
        int teamId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Team.KEY_TeamId, team.getTeamId());
        values.put(Team.KEY_TeamName, team.getTeamName());
        values.put(Team.KEY_TeamLocation, team.getTeamLocation());
        values.put(Team.KEY_TeamCurPoints, team.getTeamCurPoints());


        // Inserting Row
        teamId=(int)db.insert(Team.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return teamId;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Team.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
