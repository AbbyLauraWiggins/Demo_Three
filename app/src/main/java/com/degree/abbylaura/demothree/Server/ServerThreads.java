package com.degree.abbylaura.demothree.Server;

/**
 * Created by abbylaura on 18/02/2018.
 */

import com.degree.abbylaura.demothree.Server.ServerRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreads extends Thread{

    private Socket clientSocket;

    public ServerThreads(Socket clientSocket){
        super();
        this.clientSocket = clientSocket;
    }

    public void run(){

        try {
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);


            Boolean communicating = true;
            String response = null;

            String myresponse = "FIRST TO CLIENT"; //debugging

            //send over to ServerRequests
            ServerRequests request = new ServerRequests();


            while(communicating){



                /*
                outToClient.println("FIRST TO CLIENT");
                response = inFromClient.readLine();
                System.out.println(myresponse + " : " + response);


                if(response.equals("FIRST TO SERVER")){
                    myresponse = "SECOND TO CLIENT";
                    outToClient.println("SECOND TO CLIENT");

                }else if(response.equals("SECOND TO SERVER")){
                    myresponse = "SERVER ENDING COMMUNICATION";
                    outToClient.println("SERVER ENDING COMMUNICATION");

                }else if(response.equals("CLIENT END")){
                    communicating = false;
                } */



                //if something return a particular value then communicating = false
            }

            clientSocket.close();
            return;



        } catch (IOException e) {
            e.printStackTrace();


        } finally {
            try{
                clientSocket.close();
            }

            catch (IOException e){
                e.printStackTrace();
            }
        }




    }
}
