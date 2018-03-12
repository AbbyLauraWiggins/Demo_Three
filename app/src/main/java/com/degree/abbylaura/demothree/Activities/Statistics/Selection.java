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

import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 12/03/2018.
 */

public class Selection extends Activity implements CompoundButton.OnCheckedChangeListener {

    ArrayList<String> selected;
    //int looper;

    String[] itemsIn;
    ListView lv;
    ArrayList<Choices> choicesArrayList;
    MyListAdapter choiceAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_fragment);

        String [] kpiHeaders = new String[17];
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

        String indicator = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            indicator = extras.getString("indicator");
        }

        if(indicator.equals("KPI")){
            itemsIn = kpiHeaders;
        }

        Button done = findViewById(R.id.doneButton);
        done.setBackgroundColor(Color.WHITE);

        selected = new ArrayList<String>();

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
        Intent goingBack = new Intent();
        goingBack.putStringArrayListExtra("selected", selected);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}


