package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;



import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.TeamFixtures;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abbylaura on 07/03/2018.
 */

public class TeamStatsTabFragmentLeaderboard extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_stats_tab_leaderboard, null);

        //TODO for each KPI, find the person with the highest value
        //TODO and create a table of KPI name | Member Name | KPI value
        Spinner fixtureSpinner = (Spinner) view.findViewById(R.id.fixtureSpinner);
        List<String> spinnerItems = new ArrayList<String>();
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        String[][] fixturesList = tfRepo.getTableData();

        for(int i = 0; i < tfRepo.getTableSize(); i++){
           // spinnerItems
        }

        ArrayList<ArrayList<String>> spinnerlist = tfRepo.getSpinnerList();

        Button show = (Button) view.findViewById(R.id.btnShowLeaderboard);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setView(view);
            }
        });



        return view;
    }

    private void setView(View view){
        KPIRepo kpiRepo = new KPIRepo();
        ArrayList<ArrayList<String>> leaderboard;

        EditText fixtureIDet = (EditText) view.findViewById(R.id.fixtureIdEditText);
        String fixtureID = fixtureIDet.getText().toString();

        if(fixtureID.equals(null)){
            System.out.println("FIXTURE ID: " + fixtureID);
            leaderboard = kpiRepo.getKPISeasonLeaderboard();
        }else{
            System.out.println("FIXTURE ID ELSE: " + fixtureID);
            leaderboard = kpiRepo.getKPILeaderboard(fixtureID);
        }

        TableLayout tl = (TableLayout) view.findViewById(R.id.tableLayout);

        for(int i = 2; i < leaderboard.size(); i++) { //start at 2 because we dont want Member and fixture ID
            TableRow tr = new TableRow(getActivity());
            ArrayList<String> list = leaderboard.get(i);

            for(int j = 0; j < 3; j++){
                TextView label = new TextView(getActivity());
                label.setText(" | " + list.get(j));
                //System.out.print(label.getText());
                tr.addView(label);
            }

            //System.out.println("");

            tl.addView(tr);
        }
    }


}
