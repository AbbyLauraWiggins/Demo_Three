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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Log.LogActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.Database.Schema.Session;
import com.degree.abbylaura.demothree.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by abbylaura on 12/03/2018.
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

public class AnalyseStats extends Activity {

    ArrayList<String> selectedKPIs, selectedKpiCols, selectedSCs, playerNames, memberIDs;
    Switch toggle;
    Boolean switchState;
    String indicator;
    LineGraphSeries<DataPoint> graphSeries;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;
    HashMap<String, String> scExercises;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.analyse_stats_activity);

        selectedKpiCols = new ArrayList<String>();

        toggle = (Switch) findViewById(R.id.toggle);

        switchState = toggle.isChecked(); //ON = KPI, OFF = S&C

        indicator = "SC";

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resetGraph();
                if(isChecked){ //KPI
                    indicator = "KPI";
                }else{
                    indicator = "SC";
                }
                //selectedKPIs = null;
               // selectedSCs = null;
                System.out.println("toggle: " + indicator);
            }
        });


        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        setBottomBar();

        scExercises = new HashMap<>();
        scExercises.put("Deadlifts", "Deadlifts");
        scExercises.put("Deadlift Jumps", "DeadliftJumps");
        scExercises.put("BackSquats", "BackSquat");
        scExercises.put("BackSquat Jumps", "BackSquat");
        scExercises.put("GobletSquat", "GobletSquat");
        scExercises.put("Bench Press", "BenchPress");
        scExercises.put("Military Press", "MilitaryPress");
        scExercises.put("Supine Row", "SupineRow");
        scExercises.put("Chin Ups", "ChinUps");
        scExercises.put("Trunk", "Trunk");
        scExercises.put("RDL", "RDL");
        scExercises.put("Split Squat", "SplitSquat");
        scExercises.put("Four Way Arms", "FourWayArms");


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


    public void resetGraph(){
        //TODO delete graph contents
    }

    public void onIndicatorClick(View view) {
        //System.out.println(indicator);
        //indicator = "KPI"; //testing

        Intent showList = new Intent(this, Selection.class);
        showList.putExtra("indicator", indicator);
        startActivityForResult(showList, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LinearLayout testLayout = findViewById(R.id.testLayout);

        String returnType = data.getStringExtra("indicator");
        System.out.println("return type: " + returnType);

        if(returnType.equals("")){
            playerNames = data.getStringArrayListExtra("selected");
            memberIDs = data.getStringArrayListExtra("memberIDs");
            TextView tv = new TextView(this);

            for(int i = 0; i < playerNames.size(); i++){
                System.out.println("selected players: " + playerNames.get(i) + " " + memberIDs.get(i));
                tv.append(playerNames.get(i) + " " + memberIDs.get(i));

            }

            testLayout.addView(tv);

        }
        else if(returnType.equals("SC")){
            selectedSCs = data.getStringArrayListExtra("selected");

            TextView tvSC = new TextView(this);

            for(int i = 0; i < selectedSCs.size(); i++){
                System.out.println("selected sc: " + selectedSCs.get(i));
                tvSC.append(selectedSCs.get(i));

            }
            testLayout.addView(tvSC);

        }
        else if(returnType.equals("KPI")){
            selectedKPIs = data.getStringArrayListExtra("selected");
            selectedKpiCols = data.getStringArrayListExtra("KPI columns");

            TextView tvKPI = new TextView(this);

            for(int i = 0; i < selectedKPIs.size(); i++){
                System.out.println("selected kpi: " + selectedKPIs.get(i) + " " + selectedKpiCols.get(i));
                tvKPI.append(selectedKPIs.get(i) + " " + selectedKpiCols.get(i));

            }

            testLayout.addView(tvKPI);

        }

    }

    public void onSelectPlayers(View view) {

        Intent showList = new Intent(this, Selection.class);
        showList.putExtra("players", true);
        showList.putExtra("indicator", "");
        startActivityForResult(showList, 1);
    }

    public void onShowGraph(View view) throws ParseException {
        LinearLayout ll = findViewById(R.id.ll);
        ll.removeAllViews();

        GraphView graph = new GraphView(this);//(GraphView) findViewById(R.id.graphView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams = ll.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight/2;
        graph.setLayoutParams(layoutParams);
        graph.setVisibility(View.VISIBLE);

        ArrayList<DataPoint> dps = new ArrayList<>();

        System.out.println("in show graph: " + indicator);
        if(indicator.equals("KPI")){ //KPIS toggle
            System.out.println("in show graph 2: " + selectedKPIs.toString());
            if(selectedKPIs != null){ //KPIs chosen

                System.out.println("in show graph 3: " + memberIDs.toString());
                for(String member : memberIDs){ //for each member

                    System.out.println("in show graph 4: " + selectedKpiCols.toString());
                    for(String kpi : selectedKpiCols){ //for each KPI
                        KPIRepo kpiRepo = new KPIRepo();
                        ArrayList<ArrayList<String>> graphData = kpiRepo.getGraphStats(member, kpi);

                        System.out.println("in show graph 5: " + graphData.toString());

                        //SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

                        for(int i =0; i<graphData.size(); i++) {

                            //Date x = dates.get(i);
                            Double x = Double.parseDouble(graphData.get(i).get(2));
                            Double y = Double.parseDouble(graphData.get(i).get(0));

                            System.out.println("generate data: " + String.valueOf(x) + " : " + String.valueOf(y));
                            DataPoint v = new DataPoint(x, y);
                            dps.add(v);
                        }

                        DataPoint[] values = new DataPoint[dps.size()];
                        for(int i = 0; i < dps.size(); i++){
                            values[i] = dps.get(i);
                        }
                        graph.removeAllSeries();
                        graphSeries = new LineGraphSeries<DataPoint>(values);
                        graph.addSeries(graphSeries);

                        String legend = "";
                        MemberRepo memberRepo = new MemberRepo();
                        ArrayList<String> getname = new ArrayList<>();
                        getname.add(member);

                        legend = memberRepo.getNames(getname).get(0) + ", " + kpi;

                       /* graphSeries.setTitle(graphSeries.getTitle() + legend);
                        graph.getLegendRenderer().setVisible(true);
                        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);*/

                    }
                }
            }
            else {
                Toast.makeText(this, "Please select KPIs to view.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            if(selectedSCs != null){ //SCs chosen
                for(String member : memberIDs){ //for each member

                    for(String sc1 : selectedSCs){ //for each KPI
                        SessionRepo sessionRepo = new SessionRepo();
                        String sc = scExercises.get(sc1);
                        ArrayList<ArrayList<String>> graphData = sessionRepo.getGraphStats(member, sc);


                        for(int i =0; i<graphData.size(); i++) {

                            //Date x = dates.get(i);
                            Double x = Double.parseDouble(graphData.get(i).get(2));
                            Double y = Double.parseDouble(graphData.get(i).get(0));

                            System.out.println("generate data: " + String.valueOf(x) + " : " + String.valueOf(y));
                            DataPoint v = new DataPoint(x, y);
                            dps.add(v);
                        }

                        DataPoint[] values = new DataPoint[dps.size()];
                        for(int i = 0; i < dps.size(); i++){
                            values[i] = dps.get(i);
                        }
                        graph.removeAllSeries();
                        graphSeries = new LineGraphSeries<DataPoint>(values);
                        graph.addSeries(graphSeries);

                        String legend = "";
                        MemberRepo memberRepo = new MemberRepo();
                        ArrayList<String> getname = new ArrayList<>();
                        getname.add(member);

                        /*legend = memberRepo.getNames(getname).get(0) + ", " + sc1;
                        graphSeries.setTitle(graphSeries.getTitle() + legend);
                        graph.getLegendRenderer().setVisible(true);
                        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);*/

                    }
                }
            }
            else {
                Toast.makeText(this, "Please select exercises to view.", Toast.LENGTH_SHORT).show();
            }
        }

        ll.addView(graph);

        memberIDs = new ArrayList<>();
        selectedKPIs = new ArrayList<>();
        selectedSCs = new ArrayList<>();
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
