package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class StatisticsActivity extends Activity {

    LinearLayout analyse, mystats, teamstats;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statistics_activity);

        analyse = findViewById(R.id.analyseButton);
        mystats = findViewById(R.id.myStatsButton);
        teamstats = findViewById(R.id.teamStatsButton);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        android.view.ViewGroup.LayoutParams layoutParams = teamstats.getLayoutParams();

        if(MyClientID.myPermissions > 1){ //LEADER or above - so can view stat analysis
            analyse.setVisibility(View.VISIBLE);

            if(MyClientID.myPermissions != 3){ //3 = CAL = no personal stats - just team and analysis
                layoutParams = teamstats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/2;
                teamstats.setLayoutParams(layoutParams);
                teamstats.setVisibility(View.VISIBLE);

                layoutParams = analyse.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/2;
                analyse.setLayoutParams(layoutParams);
                analyse.setVisibility(View.VISIBLE);

            }else{
                mystats.setVisibility(View.VISIBLE); //show my, teams and analysis

                layoutParams = mystats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                mystats.setLayoutParams(layoutParams);
                mystats.setVisibility(View.VISIBLE);

                layoutParams = teamstats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                teamstats.setLayoutParams(layoutParams);
                teamstats.setVisibility(View.VISIBLE);

                layoutParams = analyse.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                analyse.setLayoutParams(layoutParams);
                analyse.setVisibility(View.VISIBLE);
            }


        }else{ //show just my and team
            layoutParams = mystats.getLayoutParams();
            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight/3;
            mystats.setLayoutParams(layoutParams);
            mystats.setVisibility(View.VISIBLE);

            layoutParams = teamstats.getLayoutParams();
            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight/3;
            teamstats.setLayoutParams(layoutParams);
            teamstats.setVisibility(View.VISIBLE);
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
