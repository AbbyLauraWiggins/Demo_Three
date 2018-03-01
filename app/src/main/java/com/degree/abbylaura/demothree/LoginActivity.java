package com.degree.abbylaura.demothree;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.SharedPreferences;
import android.content.Intent;

import com.degree.abbylaura.demothree.Client.MyClientID;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    EditText emailEditText;
    EditText passwordEditText;
    Button btnRegister;
    private static final int REG_INFO = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /**** ensure there is data to retrieve ****/

        emailEditText = (EditText) findViewById(R.id.signin_email);
        passwordEditText = (EditText) findViewById(R.id.signin_password);

        if(savedInstanceState != null) { //there is data saved
            String emailInput = savedInstanceState.getString("EMAIL");
            String passwordInput = savedInstanceState.getString("PASSWORD");

            emailEditText.setText(emailInput);
            passwordEditText.setText(passwordInput);
        }



        /**** retrieve data in shared preferences or EMPTY if no data ****/

        String savedEmail = getPreferences(Context.MODE_PRIVATE).getString("EMAIL", "EMPTY");

        if(!savedEmail.equals("EMPTY")){ //a preference value does exist
            emailEditText.setText(savedEmail);
        }

        String savedPassword = getPreferences(Context.MODE_PRIVATE).getString("PASSWORD", "EMPTY");

        if(!savedPassword.equals("EMPTY")){
            passwordEditText.setText(savedPassword);
        }


        //creating register button here (rather than in function below)
        //as will come back to this screen after


        //return whether registration was valid or cancelled: 0 = not valid, 1 = valid


        btnRegister = (Button) findViewById(R.id.register_button);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                goToRegister.putExtra("emailaddress", emailEditText.getText().toString());
                goToRegister.putExtra("password", passwordEditText.getText().toString());
                startActivityForResult(goToRegister, REG_INFO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);



        //check that intent with id REG_INFO called here
        if(requestCode == REG_INFO){

            if(data.getBooleanExtra("ValidReg", true)){
                //TODO send info to sign in so new user signs in

            }
        }

    }

    //Uses SHARED PREFERENCES to save data if user kills app
    private void saveInput(){

        SharedPreferences.Editor editSP = getPreferences(Context.MODE_PRIVATE).edit();

        // Add the key "EMAIL" and assign it to the value
        editSP.putString("EMAIL", emailEditText.getText().toString());
        editSP.putString("PASSWORD", passwordEditText.getText().toString());

        // Save the shared preferences
        editSP.commit();

    }
    @Override
    protected void onStop() {
        saveInput();
        super.onStop();
    }




    //Uses OUTSTATE to save state if android OS crashes
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Save the edittext value with key
        outState.putString("EMAIL", emailEditText.getText().toString());
        outState.putString("PASSWORD", passwordEditText.getText().toString());

        super.onSaveInstanceState(outState);
    }






    public void onSignInClick(View view) {

        //TODO validate email and password pair
        //if valid then save login and go to home
        //if not valid then TOAST error message

        // Use MyClientID getMyID
        MyClientID mc = new MyClientID();
        mc.getMyID(emailEditText.getText().toString(), passwordEditText.getText().toString());

        /* DEBUGGING
        Intent intent = new Intent(this, ServerTestActivity.class);
        intent.putExtra("email", emailEditText.getText().toString());
        intent.putExtra("password", passwordEditText.getText().toString());
        startActivity(intent);
        */

        Intent goToHome  = new Intent(this, HomeActivity.class);
        startActivity(goToHome);



    }


    public void onSkipForTestingClick(View view) {
        Intent goToHome  = new Intent(this, HomeActivity.class);
        startActivity(goToHome);
    }


}

