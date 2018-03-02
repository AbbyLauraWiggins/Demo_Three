package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class StrengthAndConditioningRepo {
    private StrengthAndConditioning strengthAndConditioning;

    public StrengthAndConditioningRepo(){

        strengthAndConditioning = new StrengthAndConditioning();

    }


    public static String createTable(){
        return "CREATE TABLE " + StrengthAndConditioning.TABLE  + "("
                + StrengthAndConditioning.KEY_SessionId  + "   PRIMARY KEY,"
                + StrengthAndConditioning.KEY_SessionDate + " TEXT,"
                + StrengthAndConditioning.KEY_SessionTime + " TEXT)";
    }


    public int insert(StrengthAndConditioning strengthAndConditioning) {
        int scID;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(StrengthAndConditioning.KEY_SessionId,
                strengthAndConditioning.getSessionID());
        values.put(StrengthAndConditioning.KEY_SessionDate,
                strengthAndConditioning.getSessionDate());
        values.put(StrengthAndConditioning.KEY_SessionTime,
                strengthAndConditioning.getSessionTime());



        // Inserting Row
        scID=(int)db.insert(StrengthAndConditioning.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return scID;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(StrengthAndConditioning.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
