package com.degree.abbylaura.demothree.Activities.Members;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.Activities.Members.MemberFragment;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class MembersActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.members_activity);

        showMembers();

    }

    @SuppressLint("ResourceType")
    public void showMembers(){

        MemberRepo memberRepo = new MemberRepo();

        String[][] result = memberRepo.getMembers(); //String[setQueryArgs][numberOfResults]

        LinearLayout fragContainer = (LinearLayout) findViewById(R.id.member_linear_layout);

        LinearLayout layout = new LinearLayout(this);


        for(int i = 0; i < result[0].length; i++){
            layout.setOrientation(LinearLayout.VERTICAL);

            layout.setId(1);

            FragmentTransaction ft =  getFragmentManager().beginTransaction();

            String name = result[1][i];
            String pos = result[5][i];
            String res = result[6][i];

            if(res.equals("None")){
                res = "";
            }

            ft.add(layout.getId(), MemberFragment.newInstance(name, pos, res, i), "someTag1").commit();

        }

        fragContainer.addView(layout);



    }


    public void onBackButton(View view) {
        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }

    public void onGoToProfile(View view) {
        Intent goToProfile = new Intent(this, ProfileActivity.class);
        goToProfile.putExtra("MemberID", MyClientID.myID); //send my own ID as this button is to view own profile
        startActivity(goToProfile);
    }
}
