package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 02/03/2018.
 *
 * In the Intent that called this activity, you must put the MEMBERID of the member whose profile you wish to see
 */

public class ProfileActivity extends Activity {

    TextView name, teamName, email, positionsPlayed, responsibilities;
    private String thisID;

    Button back, addPos, addRes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        name = (TextView) findViewById(R.id.member_name);
        teamName = (TextView) findViewById(R.id.team_name);
        email = (TextView) findViewById(R.id.email);
        positionsPlayed = (TextView) findViewById(R.id.positions_played);
        responsibilities = (TextView) findViewById(R.id.responsibilities);

        back = (Button) findViewById(R.id.back_button);
        addPos = (Button) findViewById(R.id.addPosition);
        addRes = (Button) findViewById(R.id.addResponsibilty);

        Intent activityThatCalled = getIntent();
        thisID = (String) activityThatCalled.getSerializableExtra("MemberID");

        setProfile();

    }

    public void setProfile(){
        Member member = new Member();
        MemberRepo memberRepo = new MemberRepo();

        String where = " WHERE " + Member.TABLE + "." + Member.KEY_MemberId + "='" + thisID + "'";

        memberRepo.setWhereClause(where);

        String[][] profileDetails = memberRepo.getMembers();

        if(profileDetails[1][0]!= null){
            name.setText(profileDetails[1][0]);
        }
        if(profileDetails[2][0]!= null){
            email.setText(profileDetails[2][0]);
        }
        if(profileDetails[7][0]!= null) {
            teamName.setText(profileDetails[7][0]);
        }
        if(profileDetails[5][0]!= null) {
            positionsPlayed.setText(profileDetails[5][0]);
        }
        if(profileDetails[6][0]!= null) {
            responsibilities.setText(profileDetails[6][0]);
        }


    }

    public void addPosition(View view) {
    }

    public void addResponsibility(View view) {
    }

    public void onBackButton(View view) {
        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }
}
