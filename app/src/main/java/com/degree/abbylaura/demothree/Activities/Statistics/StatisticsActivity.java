package com.degree.abbylaura.demothree.Activities.Statistics;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import com.degree.abbylaura.demothree.Activities.HomeActivity;
import com.degree.abbylaura.demothree.Activities.Log.LogActivity;
import com.degree.abbylaura.demothree.Activities.Notices.NoticeActivity;
import com.degree.abbylaura.demothree.Activities.ProfileActivity;
import com.degree.abbylaura.demothree.Client.MyClientID;
import com.degree.abbylaura.demothree.Database.Repo.MemberRepo;
import com.degree.abbylaura.demothree.Database.Schema.Notice;
import com.degree.abbylaura.demothree.R;

import org.w3c.dom.Text;

/**
 * Created by abbylaura on 09/02/2018.
 */

public class StatisticsActivity extends Activity {

    LinearLayout analyse, mystats, teamstats, homebbll, noticebbll, profilebbll, logbbll;
    ImageView my, team, an, barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    TextView tsTV, msTV, aTV;
    ButtonBarLayout bbl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statistics_activity);


        analyse = findViewById(R.id.analyseButton);
        mystats = findViewById(R.id.myStatsButton);
        teamstats = findViewById(R.id.teamStatsButton);
        my = findViewById(R.id.myStatsImage);
        team = findViewById(R.id.teamStatsImage);
        an = findViewById(R.id.analyseImage);

        bbl = findViewById(R.id.buttonBarLayout);
        barNotice = findViewById(R.id.noticesBarButton);
        barHome = findViewById(R.id.homeBarButton);
        barLog = findViewById(R.id.logBarButton);
        barProfile = findViewById(R.id.profileBarButton);
        homebbll = findViewById(R.id.homeBBLL);
        noticebbll = findViewById(R.id.noticeBBLL);
        profilebbll = findViewById(R.id.profileBBLL);
        logbbll = findViewById(R.id.logBBLL);


        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 30; //room for title
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - 30;


        tsTV = findViewById(R.id.tsTV);
        msTV = findViewById(R.id.msTV);
        aTV = findViewById(R.id.aTV);

        iconSize = screenHeight/5;




        android.view.ViewGroup.LayoutParams layoutParams;

        layoutParams = bbl.getLayoutParams();
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
        draw = getResources().getDrawable(R.drawable.ic_note_add_black_48dp);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

        System.out.println("my permissions: " + MyClientID.myPermissions);
        if(MyClientID.myPermissions > 1){ //LEADER or above - so can view stat analysis
            analyse.setVisibility(View.VISIBLE);

            if(MyClientID.myPermissions == 3){ //3 = CAL = no personal stats - just team and analysis
                layoutParams = teamstats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                teamstats.setLayoutParams(layoutParams);
                teamstats.setVisibility(View.VISIBLE);

                iconSize = (int) (screenHeight/4);

                mystats.removeAllViews();

                team.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.teamicon);
                draw = resize(draw);
                team.setImageDrawable(draw);


                layoutParams = analyse.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/3;
                analyse.setLayoutParams(layoutParams);
                analyse.setVisibility(View.VISIBLE);

                an.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ic_poll_black_48dp);
                draw = resize(draw);
                an.setImageDrawable(draw);


            }else{


                mystats.setVisibility(View.VISIBLE); //show my, teams and analysis

                iconSize = screenHeight/5;

                layoutParams = mystats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/4;
                mystats.setLayoutParams(layoutParams);
                mystats.setVisibility(View.VISIBLE);

                my.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.personicon);
                draw = resize(draw);
                my.setImageDrawable(draw);


                layoutParams = teamstats.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/4;
                teamstats.setLayoutParams(layoutParams);
                teamstats.setVisibility(View.VISIBLE);

                team.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.teamicon);
                draw = resize(draw);
                team.setImageDrawable(draw);

                layoutParams = analyse.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenHeight/4;
                analyse.setLayoutParams(layoutParams);
                analyse.setVisibility(View.VISIBLE);

                an.setImageResource(0);
                draw = getResources().getDrawable(R.drawable.ic_poll_black_48dp);
                draw = resize(draw);
                an.setImageDrawable(draw);

                tsTV.setVisibility(View.VISIBLE);
                msTV.setVisibility(View.VISIBLE);
                aTV.setVisibility(View.VISIBLE);


            }


        }else{ //show just my and team
            analyse.removeAllViews();

            iconSize = (int) (screenHeight/3.5);

            layoutParams = mystats.getLayoutParams();
            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight/3;
            mystats.setLayoutParams(layoutParams);
            mystats.setVisibility(View.VISIBLE);

            my.setImageResource(0);
            draw = getResources().getDrawable(R.drawable.personicon);
            draw = resize(draw);
            my.setImageDrawable(draw);


            layoutParams = teamstats.getLayoutParams();
            layoutParams.width = screenWidth;
            layoutParams.height = screenHeight/3;
            teamstats.setLayoutParams(layoutParams);
            teamstats.setVisibility(View.VISIBLE);

            team.setImageResource(0);
            draw = getResources().getDrawable(R.drawable.teamicon);
            draw = resize(draw);
            team.setImageDrawable(draw);

        }

        tsTV.setTextSize(iconSize/15);
        System.out.println(String.valueOf(iconSize));
        msTV.setTextSize(iconSize/15);
        aTV.setTextSize(iconSize/15);

        TextView title = findViewById(R.id.stats_title);
        title.setTextSize(iconSize/10);

    }


    private Drawable resize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                iconSize, iconSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
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


    public void onBackButton(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    public void goTeamStats(View view) {
        Intent intent = new Intent(this, TeamStatsOverview.class);
        startActivity(intent);
    }

    public void goMyStats(View view) {
        Intent intent = new Intent(this, MyStats.class);
        startActivity(intent);
    }

    public void goAnalyseStats(View view) {
        Intent intent = new Intent(this, AnalyseStats.class);
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
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
}
