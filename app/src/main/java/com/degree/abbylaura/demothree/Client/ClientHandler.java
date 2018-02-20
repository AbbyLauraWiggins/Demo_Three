package com.degree.abbylaura.demothree.Client;

/**
 * Created by abbylaura on 20/02/2018.
 */

public class ClientHandler {

    String messageToServer, messageFromServer = null;

    public ClientHandler(){
        super();
    }

    public void setMessageToServer(String msg){
        messageToServer = msg;
    }

    public void setMessageFromServer(String msg){
        messageFromServer = msg;
    }

    public String getMessageToServer(){
        return messageToServer;
    }

    public String getMessageFromServer(){
        return messageFromServer;
    }
}
