package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Member;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class MemberRepo {

    private Member member;

    public MemberRepo(){

        member = new Member();

    }


    public static String createTable(){
        return "CREATE TABLE " + Member.TABLE  + "("
                + Member.KEY_MemberId  + "   PRIMARY KEY,"
                + Member.KEY_Name + " TEXT,"
                + Member.KEY_Email + " TEXT,"
                + Member.KEY_Password + " TEXT,"
                + Member.KEY_DOB + " TEXT,"
                + Member.KEY_Positions + " TEXT,"
                + Member.KEY_Responsibilities + " TEXT,"
                + Member.KEY_TeamId + " TEXT)";
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


        // Inserting Row
        memberId=(int)db.insert(Member.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return memberId;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Member.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
