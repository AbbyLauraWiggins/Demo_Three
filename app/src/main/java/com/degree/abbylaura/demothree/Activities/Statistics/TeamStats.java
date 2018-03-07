package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 07/03/2018.
 */

public class TeamStats extends Activity {

    ActionBar.Tab overviewTab, gameTab, leaderboardTab;

    Fragment overviewFragment = new TeamStatsTabFragmentOverview();
    Fragment gameFragment = new TeamStatsTabFragmentGame();
    Fragment leaderboardFragment = new TeamStatsTabFragmentLeaderboard();

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team_stats_activity);

        ActionBar actionBar = getActionBar();

        // Set the current navigation mode to tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Add text to tabs
        overviewTab = actionBar.newTab().setText("Overview");
        gameTab = actionBar.newTab().setText("Game");
        leaderboardTab = actionBar.newTab().setText("Leaderboard");

        // Set tab listeners to alert when an action occurs
        overviewTab.setTabListener(new TeamStatsTabListener(overviewFragment));
        gameTab.setTabListener(new TeamStatsTabListener(gameFragment));
        leaderboardTab.setTabListener(new TeamStatsTabListener(leaderboardFragment));

        // Adds tabs to the actionbar
        actionBar.addTab(overviewTab);
        actionBar.addTab(gameTab);
        actionBar.addTab(leaderboardTab);

        setOverviewLayout();

    }

    public void setOverviewLayout(){
        KPIRepo kpiRepo = new KPIRepo();

        ArrayList<ArrayList<String>> leaderboard = kpiRepo.getKPILeaderboard("1");

        TableLayout tl = new TableLayout(this);

        for(int i = 0; i < leaderboard.size(); i++) {
            TableRow tr = new TableRow(this);
            ArrayList<String> list = leaderboard.get(i);

            for(int j = 0; j < 3; j++){
                TextView label = new TextView(this);
                label.setText(" | " + list.get(j));
                System.out.print(label.getText());
                tr.addView(label);
            }

            System.out.println("");

           // tl.addView(tr);
        }

        //LinearLayout tableContainer = findViewById(R.id.tableContainer);

        //TextView tableTitle = null;
        //tableTitle.setText(tableName);
        //tableContainer.addView(tableTitle);
        //tableContainer.addView(tl);
    }


    public void goBack(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }
}
