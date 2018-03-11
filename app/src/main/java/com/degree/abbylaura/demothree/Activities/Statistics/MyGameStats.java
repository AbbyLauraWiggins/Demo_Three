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

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 11/03/2018.
 */

public class MyGameStats extends Activity {

    EditText gameID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_game_stats_activity);

        gameID = findViewById(R.id.gameID);
    }

    public void onShowGame(View view) {

        KPIRepo kpiRepo = new KPIRepo();

        //get stats from
        String fixtureID = gameID.getText().toString();
        if(fixtureID == null){
            fixtureID = "1"; //testing
        }

        kpiRepo.setWhereClause("WHERE MemberID = '" + MyClientID.myID + "' AND FixtureID = '" + fixtureID + "'");

        String[][] myKpis = kpiRepo.getTableData();

        LinearLayout tableContainer = findViewById(R.id.tableContainer);
        tableContainer.setOrientation(LinearLayout.VERTICAL);

        TableLayout tl = new TableLayout(this);

        for(int row = 0; row < myKpis[0].length; row++) {
            TableRow tr = new TableRow(this);

            for(int col = 0; col < 20; col++){
                TextView label = new TextView(this);
                label.setText(" | " + myKpis[col][row]);
                System.out.print(label.getText());
                tr.addView(label);
            }

            System.out.println("");

            tl.addView(tr);
        }

        tableContainer.addView(tl);
    }
}
