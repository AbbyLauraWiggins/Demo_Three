package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class TeamStatsOverview extends Activity{
    Button overview, game, leaderboard;
    ButtonBarLayout buttonBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team_stats_tab_overview);

        overview = findViewById(R.id.teamStatOverviewButton);
        game = findViewById(R.id.teamStatGameButton);
        leaderboard = findViewById(R.id.teamStatLeaderboardButton);
        buttonBarLayout = findViewById(R.id.buttonBarLayout);

        setLayout();
    }

    private void setLayout(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        android.view.ViewGroup.LayoutParams layoutParams1 = overview.getLayoutParams();
        layoutParams1.width = screenWidth/3;
        overview.setLayoutParams(layoutParams1);

        android.view.ViewGroup.LayoutParams layoutParams2 = game.getLayoutParams();
        layoutParams2.width = screenWidth/3;
        overview.setLayoutParams(layoutParams2);

        android.view.ViewGroup.LayoutParams layoutParams3 = leaderboard.getLayoutParams();
        layoutParams3.width = screenWidth/3;
        overview.setLayoutParams(layoutParams3);

        overview.setBackgroundColor(Color.LTGRAY);
        game.setBackgroundColor(Color.LTGRAY);
        leaderboard.setBackgroundColor(Color.LTGRAY);
        buttonBarLayout.setBackgroundColor(Color.LTGRAY);

    }


    public void goToOverviewTS(View view) {
        Intent intent = new Intent(this, TeamStatsOverview.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void goToGameTS(View view) {
        Intent intent = new Intent(this, TeamStatsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void goToLeaderboardTS(View view) {
        Intent intent = new Intent(this, TeamStatsLeaderboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
