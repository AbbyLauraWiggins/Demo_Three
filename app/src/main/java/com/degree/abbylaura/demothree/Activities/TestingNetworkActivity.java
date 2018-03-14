package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 13/03/2018.
 */

public class TestingNetworkActivity extends Activity{

    TextView writtenToServer, responseFromServer;
    EditText writeToServer;
    Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_network_activity);

        send = findViewById(R.id.buttonSend);
        writeToServer = findViewById(R.id.writeToServer);
        writtenToServer = findViewById(R.id.writtenToServer);
        responseFromServer = findViewById(R.id.responseFromServer);


        //INTENT RESPONSE FROM onSendClicked
        //track when an intent with the id TRANSACTION_DONE is executed then prep the main thread to receive a broadcast and act on it
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);
    }

    public void onSendClicked(View view) {
        String messageToSend = writeToServer.getText().toString();
        writeToServer.setText("");

        // Create an intent to run the IntentService in the background
        Intent intent = new Intent(this, NetworkService.class);

        // Pass the request that the IntentService will service from
        intent.putExtra("messageToSend", messageToSend);

        System.out.println("1: sending writeToServer = " + messageToSend);
        // Start the intent service
        this.startService(intent);
    }

    // Is alerted when the IntentService broadcasts TRANSACTION_DONE
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("8: NetworkService returned to BroadcastReceiver");

            String response = intent.getStringExtra("serverResponse");

            System.out.println("9: in BR onReceiver, with response = " + response);

            updateTextView(response);

        }
    };

    private void updateTextView(String response){
        System.out.println("10: update text view with response");

        this.responseFromServer.append("\n" + response);
    }
}
