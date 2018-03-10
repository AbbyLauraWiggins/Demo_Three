package com.degree.abbylaura.demothree.Activities.SignIn;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.SharedPreferences;
import android.content.Intent;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;
import com.degree.abbylaura.demothree.Test.TestDatabaseActivity;

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

        //TODO validate email and password pair
        //if valid then save login and go to home
        //if not valid then TOAST error message

        MemberRepo memberRepo = new MemberRepo();

        String where = "WHERE Email = '" + emailEditText.getText().toString() + "'" +
                " AND Password = '" + passwordEditText.getText().toString() + "'";

        memberRepo.setWhereClause(where);

        String[][] result = memberRepo.getMembers();

        if(result[0][0] == null ){
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }else{
            String id = result[0][0];
            MyClientID.setID(id);//, result[0][7]);
            MyClientID.setMyTeamID(result[7][0]);
            Intent goToHome  = new Intent(this, HomeActivity.class);
            startActivity(goToHome);
        }


    }


    public void onSkipForTestingClick(View view) {
        Intent goToHome  = new Intent(this, HomeActivity.class);
        startActivity(goToHome);
    }


    public void onSkipForDatabase(View view) {
        Intent goToTestDB = new Intent(this, TestDatabaseActivity.class);
        startActivity(goToTestDB);
    }
}

