package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.KPI;
import com.degree.abbylaura.demothree.Database.Schema.Member;

import java.util.ArrayList;

/**
 * Created by abbylaura on 06/03/2018.
 */

public class KPIRepo {

    private KPI kpi;
    private String whereClause = "";

    public KPIRepo(){
        kpi = new KPI();
    }

    public static String createtable(){
        return "CREATE TABLE " + KPI.TABLE + "("
                + KPI.KEY_KPIPrimary + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KPI.KEY_MemberID + " TEXT, "
                + KPI.KEY_FixtureID + " TEXT,"
                + KPI.KEY_sTackles + " TEXT,"
                + KPI.KEY_uTackles + " TEXT,"
                + KPI.KEY_sCarries + " TEXT,"
                + KPI.KEY_uCarries + " TEXT,"
                + KPI.KEY_sPasses + " TEXT,"
                + KPI.KEY_uPasses + " TEXT,"
                + KPI.KEY_HandlingErrors + " TEXT,"
                + KPI.KEY_Penalties + " TEXT,"
                + KPI.KEY_YellowCards + " TEXT,"
                + KPI.KEY_TriesScored + " TEXT,"
                + KPI.KEY_TurnoversWon + " TEXT,"
                + KPI.KEY_sThrowIns + " TEXT,"
                + KPI.KEY_uThrowIns + " TEXT,"
                + KPI.KEY_sLineOutTakes + " TEXT,"
                + KPI.KEY_uLineOutTakes + " TEXT,"
                + KPI.KEY_sKicks + " TEXT,"
                + KPI.KEY_uKicks + " TEXT)";
    }

    public void insert(KPI kpi){

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KPI.KEY_MemberID, kpi.getMemberID());
        values.put(KPI.KEY_FixtureID, kpi.getFixtureID());
        values.put(KPI.KEY_sTackles, kpi.getsTackles());
        values.put(KPI.KEY_uTackles, kpi.getuTackles());
        values.put(KPI.KEY_sCarries, kpi.getsCarries());
        values.put(KPI.KEY_uCarries, kpi.getuCarries());
        values.put(KPI.KEY_sPasses, kpi.getsPasses());
        values.put(KPI.KEY_uPasses, kpi.getuPasses());
        values.put(KPI.KEY_HandlingErrors, kpi.getHandlingErrors());
        values.put(KPI.KEY_Penalties, kpi.getPenalties());
        values.put(KPI.KEY_YellowCards, kpi.getYellowCards());
        values.put(KPI.KEY_TriesScored, kpi.getTriesScored());
        values.put(KPI.KEY_TurnoversWon, kpi.getTurnoversWon());
        values.put(KPI.KEY_sThrowIns, kpi.getsThrowIns());
        values.put(KPI.KEY_uThrowIns, kpi.getuThrowIns());
        values.put(KPI.KEY_sLineOutTakes, kpi.getsLineOutTakes());
        values.put(KPI.KEY_uLineOutTakes, kpi.getuLineOutTakes());
        values.put(KPI.KEY_sKicks, kpi.getsKicks());
        values.put(KPI.KEY_uKicks, kpi.getuKicks());

        db.insert(KPI.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(KPI.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //returns whole notice table, for testing
    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, KPI.TABLE);

        String[][] kpiArray = new String[20][count];
        System.out.println("KPI COUNT = " + String.valueOf(count));

        String selectQuery = " SELECT * FROM " + KPI.TABLE + " " + whereClause;

        Log.d(KPI.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                kpiArray[0][iterator] = "PRIMARY KEY: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_KPIPrimary));
                kpiArray[1][iterator] = "MEMBER ID: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_MemberID));
                kpiArray[2][iterator] = "FIXTURE ID: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_FixtureID));
                kpiArray[3][iterator] = "S TACKLES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sTackles));
                kpiArray[4][iterator] = "U TACKLES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uTackles));
                kpiArray[5][iterator] = "S CARRIES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sCarries));
                kpiArray[6][iterator] = "U CARRIES:" + cursor.getString(cursor.getColumnIndex(KPI.KEY_uCarries));
                kpiArray[7][iterator] = "S PASSES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sPasses));
                kpiArray[8][iterator] = "U PASSES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uPasses));
                kpiArray[9][iterator] = "HANDLING: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_HandlingErrors));
                kpiArray[10][iterator] = "PENALTIES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_Penalties));
                kpiArray[11][iterator] = "YELLOW CARDS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_YellowCards));
                kpiArray[12][iterator] = "TRIES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_TriesScored));
                kpiArray[13][iterator] = "TURNOVERS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_TurnoversWon));
                kpiArray[14][iterator] = "S THROWS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sThrowIns));
                kpiArray[15][iterator] = "U THROWS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uThrowIns));
                kpiArray[16][iterator] = "S LINE OUTS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sLineOutTakes));
                kpiArray[17][iterator] = "U LINE OUTS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uLineOutTakes));
                kpiArray[18][iterator] = "S KICKS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sKicks));
                kpiArray[19][iterator] = "U KICKS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uKicks));
                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return kpiArray;

    }

    public ArrayList<ArrayList<String>> getKPILeaderboard(String teamFixtureID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, KPI.TABLE);

        //Return array of |KPI COLUMN NAME | Member.Name | KPI COLUMN VALUE
        //For each column, return the MemberID of the person with the highest value

        ArrayList<ArrayList<String>> leaderboard = new ArrayList<ArrayList<String>>();

        String array[] = new String[19];
        array[0] = "MemberID";
        array[1] = "FixtureID";
        array[2] = "sTackles";
        array[3] = "uTackles";
        array[4] = "sCarries";
        array[5] = "uCarries";
        array[6] = "sPasses";
        array[7] = "uPasses";
        array[8] = "HandlingErrors";
        array[9] = "Penalties";
        array[10] = "YellowCards";
        array[11] = "TriesScored";
        array[12] = "TurnoversWon";
        array[13] = "sThrowIns";
        array[14] = "uThrowIns";
        array[15] = "sLineOutTakes";
        array[16] = "uLineOutTakes";
        array[17] = "sKicks";
        array[18] = "uKicks";

        for(int i = 0; i < 18; i++){
            String KPIColumnName = array[i];

            String selectQuery = " SELECT Member.Name, KPI." + KPIColumnName + ", KPI.FixtureID" +
                    " FROM KPI" +
                    " INNER JOIN " + Member.TABLE + " ON Member.MemberId = KPI.MemberID";

            Log.d(KPI.TAG, selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            String fName = null;
            int max = 0;

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    String result = cursor.getString(1);
                    String fixture = cursor.getString(2);

                    //System.out.println(name + " | " + result + " | " + fixture);

                    if(fixture == teamFixtureID){
                        if(Integer.parseInt(result) > max){ //TODO deal with equals
                            max = Integer.parseInt(result);
                            fName = name;
                        }
                    }


                } while (cursor.moveToNext());
            }

            cursor.close();

            ArrayList<String> list = new ArrayList<String>();
            list.add(array[i]);
            list.add(fName);
            list.add(String.valueOf(max));

            leaderboard.add(list);
        }


        DatabaseManager.getInstance().closeDatabase();

        return leaderboard;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }



}
