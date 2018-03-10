package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

import com.degree.abbylaura.demothree.R;

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

       /* ArrayList<String> columnNames = session.get(0);
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
        tl.addView(trVal);*/
    }
}
