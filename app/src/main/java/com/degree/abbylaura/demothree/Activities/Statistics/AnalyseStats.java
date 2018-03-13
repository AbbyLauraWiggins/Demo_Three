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
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by abbylaura on 12/03/2018.
 */

public class AnalyseStats extends Activity {

    ArrayList<String> selectedKPIs, selectedKpiCols, selectedSCs, playerNames, memberIDs;
    Switch toggle;
    Boolean switchState;
    String indicator;
    PointsGraphSeries<DataPoint> graphSeries;

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
                //TODO plot graphs of KPIS over time for each person


                System.out.println("in show graph 3: " + memberIDs.toString());
                for(String member : memberIDs){ //for each member

                    System.out.println("in show graph 4: " + selectedKpiCols.toString());
                    for(String kpi : selectedKpiCols){ //for each KPI
                        KPIRepo kpiRepo = new KPIRepo();
                        ArrayList<ArrayList<String>> graphData = kpiRepo.getGraphStats(member, kpi);

                        System.out.println("in show graph 5: " + graphData.toString());

                        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

                        for(int i =0; i<graphData.size(); i++) {

                            //Date x = dates.get(i);
                            Date x = df.parse(graphData.get(i).get(1));
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

        ll.removeAllViews();
        ll.addView(graph);
    }
}
