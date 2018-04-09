package com.degree.abbylaura.demothree.Database.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Data.DatabaseManager;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.KPI;
import com.degree.abbylaura.demothree.Database.Schema.Member;

import java.util.ArrayList;

/**
 * Created by abbylaura on 06/03/2018.
 */

public class KPIRepo {

    String[] array;

    private KPI kpi;
    private String whereClause = "";

    public KPIRepo(){
        kpi = new KPI();
    }

    public static String createtable(){
        return "CREATE TABLE " + KPI.TABLE + "("
                + KPI.KEY_KPIPrimary + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KPI.KEY_MemberID + " TEXT, "
                + KPI.KEY_FixtureID + " TEXT,"
                + KPI.KEY_sTackles + " TEXT,"
                + KPI.KEY_uTackles + " TEXT,"
                + KPI.KEY_sCarries + " TEXT,"
                + KPI.KEY_uCarries + " TEXT,"
                + KPI.KEY_sPasses + " TEXT,"
                + KPI.KEY_uPasses + " TEXT,"
                + KPI.KEY_HandlingErrors + " TEXT,"
                + KPI.KEY_Penalties + " TEXT,"
                + KPI.KEY_YellowCards + " TEXT,"
                + KPI.KEY_TriesScored + " TEXT,"
                + KPI.KEY_TurnoversWon + " TEXT,"
                + KPI.KEY_sThrowIns + " TEXT,"
                + KPI.KEY_uThrowIns + " TEXT,"
                + KPI.KEY_sLineOutTakes + " TEXT,"
                + KPI.KEY_uLineOutTakes + " TEXT,"
                + KPI.KEY_sKicks + " TEXT,"
                + KPI.KEY_uKicks + " TEXT)";
    }

    public void insert(KPI kpi){

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(KPI.KEY_MemberID, kpi.getMemberID());
        values.put(KPI.KEY_FixtureID, kpi.getFixtureID());
        values.put(KPI.KEY_sTackles, kpi.getsTackles());
        values.put(KPI.KEY_uTackles, kpi.getuTackles());
        values.put(KPI.KEY_sCarries, kpi.getsCarries());
        values.put(KPI.KEY_uCarries, kpi.getuCarries());
        values.put(KPI.KEY_sPasses, kpi.getsPasses());
        values.put(KPI.KEY_uPasses, kpi.getuPasses());
        values.put(KPI.KEY_HandlingErrors, kpi.getHandlingErrors());
        values.put(KPI.KEY_Penalties, kpi.getPenalties());
        values.put(KPI.KEY_YellowCards, kpi.getYellowCards());
        values.put(KPI.KEY_TriesScored, kpi.getTriesScored());
        values.put(KPI.KEY_TurnoversWon, kpi.getTurnoversWon());
        values.put(KPI.KEY_sThrowIns, kpi.getsThrowIns());
        values.put(KPI.KEY_uThrowIns, kpi.getuThrowIns());
        values.put(KPI.KEY_sLineOutTakes, kpi.getsLineOutTakes());
        values.put(KPI.KEY_uLineOutTakes, kpi.getuLineOutTakes());
        values.put(KPI.KEY_sKicks, kpi.getsKicks());
        values.put(KPI.KEY_uKicks, kpi.getuKicks());

        db.insert(KPI.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(KPI.TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //returns whole notice table, for testing
    //only works when returning whole table, otherwise for query return arraylist<>
    public String[][] getTableData() {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, KPI.TABLE);

        String[][] kpiArray = new String[20][count];
        System.out.println("KPI COUNT = " + String.valueOf(count));

        String selectQuery = " SELECT * FROM " + KPI.TABLE + " " + whereClause;

        Log.d(KPI.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        int iterator = 0;
        if (cursor.moveToFirst()) {
            do {
                kpiArray[0][iterator] = "PRIMARY KEY: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_KPIPrimary));
                kpiArray[1][iterator] = "MEMBER ID: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_MemberID));
                kpiArray[2][iterator] = "FIXTURE ID: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_FixtureID));
                kpiArray[3][iterator] = "S TACKLES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sTackles));
                kpiArray[4][iterator] = "U TACKLES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uTackles));
                kpiArray[5][iterator] = "S CARRIES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sCarries));
                kpiArray[6][iterator] = "U CARRIES:" + cursor.getString(cursor.getColumnIndex(KPI.KEY_uCarries));
                kpiArray[7][iterator] = "S PASSES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sPasses));
                kpiArray[8][iterator] = "U PASSES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uPasses));
                kpiArray[9][iterator] = "HANDLING: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_HandlingErrors));
                kpiArray[10][iterator] = "PENALTIES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_Penalties));
                kpiArray[11][iterator] = "YELLOW CARDS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_YellowCards));
                kpiArray[12][iterator] = "TRIES: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_TriesScored));
                kpiArray[13][iterator] = "TURNOVERS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_TurnoversWon));
                kpiArray[14][iterator] = "S THROWS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sThrowIns));
                kpiArray[15][iterator] = "U THROWS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uThrowIns));
                kpiArray[16][iterator] = "S LINE OUTS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sLineOutTakes));
                kpiArray[17][iterator] = "U LINE OUTS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uLineOutTakes));
                kpiArray[18][iterator] = "S KICKS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_sKicks));
                kpiArray[19][iterator] = "U KICKS: " + cursor.getString(cursor.getColumnIndex(KPI.KEY_uKicks));
                iterator++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return kpiArray;

    }

    public ArrayList<ArrayList<String>> getKPILeaderboard(String teamFixtureID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //Return array of |KPI COLUMN NAME | Member.Name | KPI COLUMN VALUE
        //For each column, return the MemberID of the person with the highest value

        ArrayList<ArrayList<String>> leaderboard = new ArrayList<ArrayList<String>>();

        setArray();

        for(int i = 0; i < 18; i++){
            String KPIColumnName = array[i];

            String selectQuery = " SELECT Member.Name, KPI." + KPIColumnName + ", KPI.FixtureID" +
                    " FROM KPI" +
                    " INNER JOIN " + Member.TABLE + " ON Member.MemberId = KPI.MemberID";

            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            String fName = null;
            int max = 0;

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    String result = cursor.getString(1);
                    String fixture = cursor.getString(2);

                    //System.out.println(name + " | " + result + " | " + fixture);

                    if(fixture.equals(teamFixtureID)){
                        if(!(result == null)){
                            if(Integer.parseInt(result) > max) { //TODO deal with equals
                                max = Integer.parseInt(result);
                                fName = name;
                            }
                        }
                    }


                } while (cursor.moveToNext());
            }

            cursor.close();

            ArrayList<String> list = new ArrayList<String>();
            list.add(array[i]); //TODO look to change using array[i] to cursor.getColumnName(i)
            list.add(fName);
            list.add(String.valueOf(max));
            //System.out.println("name:" + array[i] + " | result: " + fName + " | value: " + String.valueOf(max));

            leaderboard.add(list);
        }


        DatabaseManager.getInstance().closeDatabase();

        return leaderboard;

    }

    public ArrayList<ArrayList<String>> getKPISeasonLeaderboard(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ArrayList<ArrayList<String>> leaderboard = new ArrayList<ArrayList<String>>();
        ArrayList<String> memberids = new ArrayList<String>();
        setArray();

        String selectQuery = " SELECT MemberId FROM Member ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                memberids.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        //for each KPI
        for(int i = 0; i < 18; i++) {
            String kpiName = array[i];

            //keep track of highest amount and winning id
            String leadingName = null;
            int maxValue = 0;

            //calculate each members total
            //for each member
            for(int j = 0; j < memberids.size(); j++){
                //get name and value for each member
                ArrayList<String> nameAndValue = getKPITotal(memberids.get(j), array[i]);

                //System.out.println(array[i] + " name " + nameAndValue.get(0) + " value " + nameAndValue.get(1) + " maxvalue " + String.valueOf(maxValue));

                if(Integer.parseInt(nameAndValue.get(1)) > maxValue){
                    System.out.println(nameAndValue.get(1) + " > " + String.valueOf(maxValue) );
                    maxValue = Integer.parseInt(nameAndValue.get(1));
                    leadingName = nameAndValue.get(0);
                   // System.out.println(array[i] + " leading max value: " + nameAndValue.get(1) + " leading name: " + leadingName);
                }
            }
           // System.out.println(array[i] + " | " + leadingName + " | " + String.valueOf(maxValue));


            //add to the arraylist: array[i], Member Name, maxValue
            ArrayList<String> row = new ArrayList<String>();
            row.add(array[i]);
            row.add(leadingName);
            row.add(String.valueOf(maxValue));

            leaderboard.add(row);
        }

       // for(ArrayList<String> s : leaderboard){
         //  System.out.println(s.get(0) + " || " + s.get(1) + " || " + s.get(2));
        //}

        return leaderboard;
    }

    /*
     * @returns result = a string list containing total value for given member and KPI
     */
    public ArrayList<String> getKPITotal(String thisMemberID, String kpiName){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        //select Name and KPI value for this member and this KPI
        String selectQuery =  " SELECT Member.Name, KPI." + kpiName +
                " FROM KPI" +
                " INNER JOIN " + Member.TABLE + " ON Member.MemberId = KPI.MemberID" +
                " AND Member.MemberId LIKE '" + thisMemberID + "'";

        //Sum through KPI values and return name and max value
        ArrayList<String> result = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String name = null;
        int total = 0;

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(0) != null && cursor.getString(1) != null){
                    name = (cursor.getString(0));
                    total = total + Integer.parseInt(cursor.getString(1));//add total
                }

            } while (cursor.moveToNext());
        }

        cursor.close();

        result.add(name);
        result.add(String.valueOf(total));

        DatabaseManager.getInstance().closeDatabase();

        return result;
    }

    public void setWhereClause(String where) {
        this.whereClause = where;
    }

    public void setArray(){
        this.array = new String[19];
        array[0] = "MemberID";
        array[1] = "FixtureID";
        array[2] = "sTackles";
        array[3] = "uTackles";
        array[4] = "sCarries";
        array[5] = "uCarries";
        array[6] = "sPasses";
        array[7] = "uPasses";
        array[8] = "HandlingErrors";
        array[9] = "Penalties";
        array[10] = "YellowCards";
        array[11] = "TriesScored";
        array[12] = "TurnoversWon";
        array[13] = "sThrowIns";
        array[14] = "uThrowIns";
        array[15] = "sLineOutTakes";
        array[16] = "uLineOutTakes";
        array[17] = "sKicks";
        array[18] = "uKicks";
    }

    /*
     * returns an arraylist as will return only one row of information from given fixture
     */
    public ArrayList<String> getMyGameKPIs(String memberID, String fixtureID){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ArrayList<String> myKPI = new ArrayList<String>();

        String selectQuery = " SELECT * FROM " + KPI.TABLE
                + " WHERE MemberID ='" + memberID + "' AND FixtureID ='" + fixtureID + "'";

        Log.d(KPI.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                for(int i = 0; i < 20; i++){
                    myKPI.add(cursor.getString(i));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return myKPI;
    }

    public ArrayList<ArrayList<String>> getGraphStats(String memberID, String kpi){
        //to do list:
        //for every row with this member id
        //get the date from SC of that session id
        //calculate average value for that session id
        //return [date][calculated average]

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT KPI." + kpi + ", TeamFixtures.TeamFixtureDate, KPI.FixtureID"
                + " FROM KPI "
                + " LEFT JOIN TeamFixtures"
                + " ON KPI.FixtureID = TeamFixtures.FixtureId"
                + " WHERE KPI.MemberID ='" + memberID +"'";

        Log.d(KPI.TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

        if (cursor.moveToFirst()) {
            do {
                String kpiValue = cursor.getString(0);


                ArrayList<String> row = new ArrayList<>();
                row.add(kpiValue);
                row.add(cursor.getString(1)); //fixture date
                row.add(cursor.getString(2)); //fixture ID
                data.add(row);

                System.out.println(row.get(0) + " -> " + row.get(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return data;
    }

    public int getTableSize(){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        int result = (int) DatabaseUtils.queryNumEntries(db, KPI.TABLE);

        DatabaseManager.getInstance().closeDatabase();

        return result;

    }

}
