package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Member;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class MemberRepo {

    private Member member;
    private String whereClause = "";


    public MemberRepo() {

        member = new Member();

    }


    public static String createTable() {
        return "CREATE TABLE " + Member.TABLE + "("
                + Member.KEY_MemberId + " TEXT PRIMARY KEY,"
                + Member.KEY_Name + " TEXT,"
                + Member.KEY_Email + " TEXT,"
                + Member.KEY_Password + " TEXT,"
                + Member.KEY_DOB + " TEXT,"
                + Member.KEY_Positions + " TEXT,"
                + Member.KEY_Responsibilities + " TEXT,"
                + Member.KEY_TeamId + " TEXT,"
                + Member.KEY_Permissions + " TEXT)";
    }


    public int insert(Member member) {
        int memberId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Member.KEY_MemberId, member.getMemberId());
        values.put(Member.KEY_Name, member.getName());
        values.put(Member.KEY_Email, member.getEmail());
        values.put(Member.KEY_Password, member.getPassword());
        values.put(Member.KEY_DOB, member.getDOB());
        values.put(Member.KEY_Positions, member.getPositions());
        values.put(Member.KEY_Responsibilities, member.getResponsibilities());
        values.put(Member.KEY_TeamId, member.getTeamId());
        values.put(Member.KEY_Permissions, member.getPermissions());


        // Inserting Row
        memberId = (int) db.insert(Member.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return memberId;
    }


    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Member.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public String[][] getMembers() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Member.TABLE);

        String[][] memberArray = new String[9][count];

        String selectQuery = " SELECT * FROM " + Member.TABLE + " " + whereClause;

        Log.d(Member.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                memberArray[0][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_MemberId));
                memberArray[1][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_Name));
                memberArray[2][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_Email));
                memberArray[3][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_Password));
                memberArray[4][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_DOB));
                memberArray[5][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_Positions));
                memberArray[6][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_Responsibilities));
                memberArray[7][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_TeamId));
                memberArray[8][iterator] = cursor.getString(cursor.getColumnIndex(Member.KEY_Permissions));


                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return memberArray;

    }



    public void setWhereClause(String where) {
        this.whereClause = where;
    }

    public int getMemberTableSize(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        int result = (int) DatabaseUtils.queryNumEntries(db, Member.TABLE);

        DatabaseManager.getInstance().closeDatabase();

        return result;

    }

    public void updatePosition(String myId, String position){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues args = new ContentValues();
        args.put(Member.KEY_Positions, position);
        String[] id = new String[1];
        id[0] = myId;
        int result = db.update(Member.TABLE, args, Member.KEY_MemberId + "=?", id);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void updateResponsibility(String myId, String responsibility){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues args = new ContentValues();
        args.put(Member.KEY_Responsibilities, responsibility);
        String[] id = new String[1];
        id[0] = myId;
        int result = db.update(Member.TABLE, args, Member.KEY_MemberId + "=?", id);
        DatabaseManager.getInstance().closeDatabase();
    }

    public String getMyPermissions(String myId){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, Member.TABLE);

        String memberPermission = null;

        String selectQuery = " SELECT Permissions FROM " + Member.TABLE + " WHERE MemberId ='" + myId + "'";

        Log.d(Member.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                memberPermission = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return memberPermission;
    }

    public ArrayList<String> getNames(ArrayList<String> ids) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ArrayList<String> names = new ArrayList<>();

        for(String s: ids){
            String selectQuery = " SELECT " + Member.KEY_Name + " FROM Member" +
                    " WHERE " + Member.KEY_MemberId + " = '" + s + "'";

            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                names.add(cursor.getString(cursor.getColumnIndex(Member.KEY_Name)));
            }
        }

        return names;
    }
}
