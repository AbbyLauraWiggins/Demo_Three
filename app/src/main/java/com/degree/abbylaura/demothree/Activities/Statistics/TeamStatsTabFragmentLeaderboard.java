package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TeamStatsTabFragmentLeaderboard extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_stats_tab_leaderboard, null);

        //TODO for each KPI, find the person with the highest value
        //TODO and create a table of KPI name | Member Name | KPI value




        return view;
    }
}
