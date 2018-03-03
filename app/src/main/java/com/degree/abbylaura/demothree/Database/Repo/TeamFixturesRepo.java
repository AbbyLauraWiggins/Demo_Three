package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;

import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamFixturesRepo {
    private TeamFixtures teamFixtures;
    private String whereClause = "";

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


    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, TeamFixtures.TABLE);

        String[][] tfArray = new String[3][count];

        String selectQuery = " SELECT * FROM " + TeamFixtures.TABLE + " " + whereClause;

        Log.d(TeamFixtures.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                tfArray[0][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_FixtureId));
                tfArray[1][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_TeamFixtureDate));
                tfArray[2][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_TeamFixtureLocation));
                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return tfArray;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }

}
