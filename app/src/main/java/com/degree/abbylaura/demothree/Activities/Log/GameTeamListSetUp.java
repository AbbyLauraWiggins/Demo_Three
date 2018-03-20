package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class GameTeamListSetUp extends Activity {

    Button upload, create, createAndLog;
    HashMap<Integer, String> playerAssignment;
    TextView fixtureDate;
    String fixtureID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gam_team_list_setup_activity);

        upload = findViewById(R.id.uploadTeamSheet);
        create = findViewById(R.id.createTeamSheet);
        createAndLog = findViewById(R.id.createAndLog);
        fixtureDate = findViewById(R.id.nextFixtureDate);

        try {
            getFixtureDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        intent.putExtra("FIXTUREID", fixtureID);
        startActivity(intent);
    }

    public void getFixtureDate() throws ParseException {
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date farAway = sdf.parse("01/01/3018");

        for(ArrayList<String> row : fixturesList){ //0=name1, 1=name2, 2=date

            //must be one my MyTeams games
            if(row.get(4).equals(MyClientID.myTeamID) || row.get(5).equals(MyClientID.myTeamID)){
                //must be a game that has NOT occured

                try {
                    Date fixtureDate = sdf.parse(row.get(2));
                    if (new Date().before(fixtureDate)) { //if NOW is before fixtureDate
                        if(fixtureDate.before(farAway)){ //if it closer to now than current closest date
                            farAway = fixtureDate;
                            fixtureID = row.get(0);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }

        String fixtureDateToPass = sdf.format(farAway);
        fixtureDate.setText(fixtureDateToPass);
    }
}
