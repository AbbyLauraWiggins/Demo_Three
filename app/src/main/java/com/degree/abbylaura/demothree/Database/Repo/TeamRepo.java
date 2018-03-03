package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Team;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamRepo {
    private Team team;
    private String whereClause = "";

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



    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Team.TABLE);

        String[][] teamArray = new String[3][count];

        String selectQuery = " SELECT * FROM " + Team.TABLE + " " + whereClause;

        Log.d(Team.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                teamArray[0][iterator] = cursor.getString(cursor.getColumnIndex(Team.KEY_TeamId));
                teamArray[1][iterator] = cursor.getString(cursor.getColumnIndex(Team.KEY_TeamName));
                teamArray[2][iterator] = cursor.getString(cursor.getColumnIndex(Team.KEY_TeamLocation));
                teamArray[4][iterator] = cursor.getString(cursor.getColumnIndex(Team.KEY_TeamCurPoints));
                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return teamArray;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }

}
