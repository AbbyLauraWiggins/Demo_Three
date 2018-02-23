package com.degree.abbylaura.demothree.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by abbylaura on 21/02/2018.
 */

public class ClientThread extends Thread {

    private Socket socket;
    private static final String TAG = "ClientThread";

    String messageFromServer = null;
    String messageToServer = null;

    BufferedReader inFromServer = null;
    PrintWriter outToServer = null;


    public ClientThread(String msg) {
        super();
        this.messageToServer = msg;

    }



    public void run(){


        try{

            socket = new Socket("10.0.2.2", 9000);

            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            outToServer = new PrintWriter(socket.getOutputStream(), true);

            Boolean communicating = true;



            while (communicating) {


                outToServer.println(messageToServer);

                ClientHelper.setMessageFromServer(inFromServer.readLine());

            }

            return;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

