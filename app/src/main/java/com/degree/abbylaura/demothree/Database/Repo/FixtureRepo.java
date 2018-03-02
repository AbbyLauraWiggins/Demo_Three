package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.App.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;

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
                + Fixture.KEY_FixturePrimary + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Fixture.KEY_TeamId + " TEXT,"  //MAY NEED TO DECLARE P/F KEYS HERE?
                + Fixture.KEY_FixtureId + " TEXT,"
                + Fixture.KEY_FixturePoints + " TEXT)";
    }


    public void insert(Fixture fixture) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Fixture.KEY_TeamId, fixture.getTeamId());
        values.put(Fixture.KEY_FixtureId, fixture.getFixtureId());
        values.put(Fixture.KEY_FixturePoints, fixture.getFixturePoints());


        // Inserting Row
        db.insert(Fixture.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Fixture.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
