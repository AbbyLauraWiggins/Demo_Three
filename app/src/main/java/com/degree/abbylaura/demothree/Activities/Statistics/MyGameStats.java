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
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by abbylaura on 11/03/2018.
 */

public class MyGameStats extends Activity {

    EditText gameID;
    String[] kpiHeaders, kpiCols;
    Spinner kpiSpinner;
    String chosenKPIforGraph;

    ArrayList<String> strPoints;
    ArrayList<Date> dates;

    PointsGraphSeries<DataPoint> graphSeries;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_game_stats_activity);

        gameID = findViewById(R.id.gameID);

        kpiHeaders = new String[17];
        kpiHeaders[0] = "Successful Tackles ";
        kpiHeaders[1] = "Unsuccessful Tackles ";
        kpiHeaders[2] = "Successful Carries ";
        kpiHeaders[3] = "Unsuccessful Carries ";
        kpiHeaders[4] = "Successful Passes ";
        kpiHeaders[5] = "Unsuccessful Passes ";
        kpiHeaders[6] = "Handling Errors ";
        kpiHeaders[7] = "Penalties ";
        kpiHeaders[8] = "Yellow Cards ";
        kpiHeaders[9] = "Tries Scored ";
        kpiHeaders[10] = "Turnovers Won ";
        kpiHeaders[11] = "Successful Line Out Throws ";
        kpiHeaders[12] = "Unsuccessful Line Out Throws ";
        kpiHeaders[13] = "Successful Line Out Takes ";
        kpiHeaders[14] = "Unsuccessful Line Out Takes ";
        kpiHeaders[15] = "Successful Kicks ";
        kpiHeaders[16] = "Unsuccessful Kicks ";

        kpiCols = new String[17];
        kpiCols[0] = "sTackles";
        kpiCols[1] = "uTackles";
        kpiCols[2] = "sCarries";
        kpiCols[3] = "uCarries";
        kpiCols[4] = "sPasses";
        kpiCols[5] = "uPasses";
        kpiCols[6] = "HandlingErrors";
        kpiCols[7] = "Penalties";
        kpiCols[8] = "YellowCards";
        kpiCols[9] = "TriesScored";
        kpiCols[10] = "TurnoversWon";
        kpiCols[11] = "sThrowIns";
        kpiCols[12] = "uThrowIns";
        kpiCols[13] = "sLineOutTakes";
        kpiCols[14] = "uLineOutTakes";
        kpiCols[15] = "sKicks";
        kpiCols[16] = "uKicks";


        this.kpiSpinner = findViewById(R.id.gameSpinner);
        setSpinner();
    }

    public void onShowGame(View view) {

        KPIRepo kpiRepo = new KPIRepo();

        //get stats from
        String fixtureID = gameID.getText().toString();
        if(fixtureID == null){
            fixtureID = "1"; //testing
        }

        //kpiRepo.setWhereClause("WHERE MemberID = '" + MyClientID.myID + "' AND FixtureID = '" + fixtureID + "'");

        ArrayList<String> myKpis = kpiRepo.getMyGameKPIs(MyClientID.myID, fixtureID);

        LinearLayout tableContainer = findViewById(R.id.tableContainer);
        tableContainer.removeAllViews();
        tableContainer.setOrientation(LinearLayout.VERTICAL);

        TableLayout tl = new TableLayout(this);

        for(int i = 3; i < myKpis.size(); i++) {
            TableRow row = new TableRow(this);

            TextView label = new TextView(this);
            label.setText(kpiHeaders[i-3] + " | " + myKpis.get(i));
            System.out.print(label.getText());
            row.addView(label);

            tl.addView(row);

        }

        tableContainer.addView(tl);
    }

    private void setSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, kpiHeaders);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kpiSpinner.setAdapter(adapter);

        kpiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //chosenKPIforGraph = kpiSpinner.getSelectedItem().toString();
                chosenKPIforGraph = kpiCols[i];
                try {
                    System.out.println(chosenKPIforGraph + " show graph");
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

        strPoints = new ArrayList<>();
        dates = new ArrayList<>();

        //Get all the data for the graph

        KPIRepo kpiRepo = new KPIRepo();
        ArrayList<ArrayList<String>> data =
                kpiRepo.getGraphStats(MyClientID.myID, chosenKPIforGraph);

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
        graphSeries = new PointsGraphSeries<DataPoint>(generateData());
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
}
