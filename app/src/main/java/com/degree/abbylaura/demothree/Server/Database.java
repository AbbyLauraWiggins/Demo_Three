package com.degree.abbylaura.demothree.Server;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.io.File;

/**
 * Created by abbylaura on 16/02/2018.
 */

public class Database extends Activity {

    //create SQLite database
    SQLiteDatabase database = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void createDatabase(View view) {

        database = this.openOrCreateDatabase("DemoThreeDB",
                MODE_PRIVATE, null);

        //execute sql statement that isnt select
        database.execSQL("CREATE TABLE IF NOT EXISTS members " +
                "(id integer primary key NOT NULL, " +
                "name VARCHAR NOT NULL, " +
                "email VARCHAR NOT NULL," +
                "password VARCHAR NOT NULL," +
                "dob DATE," +
                "positions VARCHAR," +
                "responsibilities VARCHAR," +
                "teamID integer," +
                "foreign key (teamID) references team(teamID));");

        database.execSQL("CREATE TABLE IF NOT EXISTS team (" +
                "teamID integer primary key NOT NULL," +
                "teamname VARCHAR NOT NULL," +
                "location VARCHAR," +
                "currentPoints integer);");

        database.execSQL("CREATE TABLE IF NOT EXISTS teamFixtures (" +
                "fixtureID integer primary key NOT NULL," +
                "date DATE NOT NULL," +
                "pitchLocation VARCHAR);");

        database.execSQL("CREATE TABLE IF NOT EXISTS fixture (" +
                "fixtureID integer NOT NULL," +
                "teamID integer NOT NULL," +
                "points integer," +
                "foreign key (fixtureID) references teamFixtures(fixtureID)," +
                "foreign key (teamID) references team(teamID));");

        database.execSQL("CREATE TABLE IF NOT EXISTS strengthAndConditioning (" +
                "sessionID integer primary key NOT NULL," +
                "date DATE," +
                "time TIME);");


        //saving exercise values as strings which will be parsed to get reps
        database.execSQL("CREATE TABLE IF NOT EXISTS scSession (" +
                "sessionID integer NOT NULL," +
                "memberID integer NOT NULL," +
                "deadlift VARCHAR," +
                "deadliftJump VARCHAR," +
                "backSquat VARCHAR," +
                "backSquatJump VARCHAR," +
                "gobletSquat VARCHAR," +
                "benchPress VARCHAR," +
                "militaryPress VARCHAR," +
                "supineRow VARCHAR," +
                "chinUps VARCHAR," +
                "rdl VARCHAR," +
                "trunk VARCHAR," +
                "splitsquat VARCHAR," +
                "fourWayArms VARCHAR," +
                "resistedSprint VARCHAR," +
                "rows VARCHAR," +
                "foreign key (sessionID) references strengthAndConditioning(sessionID)," +
                "foreign key (memberID) references members(memberID));");

        // The database on the file system
        File database = getApplicationContext().getDatabasePath("DemoThreeDB.db");

        //save for persistent db?

    }

    public void addNewMember(String name, String email, String password){
        //TODO generate userID
        //TODO add to database
    }

    public void deleteMember(int memberID){
        //TODO remove member from database
    }

    /*
        also maybe pass:
        Date date
        String pitchLocation
     */
    public void addTeamFixture(String oppositionTeamName, int myTeamID){
        //TODO generate fixtureID
        //TODO find opposition team ID from name
        //TODO add (generated fixture ID, myteamID, date, pitchLocation) to teamFixture table
        //TODO add (generated fixture ID, opposition teamID, date, pitchlocation) to teamFixture table
    }

    /*
        also maybe pass:
        Array of S&C information from user inputting in LogActivity
     */
    public void addSCsessionDetails(int memberID, int sessionID){
        //TODO go through array and add relevant exercises in

    }

    public void deleteDatabase(View view) {
        //this.deleteDatabase("DemoThreeDB.db");
    }

    @Override
    protected void onDestroy() { //close DB if app is shutdown
        database.close();

        super.onDestroy();
    }




}
