package com.degree.abbylaura.demothree;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.BoundService;
import com.degree.abbylaura.demothree.Client.MyClientID;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class NoticeActivity extends Activity {

    //BoundService boundService;
    //boolean bound = false;

    TextView usersMessage;

    String content = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notice_activity);
        usersMessage = (TextView)
                findViewById(R.id.input_text_view);
    }

    public void onComposeNotice(View view) {
        //when this is clicked, we want to go to D2NoticeComposeActivity
        //should return with activity name and notice content

        Intent getReturnUserInput = new Intent(this, NoticeComposeActivity.class);

        final int result = 1;
        getReturnUserInput.putExtra("User Input", "APPLES");

        startActivityForResult(getReturnUserInput, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //handle text being sent back to from D2NoticeActivity


        String composeText = data.getStringExtra("User Input");

        if(composeText.equals(null)){
            usersMessage.setText(content);

        } else{
            if(bound){
                boundService.setMessageToServer("NOTICE ADDITION:" + composeText);
            }
            content = (MyClientID.myID + ": " + composeText) + "\n" + content;
            usersMessage.setText(content);

        }
    }

    public void onUpdateButton(View view) {
        if(bound){
            boundService.setMessageToServer("request update to notices");
            content = boundService.getMessageFromServer();
            usersMessage.setText(content);
        } else{
            System.out.println("SERVICE NOT BOUND");
        }
    }




    /*
        *********SERVICE BINDING********
     */

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);

        //startService(intent); //unsure if needed

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unbindService(serviceConnection);
        bound = false;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };




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
