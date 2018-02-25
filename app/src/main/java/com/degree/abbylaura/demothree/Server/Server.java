package com.degree.abbylaura.demothree.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by abbylaura on 18/02/2018.
 */

public class Server {


    public static void main(String args[]) throws IOException {

        //create a server and client sockets
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ServerRequests requests = new ServerRequests(null);

        int portNumber = 9000; //TO BE CHANGED


        //setup server socket
        try{

            serverSocket = new ServerSocket(portNumber);
            System.out.println("Socket set up");

            //in true loop listen forever for incoming connections
            //allows multiple clients to be handled
            while(true) {

                //accept connection with clients socket
                //open reader and writer for communication
                try {

                    //connection accepted
                    clientSocket = serverSocket.accept();
                    //System.out.println("client accepted");

                    //create a new multi-server-thread object to handle new client
                    //pass it the socket returned from the accept and start the thread
                    new ServerThreads(clientSocket, requests).start();

                    //carry on listening for new connections forever

                } catch (IOException e) {
                    System.err.println("IO error " + e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try{
                serverSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }

        }


    }
}
