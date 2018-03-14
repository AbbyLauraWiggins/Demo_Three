package com.degree.abbylaura.demothree.Activities;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
        System.out.println("3: passedRequest = " + passedRequest.toString());

        Object response = serviceRequest(passedRequest);


        
        if(response instanceof String){
            System.out.println("7: returned " + passedRequest.toString() + " to onHandleIntent");

            Intent i = new Intent(TRANSACTION_DONE);
            i.putExtra("serverResponseString", (String) response);
            NetworkService.this.sendBroadcast(i);

        }else{
            System.out.println("7: returned " + passedRequest.toString() + " to onHandleIntent");

            Intent i = new Intent(TRANSACTION_DONE);

            i.putParcelableArrayListExtra("serverResponseList", (ArrayList<? extends Parcelable>) response);
            NetworkService.this.sendBroadcast(i);
        }





    }

    protected Object serviceRequest(String passedRequest) {
        NestedClient nClient = new NestedClient();
        return nClient.talkToServer(passedRequest);
    }


    public class NestedClient{
        //BufferedReader inFromServer = null;
        //PrintWriter outToServer = null;
        ObjectOutputStream outToServer;
        ObjectInputStream inFromServer;

        Socket socket;

        public NestedClient(){
            super();
            System.out.println("4: Nested Client constructor");

        }

        public Object talkToServer(String passedRequest) {
            Object response = null;

            try{
                System.out.println("5: Try block of TalkToServer NestedClient");

                socket = new Socket("10.0.2.2", 9002);

                //inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //outToServer = new PrintWriter(socket.getOutputStream(), true);
                outToServer = new ObjectOutputStream(socket.getOutputStream());
                inFromServer = new ObjectInputStream(socket.getInputStream());


                outToServer.writeObject(passedRequest);

                Object inFromServerObj = (Object) inFromServer.readObject();
                if(inFromServerObj instanceof String){
                    response = (String) inFromServerObj;
                }else{
                    response = (ArrayList<ArrayList<String>>) inFromServerObj;
                }

                System.out.println("6: Try block still, response = " + response.toString());

                socket.close();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return response;
        }

    }
}
