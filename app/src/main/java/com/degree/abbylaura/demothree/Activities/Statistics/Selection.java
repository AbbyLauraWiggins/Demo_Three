package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 12/03/2018.
 */

public class Selection extends Activity implements CompoundButton.OnCheckedChangeListener {

    ArrayList<String> selected, memberIDs, kpiColumn;
    //int looper;
    String indicator;
    String[] itemsIn, kpiCols;
    String[][] members;
    ListView lv;
    ArrayList<Choices> choicesArrayList;
    MyListAdapter choiceAdapter;
    boolean players;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_fragment);

        String[] kpiHeaders = new String[17];
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

        kpiCols = new String[18];
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

        String[] scHeaders = new String[13];
        scHeaders[0] = "Deadlifts";
        scHeaders[1] = "Deadlift Jumps";
        scHeaders[2] = "BackSquats";
        scHeaders[3] = "BackSquat Jumps";
        scHeaders[4] = "GobletSquat";
        scHeaders[5] = "Bench Press";
        scHeaders[6] = "Military Press";
        scHeaders[7] = "Supine Row";
        scHeaders[8] = "Chin Ups";
        scHeaders[9] = "Trunk";
        scHeaders[10] = "RDL";
        scHeaders[11] = "Split Squat";
        scHeaders[12] = "Four Way Arms";

        indicator = "";
        players = false;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            indicator = extras.getString("indicator");
            players = extras.getBoolean("players");
        }

        if(indicator.equals("KPI")){
            itemsIn = kpiHeaders;
        }else if(indicator.equals("SC")){
            itemsIn = scHeaders;
        }else if(players){
            MemberRepo memberRepo = new MemberRepo();
            members = memberRepo.getMembers();

            itemsIn = new String[members[0].length];
            for(int i = 0; i < members.length; i++){
                if(members[1][i] != null){
                    itemsIn[i] = members[1][i];
                }

            }
        }

        Button done = findViewById(R.id.doneButton);
        done.setBackgroundColor(Color.WHITE);

        selected = new ArrayList<String>();
        memberIDs = new ArrayList<String>();
        kpiColumn = new ArrayList<String>();

        lv = (ListView) findViewById(R.id.listview);
        displayChoiceList();




    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            Choices choice = choicesArrayList.get(pos);
            choice.setSelected(isChecked);

            selected.add(choice.getName());

            if(players){
                memberIDs.add(members[0][pos]);

            }else if(indicator.equals("KPI")){
                kpiColumn.add(kpiCols[pos]);
            }


            Toast.makeText(this, "Clicked on: " + choice.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    private void displayChoiceList(){
        choicesArrayList = new ArrayList<>();


        for(int i = 0; i < itemsIn.length; i++){
            choicesArrayList.add(new Choices(itemsIn[i]));
            System.out.println("adding to choicesArrayList: " + itemsIn[i]);
        }

        choiceAdapter = new MyListAdapter(choicesArrayList, this);
        ListView listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(choiceAdapter);
    }


    public void onDone(View view) {
        players=false;
        Intent goingBack = new Intent();
        goingBack.putExtra("indicator", indicator);
        goingBack.putStringArrayListExtra("selected", selected);
        goingBack.putStringArrayListExtra("memberIDs", memberIDs);
        goingBack.putStringArrayListExtra("KPI columns", kpiColumn);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}


