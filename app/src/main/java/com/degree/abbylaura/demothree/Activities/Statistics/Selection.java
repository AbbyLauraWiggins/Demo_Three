package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by abbylaura on 12/03/2018.
 */

public class Selection extends Activity {

    Boolean[] selected;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_fragment);

        selected = new Boolean[17];

        LinearLayout ll = findViewById(R.id.ll);

        CheckBox item1 = new CheckBox(this);
        item1.setText("Successful Tackles");
        item1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[0] = true;
                    }
                }
            }
        });


        CheckBox item2 = new CheckBox(this);
        item2.setText("Unsuccessful Tackles");
        item2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[1] = true;
                    }
                }
            }
        });



        CheckBox item3 = new CheckBox(this);
        item3.setText("Successful Carries");
        item3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[2] = true;
                    }
                }
            }
        });

        CheckBox item4 = new CheckBox(this);
        item4.setText("Unsuccessful Carries");
        item4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[3] = true;
                    }
                }
            }
        });

        CheckBox item5 = new CheckBox(this);
        item5.setText("Successful Passes");
        item5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[4] = true;
                    }
                }
            }
        });

        CheckBox item6 = new CheckBox(this);
        item6.setText("Unsuccessful Passes");
        item6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[5] = true;
                    }
                }
            }
        });


        CheckBox item7 = new CheckBox(this);
        item7.setText("Handling Errors");
        item7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[6] = true;
                    }
                }
            }
        });

        CheckBox item8 = new CheckBox(this);
        item8.setText("Penalties");
        item8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[7] = true;
                    }
                }
            }
        });

        CheckBox item9 = new CheckBox(this);
        item9.setText("Yellow Cards");
        item9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[8] = true;
                    }
                }
            }
        });

        CheckBox item10 = new CheckBox(this);
        item10.setText("Tries Scored");
        item10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[9] = true;
                    }
                }
            }
        });

        CheckBox item11 = new CheckBox(this);
        item11.setText("Turnovers Won");
        item11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[10] = true;
                    }
                }
            }
        });

        CheckBox item12 = new CheckBox(this);
        item12.setText("Successful Line Out Throws");
        item12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[11] = true;
                    }
                }
            }
        });

        CheckBox item13 = new CheckBox(this);
        item13.setText("Unsuccessful Line Out Throws");
        item13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[12] = true;
                    }
                }
            }
        });

        CheckBox item14 = new CheckBox(this);
        item14.setText("Successful Line Out Takes");
        item14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[13] = true;
                    }
                }
            }
        });

        CheckBox item15 = new CheckBox(this);
        item15.setText("Unsuccessful Line Out Takes");
        item15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[14] = true;
                    }
                }
            }
        });

        CheckBox item16 = new CheckBox(this);
        item16.setText("Successful Kicks");
        item16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[15] = true;
                    }
                }
            }
        });

        CheckBox item17 = new CheckBox(this);
        item17.setText("Unsuccessful Kicks");
        item17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isChecked){
                        selected[16] = true;
                    }
                }
            }
        });

        ll.addView(item1);
        ll.addView(item2);
        ll.addView(item3);
        ll.addView(item4);
        ll.addView(item5);
        ll.addView(item6);
        ll.addView(item7);
        ll.addView(item8);
        ll.addView(item9);
        ll.addView(item10);
        ll.addView(item11);
        ll.addView(item12);
        ll.addView(item13);
        ll.addView(item14);
        ll.addView(item15);
        ll.addView(item16);
        ll.addView(item17);


        Button done = new Button(this);

        ll.addView(done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void goBack(){
        Intent goingBack = new Intent();
        goingBack.putExtra("selected", selected);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}
