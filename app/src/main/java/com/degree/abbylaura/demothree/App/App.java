package com.degree.abbylaura.demothree.App;

import android.app.Application;
import android.content.Context;

import com.degree.abbylaura.demothree.Database.Data.DatabaseHelper;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class  App extends Application {
    private static Context context;
    private static DatabaseHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }

    public static Context getContext(){
        return context;
    }

}