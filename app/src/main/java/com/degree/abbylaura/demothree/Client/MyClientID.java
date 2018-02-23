package com.degree.abbylaura.demothree.Client;

/**
 * Created by abbylaura on 23/02/2018.
 */

public class MyClientID {

    public static String myID = "unknown"; //1 for testing

    public MyClientID(){
        super();
    }

    public String getMyID(String email, String password){
        //TODO start client to access server
        //TODO recieve clientID from server database based off email and password


        Client client = new Client("unknown");
        client.setMessageToServer("login request");
        if(client.getMessageFromServer().equals("send email")){
            client.setMessageToServer("EMAIL:" + email);
        }
        if(client.getMessageFromServer().equals("send password")){
            client.setMessageToServer("PASSWORD:" + password);
            myID = client.getMessageFromServer();
        }

        return myID;

    }

    public void newID(String email, String password){
        String newID = null;

        //TODO generate new ID
        //TODO check it is not already an id in database

        this.myID = newID;
    }


}
