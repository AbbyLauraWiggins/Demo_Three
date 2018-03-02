package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.App.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.Database.Schema.SCsession;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class SCsessionRepo {

    private SCsession sCsession;

    public SCsessionRepo(){

        sCsession = new SCsession();

    }


    public static String createTable(){
        return "CREATE TABLE " + SCsession.TABLE  + "("
                + SCsession.KEY_SCPrimary + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SCsession.KEY_MemberId  + "TEXT,"
                + SCsession.KEY_SessionId + " TEXT,"
                + SCsession.KEY_Deadlifts + " TEXT,"
                + SCsession.KEY_DeadliftJumps + " TEXT,"
                + SCsession.KEY_BackSquat + " TEXT,"
                + SCsession.KEY_BackSquatJumps + " TEXT,"
                + SCsession.KEY_GobletSquat + " TEXT,"
                + SCsession.KEY_BenchPress + " TEXT,"
                + SCsession.KEY_MilitaryPress + " TEXT,"
                + SCsession.KEY_SupineRow + " TEXT,"
                + SCsession.KEY_ChinUps + " TEXT,"
                + SCsession.KEY_Trunk + " TEXT,"
                + SCsession.KEY_RDL + " TEXT,"
                + SCsession.KEY_SplitSquat + " TEXT,"
                + SCsession.KEY_FourWayArms + " TEXT)";
    }


    public void insert(SCsession sCsession) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(SCsession.KEY_MemberId, sCsession.getMemberID());
        values.put(SCsession.KEY_SessionId, sCsession.getSessionID());
        values.put(SCsession.KEY_Deadlifts, sCsession.getDeadlifts());
        values.put(SCsession.KEY_DeadliftJumps, sCsession.getDeadliftJumps());
        values.put(SCsession.KEY_BackSquat, sCsession.getBackSquat());
        values.put(SCsession.KEY_BackSquatJumps, sCsession.getBackSquatJumps());
        values.put(SCsession.KEY_GobletSquat, sCsession.getGobletSquat());
        values.put(SCsession.KEY_BenchPress, sCsession.getBenchPress());
        values.put(SCsession.KEY_MilitaryPress, sCsession.getMilitaryPress());
        values.put(SCsession.KEY_SupineRow, sCsession.getSupineRow());
        values.put(SCsession.KEY_ChinUps, sCsession.getChinUps());
        values.put(SCsession.KEY_Trunk, sCsession.getTrunk());
        values.put(SCsession.KEY_RDL, sCsession.getRdl());
        values.put(SCsession.KEY_SplitSquat, sCsession.getSplitSquat());
        values.put(SCsession.KEY_FourWayArms, sCsession.getFourWayArms());


        // Inserting Row
        db.insert(Member.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(SCsession.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
