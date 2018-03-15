package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team_stats_tab_leaderboard);

        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();
        ArrayList<String> spinnerList = new ArrayList<String>();
        final HashMap<String, String> spinnerItemAndFixtureID = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        for(ArrayList<String> row : fixturesList){ //0=name1, 1=name2, 2=date

            //must be one my MyTeams games
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

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner fixtureSpinner = (Spinner) findViewById(R.id.fixtureSpinner);
        fixtureSpinner.setAdapter(adapter);

        fixtureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(i).toString();
                fixtureID = (spinnerItemAndFixtureID.get(itemSelectedInSpinner)); //returns FixtureID of selected item
                System.out.println(fixtureID + " " + spinnerItemAndFixtureID.get(itemSelectedInSpinner));
                setView();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        setView();
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
}
