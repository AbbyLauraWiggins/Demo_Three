package com.degree.abbylaura.demothree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by abbylaura on 16/02/2018.
 */

public class RegisterActivity extends Activity {

    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Intent activityThatCalled = getIntent();
        String emailAddress = (String) activityThatCalled.getSerializableExtra("emailaddress");
        String password = (String) activityThatCalled.getSerializableExtra("password");

        emailEditText = (EditText) findViewById(R.id.register_email);
        passwordEditText = (EditText) findViewById(R.id.register_password);

        emailEditText.setText(emailAddress);
        passwordEditText.setText(password);



        //*****USE MyClientID newID*****



    }

    public void onFinishRegistrationClick(View view) {
        Intent goingBack = new Intent();
        System.out.println("GOING BACK");
        goingBack.putExtra("ValidReg", true);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}
