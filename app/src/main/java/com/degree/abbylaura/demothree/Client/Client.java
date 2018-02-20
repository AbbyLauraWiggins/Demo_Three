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
    String hostName = "localhost";
    int portNumber = 8888; //TO CHANGE

    ClientHandler handler = new ClientHandler();

    //create client socket
    Socket socket = null;
    BufferedReader inFromServer = null;
    PrintWriter outToServer = null;

    public Client(){
        super();

        try{
            socket = new Socket(hostName, portNumber);
            System.out.println("socket created");

            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            outToServer = new PrintWriter(socket.getOutputStream(), true);


            Boolean communicating = true;
            String messageFromServer = null;
            String messageToServer = null;


            while(communicating){


                messageFromServer = inFromServer.readLine();
                handler.setMessageFromServer(messageFromServer);

                messageToServer = handler.getMessageToServer();
                outToServer.println(messageToServer);


                /*
                response = inFromServer.readLine();
                System.out.println(myresponse + " : " + response);


                if(response.equals("FIRST TO CLIENT")){
                    myresponse = "FIRST TO SERVER";

                    outToServer.println("FIRST TO SERVER");

                }else if(response.equals("SECOND TO CLIENT")){
                    myresponse = "SECOND TO SERVER";

                    outToServer.println("SECOND TO SERVER");

                }else if(response.equals("SERVER ENDING COMMUNICATION")){
                    myresponse = "CLIENT END";

                    outToServer.println("CLIENT END");
                    communicating = false; //may or may not need this line in
                }
                */

            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //create out and in threads


    }

    public static void main(String args[]){

    }


}
