package com.degree.abbylaura.demothree.Client;

/**
 * Created by abbylaura on 18/02/2018.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    String clientID = null;

    public Client() {
        super();
        System.out.println("in client constructor");

        this.clientID = clientID;

        new ClientThread().start();

    }


    public void setMessageToServer(String message){
        //System.out.println("in client set message");

        ClientHelper.setMessageToServer(message);
    }

    public String getMessageFromServer(){
        //System.out.println("in client getmessage");

        return ClientHelper.messageFromServer;
    }
}
