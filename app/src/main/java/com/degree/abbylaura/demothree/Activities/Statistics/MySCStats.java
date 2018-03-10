package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/03/2018.
 */

public class MySCStats extends Activity {

    EditText userInputID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_sc_stats_activity);

        userInputID = findViewById(R.id.scSessionID);

    }

    public void onShowSCSession(View view) {

        SessionRepo sessionRepo = new SessionRepo();

        if(userInputID.getText().toString() != null){
            sessionRepo.setWhereclause(" WHERE SessionID ='" + userInputID.getText().toString() + "'");
        }

        String[][] sessions = sessionRepo.getTable();

        LinearLayout tableContainer = findViewById(R.id.tableContainer);
        tableContainer.setOrientation(LinearLayout.VERTICAL);

        String[] scExercises = new String[13];
        scExercises[0] = "Deadlift";
        scExercises[1] = "Deadlift Jump";
        scExercises[2] = "Back Squat";
        scExercises[3] = "Back Squat Jumps";
        scExercises[4] = "Goblet Squat";
        scExercises[5] = "Bench Press";
        scExercises[6] = "Military Press";
        scExercises[7] = "Supine Row";
        scExercises[8] = "Chin Ups";
        scExercises[9] = "Trunk";
        scExercises[10] = "RDL";
        scExercises[11] = "Split Squat";
        scExercises[12] = "Four Way Arms";

        TableLayout tl = new TableLayout(this);

        for(int i = 2; i < 14; i++){
            TableRow tr = new TableRow(this);
            TextView tv = new TextView(this);
            TextView tvColumn = new TextView(this);
            tv.setText(sessions[i][0]);
            tvColumn.setText(scExercises[i-2] + "  ");
            tr.addView(tvColumn);
            tr.addView(tv);

            tl.addView(tr);
        }

        tableContainer.addView(tl);

    }
}
