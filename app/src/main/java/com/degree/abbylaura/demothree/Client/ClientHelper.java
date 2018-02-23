package com.degree.abbylaura.demothree.Client;


/**
 * Created by abbylaura on 21/02/2018.
 */

public class ClientHelper {

    public static String messageFromServer, messageToServer;

    public static String clientEmail, clientPassword;

    public static Boolean loginValid = false;


    public ClientHelper(){
        super();

    }



    public static void setMessageToServer(String message){
        messageToServer = message;
    }

    public static void setMessageFromServer(String message){
        messageFromServer = message;
    }

    public static void setEmail(String email){
        clientEmail = email;
    }

    public static void setPassword(String password){
        clientPassword = password;
    }

    public static void setLoginValid(Boolean bool){
        loginValid = bool;
    }






}
