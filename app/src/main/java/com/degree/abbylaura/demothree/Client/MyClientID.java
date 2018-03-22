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

        /*
         * Permissions dictate the permission level of that user for the system.
         * Levels are:
         * BASIC = basic user, see own data, no extra permissions
         *         + can add notices, pictures
         *         + can request to add events to calender //TODO request cal events
         * ADMIN = same as a basic user but also
         *         + can delete other users notifications, change other users permissions, accept new users
         *         + can see only their own rugby data
         *         + can add and accept events to the calendar //TODO accept cal events
         * LEADER = same as basic user does not have permissions of an admin
         *         + but can see all users rugby data
         *         + can decide and add KPIs and player feedback //TODO player feedback
         *         + can add to calendar without needing a request to admin
         *         + can set SC and training sheets
         *         + can log game stats
         *
         * CAL = Coach + Admin + Leader = all priviledges of admin and leader, but doesnt have personal logs/stats
         *
         * PAL = Player + admin + leader = all priviledges of admin and leader, AND has personal logs/stats
         *
         *
         */
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
