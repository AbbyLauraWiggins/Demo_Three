package com.degree.abbylaura.demothree.Activities.Notices;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.NoticeRepo;
import com.degree.abbylaura.demothree.Database.Schema.Notice;
import com.degree.abbylaura.demothree.R;

import java.io.Serializable;
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
    ArrayList<ArrayList<String>> noticeBuffer;
    int noticeIdNum;
    String winningNoticeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notice_activity);
        usersMessage = findViewById(R.id.input_text_view);

        this.noticeIdNum = 0;
        this.winningNoticeId = "";

        updateContent(null);

        relBottom = findViewById(R.id.rel_bottom_layout);
        relBottom.setBackgroundColor(Color.WHITE);

        noticeBuffer = new ArrayList<>();

        /* Allows use to track when an intent with the id TRANSACTION_DONE is executed
         * We can call for an intent to execute something and then tell use when it finishes
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);

    }

    /**
     * starts the Intent to run intent service in background
     */
    public void updateContent(String addition) {

        //add addition to Database Notice table
        NoticeRepo noticeRepo = new NoticeRepo();

        if(addition != null){
            Date date = Calendar.getInstance().getTime();

            /*
            Notice notice = new Notice();
            notice.setMemberId(MyClientID.myID);
            notice.setContents(addition);
            notice.setDate(date.toString());
            noticeRepo.insert(notice);

            We must send all new notices to server first, to ensure that noticeIDs are kept synchronised
            We will then update the UI from server
              > Fill clientDB from serverDB so that you can still view old notices when connection is lost
              > But cannot add new notices or update notices without server connection
            */


            ArrayList<String> noticeAL = new ArrayList<>();
            noticeAL.add(MyClientID.myID);
            noticeAL.add(addition);
            noticeAL.add(date.toString());
            noticeBuffer.add(noticeAL);

            sendNewToServer();
        }

    }

    private void sendNewToServer(){
        Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("messageToSend", noticeBuffer); //send notices to buffer
        intent.putExtra("typeSending", "NOTICE");
        this.startService(intent);
    }

    public void updateDB(ArrayList<ArrayList<String>> allNotices){
        System.out.println(">>>>>>>>>>>>>>>> updateDB " + allNotices);
        if(allNotices != null){
            System.out.print("Not all notices are null");

            for(ArrayList<String> row: allNotices) {
                //if ID of serverDBnotice is a higher increment than highest clientDBnotice, add to clientDB
                System.out.println("updateDB: " + row);
                if (Integer.parseInt(row.get(3)) > noticeIdNum) {
                    Notice notice = new Notice();
                    notice.setMemberId(row.get(0));
                    notice.setContents(row.get(1));
                    notice.setDate(row.get(2));
                    notice.setNoticeId(row.get(3)); //this is AUTOINCREMENTED in server, but not in client

                    NoticeRepo noticeRepo = new NoticeRepo();
                    noticeRepo.insert(notice);
                }
            }

        }
    }

    public void updateUI(){
        //for all entries in Notice table not on UI, add to UI using updateTextView
        NoticeRepo noticeRepo = new NoticeRepo();
        ArrayList<ArrayList<String>> noticeArray = noticeRepo.getNotices();

        LinearLayout fragContainer = findViewById(R.id.fragmentContainer);
        if(fragContainer != null){
            fragContainer.removeAllViews();
        }

        int colour = 0;

        Collections.reverse(noticeArray);//because newer notices need to be added last
        String winningID;

        for(int i = 0; i < noticeArray.size(); i++){


            ArrayList<String> row = noticeArray.get(i);

            String memberName = row.get(0);
            String noticeContent = row.get(1);
            String noticeDate = row.get(2);
            if(Integer.parseInt(row.get(3)) > noticeIdNum){
                noticeIdNum = Integer.parseInt(row.get(3));
                winningID = row.get(3);
            }

            System.out.println(memberName + " o " + noticeContent + " o " + noticeDate);

            colour++;

            updateTextView(fragContainer, noticeContent, memberName, noticeDate, colour);
        }


    }

    @SuppressLint("ResourceType")
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


    public void onBackButton(View view) {
        //go back to the activity that called it in the first place
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void emptyNoticeBuffer(){
        this.noticeBuffer = new ArrayList<>();
    }


    /*
     * Is alerted when the IntentService broadcasts TRANSACTION_DONE
     *
     * COMMENTED OUT UNTIL SERVER IS FIXED
     */
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {


            /**
             *
             *
             * TODO maybe try next:
             *    having return from server being hashmap of <String, Object> so we can update UI with obj each time
             *
             *
             *
             *
             */

            Log.e("NetworkService", "Service Received");

            ArrayList<? extends ArrayList<String>> responseList =
                    intent.getParcelableArrayListExtra("serverResponseList");

            String response = intent.getStringExtra("serverResponseString");

            if(response != null){

                if(response.equals("Notices Added")){
                    //empty the notice buffer
                    emptyNoticeBuffer();
                }
                //otherwise do not empty notice buffer, as we want to keep those contents in there
                //for next attempt to send to server
            }

            updateDB((ArrayList<ArrayList<String>>) responseList);
            updateUI();

        }
    };






}
