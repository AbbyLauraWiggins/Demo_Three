package com.degree.abbylaura.demothree.Client;

import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;

/**
 * Created by abbylaura on 23/02/2018.
 */

public class MyClientID {

    public static String myID = "unknown"; //1 for testing
    public static String myUsername = "";
    public static String myTeamID = "";
    public static int myPermissions = 0;
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
        MemberRepo memberRepo = new MemberRepo();

        String permission = memberRepo.getMyPermissions(id);
        if(permission.equals("BASIC")){
            myPermissions = 0;
        }else if(permission.equals("ADMIN")){
            myPermissions = 1;
        }else if(permission.equals("LEADER")){
            myPermissions = 2;
        }else if(permission.equals("CAL")){
            myPermissions = 3;
        }else if(permission.equals("PAL")) {
            myPermissions = 4;
        }
    }


    public static void setMyTeamID(String id){
        myTeamID = id;
    }

    public static void setMyPermissions(int perm){ //FOR TESTING ONLY
        myPermissions = perm;
    }

    public static void setTESTid(String id){
        myID = id;
    }
    public void newID(String email, String password){
        String newID = null;

        //TODO generate new ID
        //TODO check it is not already an id in database

        myID = newID;
    }



}
