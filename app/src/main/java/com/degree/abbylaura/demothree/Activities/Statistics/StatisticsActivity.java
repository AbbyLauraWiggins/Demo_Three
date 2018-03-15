package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class StatisticsActivity extends Activity {

    Button analyse, mystats;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statistics_activity);

        analyse = findViewById(R.id.analyseButton);
        mystats = findViewById(R.id.myStatsButton);

        if(MyClientID.myPermissions > 1){ //LEADER or above - so can view stat analysis
            analyse.setVisibility(View.VISIBLE);
        }

        if(MyClientID.myPermissions != 3){ //3 = CAL = no personal stats
            mystats.setVisibility(View.VISIBLE);
        }
    }


    public void onBackButton(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void goTeamStats(View view) {
        Intent intent = new Intent(this, TeamStatsOverview.class);
        startActivity(intent);
    }

    public void goMyStats(View view) {
        Intent intent = new Intent(this, MyStats.class);
        startActivity(intent);
    }

    public void goAnalyseStats(View view) {
        Intent intent = new Intent(this, AnalyseStats.class);
        startActivity(intent);
    }
}
