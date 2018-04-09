package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.FillEventHistory;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Feedback;

import java.util.ArrayList;


public class FeedbackRepo {

    private Feedback feedback;

    public FeedbackRepo(){

        feedback = new Feedback();

    }


    public static String createTable(){
        return "CREATE TABLE " + Feedback.TABLE  + "("
                + Feedback.KEY_FeedbackPrimary + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Feedback.KEY_MemberId + " TEXT,"
                + Feedback.KEY_FixtureId + " TEXT,"
                + Feedback.KEY_FeedbackText + " TEXT,"
                + Feedback.KEY_Attack + " TEXT,"
                + Feedback.KEY_Defence + " TEXT,"
                + Feedback.KEY_Effort + " TEXT,"
                + Feedback.KEY_Overall + " TEXT)";
    }


    public void insert(Feedback feedback) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(feedback.KEY_MemberId, feedback.getMemberId());
        values.put(feedback.KEY_FixtureId, feedback.getFixtureId());
        values.put(feedback.KEY_FeedbackText, feedback.getFeedbackText());
        values.put(feedback.KEY_Attack, feedback.getAttack());
        values.put(feedback.KEY_Defence, feedback.getDefence());
        values.put(feedback.KEY_Effort, feedback.getEffort());
        values.put(feedback.KEY_Overall, feedback.getOverall());

        // Inserting Row
        db.insert(Feedback.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Feedback.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int getTableSize(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int result = (int) DatabaseUtils.queryNumEntries(db, Feedback.TABLE);
        DatabaseManager.getInstance().closeDatabase();
        return result;
    }

    public ArrayList<String> getFeedback(String memberId, String fixtureId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Feedback.TABLE);

        ArrayList<String> feedback = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + Feedback.TABLE +
                " WHERE " + Feedback.KEY_MemberId + " = '" + memberId + "'" +
                " AND " + Feedback.KEY_FixtureId + " = '" + fixtureId + "'";



        Log.d(Feedback.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                feedback.add(cursor.getString(cursor.getColumnIndex("FeedbackText")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Attack")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Defence")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Effort")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Overall")));

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();


        return feedback;
    }

    public ArrayList<ArrayList<String>> getMyFeedback(String id){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ArrayList<ArrayList<String>> response = new ArrayList<>();


        String selectQuery = " SELECT * FROM " + Feedback.TABLE +
                " WHERE " + Feedback.KEY_MemberId + " = '" + id + "'";

        Log.d(Feedback.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> feedback = new ArrayList<>();

                feedback.add(cursor.getString(cursor.getColumnIndex("FeedbackText")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Attack")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Defence")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Effort")));
                feedback.add(cursor.getString(cursor.getColumnIndex("Overall")));
                feedback.add(cursor.getString(cursor.getColumnIndex("FixtureId")));

                response.add(feedback);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();


        return response;

    }

    public String[][] getTable(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Feedback.TABLE);

        String[][] feedback = new String[7][getTableSize()];

        String selectQuery = " SELECT * FROM " + Feedback.TABLE;


        Log.d(Feedback.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        int iter = 0;
        if (cursor.moveToFirst()) {
            do {
                feedback[0][iter] = (cursor.getString(cursor.getColumnIndex("MemberId")));
                feedback[1][iter] = (cursor.getString(cursor.getColumnIndex("FixtureId")));
                feedback[2][iter] = (cursor.getString(cursor.getColumnIndex("FeedbackText")));
                feedback[3][iter] = (cursor.getString(cursor.getColumnIndex("Attack")));
                feedback[4][iter] = (cursor.getString(cursor.getColumnIndex("Defence")));
                feedback[5][iter] = (cursor.getString(cursor.getColumnIndex("Effort")));
                feedback[6][iter] = (cursor.getString(cursor.getColumnIndex("Overall")));

                iter++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();


        return feedback;
    }
}
