package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class TeamStatsLeaderboard extends Activity {
    String fixtureID = "";
    Button overview, game, leaderboard;
    ButtonBarLayout buttonBarLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team_stats_tab_leaderboard);

        System.out.println("leaderboard on create 1");

        overview = findViewById(R.id.teamStatOverviewButton);
        game = findViewById(R.id.teamStatGameButton);
        leaderboard = findViewById(R.id.teamStatLeaderboardButton);
        buttonBarLayout = findViewById(R.id.buttonBarLayout);

        setLayout();

        System.out.println("leaderboard on create 2");

        try {
            System.out.println("leaderboard on create 3");

            setSpinner();
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("CAUGHT PARSE EXCEPTION E");
        }
        setView();
    }

    private void setSpinner() throws ParseException {
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        /*ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();
        ArrayList<String> spinnerList = new ArrayList<String>();
        final HashMap<String, String> spinnerItemAndFixtureID = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for(ArrayList<String> row : fixturesList){ //0=name1, 1=name2, 2=date
            System.out.println("SetSpinner, row: " + row.toString());


            //must be one my MyTeams games
            System.out.println(MyClientID.myTeamID + " < my team id | fixturesList teamID > " + row.get(5));

            if(row.get(4).equals(MyClientID.myTeamID) || row.get(5).equals(MyClientID.myTeamID)){
                //must be a game that has already occured

                try {
                    Date fixtureDate = sdf.parse(row.get(2));
                    if (new Date().after(fixtureDate)) {
                        String item = row.get(0) + " vs " + row.get(1) + ": " + row.get(2);
                        String fixtureID = row.get(3);
                        spinnerList.add(item);
                        spinnerItemAndFixtureID.put(item, fixtureID);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }*/

        System.out.println("leaderboard on create set spinner 1");


        final HashMap<String, ArrayList<String>> spinnerItemAndFixtureID = tfRepo.getQuickSpinnerList(MyClientID.myTeamID);

        System.out.println("leaderboard on create set spinner 2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerItemAndFixtureID.get("list"));

        System.out.println("leaderboard on create set spinner 3");


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner fixtureSpinner = (Spinner) findViewById(R.id.fixtureSpinner);
        fixtureSpinner.setAdapter(adapter);

        fixtureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(i).toString();

                fixtureID = (spinnerItemAndFixtureID.get(itemSelectedInSpinner)).get(0); //returns FixtureID of selected item
                System.out.println(fixtureID + " " + spinnerItemAndFixtureID.get(itemSelectedInSpinner));
                setView();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setView() {
        KPIRepo kpiRepo = new KPIRepo();
        ArrayList<ArrayList<String>> leaderboard;

        if (fixtureID.equals("")) {
            System.out.println("FIXTURE ID: " + fixtureID);
            leaderboard = kpiRepo.getKPISeasonLeaderboard();
        } else {
            System.out.println("FIXTURE ID ELSE: " + fixtureID);
            leaderboard = kpiRepo.getKPILeaderboard(fixtureID);
        }

        TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);
        tl.removeAllViews();

        for (int i = 2; i < leaderboard.size(); i++) { //start at 2 because we dont want Member and fixture ID

            TableRow tr = new TableRow(this);

            ArrayList<String> list = leaderboard.get(i);

            for (int j = 0; j < 3; j++) {
                TextView label = new TextView(this);

                label.setText(" | " + list.get(j));

                tr.addView(label);
            }


            tl.addView(tr);

        }

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
