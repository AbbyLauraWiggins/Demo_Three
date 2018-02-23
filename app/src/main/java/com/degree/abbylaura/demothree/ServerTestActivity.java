package com.degree.abbylaura.demothree;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.BoundService;
import com.degree.abbylaura.demothree.Client.Client;
import com.degree.abbylaura.demothree.Client.ClientHelper;

import org.w3c.dom.Text;

//import com.degree.abbylaura.demothree.Client.ClientHandler;

/**
 * Created by abbylaura on 20/02/2018.
 */

public class ServerTestActivity extends Activity {

    BoundService boundService;
    boolean bound = false;

    TextView messageFromServer;
    EditText messageToServer;
    Button send;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_test_activity);

        messageFromServer = (TextView) findViewById(R.id.message_from_server);
        messageToServer = (EditText) findViewById(R.id.message_to_server);
        send = (Button) findViewById(R.id.send_button);

        TextView emailtv = (TextView) findViewById(R.id.emailTV);
        TextView passwordtv = (TextView) findViewById(R.id.passTV);

        Intent activityThatCalled = getIntent();
        email = activityThatCalled.getStringExtra("email");
        password = activityThatCalled.getStringExtra("password");

        emailtv.setText(email);
        passwordtv.setText(password);


    }

    public void onSendClick(View view) {

        if(bound){
            System.out.println("on click: " + messageToServer.getText().toString());

            boundService.setMessageToServer(messageToServer.getText().toString());

            messageFromServer.setText(boundService.getMessageFromServer());
        } else{
            System.out.println("SERVICE NOT BOUND");
        }


    }



    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("in MA onstart");
        Intent intent = new Intent(this, BoundService.class);

        //startService(intent); //unsure if needed

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("in MA onstop");

        unbindService(serviceConnection);
        bound = false;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("in MA onserviceconnect");


            BoundService.MyBinder myBinder = (BoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("in MA onserviceDISconnected");

            bound = false;
        }
    };

    public void onTest(View view) {
        ClientHelper.setEmail(email);
        ClientHelper.setPassword(password);
        ClientHelper.setMessageToServer("Request Login");

        if(ClientHelper.loginValid){
            messageFromServer.setText(ClientHelper.messageFromServer);
            System.out.println("working");
        }
    }
}
