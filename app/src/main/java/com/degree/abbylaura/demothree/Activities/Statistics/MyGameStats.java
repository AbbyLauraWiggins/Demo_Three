package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Log.LogActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
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
 *
 GraphView

 Copyright 2018 Abbygayle Wiggins

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

public class MyGameStats extends Activity {

    EditText gameID;
    String[] kpiHeaders, kpiCols;
    Spinner kpiSpinner;
    String chosenKPIforGraph;

    ArrayList<String> strPoints, fixID;
    ArrayList<Date> dates;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    PointsGraphSeries<DataPoint> graphSeries;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_game_stats_activity);

        gameID = findViewById(R.id.gameID);

        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

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
        setBottomBar();
    }

    private void setBottomBar(){

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        android.view.ViewGroup.LayoutParams layoutParams = bbl.getLayoutParams();
        layoutParams.width = screenWidth + 30;
        layoutParams.height = screenHeight/10;
        bbl.setLayoutParams(layoutParams);

        layoutParams = homebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        homebbll.setLayoutParams(layoutParams);

        layoutParams = noticebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        noticebbll.setLayoutParams(layoutParams);

        layoutParams = profilebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        profilebbll.setLayoutParams(layoutParams);

        layoutParams = logbbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        logbbll.setLayoutParams(layoutParams);

        barSize = screenHeight/12;
        barNotice.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.ic_chat_black_48dp);
        draw = barresize(draw);
        barNotice.setImageDrawable(draw);

        barHome.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_home_black_48dp);
        draw = barresize(draw);
        barHome.setImageDrawable(draw);

        barProfile.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.profileiconempty);
        draw = barresize(draw);
        barProfile.setImageDrawable(draw);


        barLog.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.trend_arrow);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

    }

    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        float scaleFactor = width/height;
        int setwidth = (int) (barSize * scaleFactor);
        System.out.println(height + " " + width + " " + setwidth);
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                setwidth, barSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
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
        fixID = new ArrayList<>();

        //Get all the data for the graph

        KPIRepo kpiRepo = new KPIRepo();
        ArrayList<ArrayList<String>> data =
                kpiRepo.getGraphStats(MyClientID.myID, chosenKPIforGraph);

        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

        for(ArrayList al : data){
            strPoints.add(String.valueOf(al.get(0)));
            dates.add(df.parse(String.valueOf(al.get(1))));
            fixID.add(String.valueOf(al.get(2)));

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
/*
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(strPoints.size()); // only 4 because of the space

        graph.getViewport().setMinX(dates.get(0).getTime());
        graph.getViewport().setMaxX(dates.get(dates.size() - 1).getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getGridLabelRenderer().setHumanRounding(false);
*/

        //Add graph to linear layout
        ll.removeAllViews();
        ll.addView(graph);
    }

    private DataPoint[] generateData() {
        int size = strPoints.size();
        DataPoint[] values = new DataPoint[size];

        for(int i =0; i<strPoints.size(); i++) {

            //Date x = dates.get(i);
            Double x = Double.parseDouble(fixID.get(i));//dates.get(i);

            Double y = Double.parseDouble(strPoints.get(i));

            System.out.println("generate data: " + String.valueOf(x) + " : " + String.valueOf(y));
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }


    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onNoticeButtonClick(View view) {
        Intent intent = new Intent(this, NoticeActivity.class);
        startActivity(intent);
    }

    public void onProfileButtonClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onLogButtonClick(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
