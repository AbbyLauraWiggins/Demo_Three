package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class FixtureRepo {

    private Fixture fixture;
    private String whereClause = "";

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

    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Fixture.TABLE);

        String[][] fixArray = new String[3][count];

        String selectQuery = " SELECT * FROM " + Fixture.TABLE + " " + whereClause;

        Log.d(Fixture.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                fixArray[0][iterator] = cursor.getString(cursor.getColumnIndex(Fixture.KEY_FixtureId));
                fixArray[1][iterator] = cursor.getString(cursor.getColumnIndex(Fixture.KEY_TeamId));
                fixArray[2][iterator] = cursor.getString(cursor.getColumnIndex(Fixture.KEY_FixturePoints));
                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return fixArray;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }

}
