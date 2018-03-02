package com.degree.abbylaura.demothree;

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

        String serviceRequested = intent.getStringExtra("serviceRequested");

        System.out.println("in SS - service requested:  " + serviceRequested);


        String[][] response = null;

        if(serviceRequested.equals("NoticeActivityAddition")){
            NestedClient nClient = new NestedClient();

            String clientID = intent.getStringExtra("clientID");
            String notice = intent.getStringExtra("content");
            String date = intent.getStringExtra("date");

            //************ get CLIENTSIDE NOTICE DATABASE SIZE
            int clientsideNoticeDatabaseSize = 0; //0 FOR TESTING


            response = nClient.updateNotices(clientID, notice, date,
                    String.valueOf(clientsideNoticeDatabaseSize));
        }


        Log.e("ServerService", "Service Stopped");

        // Broadcast an intent back to MainActivity when file is downloaded
        Intent i = new Intent(TRANSACTION_DONE);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("newNotices", response);
        i.putExtras(mBundle);
        ServerService.this.sendBroadcast(i);

    }


    public class NestedClient{
        BufferedReader inFromServer = null;
        PrintWriter outToServer = null;

        Socket socket;

        public NestedClient(){
            super();

            System.out.println("nested client constructor");

        }




        public String[][] updateNotices(String clientID, String notice, String date, String myDbSize){
            String response = null;
            String[][] updateNotices = null;


            try{
                System.out.println("nclient update try ");



                socket = new Socket("10.0.2.2", 9002);

                System.out.println("connected to server");

                inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                outToServer = new PrintWriter(socket.getOutputStream(), true);

                outToServer.println("sending notice update");

                if(inFromServer.readLine().equals("send")){
                    outToServer.println(clientID);

                    outToServer.println(notice);

                    outToServer.println(date);

                    outToServer.println(myDbSize);

                    int inSize = Integer.parseInt(inFromServer.readLine());

                    updateNotices = new String[inSize][4];

                    for(int i = 0; i < inSize; i++){
                        updateNotices[i][0] = inFromServer.readLine();
                        updateNotices[i][1] = inFromServer.readLine();
                        updateNotices[i][2] = inFromServer.readLine();
                        updateNotices[i][3] = inFromServer.readLine();

                    }


                }
                //String in = inFromServer.readLine();

                /*if(in.startsWith("NOTICE LENGTH")){
                    String size = in.substring(13);
                    System.out.println(size);
                    int length = Integer.valueOf(size);


                    for(int i = 0; i < length; i++){
                        in = inFromServer.readLine();
                        response = response + "\n" + in;
                    }
                }*/



                //System.out.println("in nested client: " + response);


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
