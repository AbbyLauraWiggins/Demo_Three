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
import java.util.Random;

public class ServerThreads extends Thread{

    private Socket clientSocket;

    public ServerThreads(Socket clientSocket){
        super();
        this.clientSocket = clientSocket;

        System.out.println("in server threads constructor");
    }

    public void run(){

        System.out.println("in ST run");

        try {
            ServerRequests requests = new ServerRequests();


            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);


            Boolean communicating = true;
            outToClient.println("Connection accepted");
            System.out.println("Connection accepted");

            String fromClient = null;

            while(communicating){


                while(communicating){

                    String email, password;

                    fromClient = inFromClient.readLine();

                    if(fromClient.equals("login request")){
                        outToClient.println("send email");

                    }

                    else if(fromClient.startsWith("EMAIL")){
                        outToClient.println("send password");
                        email = fromClient;
                    }

                    else if(fromClient.startsWith("PASSWORD")){
                        password = fromClient;
                        //TODO validate email and password
                        //for now send random int as string
                        Random rn = new Random();
                        int id = rn.nextInt(100);
                        outToClient.println(String.valueOf(id));
                    }

                    else if(fromClient.equals("request update to notices")){
                        outToClient.println(requests.getNotices());
                    }

                    else if(fromClient.startsWith("NOTICE ADDITION:")){
                        requests.addToNotices(fromClient.substring(16));
                    }


                }



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
