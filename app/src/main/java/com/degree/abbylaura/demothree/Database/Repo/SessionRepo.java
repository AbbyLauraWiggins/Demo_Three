package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Session;

/**
 * Created by abbylaura on 10/03/2018.
 */

public class SessionRepo {

    private Session session;

    public SessionRepo(){
        session = new Session();
    }

    public static String createTable(){
        return "CREATE TABLE " + Session.TABLE + "("
                + Session.KEY_AUTO + " TEXT PRIMARY KEY,"
                + Session.KEY_MemberID + " TEXT,"
                + Session.KEY_SessionID + "TEXT,"
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
        values.put(Session.KEY_AUTO, session.getAuto());
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

    
}
