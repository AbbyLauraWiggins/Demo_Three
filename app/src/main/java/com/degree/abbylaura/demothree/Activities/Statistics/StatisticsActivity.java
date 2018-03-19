package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class StatisticsActivity extends Activity {

    LinearLayout analyse, mystats, teamstats;
    ImageView my, team, an;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statistics_activity);

        analyse = findViewById(R.id.analyseButton);
        mystats = findViewById(R.id.myStatsButton);
        teamstats = findViewById(R.id.teamStatsButton);
        my = findViewById(R.id.myStatsImage);
        team = findViewById(R.id.teamStatsImage);
        an = findViewById(R.id.analyseImage);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        android.view.ViewGroup.LayoutParams layoutParams = teamstats.getLayoutParams();

        if(MyClientID.myPermissions > 1){ //LEADER or above - so can view stat analysis
            analyse.setVisibility(View.VISIBLE);

            if(MyClientID.myPermissions == 3){ //3 = CAL = no personal stats - just team and analysis
                layoutParams = teamstats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                teamstats.setLayoutParams(layoutParams);
                teamstats.setVisibility(View.VISIBLE);

                /*layoutParams = team.getLayoutParams();
                layoutParams.width = (int) (teamstats.getHeight() * 0.6);
                layoutParams.height = (int) (teamstats.getHeight() * 0.6);
                team.setLayoutParams(layoutParams);
                team.setVisibility(View.VISIBLE);*/

                team.setImageResource(0);
                Drawable draw = getResources().getDrawable(R.drawable.ic_people_black_48dp);
                draw = resize(draw, (int) (teamstats.getHeight() * 0.6)+ 1);
                team.setImageDrawable(draw);


                layoutParams = analyse.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                analyse.setLayoutParams(layoutParams);
                analyse.setVisibility(View.VISIBLE);

                an.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ic_poll_black_48dp);
                draw = resize(draw, (int) (analyse.getHeight() * 0.6)+ 1);
                an.setImageDrawable(draw);


            }else{
                mystats.setVisibility(View.VISIBLE); //show my, teams and analysis

                layoutParams = mystats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/4;
                mystats.setLayoutParams(layoutParams);
                mystats.setVisibility(View.VISIBLE);

                my.setImageResource(0);
                Drawable draw = getResources().getDrawable(R.drawable.ic_person_black_48dp);
                draw = resize(draw, (int) (mystats.getHeight() * 0.6) + 1);
                my.setImageDrawable(draw);


                layoutParams = teamstats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/4;
                teamstats.setLayoutParams(layoutParams);
                teamstats.setVisibility(View.VISIBLE);

                team.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ic_people_black_48dp);
                draw = resize(draw, (int) (teamstats.getHeight() * 0.6)+ 1);
                team.setImageDrawable(draw);

                layoutParams = analyse.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/4;
                analyse.setLayoutParams(layoutParams);
                analyse.setVisibility(View.VISIBLE);

                an.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ic_poll_black_48dp);
                draw = resize(draw, (int) (analyse.getHeight() * 0.6)+ 1);
                an.setImageDrawable(draw);
            }


        }else{ //show just my and team
            layoutParams = mystats.getLayoutParams();
            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight/3;
            mystats.setLayoutParams(layoutParams);
            mystats.setVisibility(View.VISIBLE);

            my.setImageResource(0);
            Drawable draw = getResources().getDrawable(R.drawable.ic_person_black_48dp);
            draw = resize(draw, (int) (mystats.getHeight() * 0.6)+ 1);
            my.setImageDrawable(draw);


            layoutParams = teamstats.getLayoutParams();
            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight/3;
            teamstats.setLayoutParams(layoutParams);
            teamstats.setVisibility(View.VISIBLE);

            team.setImageResource(0);
            draw = getResources().getDrawable(R.drawable.ic_people_black_48dp);
            draw = resize(draw, (int) (teamstats.getHeight() * 0.6)+ 1);
            team.setImageDrawable(draw);

        }


    }

    private Drawable resize(Drawable image, int size) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                size, size, false);
        return new BitmapDrawable(getResources(), bitmapResized);
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
