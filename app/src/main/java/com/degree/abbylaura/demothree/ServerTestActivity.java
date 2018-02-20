package com.degree.abbylaura.demothree;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.ClientHandler;

/**
 * Created by abbylaura on 20/02/2018.
 */

public class ServerTestActivity extends Activity {

    TextView messageFromServerTV;
    EditText messageToServerET;

    String messageFromServer;
    String messageToServer;
    ClientHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.server_test_activity);

        messageFromServerTV = (TextView) findViewById(R.id.message_from_server);
        messageToServerET = (EditText) findViewById(R.id.message_to_server);

        handler = new ClientHandler();
    }

    private void watchMessageFromServer(){
        String previous = null;
        String current = null;

        while(true){
            if(!current.equals(previous)){
                messageFromServer = current;
                messageFromServerTV.setText(messageFromServer);
            }
            previous = current;
            current = handler.getMessageFromServer();

        }
    }

    public void onSendClick(View view) {
        messageToServer = messageToServerET.getText().toString();

        handler.setMessageToServer(messageToServer);
    }
}
