package com.degree.abbylaura.demothree.Server;

import java.util.ArrayList;

/**
 * Created by abbylaura on 18/02/2018.
 *
 * Contains all the methods to service requests from client
 *
 */

public class ServerRequests {

    //public static ArrayList<String> notices;
    public String noticeString;

    public ServerRequests(String noticeString){
        super();

        this.noticeString = noticeString;
        //notices = new ArrayList<String>();

    }

    public Boolean validateLogin(String email, String password){ //maybe make this protected??

        //TODO valid email and password with database

        return true;
    }

    public void addToNotices(String string){

        System.out.println("passed to notice string, currently: " + string);

       /// String separator = System.getProperty("line.separator");

        noticeString = noticeString + string;

        System.out.println("added to notice string, currently: " + noticeString);
    }

    public String getNotices(){
        return noticeString;
    }

}
