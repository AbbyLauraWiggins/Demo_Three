package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.content.pm.FeatureInfo;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;

import java.util.ArrayList;
import java.util.Date;

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
                + Fixture.KEY_FixturePoints + " TEXT,"
                + Fixture.KEY_Forward + " TEXT,"
                + Fixture.KEY_Back + " TEXT,"
                + Fixture.KEY_Player + " TEXT,"
                + Fixture.KEY_TriesScored + " TEXT,"
                + Fixture.KEY_TriesSucceeded + " TEXT,"
                + Fixture.KEY_Conversions + " TEXT,"
                + Fixture.KEY_ConversionsSucceeded + " TEXT,"
                + Fixture.KEY_ScrumsWon + " TEXT,"
                + Fixture.KEY_ScrumsLost + " TEXT,"
                + Fixture.KEY_MaulsWon + " TEXT,"
                + Fixture.KEY_MaulsLost + " TEXT,"
                + Fixture.KEY_LineOutsWon + " TEXT,"
                + Fixture.KEY_LineOutsLost + " TEXT,"
                + Fixture.KEY_DropGoals + " TEXT,"
                + Fixture.KEY_PenaltyKicks + " TEXT)";
    }


    public void insert(Fixture fixture) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Fixture.KEY_TeamId, fixture.getTeamId());
        values.put(Fixture.KEY_FixtureId, fixture.getFixtureId());
        values.put(Fixture.KEY_FixturePoints, fixture.getFixturePoints());
        values.put(Fixture.KEY_Forward, fixture.getForward());
        values.put(Fixture.KEY_Back, fixture.getBack());
        values.put(Fixture.KEY_Player, fixture.getPlayer());
        values.put(Fixture.KEY_TriesScored, fixture.getTriesScored());
        values.put(Fixture.KEY_TriesSucceeded, fixture.getTriesSucceeded());
        values.put(Fixture.KEY_Conversions, fixture.getConversions());
        values.put(Fixture.KEY_ConversionsSucceeded, fixture.getConversionsSucceeded());
        values.put(Fixture.KEY_ScrumsWon, fixture.getScrumsWon());
        values.put(Fixture.KEY_ScrumsLost, fixture.getScrumsLost());
        values.put(Fixture.KEY_MaulsWon, fixture.getMaulsWon());
        values.put(Fixture.KEY_MaulsLost, fixture.getMaulsLost());
        values.put(Fixture.KEY_LineOutsWon, fixture.getLineOutsWon());
        values.put(Fixture.KEY_LineOutsLost, fixture.getLineOutsLost());
        values.put(Fixture.KEY_DropGoals, fixture.getDropGoals());
        values.put(Fixture.KEY_PenaltyKicks, fixture.getPenaltyKicks());


        //TODO auto insert into fixture repo when teamfixtures updated

        // Inserting Row
        db.insert(Fixture.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Fixture.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public String[][] getTableData() { //RETURN DATA NOT INCLUDING PLAYERS OF THE MATCH

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Fixture.TABLE);

        String[][] fixArray = new String[15][count];

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

    public ArrayList<String> getFixtureData(String fixtureID, String teamID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ArrayList<String> returnData = new ArrayList<>();

        String selectQuery = " SELECT * " +
                "FROM " + Fixture.TABLE + " WHERE FixtureId = '" + fixtureID + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        String theirScore = "";

        if (cursor.moveToFirst()) {
            do {
                /*if this is my teams row
                if(cursor.getString(cursor.getColumnIndex(Fixture.KEY_TeamId)).equals(teamID)){
                    returnData.add(cursor.getString(cursor.getColumnIndex(Fixture.KEY_Forward)));
                    returnData.add(cursor.getString(cursor.getColumnIndex(Fixture.KEY_Back)));
                    returnData.add(cursor.getString(cursor.getColumnIndex(Fixture.KEY_Player)));
                    returnData.add(cursor.getString(cursor.getColumnIndex(Fixture.KEY_FixturePoints)));
                }else{
                    theirScore = cursor.getString(cursor.getColumnIndex(Fixture.KEY_FixturePoints));
                }*/
                if(cursor.getString(cursor.getColumnIndex(Fixture.KEY_TeamId)).equals(teamID)){
                    for(int i = 4; i < 19; i++){
                        returnData.add(cursor.getString(i));
                    }
                }else{
                    theirScore = cursor.getString(cursor.getColumnIndex(Fixture.KEY_FixturePoints));
                }


            } while (cursor.moveToNext());
        }

        returnData.add(theirScore);

        return returnData;
    }


}
