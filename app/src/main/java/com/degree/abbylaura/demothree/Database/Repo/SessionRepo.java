package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.Database.Schema.Session;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by abbylaura on 10/03/2018.
 */

public class SessionRepo {

    private Session session;
    private String whereclause = "";

    public SessionRepo(){
        session = new Session();
    }

    public static String createTable(){
        return "CREATE TABLE " + Session.TABLE + "("
                //+ Session.KEY_AUTO + " TEXT PRIMARY KEY,"
                + Session.KEY_MemberID + " TEXT,"
                + Session.KEY_SessionID + " TEXT,"
                + Session.KEY_Deadlifts + " TEXT,"
                + Session.KEY_DeadliftJumps + " TEXT,"
                + Session.KEY_BackSquat + " TEXT,"
                + Session.KEY_BackSquatJumps + " TEXT,"
                + Session.KEY_GobletSquat + " TEXT,"
                + Session.KEY_BenchPress + " TEXT,"
                + Session.KEY_MilitaryPress + " TEXT,"
                + Session.KEY_SupineRow + " TEXT,"
                + Session.KEY_ChinUps + " TEXT,"
                + Session.KEY_Trunk + " TEXT,"
                + Session.KEY_RDL + " TEXT,"
                + Session.KEY_SplitSquat + " TEXT,"
                + Session.KEY_FourWayArms + " TEXT)";

    }

    public int insert(Session session) {
        int sId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Session.KEY_AUTO, session.getAuto());
        values.put(Session.KEY_MemberID, session.getMemberID());
        values.put(Session.KEY_SessionID, session.getSessionID());
        values.put(Session.KEY_Deadlifts, session.getDeadlifts());
        values.put(Session.KEY_DeadliftJumps, session.getDeadliftJumps());
        values.put(Session.KEY_BackSquat, session.getBackSquat());
        values.put(Session.KEY_BackSquatJumps, session.getBackSquatJumps());
        values.put(Session.KEY_GobletSquat, session.getGobletSquat());
        values.put(Session.KEY_BenchPress, session.getBenchPress());
        values.put(Session.KEY_MilitaryPress, session.getMilitaryPress());
        values.put(Session.KEY_SupineRow, session.getSupineRow());
        values.put(Session.KEY_ChinUps, session.getChinUps());
        values.put(Session.KEY_Trunk, session.getTrunk());
        values.put(Session.KEY_RDL, session.getRdl());
        values.put(Session.KEY_SplitSquat, session.getSplitSquat());
        values.put(Session.KEY_FourWayArms, session.getFourWayArms());


        // Inserting Row
        sId = (int) db.insert(Session.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return sId;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Session.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public String[][] getTable(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Session.TABLE);

        String[][] sessionArray = new String[15][count];

        String selectQuery = " SELECT * FROM " + Session.TABLE + " " + whereclause;

        Log.d(Session.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {

                sessionArray[0][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_MemberID));
                sessionArray[1][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_SessionID));
                sessionArray[2][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_Deadlifts));
                sessionArray[3][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_DeadliftJumps));
                sessionArray[4][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_BackSquat));
                sessionArray[5][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_BackSquatJumps));
                sessionArray[6][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_GobletSquat));
                sessionArray[7][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_BenchPress));
                sessionArray[8][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_MilitaryPress));
                sessionArray[9][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_SupineRow));
                sessionArray[10][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_ChinUps));
                sessionArray[11][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_Trunk));
                sessionArray[12][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_RDL));
                sessionArray[13][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_SplitSquat));
                sessionArray[14][iterator] = cursor.getString(cursor.getColumnIndex(Session.KEY_FourWayArms));

                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return sessionArray;

    }

    public ArrayList<ArrayList<String>> getGraphStats(String memberID, String exercise){
        //to do list:
            //for every row with this member id
            //get the date from SC of that session id
            //calculate average value for that session id
            //return [date][calculated average]

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT Session." + exercise + ", StrengthAndConditioning.SessionDate"
                + " FROM Session "
                + " LEFT JOIN StrengthAndConditioning"
                + " ON StrengthAndConditioning.SessionId = Session.SessionID"
                + " WHERE Session.MemberID ='" + memberID +"'";

        Log.d(Session.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        if (cursor.moveToFirst()) {
            do {
                String exerciseValues = cursor.getString(0);
                String[] values = exerciseValues.split(", ");
                int totalValue = 0;
                for(int i = 0; i < values.length; i++){
                    totalValue = totalValue + Integer.parseInt(values[i]);
                }
                int value = totalValue/values.length;

                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(value));
                row.add(cursor.getString(1));

                data.add(row);

                //System.out.println(cursor.getString(0) + " | " + cursor.getString(1) + " | " + cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return data;
    }

    public void setWhereclause(String whereclause){
        this.whereclause = whereclause;
    }
}
