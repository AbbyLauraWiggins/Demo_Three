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
            int count = 0;

            while(communicating){


                fromClient = inFromClient.readLine();
                System.out.println(fromClient);

                if(fromClient.equals("Request Login")){
                    outToClient.println("Send email");
                    String email = inFromClient.readLine();
                    outToClient.print("Send password");
                    String password = inFromClient.readLine();

                    if(requests.validateLogin(email, password)){
                        outToClient.println("Login valid");
                    }else{
                        outToClient.println("Login invalid");
                    }
                } else if(fromClient.equals("OTHERWISE")){
                    outToClient.println("OTHERWISE RECIEVED");
                } else{
                    outToClient.print("FINAL");
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
