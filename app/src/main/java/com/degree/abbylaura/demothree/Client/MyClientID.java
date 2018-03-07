package com.degree.abbylaura.demothree.Client;

/**
 * Created by abbylaura on 23/02/2018.
 */

public class MyClientID {

    public static String myID = "unknown"; //1 for testing
    public static String myUsername = "";
    public static String myTeamID = "";

    public MyClientID(){
        super();
    }

    public String getMyID(String email, String password){
        //TODO start client to access server
        //TODO recieve clientID from server database based off email and password

        return myID;

    }

    public String getMyUsername(){
        return myUsername;
    }

    public static void setID(String id){
        myID = id;
        //myTeamID = teamid;
    }

    public void newID(String email, String password){
        String newID = null;

        //TODO generate new ID
        //TODO check it is not already an id in database

        myID = newID;
    }



}
