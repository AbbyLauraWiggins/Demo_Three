package com.degree.abbylaura.demothree.Activities.SignIn;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.SharedPreferences;
import android.content.Intent;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.R;
import com.degree.abbylaura.demothree.Test.TestDatabaseActivity;

import java.util.ArrayList;

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

        emailEditText = findViewById(R.id.signin_email);
        passwordEditText = findViewById(R.id.signin_password);

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


        btnRegister = findViewById(R.id.register_button);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                goToRegister.putExtra("emailaddress", emailEditText.getText().toString());
                goToRegister.putExtra("password", passwordEditText.getText().toString());
                startActivityForResult(goToRegister, REG_INFO);
            }
        });


        /* Allows use to track when an intent with the id TRANSACTION_DONE is executed
         * We can call for an intent to execute something and then tell use when it finishes
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE);
        registerReceiver(clientReceiver, intentFilter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //TODO ensure correct registration rather than just come back
        emailEditText.setText(data.getStringExtra("Email"));
        passwordEditText.setText(data.getStringExtra("Password"));

        Toast.makeText(this, "Registration successful, please log in.", Toast.LENGTH_LONG).show();

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
        /*MemberRepo memberRepo = new MemberRepo();
        String where = "WHERE Email = '" + emailEditText.getText().toString() + "'" +
                " AND Password = '" + passwordEditText.getText().toString() + "'";
        memberRepo.setWhereClause(where);
        String[][] result = memberRepo.getMembers();*/


        ArrayList<String> logInDetails = new ArrayList<>();
        logInDetails.add(emailEditText.getText().toString());
        logInDetails.add(passwordEditText.getText().toString());

        Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("typeSending", "LOGIN");
        intent.putExtra("CLASS", logInDetails);
        intent.putExtra("TABLESIZE", "0");
        this.startService(intent);

    }

    /*
 * Is alerted when the IntentService broadcasts TRANSACTION_DONE
 */
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String valid = intent.getStringExtra("VALIDATION");

            if(valid.equals("false")){
                showToast();
            }else{
                String[] splitter = valid.split("4h4f");
                String id = splitter[0];
                MyClientID.setID(id);//, result[0][7]);

                String teamId = splitter[1];
                MyClientID.setMyTeamID(teamId);

                goToHome();
            }
        }

    };

    private void goToHome(){
        Intent goToHome  = new Intent(this, HomeActivity.class);
        startActivity(goToHome);
    }

    private void showToast(){
        Toast.makeText(this, getString(R.string.toast_invalid_message), Toast.LENGTH_SHORT).show();
    }

    public void onSkipForTestingClick(View view) {
        MyClientID.setTESTid("3");
        MyClientID.setMyPermissions(2);
        Intent goToNetwork = new Intent(this, HomeActivity.class);
        startActivity(goToNetwork);
    }


    public void onSkipForDatabase(View view) {
        Intent goToTestDB = new Intent(this, TestDatabaseActivity.class);
        startActivity(goToTestDB);
    }
}

