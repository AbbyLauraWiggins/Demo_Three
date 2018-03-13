package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.degree.abbylaura.demothree.Activities.Statistics.MyStats;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class LogActivity extends Activity {

    Button mystats;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_activity);

        mystats = findViewById(R.id.myStatsAndLogs);

        if(MyClientID.myPermissions != 3){
            mystats.setVisibility(View.VISIBLE);
        }
    }


    public void onBackButton(View view) {
        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }

    public void goMyStats(View view) {
        Intent goToLog = new Intent(this, MyStats.class);
        startActivity(goToLog);
    }

    public void goToSetTrainingSession(View view) {
    }

    public void goLogGameStats(View view) {
        Intent goToLog = new Intent(this, LogGameStats.class);
        startActivity(goToLog);
    }
}
