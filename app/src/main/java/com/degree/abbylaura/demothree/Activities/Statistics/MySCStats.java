package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abbylaura on 09/03/2018.
 */

public class MySCStats extends Activity {

    EditText userInputID;
    Spinner scSpinner;

    int graphNum;

    LineGraphSeries<DataPoint> graphSeries;

    ArrayList<String> strPoints;
    ArrayList<Date> dates;

    String[] scExercises;
    String chosenExerciseForGraph;

    public MySCStats() {
        chosenExerciseForGraph = "";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_sc_stats_activity);

        userInputID = findViewById(R.id.scSessionID);

        scSpinner = findViewById(R.id.scSpinner);

        this.graphNum = 0;


        scExercises = new String[13];
        scExercises[0] = "Deadlifts";
        scExercises[1] = "DeadliftJumps";
        scExercises[2] = "BackSquat";
        scExercises[3] = "BackSquatJumps";
        scExercises[4] = "GobletSquat";
        scExercises[5] = "BenchPress";
        scExercises[6] = "MilitaryPress";
        scExercises[7] = "SupineRow";
        scExercises[8] = "ChinUps";
        scExercises[9] = "Trunk";
        scExercises[10] = "RDL";
        scExercises[11] = "SplitSquat";
        scExercises[12] = "FourWayArms";

        setSpinner();

    }

    private void setSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, scExercises);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scSpinner.setAdapter(adapter);

        scSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosenExerciseForGraph = scSpinner.getSelectedItem().toString();
                try {
                    System.out.println(chosenExerciseForGraph + " show graph");
                    showGraph();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showGraph() throws ParseException {
        LinearLayout ll = findViewById(R.id.graphContainer);
        graphNum++;

        strPoints = new ArrayList<>();
        dates = new ArrayList<>();

        //Get all the data for the graph

        SessionRepo sessionRepo = new SessionRepo();
        ArrayList<ArrayList<String>> data =
                sessionRepo.getGraphStats(MyClientID.myID, chosenExerciseForGraph);

        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

        for(ArrayList al : data){
            strPoints.add(String.valueOf(al.get(0)));
            dates.add(df.parse(String.valueOf(al.get(1))));

            System.out.println("date: " + String.valueOf(al.get(1)) + " value: " + String.valueOf(al.get(0)));
        }


        //Create graph and set size

        GraphView graph = new GraphView(this);//(GraphView) findViewById(R.id.graphView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams = ll.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight/2;
        graph.setLayoutParams(layoutParams);
        graph.setVisibility(View.VISIBLE);


        //Add data to graph

        graph.removeAllSeries();
        graphSeries = new LineGraphSeries<DataPoint>(generateData());
        graph.addSeries(graphSeries);

        //Set date label formatter

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(strPoints.size()); // only 4 because of the space

        graph.getViewport().setMinX(dates.get(0).getTime());
        graph.getViewport().setMaxX(dates.get(dates.size() - 1).getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getGridLabelRenderer().setHumanRounding(false);


        //Add graph to linear layout
        ll.removeAllViews();
        ll.addView(graph);

        //TODO get dates showing correctly on x axis
    }

    private DataPoint[] generateData() {
        int size = strPoints.size();
        DataPoint[] values = new DataPoint[size];

        for(int i =0; i<strPoints.size(); i++) {
            Date x = dates.get(i);
            Double y = Double.parseDouble(strPoints.get(i));

            System.out.println("generate data: " + String.valueOf(x) + " : " + String.valueOf(y));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    public void onShowSCSession(View view) {

        SessionRepo sessionRepo = new SessionRepo();

        if(userInputID.getText().toString() != null){
            sessionRepo.setWhereclause(" WHERE SessionID ='" + userInputID.getText().toString() + "'");
        }

        String[][] sessions = sessionRepo.getTable();

        LinearLayout tableContainer = findViewById(R.id.tableContainer);
        tableContainer.setOrientation(LinearLayout.VERTICAL);


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
