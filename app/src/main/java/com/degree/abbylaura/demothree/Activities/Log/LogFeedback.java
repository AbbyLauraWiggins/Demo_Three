package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.NetworkService;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.FeedbackRepo;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class LogFeedback extends Activity {

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    Spinner fixtureSpinner, playerSpinner;
    EditText inputFeedback;
    String fixtureID, playerID;
    float attackRate, defenceRate, effortRate, overallRate;

    RatingBar attackBar, defBar, effortBar, overallBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_feedback_activity);


        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        fixtureSpinner = findViewById(R.id.fixtureSpinner);
        playerSpinner = findViewById(R.id.playerSpinner);
        inputFeedback = findViewById(R.id.inputFeedback);
        fixtureID = "";
        playerID = "";
        attackRate = 0;
        defenceRate = 0;
        effortRate = 0;
        overallRate = 0;

        setBottomBar();
        setFixtureSpinner();
        setPlayerSpinner();
        setTV();
        setRatingBars();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_FEEDBACK);
        registerReceiver(clientReceiver, intentFilter);


    }

    private void setRatingBars(){
        attackBar =  findViewById(R.id.ratingBarAttack);
        attackBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                attackRate = rating;

            }
        });

        defBar =  findViewById(R.id.ratingBarDef);
        defBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                defenceRate = rating;

            }
        });

        effortBar =  findViewById(R.id.ratingBarEffort);
        effortBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                effortRate = rating;

            }
        });

        overallBar =  findViewById(R.id.ratingBarOverall);
        overallBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                overallRate = rating;

            }
        });

    }

    private void setTV() {
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        LinearLayout attack, defence, effort, overall;

        attack = findViewById(R.id.attackTV);
        defence = findViewById(R.id.defenceTV);
        effort = findViewById(R.id.effortTV);
        overall = findViewById(R.id.overallTV);

        android.view.ViewGroup.LayoutParams layoutParams = attack.getLayoutParams();
        layoutParams.width = screenWidth / 5;
        attack.setLayoutParams(layoutParams);

        layoutParams = defence.getLayoutParams();
        layoutParams.width = screenWidth / 5;
        defence.setLayoutParams(layoutParams);

        layoutParams = effort.getLayoutParams();
        layoutParams.width = screenWidth / 5;
        effort.setLayoutParams(layoutParams);

        layoutParams = overall.getLayoutParams();
        layoutParams.width = screenWidth / 5;
        overall.setLayoutParams(layoutParams);
    }

    private void setPlayerSpinner(){
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
        playerSpinner.setAdapter(adapter);

        playerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                playerID = getID(adapterView.getItemAtPosition(i).toString());
                System.out.println("player spinner has selected: " + adapterView.getItemAtPosition(i).toString() + ": " + playerID);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public String getID(String name){
        MemberRepo memberRepo = new MemberRepo();
        return memberRepo.getID(name);
    }

    private void setFixtureSpinner(){
        TeamFixturesRepo tfRepo = new TeamFixturesRepo();

        final ArrayList<ArrayList<String>> fixturesList = tfRepo.getSpinnerList();
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
        Spinner fixtureSpinner = (Spinner) findViewById(R.id.fixtureSpinner);
        fixtureSpinner.setAdapter(adapter);

        fixtureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(i).toString();
                fixtureID = (spinnerItemAndFixtureID.get(itemSelectedInSpinner)); //returns FixtureID of selected item
                System.out.println(fixtureID + " " + spinnerItemAndFixtureID.get(itemSelectedInSpinner));
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

    public void onSubmitClick(View view) {
        FeedbackRepo feedbackRepo = new FeedbackRepo();

        ArrayList<String> toServer = new ArrayList<>();
        toServer.add(playerID);
        toServer.add(fixtureID);
        toServer.add(inputFeedback.getText().toString());
        toServer.add(String.valueOf(attackRate));
        toServer.add(String.valueOf(defenceRate));
        toServer.add(String.valueOf(effortRate));
        toServer.add(String.valueOf(overallRate));

        Intent intent = new Intent(this, NetworkService.class);
        intent.putExtra("TABLESIZE", String.valueOf(feedbackRepo.getTableSize()));
        intent.putExtra("CLASS", toServer);
        intent.putExtra("typeSending", "ADDFEEDBACK");
        this.startService(intent);
    }

    private BroadcastReceiver clientReceiver = new BroadcastReceiver() {

        // Called when the broadcast is received
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive BroadcastReceiver");
            makeToast();
        }

    };

    private void makeToast(){
        Toast.makeText(this, "Feedback Added", Toast.LENGTH_SHORT).show();
        inputFeedback.setText("");
        attackBar.setNumStars(0);
        defBar.setNumStars(0);
        effortBar.setNumStars(0);
        overallBar.setNumStars(0);
    }

    public void onSeePrev(View view) {
        Intent intent = new Intent(this, PreviousFeedback.class);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();
        System.out.println("onPause BroadcastReceiver");

        unregisterReceiver(clientReceiver);
    }

    protected void onResume() {
        super.onResume();
        System.out.println("onResume BroadcastReceiver");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkService.TRANSACTION_DONE_NOTICE);
        registerReceiver(clientReceiver, intentFilter);
    }
}

