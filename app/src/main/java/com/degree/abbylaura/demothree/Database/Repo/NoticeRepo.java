package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.Database.Schema.Notice;
import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Team;

import java.util.ArrayList;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class NoticeRepo {

    private Notice notice;
    String whereClause = "";

    public NoticeRepo(){
        notice = new Notice();
    }

    public static String createTable() {
        return "CREATE TABLE " + Notice.TABLE + "("
                + Notice.KEY_NoticeId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Notice.KEY_MemberId + " TEXT," //FOREIGN KEY FROM MEMBERID
                + Notice.KEY_Contents + " TEXT,"
                + Notice.KEY_Date + " TEXT)";
    }

    public void insert(Notice notice) {

        System.out.println("in insert notice");

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Notice.KEY_NoticeId, notice.getNoticeId());
        values.put(Notice.KEY_MemberId, notice.getMemberId());
        values.put(Notice.KEY_Contents, notice.getContents());
        values.put(Notice.KEY_Date, notice.getDate());



        // Inserting Row
        db.insert(Notice.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Notice.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //QUERIES BELOW


    /*
     * Query to get all Notice.Contents and return in arraylist
     * Used to get the Member name and Notice that isnt currently shown on uI
     */
    public ArrayList<ArrayList<String>> getNotices() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //int count = (int) DatabaseUtils.queryNumEntries(db, Notice.TABLE);


        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();


        String selectQuery = " SELECT Member." + Member.KEY_Name + ", " +
                Notice.TABLE + "." + Notice.KEY_Contents + ", " +
                Notice.TABLE + "." + Notice.KEY_Date + " " +
                "FROM " + Notice.TABLE + " " +
                "INNER JOIN " + Member.TABLE + " ON " +
                Notice.TABLE + "." + Notice.KEY_MemberId + "=Member." + Member.KEY_MemberId;

        Log.d(Notice.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> list = new ArrayList<>();
                list.add(cursor.getString(cursor.getColumnIndex(Member.KEY_Name)));
                list.add(cursor.getString(cursor.getColumnIndex(Notice.KEY_Contents)));
                list.add(cursor.getString(cursor.getColumnIndex(Notice.KEY_Date)));

                result.add(list);

                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return result;

    }

    public int tableSize(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, Notice.TABLE);
    }


    //returns whole notice table, for testing
    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Team.TABLE);

        String[][] noticeArray = new String[5][count];

        String selectQuery = " SELECT * FROM " + Notice.TABLE + " " + whereClause;

        Log.d(Team.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                noticeArray[0][iterator] = cursor.getString(cursor.getColumnIndex(Notice.KEY_NoticeId));
                noticeArray[1][iterator] = cursor.getString(cursor.getColumnIndex(Notice.KEY_MemberId));
                noticeArray[2][iterator] = cursor.getString(cursor.getColumnIndex(Notice.KEY_Contents));
                noticeArray[3][iterator] = cursor.getString(cursor.getColumnIndex(Notice.KEY_Date));

                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return noticeArray;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }



}
