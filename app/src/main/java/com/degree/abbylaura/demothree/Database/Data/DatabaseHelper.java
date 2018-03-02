package com.degree.abbylaura.demothree.Database.Data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Schema.SCsession;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;

import java.util.Scanner;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //note: remember each time if you Add, Edit table, you need to change the version number.
    private static final int DATABASE_VERSION =8;
    private static final String DATABASE_NAME = "clientTeam.db";
    private static final String TAG = DatabaseHelper.class.getSimpleName().toString();

    public DatabaseHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(FixtureRepo.createTable());
        db.execSQL(MemberRepo.createTable());
        db.execSQL(SCsessionRepo.createTable());
        db.execSQL(StrengthAndConditioningRepo.createTable());
        db.execSQL(TeamRepo.createTable());
        db.execSQL(TeamFixturesRepo.createTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Fixture.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Member.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCsession.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + StrengthAndConditioning.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Team.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TeamFixtures.TABLE);

        onCreate(db);
    }
}
