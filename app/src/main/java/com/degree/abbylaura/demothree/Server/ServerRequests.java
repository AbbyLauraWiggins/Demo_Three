package com.degree.abbylaura.demothree.Server;

import java.util.ArrayList;

/**
 * Created by abbylaura on 18/02/2018.
 *
 * Contains all the methods to service requests from client
 *
 */

class ServerRequests {

    public static ArrayList<String> notices;

    public ServerRequests(){
        super();

        notices = new ArrayList<String>();

    }

    public Boolean validateLogin(String email, String password){ //maybe make this protected??

        //TODO valid email and password with database

        return true;
    }

    public void addToNotices(String string){
        notices.add("\n" + string);
    }

    public String getNotices(){
        return notices.toString();
    }

}
