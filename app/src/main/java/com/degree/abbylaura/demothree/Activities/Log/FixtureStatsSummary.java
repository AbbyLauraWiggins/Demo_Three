package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.KPIRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.Database.Schema.Member;
import com.degree.abbylaura.demothree.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by abbylaura on 16/03/2018.
 */

public class FixtureStatsSummary extends Activity {

    TextView fixtureIdTextView;
    Spinner forwardSpinner, backSpinner, playerSpinner;
    String forward, back, player;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    String[] fixtureStats;


    String fixtureID;
    HashMap<Integer, String> playerAssignment;
    HashMap<String, Integer> fixtureStatistics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fixture_stats_summary_activity);



        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        setBottomBar();

        fixtureIdTextView = findViewById(R.id.fixtureIdTextView);
        forwardSpinner = findViewById(R.id.forwardSpinner);
        backSpinner = findViewById(R.id.backSpinner);
        playerSpinner = findViewById(R.id.playerSpinner);

        Intent activityThatCalled = getIntent();
        playerAssignment = (HashMap<Integer, String>) activityThatCalled.getSerializableExtra("PLAYERS");
        fixtureID = activityThatCalled.getStringExtra("FIXTUREID");
        System.out.println("fixtureID = " + fixtureID);
        fixtureStatistics = (HashMap<String, Integer>) activityThatCalled.getSerializableExtra("STATISTICS");

        forward = "";
        back = "";
        player = "";

        fixtureStats = new String[19];

        fixtureIdTextView.append(fixtureID);

        //showFixtureStatisticsTable();g
        showFixStatsTextView();
        //showKPIstats();
        fillSpinners();

        /* Allows us to track when an intent with the id TRANSACTION_DONE is executed
         * We can call for an intent to execute something and then tell use when it finishes
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_FIXTURE);
        registerReceiver(clientReceiver, intentFilter);

    }


    private void setBottomBar(){

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        android.view.ViewGroup.LayoutParams layoutParams = bbl.getLayoutParams();
        layoutParams.width = screenWidth + 30;
        layoutParams.height = screenHeight/10;
        bbl.setLayoutParams(layoutParams);

        layoutParams = homebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        homebbll.setLayoutParams(layoutParams);

        layoutParams = noticebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        noticebbll.setLayoutParams(layoutParams);

        layoutParams = profilebbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        profilebbll.setLayoutParams(layoutParams);

        layoutParams = logbbll.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenHeight/10;
        logbbll.setLayoutParams(layoutParams);

        barSize = screenHeight/12;
        barNotice.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.ic_chat_black_48dp);
        draw = barresize(draw);
        barNotice.setImageDrawable(draw);

        barHome.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_home_black_48dp);
        draw = barresize(draw);
        barHome.setImageDrawable(draw);

        barProfile.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.profileiconempty);
        draw = barresize(draw);
        barProfile.setImageDrawable(draw);

        barLog.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.trend_arrow);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

        LinearLayout matchPlayersLL = findViewById(R.id.setMatchPlayersLL);
        layoutParams = matchPlayersLL.getLayoutParams();
        layoutParams.width = screenWidth/2;
        matchPlayersLL.setLayoutParams(layoutParams);
    }

    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        float scaleFactor = width/height;
        int setwidth = (int) (barSize * scaleFactor);
        System.out.println(height + " " + width + " " + setwidth);
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                setwidth, barSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private void showFixStatsTextView(){
        TextView showFixTV = findViewById(R.id.teamStatsOutputTV);


        fixtureStats[1] = MyClientID.myTeamID;
        fixtureStats[2] = fixtureID;



        int fixturePoints = 0;

        String value = "";
        if(fixtureStatistics.containsKey("Try")){
            value = value + "\n Our Tries: " + (String.valueOf(fixtureStatistics.get("Try")));
            int tryPoints = 5 * fixtureStatistics.get("Try");
            fixturePoints = fixturePoints + tryPoints;
            fixtureStats[4] =(String.valueOf(fixtureStatistics.get("Try")));
        }else{
            value = value + "\n Our Tries: 0";
            fixtureStats[4] =("0");
        }
        if(fixtureStatistics.containsKey("Try Against")){
            value = value  + "\n Their Tries: " + (String.valueOf(fixtureStatistics.get("Try Against")));
            fixtureStats[5] =(String.valueOf(fixtureStatistics.get("Try Against")));
        }else{
            value = value + "\n Their Tries: 0";
            fixtureStats[5] =("0");
        }
        if(fixtureStatistics.containsKey("Conversion")){
            value = value  + "\n Our Conversions: " + (String.valueOf(fixtureStatistics.get("Conversion")));
            int conPoints = 2 * fixtureStatistics.get("Conversion");
            fixturePoints = conPoints + fixturePoints;
            fixtureStats[6] =(String.valueOf(fixtureStatistics.get("Conversion")));
        }else{
            value = value + "\n Our Conversions: 0";
            fixtureStats[6] =("0");
        }
        if(fixtureStatistics.containsKey("Conversion Against")){
            value = value  + "\n Their Conversions: " + (String.valueOf(fixtureStatistics.get("Conversion Against")));
            fixtureStats[7] =(String.valueOf(fixtureStatistics.get("Conversion Against")));
        }else{
            value = value + "\n Their Conversions: 0";
            fixtureStats[7] =("0");
        }
        if(fixtureStatistics.containsKey("Scrum Won")){
            value = value  + "\n Scrums Won: " + (String.valueOf(fixtureStatistics.get("Scrum Won")));
            fixtureStats[8] =(String.valueOf(fixtureStatistics.get("Scrum Won")));
        }else{
            value = value + "\n Scrums Won: 0";
            fixtureStats[8] =("0");
        }
        if(fixtureStatistics.containsKey("Scrum Lost")){
            value = value  + "\n Scrums Lost: " + (String.valueOf(fixtureStatistics.get("Scrum Lost")));
            fixtureStats[9] =(String.valueOf(fixtureStatistics.get("Scrum Lost")));
        }else{
            value = value + "\n Scrums Lost: 0";
            fixtureStats[9] =("0");
        }
        if(fixtureStatistics.containsKey("Line Out Won")){
            value = value  + "\n Line Outs Won: " + (String.valueOf(fixtureStatistics.get("Line Out Won")));
            fixtureStats[10] = (String.valueOf(fixtureStatistics.get("Line Out Won")));
        }else{
            value = value + "\n Line Outs Won: 0";
            fixtureStats[10] = ("0");
        }
        if(fixtureStatistics.containsKey("Line Out Lost")){
            value = value  + "\n Line Outs Lost: " + (String.valueOf(fixtureStatistics.get("Line Out Lost")));
            fixtureStats[11] = (String.valueOf(fixtureStatistics.get("Line Out Lost")));
        }else{
            value = value + "\n Line Outs Lost: 0";
            fixtureStats[11] = ("0");
        }
        if(fixtureStatistics.containsKey("Maul Won")){
            value = value  + "\n Mauls Won: " + (String.valueOf(fixtureStatistics.get("Maul Won")));
            fixtureStats[12] = (String.valueOf(fixtureStatistics.get("Maul Won")));
        }else{
            value = value + "\n Mauls Won: 0";
            fixtureStats[12] = ("0");
        }
        if(fixtureStatistics.containsKey("Maul Lost")){
            value = value  + "\n Mauls Lost: " + (String.valueOf(fixtureStatistics.get("Maul Lost")));
            fixtureStats[13] = (String.valueOf(fixtureStatistics.get("Maul Lost")));
        }else{
            value = value + "\n Mauls Lost: 0";
            fixtureStats[13] = ("0");
        }
        if(fixtureStatistics.containsKey("Drop Goal")){
            value = value  + "\n Our Drop Goals: " + (String.valueOf(fixtureStatistics.get("Drop Goal")));
            int dgPoints = 3 * fixtureStatistics.get("Drop Goal");
            fixturePoints = fixturePoints + dgPoints;
            fixtureStats[14] = (String.valueOf(fixtureStatistics.get("Drop Goal")));
        }else{
            value = value + "\n Our Drop Goals: 0";
            fixtureStats[14] = ("0");
        }
        if(fixtureStatistics.containsKey("Their Drop Goal")){
            value = value  + "\n Their Drop Goals: " + (String.valueOf(fixtureStatistics.get("Their Drop Goal")));
        }else{
            value = value + "\n Their Drop Goals: 0";
        }
        if(fixtureStatistics.containsKey("Penalty Kick")){
            value = value  + "\n Our Penalty Kicks: " + (String.valueOf(fixtureStatistics.get("Penalty Kick")));
            int kickPoints = 3 * fixtureStatistics.get("Penalty Kick");
            fixturePoints = fixturePoints + kickPoints;
            fixtureStats[15] = (String.valueOf(fixtureStatistics.get("Penalty Kick")));
        }else{
            value = value + "\n Our Penalty Kicks: 0";
            fixtureStats[15] = ("0");
        }
        if(fixtureStatistics.containsKey("Their Penalty Kick")){
            value = value  + "\n Their Penalty Kicks: " + (String.valueOf(fixtureStatistics.get("Their Penalty Kick")));
        }else{
            value = value + "\n Their Penalty Kicks: 0";
        }


        fixtureStats[3] = String.valueOf(fixturePoints);

        showFixTV.setTextSize(15);
        showFixTV.setText(value);


    }

    private void showKPIstats(){
        //KPIRepo kpiRepo = new KPIRepo();

        KPIRepo kpiRepo = new KPIRepo();
        ArrayList<ArrayList<String>> leaderboard;

        leaderboard = kpiRepo.getKPILeaderboard(fixtureID);


        String[][] table = kpiRepo.getTableData();
        System.out.println("tablelength: " + String.valueOf(table[0].length));

        for(int i = 0; i < table[0].length; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(table[j][i] + " / ");
            }
            System.out.println("");
        }

        TableLayout tl = findViewById(R.id.kpiTL);
        tl.removeAllViews();

        for (int i = 2; i < leaderboard.size(); i++) { //start at 2 because we dont want Member and fixture ID

            TableRow tr = new TableRow(this);

            ArrayList<String> list = leaderboard.get(i);

            for (int j = 0; j < 3; j++) {
                TextView label = new TextView(this);

                label.setText(" | " + list.get(j));

                tr.addView(label);
            }


            tl.addView(tr);

        }

    }

    private void fillSpinners(){
        //Positions 1-8 and 16-19 are forwards positions
        MemberRepo memberRepo = new MemberRepo();
        ArrayList<String> playerIDs = new ArrayList<>();
        for(int i = 1; i < 23; i++){
            playerIDs.add(playerAssignment.get(i));
        }

        ArrayList<String> playerArr = memberRepo.getNames(playerIDs);

        final ArrayList<String> forwards = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            forwards.add(playerArr.get(i));
        }
        for(int i = 15; i < 19; i++){
            forwards.add(playerArr.get(i));
        }

        //Positions 9-15 and 20-22 are back positions
        final ArrayList<String> backs = new ArrayList<>();
        for(int i = 8; i < 15; i++){
            backs.add(playerArr.get(i));
        }
        for(int i = 19; i < 22; i++){
            backs.add(playerArr.get(i));
        }

        //All positions 1 to 22
        final ArrayList<String> players = new ArrayList<>();
        for(int i = 0; i < 22; i++){
            players.add(playerArr.get(i));
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




    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onNoticeButtonClick(View view) {
        Intent intent = new Intent(this, NoticeActivity.class);
        startActivity(intent);
    }

    public void onProfileButtonClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onLogButtonClick(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void onTrackClick(View view) {
        Intent goToLog = new Intent(this, GameTeamListSetUp.class);
        startActivity(goToLog);
    }


    public void saveFixStats(View view) {

        fixtureStats[16] = forward;
        fixtureStats[17] = back;
        fixtureStats[18] = player;

        ArrayList<String> fixtureStatsAL = new ArrayList<>();
        String fixtureStatsStr = "";
        fixtureStatsStr = fixtureStatsStr + (fixtureStats[1]) + "4h4f";
        fixtureStatsStr = fixtureStatsStr + (fixtureStats[2]) + "4h4f";
        fixtureStatsStr = fixtureStatsStr + (fixtureStats[16]) + "4h4f";
        fixtureStatsStr = fixtureStatsStr + (fixtureStats[17]) + "4h4f";
        fixtureStatsStr = fixtureStatsStr + (fixtureStats[18]) + "4h4f";


        for(int i = 3; i <16; i++){
            fixtureStatsStr = fixtureStatsStr + (fixtureStats[i]) + "4h4f";

        }

        fixtureStatsStr = fixtureStatsStr + (fixtureStats[15]);

        fixtureStatsAL.add(fixtureStatsStr);

        Intent intent = new Intent(this, NetworkService.class);

        intent.putExtra("CLASS", fixtureStatsAL);
        intent.putExtra("typeSending", "FIXTURES");
        intent.putExtra("TABLESIZE", "0");
        this.startService(intent);
    }

    /*
     * Is alerted when the IntentService broadcasts TRANSACTION_DONE
     */
    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            //NoticeActivity.this.unregisterReceiver(this);
            System.out.println("onReceive BroadcastReceiver");

            makeToast();
        }

    };

    private void makeToast(){
        Toast.makeText(this, "Fixture statistics saved.", Toast.LENGTH_SHORT).show();
    }
}
