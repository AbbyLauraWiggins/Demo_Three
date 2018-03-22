package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Log.LogActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 07/03/2018.
 */

public class MyStats extends Activity {

    LinearLayout scStat, scLog, gameStat, gameFeedback;
    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;
    ImageView scStatIV, scLogIV, gameStatIV, gameFeedbackIV;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_stats_activity);

        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);

        bbl = findViewById(R.id.buttonBarLayout);

        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);

        scStat = findViewById(R.id.scViewStats);
        scLog = findViewById(R.id.scLogStats);
        gameStat = findViewById(R.id.gameViewStats);
        gameFeedback = findViewById(R.id.feedbackLayout);

        scStatIV = findViewById(R.id.scViewStatsIV);
        scLogIV = findViewById(R.id.scLogStatsIV);
        gameStatIV = findViewById(R.id.gameViewStatsIV);
        gameFeedbackIV = findViewById(R.id.gameFeedbackIV);

        setLayout();
        setBottomBar();
    }

    private void setLayout(){

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenWidth = screenWidth - (screenWidth/50);
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;


        android.view.ViewGroup.LayoutParams layoutParams = scStat.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3.2);
        scStat.setLayoutParams(layoutParams);
        scStat.setVisibility(View.VISIBLE);

        layoutParams = scLog.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3.2);
        scLog.setLayoutParams(layoutParams);
        scLog.setVisibility(View.VISIBLE);

        layoutParams = gameStat.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3.2);
        gameStat.setLayoutParams(layoutParams);
        gameStat.setVisibility(View.VISIBLE);

        layoutParams = gameFeedback.getLayoutParams();
        layoutParams.width = screenWidth/2;
        layoutParams.height = (int) (screenHeight/3.2);
        gameFeedback.setLayoutParams(layoutParams);
        gameFeedback.setVisibility(View.VISIBLE);

        //iconSize = screenHeight/5;
        iconSize = (int) (screenHeight/4.2);

        scLogIV.setImageResource(0);
        Drawable draw = getResources().getDrawable(R.drawable.newlogicon);
        draw = logsize(draw);
        scLogIV.setImageDrawable(draw);


        gameFeedbackIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.feedbackicon);
        draw = logsize(draw);
        gameFeedbackIV.setImageDrawable(draw);


        scStatIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_trending_up_black_48dp);
        draw = resize(draw);
        scStatIV.setImageDrawable(draw);


        gameStatIV.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_trending_up_black_48dp);
        draw = resize(draw);
        gameStatIV.setImageDrawable(draw);



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

    private Drawable resize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                iconSize, iconSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private Drawable logsize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        float height = bitmap.getHeight();
        float width = bitmap.getWidth();
        float scaleFactor = width/height;
        int setwidth = (int) (iconSize * scaleFactor);
        System.out.println(height + " " + width + " " + setwidth);
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                setwidth, iconSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }


    public void goBack(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void onViewSC(View view) {
        Intent intent = new Intent(this, MySCStats.class);
        startActivity(intent);
    }

    public void onViewGameStats(View view) {
        Intent intent = new Intent(this, MyGameStats.class);
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
