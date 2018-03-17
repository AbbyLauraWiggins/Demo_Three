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

            //ArrayList<Object> passedObj = new ArrayList<Object>();
            if(typeSending.equals("NOTICE")){

                ArrayList<String> response = serviceRequest(passedList, typeSending);

                updateNotices(response);

            }

            //so send response back to activity that requested it
            Intent i = new Intent(TRANSACTION_DONE);
            NetworkService.this.sendBroadcast(i);

        }


    }

    protected ArrayList<String> serviceRequest(ArrayList<String> passedRequest, String typeSending) {
        //start a client, connect to server, send it request and object
        NestedClient nClient = new NestedClient();

        return nClient.talkToServer(passedRequest, typeSending);
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

        public ArrayList<String> talkToServer(ArrayList<String> passedRequest, String typeSending) {
            ArrayList<String> response = new ArrayList<>();

            try{
                System.out.println("5: Try block of TalkToServer NestedClient");

                socket = new Socket("10.0.2.2", 9002);

                outToServer = new ObjectOutputStream(socket.getOutputStream());
                inFromServer = new ObjectInputStream(socket.getInputStream());


                //HashMap<String, Object> sendingMap = new HashMap<>();
                //sendingMap.put("TYPE", (String) typeSending);
                //sendingMap.put("CONTENT", passedRequest);
                outToServer.writeObject(typeSending);
                outToServer.writeObject(passedRequest);


                response = (ArrayList<String>) inFromServer.readObject();


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
        NoticeRepo noticeRepo = new NoticeRepo();

        noticeRepo.delete();

        for(String al: response){
            String[] splitter = al.split("||");
            Notice notice = new Notice();
            notice.setNoticeId((String) splitter[0]);
            notice.setMemberId((String) splitter[1]);
            notice.setContents((String) splitter[2]);
            notice.setDate((String) splitter[3]);

            noticeRepo.insert(notice);
        }
    }
}
