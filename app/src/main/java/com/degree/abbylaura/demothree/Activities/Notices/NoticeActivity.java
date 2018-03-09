package com.degree.abbylaura.demothree.Activities.Notices;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Schema.Notice;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class NoticeActivity extends Activity {

    TextView usersMessage;
    RelativeLayout relBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notice_activity);
        usersMessage = findViewById(R.id.input_text_view);

        updateContent(null);

        relBottom = findViewById(R.id.rel_bottom_layout);
        relBottom.setBackgroundColor(Color.WHITE);

        /* Allows use to track when an intent with the id TRANSACTION_DONE is executed
         * We can call for an intent to execute something and then tell use when it finishes
         *
         * COMMENTED OUT UNTIL SERVER IS FIXED
         *
         *
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServerService.TRANSACTION_DONE);

        // Prepare the main thread to receive a broadcast and act on it
        registerReceiver(clientReceiver, intentFilter);

        */

    }


    /**
     * starts the Intent to run intent service in background
     */
    public void updateContent(String addition) {

        //TODO add addition to Database Notice table
        NoticeRepo noticeRepo = new NoticeRepo();

        if(addition != null){
            Date date = Calendar.getInstance().getTime();
            Notice notice = new Notice();

            notice.setMemberId(MyClientID.myID);
            notice.setContents(addition);
            notice.setDate(date.toString());

            noticeRepo.insert(notice);

            System.out.println("inserted " + addition);
        }


        //TODO for all entries in Notice table not on UI, add to UI using updateTextView

        ArrayList<ArrayList<String>> noticeArray = noticeRepo.getNotices();

        LinearLayout fragContainer = findViewById(R.id.fragmentContainer);
        if(fragContainer != null){
            fragContainer.removeAllViews();
        }

        int colour = 0;

        Collections.reverse(noticeArray);//because newer notices need to be added last

        for(int i = 0; i < noticeArray.size(); i++){


            ArrayList<String> row = noticeArray.get(i);

            String memberName = row.get(0);
            String noticeContent = row.get(1);
            String noticeDate = row.get(2);

            System.out.println(memberName + " o " + noticeContent + " o " + noticeDate);

            colour++;

            updateTextView(fragContainer, noticeContent, memberName, noticeDate, colour);
        }



        /* COMMUNICATION WITH SERVER

         * COMMENTED OUT UNTIL SERVER FIXTED




        // Create an intent to run the IntentService in the background
        Intent intent = new Intent(this, ServerService.class);

        // Pass the request that the IntentService will service from
        intent.putExtra("serviceRequested", "NoticeActivityAddition");
        intent.putExtra("content", addition);
        intent.putExtra("clientID", "1"); //*****CLIENT ID = 1 FOR TESTING
        intent.putExtra("date", String.valueOf(date));

        System.out.println("updatecontent: going to start service");

        // Start the intent service
        this.startService(intent);
        */
    }






    protected void updateTextView(LinearLayout fragContainer, String notice, String memberName, String date, int i){
        System.out.print("in update text view");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setId(12345);

        FragmentTransaction ft =  getFragmentManager().beginTransaction();
        ft.add(layout.getId(), NoticeFragment.newInstance(notice, memberName, date, i), "someTag1").commit();

        fragContainer.addView(layout);
    }


    public void onComposeNotice(View view) {
        //when this is clicked, we want to go to D2NoticeComposeActivity
        //should return with activity name and notice content

        Intent getReturnUserInput = new Intent(this, NoticeComposeActivity.class);

        final int result = 1;
        //getReturnUserInput.putExtra("User Input", "TEST");

        startActivityForResult(getReturnUserInput, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //handle text being sent back to from D2NoticeActivity
        String composeText = data.getStringExtra("User Input");
        System.out.println("return from compose: " + composeText);

        updateContent(composeText);
    }


    protected void onUpdateButton(View view) {
        //boundService.setMessageToServer("request update to notices");
        //content = boundService.getMessageFromServer();
        //usersMessage.setText(content);
        updateContent(null);
    }


    public void onBackButton(View view) {
        //go back to the activity that called it in the first place
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }


    /*
     * Is alerted when the IntentService broadcasts TRANSACTION_DONE
     *
     * COMMENTED OUT UNTIL SERVER IS FIXED
     *
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("ServerService", "Service Received");

            String[][] noticesReceived=null;
            Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("serverResponse");
            if(objectArray!=null){
                noticesReceived = new String[objectArray.length][];

                for(int i=0;i<objectArray.length;i++){
                    noticesReceived[i]=(String[]) objectArray[i];
                }

            }


            for(int i = 0; i > noticesReceived.length; i++){
                updateTextView(noticesReceived[i][2], noticesReceived[i][1], noticesReceived[i][3]);
            }


        }
    };

     */




}
