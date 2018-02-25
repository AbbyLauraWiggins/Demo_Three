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
    private ServerRequests requests;

    public ServerThreads(Socket clientSocket, ServerRequests requests){
        super();
        this.clientSocket = clientSocket;

        this.requests = requests;

    }

    public void run(){



        try {

            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true);


            String passedRequest = inFromClient.readLine();

            requests.addToNotices(passedRequest);

            System.out.println(passedRequest);

            outToClient.println(requests.getNotices());

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
