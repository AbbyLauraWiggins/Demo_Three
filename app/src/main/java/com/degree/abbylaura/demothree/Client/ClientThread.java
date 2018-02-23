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
    // private String HOST_NAME = "localhost";
    //private int PORT_NUMBER = 9000;

    String messageFromServer = null;
    String messageToServer = null;

    BufferedReader inFromServer = null;
    PrintWriter outToServer = null;


    public ClientThread(){
        super();

    }


    public void run(){


        try{

            socket = new Socket("10.0.2.2", 9000);  //current HOSTNAME is for running on emulator

            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            outToServer = new PrintWriter(socket.getOutputStream(), true);

            Boolean communicating = true;


            while (communicating) {

                /*
                messageFromServer = inFromServer.readLine();
                ClientHelper.setMessageFromServer(messageFromServer);

                messageToServer = ClientHelper.messageToServer;
                outToServer.println(messageToServer);
                */

                messageToServer = ClientHelper.messageToServer;
                ClientHelper.setMessageFromServer(messageFromServer);

                messageFromServer = inFromServer.readLine();
                System.out.println(messageFromServer);

                if(messageFromServer.equals("Connection accepted")){
                    outToServer.println(messageToServer);
                }

                else if(messageFromServer.equals("Send email")){
                    outToServer.println(ClientHelper.clientEmail);
                }

                else if(messageFromServer.equals("Send password")){
                    outToServer.println(ClientHelper.clientPassword);
                }

                else if(messageFromServer.equals("Login valid")){
                    ClientHelper.setLoginValid(true);
                }

                else{
                    outToServer.println("OTHERWISE");
                }

            }

            return;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

