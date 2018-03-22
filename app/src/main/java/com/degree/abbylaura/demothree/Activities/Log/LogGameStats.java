package com.degree.abbylaura.demothree.Activities.Log;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Schema.KPI;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abbylaura on 13/03/2018.
 */

public class LogGameStats extends Activity {

    int pressedButton;
    String[] playerKPIs;
    HashMap<String, String> hashKPI;
    HashMap<Integer, String> playerAssignment;
    HashMap<String, Integer> fixtureStatistics;
    String fixtureID;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_game_stats);

        Intent activityThatCalled = getIntent();
        playerAssignment = (HashMap<Integer, String>) activityThatCalled.getSerializableExtra("PLAYERS");
        fixtureID = activityThatCalled.getStringExtra("FIXTUREID");

        fixtureStatistics = new HashMap<>();

        setUpButtons();



        playerKPIs = new String[23];//index of id = button number = player number
        for(int i = 0; i < 23; i++){
            playerKPIs[i] = "";
        }

        hashKPI = new HashMap<>();
        hashKPI.put("Tackle", "sTackles");
        hashKPI.put("Carry", "sCarries");
        hashKPI.put("Successful Pass", "sPasses");
        hashKPI.put("Try Scored", "TriesScored");
        hashKPI.put( "Successful Throw In", "sThrowIns");
        hashKPI.put("Successful Line Out Take", "sLineOutTakes");
        hashKPI.put("Successful Kick", "sKicks");
        hashKPI.put("Missed Tackle", "uTackles");
        hashKPI.put("Unsuccessful Carry", "uCarries");
        hashKPI.put("Unsuccesful Pass", "uPasses");
        hashKPI.put("Turnover Won", "TurnoversWon");
        hashKPI.put("Handling Error", "HandlingErrors");
        hashKPI.put("Penalty", "Penalties");
        hashKPI.put("Yellow Card", "YellowCards");
        hashKPI.put("Unsuccessful Throw In", "uThrowIns");
        hashKPI.put("Unsuccessful Line Out Take", "uLineOutTakes");
        hashKPI.put("Unsuccessful Kick", "uKicks");
    }



    private void setUpButtons(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        ArrayList<Button> buttons = new ArrayList<Button>();

        Button button1 = findViewById(R.id.button1);
        buttons.add(button1);
        Button button2 = findViewById(R.id.button2);
        buttons.add(button2);
        Button button3 = findViewById(R.id.button3);
        buttons.add(button3);
        Button button4 = findViewById(R.id.button4);
        buttons.add(button4);
        Button button5 = findViewById(R.id.button5);
        buttons.add(button5);
        Button button6 = findViewById(R.id.button6);
        buttons.add(button6);
        Button button7 = findViewById(R.id.button7);
        buttons.add(button7);
        Button button8 = findViewById(R.id.button8);
        buttons.add(button8);
        Button button9 = findViewById(R.id.button9);
        buttons.add(button9);
        Button button10 = findViewById(R.id.button10);
        buttons.add(button10);
        Button button11 = findViewById(R.id.button11);
        buttons.add(button11);
        Button button12 = findViewById(R.id.button12);
        buttons.add(button12);
        Button button13 = findViewById(R.id.button13);
        buttons.add(button13);
        Button button14 = findViewById(R.id.button14);
        buttons.add(button14);
        Button button15 = findViewById(R.id.button15);
        buttons.add(button15);
        Button button16 = findViewById(R.id.button16);
        buttons.add(button16);
        Button button17 = findViewById(R.id.button17);
        buttons.add(button17);
        Button button18 = findViewById(R.id.button18);
        buttons.add(button18);
        Button button19 = findViewById(R.id.button19);
        buttons.add(button19);
        Button button20 = findViewById(R.id.button20);
        buttons.add(button20);
        Button button21 = findViewById(R.id.button21);
        buttons.add(button21);
        Button button22 = findViewById(R.id.button22);
        buttons.add(button22);

        ArrayList<Button> bottomButtons = new ArrayList<Button>();

        Button buttonTRY = findViewById(R.id.buttonTRY);
        bottomButtons.add(buttonTRY);
        Button buttonOTRY = findViewById(R.id.buttonOTRY);
        bottomButtons.add(buttonOTRY);
        Button buttonCON = findViewById(R.id.buttonCON);
        bottomButtons.add(buttonCON);
        Button buttonOCON = findViewById(R.id.buttonOCON);
        bottomButtons.add(buttonOCON);
        Button buttonSCRUM = findViewById(R.id.buttonSCRUM);
        bottomButtons.add(buttonSCRUM);
        Button buttonOSCRUM = findViewById(R.id.buttonOSCRUM);
        bottomButtons.add(buttonOSCRUM);
        Button buttonLO = findViewById(R.id.buttonLO);
        bottomButtons.add(buttonLO);
        Button buttonOLO = findViewById(R.id.buttonOLO);
        bottomButtons.add(buttonOLO);
        Button buttonMAUL = findViewById(R.id.buttonMAUL);
        bottomButtons.add(buttonMAUL);
        Button buttonOMAUL = findViewById(R.id.buttonOMAUL);
        bottomButtons.add(buttonOMAUL);
        Button buttonDG = findViewById(R.id.buttonDG);
        bottomButtons.add(buttonDG);
        Button buttonODG = findViewById(R.id.buttonODG);
        bottomButtons.add(buttonODG);
        Button buttonPK = findViewById(R.id.buttonPK);
        bottomButtons.add(buttonPK);
        Button buttonOPK = findViewById(R.id.buttonOPK);
        bottomButtons.add(buttonOPK);

        for(Button b: buttons){
            android.view.ViewGroup.LayoutParams layoutParams = b.getLayoutParams();
            layoutParams.width = screenWidth/7;
            layoutParams.height = screenWidth/10;
            b.setLayoutParams(layoutParams);
            b.setVisibility(View.VISIBLE);

            setListener(b);
        }

        for(Button b: bottomButtons){
            android.view.ViewGroup.LayoutParams layoutParams = b.getLayoutParams();
            layoutParams.width = screenWidth/4;
            layoutParams.height = screenWidth/7;
            b.setLayoutParams(layoutParams);
            b.setVisibility(View.VISIBLE);
            setBottomListener(b);
        }

        Button done = findViewById(R.id.doneButton);
        android.view.ViewGroup.LayoutParams layoutParams = done.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenWidth/7;
        done.setLayoutParams(layoutParams);
        done.setVisibility(View.VISIBLE);


        /*GridLayout backLine = findViewById(R.id.backLineLayout);
        int paddingTop = screenHeight/20;
        backLine.setPadding(0, paddingTop, 0,0);

        GridLayout subs = findViewById(R.id.firstSubsLayout);
        subs.setPadding(0, paddingTop, 0,0);*/

    }

    private void setBottomListener(final Button b){
        b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                String stat = b.getText().toString();

                if(fixtureStatistics.containsKey(stat)){//already contains this stat
                    int count = fixtureStatistics.get(stat);
                    fixtureStatistics.put(stat, count + 1);
                }else{
                    fixtureStatistics.put(stat, 1);
                }

                Toast.makeText(LogGameStats.this, stat + " added.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListener(final Button b){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectKPI.class);
                pressedButton = Integer.parseInt(b.getText().toString());
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println(">>>>>>>>>>>>>>>on intent returned");

        String kpi = data.getStringExtra("KPI");

        String contains = playerKPIs[pressedButton] + kpi + ",";

        System.out.println(String.valueOf(pressedButton) + " " + contains);

        playerKPIs[pressedButton] = contains;
        pressedButton = 0;
    }

    @SuppressLint("NewApi")
    public void onDone(View view) {
        for(int i = 1; i < 23; i++){
            System.out.println(String.valueOf(i) + ": " + playerKPIs[i]);

            String strKPI = playerKPIs[i];
            HashMap<String, Integer> countHash = new HashMap<>();
            if(!strKPI.equals("")){
                String[] strArrKPI = strKPI.split(",");

                //for all string KPIs recorded
                for(int j = 0; j < strArrKPI.length; j++){

                    //get the table column name of that KPI
                    String kPIcol = hashKPI.get(strArrKPI[j]);

                    //add to the counter of times that KPI has been seen for this user
                    if(countHash.containsKey(kPIcol)){
                        int count = countHash.get(kPIcol);
                        countHash.put(kPIcol, count + 1);
                    }else{
                        countHash.put(kPIcol, 1);
                    }
                }
            }

            addToDatabase(countHash, i);
        }

        Intent intent = new Intent(this, FixtureStatsSummary.class);
        intent.putExtra("FIXTUREID", fixtureID);
        intent.putExtra("PLAYERS", playerAssignment);
        intent.putExtra("STATISTICS", fixtureStatistics);
        startActivity(intent);

    }

    private void addToDatabase(HashMap<String, Integer> countHash, int playerNumber){

        String playerID = playerAssignment.get(playerNumber);

        KPI kpi = new KPI();
        kpi.setMemberID(playerID);
        kpi.setFixtureID(fixtureID);

        if(countHash.containsKey("sTackles")){
            kpi.setsTackles(String.valueOf(countHash.get("sTackles")));
        }else{
            kpi.setsTackles("0");
        }
        if(countHash.containsKey("uTackles")){
            kpi.setuTackles(String.valueOf(countHash.get("uTackles")));
        }else{
            kpi.setuTackles("0");
        }
        if(countHash.containsKey("sCarries")){
            kpi.setsCarries(String.valueOf(countHash.get("sCarries")));
        }else{
            kpi.setsCarries("0");
        }
        if(countHash.containsKey("uCarries")){
            kpi.setuCarries(String.valueOf(countHash.get("uCarries")));
        }else{
            kpi.setuCarries("0");
        }
        if(countHash.containsKey("sPasses")){
            kpi.setsPasses(String.valueOf(countHash.get("sPasses")));
        }else{
            kpi.setsPasses("0");
        }
        if(countHash.containsKey("uPasses")){
            kpi.setuPasses(String.valueOf(countHash.get("uPasses")));
        }else{
            kpi.setuPasses("0");
        }
        if(countHash.containsKey("HandlingErrors")){
            kpi.setHandlingErrors(String.valueOf(countHash.get("HandlingErrors")));
        }else{
            kpi.setHandlingErrors("0");
        }
        if(countHash.containsKey("Penalties")){
            kpi.setPenalties(String.valueOf(countHash.get("Penalties")));
        }else{
            kpi.setPenalties("0");
        }
        if(countHash.containsKey("YellowCards")){
            kpi.setYellowCards(String.valueOf(countHash.get("YellowCards")));
        }else{
            kpi.setYellowCards("0");
        }
        if(countHash.containsKey("TriesScored")){
            kpi.setTriesScored(String.valueOf(countHash.get("TriesScored")));
        }else{
            kpi.setTriesScored("0");
        }
        if(countHash.containsKey("TurnoversWon")){
            kpi.setTurnoversWon(String.valueOf(countHash.get("TurnoversWon")));
        }else{
            kpi.setTurnoversWon("0");
        }
        if(countHash.containsKey("sThrowIns")){
            kpi.setsThrowIns(String.valueOf(countHash.get("sThrowIns")));
        }else{
            kpi.setsThrowIns("0");
        }
        if(countHash.containsKey("uThrowIns")){
            kpi.setuThrowIns(String.valueOf(countHash.get("uThrowIns")));
        }else{
            kpi.setsTackles("0");
        }
        if(countHash.containsKey("sLineOutTakes")){
            kpi.setsLineOutTakes(String.valueOf(countHash.get("sLineOutTakes")));
        }else{
            kpi.setsLineOutTakes("0");
        }
        if(countHash.containsKey("uLineOutTakes")){
            kpi.setuLineOutTakes(String.valueOf(countHash.get("uLineOutTakes")));
        }else{
            kpi.setuLineOutTakes("0");
        }
        if(countHash.containsKey("sKicks")){
            kpi.setsKicks(String.valueOf(countHash.get("sKicks")));
        }else{
            kpi.setsKicks("0");
        }
        if(countHash.containsKey("uKicks")){
            kpi.setuKicks(String.valueOf(countHash.get("uKicks")));
        }else{
            kpi.setuKicks("0");
        }
        KPIRepo kpiRepo = new KPIRepo();
        kpiRepo.insert(kpi);
    }

}
