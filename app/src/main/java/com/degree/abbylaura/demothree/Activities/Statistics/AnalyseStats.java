package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;


/**
 * Created by abbylaura on 12/03/2018.
 */

public class AnalyseStats extends Activity {

    ArrayList<String> selectedKPIs;
    ArrayList<String> selectedSCs;
    ArrayList<String> playerNames, memberIDs;
    Switch toggle;
    Boolean switchState;
    String indicator;

    PointsGraphSeries<DataPoint> graphSeries;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.analyse_stats_activity);

        toggle = (Switch) findViewById(R.id.toggle);

        switchState = toggle.isChecked(); //ON = KPI, OFF = S&C

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resetGraph();
                if(switchState){ //KPI
                    indicator = "KPI";
                }else{
                    indicator = "SC";
                }
                //selectedKPIs = null;
               // selectedSCs = null;
                System.out.println("toggle: " + indicator);
            }
        });


    }



    public void resetGraph(){
        //TODO delete graph contents
    }

    public void onIndicatorClick(View view) {
        System.out.println(indicator);
        indicator = "KPI"; //testing

        Intent showList = new Intent(this, Selection.class);
        showList.putExtra("indicator", indicator);
        startActivityForResult(showList, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String returnType = data.getStringExtra("indicator");
        System.out.println("return type: " + returnType);

        if(returnType.equals("PLAYERS")){
            playerNames = data.getStringArrayListExtra("selected");
            memberIDs = data.getStringArrayListExtra("memberIDs");

            for(int i = 0; i < playerNames.size(); i++){
                System.out.println("selected players: " + playerNames.get(i) + " " + memberIDs.get(i));
            }
        }
        else if(returnType.equals("SC")){
            selectedSCs = data.getStringArrayListExtra("selected");

            for(int i = 0; i < selectedSCs.size(); i++){
                System.out.println("selected sc: " + selectedSCs.get(i));
            }
        }
        else if(returnType.equals("KPI")){
            selectedKPIs = data.getStringArrayListExtra("selected");

            for(int i = 0; i < selectedKPIs.size(); i++){
                System.out.println("selected kpi: " + selectedKPIs.get(i));
            }
        }

    }


    public void onSelectPlayers(View view) {
        indicator = "PLAYERS"; //testing

        Intent showList = new Intent(this, Selection.class);
        showList.putExtra("indicator", indicator);
        startActivityForResult(showList, 1);
    }

    public void onShowGraph(View view) {
        LinearLayout ll = findViewById(R.id.ll);
        GraphView graph = new GraphView(this);//(GraphView) findViewById(R.id.graphView);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        android.view.ViewGroup.LayoutParams layoutParams = ll.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight/2;
        graph.setLayoutParams(layoutParams);
        graph.setVisibility(View.VISIBLE);

        ArrayList<DataPoint> dps = new ArrayList<>();

        if(switchState){ //KPIS toggle
            if(selectedKPIs != null){ //KPIs chosen
                //TODO plot graphs of KPIS over time for each person

                for(String member : memberIDs){ //for each member
                    for(String kpi : selectedKPIs){ //for each KPI
                        KPIRepo kpiRepo = new KPIRepo();
                        ArrayList<ArrayList<String>> graphData = kpiRepo.getGraphStats(member, kpi);

                        for(int i =0; i<graphData.size(); i++) {

                            //Date x = dates.get(i);
                            Double x = Double.parseDouble(member);//dates.get(i);
                            Double y = Double.parseDouble(graphData.get(i).get(0));

                            System.out.println("generate data: " + String.valueOf(x) + " : " + String.valueOf(y));
                            DataPoint v = new DataPoint(x, y);
                            dps.add(v);
                        }

                    }
                }
            }
            else {
                Toast.makeText(this, "Please select KPIs to view.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            if(selectedSCs != null){ //SCs chosen

            }
            else {
                Toast.makeText(this, "Please select exercises to view.", Toast.LENGTH_SHORT).show();
            }
        }

        DataPoint[] values = new DataPoint[dps.size()];
        for(int i = 0; i < dps.size(); i++){
            values[i] = dps.get(i);
        }
        graph.removeAllSeries();
        graphSeries = new PointsGraphSeries<DataPoint>(values);
        graph.addSeries(graphSeries);

        ll.addView(graph);
    }
}
