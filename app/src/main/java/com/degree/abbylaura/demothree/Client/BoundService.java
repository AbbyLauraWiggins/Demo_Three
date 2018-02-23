package com.degree.abbylaura.demothree.Client;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by abbylaura on 21/02/2018.
 */

public class BoundService extends Service {

    private final IBinder binder = new MyBinder();
    private Client client;

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("in BS onbind"); //debugging
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        client = new Client("1"); //clientID set to 1 for testing

    }

    public String getMessageFromServer(){
        return client.getMessageFromServer();
    }

    public void setMessageToServer(String message){
        client.setMessageToServer(message);
    }


    public class MyBinder extends Binder {
        public BoundService getService(){

            return BoundService.this;
        }
    }
}
