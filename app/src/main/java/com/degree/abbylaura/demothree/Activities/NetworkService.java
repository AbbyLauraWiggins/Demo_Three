package com.degree.abbylaura.demothree.Activities;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by abbylaura on 25/02/2018.
 */

public class NetworkService extends IntentService {

    // Used to identify when the IntentService finishes
    public static final String TRANSACTION_DONE = "TRANSACTION_DONE";

    // Validates resource references inside Android XML files
    public NetworkService() {
        super(NetworkService.class.getName());
    }

    public NetworkService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("NetworkService", "Service Started");
        System.out.println("2: NetworkService onHandleIntent");

        String passedRequest = intent.getStringExtra("messageToSend");
        System.out.println("3: passedRequest = " + passedRequest);

        String response = serviceRequest(passedRequest);
        System.out.println("7: returned " + passedRequest + " to onHandleIntent");



        Log.e("ServerService", "Service Stopped");

        // Broadcast an intent back to MainActivity when file is downloaded
        Intent i = new Intent(TRANSACTION_DONE);
        i.putExtra("serverResponse", response);
        NetworkService.this.sendBroadcast(i);

    }

    protected String serviceRequest(String passedRequest) {
        NestedClient nClient = new NestedClient();
        return nClient.talkToServer(passedRequest);
    }


    public class NestedClient{
        BufferedReader inFromServer = null;
        PrintWriter outToServer = null;

        Socket socket;

        public NestedClient(){
            super();
            System.out.println("4: Nested Client constructor");

        }

        public String talkToServer(String passedRequest) {
            String response = null;

            try{
                System.out.println("5: Try block of TalkToServer NestedClient");

                socket = new Socket("10.0.2.2", 9002);

                inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                outToServer = new PrintWriter(socket.getOutputStream(), true);

                outToServer.println(passedRequest);

                response = inFromServer.readLine();
                System.out.println("6: Try block still, response = " + response);

                socket.close();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        public String[][] updateNotices(String clientID, String notice, String date, String myDbSize){
            String response = null;
            String[][] updateNotices = null;

            try{
                socket = new Socket("10.0.2.2", 9002);

                System.out.println("connected to server");

                inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                outToServer = new PrintWriter(socket.getOutputStream(), true);

                outToServer.println("sending notice update");

                String in  = inFromServer.readLine();

                outToServer.println(in);

                socket.close();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return updateNotices;
        }
    }
}
