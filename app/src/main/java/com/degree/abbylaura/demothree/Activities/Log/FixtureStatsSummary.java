package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Spinner;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;

import java.util.HashMap;


/**
 * Created by abbylaura on 16/03/2018.
 */

public class FixtureStatsSummary extends Activity {

    TextView fixtureIdTextView;
    Spinner forwardSpinner, backSpinner, playerSpinner;

    String fixtureID;
    HashMap<Integer, String> playerAssignment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fixture_stats_summary_activity);

        fixtureIdTextView = findViewById(R.id.fixtureIdTextView);
        forwardSpinner = findViewById(R.id.forwardSpinner);
        backSpinner = findViewById(R.id.backSpinner);
        playerSpinner = findViewById(R.id.playerSpinner);

        Intent activityThatCalled = getIntent();
        playerAssignment = (HashMap<Integer, String>) activityThatCalled.getSerializableExtra("PLAYERS");
        fixtureID = activityThatCalled.getStringExtra("FIXTUREID");

        showStatisticsTable();
    }

    private void showStatisticsTable(){

    }
}
