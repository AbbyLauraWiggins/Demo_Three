package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.Switch;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;


/**
 * Created by abbylaura on 12/03/2018.
 */

public class AnalyseStats extends Activity {

    ArrayList<String> selectedIndicators;

    Switch toggle;
    Boolean switchState;

    String indicator;

    String[] kpiHeaders;
    private PopupWindow pw;
    private boolean expanded; 		//to  store information whether the selected values are displayed completely or in shortened representatn
    public static boolean[] checkSelected;	// store select/unselect information about the values in the list


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
                setSpinner();
            }
        });

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

        //initialize();

    }



    public void resetGraph(){
        //TODO delete graph contents
    }

    public void setSpinner(){
        if(switchState){
            //TODO Set spinner up with KPI options
        }else{
            //TODO set spinner up with S&C options
        }
    }


    public void onIndicatorClick(View view) {
        indicator = "KPI"; //testing

        Intent showList = new Intent(this, Selection.class);
        showList.putExtra("indicator", indicator);
        startActivityForResult(showList, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        selectedIndicators = data.getStringArrayListExtra("selected");



        for(int i = 0; i < selectedIndicators.size(); i++){
            System.out.println("selected ind: " + selectedIndicators.get(i));
        }
    }


}
