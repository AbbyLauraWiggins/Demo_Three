package com.degree.abbylaura.demothree.AppState.Repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.degree.abbylaura.demothree.AppState.Schema.Notice;
import com.degree.abbylaura.demothree.App.DatabaseManager;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class NoticeRepo {

    private NoticeRepo noticeRepo;

    public NoticeRepo(){
        noticeRepo = new NoticeRepo();
    }

    public static String createTable() {
        return "CREATE TABLE " + Notice.TABLE + "("
                + Notice.KEY_NoticeId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Notice.KEY_MemberId + " TEXT,"
                + Notice.KEY_Contents + " TEXT,"
                + Notice.KEY_Date + " TEXT)";
    }

    public void insert(Notice notice) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Notice.KEY_NoticeId, notice.getNoticeId());
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
}
