package com.degree.abbylaura.demothree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;

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

    public void showMembers(){

        MemberRepo memberRepo = new MemberRepo();
        String selection = "Name";

        memberRepo.setSelection(selection);
        memberRepo.setQueryArgs(1);

        String[][] result = memberRepo.getMembers(); //String[setQueryArgs][numberOfResults]


        for(int i = 0; i < result[0].length; i++){

        }


    }


    public void onBackButton(View view) {
        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }
}
