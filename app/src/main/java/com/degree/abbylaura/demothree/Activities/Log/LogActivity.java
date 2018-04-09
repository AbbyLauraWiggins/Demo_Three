package com.degree.abbylaura.demothree.Activities.Log;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.MyStats;
import com.degree.abbylaura.demothree.Activities.Statistics.StatisticsActivity;
import com.degree.abbylaura.demothree.Activities.Statistics.TeamStatsOverview;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class LogActivity extends Activity {

    Button mystats;

    LinearLayout trackLL, trainingLL, feedbackLL, myORteamLL;
    ImageView trackIV, trainingIV, feedbackIV, myORteamIV;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    TextView mORt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_activity);

        mORt = findViewById(R.id.mORt);
        trackLL = findViewById(R.id.trackGameStatsLL);
        feedbackLL = findViewById(R.id.logFeedbackLL);
        trainingLL = findViewById(R.id.setUpSessionLL);
        myORteamLL = findViewById(R.id.myORteamLL);

        trackIV = findViewById(R.id.trackImg);
        trainingIV = findViewById(R.id.trainingImg);
        feedbackIV = findViewById(R.id.feedbackImg);
        myORteamIV = findViewById(R.id.mORtImg);

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
        setButtons();

    }

    private void setButtons(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenWidth = screenWidth - (screenWidth/40);
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;

        int perm = MyClientID.myPermissions;

        barSize = screenHeight/4;

        android.view.ViewGroup.LayoutParams layoutParams = trackLL.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3);
        trackLL.setLayoutParams(layoutParams);
        trackLL.setVisibility(View.VISIBLE);

        iconSize = (int) (screenHeight/4);

        trackIV.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.ic_poll_black_48dp);
        draw = barresize(draw);
        trackIV.setImageDrawable(draw);

        layoutParams = feedbackLL.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3);
        feedbackLL.setLayoutParams(layoutParams);
        feedbackLL.setVisibility(View.VISIBLE);

        feedbackIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.feedbackicon);
        draw = barresize(draw);
        feedbackIV.setImageDrawable(draw);

        layoutParams = trainingLL.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3);
        trainingLL.setLayoutParams(layoutParams);
        trainingLL.setVisibility(View.VISIBLE);

        trainingIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.trainingicon);
        draw = barresize(draw);
        trainingIV.setImageDrawable(draw);

        layoutParams = myORteamLL.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3);
        myORteamLL.setLayoutParams(layoutParams);
        myORteamLL.setVisibility(View.VISIBLE);



        if(perm == 3){
            mORt.setText("Team Statistics");
            myORteamIV.setImageResource(0);
            draw = getResources().getDrawable(R.drawable.teamicon);
            draw = barresize(draw);
            myORteamIV.setImageDrawable(draw);
        }else{ //CAL = perm 3: has no personal logs
            mORt.setText("My Statistics and Logs");
            myORteamIV.setImageResource(0);
            draw = getResources().getDrawable(R.drawable.personicon);
            draw = barresize(draw);
            myORteamIV.setImageDrawable(draw);
        }


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

    public void onTrackClick(View view) {
        Intent goToLog = new Intent(this, GameTeamListSetUp.class);
        startActivity(goToLog);
    }

    public void onFeedbackClick(View view) {
        Intent goToLog = new Intent(this, LogFeedback.class);
        startActivity(goToLog);
    }

    public void onSetUpClick(View view) {
        Intent goToLog = new Intent(this, TrainingSetUp.class);
        startActivity(goToLog);
    }

    public void onMyStatsClick(View view) {
        Intent goToLog = new Intent(this, MyStats.class);
        startActivity(goToLog);
    }

    public void onTeamStatsClick(View view) {
        if(mORt.equals("Team Statistics")){
            Intent goToTS = new Intent(this, TeamStatsOverview.class);
            startActivity(goToTS);
        }else{
            Intent goToTS = new Intent(this, MyStats.class);
            startActivity(goToTS);
        }

    }
}
