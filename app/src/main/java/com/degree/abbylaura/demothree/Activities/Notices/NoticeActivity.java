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
import android.os.Parcelable;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class NoticeActivity extends Activity {

    TextView usersMessage;
    RelativeLayout relBottom;
    ArrayList<String> noticeBuffer;

    int noticeIdNum;
    String winningNoticeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notice_activity);
        usersMessage = findViewById(R.id.input_text_view);

        this.noticeIdNum = 0;
        this.winningNoticeId = "";

        noticeBuffer = new ArrayList<>();

        updateContent(null);

        relBottom = findViewById(R.id.rel_bottom_layout);
        relBottom.setBackgroundColor(Color.WHITE);



        /* Allows use to track when an intent with the id TRANSACTION_DONE is executed
         * We can call for an intent to execute something and then tell use when it finishes
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);

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

    public void updateContent(String addition) {

        //add addition to Database Notice table
        NoticeRepo noticeRepo = new NoticeRepo();

        if(addition != null){
            Date date = Calendar.getInstance().getTime();

            noticeBuffer.add(MyClientID.myID + "||" + addition + "||" + date.toString());

        }

        sendBufferToServer();

    }

    private void sendBufferToServer(){
        NoticeRepo noticeRepo = new NoticeRepo();

        if(!noticeBuffer.isEmpty()){
            System.out.println(noticeBuffer.toString());
            Intent intent = new Intent(this, NetworkService.class);
            intent.putExtra("CLASS", noticeBuffer);
            intent.putExtra("typeSending", "NOTICE");
            this.startService(intent);

        }


    }

    /*
     * Is alerted when the IntentService broadcasts TRANSACTION_DONE
     */
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            //NoticeActivity.this.unregisterReceiver(this);
            emptyNoticeBuffer();

            Log.e("NetworkService", "Service Received");

            //updateDB(response);

            updateUI();


        }

    };

    private void updateDB(ArrayList<String> notices){
        NoticeRepo noticeRepo = new NoticeRepo();



        if(notices != null){
            //noticeRepo.delete(); //delete all notices

            System.out.println("UPDATE DB >>>>>>>" + notices.toString());
            Notice notice = new Notice();
            notice.setNoticeId((String) notices.get(0));
            notice.setMemberId((String) notices.get(1));
            notice.setContents((String) notices.get(2));
            notice.setDate((String) notices.get(3));

            noticeRepo.insert(notice);
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

    /*
     * Called from updateUI;
     */
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

    public void onBackButton(View view) {
        //go back to the activity that called it in the first place
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void emptyNoticeBuffer(){
        this.noticeBuffer = new ArrayList<>();
    }

    protected void onPause() {
        super.onPause();

        unregisterReceiver(clientReceiver);
    }

    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);
    }

}
