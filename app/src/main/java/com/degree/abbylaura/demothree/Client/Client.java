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
    ClientThread thread;

    String msg;
    //ClientHelper helper;

    public Client(String clientID) {
        super();
        System.out.println("in client constructor");

        this.clientID = clientID;

    }


    public void setMessageToServer(String message){

        thread = new ClientThread(message);

        thread.start();
    }



    public String getMessageFromServer(){
        //return thread.getMessageFromServer();
        thread.interrupt();
        return ClientHelper.messageFromServer;
    }
}
