package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;

/**
 * Created by abbylaura on 12/03/2018.
 */

public class AnalyseStats extends Activity {

    Switch toggle;
    Boolean switchState;

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
        Intent showList = new Intent(this, Selection.class);
        startActivityForResult(showList, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        boolean[] selected = data.getBooleanArrayExtra("selected");


    }

    public void onTestClick(View view) {
        Intent showList = new Intent(this, Selection.class);
        startActivityForResult(showList, 1);
    }
}
