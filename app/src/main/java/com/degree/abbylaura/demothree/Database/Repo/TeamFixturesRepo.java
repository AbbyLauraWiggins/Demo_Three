package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.App.DatabaseManager;

import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamFixturesRepo {
    private TeamFixtures teamFixtures;

    public TeamFixturesRepo(){

        teamFixtures = new TeamFixtures();

    }


    public static String createTable(){
        return "CREATE TABLE " + TeamFixtures.TABLE  + "("
                + TeamFixtures.KEY_FixtureId  + "   PRIMARY KEY,"
                + TeamFixtures.KEY_TeamFixtureDate + " TEXT,"
                + TeamFixtures.KEY_TeamFixtureLocation + " TEXT)";
    }


    public int insert(TeamFixtures teamFixtures) {
        int tfID;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(TeamFixtures.KEY_FixtureId, teamFixtures.getFixtureId());
        values.put(TeamFixtures.KEY_TeamFixtureDate, teamFixtures.getFixtureDate());
        values.put(TeamFixtures.KEY_TeamFixtureLocation, teamFixtures.getFixtureLocation());


        // Inserting Row
        tfID=(int)db.insert(TeamFixtures.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return tfID;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TeamFixtures.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
