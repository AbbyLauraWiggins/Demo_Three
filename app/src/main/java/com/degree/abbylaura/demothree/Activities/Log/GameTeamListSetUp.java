package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class GameTeamListSetUp extends Activity {

    Button upload, create, createAndLog;
    HashMap<Integer, String> playerAssignment;
    TextView fixtureDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gam_team_list_setup_activity);

        upload = findViewById(R.id.uploadTeamSheet);
        create = findViewById(R.id.createTeamSheet);
        createAndLog = findViewById(R.id.createAndLog);
        fixtureDate = findViewById(R.id.nextFixtureDate);

        playerAssignment = new HashMap<>();
    }

    public void onCreateClick(View view) {
        upload.setVisibility(View.INVISIBLE);

        LinearLayout ll = findViewById(R.id.ll);
        ll.removeAllViews();

        TableLayout tl = new TableLayout(this);

        for (int i = 1; i < 23; i++) {
            TableRow tr = new TableRow(this);
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(i));
            tv.setTextSize(20);

            tr.addView(tv);

            Spinner memberSpinner = new Spinner(this);
            setSpinnerContents(memberSpinner, i);

            tr.addView(memberSpinner);

            tl.addView(tr);

        }

        ll.addView(tl);

        createAndLog.setVisibility(View.VISIBLE);

    }

    private void setSpinnerContents(Spinner memberSpinner, final int pos){
        MemberRepo memberRepo = new MemberRepo();
        String[][] memberTable = memberRepo.getMembers();

        ArrayList<String> memberList = new ArrayList<>();
        HashMap<String, String> memberHash = new HashMap<>();

        for(int i = 0; i < memberTable[0].length; i++){
            memberList.add(memberTable[1][i]);
            memberHash.put(memberTable[1][i], memberTable[0][i]); //HashMap<MemberName, MemberId>
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, memberList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberSpinner.setAdapter(adapter);

        memberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                playerAssignment.put(pos, adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onCreateAndLogClick(View view) {
        Intent intent = new Intent(getApplicationContext(), LogGameStats.class);
        intent.putExtra("PLAYERS", playerAssignment);
        startActivity(intent);
    }

    public void getFixtureDate(){
        
    }
}
