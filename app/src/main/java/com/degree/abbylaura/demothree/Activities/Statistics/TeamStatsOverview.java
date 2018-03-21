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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.degree.abbylaura.demothree.R;

/**
 * Created by abbylaura on 15/03/2018.
 */

public class TeamStatsOverview extends Activity{
    Button overview, game, leaderboard;
    ButtonBarLayout buttonBarLayout;

    LinearLayout homebbll, noticebbll, profilebbll, logbbll;
    ImageView barNotice, barHome, barLog, barProfile;
    int iconSize, barSize;
    ButtonBarLayout bbl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team_stats_tab_overview);

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
        draw = getResources().getDrawable(R.drawable.ic_chat_black_48dp);
        draw = barresize(draw);
        barHome.setImageDrawable(draw);

        barProfile.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_person_black_48dp);
        draw = barresize(draw);
        barProfile.setImageDrawable(draw);

        barLog.setImageResource(0);
        draw = getResources().getDrawable(R.drawable.ic_note_add_black_48dp);
        draw = barresize(draw);
        barLog.setImageDrawable(draw);

    }

    private Drawable barresize(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,
                barSize, barSize, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }


    private void setLayout(){
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        android.view.ViewGroup.LayoutParams layoutParams1 = overview.getLayoutParams();
        layoutParams1.width = screenWidth/3;
        overview.setLayoutParams(layoutParams1);

        android.view.ViewGroup.LayoutParams layoutParams2 = game.getLayoutParams();
        layoutParams2.width = screenWidth/3;
        overview.setLayoutParams(layoutParams2);

        android.view.ViewGroup.LayoutParams layoutParams3 = leaderboard.getLayoutParams();
        layoutParams3.width = screenWidth/3;
        overview.setLayoutParams(layoutParams3);

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
}
