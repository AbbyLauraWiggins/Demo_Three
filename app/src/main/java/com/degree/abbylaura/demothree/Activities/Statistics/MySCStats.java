package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
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

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.SessionRepo;
import com.degree.abbylaura.demothree.Database.Repo.StrengthAndConditioningRepo;
import com.degree.abbylaura.demothree.Database.Schema.StrengthAndConditioning;
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

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    LineGraphSeries<DataPoint> graphSeries;

    ArrayList<String> strPoints;
    ArrayList<Date> dates;
    ArrayList<String> sessionID;

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

        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);


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
        draw = getResources().getDrawable(R.drawable.ic_chat_black_48dp);
        draw = barresize(draw);
        barHome.setImageDrawable(draw);

        barProfile.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_person_black_48dp);
        draw = barresize(draw);
        barProfile.setImageDrawable(draw);

        barLog.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_note_add_black_48dp);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

    }

    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                barSize, barSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
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
        sessionID = new ArrayList<>();


        SessionRepo sessionRepo = new SessionRepo();

        StrengthAndConditioningRepo scRepo = new StrengthAndConditioningRepo();
        String[][] table = scRepo.getTableData();
        System.out.println("sc tablelength: " + String.valueOf(table[0].length));

        for(int i = 0; i < table[0].length; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(table[j][i] + " / ");
            }
            System.out.println("");
        }


        ArrayList<ArrayList<String>> data =
                sessionRepo.getGraphStats(MyClientID.myID, chosenExerciseForGraph);

        //SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

        for(ArrayList al : data){
            strPoints.add(String.valueOf(al.get(0)));
            //dates.add(df.parse(String.valueOf(al.get(1))));
            sessionID.add(String.valueOf(al.get(2)));
            System.out.println("id: " + String.valueOf(al.get(2)) + " value: " + String.valueOf(al.get(0)));
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

        /*graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(strPoints.size()); // only 4 because of the space

        graph.getViewport().setMinX(dates.get(0).getTime());
        graph.getViewport().setMaxX(dates.get(dates.size() - 1).getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getGridLabelRenderer().setHumanRounding(false);
        */

        //Add graph to linear layout
        ll.removeAllViews();
        ll.addView(graph);

        //TODO get dates showing correctly on x axis
    }

    private DataPoint[] generateData() {
        int size = strPoints.size();
        DataPoint[] values = new DataPoint[size];

        for(int i =0; i<strPoints.size(); i++) {
            Double x = Double.parseDouble(sessionID.get(i));//dates.get(i);
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
