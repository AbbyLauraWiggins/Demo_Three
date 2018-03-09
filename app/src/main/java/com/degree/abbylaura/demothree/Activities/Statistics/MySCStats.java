package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.SCsessionRepo;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 09/03/2018.
 */

public class MySCStats extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_sc_stats_activity);


    }

    public void onShowSCSession(View view) {
        SCsessionRepo sCsessionRepo = new SCsessionRepo();
        EditText inputSessionID = findViewById(R.id.scSessionID);

        ArrayList<ArrayList<String>> session = sCsessionRepo.getMySCsession(inputSessionID.getText().toString());

        TableLayout tl = (TableLayout) view.findViewById(R.id.tableLayout);

        ArrayList<String> columnNames = session.get(0);
        ArrayList<String> values = session.get(1);

        TableRow trCol = new TableRow(this);
        TableRow trVal = new TableRow(this);

        for (int i = 0; i < values.size(); i++) {

            TextView labelCol = new TextView(this);
            TextView labelVal = new TextView(this);

            labelCol.setText(" | " + columnNames.get(i));
            labelVal.setText(" | " + values.get(i));

            trCol.addView(labelCol);
            trVal.addView(labelVal);
        }

        tl.addView(trCol);
        tl.addView(trVal);
    }
}
