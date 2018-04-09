package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TableRow;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;

import com.degree.abbylaura.demothree.Database.Schema.Team;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class TeamFixturesRepo {
    private TeamFixtures teamFixtures;
    private String whereClause = "";

    public TeamFixturesRepo(){

        teamFixtures = new TeamFixtures();

    }


    public static String createTable(){
        return "CREATE TABLE " + TeamFixtures.TABLE  + "("
                + TeamFixtures.KEY_FixtureId  + " PRIMARY KEY,"
                + TeamFixtures.KEY_TeamFixtureDate + " TEXT,"
                + TeamFixtures.KEY_TeamFixtureLocation + " TEXT,"
                + TeamFixtures.KEY_HomeTeam + " TEXT,"
                + TeamFixtures.KEY_AwayTeam + " TEXT)"
                ;
    }


    public int insert(TeamFixtures teamFixtures) {
        int tfID;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(TeamFixtures.KEY_FixtureId, teamFixtures.getFixtureId());
        values.put(TeamFixtures.KEY_TeamFixtureDate, teamFixtures.getFixtureDate());
        values.put(TeamFixtures.KEY_TeamFixtureLocation, teamFixtures.getFixtureLocation());
        values.put(TeamFixtures.KEY_HomeTeam, teamFixtures.getHomeTeam());
        values.put(TeamFixtures.KEY_AwayTeam, teamFixtures.getAwayTeam());


        // Inserting Row
        tfID=(int)db.insert(TeamFixtures.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return tfID;
    }



    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TeamFixtures.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public String getFixText(String id){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT " +  TeamFixtures.KEY_HomeTeam + ", " +
                TeamFixtures.KEY_AwayTeam + ", " + TeamFixtures.KEY_TeamFixtureDate +
                " FROM " + TeamFixtures.TABLE;

        String homeTeam = "";
        String awayTeam = "";
        String date = "";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
               homeTeam = cursor.getString(0);
               awayTeam = cursor.getString(1);
               date = cursor.getString(2);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        TeamRepo teamRepo = new TeamRepo();
        homeTeam = getTeamName(homeTeam);
        awayTeam = getTeamName(awayTeam);

        String ret = homeTeam + " vs " + awayTeam + ": " + date;
        return ret;
    }

    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, TeamFixtures.TABLE);

        String[][] tfArray = new String[5][count];

        String selectQuery = " SELECT * FROM " + TeamFixtures.TABLE + " " + whereClause;

        Log.d(TeamFixtures.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                tfArray[0][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_FixtureId));
                tfArray[1][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_TeamFixtureDate));
                tfArray[2][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_TeamFixtureLocation));
                tfArray[3][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_HomeTeam));
                tfArray[4][iterator] = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_AwayTeam));

                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return tfArray;

    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }

    public int getTableSize(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String getSize = "SELECT COUNT(*) FROM " + TeamFixtures.TABLE;

        int size = 0;

        Cursor cursor = db.rawQuery(getSize, null);

        if(cursor.moveToNext()){
            do{
                size = cursor.getInt(0);
            }while(cursor.moveToNext());
        }

        DatabaseManager.getInstance().closeDatabase();

        return size;

    }

    public HashMap<String, ArrayList<String>> getQuickSpinnerList(String myTeamID) throws ParseException {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT TeamFixtures.TeamFixtureDate, HomeTeam, AwayTeam, FixtureId " +
                "FROM TeamFixtures " +
                "WHERE HomeTeam = '" + myTeamID + "' OR AwayTeam = '" + myTeamID + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        HashMap<String, ArrayList<String>> result = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fixtureDate = sdf.parse(cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_TeamFixtureDate)));
                if(new Date().after(fixtureDate)){
                    String str =
                            cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_HomeTeam))
                                    + " vs "
                                    + getTeamName(cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_AwayTeam)))
                                    + ": "
                                    + getTeamName(cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_TeamFixtureDate)));

                    ArrayList<String> fixIDlist = new ArrayList<>();
                    String fixID = cursor.getString(cursor.getColumnIndex(TeamFixtures.KEY_FixtureId));
                    fixIDlist.add(fixID);

                    System.out.println("getQuickSpinnerList: " + str);

                    list.add(str);
                    result.put(str, fixIDlist);

                }
            } while (cursor.moveToNext());
        } else{
            System.out.println("EMPTY TEAM FIXTURES TABLE");

        }

        result.put("list", list);

        return result;
    }

    public String getTeamName(String id){
        String name = "";

        String selectQuery = "SELECT TeamName FROM Team WHERE TeamId = '" + id + "'";

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToNext()){
            do{
                name = cursor.getString(cursor.getColumnIndex("TeamName"));
            }while(cursor.moveToNext());
        }

        return name;
    }

    public ArrayList<ArrayList<String>> getSpinnerList(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        HashMap<String, String> teams = getTeam();

        String selectQuery =
                "SELECT Fixture.TeamId, TeamFixtures.FixtureId, TeamFixtures.TeamFixtureDate " +
                        "FROM TeamFixtures, Fixture" +
                        " WHERE TeamFixtures.FixtureId = Fixture.FixtureId";

        Log.d(TeamFixtures.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        int size = (getTableSize()*2); //getTableSize*2 because they'll be two teamIDs per date

        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        String fixID = "";
        String previousID = "";

        if (cursor.moveToFirst()) {
            do {
               // System.out.println("spinner cursor");
                System.out.println(cursor.getColumnName(0) + " " + cursor.getString(0)); //team id
                System.out.println(cursor.getColumnName(1) + " " +cursor.getString(1)); //fixture id
                System.out.println(cursor.getColumnName(2) + " " +cursor.getString(2)); //fixture date


                if(fixID.equals(cursor.getString(1))){ //same as previous so add
                    ArrayList<String> row = new ArrayList<String>();
                    row.add(teams.get(previousID)); //previous team name
                    row.add(teams.get(cursor.getString(0))); //current team name
                    row.add(cursor.getString(2)); //fixture date
                    row.add(cursor.getString(1)); //fixture ID
                    row.add(previousID); //teamID 1
                    row.add(cursor.getString(0)); //team ID 2


                    /* row format:
                     *  (0) TEAM NAME 1
                     *  (1) TEAM NAME 2
                     *  (2) FIXTURE DATE   <^^ these three to be displayed on spinner
                     *  (3) FIXTURE ID     <   to pass to query to display correct data
                     *  (4) TEAM ID 1
                     *  (5) TEAM ID 2      <^  so can check if we belong to any of these teams
                     */
                    //System.out.println("row: " + row.toString());
                    table.add(row);

                } else {
                    //previous fixtureID is different so we are looking at a new row
                    previousID = cursor.getString(0);
                    fixID = cursor.getString(1);
                }
            } while (cursor.moveToNext());
        }

        //FOR TESTING
        for(int i = 0; i < table.size(); i++){
            ArrayList<String> row = table.get(i);
            //System.out.println(row.get(0) + " | " + row.get(1) + " | " + row.get(2));
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return table;

    }

    public HashMap<String, String> getTeam(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT TeamName, TeamId FROM Team";
        Cursor cursor = db.rawQuery(selectQuery, null);

        HashMap<String, String> teams = new HashMap<String, String>();

        if (cursor.moveToFirst()) {
            do {
                String key = cursor.getString(1);
                String value = cursor.getString(0);
                System.out.println(key + " | " + value);
                teams.put(key, value);
            } while (cursor.moveToNext());
        }

        cursor.close();

        DatabaseManager.getInstance().closeDatabase();

        return teams;
    }

    public ArrayList<String> getMyFixtureDates(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ArrayList<String> result = new ArrayList<String>();

        String selectQuery = " SELECT TeamFixtures.TeamFixtureDate, Fixture.TeamId " +
                "FROM TeamFixtures, Fixture " +
                "WHERE TeamFixtures.FixtureId = Fixture.FixtureId";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String teamID = cursor.getString(1);
                if(teamID.equals(MyClientID.myTeamID)){
                    result.add(cursor.getString(0));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return result;

    }
}
