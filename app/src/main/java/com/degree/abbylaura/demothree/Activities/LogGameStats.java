package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.degree.abbylaura.demothree.Activities.Statistics.Selection;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_game_stats);

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


        for(Button b: buttons){
            android.view.ViewGroup.LayoutParams layoutParams = b.getLayoutParams();
            layoutParams.width = screenWidth/7;
            layoutParams.height = screenWidth/7;
            b.setLayoutParams(layoutParams);
            b.setVisibility(View.VISIBLE);
            setListener(b);
        }



        GridLayout backLine = findViewById(R.id.backLineLayout);
        int paddingTop = screenHeight/16;
        backLine.setPadding(0, paddingTop, 0,0);

        GridLayout subs = findViewById(R.id.firstSubsLayout);
        subs.setPadding(0, paddingTop, 0,0);


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

    public void onDone(View view) {
        for(int i = 1; i < 23; i++){
            System.out.println(String.valueOf(i) + ": " + playerKPIs[i]);

            String strKPI = playerKPIs[i];
            if(!strKPI.equals("")){
                String[] strArrKPI = strKPI.split(",");
                for(int j = 0; i < strArrKPI.length; j++){
                    String KPIcol = hashKPI.get(strArrKPI[j]);
                }
            }
        }

        //TODO for each player, count the strings of each KPI, add to KPI table
    }
}
