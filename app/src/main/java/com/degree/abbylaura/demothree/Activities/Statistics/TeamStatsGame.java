package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Log.LogActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FixtureRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.Database.Schema.Fixture;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class TeamStatsGame extends Activity {

    TextView myScore, theirScore, forward, back, player;
    Spinner gamespinner;
    Button overview, game, leaderboard;
    ButtonBarLayout buttonBarLayout;



    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;


    String fixtureID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team_stats_tab_game);

        myScore = findViewById(R.id.myScore);
        theirScore = findViewById(R.id.theirScore);
        forward = findViewById(R.id.forwardTextView);
        back = findViewById(R.id.backTextView);
        player = findViewById(R.id.playerTextView);
        gamespinner = findViewById(R.id.gameSpinner);

        fixtureID = "";

        overview = findViewById(R.id.teamStatOverviewButton);
        game = findViewById(R.id.teamStatGameButton);
        leaderboard = findViewById(R.id.teamStatLeaderboardButton);
        buttonBarLayout = findViewById(R.id.buttonBarLayout);

        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.bottomBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        setBottomBar();


        setLayout();

        setSpinner();

    }


    private void setSpinner(){
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();
        ArrayList<String> spinnerList = new ArrayList<String>();
        final HashMap<String, String> spinnerItemAndFixtureID = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for(ArrayList<String> row : fixturesList){ //0=name1, 1=name2, 2=date

            //must be one my MyTeams games
            if(row.get(4).equals(MyClientID.myTeamID) || row.get(5).equals(MyClientID.myTeamID)){
                //must be a game that has already occured

                try {
                    Date fixtureDate = sdf.parse(row.get(2));
                    if (new Date().after(fixtureDate)) {
                        String item = row.get(0) + " vs " + row.get(1) + ": " + row.get(2);
                        String fixtureID = row.get(3);
                        spinnerList.add(item);
                        spinnerItemAndFixtureID.put(item, fixtureID);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gamespinner.setAdapter(adapter);

        gamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(i).toString();
                fixtureID = (spinnerItemAndFixtureID.get(itemSelectedInSpinner)); //returns FixtureID of selected item
                System.out.println(fixtureID + " " + spinnerItemAndFixtureID.get(itemSelectedInSpinner));
                setView();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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


    private void setView(){
        FixtureRepo fixtureRepo = new FixtureRepo();

        /*
         * getFixtureData returns
         * 0: forward
         * 1: back
         * 2: player
         * 3: myscore
         *
         * 4...15: table stats
         * 16: theirscore
         */

        ArrayList<String> fixtureData = fixtureRepo.getFixtureData(fixtureID, MyClientID.myTeamID);

        String forwardID = fixtureData.get(0);
        String backID = fixtureData.get(1);
        String playerID = fixtureData.get(2);
        myScore.setText(fixtureData.get(3));
        theirScore.setText(fixtureData.get(15));

        MemberRepo memberRepo = new MemberRepo();
        ArrayList<String> playerIDs = new ArrayList<>();
        playerIDs.add(forwardID);
        playerIDs.add(backID);
        playerIDs.add(playerID);

        ArrayList<String> name = memberRepo.getNames(playerIDs);
        if(name != null){
            forward.setText(name.get(0));
            back.setText(name.get(1));
            player.setText(name.get(2));
        }

        String[] titles = new String[12];
        titles[0] = "Tries Scored";
        titles[1] = "Tries Succeeded";
        titles[2] = "Conversions";
        titles[3] = "Conversions Succeeded";
        titles[4] = "Scrums Won";
        titles[5] = "Scrums Lost";
        titles[6] = "Mauls Won";
        titles[7] = "Mauls Lost";
        titles[8] = "Line Outs Won";
        titles[9] = "Line Outs Lost";
        titles[10] = "Drop Goals";
        titles[11] = "Penalty Kicks";

        TableLayout tl = (TableLayout) findViewById(R.id.teamGameTableLayout);
        tl.removeAllViews();

        for (int i = 4; i < 15; i++) { //start at 2 because we dont want Member and fixture ID

            TableRow tr = new TableRow(this);

            TextView labelTitle = new TextView(this);
            labelTitle.setText(titles[i-4]);
            tr.addView(labelTitle);

            TextView labelValue = new TextView(this);
            System.out.println(String.valueOf(i) + " " + fixtureData.get(i));
            labelValue.setText("   " + fixtureData.get(i));
            tr.addView(labelValue);

            tl.addView(tr);

        }




    }

    private void setLayout(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        android.view.ViewGroup.LayoutParams layoutParams1 = overview.getLayoutParams();
        layoutParams1.width = screenWidth/3;
        overview.setLayoutParams(layoutParams1);

        android.view.ViewGroup.LayoutParams layoutParams2 = game.getLayoutParams();
        layoutParams2.width = screenWidth/3;
        game.setLayoutParams(layoutParams2);

        android.view.ViewGroup.LayoutParams layoutParams3 = leaderboard.getLayoutParams();
        layoutParams3.width = screenWidth/3;
        leaderboard.setLayoutParams(layoutParams3);

        overview.setBackgroundColor(Color.LTGRAY);
        game.setBackgroundColor(Color.LTGRAY);
        leaderboard.setBackgroundColor(Color.LTGRAY);
        buttonBarLayout.setBackgroundColor(Color.LTGRAY);

    }



    public void goToOverviewTS(View view) {
        Intent intent = new Intent(this, TeamStatsOverview.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void goToGameTS(View view) {
        Intent intent = new Intent(this, TeamStatsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void goToLeaderboardTS(View view) {
        Intent intent = new Intent(this, TeamStatsLeaderboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
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
}
