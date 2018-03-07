package com.degree.abbylaura.demothree.Activities.SignIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 16/02/2018.
 */

public class RegisterActivity extends Activity {

    EditText emailEditText, passwordEditText, nameEditText, dobEditText, teamEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Intent activityThatCalled = getIntent();
        String emailAddress = (String) activityThatCalled.getSerializableExtra("emailaddress");
        String password = (String) activityThatCalled.getSerializableExtra("password");

        emailEditText = findViewById(R.id.register_email);
        passwordEditText = findViewById(R.id.register_password);
        nameEditText = findViewById(R.id.register_name);
        dobEditText = findViewById(R.id.register_dob);
        teamEditText = findViewById(R.id.register_teamID);

        emailEditText.setText(emailAddress);
        passwordEditText.setText(password);



        //*****USE MyClientID newID*****


    }

    public void onFinishRegistrationClick(View view) {

        if(registrationValid()){
            //TODO add to database and then go back

            Member member = new Member();
            MemberRepo memberRepo   = new MemberRepo();

            //TODO a better way to generate new unique primary key
            String id = String.valueOf(memberRepo.getMemberTableSize() + 1);

            member.setMemberId(id);
            member.setName(nameEditText.getText().toString());
            member.setEmail(emailEditText.getText().toString());
            member.setPassword(passwordEditText.getText().toString());
            member.setDOB(dobEditText.getText().toString());
            member.setPositions(null);
            member.setResponsibilities("None");
            member.setTeamId(teamEditText.getText().toString());
            member.setPermissions("BASIC");
            memberRepo.insert(member);

            goBack();
        } else {
            //TODO toast with error message
        }





    }

    private Boolean registrationValid(){
        //TODO check registration values are valid
        //TODO check noone else is using the same email address

        //for now just return true
        return true;
    }

    private void goBack(){
        Intent goingBack = new Intent();
        System.out.println("GOING BACK");
        goingBack.putExtra("ValidReg", true);
        goingBack.putExtra("Email", emailEditText.getText().toString());
        goingBack.putExtra("Password", passwordEditText.getText().toString());
        setResult(RESULT_OK, goingBack);
        finish();
    }
}
