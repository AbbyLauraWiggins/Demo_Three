package com.degree.abbylaura.demothree.Activities;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.Log.LogActivity;
import com.degree.abbylaura.demothree.Activities.Members.MembersActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.SignIn.CreateNewTeam;
import com.degree.abbylaura.demothree.Activities.Statistics.MyStats;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.TeamFixturesRepo;
import com.degree.abbylaura.demothree.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class HomeActivity extends Activity {

    TextView nextGameDate, nextGameMonth;
    LinearLayout notice, stats, log, calendar, members, gallery, nextGameLL, nextTrainingLL, nextSocialLL;
    ImageView noticeIV, statsIV, logIV, calendarIV, membersIV, galleryIV, settingsIV;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);

        nextGameDate = findViewById(R.id.next_game_home_tv);
        nextGameMonth = findViewById(R.id.next_game_month_home_tv);

        setButtons();


        try {
            setNextGame();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setButtons(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        notice = findViewById(R.id.notice_home_button);
        stats = findViewById(R.id.stats_home_button);
        log = findViewById(R.id.log_home_button);

        members = findViewById(R.id.members_home_button);

        noticeIV = findViewById(R.id.noticepng);
        statsIV = findViewById(R.id.statpng);
        logIV = findViewById(R.id.logpng);
        settingsIV = findViewById(R.id.settingsButton);

        membersIV = findViewById(R.id.mempng);

        nextGameLL = findViewById(R.id.next_game_ll);
        nextTrainingLL = findViewById(R.id.next_training_ll);
        nextSocialLL = findViewById(R.id.next_social_ll);

        android.view.ViewGroup.LayoutParams layoutParams = notice.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenWidth/3;
        notice.setLayoutParams(layoutParams);
        notice.setVisibility(View.VISIBLE);

        layoutParams = noticeIV.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        noticeIV.setLayoutParams(layoutParams);

        layoutParams = stats.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenWidth/3;
        stats.setLayoutParams(layoutParams);
        stats.setVisibility(View.VISIBLE);

        layoutParams = statsIV.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/4;
        statsIV.setLayoutParams(layoutParams);

        layoutParams = log.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenWidth/2;
        log.setLayoutParams(layoutParams);
        log.setVisibility(View.VISIBLE);

        layoutParams = logIV.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/3;
        logIV.setLayoutParams(layoutParams);

        layoutParams = members.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = screenWidth/2;
        members.setLayoutParams(layoutParams);
        members.setVisibility(View.VISIBLE);

        layoutParams = membersIV.getLayoutParams();
        layoutParams.width = screenWidth/4;
        layoutParams.height = screenWidth/3;
        membersIV.setLayoutParams(layoutParams);


        LinearLayout top = findViewById(R.id.top_linear);
        layoutParams = top.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = (int) (screenHeight/3.5);
        top.setLayoutParams(layoutParams);
        top.setVisibility(View.VISIBLE);

        GridLayout buttonGrid = findViewById(R.id.grid_layout);
        layoutParams = buttonGrid.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = (int) (screenHeight/2);
        buttonGrid.setLayoutParams(layoutParams);



        layoutParams = nextGameLL.getLayoutParams();
        layoutParams.width = screenWidth/3;
        layoutParams.height = screenWidth/3;
        nextGameLL.setLayoutParams(layoutParams);
        nextGameLL.setVisibility(View.VISIBLE);

        layoutParams = nextTrainingLL.getLayoutParams();
        layoutParams.width = screenWidth/3;
        layoutParams.height = screenWidth/3;
        nextTrainingLL.setLayoutParams(layoutParams);
        nextTrainingLL.setVisibility(View.VISIBLE);

        layoutParams = nextSocialLL.getLayoutParams();
        layoutParams.width = screenWidth/3;
        layoutParams.height = screenWidth/3;
        nextSocialLL.setLayoutParams(layoutParams);
        nextSocialLL.setVisibility(View.VISIBLE);


        settingsIV.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.settings);
        draw = barresize(draw);
        settingsIV.setImageDrawable(draw);
    }

    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                screenWidth/10, screenWidth/10, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    public void setNextGame() throws ParseException {

        String fixtureDate = getFixtureDate();

        nextGameDate.setText(fixtureDate.substring(0,2));

        String month = fixtureDate.substring(3,5);

        nextGameMonth.setText(month);
        //TODO show month




    }

    public String getFixtureDate() throws ParseException {
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
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        }

        String fixtureDateToPass = sdf.format(farAway);
        return fixtureDateToPass;
    }

    public void onGoToNotices(View view) {
        Intent goToNotices = new Intent(this, NoticeActivity.class);
        startActivity(goToNotices);
    }

    public void onGoToStats(View view) {
        Intent goToStats = new Intent(this, StatisticsActivity.class);
        startActivity(goToStats);
    }

    public void onGoToLog(View view) {
        //if users permissions = BASIC 0 or ADMIN 1, then go straight to MyStats
        if(MyClientID.myPermissions < 2){
            Intent goToLog = new Intent(this, MyStats.class);
            startActivity(goToLog);
        }
        //else if permissions are LEADER and above, go to log screen for more options
        else{
            Intent goToLog = new Intent(this, LogActivity.class);
            startActivity(goToLog);
        }

    }


    public void onGoToMembers(View view) {
        Intent goToMembers = new Intent(this, MembersActivity.class);
        startActivity(goToMembers);
    }

    public void onGoToProfile(View view) {
        Intent goToProfile = new Intent(this, ProfileActivity.class);
        goToProfile.putExtra("MemberID", MyClientID.myID); //send my own ID as this button is to view own profile
        startActivity(goToProfile);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, CreateNewTeam.class);
        intent.putExtra("PRECEDING", "not new");
        startActivity(intent);
    }
}
