package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.content.pm.FeatureInfo;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.Member;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class FixtureRepo {

    private Fixture fixture;

    public FixtureRepo(){

        fixture = new Fixture();

    }


    public static String createTable(){
        return "CREATE TABLE " + Fixture.TABLE  + "("
                + Fixture.KEY_TeamId + " TEXT,"  //MAY NEED TO DECLARE P/F KEYS HERE?
                + Fixture.KEY_FixtureId + " TEXT,"
                + Fixture.KEY_FixturePoints + " TEXT)";
    }


    public int insert(Fixture fixture) {
        int teamfixtureId; //A TEAM-FIXTURE COMPOSITE KEY?

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Fixture.KEY_TeamId, fixture.getTeamId());
        values.put(Fixture.KEY_FixtureId, fixture.getFixtureId());
        values.put(Fixture.KEY_FixturePoints, fixture.getFixturePoints());


        // Inserting Row
        teamfixtureId=(int)db.insert(Fixture.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return teamfixtureId;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Fixture.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
