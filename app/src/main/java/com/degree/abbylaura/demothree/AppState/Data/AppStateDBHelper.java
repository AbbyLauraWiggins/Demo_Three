package com.degree.abbylaura.demothree.AppState.Data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.degree.abbylaura.demothree.App.App;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class AppStateDBHelper extends SQLiteOpenHelper{

    //note: remember each time if you Add, Edit table, you need to change the version number.
    private static final int DATABASE_VERSION =8;
    private static final String DATABASE_NAME = "appState.db";
    private static final String TAG = AppStateDBHelper.class.getSimpleName().toString();

    public AppStateDBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        //db.execSQL(FixtureRepo.createTable());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("ASDBhelper onUpgrade", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        //db.execSQL("DROP TABLE IF EXISTS " + Fixture.TABLE);


        onCreate(db);
    }
}
