package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;
import com.degree.abbylaura.demothree.Database.Schema.Team;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class StrengthAndConditioningRepo {
    private StrengthAndConditioning strengthAndConditioning;
    private String whereClause = "";

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


    public int getTableSize(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        int result = (int) DatabaseUtils.queryNumEntries(db, StrengthAndConditioning.TABLE);

        DatabaseManager.getInstance().closeDatabase();

        return result;

    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(StrengthAndConditioning.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }


    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, StrengthAndConditioning.TABLE);

        String[][] scArray = new String[3][count];

        String selectQuery = " SELECT * FROM " + StrengthAndConditioning.TABLE + " " + whereClause;

        Log.d(StrengthAndConditioning.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                scArray[0][iterator] = cursor.getString(cursor.getColumnIndex(StrengthAndConditioning.KEY_SessionId));
                scArray[1][iterator] = cursor.getString(cursor.getColumnIndex(StrengthAndConditioning.KEY_SessionDate));
                scArray[2][iterator] = cursor.getString(cursor.getColumnIndex(StrengthAndConditioning.KEY_SessionTime));
                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return scArray;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }

}
