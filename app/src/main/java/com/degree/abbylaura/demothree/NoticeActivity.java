package com.degree.abbylaura.demothree;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.MyClientID;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class NoticeActivity extends Activity {

    TextView usersMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notice_activity);
        usersMessage = (TextView)
                findViewById(R.id.input_text_view);



        // Allows use to track when an intent with the id TRANSACTION_DONE is executed
        // We can call for an intent to execute something and then tell use when it finishes
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServerService.TRANSACTION_DONE);

        // Prepare the main thread to receive a broadcast and act on it
        registerReceiver(clientReceiver, intentFilter);

    }


    /**
     * starts the Intent to run intent service in background
     */
    public void updateContent(String addition) {


        Date date = Calendar.getInstance().getTime();

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
    }



    // Is alerted when the IntentService broadcasts TRANSACTION_DONE
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


    @SuppressLint("ResourceType")
    protected void updateTextView(String notice, String clientID, String date){
        LinearLayout fragContainer = (LinearLayout) findViewById(R.id.fragmentContainer);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setId(12345);

        FragmentTransaction ft =  getFragmentManager().beginTransaction();
        ft.add(layout.getId(), NoticeFragment.newInstance(notice, clientID, date), "someTag1").commit();



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

    }





    /*
        *****ACTIVITY NAVIGATION************
     */

    public void onBackButton(View view) {
        //go back to the activity that called it in the first place
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }


}
