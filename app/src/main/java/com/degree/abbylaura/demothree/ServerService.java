package com.degree.abbylaura.demothree;

import android.app.IntentService;
import android.content.Intent;
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

public class ServerService extends IntentService {

    // Used to identify when the IntentService finishes
    public static final String TRANSACTION_DONE = "TRANSACTION_DONE";

    // Validates resource references inside Android XML files
    public ServerService() {
        super(ServerService.class.getName());
    }


    public ServerService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("ServerService", "Service Started");

        // Get the URL for the file to download
        String passedRequest = intent.getStringExtra("request");
        System.out.println(passedRequest);

        String response = serviceRequest(passedRequest);

        Log.e("ServerService", "Service Stopped");

        // Broadcast an intent back to MainActivity when file is downloaded
        Intent i = new Intent(TRANSACTION_DONE);
        i.putExtra("serverResponse", response);
        ServerService.this.sendBroadcast(i);

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

        }

        public String talkToServer(String passedRequest){
            String response = null;

            try{
                socket = new Socket("10.0.2.2", 9000);

                inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                outToServer = new PrintWriter(socket.getOutputStream(), true);

                outToServer.println(passedRequest);

                response = inFromServer.readLine();

                System.out.println("in nested client: " + response);


                socket.close();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }
    }
}
