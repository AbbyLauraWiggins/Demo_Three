package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by abbylaura on 16/03/2018.
 */

public class FixtureStatsSummary extends Activity {

    TextView fixtureIdTextView;
    Spinner forwardSpinner, backSpinner, playerSpinner;
    String forward, back, player;

    String fixtureID;
    HashMap<Integer, String> playerAssignment;
    HashMap<String, Integer> fixtureStatistics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fixture_stats_summary_activity);

        fixtureIdTextView = findViewById(R.id.fixtureIdTextView);
        forwardSpinner = findViewById(R.id.forwardSpinner);
        backSpinner = findViewById(R.id.backSpinner);
        playerSpinner = findViewById(R.id.playerSpinner);

        Intent activityThatCalled = getIntent();
        playerAssignment = (HashMap<Integer, String>) activityThatCalled.getSerializableExtra("PLAYERS");
        fixtureID = activityThatCalled.getStringExtra("FIXTUREID");
        fixtureStatistics = (HashMap<String, Integer>) activityThatCalled.getSerializableExtra("STATISTICS");

        forward = "";
        back = "";
        player = "";

        //showFixtureStatisticsTable();g
        showFixStatsTextView();
        showKPIstats();
        fillSpinners();
        updateFixtureTable();

        //TODO make this look pretty and add buttons linking to analysis page
        //TODO add go straight to home page button
    }

    private void showFixStatsTextView(){
        TextView showFixTV = findViewById(R.id.teamStatsOutputTV);

        String value = "";
        if(fixtureStatistics.containsKey("Try")){
            value = value + "\n Our Tries: " + (String.valueOf(fixtureStatistics.get("Try")));
        }else{
            value = value + "\n Our Tries: 0";
        }
        if(fixtureStatistics.containsKey("Try Against")){
            value = value  + "\n Their Tries: " + (String.valueOf(fixtureStatistics.get("Try Against")));
        }else{
            value = value + "\n Their Tries: 0";
        }
        if(fixtureStatistics.containsKey("Conversion")){
            value = value  + "\n Our Conversions: " + (String.valueOf(fixtureStatistics.get("Conversion")));
        }else{
            value = value + "\n Our Conversions: 0";
        }
        if(fixtureStatistics.containsKey("Conversion Against")){
            value = value  + "\n Their Conversions: " + (String.valueOf(fixtureStatistics.get("Conversion Against")));
        }else{
            value = value + "\n Their Conversions: 0";
        }
        if(fixtureStatistics.containsKey("Scrum Won")){
            value = value  + "\n Scrums Won: " + (String.valueOf(fixtureStatistics.get("Scrum Won")));
        }else{
            value = value + "\n Scrums Won: 0";
        }
        if(fixtureStatistics.containsKey("Scrum Lost")){
            value = value  + "\n Scrums Lost: " + (String.valueOf(fixtureStatistics.get("Scrum Lost")));
        }else{
            value = value + "\n Scrums Lost: 0";
        }
        if(fixtureStatistics.containsKey("Line Out Won")){
            value = value  + "\n Line Outs Won: " + (String.valueOf(fixtureStatistics.get("Line Out Won")));
        }else{
            value = value + "\n Line Outs Won: 0";
        }
        if(fixtureStatistics.containsKey("Line Out Lost")){
            value = value  + "\n Line Outs Lost: " + (String.valueOf(fixtureStatistics.get("Line Out Lost")));
        }else{
            value = value + "\n Line Outs Lost: 0";
        }
        if(fixtureStatistics.containsKey("Maul Won")){
            value = value  + "\n Mauls Won: " + (String.valueOf(fixtureStatistics.get("Maul Won")));
        }else{
            value = value + "\n Mauls Won: 0";
        }
        if(fixtureStatistics.containsKey("Maul Lost")){
            value = value  + "\n Mauls Lost: " + (String.valueOf(fixtureStatistics.get("Maul Lost")));
        }else{
            value = value + "\n Mauls Lost: 0";
        }
        if(fixtureStatistics.containsKey("Drop Goal")){
            value = value  + "\n Our Drop Goals: " + (String.valueOf(fixtureStatistics.get("Drop Goal")));
        }else{
            value = value + "\n Our Drop Goals: 0";
        }
        if(fixtureStatistics.containsKey("Their Drop Goal")){
            value = value  + "\n Their Drop Goals: " + (String.valueOf(fixtureStatistics.get("Their Drop Goal")));
        }else{
            value = value + "\n Their Drop Goals: 0";
        }
        if(fixtureStatistics.containsKey("Penalty Kick")){
            value = value  + "\n Our Penalty Kicks: " + (String.valueOf(fixtureStatistics.get("Penalty Kick")));
        }else{
            value = value + "\n Our Penalty Kicks: 0";
        }
        if(fixtureStatistics.containsKey("Their Penalty Kick")){
            value = value  + "\n Their Penalty Kicks: " + (String.valueOf(fixtureStatistics.get("Their Penalty Kick")));
        }else{
            value = value + "\n Their Penalty Kicks: 0";
        }

        showFixTV.setTextSize(15);
        showFixTV.setText(value);

    }

    private void showKPIstats(){
        //KPIRepo kpiRepo = new KPIRepo();

        //call kpi method to:
        //TODO get total for sTackles, uTackles, sCarries, uCarries, sPasses, uPasses, Penalties, Turnovers

    }

    private void fillSpinners(){
        //Positions 1-8 and 16-19 are forwards positions
        final ArrayList<String> forwards = new ArrayList<>();
        for(int i = 1; i < 9; i++){
            forwards.add(playerAssignment.get(i));
        }
        for(int i = 16; i < 20; i++){
            forwards.add(playerAssignment.get(i));
        }

        //Positions 9-15 and 20-22 are back positions
        final ArrayList<String> backs = new ArrayList<>();
        for(int i = 9; i < 16; i++){
            backs.add(playerAssignment.get(i));
        }
        for(int i = 20; i < 23; i++){
            backs.add(playerAssignment.get(i));
        }

        //All positions 1 to 22
        final ArrayList<String> players = new ArrayList<>();
        for(int i = 1; i < 23; i++){
            players.add(playerAssignment.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, forwards);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        forwardSpinner.setAdapter(adapter);

        forwardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                forward = forwards.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, backs);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backSpinner.setAdapter(adapter2);

        backSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                back = backs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, players);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerSpinner.setAdapter(adapter3);

        playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                player = players.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateFixtureTable(){
        FixtureRepo fixtureRepo = new FixtureRepo();
        Fixture fixtureTable = new Fixture();

        //TODO update database with this data


    }



}
