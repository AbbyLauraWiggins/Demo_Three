package com.degree.abbylaura.demothree.Activities;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Schema.Notice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

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


    //Runs when called from an activity
    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("NetworkService", "Service Started");


        ArrayList<String> passedList = intent.getStringArrayListExtra("CLASS");

        if(passedList == null){
            Intent i = new Intent(TRANSACTION_DONE);
            NetworkService.this.sendBroadcast(i);
        }else{
            String typeSending = intent.getStringExtra("typeSending");

            int size = Integer.parseInt(intent.getStringExtra("TABLESIZE"));

            //ArrayList<Object> passedObj = new ArrayList<Object>();
            if(typeSending.equals("NOTICE")){
                NoticeRepo noticeRepo = new NoticeRepo();

                ArrayList<String> response = serviceRequest(passedList, typeSending, size);

                updateNotices(response);

            }

            //so send response back to activity that requested it
            System.out.println("JUST BEFORE INTENT");
            Intent i = new Intent(TRANSACTION_DONE);
            System.out.println("JUST AFTER INTENT");

            NetworkService.this.sendBroadcast(i);

            System.out.println("after send broadcast");

        }


    }

    protected ArrayList<String> serviceRequest(ArrayList<String> passedRequest, String typeSending, int size) {
        //start a client, connect to server, send it request and object
        NestedClient nClient = new NestedClient();

        return nClient.talkToServer(passedRequest, typeSending, size);
    }



    /*
     * Will return an ArrayList<String> in format that each string = table row: "col1||col2||col3..."
     */
    public class NestedClient{
        ObjectOutputStream outToServer;
        ObjectInputStream inFromServer;

        Socket socket;

        public NestedClient(){
            super();
            System.out.println("4: Nested Client constructor");

        }

        public ArrayList<String> talkToServer(ArrayList<String> passedRequest, String typeSending, int size) {
            ArrayList<String> response = new ArrayList<>();

            try{
                System.out.println("5: Try block of TalkToServer NestedClient");

                socket = new Socket("192.168.0.18", 9002); //192.168.0.18

                outToServer = new ObjectOutputStream(socket.getOutputStream());
                inFromServer = new ObjectInputStream(socket.getInputStream());



                outToServer.writeObject(typeSending);   //DATABASE TABLE TYPE
                outToServer.writeObject(size);          //SIZE OF CLIENT TABLE
                outToServer.writeObject(passedRequest); //WHAT WE WANT TO ADD TO TABLE



                response = (ArrayList<String>) inFromServer.readObject();
                System.out.println("nClient try response: " + response);


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



    private void updateNotices(ArrayList<String> response){

        if(!response.isEmpty()){
            if(!(response.get(0).equals("CODE:4698:EMPTYBUFFER"))){
                System.out.println("updateNotices: " + response.toString());

                NoticeRepo noticeRepo = new NoticeRepo();

                for(String al: response){
                    String[] splitter = al.split("4h4f");
                    Notice notice = new Notice();
                    notice.setNoticeId((String) splitter[0]);
                    notice.setMemberId((String) splitter[1]);
                    notice.setContents((String) splitter[2]);
                    notice.setDate((String) splitter[3]);

                    noticeRepo.insert(notice);
                }
            }

        }
    }
}
