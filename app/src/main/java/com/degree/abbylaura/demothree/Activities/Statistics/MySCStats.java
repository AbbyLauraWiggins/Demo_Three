package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.res.Resources;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by abbylaura on 09/03/2018.
 */

public class MySCStats extends Activity {

    EditText userInputID;

    LineGraphSeries<DataPoint> graphSeries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_sc_stats_activity);

        userInputID = findViewById(R.id.scSessionID);

        showGraph();


    }

    private void showGraph(){
        GraphView graph = (GraphView) findViewById(R.id.graphView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams = graph.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight/2;
        graph.setLayoutParams(layoutParams);
        graph.setVisibility(View.VISIBLE);


        double y,x;
        x = -5.0;

        graphSeries = new LineGraphSeries<DataPoint>();
        for(int i =0; i<100; i++) {
            x = x + 0.1;
            y = Math.sin(x);
            graphSeries.appendData(new DataPoint(x, y), true, 100);
        }
        graph.addSeries(graphSeries);
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
